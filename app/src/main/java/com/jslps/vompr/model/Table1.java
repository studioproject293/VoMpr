package com.jslps.vompr.model;

public class Table1  {
    private String StateCode;
    private String DistrictCode;
    private String BlockCode;
    private String BlockName;
    private String BlockName_H;

    public Table1(String stateCode, String districtCode, String blockCode, String blockName, String blockName_H) {
        StateCode = stateCode;
        DistrictCode = districtCode;
        BlockCode = blockCode;
        BlockName = blockName;
        BlockName_H = blockName_H;
    }

    public Table1() { }

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

    public String getBlockCode() {
        return BlockCode;
    }

    public void setBlockCode(String blockCode) {
        BlockCode = blockCode;
    }

    public String getBlockName() {
        return BlockName;
    }

    public void setBlockName(String blockName) {
        BlockName = blockName;
    }

    public String getBlockName_H() {
        return BlockName_H;
    }

    public void setBlockName_H(String blockName_H) {
        BlockName_H = blockName_H;
    }


}
