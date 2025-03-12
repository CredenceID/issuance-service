package com.credenceid.issuance.service.data.repository;

import com.credenceid.issuance.service.domain.model.DigitalIdResult;

public interface ReviewCenterRepository {
    /**
     * Sends a digital ID result to the review center
     * @param digitalIdResult The result of the digital ID creation
     */
    void sendDigitalId(DigitalIdResult digitalIdResult);
}
