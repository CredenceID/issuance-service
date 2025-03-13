package com.credenceid.issuance.service.vo;

import java.util.Arrays;

public class DigitalIdResponse {
    private String walletToken;
    private byte[] mso;
    private byte[] nameSpaces;

    public byte[] getMso() {
        return mso;
    }

    public void setMso(byte[] mso) {
        this.mso = mso;
    }

    public byte[] getNameSpaces() {
        return nameSpaces;
    }

    public void setNameSpaces(byte[] nameSpaces) {
        this.nameSpaces = nameSpaces;
    }

    public String getWalletToken() {
        return walletToken;
    }

    public void setWalletToken(String walletToken) {
        this.walletToken = walletToken;
    }

    @Override
    public String toString() {
        return "DigitalIdResult{" +
                "mso=" + Arrays.toString(mso) +
                ", walletToken='" + walletToken + '\'' +
                ", nameSpaces=" + Arrays.toString(nameSpaces) +
                '}';
    }
}
