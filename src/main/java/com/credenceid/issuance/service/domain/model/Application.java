package com.credenceid.issuance.service.domain.model;

import com.credenceid.identity.iso18013.cosekey.CoseKey;

public record Application(
        PersonalData personalData,
        CoseKey deviceKey,
        String walletToken
) {
}
