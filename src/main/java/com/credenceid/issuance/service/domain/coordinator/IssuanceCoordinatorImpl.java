package com.credenceid.issuance.service.domain.coordinator;

import com.credenceid.issuance.service.domain.model.Application;
import com.credenceid.issuance.service.domain.model.DigitalIdResult;
import com.credenceid.issuance.service.domain.usecase.DeliverDigitalIdUseCase;
import com.credenceid.issuance.service.domain.usecase.GenerateDigitalIdUseCase;
import com.credenceid.issuance.service.domain.usecase.GetApplicationUseCase;

public class IssuanceCoordinatorImpl implements IssuanceCoordinator {

    private final GetApplicationUseCase getApplicationUseCase;
    private final GenerateDigitalIdUseCase generateDigitalIdUseCase;
    private final DeliverDigitalIdUseCase deliverDigitalIdUseCase;

    public IssuanceCoordinatorImpl(
        GetApplicationUseCase getApplicationUseCase,
        GenerateDigitalIdUseCase generateDigitalIdUseCase,
        DeliverDigitalIdUseCase deliverDigitalIdUseCase
    ) {
        this.getApplicationUseCase = getApplicationUseCase;
        this.generateDigitalIdUseCase = generateDigitalIdUseCase;
        this.deliverDigitalIdUseCase = deliverDigitalIdUseCase;
    }

    @Override
    public void issueDigitalId() {
        // Gets the Application
        // TODO missing parameters to issueDigitalId and to getApplicationUseCase.execute()
        Application application = getApplicationUseCase.execute();

        // Creates the digital ID
        DigitalIdResult digitalIdResult = generateDigitalIdUseCase.execute(application);

        // Delivers the digital id to the review center
        deliverDigitalIdUseCase.execute(digitalIdResult);
    }
}
