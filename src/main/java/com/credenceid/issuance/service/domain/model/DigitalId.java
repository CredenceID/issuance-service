package com.credenceid.issuance.service.domain.model;

import com.credenceid.identity.iso18013.mdoc.NameSpace;
import com.credenceid.identity.iso18013.mdoc.mso.MobileSecureObject;

public record DigitalId(NameSpace nameSpace, MobileSecureObject mso) {
}
