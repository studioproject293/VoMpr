package com.jslps.vompr.model;

import com.orm.SugarRecord;

public class StateModelDb extends SugarRecord {
    private String statecode;
    private String districtcode;
    private String districtname;
    private String districtnameh;
    public StateModelDb() { }

    public StateModelDb(String statecode, String districtcode, String districtname, String districtnameh) {
        this.statecode = statecode;
        this.districtcode = districtcode;
        this.districtname = districtname;
        this.districtnameh = districtnameh;
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

    public String getDistrictname() {
        return districtname;
    }

    public void setDistrictname(String districtname) {
        this.districtname = districtname;
    }

    public String getDistrictnameh() {
        return districtnameh;
    }

    public void setDistrictnameh(String districtnameh) {
        this.districtnameh = districtnameh;
    }

    @Override
    public String toString() {
        return districtname;
    }
}
