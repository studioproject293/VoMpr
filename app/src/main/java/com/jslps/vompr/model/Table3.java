package com.jslps.vompr.model;

import com.orm.SugarRecord;

public class Table3  {
    private String StateCode;
    private String DistrictCode;
    private String BlockCode;
    private String ClusterCode;
    private String VillageCode;
    private String VillageName;
    private String VillageName_H;
    public Table3() { }

    public Table3(String stateCode, String districtCode, String blockCode, String clusterCode, String villageCode, String villageName, String villageName_H) {
        StateCode = stateCode;
        DistrictCode = districtCode;
        BlockCode = blockCode;
        ClusterCode = clusterCode;
        VillageCode = villageCode;
        VillageName = villageName;
        VillageName_H = villageName_H;
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

    public String getVillageCode() {
        return VillageCode;
    }

    public void setVillageCode(String villageCode) {
        VillageCode = villageCode;
    }

    public String getVillageName() {
        return VillageName;
    }

    public void setVillageName(String villageName) {
        VillageName = villageName;
    }

    public String getVillageName_H() {
        return VillageName_H;
    }

    public void setVillageName_H(String villageName_H) {
        VillageName_H = villageName_H;
    }

}
