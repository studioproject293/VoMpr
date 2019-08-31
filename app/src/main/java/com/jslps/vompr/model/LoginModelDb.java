package com.jslps.vompr.model;

import com.orm.SugarRecord;

public class LoginModelDb extends SugarRecord {
    private String loginid;
    private String username;
    private String password;
    private String roleid;
    private String rsetiid;
    private String userlevel;
    private String districtcode;
    private String blockcode;
    private String panchayatcode;

    public LoginModelDb() { }


    public LoginModelDb(String loginid, String username, String password, String roleid, String rsetiid, String userlevel,
                        String districtcode, String blockcode, String panchayatcode) {
        this.loginid = loginid;
        this.username = username;
        this.password = password;
        this.roleid = roleid;
        this.rsetiid = rsetiid;
        this.userlevel = userlevel;
        this.districtcode = districtcode;
        this.blockcode = blockcode;
        this.panchayatcode = panchayatcode;
    }

    public String getLoginid() {
        return loginid;
    }

    public void setLoginid(String loginid) {
        this.loginid = loginid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }

    public String getRsetiid() {
        return rsetiid;
    }

    public void setRsetiid(String rsetiid) {
        this.rsetiid = rsetiid;
    }

    public String getUserlevel() {
        return userlevel;
    }

    public void setUserlevel(String userlevel) {
        this.userlevel = userlevel;
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

    public String getPanchayatcode() {
        return panchayatcode;
    }

    public void setPanchayatcode(String panchayatcode) {
        this.panchayatcode = panchayatcode;
    }
}
