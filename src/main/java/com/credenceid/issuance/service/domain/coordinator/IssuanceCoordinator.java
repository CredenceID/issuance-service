package com.credenceid.issuance.service.domain.coordinator;

public interface IssuanceCoordinator {
    /**
     * Gets the application from review center, generates the digital ID and sends it back to review center
     */
    // TODO define the parameters according on the API between review center and issuance service
    void issueDigitalId();
}
