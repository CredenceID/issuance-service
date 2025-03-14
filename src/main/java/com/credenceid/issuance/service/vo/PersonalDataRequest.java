package com.credenceid.issuance.service.vo;

import java.time.LocalDate;

public record PersonalDataRequest(
        byte[] portrait,
        String documentCode,
        String issuer,
        String documentNumber,
        LocalDate dateOfBirth,
        String sex,
        LocalDate dateOfExpiry,
        String familyName,
        String givenNames
) {}
