package com.credenceid.issuance.service.domain.usecase.digitalid.generate.mso;

import com.credenceid.identity.iso18013.mdoc.mso.MobileSecureObject;

public interface SignMsoUseCase {
    /**
     * Signs the provided {@link MobileSecureObject}
     * @param mso {@link MobileSecureObject} to be signed
     * @return Signed {@link MobileSecureObject} as byte[]
     */
    byte[] execute(MobileSecureObject mso);
}
