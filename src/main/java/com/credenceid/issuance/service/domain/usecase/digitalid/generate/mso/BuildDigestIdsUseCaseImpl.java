package com.credenceid.issuance.service.domain.usecase.digitalid.generate.mso;

import com.credenceid.identity.iso18013.mdoc.NameSpace;
import com.credenceid.identity.iso18013.mdoc.mso.DigestIds;

import java.security.NoSuchAlgorithmException;

public class BuildDigestIdsUseCaseImpl implements BuildDigestIdsUseCase {
    @Override
    public DigestIds execute(NameSpace nameSpace, String algorithm) {
        try {
            return new DigestIds(nameSpace, algorithm);
        } catch (NoSuchAlgorithmException nsae) {
            throw new IllegalArgumentException("Unsupported algorithm: " + nsae.getMessage());
        }
    }
}
