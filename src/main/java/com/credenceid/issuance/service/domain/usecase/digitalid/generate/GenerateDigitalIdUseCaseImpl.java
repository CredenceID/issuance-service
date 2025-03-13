package com.credenceid.issuance.service.domain.usecase.digitalid.generate;

import com.credenceid.identity.iso18013.mdoc.NameSpace;
import com.credenceid.identity.iso18013.mdoc.mso.*;
import com.credenceid.issuance.service.data.repository.ApplicationPropertiesRepository;
import com.credenceid.issuance.service.domain.model.Application;
import com.credenceid.issuance.service.domain.model.DigitalId;
import com.credenceid.issuance.service.domain.usecase.digitalid.generate.issuer.BuildNameSpaceUseCase;
import com.credenceid.issuance.service.domain.usecase.digitalid.generate.mso.BuildDigestIdsUseCase;
import com.credenceid.issuance.service.domain.usecase.digitalid.generate.mso.BuildValidityInfoUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GenerateDigitalIdUseCaseImpl implements GenerateDigitalIdUseCase {
    private final ApplicationPropertiesRepository applicationPropertiesRepository;
    private final BuildNameSpaceUseCase buildNameSpaceUseCase;
    private final BuildDigestIdsUseCase buildDigestIdsUseCase;
    private final BuildValidityInfoUseCase buildValidityInfoUseCase;

    @Autowired
    public GenerateDigitalIdUseCaseImpl(
            ApplicationPropertiesRepository applicationPropertiesRepository,
            BuildNameSpaceUseCase buildNameSpaceUseCase,
            BuildDigestIdsUseCase buildDigestIdsUseCase,
            BuildValidityInfoUseCase buildValidityInfoUseCase
    ) {
        this.applicationPropertiesRepository = applicationPropertiesRepository;
        this.buildNameSpaceUseCase = buildNameSpaceUseCase;
        this.buildDigestIdsUseCase = buildDigestIdsUseCase;
        this.buildValidityInfoUseCase = buildValidityInfoUseCase;
    }

    @Override
    public DigitalId execute(Application application) {
        // Builds NameSpace with application personal data
        NameSpace nameSpace = buildNameSpaceUseCase.execute(application.personalData());

        // Builds MSO
        // Builds ValueDigests
        DigestIds digestIds = buildDigestIdsUseCase.execute(nameSpace, applicationPropertiesRepository.getDigestAlgorithm());
        ValueDigests valueDigests = new ValueDigests(List.of(digestIds));
        // Builds DeviceKeyInfo
        DeviceKeyInfo deviceKeyInfo = new DeviceKeyInfo(application.deviceKey());
        // Builds ValidityInfo
        ValidityInfo validityInfo = buildValidityInfoUseCase.execute(applicationPropertiesRepository.getValidityPeriod());

        MobileSecureObject mso = new MobileSecureObject(
                applicationPropertiesRepository.getMsoVersion(),
                applicationPropertiesRepository.getDigestAlgorithm(),
                valueDigests,
                deviceKeyInfo,
                applicationPropertiesRepository.getDocType(),
                validityInfo
        );

        // Builds DigitalId
        return new DigitalId(nameSpace, mso);
    }
}
