package com.credenceid.issuance.service.services;

import com.credenceid.identity.iso18013.cosekey.CoseKey;
import com.credenceid.issuance.service.domain.model.Application;
import com.credenceid.issuance.service.domain.model.DigitalIdResult;
import com.credenceid.issuance.service.domain.model.PersonalData;
import com.credenceid.issuance.service.vo.ApplicationRequest;
import com.credenceid.issuance.service.vo.DigitalIdResponse;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class IssuanceServiceImpl implements IssuanceService {

    @Override
    public DigitalIdResponse generateDigitalId(ApplicationRequest request) {
        Application application = getApplicationData(request);

        /* created dummy response */
        //TODO implement generation of digitalId
        DigitalIdResult result = new DigitalIdResult(application.walletToken(), "testDigitalKey".getBytes(StandardCharsets.UTF_8), "testNameSpaces".getBytes(StandardCharsets.UTF_8));
        DigitalIdResponse response = new DigitalIdResponse();
        response.setWalletToken(result.walletToken());
        response.setMso(result.mso());
        response.setNameSpaces(result.nameSpaces());

        return response;
    }

    private PersonalData getPersonalData(ApplicationRequest request) {
        return new PersonalData(
                request.getPersonalData().getPortrait(),
                request.getPersonalData().getDocumentCode(),
                request.getPersonalData().getIssuer(),
                request.getPersonalData().getDocumentNumber(),
                request.getPersonalData().getDateOfBirth(),
                request.getPersonalData().getSex(),
                request.getPersonalData().getDateOfExpiry(),
                request.getPersonalData().getFamilyName(),
                request.getPersonalData().getGivenNames());
    }

    private Application getApplicationData(ApplicationRequest request) {
        CoseKey deviceKey = new CoseKey(request.getDeviceKey());
        return new Application(getPersonalData(request), deviceKey, request.getWalletToken());
    }

}
