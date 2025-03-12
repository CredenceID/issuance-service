package com.credenceid.issuance.service.domain.model

/**
 * This is the result of the digital Id creation
 */
data class DigitalIdResult(
    val walletToken: String,
    val mso: ByteArray,
    val nameSpaces: ByteArray,
)
