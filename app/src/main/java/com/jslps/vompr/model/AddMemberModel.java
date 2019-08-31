package com.jslps.vompr.model;

import com.orm.SugarRecord;

public class AddMemberModel extends SugarRecord {
    private int vocode;
    private int groupcode;
    private int memberid;
    private String groupname;
    private String groupmembername;
    private String fatherhushbandname;
    private String groupofofficerpostname;
    private String phoneno;
    private String dateofjoin;
    private String status;
    private String uuid;

    public String getGroupmembername() {
        return groupmembername;
    }

    public AddMemberModel() {
    }

    public AddMemberModel(int vocode, int groupcode, int memberid, String groupname, String groupmembername, String fatherhushbandname, String groupofofficerpostname, String phoneno, String dateofjoin, String status, String uuid) {
        this.vocode = vocode;
        this.groupcode = groupcode;
        this.memberid = memberid;
        this.groupname = groupname;
        this.fatherhushbandname = fatherhushbandname;
        this.groupofofficerpostname = groupofofficerpostname;
        this.phoneno = phoneno;
        this.dateofjoin = dateofjoin;
        this.status = status;
        this.groupmembername = groupmembername;
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }

    public int getVocode() {
        return vocode;
    }

    public int getGroupcode() {
        return groupcode;
    }

    public int getMemberid() {
        return memberid;
    }

    public String getGroupname() {
        return groupname;
    }

    public String getFatherhushbandname() {
        return fatherhushbandname;
    }

    public String getGroupofofficerpostname() {
        return groupofofficerpostname;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public String getDateofjoin() {
        return dateofjoin;
    }

    public String getStatus() {
        return status;
    }
}
