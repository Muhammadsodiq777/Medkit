package com.example.medkit.services.user;

import com.example.medkit.dto.ReqHeader;
import com.example.medkit.dto.UserDto;
import com.example.medkit.dto.request.auth.*;
import com.example.medkit.dto.response.GeneralResponse;
import com.example.medkit.model.entity.User;
import com.example.medkit.model.enums.Role;
import org.springframework.http.ResponseEntity;

public interface UserService {
    GeneralResponse smsAsk(SmsRequest request, ReqHeader reqHeader);

    GeneralResponse smsCheck(SmsCheckRequest request, ReqHeader reqHeader);

    GeneralResponse register(RegisterRequest request, ReqHeader reqHeader);

    GeneralResponse login(LoginRequest request, ReqHeader reqHeader);

    GeneralResponse forgotPassword(ForgotPasswordRequest request, ReqHeader reqHeader);

    GeneralResponse refreshToken(String refreshToken, ReqHeader reqHeader);

    GeneralResponse employeeLogin(EmployeeLoginRequest request, ReqHeader reqHeader);

    User saveHospitalUser(UserDto userDto, User user, Role role);
}
