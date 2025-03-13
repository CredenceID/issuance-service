package com.credenceid.issuance.service.domain.usecase.digitalid.sign;

import com.credenceid.identity.iso18013.mdoc.mso.MobileSecureObject;
import org.springframework.stereotype.Component;

@Component
public class SignMsoUseCaseImpl implements SignMsoUseCase {
    @Override
    public byte[] execute(MobileSecureObject mso) {
        // TODO Not implemented yet
        return new byte[0];
    }
}
