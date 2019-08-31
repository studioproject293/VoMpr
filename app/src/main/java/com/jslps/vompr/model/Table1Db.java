package com.jslps.vompr.model;

import com.orm.SugarRecord;

public class Table1Db extends SugarRecord {
    private String statecode;
    private String districtcode;
    private String blockcode;
    private String blockname;
    private String blocknameh;

    public Table1Db() { }

    public Table1Db(String statecode, String districtcode, String blockcode, String blockname, String blocknameh) {
        this.statecode = statecode;
        this.districtcode = districtcode;
        this.blockcode = blockcode;
        this.blockname = blockname;
        this.blocknameh = blocknameh;
    }

    public String getStatecode() {
        return statecode;
    }

    public void setStatecode(String statecode) {
        this.statecode = statecode;
    }

    public String getDistrictcode() {
        return districtcode;
    }

    public void setDistrictcode(String districtcode) {
        this.districtcode = districtcode;
    }

    public String getBlockcode() {
        return blockcode;
    }

    public void setBlockcode(String blockcode) {
        this.blockcode = blockcode;
    }

    public String getBlockname() {
        return blockname;
    }

    public void setBlockname(String blockname) {
        this.blockname = blockname;
    }

    public String getBlocknameh() {
        return blocknameh;
    }

    public void setBlocknameh(String blocknameh) {
        this.blocknameh = blocknameh;
    }

    @Override
    public String toString() {
        return blockname;
    }
}
