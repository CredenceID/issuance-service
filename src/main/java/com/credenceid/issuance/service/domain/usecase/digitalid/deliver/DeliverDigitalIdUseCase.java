package com.credenceid.issuance.service.domain.usecase.digitalid.deliver;

import com.credenceid.issuance.service.domain.model.DigitalIdResult;

public interface DeliverDigitalIdUseCase {
    /**
     * Sends the digital ID result to the review center
     * @param digitalIdResult the created digital ID
     */
    void execute(DigitalIdResult digitalIdResult);
}
