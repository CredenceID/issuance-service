package com.credenceid.issuance.service.domain.usecase.digitalid.generate.issuer;

import com.credenceid.identity.iso18013.mdoc.IssuerSignedItem;
import com.credenceid.identity.iso18013.mdoc.NameSpace;
import com.credenceid.issuance.service.data.repository.ApplicationPropertiesRepository;
import com.credenceid.issuance.service.domain.model.PersonalData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

@Component
public class BuildNameSpaceUseCaseImpl implements BuildNameSpaceUseCase {
    private static final int RANDOMS_LIST_SIZE = 256; // TODO Big on purpose, to be optimized

    private final ApplicationPropertiesRepository applicationPropertiesRepository;
    private final BuildIssuerSignedItemsUseCase buildIssuerSignedItemsUseCase;

    @Autowired
    public BuildNameSpaceUseCaseImpl(
            ApplicationPropertiesRepository applicationPropertiesRepository,
            BuildIssuerSignedItemsUseCase buildIssuerSignedItemsUseCase
    ) {
        this.applicationPropertiesRepository = applicationPropertiesRepository;
        this.buildIssuerSignedItemsUseCase = buildIssuerSignedItemsUseCase;
    }

    @Override
    public NameSpace execute(PersonalData personalData) {
        // Creates all the IssuerSignedItems for personalData
        List<IssuerSignedItem> issuerSignedItems = buildIssuerSignedItemsUseCase.execute(personalData, createRandoms());

        // Creates NameSpace
        return new NameSpace(applicationPropertiesRepository.getNameSpace(), issuerSignedItems);
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
