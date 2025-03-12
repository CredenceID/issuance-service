package com.credenceid.issuance.service.domain.model;

public record DigitalIdResult(
        String walletToken,
        byte[] mso,
        byte[] nameSpaces
) {
}
