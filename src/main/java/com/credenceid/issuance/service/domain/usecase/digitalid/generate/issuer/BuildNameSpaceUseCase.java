package com.credenceid.issuance.service.domain.usecase.digitalid.generate.issuer;

import com.credenceid.identity.iso18013.mdoc.NameSpace;
import com.credenceid.issuance.service.domain.model.PersonalData;

public interface BuildNameSpaceUseCase {
    /**
     * Creates a {@link NameSpace} from the specified {@link PersonalData}
     * @param personalData Applicant personal data
     * @return {@link NameSpace}
     */
    NameSpace execute(PersonalData personalData);
}
