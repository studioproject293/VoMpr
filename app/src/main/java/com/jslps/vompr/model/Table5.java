package com.jslps.vompr.model;

import com.orm.SugarRecord;

public class Table5 extends SugarRecord {
    private String BankCode;
    private String BankId;
    private String BankName;
    private String BankName_Hindi;
    private String BankType;
    private String IFSCCode;
    private String Logo;
    public Table5() { }

    public Table5(String bankCode, String bankId, String bankName, String bankName_Hindi, String bankType, String IFSCCode, String logo) {
        BankCode = bankCode;
        BankId = bankId;
        BankName = bankName;
        BankName_Hindi = bankName_Hindi;
        BankType = bankType;
        this.IFSCCode = IFSCCode;
        Logo = logo;
    }

    public String getBankCode() {
        return BankCode;
    }

    public void setBankCode(String bankCode) {
        BankCode = bankCode;
    }

    public String getBankId() {
        return BankId;
    }

    public void setBankId(String bankId) {
        BankId = bankId;
    }

    public String getBankName() {
        return BankName;
    }

    public void setBankName(String bankName) {
        BankName = bankName;
    }

    public String getBankName_Hindi() {
        return BankName_Hindi;
    }

    public void setBankName_Hindi(String bankName_Hindi) {
        BankName_Hindi = bankName_Hindi;
    }

    public String getBankType() {
        return BankType;
    }

    public void setBankType(String bankType) {
        BankType = bankType;
    }

    public String getIFSCCode() {
        return IFSCCode;
    }

    public void setIFSCCode(String IFSCCode) {
        this.IFSCCode = IFSCCode;
    }

    public String getLogo() {
        return Logo;
    }

    public void setLogo(String logo) {
        Logo = logo;
    }
}
