package com.example.medkit.services.hospital;

import com.example.medkit.dto.ReqHeader;
import com.example.medkit.dto.request.hospital.HospitalRequest;
import com.example.medkit.dto.response.GeneralResponse;

public interface HospitalService {
    GeneralResponse createHospital(HospitalRequest request, ReqHeader reqHeader);

    GeneralResponse deleteHospitalById(Long id, String lang);

    GeneralResponse getHospital(Long id, String lang);
}
