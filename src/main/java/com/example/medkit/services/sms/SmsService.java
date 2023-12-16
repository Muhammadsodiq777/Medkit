package com.example.medkit.services.sms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SmsService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    public String sendSmsToUser(String phoneNumber) {
        int smsCode = 123456;

//        sms api request send

        return String.valueOf(smsCode);
    }
}
