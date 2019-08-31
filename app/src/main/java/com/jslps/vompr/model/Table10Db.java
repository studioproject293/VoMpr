package com.jslps.vompr.model;

import com.orm.SugarRecord;

public class Table10Db extends SugarRecord {
    private String groupcode;
    private String groupname;

    public Table10Db() {
    }

    public Table10Db(String membername) {
        this.groupname = membername;
    }

    public Table10Db(String groupcode, String groupname) {
        this.groupcode = groupcode;
        this.groupname = groupname;
    }

    public String getGroupcode() {
        return groupcode;
    }

    public String getGroupname() {
        return groupname;
    }

    @Override
    public String toString() {
        return groupname;
    }
}
