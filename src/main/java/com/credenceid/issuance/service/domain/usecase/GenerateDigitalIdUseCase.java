package com.credenceid.issuance.service.domain.usecase;

import com.credenceid.issuance.service.domain.model.Application;
import com.credenceid.issuance.service.domain.model.DigitalIdResult;

public interface GenerateDigitalIdUseCase {
    /**
     * Generates a digital ID from an {@link Application}
     * @param application the application received from the review center
     * @return {@link DigitalIdResult} containing the generated digital ID
     */
    DigitalIdResult execute(Application application);
}
