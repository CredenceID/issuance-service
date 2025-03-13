package com.credenceid.issuance.service.domain.usecase.digitalid.generate.issuer;

import com.credenceid.identity.iso18013.mdoc.NameSpace;
import com.credenceid.issuance.service.domain.usecase.digitalid.generate.GenerateDigitalIdTestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class BuildNameSpaceUseCaseTest {
    @Mock
    private BuildIssuerSignedItemsUseCase buildIssuerSignedItemsUseCase;

    @InjectMocks
    private BuildNameSpaceUseCaseImpl buildNameSpaceUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void tests_building_a_namespace() {
        // Given
        when(buildIssuerSignedItemsUseCase.execute(any(), any())).thenReturn(GenerateDigitalIdTestUtils.getIssuerSignedItems());

        // When
        NameSpace nameSpace = buildNameSpaceUseCase.execute(GenerateDigitalIdTestUtils.getPersonalData());

        // Then
        assertThat(nameSpace.getName()).isEqualTo("org.iso.18013.5.1");
        GenerateDigitalIdTestUtils.checkIssuerSignedItems(nameSpace.getIssuerSignedItems());
    }
}
