package com.jslps.vompr.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.chootdev.csnackbar.Align;
import com.chootdev.csnackbar.Duration;
import com.chootdev.csnackbar.Snackbar;
import com.chootdev.csnackbar.Type;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jslps.vompr.Constant;
import com.jslps.vompr.DialogUtil;
import com.jslps.vompr.PrefManager;
import com.jslps.vompr.R;
import com.jslps.vompr.model.LoginModel;
import com.jslps.vompr.model.LoginModelDb;
import com.jslps.vompr.model.StateModelDb;
import com.jslps.vompr.model.Table10Db;
import com.jslps.vompr.model.Table11Db;
import com.jslps.vompr.model.Table13Db;
import com.jslps.vompr.model.Table1Db;
import com.jslps.vompr.model.Table2Db;
import com.jslps.vompr.model.Table3Db;
import com.jslps.vompr.model.Table8Db;
import com.jslps.vompr.model.Table9Db;
import com.jslps.vompr.services.ApiServices;
import com.orm.SugarContext;
import com.orm.query.Condition;
import com.orm.query.Select;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class LoginActivity extends AppCompatActivity {
    private Button buttonLogin;
    private EditText etusername, editTextPassword;
    CheckBox checkBox, checkboxRember;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.layout_new_login);
        PrefManager.getInstance().init(this);
        SugarContext.init(this);
        buttonLogin = findViewById(R.id.btnLogin);
        etusername = findViewById(R.id.etusername);
        editTextPassword = findViewById(R.id.etpass);
        checkBox = findViewById(R.id.checkbox);
        checkboxRember = findViewById(R.id.checkboxRember);
        preferences = getSharedPreferences("MyPref",
                Context.MODE_PRIVATE);

        String value = preferences.getString("userName", "");
        String value1 = preferences.getString("Password", "");
        if (!TextUtils.isEmpty(value) && !TextUtils.isEmpty(value1)) {
            etusername.setText(value);
            etusername.setEnabled(true);
            editTextPassword.setText(value1);
        } else {
            etusername.setText("");
            etusername.setEnabled(true);
            editTextPassword.setText("");
        }
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked())
                    editTextPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                else
                    editTextPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());


            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etusername.getText().toString().isEmpty()) {
                    etusername.setError("Please enter user name");
                    etusername.requestFocus();
                    showError(etusername);
                } else if (editTextPassword.getText().toString().isEmpty()) {
                    editTextPassword.setError("Please enter user password");
                    editTextPassword.requestFocus();
                    showError(editTextPassword);
                } else {
                    DialogUtil.displayProgress(LoginActivity.this);
                    ArrayList<LoginModelDb> arrayListVillage1 = (ArrayList<LoginModelDb>) Select.from(LoginModelDb.class)
                            .where(Condition.prop("username").eq(etusername.getText().toString()),
                                    Condition.prop("password").eq(editTextPassword.getText().toString())).list();
                    System.out.println("LogInDbsdfsdfs" + new Gson().toJson(arrayListVillage1));
                    if (arrayListVillage1 != null && arrayListVillage1.size() > 0) {

                        if (arrayListVillage1.get(0).getUsername().equals(etusername.getText().toString()) || arrayListVillage1.get(0).getUsername().equals(editTextPassword.getText().toString())) {
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            if (checkboxRember.isChecked()) {
                                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
                                SharedPreferences.Editor editor = pref.edit();
                                editor.putString("userName", etusername.getText().toString());
                                editor.putString("Password", editTextPassword.getText().toString());
                                editor.apply();
                            }
                            DialogUtil.stopProgressDisplay();
                        } else {
                            Gson gson = new GsonBuilder().setLenient().create();
                            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                            OkHttpClient.Builder builder = new OkHttpClient.Builder();
                            //comment in live build and uncomment in uat
                            builder.interceptors().add(interceptor);

                            builder.connectTimeout(120, TimeUnit.SECONDS);
                            builder.readTimeout(120, TimeUnit.SECONDS);
                            OkHttpClient client = builder.build();
                            Retrofit retrofit = new Retrofit.Builder().baseUrl(Constant.API_BASE_URL).addConverterFactory(ScalarsConverterFactory.create()).client(client).build();
                            apiServices = retrofit.create(ApiServices.class);
                            Call<String> changePhotoResponseModelCall = apiServices.getProduct(etusername.getText().toString(), editTextPassword.getText().toString());
                            changePhotoResponseModelCall.enqueue(new Callback<String>() {
                                @Override
                                public void onResponse(Call<String> call, Response<String> response) {
                                    DialogUtil.stopProgressDisplay();
                                    Gson gson = new Gson();
                                    Log.v("Response prof :", "hgfgfrhgs" + response.body());

                                    String fullResponse = response.body();
                                    String XmlString = fullResponse.substring(fullResponse.indexOf("\">") + 2);
                                    String result = XmlString.replaceAll("</string>", "");

                                    System.out.print("fhrjfghf" + result);
                                    LoginModel mStudentObject1 = gson.fromJson(result, LoginModel.class);
                                    System.out.println("vvh" + gson.toJson(mStudentObject1));
                                    StateModelDb.deleteAll(StateModelDb.class);
                                    if (mStudentObject1.getStateModel() != null && mStudentObject1.getStateModel().size() > 0) {
                                        if (checkboxRember.isChecked()) {
                                            SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
                                            SharedPreferences.Editor editor = pref.edit();
                                            editor.putString("userName", etusername.getText().toString());
                                            editor.putString("Password", editTextPassword.getText().toString());
                                            editor.apply();
                                        }
                                        for (int i = 0; i < mStudentObject1.getStateModel().size(); i++) {
                                            /* if (stateModelArrayList != null && stateModelArrayList.size() > 0) {*/

                                            //  if (!stateModelArrayList.get(i).getDistrictName().equals(mStudentObject1.getStateModel().get(i).getDistrictName())) {
                                            StateModelDb stateModel1 = new StateModelDb(mStudentObject1.getStateModel().get(i).getStateCode(), mStudentObject1.getStateModel().get(i).getDistrictCode()
                                                    , mStudentObject1.getStateModel().get(i).getDistrictName(), mStudentObject1.getStateModel().get(i).getDistrictName_H());
                                            stateModel1.save();
                                        }
                                   /* } else {
                                        StateModel stateModel1 = new StateModel(mStudentObject1.getStateModel().get(i).getStateCode(), mStudentObject1.getStateModel().get(i).getDistrictCode()
                                                , mStudentObject1.getStateModel().get(i).getDistrictName(), mStudentObject1.getStateModel().get(i).getDistrictName_H());
                                        stateModel1.save();
                                    }*/
                                        // }
                                        Table1Db.deleteAll(Table1Db.class);
                                        for (int i = 0; i < mStudentObject1.getTable1().size(); i++) {

                                            Table1Db table1 = new Table1Db(mStudentObject1.getTable1().get(i).getStateCode(), mStudentObject1.getTable1().get(i).getDistrictCode(),
                                                    mStudentObject1.getTable1().get(i).getBlockCode(), mStudentObject1.getTable1().get(i).getBlockName(),
                                                    mStudentObject1.getTable1().get(i).getBlockName_H());
                                            table1.save();
                                        }
                                        Table2Db.deleteAll(Table2Db.class);
                                        for (int i = 0; i < mStudentObject1.getTable2().size(); i++) {

                                            Table2Db table2 = new Table2Db(mStudentObject1.getTable2().get(i).getStateCode(), mStudentObject1.getTable2().get(i).getDistrictCode(),
                                                    mStudentObject1.getTable2().get(i).getBlockCode(), mStudentObject1.getTable2().get(i).getClusterCode()
                                                    , mStudentObject1.getTable2().get(i).getClusterName(),
                                                    mStudentObject1.getTable2().get(i).getClusterName_H());
                                            table2.save();
                                        }
                                        Table3Db.deleteAll(Table3Db.class);
                                        for (int i = 0; i < mStudentObject1.getTable3().size(); i++) {

                                            Table3Db table3 = new Table3Db(mStudentObject1.getTable3().get(i).getStateCode(), mStudentObject1.getTable3().get(i).getDistrictCode(),
                                                    mStudentObject1.getTable3().get(i).getBlockCode(), mStudentObject1.getTable3().get(i).getClusterCode(),
                                                    mStudentObject1.getTable3().get(i).getVillageCode(), mStudentObject1.getTable3().get(i).getVillageName(),
                                                    mStudentObject1.getTable3().get(i).getVillageName_H());
                                            table3.save();
                                        }
                                        Table8Db.deleteAll(Table8Db.class);
                                        for (int i = 0; i < mStudentObject1.getTable7().size(); i++) {

                                            Table8Db table8Db = new Table8Db(mStudentObject1.getTable7().get(i).getBlockcode(), mStudentObject1.getTable7().get(i).getClustercode(),
                                                    mStudentObject1.getTable7().get(i).getDistrictcode(), mStudentObject1.getTable7().get(i).getFedration_name(),
                                                    mStudentObject1.getTable7().get(i).getVillageCode2(), mStudentObject1.getTable7().get(i).getVillagecode(),
                                                    mStudentObject1.getTable7().get(i).getFormation_Date(), mStudentObject1.getTable7().get(i).getVOCode(),
                                                    mStudentObject1.getTable7().get(i).getStatus(), mStudentObject1.getTable7().get(i).getAct(), mStudentObject1.getTable7().get(i).getAgencyName(),
                                                    mStudentObject1.getTable7().get(i).getCompulsary_Saving(), mStudentObject1.getTable7().get(i).getContactNumber(), mStudentObject1.getTable7().get(i).getFed_Reg_Date(),
                                                    mStudentObject1.getTable7().get(i).getMeeting_Frequency(), mStudentObject1.getTable7().get(i).getRate_of_intrest_on_CIF(), mStudentObject1.getTable7().get(i).getTotal_SHG_in_fedration(),
                                                    mStudentObject1.getTable7().get(i).getTotal_SHG_in_Village(), mStudentObject1.getTable7().get(i).getVillageCode3(), mStudentObject1.getTable7().get(i).getVillagecode4(),
                                                    mStudentObject1.getTable7().get(i).getVillagecode5(), mStudentObject1.getTable7().get(i).getVOaddress(), mStudentObject1.getTable7().get(i).getVOFormationagency(),
                                                    mStudentObject1.getTable7().get(i).getVo_Office_Type(), mStudentObject1.getTable7().get(i).getRegistration(),
                                                    mStudentObject1.getTable7().get(i).getType(), mStudentObject1.getTable7().get(i).getClusterName(), mStudentObject1.getTable7().get(i).getVillagename(),
                                                    mStudentObject1.getTable7().get(i).getFlagOpen(),mStudentObject1.getTable7().get(i).getFlagClose(),mStudentObject1.getTable7().get(i).getMeetingDate(),
                                                    mStudentObject1.getTable7().get(i).getMeetingNo());
                                            table8Db.save();
                                        }
                                        Table9Db.deleteAll(Table9Db.class);
                                        for (int i = 0; i < mStudentObject1.getTable8().size(); i++) {

                                            Table9Db table9Db = new Table9Db(mStudentObject1.getTable8().get(i).getBlockcode(),
                                                    mStudentObject1.getTable8().get(i).getClustercode(),
                                                    mStudentObject1.getTable8().get(i).getDistrictcode(),
                                                    mStudentObject1.getTable8().get(i).getVillagecode(),
                                                    mStudentObject1.getTable8().get(i).getVOCode(), mStudentObject1.getTable8().get(i).getStatus(),
                                                    mStudentObject1.getTable8().get(i).getVOMemerID(), mStudentObject1.getTable8().get(i).getVLFCode(),
                                                    mStudentObject1.getTable8().get(i).getStatecode(), mStudentObject1.getTable8().get(i).getPhoneNo(),
                                                    mStudentObject1.getTable8().get(i).getGrop_of_officerPostName(), mStudentObject1.getTable8().get(i).getGroupCode(),
                                                    mStudentObject1.getTable8().get(i).getMemberID(), mStudentObject1.getTable8().get(i).getCLFCode(),
                                                    mStudentObject1.getTable8().get(i).getDateofJoin(), mStudentObject1.getTable8().get(i).getFather_hasbandName(),
                                                    mStudentObject1.getTable8().get(i).getGropMemberName(), mStudentObject1.getTable8().get(i).getGroup_Name());
                                            table9Db.save();
                                        }
                                        LoginModelDb.deleteAll(LoginModelDb.class);
                                        for (int i = 0; i < mStudentObject1.getTable4().size(); i++) {

                                            LoginModelDb loginModelDb = new LoginModelDb(mStudentObject1.getTable4().get(i).getLoginID().toString(),
                                                    mStudentObject1.getTable4().get(i).getUsername(),
                                                    mStudentObject1.getTable4().get(i).getPassword(), mStudentObject1.getTable4().get(i).getRoleID().toString(),
                                                    mStudentObject1.getTable4().get(i).getRsetiID().toString(), mStudentObject1.getTable4().get(i).getUserlevel(),
                                                    mStudentObject1.getTable4().get(i).getDistrictCode(), mStudentObject1.getTable4().get(i).getBlockCode(),
                                                    mStudentObject1.getTable4().get(i).getPanchayatCode());
                                            loginModelDb.save();
                                            System.out.println("LogInDb" + new Gson().toJson(loginModelDb));

                                        }
                                        Table13Db.deleteAll(Table13Db.class);
                                        for (int i = 0; i < mStudentObject1.getTable11().size(); i++) {
                                            Table13Db table13Db = new Table13Db(mStudentObject1.getTable11().get(i).getBlockcode(),
                                                    mStudentObject1.getTable11().get(i).getClustercode(), mStudentObject1.getTable11().get(i).getDistrictcode(),
                                                    mStudentObject1.getTable11().get(i).getClBal(), mStudentObject1.getTable11().get(i).getVillagecode(),
                                                    mStudentObject1.getTable11().get(i).getMeeting_Year(), mStudentObject1.getTable11().get(i).getModificationNo(),
                                                    mStudentObject1.getTable11().get(i).getCreatedBy(), mStudentObject1.getTable11().get(i).getCreatedOn(),
                                                    mStudentObject1.getTable11().get(i).getFlagClose(), mStudentObject1.getTable11().get(i).getFlagExport(),
                                                    mStudentObject1.getTable11().get(i).getFlagOpen(), mStudentObject1.getTable11().get(i).getMeetingDate(),
                                                    mStudentObject1.getTable11().get(i).getMeeting_Month(), mStudentObject1.getTable11().get(i).getModifiedBy(),
                                                    mStudentObject1.getTable11().get(i).getModifiedOn(), mStudentObject1.getTable11().get(i).getNo(),
                                                    mStudentObject1.getTable11().get(i).getPresentSHG(), mStudentObject1.getTable11().get(i).getStateCode(),
                                                    mStudentObject1.getTable11().get(i).getTotalMeetingHeld(),mStudentObject1.getTable11().get(i).getTotalNoSHG(),
                                                    mStudentObject1.getTable11().get(i).getVOCode());
                                            table13Db.save();
                                            System.out.println("LogInDbVioMember" + new Gson().toJson(table13Db));

                                        }
                                        ArrayList<LoginModelDb> loginModelDbs = new ArrayList<>();
                                        loginModelDbs = (ArrayList<LoginModelDb>) LoginModelDb.listAll(LoginModelDb.class);
                                        /*(ArrayList<LoginModelDb>) LoginModelDb.listAll(LoginModelDb.class))*/
                                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                        startActivity(intent);
                                    } else {
                                        DialogUtil.stopProgressDisplay();
                                        Snackbar.with(LoginActivity.this, null)
                                                .type(Type.ERROR)
                                                .message("Invalid User Details")
                                                .duration(Duration.SHORT)
                                                .fillParent(true)
                                                .textAlign(Align.LEFT)
                                                .show();
                                    }
//                            StateModel stateModel = new StateModel();
//                            System.out.print("bhdvhjfdghg" + gson.toJson(stateModel.listAll(StateModel.class)));
//                        String XmlString = response.body().substring(response.body().indexOf("\">")+2);
//                        String result = response.body().replace("<string xmlns=\"http://tempuri.org/\">","");
//                        String result1 = result.replace("</string>","");
//                        String result2 = result1.replace("<?xml version=\"1.0\" encoding=\"utf-8\"?>","");

//                        LoginModel mStudentObject1 = gson.fromJson(result2, LoginModel.class);
//                        Log.d("dkjfdsn","dfhvj"+result2);
//                        Log.d("dkjfdsn","fwshjvshj"+gson.toJson(mStudentObject1));
                                    // StateModel stateModel=new StateModel()

                                }

                                @Override
                                public void onFailure(Call<String> call, Throwable t) {
                                    DialogUtil.stopProgressDisplay();
                                    Log.v("Error in take photo:", t.toString());
                                    Snackbar.with(LoginActivity.this, null)
                                            .type(Type.ERROR)
                                            .message(t.toString())
                                            .duration(Duration.SHORT)
                                            .fillParent(true)
                                            .textAlign(Align.LEFT)
                                            .show();
                                }
                            });
                        }
                    } else {
                        Gson gson = new GsonBuilder().setLenient().create();
                        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                        OkHttpClient.Builder builder = new OkHttpClient.Builder();
                        //comment in live build and uncomment in uat
                        builder.interceptors().add(interceptor);

                        builder.connectTimeout(120, TimeUnit.SECONDS);
                        builder.readTimeout(120, TimeUnit.SECONDS);

                        OkHttpClient client = builder.build();
                        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constant.API_BASE_URL).addConverterFactory(ScalarsConverterFactory.create()).client(client).build();
                        apiServices = retrofit.create(ApiServices.class);
                        Call<String> changePhotoResponseModelCall = apiServices.getProduct(etusername.getText().toString(), editTextPassword.getText().toString());
                        changePhotoResponseModelCall.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                DialogUtil.stopProgressDisplay();
                                Gson gson = new Gson();
                                Log.v("Response prof :", "hgfgfrhgs" + response.body());

                                String fullResponse = response.body();
                                String XmlString = fullResponse.substring(fullResponse.indexOf("\">") + 2);
                                String result = XmlString.replaceAll("</string>", "");

                                System.out.print("fhrjfghf" + result);
                                LoginModel mStudentObject1 = gson.fromJson(result, LoginModel.class);
                                System.out.println("vvh" + gson.toJson(mStudentObject1));
                                StateModelDb.deleteAll(StateModelDb.class);
                                if (mStudentObject1.getStateModel() != null && mStudentObject1.getStateModel().size() > 0) {
                                    for (int i = 0; i < mStudentObject1.getStateModel().size(); i++) {
                                        /* if (stateModelArrayList != null && stateModelArrayList.size() > 0) {*/

                                        //  if (!stateModelArrayList.get(i).getDistrictName().equals(mStudentObject1.getStateModel().get(i).getDistrictName())) {
                                        StateModelDb stateModel1 = new StateModelDb(mStudentObject1.getStateModel().get(i).getStateCode(), mStudentObject1.getStateModel().get(i).getDistrictCode()
                                                , mStudentObject1.getStateModel().get(i).getDistrictName(), mStudentObject1.getStateModel().get(i).getDistrictName_H());
                                        stateModel1.save();
                                    }
                                   /* } else {
                                        StateModel stateModel1 = new StateModel(mStudentObject1.getStateModel().get(i).getStateCode(), mStudentObject1.getStateModel().get(i).getDistrictCode()
                                                , mStudentObject1.getStateModel().get(i).getDistrictName(), mStudentObject1.getStateModel().get(i).getDistrictName_H());
                                        stateModel1.save();
                                    }*/
                                    // }
                                    Table1Db.deleteAll(Table1Db.class);
                                    for (int i = 0; i < mStudentObject1.getTable1().size(); i++) {

                                        Table1Db table1 = new Table1Db(mStudentObject1.getTable1().get(i).getStateCode(), mStudentObject1.getTable1().get(i).getDistrictCode(),
                                                mStudentObject1.getTable1().get(i).getBlockCode(), mStudentObject1.getTable1().get(i).getBlockName(),
                                                mStudentObject1.getTable1().get(i).getBlockName_H());
                                        table1.save();
                                    }
                                    Table2Db.deleteAll(Table2Db.class);
                                    for (int i = 0; i < mStudentObject1.getTable2().size(); i++) {

                                        Table2Db table2 = new Table2Db(mStudentObject1.getTable2().get(i).getStateCode(), mStudentObject1.getTable2().get(i).getDistrictCode(),
                                                mStudentObject1.getTable2().get(i).getBlockCode(), mStudentObject1.getTable2().get(i).getClusterCode()
                                                , mStudentObject1.getTable2().get(i).getClusterName(),
                                                mStudentObject1.getTable2().get(i).getClusterName_H());
                                        table2.save();
                                    }
                                    Table3Db.deleteAll(Table3Db.class);
                                    for (int i = 0; i < mStudentObject1.getTable3().size(); i++) {

                                        Table3Db table3 = new Table3Db(mStudentObject1.getTable3().get(i).getStateCode(), mStudentObject1.getTable3().get(i).getDistrictCode(),
                                                mStudentObject1.getTable3().get(i).getBlockCode(), mStudentObject1.getTable3().get(i).getClusterCode(),
                                                mStudentObject1.getTable3().get(i).getVillageCode(), mStudentObject1.getTable3().get(i).getVillageName(),
                                                mStudentObject1.getTable3().get(i).getVillageName_H());
                                        table3.save();
                                    }
                                    Table8Db.deleteAll(Table8Db.class);
                                    for (int i = 0; i < mStudentObject1.getTable7().size(); i++) {

                                        Table8Db table8Db = new Table8Db(mStudentObject1.getTable7().get(i).getBlockcode(), mStudentObject1.getTable7().get(i).getClustercode(),
                                                mStudentObject1.getTable7().get(i).getDistrictcode(), mStudentObject1.getTable7().get(i).getFedration_name(),
                                                mStudentObject1.getTable7().get(i).getVillageCode2(), mStudentObject1.getTable7().get(i).getVillagecode(),
                                                mStudentObject1.getTable7().get(i).getFormation_Date(), mStudentObject1.getTable7().get(i).getVOCode(),
                                                mStudentObject1.getTable7().get(i).getStatus(), mStudentObject1.getTable7().get(i).getAct(), mStudentObject1.getTable7().get(i).getAgencyName(),
                                                mStudentObject1.getTable7().get(i).getCompulsary_Saving(), mStudentObject1.getTable7().get(i).getContactNumber(), mStudentObject1.getTable7().get(i).getFed_Reg_Date(),
                                                mStudentObject1.getTable7().get(i).getMeeting_Frequency(), mStudentObject1.getTable7().get(i).getRate_of_intrest_on_CIF(), mStudentObject1.getTable7().get(i).getTotal_SHG_in_fedration(),
                                                mStudentObject1.getTable7().get(i).getTotal_SHG_in_Village(), mStudentObject1.getTable7().get(i).getVillageCode3(), mStudentObject1.getTable7().get(i).getVillagecode4(),
                                                mStudentObject1.getTable7().get(i).getVillagecode5(), mStudentObject1.getTable7().get(i).getVOaddress(), mStudentObject1.getTable7().get(i).getVOFormationagency(),
                                                mStudentObject1.getTable7().get(i).getVo_Office_Type(), mStudentObject1.getTable7().get(i).getRegistration(),
                                                mStudentObject1.getTable7().get(i).getType(), mStudentObject1.getTable7().get(i).getClusterName(), mStudentObject1.getTable7().get(i).getVillagename(),
                                                mStudentObject1.getTable7().get(i).getFlagOpen(),mStudentObject1.getTable7().get(i).getFlagClose(),mStudentObject1.getTable7().get(i).getMeetingDate(),
                                                mStudentObject1.getTable7().get(i).getMeetingNo());
                                        table8Db.save();
                                    }
                                    Table9Db.deleteAll(Table9Db.class);
                                    for (int i = 0; i < mStudentObject1.getTable8().size(); i++) {

                                        Table9Db table9Db = new Table9Db(mStudentObject1.getTable8().get(i).getBlockcode(),
                                                mStudentObject1.getTable8().get(i).getClustercode(),
                                                mStudentObject1.getTable8().get(i).getDistrictcode(),
                                                mStudentObject1.getTable8().get(i).getVillagecode(),
                                                mStudentObject1.getTable8().get(i).getVOCode(), mStudentObject1.getTable8().get(i).getStatus(),
                                                mStudentObject1.getTable8().get(i).getVOMemerID(), mStudentObject1.getTable8().get(i).getVLFCode(),
                                                mStudentObject1.getTable8().get(i).getStatecode(), mStudentObject1.getTable8().get(i).getPhoneNo(),
                                                mStudentObject1.getTable8().get(i).getGrop_of_officerPostName(), mStudentObject1.getTable8().get(i).getGroupCode(),
                                                mStudentObject1.getTable8().get(i).getMemberID(), mStudentObject1.getTable8().get(i).getCLFCode(),
                                                mStudentObject1.getTable8().get(i).getDateofJoin(), mStudentObject1.getTable8().get(i).getFather_hasbandName(),
                                                mStudentObject1.getTable8().get(i).getGropMemberName(), mStudentObject1.getTable8().get(i).getGroup_Name());
                                        table9Db.save();
                                    }
                                    Table10Db.deleteAll(Table10Db.class);
                                    for (int i = 0; i < mStudentObject1.getTable9().size(); i++) {

                                        Table10Db table10Db = new Table10Db(mStudentObject1.getTable9().get(i).getGroupCode(),
                                                mStudentObject1.getTable9().get(i).getGroup_Name());
                                        table10Db.save();
                                    }
                                    Table11Db.deleteAll(Table11Db.class);
                                    for (int i = 0; i < mStudentObject1.getTable10().size(); i++) {

                                        Table11Db table11Db = new Table11Db(mStudentObject1.getTable10().get(i).getGroupcode(),
                                                mStudentObject1.getTable10().get(i).getGroup_M_Code(), mStudentObject1.getTable10().get(i).getMembername());
                                        table11Db.save();
                                    }
                                    LoginModelDb.deleteAll(LoginModelDb.class);
                                    for (int i = 0; i < mStudentObject1.getTable4().size(); i++) {

                                        LoginModelDb loginModelDb = new LoginModelDb(mStudentObject1.getTable4().get(i).getLoginID().toString(),
                                                mStudentObject1.getTable4().get(i).getUsername(),
                                                mStudentObject1.getTable4().get(i).getPassword(), mStudentObject1.getTable4().get(i).getRoleID().toString(),
                                                mStudentObject1.getTable4().get(i).getRsetiID().toString(), mStudentObject1.getTable4().get(i).getUserlevel(),
                                                mStudentObject1.getTable4().get(i).getDistrictCode(), mStudentObject1.getTable4().get(i).getBlockCode(),
                                                mStudentObject1.getTable4().get(i).getPanchayatCode());
                                        loginModelDb.save();
                                        System.out.println("LogInDb" + new Gson().toJson(loginModelDb));

                                    }
                                    Table13Db.deleteAll(Table13Db.class);
                                    for (int i = 0; i < mStudentObject1.getTable11().size(); i++) {
                                        Table13Db table13Db = new Table13Db(mStudentObject1.getTable11().get(i).getBlockcode(),
                                                mStudentObject1.getTable11().get(i).getClustercode(), mStudentObject1.getTable11().get(i).getDistrictcode(),
                                                mStudentObject1.getTable11().get(i).getClBal(), mStudentObject1.getTable11().get(i).getVillagecode(),
                                                mStudentObject1.getTable11().get(i).getMeeting_Year(), mStudentObject1.getTable11().get(i).getModificationNo(),
                                                mStudentObject1.getTable11().get(i).getCreatedBy(), mStudentObject1.getTable11().get(i).getCreatedOn(),
                                                mStudentObject1.getTable11().get(i).getFlagClose(), mStudentObject1.getTable11().get(i).getFlagExport(),
                                                mStudentObject1.getTable11().get(i).getFlagOpen(), mStudentObject1.getTable11().get(i).getMeetingDate(),
                                                mStudentObject1.getTable11().get(i).getMeeting_Month(), mStudentObject1.getTable11().get(i).getModifiedBy(),
                                                mStudentObject1.getTable11().get(i).getModifiedOn(), mStudentObject1.getTable11().get(i).getNo(),
                                                mStudentObject1.getTable11().get(i).getPresentSHG(), mStudentObject1.getTable11().get(i).getStateCode(),
                                                mStudentObject1.getTable11().get(i).getTotalMeetingHeld(),mStudentObject1.getTable11().get(i).getTotalNoSHG(),
                                                mStudentObject1.getTable11().get(i).getVOCode());
                                        table13Db.save();
                                        System.out.println("LogInDbVioMember" + new Gson().toJson(table13Db));

                                    }
                                    ArrayList<LoginModelDb> loginModelDbs = new ArrayList<>();
                                    loginModelDbs = (ArrayList<LoginModelDb>) LoginModelDb.listAll(LoginModelDb.class);
                                    /*(ArrayList<LoginModelDb>) LoginModelDb.listAll(LoginModelDb.class))*/
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(intent);
                                } else {
                                    DialogUtil.stopProgressDisplay();
                                    Snackbar.with(LoginActivity.this, null)
                                            .type(Type.ERROR)
                                            .message("Invalid User Details")
                                            .duration(Duration.SHORT)
                                            .fillParent(true)
                                            .textAlign(Align.LEFT)
                                            .show();

                                }
//                            StateModel stateModel = new StateModel();
//                            System.out.print("bhdvhjfdghg" + gson.toJson(stateModel.listAll(StateModel.class)));
//                        String XmlString = response.body().substring(response.body().indexOf("\">")+2);
//                        String result = response.body().replace("<string xmlns=\"http://tempuri.org/\">","");
//                        String result1 = result.replace("</string>","");
//                        String result2 = result1.replace("<?xml version=\"1.0\" encoding=\"utf-8\"?>","");

//                        LoginModel mStudentObject1 = gson.fromJson(result2, LoginModel.class);
//                        Log.d("dkjfdsn","dfhvj"+result2);
//                        Log.d("dkjfdsn","fwshjvshj"+gson.toJson(mStudentObject1));
                                // StateModel stateModel=new StateModel()

                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {
                                DialogUtil.stopProgressDisplay();
                                Log.v("Error in take photo:", t.toString());
                            }
                        });
                    }
                }
            }
        });

    }

    private void showError(EditText editText) {
        Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
        editText.startAnimation(shake);
    }

    private static ApiServices apiServices;

}
