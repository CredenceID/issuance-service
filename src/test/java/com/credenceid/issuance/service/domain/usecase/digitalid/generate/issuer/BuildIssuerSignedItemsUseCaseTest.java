package com.credenceid.issuance.service.domain.usecase.digitalid.generate.issuer;

import com.credenceid.identity.iso18013.mdoc.IssuerSignedItem;
import com.credenceid.issuance.service.domain.model.PersonalData;
import com.credenceid.issuance.service.domain.usecase.digitalid.generate.GenerateDigitalIdTestUtils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BuildIssuerSignedItemsUseCaseTest {
    private final BuildIssuerSignedItemsUseCase buildIssuerSignedItemsUseCase = new BuildIssuerSignedItemsUseCaseImpl();

    @Test
    public void tests_building_issuer_signed_items_list_with_valid_personal_data() {
        // Given
        PersonalData personalData = GenerateDigitalIdTestUtils.getPersonalData();

        List<byte[]> randoms = GenerateDigitalIdTestUtils.getRandoms();

        // When
        List<IssuerSignedItem> issuerSignedItems = buildIssuerSignedItemsUseCase.execute(personalData, randoms);

        // Then
        GenerateDigitalIdTestUtils.checkIssuerSignedItems(issuerSignedItems);
    }
}
