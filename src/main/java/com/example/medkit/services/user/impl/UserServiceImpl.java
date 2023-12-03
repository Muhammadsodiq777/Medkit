package com.example.medkit.services.user.impl;

import com.example.medkit.dto.request.auth.SmsRequest;
import com.example.medkit.dto.response.GeneralResponse;
import com.example.medkit.repository.UserRepository;
import com.example.medkit.services.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public GeneralResponse smsAsk(SmsRequest request) {
        return null;
    }
}
