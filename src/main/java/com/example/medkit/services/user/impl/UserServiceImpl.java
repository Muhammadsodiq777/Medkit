package com.example.medkit.services.user.impl;

import com.example.medkit.dto.PatientDto;
import com.example.medkit.dto.ReqHeader;
import com.example.medkit.dto.AuthenticationResponse;
import com.example.medkit.dto.UserDto;
import com.example.medkit.dto.request.auth.*;
import com.example.medkit.dto.request.employee.EmployeeDto;
import com.example.medkit.dto.response.GeneralResponse;
import com.example.medkit.mapper.EmployeeMapper;
import com.example.medkit.mapper.PatientMapper;
import com.example.medkit.model.entity.Employee;
import com.example.medkit.model.entity.Patient;
import com.example.medkit.model.entity.Roles;
import com.example.medkit.model.entity.User;
import com.example.medkit.model.enums.Role;
import com.example.medkit.repository.EmployeeRepository;
import com.example.medkit.repository.PatientRepository;
import com.example.medkit.repository.RoleRepository;
import com.example.medkit.repository.UserRepository;
import com.example.medkit.services.jwt.JwtService;
import com.example.medkit.services.sms.SmsService;
import com.example.medkit.services.source.MessageSourceService;
import com.example.medkit.services.user.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final SmsService smsService;
    private final MessageSourceService messageSourceService;
    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;
    private final ObjectMapper objectMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final EmployeeMapper employeeMapper;
    private final EmployeeRepository employeeRepository;
    private final RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;

    @Override
    public GeneralResponse smsAsk(SmsRequest request, ReqHeader reqHeader) {
        if (request.getSessionKey() == null) {
            return GeneralResponse.error(400, messageSourceService.getMessage("session.key.required", reqHeader.getLang()));
        }
        User user = userRepository.findByPhoneNumber(request.getPhoneNumber());
        if (user == null) {
            user = new User();
        }
        String smsCode = smsService.sendSmsToUser(request.getPhoneNumber());;
        user.setSmsCode(smsCode);
        user.setPhoneNumber(request.getPhoneNumber());
        user.setSessionKey(request.getSessionKey());
        userRepository.save(user);
        request = new SmsRequest();
        request.setPhoneNumber(user.getPhoneNumber());
        return GeneralResponse.success(reqHeader);
    }

    @Override
    public GeneralResponse smsCheck(SmsCheckRequest request, ReqHeader reqHeader) {
        User user = userRepository.findByPhoneNumber(request.getPhoneNumber());
        if (user == null)
            return GeneralResponse.error(404, messageSourceService.getMessage("user.notfound", reqHeader.getLang()));

        if (!user.getSessionKey().equals(request.getSessionKey()))
            return GeneralResponse.error(400, messageSourceService.getMessage("session.incorrect", reqHeader.getLang()));

        if (user.getSmsCode().equals(request.getCode())) {
            return GeneralResponse.error(400, messageSourceService.getMessage("code.incorrect", reqHeader.getLang()));
        }
        boolean isExist = user.getPassword() != null;
        String sessionKey = UUID.randomUUID().toString();
        sessionKey = sessionKey.replaceAll("-", "hdsh");
        user.setSessionKey(sessionKey);
        user.setSmsCode(null);
        user.setActive(true);
        userRepository.save(user);
        return GeneralResponse.success(new CheckResponse(isExist, user.getPhoneNumber(), user.getSessionKey()));
    }

    @Override
    public GeneralResponse register(RegisterRequest request, ReqHeader reqHeader) {
        User user = userRepository.findByPhoneNumber(request.getPhoneNumber());
        if (user == null || !user.isActive() || user.getPassword() == null)
            return GeneralResponse.error(404, messageSourceService.getMessage("user.notfound", reqHeader.getLang()));

        if (!user.getSessionKey().equals(request.getSessionKey()))
            return GeneralResponse.error(400, messageSourceService.getMessage("session.incorrect", reqHeader.getLang()));

        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
        Patient patient = new Patient();
        patient.setFirstname(request.getFirstname());
        patient.setLastname(request.getLastname());
        patient.setParentName(request.getSurname());
        patient.setUser(user);
        patientRepository.save(patient);
        ObjectNode response = objectMapper.createObjectNode();
        PatientDto patientDto = patientMapper.toDto(patient);
        AuthenticationResponse authenticationResponse = new AuthenticationResponse(jwtService.createAccessToken(user, new Date()), jwtService.createRefreshToken(new Date()));
        response.putPOJO("user", patientDto);
        response.putPOJO("token", authenticationResponse);
        return GeneralResponse.success(response);
    }

    @Override
    public GeneralResponse login(LoginRequest request, ReqHeader reqHeader) {
        User user = userRepository.findByPhoneNumber(request.getPhoneNumber());
        if (user == null)
            return GeneralResponse.error(404, messageSourceService.getMessage("user.notfound", reqHeader.getLang()));

        if (!user.getSessionKey().equals(request.getSessionKey()))
            return GeneralResponse.error(400, messageSourceService.getMessage("session.incorrect", reqHeader.getLang()));

        ObjectNode response = objectMapper.createObjectNode();
        if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            PatientDto patientDto = patientMapper.toDto(patientRepository.findByUser(user));
            AuthenticationResponse authenticationResponse = new AuthenticationResponse(jwtService.createAccessToken(user, new Date()), jwtService.createRefreshToken(new Date()));
            response.putPOJO("user", patientDto);
            response.putPOJO("token", authenticationResponse);
            return GeneralResponse.success(response);
        }
        return GeneralResponse.error(404, messageSourceService.getMessage("user.password", reqHeader.getLang()));
    }

    @Override
    public GeneralResponse forgotPassword(ForgotPasswordRequest request, ReqHeader reqHeader) {
        User user = userRepository.findByPhoneNumber(request.getPhoneNumber());
        if (user == null)
            return GeneralResponse.error(404, messageSourceService.getMessage("user.notfound", reqHeader.getLang()));

        if (!user.getSessionKey().equals(request.getSessionKey()))
            return GeneralResponse.error(400, messageSourceService.getMessage("session.incorrect", reqHeader.getLang()));

        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
        return GeneralResponse.success(messageSourceService.getMessage("change.password.success", reqHeader.getLang()));
    }

    @Override
    public GeneralResponse refreshToken(String refreshToken, ReqHeader reqHeader) {
        return GeneralResponse.success("");
    }

    @Override
    public GeneralResponse employeeLogin(EmployeeLoginRequest request, ReqHeader reqHeader) {
//        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getPhoneNumber(), request.getPassword()));
        User user = userRepository.findByPhoneNumber(request.getUsername());
        if (user == null) {
            return GeneralResponse.error(404, messageSourceService.getMessage("user.employee.notfound", reqHeader.getLang()));
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return GeneralResponse.success(messageSourceService.getMessage("change.password.success", reqHeader.getLang()));
        }
        ObjectNode response = objectMapper.createObjectNode();

        Employee employee = employeeRepository.findByUser(user);
        if (employee == null) {
            return GeneralResponse.error(404, messageSourceService.getMessage("user.employee.notfound", reqHeader.getLang()));
        }
        EmployeeDto employeeDto = employeeMapper.toDto(employee);
        response.putPOJO("user", employeeDto);
        response.putPOJO("token", new AuthenticationResponse(jwtService.createAccessToken(user, new Date()), jwtService.createRefreshToken(new Date())));
        return GeneralResponse.success(response);
    }

    @Override
    public User saveHospitalUser(UserDto userDto, User user, Role roleName) {
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Set<Roles> roles = user.getRoles();
        Roles role = roleRepository.findByRoleName(roleName);
        roles.add(role);
        user.setRoles(roles);
        userRepository.save(user);
        return user;
    }

    private boolean isSuperAdmin(Set<Roles> roles) {
        for (Roles role: roles) {
            if (role.getRoleName() == Role.ROLE_SUPER_ADMIN) {
                return true;
            }
        }
        return false;
    }


    private boolean isOrganisation(Set<Roles> roles) {
        for (Roles role: roles) {
            if (role.getRoleName().getValue().contains("ORGANIZATION"))
                return true;
        }
        return false;
    }
}
