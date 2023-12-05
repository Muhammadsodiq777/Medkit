package com.example.medkit.controller.auth;

import com.example.medkit.dto.ReqHeader;
import com.example.medkit.dto.request.auth.EmployeeLoginRequest;
import com.example.medkit.dto.response.GeneralResponse;
import com.example.medkit.services.source.MessageSourceService;
import com.example.medkit.services.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth/employee")
@RequiredArgsConstructor
public class AuthEmployeeController {

    private final UserService userService;
    private final MessageSourceService messageSourceService;

    @PostMapping("login")
    @Operation(summary = "Xodimlarning accountiga kirish uchun")
    public ResponseEntity<GeneralResponse> employeeLogin(@Valid @RequestBody EmployeeLoginRequest request,
                                                         @RequestHeader(name = "lang", required = false, defaultValue = "uz") String lang) {
        if (!request.getPhoneNumber().startsWith("+998")) {
            return ResponseEntity.badRequest().body(GeneralResponse.error(400, messageSourceService.getMessage("phoneNumber.format", lang)));
        }
        ReqHeader reqHeader = new ReqHeader();
        reqHeader.setLang(lang);
        return ResponseEntity.ok(userService.employeeLogin(request, reqHeader));
    }
}
