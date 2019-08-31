package com.jslps.vompr.fragment;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chootdev.csnackbar.Align;
import com.chootdev.csnackbar.Duration;
import com.chootdev.csnackbar.Snackbar;
import com.chootdev.csnackbar.Type;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.gson.Gson;
import com.jslps.vompr.Constant;
import com.jslps.vompr.PrefManager;
import com.jslps.vompr.R;
import com.jslps.vompr.adapter.VoMemberListRecyclerAdapter;
import com.jslps.vompr.listener.OnFragmentListItemSelectListener;
import com.jslps.vompr.model.HeaderData;
import com.jslps.vompr.model.StateModelDb;
import com.jslps.vompr.model.Table13Db;
import com.jslps.vompr.model.Table1Db;
import com.jslps.vompr.model.Table2Db;
import com.jslps.vompr.model.Table3Db;
import com.jslps.vompr.model.Table8Db;
import com.jslps.vompr.model.Table9;
import com.jslps.vompr.model.Table9Db;
import com.jslps.vompr.services.ApiServices;
import com.orm.query.Condition;
import com.orm.query.Select;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class HomeFragment extends BaseFragment implements OnFragmentListItemSelectListener {

    private View rootView;
    public static FloatingActionMenu materialDesignFAM;
    FloatingActionButton floatingActionButton1, floatingActionButton2, floatingActionButton3;
    Spinner sppinerDistrict, sppinerBlock, sppinerVillage, sppinerPanchyat;
    long districtId, blockId, stateId;
    String villageId, pancahaytId;
    RecyclerView recyclerViewVoMember;
    VoMemberListRecyclerAdapter voMemberListRecyclerAdapter;
    ArrayList<StateModelDb> arrayListDistict = new ArrayList<>();
    ArrayList<Table1Db> arrayListBlock = new ArrayList<>();
    ArrayList<Table8Db> arrayListVillage = new ArrayList<>();
    ArrayList<Table2Db> arrayListPanchayt = new ArrayList<>();
    PrefManager prefManager;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        mListener.onFragmentUpdate(Constant.setTitle, new HeaderData(false, "Dashboard"));
        arrayListDistict = (ArrayList<StateModelDb>) StateModelDb.listAll(StateModelDb.class);
        arrayListBlock = (ArrayList<Table1Db>) Table1Db.listAll(Table1Db.class);
        arrayListVillage = (ArrayList<Table8Db>) Table8Db.listAll(Table8Db.class);
        arrayListPanchayt = (ArrayList<Table2Db>) Table2Db.listAll(Table2Db.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_home, container, false);
        prefManager = PrefManager.getInstance();
        init();
        arrayListDistict = (ArrayList<StateModelDb>) StateModelDb.listAll(StateModelDb.class);
        arrayListBlock = (ArrayList<Table1Db>) Table1Db.listAll(Table1Db.class);
        arrayListVillage = (ArrayList<Table8Db>) Table8Db.listAll(Table8Db.class);
        arrayListPanchayt = (ArrayList<Table2Db>) Table2Db.listAll(Table2Db.class);
//        sppinerBlock.setEnabled(true);
//        sppinerPanchyat.setEnabled(false);
//        sppinerVillage.setEnabled(false);

        arrayListPanchayt.add(0, new Table2Db("All"));

        System.out.println("vomember Data" + new Gson().toJson(arrayListVillage));
        ArrayAdapter<StateModelDb> dataAdapter = new ArrayAdapter<StateModelDb>(getActivity(), android.R.layout.simple_spinner_item, arrayListDistict);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        sppinerDistrict.setAdapter(dataAdapter);

        ArrayAdapter<Table1Db> dataAdapterBlock = new ArrayAdapter<Table1Db>(getActivity(), android.R.layout.simple_list_item_1, arrayListBlock);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);

        // attaching data adapter to spinner
        sppinerBlock.setAdapter(dataAdapterBlock);

        ArrayAdapter<Table2Db> dataAdapterPanchayat = new ArrayAdapter<Table2Db>(getActivity(), android.R.layout.simple_list_item_1, arrayListPanchayt);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);

        // attaching data adapter to spinner
        sppinerPanchyat.setAdapter(dataAdapterPanchayat);

        sppinerVillage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (sppinerVillage.getItemAtPosition(i).toString().equals("All")) {
                    recyclerViewVoMember.setVisibility(View.VISIBLE);
                    ArrayList<Table8Db> arrayListVoMember = new ArrayList<>();
                    arrayListVoMember = (ArrayList<Table8Db>) Table8Db.listAll(Table8Db.class);
                    ArrayList<Table13Db> arrayListMeetingData = (ArrayList<Table13Db>) Table8Db.listAll(Table13Db.class);
                    voMemberListRecyclerAdapter = new VoMemberListRecyclerAdapter(getActivity(), arrayListVoMember, arrayListMeetingData);
                    voMemberListRecyclerAdapter.setListener(HomeFragment.this);
                    recyclerViewVoMember.setAdapter(voMemberListRecyclerAdapter);
                } else {
                    Table3Db table3 = (Table3Db) adapterView.getItemAtPosition(i);
                    Gson gson = new Gson();
                    villageId = table3.getVillagecode();
                    //Toast.makeText(getActivity(), villageId, Toast.LENGTH_SHORT).show();
                    System.out.print("dataSppinersdhvf" + gson.toJson(table3));
                    ArrayList<Table8Db> arrayListVoMember = (ArrayList<Table8Db>) Select.from(Table8Db.class)
                            .where(Condition.prop("villagecode").eq(villageId)).list();
                    System.out.println("sdbhvusdgfghDGFhjVoMember" + new Gson().toJson(arrayListVoMember));
                    if (arrayListVoMember != null && arrayListVoMember.size() > 0) {
                        ArrayList<Table13Db> arrayListMeetingData = (ArrayList<Table13Db>) Table8Db.listAll(Table13Db.class);
                        recyclerViewVoMember.setVisibility(View.VISIBLE);
                        voMemberListRecyclerAdapter = new VoMemberListRecyclerAdapter(getActivity(), arrayListVoMember, arrayListMeetingData);
                        voMemberListRecyclerAdapter.setListener(HomeFragment.this);
                        recyclerViewVoMember.setAdapter(voMemberListRecyclerAdapter);
                    } else recyclerViewVoMember.setVisibility(View.GONE);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        sppinerBlock.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                Table1Db table1 = (Table1Db) adapterView.getItemAtPosition(i);
                Gson gson = new Gson();
                blockId = Integer.parseInt(table1.getBlockcode());
                //Toast.makeText(getActivity(), blockId + "", Toast.LENGTH_SHORT).show();
                System.out.print("dataSppinersdhvf" + gson.toJson(table1));
                sppinerPanchyat.setEnabled(true);
                prefManager.setPrefBlockName(table1.getBlockname());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        sppinerPanchyat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                Table2Db table2 = (Table2Db) adapterView.getItemAtPosition(i);
                Gson gson = new Gson();
                pancahaytId = table2.getClustercode();
                //Toast.makeText(getActivity(), pancahaytId + "", Toast.LENGTH_SHORT).show();
                System.out.print("dataSppinersdhvf" + gson.toJson(table2));

                if (i == 0) {
                    ArrayList<Table3Db> table3DbArrayList = new ArrayList<>();
                    table3DbArrayList.add(0, new Table3Db("All"));
                    sppinerVillage.setEnabled(false);
                    ArrayAdapter<Table3Db> dataAdapterVillage = new ArrayAdapter<Table3Db>(getActivity(), android.R.layout.simple_spinner_item, table3DbArrayList);

                    // Drop down layout style - list view with radio button
                    dataAdapterVillage.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    // attaching data adapter to spinner
                    sppinerVillage.setAdapter(dataAdapterVillage);

                } else {
                    ArrayList<Table3Db> arrayListVillage1 = (ArrayList<Table3Db>) Select.from(Table3Db.class)
                            .where(Condition.prop("blockcode").eq(blockId),
                                    Condition.prop("statecode").eq(stateId),
                                    Condition.prop("clustercode").eq(pancahaytId)).list();
                    System.out.println("sdbhvusdgfghDGFhj" + new Gson().toJson(arrayListVillage1));
                    //arrayListVillage1.add(0,new Table3Db("All"));
                    ArrayAdapter<Table3Db> dataAdapterVillage = new ArrayAdapter<Table3Db>(getActivity(), android.R.layout.simple_spinner_item, arrayListVillage1);

                    // Drop down layout style - list view with radio button
                    dataAdapterVillage.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    // attaching data adapter to spinner
                    sppinerVillage.setAdapter(dataAdapterVillage);
                    sppinerVillage.setEnabled(true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        sppinerDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                StateModelDb stateModel = (StateModelDb) adapterView.getItemAtPosition(i);
                Gson gson = new Gson();
                districtId = Integer.parseInt(stateModel.getDistrictcode());
                stateId = Integer.parseInt(stateModel.getStatecode());
//                Toast.makeText(getActivity(), districtId + "", Toast.LENGTH_SHORT).show();
                System.out.print("dataSppinersdhvf" + gson.toJson(stateModel));
                sppinerBlock.setEnabled(true);
                prefManager.setDistictNme(stateModel.getDistrictname());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final ArrayList<Table9Db> arrayListVillage1 = (ArrayList<Table9Db>) Select.from(Table9Db.class)
                        .where(Condition.prop("isupdated").eq("false")).list();
                String data = "VOMPRTypeTable :" + new Gson().toJson(arrayListVillage1);
                System.out.println("Jsva Data" + data);
                System.out.println("sdbhvusdgfghDGFhjwerewqf" + new Gson().toJson(arrayListVillage1));
                if (arrayListVillage1 != null && arrayListVillage1.size() > 0) {
                    final ArrayList<Table9> table9ArrayList = new ArrayList<>();
                    for (int i = 0; i < arrayListVillage1.size(); i++) {
                        Table9 table9 = new Table9();
                        table9.setMemberID(arrayListVillage1.get(i).getMemberid());
                        table9.setDateofJoin(arrayListVillage1.get(i).getDateofjoin() + " 00:00:00");
                        table9.setVOCode(arrayListVillage1.get(i).getVoCode());
                        table9.setFather_hasbandName(arrayListVillage1.get(i).getFatherhasbandname());
                        table9.setPhoneNo(arrayListVillage1.get(i).getPhoneno());
                        table9.setGrop_of_officerPostName(arrayListVillage1.get(i).getGropofofficerpostname());
                        table9.setGropMemberName(arrayListVillage1.get(i).getGropmembername());
                        table9.setGroupCode(arrayListVillage1.get(i).getGroupcode());
                        table9.setStatus(arrayListVillage1.get(i).getStatus());
                        table9.setGroup_Name(arrayListVillage1.get(i).getGroupname());
                        table9ArrayList.add(table9);
                    }
                    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                    OkHttpClient.Builder builder = new OkHttpClient.Builder();
                    //comment in live build and uncomment in uat
                    builder.interceptors().add(interceptor);

                    builder.connectTimeout(120, TimeUnit.SECONDS);
                    builder.readTimeout(120, TimeUnit.SECONDS);

                    OkHttpClient client = builder.build();
                    Retrofit retrofit = new Retrofit.Builder().baseUrl(Constant.API_BASE_URL).addConverterFactory(ScalarsConverterFactory.create()).client(client).build();
                    ApiServices apiServices = retrofit.create(ApiServices.class);
                    Call<String> changePhotoResponseModelCall = apiServices.saveMemberData("\"VOMPRTypeTable\" :" + new Gson().toJson(table9ArrayList));
                    changePhotoResponseModelCall.enqueue(new Callback<String>() {

                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            System.out.println("Response  data" + response.body());
                            String fullResponse = response.body();
                            String XmlString = fullResponse.substring(fullResponse.indexOf("\">") + 2);
                            String result = XmlString.replaceAll("</string>", "");
                            JSONObject jsonObj = null;
                            try {
                                jsonObj = new JSONObject(result.toString());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            try {
                                JSONArray categoryObject = jsonObj.getJSONArray("Table");
                                JSONObject jsonObject = categoryObject.getJSONObject(0);
                                String Result = jsonObject.getString("RetValue");
                                if (Result.equalsIgnoreCase("1")) {
                                    for (int i = 0; i < arrayListVillage1.size(); i++) {
                                        arrayListVillage1.get(i).setIsupdated("true");
                                        arrayListVillage1.get(i).save();
                                        System.out.println(new Gson().toJson(arrayListVillage1));
                                        Snackbar.with(getActivity(), null)
                                                .type(Type.SUCCESS)
                                                .message("Data  Uploaded Successfully")
                                                .duration(Duration.SHORT)
                                                .fillParent(true)
                                                .textAlign(Align.LEFT)
                                                .show();
                                    }
                                } else {
                                    Snackbar.with(getActivity(), null)
                                            .type(Type.ERROR)
                                            .message("Data not Uploaded")
                                            .duration(Duration.SHORT)
                                            .fillParent(true)
                                            .textAlign(Align.LEFT)
                                            .show();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            System.out.println("Response  failuree" + t.getLocalizedMessage());
                        }
                    });
                    System.out.println("sdbhvusdgfghDGFhjwerewqfvsfFDfsfwdsZ" + "\"VOMPRTypeTable\" :" + new Gson().toJson(table9ArrayList));
                }
            }
        });

        return rootView;
    }

    private void init() {
        materialDesignFAM = rootView.findViewById(R.id.material_design_android_floating_action_menu);
        floatingActionButton2 = rootView.findViewById(R.id.material_design_floating_action_menu_item5);
        sppinerDistrict = rootView.findViewById(R.id.sppinerDistrict);
        sppinerBlock = rootView.findViewById(R.id.sppinerBlock);
        sppinerVillage = rootView.findViewById(R.id.sppinerVillage);
        sppinerPanchyat = rootView.findViewById(R.id.sppinerPanchyat);
        recyclerViewVoMember = rootView.findViewById(R.id.recylerviewVoMember);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerViewVoMember.setHasFixedSize(true);
        recyclerViewVoMember.setLayoutManager(mLayoutManager);
        recyclerViewVoMember.setItemAnimator(new DefaultItemAnimator());

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Add your menu entries here
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.search_menu, menu);
    }


    @Override
    public void onListItemSelected(int itemId, Object data) {
        switch (itemId) {
            case R.id.btngenrateMeeting:
                Table8Db table8Db = (Table8Db) data;
                ArrayList<Table13Db> arrayListVoMember = (ArrayList<Table13Db>) Select.from(Table13Db.class)
                        .where(Condition.prop("vocode").eq(table8Db.getVocode())).list();
                System.out.println("meetingDetails" + new Gson().toJson(arrayListVoMember));
                /* ArrayList<Table13Db> arrayListVillage = (ArrayList<Table13Db>) Table8Db.listAll(Table13Db.class);*/
                //System.out.println("meetingDetailsefQED" + new Gson().toJson(arrayListVoMember.get(arrayListVoMember.size() - 1)));
                if (arrayListVoMember != null && arrayListVoMember.size() > 0) {
                    showCustomDialog(arrayListVoMember.get(arrayListVoMember.size() - 1), table8Db.getFedrationname(), table8Db.getFormationdate(), "true", table8Db.getMeetingfrequency(), table8Db.getVocode());
                } else {
                    showCustomDialog(null, table8Db.getFedrationname(), table8Db.getFormationdate(), "false", table8Db.getMeetingfrequency(), table8Db.getVocode());
                }
                break;
            case R.id.linearLayout:
                Table8Db table8Db1 = (Table8Db) data;
                mListener.onFragmentInteraction(Constant.VOFRAGMENT, table8Db1);
                break;

        }

    }

    @Override
    public void onListItemLongClicked(int itemId, Object data) {

    }

    private void showCustomDialog(Table13Db table8Db, String fedrationname, String fomationDate, String aTrue, String meetingfrequency, String vocode) {
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
        final Dialog dialog = new Dialog(getActivity(), android.R.style.Theme_DeviceDefault_Dialog_Alert
        );
        // Include dialog.xml file
        dialog.setContentView(R.layout.layout_dialog);
        TextView lastmeetingdate = dialog.findViewById(R.id.lastmeetingdate);
        final EditText thismeetingheld = dialog.findViewById(R.id.thismeetingheld);
        TextView lastmeetingsno = dialog.findViewById(R.id.lastmeetingsno);
        EditText thismeetingno = dialog.findViewById(R.id.thismeetingno);
        TextView voName = dialog.findViewById(R.id.voName);
        final TextView meetingDate = dialog.findViewById(R.id.meetingDate);
        ImageView closeButton = dialog.findViewById(R.id.closeButton);
        final LinearLayout thismeetingheldLayout = dialog.findViewById(R.id.thismeetingheldLayout);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });

        if (table8Db != null) {
            if (aTrue.equals("true") || !TextUtils.isEmpty(table8Db.getNo())) {
                thismeetingno.setEnabled(false);
                thismeetingheldLayout.setVisibility(View.GONE);
                try {
                    String s = table8Db.getMeetingdate();
                    String newS = s.replace("T", " ");
                    lastmeetingdate.setText(convertStringToData(newS));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                lastmeetingsno.setText(table8Db.getNo());
                thismeetingno.setText(Integer.parseInt(table8Db.getNo()) + 1 + "");

                voName.setText(fedrationname + "(" + table8Db.getVocode() + ")");
                voName.setEnabled(false);
                voName.setAlpha(0.5f);
                String s = table8Db.getMeetingdate();
                String newS = s.replace("T", " ");
                String dt = newS;  // Start date
                final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Calendar c = Calendar.getInstance();
                try {
                    c.setTime(sdf.parse(dt));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (meetingfrequency.equals("FortNight")) {
                    c.add(Calendar.DATE, 15);
                } else if (meetingfrequency.equals("Monthly")) {
                    c.add(Calendar.DATE, 30);
                } else
                    c.add(Calendar.DATE, 7);
                // number of days to add
                dt = sdf.format(c.getTime());
        /*SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(newS);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long timestamp = date.getTime();*/
                System.out.println("sdqewrtgh" + dt);
                meetingDate.setText(dt);

                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date date = null;
                try {
                    date = format.parse(dt);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                final long timestamp = date.getTime();
                SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
                Date date1 = null;
                try {
                    date1 = format1.parse(newS);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                final long timestamp1 = date1.getTime();
                System.out.println("Today is1 " + timestamp1);
                meetingDate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Calendar c = Calendar.getInstance(Locale.ENGLISH);
                        Integer ALyear = c.get(Calendar.YEAR);
                        Integer ALmonthOfYear = c.get(Calendar.MONTH);
                        Integer ALdayOfMonth = c.get(Calendar.DAY_OF_MONTH);

                        DatePickerDialog dpd = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                String dateSelected = year + "-" + (getProperFormat(dayOfMonth) + "-" + getProperFormat(monthOfYear + 1));
                                meetingDate.setText(dateSelected);
                            }
                        }, ALyear, ALmonthOfYear, ALdayOfMonth);


                        dpd.getDatePicker().setMinDate(timestamp1);
                        //c.add(Calendar.YEAR, 5);
                        dpd.getDatePicker().setMaxDate(timestamp);
                        dpd.show();
                    }
                });
            } else {
                voName.setText(fedrationname + "(" + vocode + ")");
                voName.setEnabled(false);
                voName.setAlpha(0.5f);
                thismeetingno.setText("1");
                thismeetingno.setEnabled(true);
                thismeetingheldLayout.setVisibility(View.GONE);
                thismeetingno.addTextChangedListener(new TextWatcher() {

                    public void onTextChanged(CharSequence s, int start, int before,
                                              int count) {
                        if (s.toString().equalsIgnoreCase("0")) {
                            thismeetingheldLayout.setVisibility(View.VISIBLE);
                        } else thismeetingheldLayout.setVisibility(View.GONE);
                    }


                    public void beforeTextChanged(CharSequence s, int start, int count,
                                                  int after) {

                    }

                    public void afterTextChanged(Editable s) {

                    }
                });
                lastmeetingdate.setVisibility(View.VISIBLE);
                meetingDate.setText(updatedDateShowFormat1(System.currentTimeMillis()));
                SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
                Date date1 = null;
                try {
                    date1 = format1.parse(convertStringToData(fomationDate.replace("T", " ")));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                final long timestamp1 = date1.getTime();

                meetingDate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Calendar c = Calendar.getInstance(Locale.ENGLISH);
                        Integer ALyear = c.get(Calendar.YEAR);
                        Integer ALmonthOfYear = c.get(Calendar.MONTH);
                        Integer ALdayOfMonth = c.get(Calendar.DAY_OF_MONTH);

                        DatePickerDialog dpd = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                String dateSelected = year + "-" + (getProperFormat(dayOfMonth) + "-" + getProperFormat(monthOfYear + 1));
                                meetingDate.setText(dateSelected);
                            }
                        }, ALyear, ALmonthOfYear, ALdayOfMonth);


                        dpd.getDatePicker().setMinDate(timestamp1);
                        //c.add(Calendar.YEAR, 5);
                        dpd.getDatePicker().setMaxDate(System.currentTimeMillis());
                        dpd.show();
                    }
                });

            }
        } else {
            voName.setText(fedrationname + "(" + vocode + ")");
            voName.setEnabled(false);
            voName.setAlpha(0.5f);
            thismeetingno.setText("1");
            thismeetingno.setEnabled(true);
            thismeetingheldLayout.setVisibility(View.GONE);
            thismeetingno.addTextChangedListener(new TextWatcher() {

                public void onTextChanged(CharSequence s, int start, int before,
                                          int count) {
                    if (s.toString().equalsIgnoreCase("0")) {
                        thismeetingheldLayout.setVisibility(View.VISIBLE);
                    } else thismeetingheldLayout.setVisibility(View.GONE);
                }


                public void beforeTextChanged(CharSequence s, int start, int count,
                                              int after) {

                }

                public void afterTextChanged(Editable s) {

                }
            });
            lastmeetingdate.setVisibility(View.VISIBLE);
            meetingDate.setText(updatedDateShowFormat1(System.currentTimeMillis()));
            SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
            Date date1 = null;
            try {
                date1 = format1.parse(convertStringToData(fomationDate.replace("T", " ")));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            final long timestamp1 = date1.getTime();

            meetingDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Calendar c = Calendar.getInstance(Locale.ENGLISH);
                    Integer ALyear = c.get(Calendar.YEAR);
                    Integer ALmonthOfYear = c.get(Calendar.MONTH);
                    Integer ALdayOfMonth = c.get(Calendar.DAY_OF_MONTH);

                    DatePickerDialog dpd = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            String dateSelected = year + "-" + (getProperFormat(dayOfMonth) + "-" + getProperFormat(monthOfYear + 1));
                            meetingDate.setText(dateSelected);
                        }
                    }, ALyear, ALmonthOfYear, ALdayOfMonth);


                    dpd.getDatePicker().setMinDate(timestamp1);
                    //c.add(Calendar.YEAR, 5);
                    dpd.getDatePicker().setMaxDate(System.currentTimeMillis());
                    dpd.show();
                }
            });

        }
        //Now we need an AlertDialog.Builder object


        //setting the view of the builder to our custom view that we already inflated
        dialog.show();

    }

    private String getProperFormat(int hhORmm) {
        String temp = hhORmm + "";
        if (temp.length() == 1) {
            temp = "0" + temp;
        } else {

        }
        return temp;
    }

    public static String convertStringToData(String stringData)
            throws ParseException {


        SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//yyyy-MM-dd'T'HH:mm:ss
        Date data = output.parse(stringData);
        String formattedTime = sdf.format(data);
        return formattedTime;
    }

    public static String updatedDateShowFormat1(long time) {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(time);
        String dateFormat1 = android.text.format.DateFormat.format("yyyy-MM-dd", cal).toString();
        return dateFormat1;
    }
}

