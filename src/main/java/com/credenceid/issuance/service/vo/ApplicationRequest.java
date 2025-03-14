package com.credenceid.issuance.service.vo;

public record ApplicationRequest(
        PersonalDataRequest personalData,
        byte[] deviceKey,
        String walletToken
) {}
