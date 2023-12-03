package com.example.medkit.services.user.impl;

import com.example.medkit.dto.PatientDto;
import com.example.medkit.dto.ReqHeader;
import com.example.medkit.dto.TokenDto;
import com.example.medkit.dto.request.auth.*;
import com.example.medkit.dto.response.GeneralResponse;
import com.example.medkit.mapper.PatientMapper;
import com.example.medkit.model.entity.Patient;
import com.example.medkit.model.entity.User;
import com.example.medkit.repository.PatientRepository;
import com.example.medkit.repository.UserRepository;
import com.example.medkit.services.jwt.JwtService;
import com.example.medkit.services.sms.SmsService;
import com.example.medkit.services.source.MessageSourceService;
import com.example.medkit.services.user.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
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
        TokenDto tokenDto = new TokenDto(jwtService.createAccessToken(user, new Date()), jwtService.createRefreshToken(new Date()));
        response.putPOJO("user", patientDto);
        response.putPOJO("token", tokenDto);
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
            TokenDto tokenDto = new TokenDto(jwtService.createAccessToken(user, new Date()), jwtService.createRefreshToken(new Date()));
            response.putPOJO("user", patientDto);
            response.putPOJO("token", tokenDto);
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
}
