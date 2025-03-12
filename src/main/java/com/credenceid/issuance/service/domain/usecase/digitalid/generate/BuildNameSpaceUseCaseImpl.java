package com.credenceid.issuance.service.domain.usecase.digitalid.generate;

import com.credenceid.identity.iso18013.mdoc.IssuerSignedItem;
import com.credenceid.identity.iso18013.mdoc.NameSpace;
import com.credenceid.issuance.service.domain.model.PersonalData;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class BuildNameSpaceUseCaseImpl implements BuildNameSpaceUseCase {
    private static final String ISO_18031_5_BASE_NAMESPACE = "org.iso.18013.5.1";
    private static final int RANDOMS_LIST_SIZE = 9;

    private final BuildIssuerSignedItemsUseCase buildIssuerSignedItemsUseCase;

    public BuildNameSpaceUseCaseImpl(BuildIssuerSignedItemsUseCase buildIssuerSignedItemsUseCase) {
        this.buildIssuerSignedItemsUseCase = buildIssuerSignedItemsUseCase;
    }

    @Override
    public NameSpace execute(PersonalData personalData) {
        // Creates all the IssuerSignedItems for personalData
        List<IssuerSignedItem> issuerSignedItems = buildIssuerSignedItemsUseCase.execute(personalData, createRandoms());

        // Creates NameSpace
        return new NameSpace(ISO_18031_5_BASE_NAMESPACE, issuerSignedItems);
    }

    /**
     * Creates a List of randoms byte[]
     * @return List<byte[]>
     */
    private List<byte[]> createRandoms() {
        List<byte[]> randoms = new ArrayList<>();
        SecureRandom secureRandom = new SecureRandom();
        byte[] randomBytes = new byte[16];

        for (int i = 0; i < RANDOMS_LIST_SIZE; i++) {
            secureRandom.nextBytes(randomBytes);
            randoms.add(randomBytes);
        }

        return randoms;
    }
}
