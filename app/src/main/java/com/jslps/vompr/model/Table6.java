package com.jslps.vompr.model;

import com.orm.SugarRecord;

public class Table6 extends SugarRecord {
    private String BranchCode;
    private String BankCode;
    private String BranchName;
    private String IFSCCode;
    private String BranchName_H;
    public Table6() { }

    public Table6(String branchCode, String bankCode, String branchName, String IFSCCode, String branchName_H) {
        BranchCode = branchCode;
        BankCode = bankCode;
        BranchName = branchName;
        this.IFSCCode = IFSCCode;
        BranchName_H = branchName_H;
    }

    public String getBranchCode() {
        return BranchCode;
    }

    public void setBranchCode(String branchCode) {
        BranchCode = branchCode;
    }

    public String getBankCode() {
        return BankCode;
    }

    public void setBankCode(String bankCode) {
        BankCode = bankCode;
    }

    public String getBranchName() {
        return BranchName;
    }

    public void setBranchName(String branchName) {
        BranchName = branchName;
    }

    public String getIFSCCode() {
        return IFSCCode;
    }

    public void setIFSCCode(String IFSCCode) {
        this.IFSCCode = IFSCCode;
    }

    public String getBranchName_H() {
        return BranchName_H;
    }

    public void setBranchName_H(String branchName_H) {
        BranchName_H = branchName_H;
    }
}
