package com.credenceid.issuance.service.controller;

import com.credenceid.identity.iso18013.cosekey.CoseKey;
import com.credenceid.issuance.service.domain.model.Application;
import com.credenceid.issuance.service.domain.model.DigitalIdResult;
import com.credenceid.issuance.service.domain.model.PersonalData;
import com.credenceid.issuance.service.vo.ApplicationRequest;
import com.credenceid.issuance.service.vo.DigitalIdResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

@RequestMapping(value = "/issuanceservice")
@RestController
public class IssuanceController {

    private static final Logger logger = LoggerFactory.getLogger(IssuanceController.class);


    /**
     * Endpoint to generate digital id from application data
     *
     * @param applicationRequest
     * @return the generated digital Id
     */
    @PostMapping(value = "/generateDigitalId")
    public DigitalIdResponse generateDigitalId(@RequestBody(required = true) ApplicationRequest applicationRequest) {

        logger.info("WalletToken : {}, DeviceKey : {}, Personal Data : {}",
                applicationRequest.getWalletToken(), applicationRequest.getDeviceKey(), applicationRequest.getPersonalData());
        Application application = getApplicationData(applicationRequest);

        /* created dummy response */
        //TODO implement generation of digitalId
        DigitalIdResult result = new DigitalIdResult(application.walletToken(), "testDigitalKey".getBytes(StandardCharsets.UTF_8), "testNameSpaces".getBytes(StandardCharsets.UTF_8));
        DigitalIdResponse response = new DigitalIdResponse();
        response.setWalletToken(result.walletToken());
        response.setMso(result.mso());
        response.setNameSpaces(result.nameSpaces());
        return response;
    }

    /**
     * fetch PersonalData from applicationRequest
     *
     * @param applicationRequest
     * @return PersonalData
     */
    private PersonalData getPersonalData(ApplicationRequest applicationRequest) {
        return new PersonalData(
                applicationRequest.getPersonalData().getPortrait(),
                applicationRequest.getPersonalData().getDocumentCode(),
                applicationRequest.getPersonalData().getIssuer(),
                applicationRequest.getPersonalData().getDocumentNumber(),
                applicationRequest.getPersonalData().getDateOfBirth(),
                applicationRequest.getPersonalData().getSex(),
                applicationRequest.getPersonalData().getDateOfExpiry(),
                applicationRequest.getPersonalData().getFamilyName(),
                applicationRequest.getPersonalData().getGivenNames());
    }

    /**
     * fetch ApplicationData from ApplicationRequest
     *
     * @param applicationRequest
     * @return Application
     */
    private Application getApplicationData(ApplicationRequest applicationRequest) {
        CoseKey deviceKey = new CoseKey(applicationRequest.getDeviceKey());
        return new Application(getPersonalData(applicationRequest), deviceKey, applicationRequest.getWalletToken());
    }

}
