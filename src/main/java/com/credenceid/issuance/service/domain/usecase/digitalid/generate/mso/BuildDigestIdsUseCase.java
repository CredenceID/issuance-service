package com.credenceid.issuance.service.domain.usecase.digitalid.generate.mso;

import com.credenceid.identity.iso18013.mdoc.NameSpace;
import com.credenceid.identity.iso18013.mdoc.mso.DigestIds;

public interface BuildDigestIdsUseCase {
     DigestIds execute(NameSpace nameSpace, String algorithm);
}
