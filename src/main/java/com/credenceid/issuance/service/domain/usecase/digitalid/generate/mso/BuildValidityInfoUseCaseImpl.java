package com.credenceid.issuance.service.domain.usecase.digitalid.generate.mso;

import com.credenceid.identity.iso18013.mdoc.mso.ValidityInfo;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class BuildValidityInfoUseCaseImpl implements BuildValidityInfoUseCase {
    @Override
    public ValidityInfo execute(long validityPeriod) {
        Instant signed = Instant.now();
        Instant validFrom = Instant.now();
        Instant validUntil = Instant.ofEpochMilli(validFrom.toEpochMilli() + validityPeriod);

        return new ValidityInfo(signed, validFrom, validUntil, null); // TODO check what we need to do with expectedUpdate
    }
}
