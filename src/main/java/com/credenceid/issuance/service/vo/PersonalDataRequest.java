package com.credenceid.issuance.service.vo;

import com.credenceid.identity.iso5218.Sex;

import java.time.LocalDate;
import java.util.Arrays;

public class PersonalDataRequest {
    private byte[] portrait;
    private String documentCode;
    private String issuer;
    private String documentNumber;
    private LocalDate dateOfBirth;
    private Sex sex;
    private LocalDate dateOfExpiry;
    private String familyName;
    private String givenNames;

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDate getDateOfExpiry() {
        return dateOfExpiry;
    }

    public void setDateOfExpiry(LocalDate dateOfExpiry) {
        this.dateOfExpiry = dateOfExpiry;
    }

    public String getDocumentCode() {
        return documentCode;
    }

    public void setDocumentCode(String documentCode) {
        this.documentCode = documentCode;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getGivenNames() {
        return givenNames;
    }

    public void setGivenNames(String givenNames) {
        this.givenNames = givenNames;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public byte[] getPortrait() {
        return portrait;
    }

    public void setPortrait(byte[] portrait) {
        this.portrait = portrait;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "PersonalData{" +
                "dateOfBirth=" + dateOfBirth +
                ", portrait=" + Arrays.toString(portrait) +
                ", documentCode='" + documentCode + '\'' +
                ", issuer='" + issuer + '\'' +
                ", documentNumber='" + documentNumber + '\'' +
                ", sex=" + sex +
                ", dateOfExpiry=" + dateOfExpiry +
                ", familyName='" + familyName + '\'' +
                ", givenNames='" + givenNames + '\'' +
                '}';
    }
}
