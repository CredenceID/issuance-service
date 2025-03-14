package com.credenceid.issuance.service.domain.usecase.application;

import com.credenceid.issuance.service.domain.model.Application;
import com.credenceid.issuance.service.vo.ApplicationRequest;

public interface GetApplicationUseCase {
    /**
     * Takes the data received from the review center and converts them to an {@link Application}
     * @param applicationRequest The ApplicationRequest received from the Review Center
     * @return {@link Application}
     */
    Application execute(ApplicationRequest applicationRequest);
}
