package com.credenceid.issuance.service.domain.usecase.application;

import com.credenceid.identity.iso18013.Util;
import com.credenceid.identity.iso18013.cosekey.CoseKeyIdentifier;
import com.credenceid.identity.iso18013.cosekey.CoseKeyType;
import com.credenceid.identity.iso5218.Sex;
import com.credenceid.issuance.service.domain.model.Application;
import com.credenceid.issuance.service.vo.ApplicationRequest;
import com.credenceid.issuance.service.vo.PersonalDataRequest;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class GetApplicationUseCaseTest {
    @Test
    public void tests_converting_an_application_request_to_an_application() {
        // Given
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

        ApplicationRequest applicationRequest = new ApplicationRequest(
                personalDataRequest,
                Util.fromHex("a4010220012158205a88d182bce5f42efa59943f33359d2e8a968ff289d93e5fa444b624343167fe225820b16e8cf858ddc7690407ba61d4c338237a8cfcf3de6aa672fc60a557aa32fc67"),
                "TOKEN"
        );

        GetApplicationUseCase getApplicationUseCase = new GetApplicationUseCaseImpl();

        // When
        Application application = getApplicationUseCase.execute(applicationRequest);

        // Then
        assertThat(application.walletToken()).isEqualTo("TOKEN");
        assertThat(application.deviceKey().getType()).isEqualTo(CoseKeyType.EC2);
        assertThat(application.deviceKey().getIdentifier()).isEqualTo(CoseKeyIdentifier.P_256);
        assertThat(application.deviceKey().getX().toString())
                .isEqualTo(
                        "40949893562976689537200681350092179745695899445198194294454926870559623047166");
        assertThat(application.deviceKey().getY().toString())
                .isEqualTo(
                        "80254700316277813384111787755154293988831843346745783788191535572893075438695");
        assertThat(application.personalData().portrait()).isEqualTo(new byte[] { 0x00, 0x01, 0x2, 0x03 });
        assertThat(application.personalData().documentCode()).isEqualTo("ID");
        assertThat(application.personalData().issuer()).isEqualTo("GAB");
        assertThat(application.personalData().documentNumber()).isEqualTo("12345678");
        assertThat(application.personalData().dateOfBirth()).isEqualTo(LocalDate.parse("2000-01-01"));
        assertThat(application.personalData().sex()).isEqualTo(Sex.FEMALE);
        assertThat(application.personalData().dateOfExpiry()).isEqualTo(LocalDate.parse("2025-12-31"));
        assertThat(application.personalData().familyName()).isEqualTo("Mustermann");
        assertThat(application.personalData().givenNames()).isEqualTo("Erika");
    }
}
