package com.jslps.vompr;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;


public class PrefManager {

    private SharedPreferences pref;
    private Context _context;
    private SharedPreferences.Editor editor;
    private int PRIVATE_MODE = 0;
    private static PrefManager manager;
    private static final String PREF_DISTRICT_NAME = "districtName";
    private static final String PREF_BLOCK_NAME = "blockName";
    private static final String PREF_PANCHYAT_NAME = "panchyatName";
    private static final String PREF_VILLLAGE1 = "village1";
    private static final String PREF_NAME = "voMPR";
    private static final String PREF_Grop_of_officerPostName = "Grop_of_officerPostName";
    private static final String PREF_memberID = "memberID";
    private static final String PREF_GroupCode = "GroupCode";
    private static final String PREF_VOCode = "VOCode";
    public void init(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public static PrefManager getInstance() {
        if (manager == null) manager = new PrefManager();
        return manager;
    }
    public String getDistictNme() {
        return pref.getString(PREF_DISTRICT_NAME, "");
    }

    public void setDistictNme(String value) {
        editor.putString(PREF_DISTRICT_NAME, value);
        editor.commit();
    }

    public String getPrefBlockName() {
        return pref.getString(PREF_BLOCK_NAME, null);
    }

    public void setPrefBlockName(String blockName) {
        editor.putString(PREF_BLOCK_NAME, blockName);
        editor.commit();
    }
    public String getPREF_Grop_of_officerPostName() {
        return pref.getString(PREF_Grop_of_officerPostName, null);
    }

    public void setPREF_Grop_of_officerPostName(String blockName) {
        editor.putString(PREF_Grop_of_officerPostName, blockName);
        editor.commit();
    }

    public String getPREF_GroupCode() {
        return pref.getString(PREF_GroupCode, null);
    }

    public void setPREF_GroupCode(String groupCode) {
        editor.putString(PREF_GroupCode, groupCode);
        editor.commit();
    }
    public String getPREF_memberID() {
        return pref.getString(PREF_memberID, null);
    }

    public void setPREF__memberID(String memberID) {
        editor.putString(PREF_memberID, memberID);
        editor.commit();
    }
    public String getPREF_VOCode() {
        return pref.getString(PREF_VOCode, null);
    }

    public void setPREF_VOCode(String voCode) {
        editor.putString(PREF_VOCode, voCode);
        editor.commit();
    }
    public String getPrefPanchyatName() {
        return pref.getString(PREF_PANCHYAT_NAME, null);
    }

    public void setPrefPanchyatName(String imageLink) {
        editor.putString(PREF_PANCHYAT_NAME, imageLink);
        editor.commit();
    }

    public String getPrefVilllage1() {
        return pref.getString(PREF_VILLLAGE1, null);
    }

    public void setPrefVilllage1(String userApartmnet) {
        editor.putString(PREF_VILLLAGE1, userApartmnet);
        editor.commit();
    }
    public int getSize() {
        int size;
        Map<String, ?> entries = pref.getAll();
        if (entries != null) {
            size = entries.size();
        } else size = 0;

        return size;
    }


}
