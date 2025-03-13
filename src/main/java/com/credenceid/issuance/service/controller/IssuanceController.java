package com.credenceid.issuance.service.controller;

import com.credenceid.issuance.service.services.IssuanceService;
import com.credenceid.issuance.service.vo.ApplicationRequest;
import com.credenceid.issuance.service.vo.DigitalIdResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RequestMapping(value = "/issuanceservice")
@RestController
public class IssuanceController {

    private static final Logger logger = LoggerFactory.getLogger(IssuanceController.class);

    @Autowired
    IssuanceService service;

    @PostMapping(value = "/generateDigitalId")
    public DigitalIdResponse generateDigitalId(@RequestBody(required = true) ApplicationRequest applicationRequest) {

        logger.info("WalletToken : {}, DeviceKey : {}, Personal Data : {}",
                applicationRequest.getWalletToken(), applicationRequest.getDeviceKey(), applicationRequest.getPersonalData());

        DigitalIdResponse response = service.generateDigitalId(applicationRequest);

        return response;
    }

}
