package com.credenceid.issuance.service.domain.usecase.application;

import com.credenceid.issuance.service.domain.model.Application;

public interface GetApplicationUseCase {
    /**
     * Takes the data received from the review center and converts them to an {@link Application}
     *
     * @return {@link Application}
     */
    // TODO add the parameters as received from Review Center
    Application execute();
}
