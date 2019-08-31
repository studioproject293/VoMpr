package com.jslps.vompr.model;

import com.orm.SugarRecord;

public  class Table2Db extends SugarRecord {
    private String statecode;
    private String districtcode;
    private String blockcode;
    private String clustercode;
    private String clustername;
    private String clustername_H;
    public Table2Db() { }

    public Table2Db(String statecode, String districtcode, String blockcode, String clustercode, String clustername, String clustername_H) {
        this.statecode = statecode;
        this.districtcode = districtcode;
        this.blockcode = blockcode;
        this.clustercode = clustercode;
        this.clustername = clustername;
        this.clustername_H = clustername_H;
    }

    public Table2Db(String clustername) {
        this.clustername = clustername;
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

    public String getClustername() {
        return clustername;
    }

    public void setClustername(String clustername) {
        this.clustername = clustername;
    }

    public String getClustername_H() {
        return clustername_H;
    }

    public void setClustername_H(String clustername_H) {
        this.clustername_H = clustername_H;
    }

    @Override
    public String toString() {
        return clustername;
    }
}
