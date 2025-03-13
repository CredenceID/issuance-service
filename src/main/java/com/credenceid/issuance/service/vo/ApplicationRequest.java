package com.credenceid.issuance.service.vo;


import java.util.Arrays;

public class ApplicationRequest {
    private PersonalDataRequest personalData;
    private byte[] deviceKey;
    private String walletToken;

    public byte[] getDeviceKey() {
        return deviceKey;
    }

    public void setDeviceKey(byte[] deviceKey) {
        this.deviceKey = deviceKey;
    }

    public PersonalDataRequest getPersonalData() {
        return personalData;
    }

    public void setPersonalData(PersonalDataRequest personalData) {
        this.personalData = personalData;
    }

    public String getWalletToken() {
        return walletToken;
    }

    public void setWalletToken(String walletToken) {
        this.walletToken = walletToken;
    }

    @Override
    public String toString() {
        return "ApplicationRequest{" +
                "deviceKey=" + Arrays.toString(deviceKey) +
                ", personalData=" + personalData +
                ", walletToken='" + walletToken + '\'' +
                '}';
    }
}

