package com.jslps.vompr.model;

import com.orm.SugarRecord;

public class Table9Db extends SugarRecord {
    private String blockcode;
    private String clustercode;
    private String districtcode;
    private String villagecode;
    private String voCode;
    private String status;
    private String vomemerid;
    private String vlfcode;
    private String statecode;
    private String phoneno;
    private String gropofofficerpostname;
    private String groupcode;
    private String memberid;
    private String clfcode;
    private String dateofjoin;
    private String fatherhasbandname;
    private String gropmembername;
    private String groupname;
    private String uuid;
    private String isupdated;

    public Table9Db() {
    }

    public Table9Db(String phoneno) {
        this.phoneno = phoneno;
    }

    public Table9Db(String blockcode, String clustercode, String districtcode, String villagecode, String voCode, String status, String vomemerid, String vlfcode, String statecode, String phoneno, String gropofofficerpostname, String groupcode, String memberid, String clfcode, String dateofjoin, String fatherhasbandname, String gropmembername, String groupname) {
        this.blockcode = blockcode;
        this.clustercode = clustercode;
        this.districtcode = districtcode;
        this.villagecode = villagecode;
        this.voCode = voCode;
        this.status = status;
        this.vomemerid = vomemerid;
        this.vlfcode = vlfcode;
        this.statecode = statecode;
        this.phoneno = phoneno;
        this.gropofofficerpostname = gropofofficerpostname;
        this.groupcode = groupcode;
        this.memberid = memberid;
        this.clfcode = clfcode;
        this.dateofjoin = dateofjoin;
        this.fatherhasbandname = fatherhasbandname;
        this.gropmembername = gropmembername;
        this.groupname = groupname;

    }

    public Table9Db(String voCode, String status, String phoneno, String gropofofficerpostname, String groupcode, String memberid, String dateofjoin, String fatherhasbandname, String gropmembername, String groupname, String uuid, String isupdated) {
        this.voCode = voCode;
        this.status = status;
        this.phoneno = phoneno;
        this.gropofofficerpostname = gropofofficerpostname;
        this.groupcode = groupcode;
        this.memberid = memberid;
        this.dateofjoin = dateofjoin;
        this.fatherhasbandname = fatherhasbandname;
        this.gropmembername = gropmembername;
        this.groupname = groupname;
        this.uuid = uuid;
        this.isupdated = isupdated;
    }

    public void setBlockcode(String blockcode) {
        this.blockcode = blockcode;
    }

    public void setClustercode(String clustercode) {
        this.clustercode = clustercode;
    }

    public void setDistrictcode(String districtcode) {
        this.districtcode = districtcode;
    }

    public void setVillagecode(String villagecode) {
        this.villagecode = villagecode;
    }

    public void setVoCode(String voCode) {
        this.voCode = voCode;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setVomemerid(String vomemerid) {
        this.vomemerid = vomemerid;
    }

    public void setVlfcode(String vlfcode) {
        this.vlfcode = vlfcode;
    }

    public void setStatecode(String statecode) {
        this.statecode = statecode;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public void setGropofofficerpostname(String gropofofficerpostname) {
        this.gropofofficerpostname = gropofofficerpostname;
    }

    public void setGroupcode(String groupcode) {
        this.groupcode = groupcode;
    }

    public void setMemberid(String memberid) {
        this.memberid = memberid;
    }

    public void setClfcode(String clfcode) {
        this.clfcode = clfcode;
    }

    public void setDateofjoin(String dateofjoin) {
        this.dateofjoin = dateofjoin;
    }

    public void setFatherhasbandname(String fatherhasbandname) {
        this.fatherhasbandname = fatherhasbandname;
    }

    public void setGropmembername(String gropmembername) {
        this.gropmembername = gropmembername;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getIsupdated() {
        return isupdated;
    }

    public void setIsupdated(String isupdated) {
        this.isupdated = isupdated;
    }

    public String getUuid() {
        return uuid;
    }

    public String getGroupname() {
        return groupname;
    }

    public String getBlockcode() {
        return blockcode;
    }

    public String getClustercode() {
        return clustercode;
    }

    public String getDistrictcode() {
        return districtcode;
    }

    public String getVillagecode() {
        return villagecode;
    }

    public String getVoCode() {
        return voCode;
    }

    public String getStatus() {
        return status;
    }

    public String getVomemerid() {
        return vomemerid;
    }

    public String getVlfcode() {
        return vlfcode;
    }

    public String getStatecode() {
        return statecode;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public String getGropofofficerpostname() {
        return gropofofficerpostname;
    }

    public String getGroupcode() {
        return groupcode;
    }

    public String getMemberid() {
        return memberid;
    }

    public String getClfcode() {
        return clfcode;
    }

    public String getDateofjoin() {
        return dateofjoin;
    }

    public String getFatherhasbandname() {
        return fatherhasbandname;
    }

    public String getGropmembername() {
        return gropmembername;
    }
}
