package com.example.medkit.services.hospital.impl;

import com.example.medkit.dto.ReqHeader;
import com.example.medkit.dto.UserDto;
import com.example.medkit.dto.request.hospital.HospitalDto;
import com.example.medkit.dto.request.hospital.HospitalRequest;
import com.example.medkit.dto.response.GeneralResponse;
import com.example.medkit.dto.response.SaveResponse;
import com.example.medkit.mapper.HospitalMapper;
import com.example.medkit.model.entity.Hospital;
import com.example.medkit.model.entity.User;
import com.example.medkit.model.enums.Role;
import com.example.medkit.repository.HospitalRepository;
import com.example.medkit.repository.UserRepository;
import com.example.medkit.services.employee.EmployeeService;
import com.example.medkit.services.hospital.HospitalService;
import com.example.medkit.services.source.MessageSourceService;
import com.example.medkit.services.user.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HospitalServiceImpl implements HospitalService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final HospitalRepository hospitalRepository;
    private final MessageSourceService messageSourceService;
    private final UserRepository userRepository;
    private final HospitalMapper hospitalMapper;
    private final UserService userService;
    private final EmployeeService employeeService;
    @Override
    public GeneralResponse createHospital(HospitalRequest request, ReqHeader reqHeader) {
        Hospital hospital = hospitalRepository.findByAccountNumber(request.getAccountNumber());
        if (hospital != null) {
            return GeneralResponse.error(400, messageSourceService.getMessage("exist.hospital", reqHeader.getLang()));
        }
        User user = userRepository.findByPhoneNumber(request.getPhoneNumber());
        if (user == null) {
            user = userRepository.findByEmail(request.getEmail());
            if (user == null)
                user = new User();
        }
        if (!user.getPhoneNumber().equals(request.getPhoneNumber()) || !user.getEmail().equals(request.getEmail())) {
            return GeneralResponse.error(400, messageSourceService.getMessage("exist.hospital.wrong", reqHeader.getLang()));
        }
        user = userService.saveHospitalUser(new UserDto(request.getEmail(), request.getPhoneNumber(), request.getPassword()), user, Role.ROLE_ORGANIZATION_ADMIN);
        hospital = hospitalMapper.toHospital(request);
        hospital.setActive(true);
        hospitalRepository.save(hospital);
        employeeService.saveEmployee(user, hospital, request);
        return GeneralResponse.success(new SaveResponse(hospital.getId()));
    }

    @Override
    public GeneralResponse deleteHospitalById(Long id, String lang) {
        Optional<Hospital> optionalHospital = hospitalRepository.findById(id);
        if (optionalHospital.isPresent()) {
            Hospital hospital = optionalHospital.get();
            hospital.setActive(false);
            hospitalRepository.save(hospital);
            return GeneralResponse.success();
        }
        return GeneralResponse.error(404, String.format(messageSourceService.getMessage("not.found", lang), hospitalByLang(lang)));
    }

    @Override
    public GeneralResponse getHospital(Long id, String lang) {
        Optional<Hospital> optionalHospital = hospitalRepository.findById(id);
        if (optionalHospital.isEmpty()) {
            return GeneralResponse.error(404, String.format(messageSourceService.getMessage("not.found", lang), hospitalByLang(lang)));
        }
        Hospital hospital = optionalHospital.get();
        HospitalDto response = hospitalMapper.toDto(hospital);
        return GeneralResponse.success(response);
    }

    private String hospitalByLang(String lang) {
        return getValue(lang, "Kasalxona", "Больница", "Hospital");
    }

    private String getValue(String lang, String uz, String ru, String en) {
        return switch (lang) {
            case "uz" -> uz;
            case "ru" -> ru;
            case "en" -> en;
            default -> uz;
        };
    }
}
