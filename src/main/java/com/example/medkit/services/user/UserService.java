package com.example.medkit.services.user;

import com.example.medkit.dto.request.auth.SmsRequest;
import com.example.medkit.dto.response.GeneralResponse;

public interface UserService {
    GeneralResponse smsAsk(SmsRequest request);
}
