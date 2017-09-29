package com.zy.cms.vo;

public class MobileOperator extends MobileOperatorKey {
    private String numberPrefix;

    public String getNumberPrefix() {
        return numberPrefix;
    }

    public void setNumberPrefix(String numberPrefix) {
        this.numberPrefix = numberPrefix == null ? null : numberPrefix.trim();
    }
}