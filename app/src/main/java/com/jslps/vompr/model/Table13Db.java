package com.jslps.vompr.model;

import com.orm.SugarRecord;

public class Table13Db extends SugarRecord {
    private String blockcode;
    private String clustercode;
    private String districtcode;
    private String clbal;
    private String villagecode;
    private String meetingyear;
    private String modificationno;
    private String createdby;
    private String createdon;
    private String flagclose;
    private String flagexport;
    private String flagopen;
    private String meetingdate;
    private String meeting_month;
    private String modifiedby;
    private String modifiedon;
    private String no;
    private String presentshg;
    private String statecode;
    private String totalmeetingheld;
    private String totalnoshg;
    private String vocode;
    public Table13Db() { }

    public Table13Db(String blockcode, String clustercode, String districtcode, String clbal, String villagecode,
                     String meetingyear, String modificationno, String createdby, String createdon, String flagclose,
                     String flagexport, String flagopen, String meetingdate, String meeting_month, String modifiedby, String modifiedon,
                     String no, String presentshg, String statecode, String totalmeetingheld, String totalnoshg, String vocode) {
        this.blockcode = blockcode;
        this.clustercode = clustercode;
        this.districtcode = districtcode;
        this.clbal = clbal;
        this.villagecode = villagecode;
        this.meetingyear = meetingyear;
        this.modificationno = modificationno;
        this.createdby = createdby;
        this.createdon = createdon;
        this.flagclose = flagclose;
        this.flagexport = flagexport;
        this.flagopen = flagopen;
        this.meetingdate = meetingdate;
        this.meeting_month = meeting_month;
        this.modifiedby = modifiedby;
        this.modifiedon = modifiedon;
        this.no = no;
        this.presentshg = presentshg;
        this.statecode = statecode;
        this.totalmeetingheld = totalmeetingheld;
        this.totalnoshg = totalnoshg;
        this.vocode = vocode;
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

    public String getClbal() {
        return clbal;
    }

    public String getVillagecode() {
        return villagecode;
    }

    public String getMeetingyear() {
        return meetingyear;
    }

    public String getModificationno() {
        return modificationno;
    }

    public String getCreatedby() {
        return createdby;
    }

    public String getCreatedon() {
        return createdon;
    }

    public String getFlagclose() {
        return flagclose;
    }

    public String getFlagexport() {
        return flagexport;
    }

    public String getFlagopen() {
        return flagopen;
    }

    public String getMeetingdate() {
        return meetingdate;
    }

    public String getMeeting_month() {
        return meeting_month;
    }

    public String getModifiedby() {
        return modifiedby;
    }

    public String getModifiedon() {
        return modifiedon;
    }

    public String getNo() {
        return no;
    }

    public String getPresentshg() {
        return presentshg;
    }

    public String getStatecode() {
        return statecode;
    }

    public String getTotalmeetingheld() {
        return totalmeetingheld;
    }

    public String getTotalnoshg() {
        return totalnoshg;
    }

    public String getVocode() {
        return vocode;
    }
}
