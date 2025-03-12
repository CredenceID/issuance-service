package com.credenceid.issuance.service.domain.coordinator;

import com.credenceid.issuance.service.domain.usecase.DeliverDigitalIdUseCase;
import com.credenceid.issuance.service.domain.usecase.GenerateDigitalIdUseCase;
import com.credenceid.issuance.service.domain.usecase.GetApplicationUseCase;

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
