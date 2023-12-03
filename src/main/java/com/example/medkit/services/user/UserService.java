package com.example.medkit.services.user;

import com.example.medkit.dto.ReqHeader;
import com.example.medkit.dto.request.auth.RegisterRequest;
import com.example.medkit.dto.request.auth.SmsCheckRequest;
import com.example.medkit.dto.request.auth.SmsRequest;
import com.example.medkit.dto.response.GeneralResponse;

public interface UserService {
    GeneralResponse smsAsk(SmsRequest request, ReqHeader reqHeader);

    GeneralResponse smsCheck(SmsCheckRequest request, ReqHeader reqHeader);

    GeneralResponse register(RegisterRequest request, ReqHeader reqHeader);
}
