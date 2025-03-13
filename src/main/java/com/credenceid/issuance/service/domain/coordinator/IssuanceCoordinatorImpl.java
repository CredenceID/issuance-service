package com.credenceid.issuance.service.domain.coordinator;

import com.credenceid.issuance.service.domain.model.Application;
import com.credenceid.issuance.service.domain.model.DigitalId;
import com.credenceid.issuance.service.domain.model.DigitalIdResult;
import com.credenceid.issuance.service.domain.usecase.application.GetApplicationUseCase;
import com.credenceid.issuance.service.domain.usecase.digitalid.deliver.DeliverDigitalIdUseCase;
import com.credenceid.issuance.service.domain.usecase.digitalid.generate.GenerateDigitalIdUseCase;
import com.credenceid.issuance.service.domain.usecase.digitalid.sign.SignMsoUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IssuanceCoordinatorImpl implements IssuanceCoordinator {

    private final GetApplicationUseCase getApplicationUseCase;
    private final GenerateDigitalIdUseCase generateDigitalIdUseCase;
    private final SignMsoUseCase signMsoUseCase;
    private final DeliverDigitalIdUseCase deliverDigitalIdUseCase;

    @Autowired
    public IssuanceCoordinatorImpl(
        GetApplicationUseCase getApplicationUseCase,
        GenerateDigitalIdUseCase generateDigitalIdUseCase,
        SignMsoUseCase signMsoUseCase,
        DeliverDigitalIdUseCase deliverDigitalIdUseCase
    ) {
        this.getApplicationUseCase = getApplicationUseCase;
        this.generateDigitalIdUseCase = generateDigitalIdUseCase;
        this.signMsoUseCase = signMsoUseCase;
        this.deliverDigitalIdUseCase = deliverDigitalIdUseCase;
    }

    @Override
    public void issueDigitalId() {
        // Gets the Application
        // TODO missing parameters to issueDigitalId and to getApplicationUseCase.execute()
        Application application = getApplicationUseCase.execute();

        // Creates the digital ID
        DigitalId digitalId = generateDigitalIdUseCase.execute(application);

        // Signs Mso
        byte[] signedMso = signMsoUseCase.execute(digitalId.mso());

        //Builds DigitalIdResult
        DigitalIdResult digitalIdResult = new DigitalIdResult(application.walletToken(), digitalId.nameSpace().getEncodedBytes(), signedMso);

        // Delivers the digital id to the review center
        deliverDigitalIdUseCase.execute(digitalIdResult);
    }
}
