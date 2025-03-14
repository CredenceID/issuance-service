package com.credenceid.issuance.service.controller;

import com.credenceid.identity.iso18013.Util;
import com.credenceid.identity.iso18013.cosekey.CoseKey;
import com.credenceid.identity.iso18013.mdoc.NameSpace;
import com.credenceid.identity.iso18013.mdoc.mso.MobileSecureObject;
import com.credenceid.identity.iso5218.Sex;
import com.credenceid.issuance.service.domain.controller.IssuanceController;
import com.credenceid.issuance.service.domain.controller.IssuanceControllerImpl;
import com.credenceid.issuance.service.domain.model.Application;
import com.credenceid.issuance.service.domain.model.DigitalId;
import com.credenceid.issuance.service.domain.model.DigitalIdResult;
import com.credenceid.issuance.service.domain.model.PersonalData;
import com.credenceid.issuance.service.domain.usecase.application.GetApplicationUseCase;
import com.credenceid.issuance.service.domain.usecase.digitalid.generate.GenerateDigitalIdUseCase;
import com.credenceid.issuance.service.domain.usecase.digitalid.sign.SignMsoUseCase;
import com.credenceid.issuance.service.vo.ApplicationRequest;
import com.credenceid.issuance.service.vo.PersonalDataRequest;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
public class IssuanceControllerTests {
    @Mock
    private GetApplicationUseCase getApplicationUseCase;
    @Mock
    private GenerateDigitalIdUseCase generateDigitalIdUseCase;
    @Mock
    private SignMsoUseCase signMsoUseCase;
    @Mock
    private NameSpace nameSpace;
    @Mock
    private MobileSecureObject mso;

    private IssuanceController issuanceController;

    @Test
    public void tests_issuing_digital_id() {
        // Given
        ApplicationRequest applicationRequest = getApplicationRequest();
        DigitalId digitalId = new DigitalId(
                nameSpace,
                mso
        );
        Application application = getApplication();
        when(nameSpace.getEncodedBytes()).thenReturn(new byte[] {0x02, 0x03, 0x04});
        when(getApplicationUseCase.execute(applicationRequest)).thenReturn(application);
        when(generateDigitalIdUseCase.execute(application)).thenReturn(digitalId);
        when(signMsoUseCase.execute(digitalId.mso())).thenReturn(new byte[] {0x00, 0x01, 0x02, 0x03});

        issuanceController = new IssuanceControllerImpl(getApplicationUseCase, generateDigitalIdUseCase, signMsoUseCase);

        // When
        DigitalIdResult digitalIdResult = issuanceController.generateDigitalId(applicationRequest);

        // Then
        assertThat(digitalIdResult.walletToken()).isEqualTo("TOKEN");
        assertThat(digitalIdResult.nameSpaces()).isEqualTo(new byte[] {0x02, 0x03, 0x04});
        assertThat(digitalIdResult.mso()).isEqualTo(new byte[] {0x00, 0x01, 0x02, 0x03});
    }

    private ApplicationRequest getApplicationRequest() {
        PersonalDataRequest personalDataRequest = new PersonalDataRequest(
                new byte[] { 0x00, 0x01, 0x02, 0x03 },
                "ID",
                "GAB",
                "12345678",
                LocalDate.parse("2000-01-01"),
                "F",
                LocalDate.parse("2025-12-31"),
                "Mustermann",
                "Erika"
        );

        return new ApplicationRequest(
                personalDataRequest,
                Util.fromHex("a4010220012158205a88d182bce5f42efa59943f33359d2e8a968ff289d93e5fa444b624343167fe225820b16e8cf858ddc7690407ba61d4c338237a8cfcf3de6aa672fc60a557aa32fc67"),
                "TOKEN"
        );
    }

    public Application getApplication() {
        PersonalData personalData = new PersonalData(
                new byte[] { 0x00, 0x01, 0x02, 0x03 },
                "ID",
                "GAB",
                "12345678",
                LocalDate.parse("2000-01-01"),
                Sex.FEMALE,
                LocalDate.parse("2025-12-31"),
                "Mustermann",
                "Erika"
        );

        return new Application(
                personalData,
                new CoseKey(Util.fromHex("a4010220012158205a88d182bce5f42efa59943f33359d2e8a968ff289d93e5fa444b624343167fe225820b16e8cf858ddc7690407ba61d4c338237a8cfcf3de6aa672fc60a557aa32fc67")),
                "TOKEN"
        );
    }
}
