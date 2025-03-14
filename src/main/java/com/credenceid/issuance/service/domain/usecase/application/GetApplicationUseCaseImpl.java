package com.credenceid.issuance.service.domain.usecase.application;

import com.credenceid.identity.iso18013.cosekey.CoseKey;
import com.credenceid.identity.iso5218.Sex;
import com.credenceid.issuance.service.domain.model.Application;
import com.credenceid.issuance.service.domain.model.PersonalData;
import com.credenceid.issuance.service.vo.ApplicationRequest;
import org.springframework.stereotype.Component;

@Component
public class GetApplicationUseCaseImpl implements GetApplicationUseCase {

    @Override
    public Application execute(ApplicationRequest applicationRequest) {
        PersonalData personalData = new PersonalData(
                applicationRequest.personalData().portrait(),
                applicationRequest.personalData().documentCode(),
                applicationRequest.personalData().issuer(),
                applicationRequest.personalData().documentNumber(),
                applicationRequest.personalData().dateOfBirth(),
                getSexFromString(applicationRequest.personalData().sex()),
                applicationRequest.personalData().dateOfExpiry(),
                applicationRequest.personalData().familyName(),
                applicationRequest.personalData().givenNames()
        );

        return new Application(
            personalData,
            new CoseKey(applicationRequest.deviceKey()),
            applicationRequest.walletToken()
        );
    }

    private Sex getSexFromString(String sex) {
        if (sex.equals("M")) {
            return Sex.MALE;
        } else if (sex.equals("F")) {
            return Sex.FEMALE;
        } else {
            return Sex.NOT_APPLICABLE;
        }
    }
}
