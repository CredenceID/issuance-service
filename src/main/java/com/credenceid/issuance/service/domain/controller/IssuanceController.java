package com.credenceid.issuance.service.domain.controller;

import com.credenceid.issuance.service.domain.model.DigitalIdResult;
import com.credenceid.issuance.service.vo.ApplicationRequest;
import org.springframework.web.bind.annotation.RequestBody;

public interface IssuanceController {
    DigitalIdResult generateDigitalId(@RequestBody ApplicationRequest applicationRequest);
}
