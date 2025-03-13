package com.credenceid.issuance.service.controller;

import com.credenceid.identity.iso5218.Sex;
import com.credenceid.issuance.service.services.IssuanceService;
import com.credenceid.issuance.service.vo.ApplicationRequest;
import com.credenceid.issuance.service.vo.DigitalIdResponse;
import com.credenceid.issuance.service.vo.PersonalDataRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

import static org.mockito.Mockito.*;

@SpringBootTest
public class IssuanceControllerTests {

    @Mock
    private DigitalIdResponse digitalIdResponse;

    @Mock
    private ApplicationRequest applicationRequest;

    @Mock
    private IssuanceService issuanceService;

    @InjectMocks
    private IssuanceController issuanceController;


    static PersonalDataRequest personalDataRequest;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        LocalDate birthDate = LocalDate.of(1988, 12, 12);
        LocalDate expiryDate = LocalDate.of(2029, 12, 12);
        personalDataRequest = new PersonalDataRequest();
        personalDataRequest.setPortrait("testPortraits".getBytes(StandardCharsets.UTF_8));
        personalDataRequest.setDocumentCode("4234234");
        personalDataRequest.setIssuer("GAB");
        personalDataRequest.setDocumentNumber("2222");
        personalDataRequest.setDateOfBirth(birthDate);
        personalDataRequest.setSex(Sex.MALE);
        personalDataRequest.setDateOfExpiry(expiryDate);
        personalDataRequest.setFamilyName("Family Name");
        personalDataRequest.setGivenNames("Given Name");

    }

    @Test
    public void testGenerateDigitalId() {

        when(applicationRequest.getDeviceKey()).thenReturn("testDeviceKey".getBytes(StandardCharsets.UTF_8));
        when(applicationRequest.getWalletToken()).thenReturn("31232131232");
        when(applicationRequest.getPersonalData()).thenReturn(personalDataRequest);
        when(issuanceService.generateDigitalId(applicationRequest)).thenReturn(digitalIdResponse);
        when(digitalIdResponse.getWalletToken()).thenReturn("31232131232");
        when(digitalIdResponse.getMso()).thenReturn("DigitalId".getBytes(StandardCharsets.UTF_8));
        when(digitalIdResponse.getNameSpaces()).thenReturn("TestNameSpaces".getBytes(StandardCharsets.UTF_8));
        DigitalIdResponse response = issuanceController.generateDigitalId(applicationRequest);
        Assert.notNull(response, "Digital Id not generated ");
        Assertions.assertEquals("31232131232", response.getWalletToken());
        Assertions.assertNotNull(response.getMso());
    }
}
