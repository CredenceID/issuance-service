package com.credenceid.issuance.service.data.repository;

public interface ApplicationPropertiesRepository {
    /**
     * Returns the digest algorithm
     * @return Digest algorithm
     */
    String getDigestAlgorithm();

    /**
     * Returns the NameSpace to be used for Digital ID generation
     * @return NameSpace
     */
    String getNameSpace();

    /**
     * Returns the doc type for Digital ID generation
     * @return doc type
     */
    String getDocType();

    /**
     * Returns the MSO version for Digital ID creation
     * @return MSO version
     */
    String getMsoVersion();

    /**
     * Returns the Digital ID validity period in ms
     * @return Validity period
     */
    long getValidityPeriod();
}
