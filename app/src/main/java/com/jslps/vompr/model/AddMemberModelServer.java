package com.jslps.vompr.model;

public class AddMemberModelServer  {
    private int VOCode;
    private int GroupCode;
    private int memberID;
    private String Group_Name;
    private String Father_hasbandName;
    private String GropMemberName;
    private String Grop_of_officerPostName;
    private String PhoneNo;
    private String DateofJoin;
    private String Status;


    public int getVOCode() {
        return VOCode;
    }

    public void setVOCode(int VOCode) {
        this.VOCode = VOCode;
    }

    public int getGroupCode() {
        return GroupCode;
    }

    public void setGroupCode(int groupCode) {
        GroupCode = groupCode;
    }

    public int getMemberID() {
        return memberID;
    }

    public void setMemberID(int memberID) {
        this.memberID = memberID;
    }

    public String getGroup_Name() {
        return Group_Name;
    }

    public void setGroup_Name(String group_Name) {
        Group_Name = group_Name;
    }

    public String getFather_hasbandName() {
        return Father_hasbandName;
    }

    public void setFather_hasbandName(String father_hasbandName) {
        Father_hasbandName = father_hasbandName;
    }

    public String getGropMemberName() {
        return GropMemberName;
    }

    public void setGropMemberName(String gropMemberName) {
        GropMemberName = gropMemberName;
    }

    public String getGrop_of_officerPostName() {
        return Grop_of_officerPostName;
    }

    public void setGrop_of_officerPostName(String grop_of_officerPostName) {
        Grop_of_officerPostName = grop_of_officerPostName;
    }

    public String getPhoneNo() {
        return PhoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        PhoneNo = phoneNo;
    }

    public String getDateofJoin() {
        return DateofJoin;
    }

    public void setDateofJoin(String dateofJoin) {
        DateofJoin = dateofJoin;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
