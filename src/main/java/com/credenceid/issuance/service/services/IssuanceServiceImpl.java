package com.credenceid.issuance.service.services;

import com.credenceid.identity.iso18013.cosekey.CoseKey;
import com.credenceid.issuance.service.domain.model.Application;
import com.credenceid.issuance.service.domain.model.DigitalIdResult;
import com.credenceid.issuance.service.domain.model.PersonalData;
import com.credenceid.issuance.service.vo.ApplicationRequest;
import com.credenceid.issuance.service.vo.DigitalIdResponse;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

/**
 * Implementation of IssuanceService interface
 */
@Service
public class IssuanceServiceImpl implements IssuanceService {

    /**
     * process ApplicationRequest and generate DigitalId against application data
     *
     * @param applicationRequest
     * @return DigitalIdResponse
     */
    @Override
    public DigitalIdResponse generateDigitalId(ApplicationRequest applicationRequest) {
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
