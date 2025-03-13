package com.credenceid.issuance.service.data.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApplicationPropertiesRepositoryImpl implements ApplicationPropertiesRepository {
    @Value("${digital.id.digest.algorithm}")
    private String digestAlgorithm;

    @Value("${digital.id.namespace}")
    private String namespace;

    @Value("${digital.id.doctype}")
    private String docType;

    @Value("${digital.id.mso.version}")
    private String msoVersion;

    @Value("${digital.id.validity.period}")
    private long validityPeriod;

    @Override
    public String getDigestAlgorithm() {
        return digestAlgorithm;
    }

    @Override
    public String getNameSpace() {
        return namespace;
    }

    @Override
    public String getDocType() {
        return docType;
    }

    @Override
    public String getMsoVersion() {
        return msoVersion;
    }

    @Override
    public long getValidityPeriod() {
        return validityPeriod;
    }
}
