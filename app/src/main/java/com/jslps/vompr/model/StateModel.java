package com.jslps.vompr.model;

public class StateModel {
    private String StateCode;
    private String DistrictCode;
    private String DistrictName;
    private String DistrictName_H;
    public StateModel() {
    }

    public StateModel(String stateCode, String districtCode, String districtName, String districtName_H) {
        StateCode = stateCode;
        DistrictCode = districtCode;
        DistrictName = districtName;
        DistrictName_H = districtName_H;
    }

    public String getStateCode() {
        return StateCode;
    }

    public void setStateCode(String stateCode) {
        StateCode = stateCode;
    }

    public String getDistrictCode() {
        return DistrictCode;
    }

    public void setDistrictCode(String districtCode) {
        DistrictCode = districtCode;
    }

    public String getDistrictName() {
        return DistrictName;
    }

    public void setDistrictName(String districtName) {
        DistrictName = districtName;
    }

    public String getDistrictName_H() {
        return DistrictName_H;
    }

    public void setDistrictName_H(String districtName_H) {
        DistrictName_H = districtName_H;
    }

}
