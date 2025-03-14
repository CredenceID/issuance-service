package com.credenceid.issuance.service.domain.controller;

import com.credenceid.issuance.service.domain.model.Application;
import com.credenceid.issuance.service.domain.model.DigitalId;
import com.credenceid.issuance.service.domain.model.DigitalIdResult;
import com.credenceid.issuance.service.domain.usecase.application.GetApplicationUseCase;
import com.credenceid.issuance.service.domain.usecase.digitalid.generate.GenerateDigitalIdUseCase;
import com.credenceid.issuance.service.domain.usecase.digitalid.sign.SignMsoUseCase;
import com.credenceid.issuance.service.vo.ApplicationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RequestMapping(value = "/issuanceservice")
@RestController
public class IssuanceControllerImpl implements IssuanceController {
    private final GetApplicationUseCase getApplicationUseCase;
    private final GenerateDigitalIdUseCase generateDigitalIdUseCase;
    private final SignMsoUseCase signMsoUseCase;

    @Autowired
    public IssuanceControllerImpl(
            GetApplicationUseCase getApplicationUseCase,
            GenerateDigitalIdUseCase generateDigitalIdUseCase,
            SignMsoUseCase signMsoUseCase
    ) {
        this.getApplicationUseCase = getApplicationUseCase;
        this.generateDigitalIdUseCase = generateDigitalIdUseCase;
        this.signMsoUseCase = signMsoUseCase;
    }


    /**
     * Endpoint to generate digital id from application data
     *
     * @param applicationRequest The application request received from REST request
     * @return the generated digital ID
     */
    @Override
    @PostMapping(value = "/generateDigitalId")
    public DigitalIdResult generateDigitalId(@RequestBody ApplicationRequest applicationRequest) {
        Application application = getApplicationUseCase.execute(applicationRequest);

        // Creates the digital ID
        DigitalId digitalId = generateDigitalIdUseCase.execute(application);

        // Signs Mso
        byte[] signedMso = signMsoUseCase.execute(digitalId.mso());

        // Builds DigitalIdResult
        return new DigitalIdResult(application.walletToken(), digitalId.nameSpace().getEncodedBytes(), signedMso);
    }
}
