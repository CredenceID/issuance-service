package com.credenceid.issuance.service.domain.usecase.digitalid.generate;

import com.credenceid.identity.iso18013.mdoc.NameSpace;
import com.credenceid.identity.iso18013.mdoc.mso.*;
import com.credenceid.issuance.service.domain.model.Application;
import com.credenceid.issuance.service.domain.model.DigitalIdResult;
import com.credenceid.issuance.service.domain.usecase.digitalid.generate.issuer.BuildNameSpaceUseCase;
import com.credenceid.issuance.service.domain.usecase.digitalid.generate.mso.BuildDigestIdsUseCase;
import com.credenceid.issuance.service.domain.usecase.digitalid.generate.mso.BuildValidityInfoUseCase;

import java.util.List;

public class GenerateDigitalIdUseCaseImpl implements GenerateDigitalIdUseCase {
    private static final String ALGORITHM = "SHA-256"; // TODO extract this to app config file
    private static final String VERSION = "1.0"; // TODO extract this to app config file + check exact value
    private static final String DOC_TYPE = "org.iso.18013.5.1.mDL"; // TODO extract this to app config file + change to photo ID
    private static final long VALIDITY_PERIOD = 15 * 365 * 24 * 60 * 60 * 1000L; // TODO extract this to app config file + check what this should be (now around 15 years)
    private final BuildNameSpaceUseCase buildNameSpaceUseCase;
    private final BuildDigestIdsUseCase buildDigestIdsUseCase;
    private final BuildValidityInfoUseCase buildValidityInfoUseCase;

    public GenerateDigitalIdUseCaseImpl(
            BuildNameSpaceUseCase buildNameSpaceUseCase,
            BuildDigestIdsUseCase buildDigestIdsUseCase,
            BuildValidityInfoUseCase buildValidityInfoUseCase
    ) {
        this.buildNameSpaceUseCase = buildNameSpaceUseCase;
        this.buildDigestIdsUseCase = buildDigestIdsUseCase;
        this.buildValidityInfoUseCase = buildValidityInfoUseCase;
    }

    @Override
    public DigitalIdResult execute(Application application) {
        // Builds NameSpace with application personal data
        NameSpace nameSpace = buildNameSpaceUseCase.execute(application.personalData());

        // Builds MSO
        // Builds ValueDigests
        DigestIds digestIds = buildDigestIdsUseCase.execute(nameSpace, ALGORITHM);
        ValueDigests valueDigests = new ValueDigests(List.of(digestIds));
        // Builds DeviceKeyInfo
        DeviceKeyInfo deviceKeyInfo = new DeviceKeyInfo(application.deviceKey());
        // Builds ValidityInfo
        ValidityInfo validityInfo = buildValidityInfoUseCase.execute(VALIDITY_PERIOD);

        MobileSecureObject mso = new MobileSecureObject(
                VERSION,
                ALGORITHM,
                valueDigests,
                deviceKeyInfo,
                DOC_TYPE,
                validityInfo
        );

        // Builds DigitalIdResult
        DigitalIdResult digitalIdResult = new DigitalIdResult(
                application.walletToken(),
                null,
                null
        );

        return null;
    }
}
