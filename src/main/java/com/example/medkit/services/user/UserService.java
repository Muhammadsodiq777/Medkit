package com.example.medkit.services.user;

import com.example.medkit.dto.ReqHeader;
import com.example.medkit.dto.request.auth.*;
import com.example.medkit.dto.response.GeneralResponse;

public interface UserService {
    GeneralResponse smsAsk(SmsRequest request, ReqHeader reqHeader);

    GeneralResponse smsCheck(SmsCheckRequest request, ReqHeader reqHeader);

    GeneralResponse register(RegisterRequest request, ReqHeader reqHeader);

    GeneralResponse login(LoginRequest request, ReqHeader reqHeader);

    GeneralResponse forgotPassword(ForgotPasswordRequest request, ReqHeader reqHeader);

    GeneralResponse refreshToken(String refreshToken, ReqHeader reqHeader);
}
