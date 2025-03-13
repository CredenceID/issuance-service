package com.credenceid.issuance.service.domain.usecase.digitalid.generate;

import com.credenceid.issuance.service.domain.model.Application;
import com.credenceid.issuance.service.domain.model.DigitalId;
import com.credenceid.issuance.service.domain.model.DigitalIdResult;

public interface GenerateDigitalIdUseCase {
    /**
     * Generates a digital ID from an {@link Application}
     * @param application the application received from the review center
     * @return {@link DigitalId} containing the generated digital ID
     */
    DigitalId execute(Application application);
}
