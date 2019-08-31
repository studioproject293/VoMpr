package com.jslps.vompr.model;

public  class Table2  {
    private String StateCode;
    private String DistrictCode;
    private String BlockCode;
    private String ClusterCode;
    private String ClusterName;
    private String ClusterName_H;
    public Table2() { }

    public Table2(String stateCode, String districtCode, String blockCode, String clusterCode, String clusterName, String clusterName_H) {
        StateCode = stateCode;
        DistrictCode = districtCode;
        BlockCode = blockCode;
        ClusterCode = clusterCode;
        ClusterName = clusterName;
        ClusterName_H = clusterName_H;
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

    public String getBlockCode() {
        return BlockCode;
    }

    public void setBlockCode(String blockCode) {
        BlockCode = blockCode;
    }

    public String getClusterCode() {
        return ClusterCode;
    }

    public void setClusterCode(String clusterCode) {
        ClusterCode = clusterCode;
    }

    public String getClusterName() {
        return ClusterName;
    }

    public void setClusterName(String clusterName) {
        ClusterName = clusterName;
    }

    public String getClusterName_H() {
        return ClusterName_H;
    }

    public void setClusterName_H(String clusterName_H) {
        ClusterName_H = clusterName_H;
    }
}
