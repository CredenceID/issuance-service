package com.credenceid.issuance.service.domain.coordinator;

import com.credenceid.issuance.service.domain.usecase.digitalid.deliver.DeliverDigitalIdUseCase;
import com.credenceid.issuance.service.domain.usecase.digitalid.generate.GenerateDigitalIdUseCase;
import com.credenceid.issuance.service.domain.usecase.application.GetApplicationUseCase;

public class IssuanceCoordinatorBuilderImpl implements IssuanceCoordinatorBuilder {
    private GetApplicationUseCase getApplicationUseCase = null;
    private GenerateDigitalIdUseCase generateDigitalIdUseCase = null;
    private DeliverDigitalIdUseCase deliverDigitalIdUseCase = null;

    @Override
    public IssuanceCoordinator build() {
        return new IssuanceCoordinatorImpl(
                getApplicationUseCase,
                generateDigitalIdUseCase,
                deliverDigitalIdUseCase
        );
    }
}
