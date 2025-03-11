package com.credenceid.issuance.service.domain.model

import com.credenceid.identity.iso18013.cosekey.CoseKey

/**
 * This is an Application made by a citizen on the wallet
 */
data class Application(
    val personalData: PersonalData,
    val devicePublicKey: CoseKey,
    val walletToken: String,
)
