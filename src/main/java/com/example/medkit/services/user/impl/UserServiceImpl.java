package com.example.medkit.services.user.impl;

import com.example.medkit.dto.ReqHeader;
import com.example.medkit.dto.request.auth.CheckResponse;
import com.example.medkit.dto.request.auth.RegisterRequest;
import com.example.medkit.dto.request.auth.SmsCheckRequest;
import com.example.medkit.dto.request.auth.SmsRequest;
import com.example.medkit.dto.response.GeneralResponse;
import com.example.medkit.model.entity.Patient;
import com.example.medkit.model.entity.User;
import com.example.medkit.repository.PatientRepository;
import com.example.medkit.repository.UserRepository;
import com.example.medkit.services.sms.SmsService;
import com.example.medkit.services.source.MessageSourceService;
import com.example.medkit.services.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final SmsService smsService;
    private final MessageSourceService messageSourceService;
    private final PatientRepository patientRepository;

    @Override
    public GeneralResponse smsAsk(SmsRequest request, ReqHeader reqHeader) {
        if (!request.getPhoneNumber().startsWith("+998")) {
            return GeneralResponse.error(400, messageSourceService.getMessage("phoneNumber.format", reqHeader.getLang()));
        }
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
        if (!request.getPhoneNumber().startsWith("+998")) {
            return GeneralResponse.error(400, messageSourceService.getMessage("phoneNumber.format", reqHeader.getLang()));
        }
        User user = userRepository.findByPhoneNumber(request.getPhoneNumber());
        if (user == null)
            return GeneralResponse.error(404, messageSourceService.getMessage("user.notfound", reqHeader.getLang()));

        if (!user.getSessionKey().equals(request.getSessionKey()))
            return GeneralResponse.error(400, messageSourceService.getMessage("session.incorrect", reqHeader.getLang()));

        if (user.getSmsCode().equals(request.getCode())) {
            return GeneralResponse.error(400, messageSourceService.getMessage("code.incorrect", reqHeader.getLang()));
        }
        boolean isExist = user.getPassword() != null;
        user.setSmsCode(null);
        user.setActive(true);
        userRepository.save(user);
        return GeneralResponse.success(new CheckResponse(isExist, user.getPhoneNumber()));
    }

    @Override
    public GeneralResponse register(RegisterRequest request, ReqHeader reqHeader) {
        User user = userRepository.findByPhoneNumber(request.getPhoneNumber());
        if (user == null)
            return GeneralResponse.error(404, messageSourceService.getMessage("user.notfound", reqHeader.getLang()));
        Patient patient = new Patient();
        patient.setFirstname(request.getFirstname());
        patient.setLastname(request.getLastname());
        patient.setParentName(request.getSurname());
        patient.setUser(user);
        patientRepository.save(patient);
        return null;
    }
}
