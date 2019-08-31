package com.jslps.vompr.model;

import com.orm.SugarRecord;

public class  Table8Db extends SugarRecord {
    private String blockcode;
    private String clustercode;
    private String districtcode;
    private String fedrationname;
    private String villagecode2;
    private String villagecode;
    private String formationdate;
    private String vocode;
    private String status;
    private String act;
    private String agencyname;
    private String compulsarysaving;
    private String contactnumber;
    private String fedregdate;
    private String meetingfrequency;
    private String rateofintrestoncif;
    private String totalshginfedration;
    private String totalshginvillage;
    private String villagecode3;
    private String villagecode4;
    private String villagecode5;
    private String voaddress;
    private String voformationagency;
    private String voOfficetype;
    private String registration;
    private String type;
    private String clustername;
    private String villagename;
    private String flagopen;
    private String flagclose;
    private String meetingdate;
    private String meetingno;
    public Table8Db() { }

    public Table8Db(String blockcode, String clustercode, String districtcode, String fedrationname, String villagecode2, String villagecode, String formationdate, String vocode, String status, String act, String agencyname, String compulsarysaving, String contactnumber, String fedregdate, String meetingfrequency, String rateofintrestoncif, String totalshginfedration, String totalshginvillage, String villagecode3, String villagecode4, String villagecode5, String voaddress, String voformationagency, String voOfficetype, String registration, String type, String clustername, String villagename, String flagopen, String flagclose, String meetingdate, String meetingno) {
        this.blockcode = blockcode;
        this.clustercode = clustercode;
        this.districtcode = districtcode;
        this.fedrationname = fedrationname;
        this.villagecode2 = villagecode2;
        this.villagecode = villagecode;
        this.formationdate = formationdate;
        this.vocode = vocode;
        this.status = status;
        this.act = act;
        this.agencyname = agencyname;
        this.compulsarysaving = compulsarysaving;
        this.contactnumber = contactnumber;
        this.fedregdate = fedregdate;
        this.meetingfrequency = meetingfrequency;
        this.rateofintrestoncif = rateofintrestoncif;
        this.totalshginfedration = totalshginfedration;
        this.totalshginvillage = totalshginvillage;
        this.villagecode3 = villagecode3;
        this.villagecode4 = villagecode4;
        this.villagecode5 = villagecode5;
        this.voaddress = voaddress;
        this.voformationagency = voformationagency;
        this.voOfficetype = voOfficetype;
        this.registration = registration;
        this.type = type;
        this.clustername = clustername;
        this.villagename = villagename;
        this.flagopen = flagopen;
        this.flagclose = flagclose;
        this.meetingdate = meetingdate;
        this.meetingno = meetingno;
    }

    public String getFlagopen() {
        return flagopen;
    }

    public String getFlagclose() {
        return flagclose;
    }

    public String getMeetingdate() {
        return meetingdate;
    }

    public String getMeetingno() {
        return meetingno;
    }

    public String getClustername() {
        return clustername;
    }

    public void setClustername(String clustername) {
        this.clustername = clustername;
    }

    public String getVillagename() {
        return villagename;
    }

    public void setVillagename(String villagename) {
        this.villagename = villagename;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAct() {
        return act;
    }

    public void setAct(String act) {
        this.act = act;
    }

    public String getAgencyname() {
        return agencyname;
    }

    public void setAgencyname(String agencyname) {
        this.agencyname = agencyname;
    }

    public String getCompulsarysaving() {
        return compulsarysaving;
    }

    public void setCompulsarysaving(String compulsarysaving) {
        this.compulsarysaving = compulsarysaving;
    }

    public String getContactnumber() {
        return contactnumber;
    }

    public void setContactnumber(String contactnumber) {
        this.contactnumber = contactnumber;
    }

    public String getFedregdate() {
        return fedregdate;
    }

    public void setFedregdate(String fedregdate) {
        this.fedregdate = fedregdate;
    }

    public String getMeetingfrequency() {
        return meetingfrequency;
    }

    public void setMeetingfrequency(String meetingfrequency) {
        this.meetingfrequency = meetingfrequency;
    }

    public String getRateofintrestoncif() {
        return rateofintrestoncif;
    }

    public void setRateofintrestoncif(String rateofintrestoncif) {
        this.rateofintrestoncif = rateofintrestoncif;
    }

    public String getTotalshginfedration() {
        return totalshginfedration;
    }

    public void setTotalshginfedration(String totalshginfedration) {
        this.totalshginfedration = totalshginfedration;
    }

    public String getTotalshginvillage() {
        return totalshginvillage;
    }

    public void setTotalshginvillage(String totalshginvillage) {
        this.totalshginvillage = totalshginvillage;
    }

    public String getVillagecode3() {
        return villagecode3;
    }

    public void setVillagecode3(String villagecode3) {
        this.villagecode3 = villagecode3;
    }

    public String getVillagecode4() {
        return villagecode4;
    }

    public void setVillagecode4(String villagecode4) {
        this.villagecode4 = villagecode4;
    }

    public String getVillagecode5() {
        return villagecode5;
    }

    public void setVillagecode5(String villagecode5) {
        this.villagecode5 = villagecode5;
    }

    public String getVoaddress() {
        return voaddress;
    }

    public void setVoaddress(String voaddress) {
        this.voaddress = voaddress;
    }

    public String getVoformationagency() {
        return voformationagency;
    }

    public void setVoformationagency(String voformationagency) {
        this.voformationagency = voformationagency;
    }

    public String getVoOfficetype() {
        return voOfficetype;
    }

    public void setVoOfficetype(String voOfficetype) {
        this.voOfficetype = voOfficetype;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getVillagecode() {
        return villagecode;
    }

    public void setVillagecode(String villagecode) {
        this.villagecode = villagecode;
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

    public String getDistrictcode() {
        return districtcode;
    }

    public void setDistrictcode(String districtcode) {
        this.districtcode = districtcode;
    }

    public String getFedrationname() {
        return fedrationname;
    }

    public void setFedrationname(String fedrationname) {
        this.fedrationname = fedrationname;
    }

    public String getVillagecode2() {
        return villagecode2;
    }

    public void setVillagecode2(String villagecode2) {
        this.villagecode2 = villagecode2;
    }

    public String getFormationdate() {
        return formationdate;
    }

    public void setFormationdate(String formationdate) {
        this.formationdate = formationdate;
    }

    public String getVocode() {
        return vocode;
    }

    public void setVocode(String vocode) {
        this.vocode = vocode;
    }
}
