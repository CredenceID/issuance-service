package com.credenceid.issuance.service.domain.model

import com.credenceid.identity.iso5218.Sex
import java.time.LocalDate

/**
 * Personal data that are gathered during on-wallet application
 */
data class PersonalData(
    val portrait: ByteArray,
    val documentCode: String,
    val issuer: String,
    val documentNumber: String,
    val dateOfBirth: LocalDate,
    val sex: Sex,
    val dateOfExpiry: LocalDate,
    val familyName: String,
    val givenNames: String
)
