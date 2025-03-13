package com.credenceid.issuance.service.domain.usecase.digitalid.generate.mso;

import com.credenceid.identity.iso18013.mdoc.mso.ValidityInfo;

public interface BuildValidityInfoUseCase {
    /**
     * Builds a {@link ValidityInfo} object based on validity period
     * @param validityPeriod Validity period in ms
     * @return {@link ValidityInfo}
     */
    ValidityInfo execute(long validityPeriod);
}
