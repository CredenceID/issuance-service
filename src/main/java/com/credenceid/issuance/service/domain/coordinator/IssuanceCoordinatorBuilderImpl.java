package com.credenceid.issuance.service.domain.coordinator;

import com.credenceid.issuance.service.domain.usecase.digitalid.deliver.DeliverDigitalIdUseCase;
import com.credenceid.issuance.service.domain.usecase.digitalid.generate.GenerateDigitalIdUseCase;
import com.credenceid.issuance.service.domain.usecase.application.GetApplicationUseCase;
import com.credenceid.issuance.service.domain.usecase.digitalid.sign.SignMsoUseCase;

public class IssuanceCoordinatorBuilderImpl implements IssuanceCoordinatorBuilder {
    private GetApplicationUseCase getApplicationUseCase = null;
    private GenerateDigitalIdUseCase generateDigitalIdUseCase = null;
    private SignMsoUseCase signMsoUseCase;
    private DeliverDigitalIdUseCase deliverDigitalIdUseCase = null;

    @Override
    public IssuanceCoordinator build() {
        return new IssuanceCoordinatorImpl(
                getApplicationUseCase,
                generateDigitalIdUseCase,
                signMsoUseCase,
                deliverDigitalIdUseCase
        );
    }
}
