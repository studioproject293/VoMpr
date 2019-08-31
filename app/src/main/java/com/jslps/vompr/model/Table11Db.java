package com.jslps.vompr.model;

import com.orm.SugarRecord;

public class Table11Db extends SugarRecord {

    private String groupcode;
    private String groupmcode;
    private String membername;

    public Table11Db() {
    }

    public Table11Db(String groupcode, String groupmcode, String membername) {
        this.groupcode = groupcode;
        this.groupmcode = groupmcode;
        this.membername = membername;
    }

    public Table11Db(String membername) {
       this.membername=membername;
    }

    public String getGroupcode() {
        return groupcode;
    }

    public String getGroupmcode() {
        return groupmcode;
    }

    public String getMembername() {
        return membername;
    }
    @Override
    public String toString() {
        return membername;
    }
}
