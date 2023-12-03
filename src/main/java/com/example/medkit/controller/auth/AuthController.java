package com.example.medkit.controller.auth;

import com.example.medkit.dto.ReqHeader;
import com.example.medkit.dto.request.auth.*;
import com.example.medkit.dto.response.GeneralResponse;
import com.example.medkit.services.source.MessageSourceService;
import com.example.medkit.services.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth/patient")
public class AuthController {

    private final UserService userService;
    private final MessageSourceService messageSourceService;

    public AuthController(UserService userService, MessageSourceService messageSourceService) {
        this.userService = userService;
        this.messageSourceService = messageSourceService;
    }

    @PostMapping("sms/ask")
    @Operation(summary = "Bemorni telefoniga sms yuborish")
    public ResponseEntity<GeneralResponse> smsAsk(@Valid @RequestBody SmsRequest request,
                                                  @RequestHeader(name = "lang", required = false, defaultValue = "uz") String lang,
                                                  @RequestHeader(name = "User-Agent", required = false, defaultValue = "") String userAgent) {
        if (!request.getPhoneNumber().startsWith("+998")) {
            return ResponseEntity.badRequest().body(GeneralResponse.error(400, messageSourceService.getMessage("phoneNumber.format", lang)));
        }
        ReqHeader reqHeader = new ReqHeader();
        reqHeader.setLang(lang);
        reqHeader.setUserAgent(userAgent);
        return ResponseEntity.ok(userService.smsAsk(request, reqHeader));
    }

    @PostMapping("sms/check")
    @Operation(summary = "Bemorni telefoniga yuborilgan smsni check qilish")
    public ResponseEntity<GeneralResponse> smsCheck(@Valid @RequestBody SmsCheckRequest request,
                                                    @RequestHeader(name = "lang", required = false, defaultValue = "uz") String lang,
                                                    @RequestHeader(name = "User-Agent", required = false, defaultValue = "") String userAgent) {
        if (!request.getPhoneNumber().startsWith("+998")) {
            return ResponseEntity.badRequest().body(GeneralResponse.error(400, messageSourceService.getMessage("phoneNumber.format", lang)));
        }
        ReqHeader reqHeader = new ReqHeader();
        reqHeader.setLang(lang);
        reqHeader.setUserAgent(userAgent);
        return ResponseEntity.ok(userService.smsCheck(request, reqHeader));
    }

    @PostMapping("register")
    @Operation(summary = "Bemorni uchun account yaratish")
    public ResponseEntity<GeneralResponse> register(@Valid @RequestBody RegisterRequest request,
                                                    @RequestHeader(name = "lang", required = false, defaultValue = "uz") String lang,
                                                    @RequestHeader(name = "User-Agent", required = false, defaultValue = "") String userAgent) {
        if (!request.getPhoneNumber().startsWith("+998")) {
            return ResponseEntity.badRequest().body(GeneralResponse.error(400, messageSourceService.getMessage("phoneNumber.format", lang)));
        }
        ReqHeader reqHeader = new ReqHeader();
        reqHeader.setUserAgent(userAgent);
        reqHeader.setLang(lang);
        return ResponseEntity.ok(userService.register(request, reqHeader));
    }

    @PostMapping("login")
    @Operation(summary = "Bemor accountiga kirish")
    public ResponseEntity<GeneralResponse> login(@Valid @RequestBody LoginRequest request,
                                                  @RequestHeader(name = "lang", required = false, defaultValue = "uz") String lang,
                                                  @RequestHeader(name = "User-Agent", required = false, defaultValue = "") String userAgent) {
        if (!request.getPhoneNumber().startsWith("+998")) {
            return ResponseEntity.badRequest().body(GeneralResponse.error(400, messageSourceService.getMessage("phoneNumber.format", lang)));
        }
        ReqHeader reqHeader = new ReqHeader();
        reqHeader.setUserAgent(userAgent);
        reqHeader.setLang(lang);
        return ResponseEntity.ok(userService.login(request, reqHeader));
    }

    @PostMapping("forgot-password")
    @Operation(summary = "Parolni unutganda Forgot password")
    public ResponseEntity<GeneralResponse> forgotPassword(@Valid @RequestBody ForgotPasswordRequest request,
                                                  @RequestHeader(name = "lang", required = false, defaultValue = "uz") String lang,
                                                  @RequestHeader(name = "User-Agent", required = false, defaultValue = "") String userAgent) {
        if (!request.getPhoneNumber().startsWith("+998")) {
            return ResponseEntity.badRequest().body(GeneralResponse.error(400, messageSourceService.getMessage("phoneNumber.format", lang)));
        }
        ReqHeader reqHeader = new ReqHeader();
        reqHeader.setUserAgent(userAgent);
        reqHeader.setLang(lang);
        return ResponseEntity.ok(userService.forgotPassword(request, reqHeader));
    }

    @PostMapping("refresh-token")
    @Operation(summary = "Token expire bolganda refresh token")
    public ResponseEntity<GeneralResponse> refreshToken(@RequestParam(name = "refresh") String refreshToken,
                                                        @RequestHeader(name = "lang", required = false, defaultValue = "uz") String lang,
                                                        @RequestHeader(name = "User-Agent", required = false, defaultValue = "") String userAgent) {
        ReqHeader reqHeader = new ReqHeader();
        reqHeader.setUserAgent(userAgent);
        reqHeader.setLang(lang);
        return ResponseEntity.ok(userService.refreshToken(refreshToken, reqHeader));
    }

    @PostMapping("employee-login")
    @Operation(summary = "Xodimlarning accountiga kirish uchun")
    public ResponseEntity<GeneralResponse> employeeLogin() {
        return null;
    }
}
