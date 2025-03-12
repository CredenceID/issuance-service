package com.credenceid.issuance.service.domain.usecase.digitalid.generate;

import com.credenceid.identity.iso18013.mdoc.IssuerSignedItem;
import com.credenceid.issuance.service.domain.model.PersonalData;

import java.util.List;

public interface BuildIssuerSignedItemsUseCase {
    /**
     * Builds a List<{@link IssuerSignedItem}> from personalData
     * @param personalData Applicant's personal data
     * @param random List of random byte arrays
     * @return List<{@link IssuerSignedItem}>
     */
    List<IssuerSignedItem> execute(PersonalData personalData, List<byte[]> random);
}
