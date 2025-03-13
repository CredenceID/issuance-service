package com.credenceid.issuance.service.services;

import com.credenceid.issuance.service.vo.ApplicationRequest;
import com.credenceid.issuance.service.vo.DigitalIdResponse;

public interface IssuanceService {
    public DigitalIdResponse generateDigitalId(ApplicationRequest req);
}
