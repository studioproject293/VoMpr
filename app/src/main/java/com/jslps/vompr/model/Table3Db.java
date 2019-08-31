package com.jslps.vompr.model;

import com.orm.SugarRecord;

public class Table3Db extends SugarRecord {
    private String statecode;
    private String districtcode;
    private String blockcode;
    private String clustercode;
    private String villagecode;
    private String villagename;
    private String villagenameh;
    public Table3Db() { }

    public Table3Db(String villagename) {
        this.villagename = villagename;
    }

    public Table3Db(String statecode, String districtcode, String blockcode, String clustercode, String villagecode, String villagename, String villagenameh) {
        this.statecode = statecode;
        this.districtcode = districtcode;
        this.blockcode = blockcode;
        this.clustercode = clustercode;
        this.villagecode = villagecode;
        this.villagename = villagename;
        this.villagenameh = villagenameh;
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

    public String getClustercode() {
        return clustercode;
    }

    public void setClustercode(String clustercode) {
        this.clustercode = clustercode;
    }

    public String getVillagecode() {
        return villagecode;
    }

    public void setVillagecode(String villagecode) {
        this.villagecode = villagecode;
    }

    public String getVillagename() {
        return villagename;
    }

    public void setVillagename(String villagename) {
        this.villagename = villagename;
    }

    public String getVillagenameh() {
        return villagenameh;
    }

    public void setVillagenameh(String villagenameh) {
        this.villagenameh = villagenameh;
    }

    @Override
    public String toString() {
        return villagename;
    }
}
