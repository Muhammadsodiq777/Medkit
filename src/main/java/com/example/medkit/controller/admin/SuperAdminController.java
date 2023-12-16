package com.example.medkit.controller.admin;

import com.example.medkit.dto.ReqHeader;
import com.example.medkit.dto.request.hospital.HospitalRequest;
import com.example.medkit.dto.response.GeneralResponse;
import com.example.medkit.services.hospital.HospitalService;
import com.example.medkit.services.source.MessageSourceService;
import com.example.medkit.utils.RegexUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/")
@RequiredArgsConstructor
public class SuperAdminController {

    private final HospitalService hospitalService;
    private final MessageSourceService messageSourceService;

    @PostMapping("create/hospital")
    public ResponseEntity<GeneralResponse> createHospital(@Valid @RequestBody HospitalRequest request,
                                                          @RequestHeader(name = "lang", defaultValue = "uz", required = false) String lang) {
        if (!RegexUtils.isPhoneNumber(request.getPhoneNumber()) && !RegexUtils.isEmailAddress(request.getEmail())) {
            return ResponseEntity.ok(GeneralResponse.error(400, messageSourceService.getMessage("username.format", lang)));
        }
        ReqHeader reqHeader = new ReqHeader();
        reqHeader.setLang(lang);
        return ResponseEntity.ok(hospitalService.createHospital(request, reqHeader));
    }

    @GetMapping("byId/{id}")
    public ResponseEntity<GeneralResponse> getHospitalById(@PathVariable(name = "id") Long id,
                                                           @RequestHeader(name = "lang", required = false, defaultValue = "uz") String lang) {
        return ResponseEntity.ok(hospitalService.getHospital(id, lang));
    }

    @DeleteMapping("delete/hospital/{id}")
    public ResponseEntity<GeneralResponse> deleteHospital(@PathVariable(name = "id") Long id,
                                                          @RequestHeader(name = "lang", required = false, defaultValue = "uz") String lang) {
        return ResponseEntity.ok(hospitalService.deleteHospitalById(id, lang));
    }
}
