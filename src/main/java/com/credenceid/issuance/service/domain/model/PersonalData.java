package com.credenceid.issuance.service.domain.model;

import com.credenceid.identity.iso5218.Sex;

import java.time.LocalDate;

public record PersonalData(
        byte[] portrait,
        String documentCode,
        String issuer,
        String documentNumber,
        LocalDate dateOfBirth,
        Sex sex,
        LocalDate dateOfExpiry,
        String familyName,
        String givenNames
) {
}
