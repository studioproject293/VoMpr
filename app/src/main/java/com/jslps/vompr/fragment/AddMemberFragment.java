package com.jslps.vompr.fragment;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jslps.vompr.Constant;
import com.jslps.vompr.PrefManager;
import com.jslps.vompr.R;
import com.jslps.vompr.model.AddMemberModel;
import com.jslps.vompr.model.HeaderData;
import com.jslps.vompr.model.StateModelDb;
import com.jslps.vompr.model.Table10Db;
import com.jslps.vompr.model.Table11Db;
import com.jslps.vompr.model.Table2Db;
import com.jslps.vompr.model.Table3Db;
import com.jslps.vompr.model.Table9Db;
import com.orm.query.Condition;
import com.orm.query.Select;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public class AddMemberFragment extends BaseFragment {

    private TextView dateOfJoining;
    private EditText fatherHusbandName, phoneNumber;
    private Button addMember;
    Spinner spinnerMemberStatus, groupVoPost, groupName, groupMemberName;
    String[] status = {"Active", "Inactive"};
    String[] voPost = {"Member", "Chairman", "Vice Chairman", "Treasurer", "Secretary", "Vice Secretary"};
    PrefManager prefManager;
    private String groupOfficialpostname, groupNameData, statusData, groupCode, groupMemberNameData;

    public AddMemberFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public static AddMemberFragment newInstance() {
        return new AddMemberFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.add_member_fragment, container, false);
        prefManager = PrefManager.getInstance();
        setId(rootView);

        dateOfJoining.setText(entryDate);
        ArrayList<Table11Db> table11DbArrayList = new ArrayList<>();
        ArrayList<Table10Db> table10DbArrayList = new ArrayList<>();
        table11DbArrayList.add(0, new Table11Db("Select"));
        table10DbArrayList.add(0, new Table10Db("Select"));
        table10DbArrayList = (ArrayList<Table10Db>) Table10Db.listAll(Table10Db.class);
        ArrayAdapter<Table10Db> dataAdapter = new ArrayAdapter<Table10Db>(getActivity(), android.R.layout.simple_spinner_item, table10DbArrayList);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        groupName.setAdapter(dataAdapter);
        groupMemberName.setEnabled(false);
        groupName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Table10Db table10Db = (Table10Db) adapterView.getItemAtPosition(i);
                groupCode = table10Db.getGroupcode();
                groupNameData = table10Db.getGroupname();
                ArrayList<Table11Db> table11DbArrayList1 = (ArrayList<Table11Db>) Select.from(Table11Db.class)
                        .where(Condition.prop("groupcode").eq(groupCode)).list();
                System.out.println("sdbhvusdgfghDGFhj" + new Gson().toJson(table11DbArrayList1));
                //arrayListVillage1.add(0,new Table3Db("All"));
                ArrayAdapter<Table11Db> dataAdapterVillage = new ArrayAdapter<Table11Db>(getActivity(), android.R.layout.simple_spinner_item, table11DbArrayList1);

                // Drop down layout style - list view with radio button
                dataAdapterVillage.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                // attaching data adapter to spinner
                groupMemberName.setAdapter(dataAdapterVillage);
                groupMemberName.setEnabled(true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        groupMemberName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Table11Db table11Db = (Table11Db) adapterView.getItemAtPosition(i);
                groupMemberNameData = table11Db.getMembername();
                ArrayList<Table11Db> table11DbArrayList1 = (ArrayList<Table11Db>) Select.from(Table11Db.class)
                        .where(Condition.prop("groupcode").eq(groupCode)).list();
                System.out.println("sdbhvusdgfghDGFhj" + new Gson().toJson(table11DbArrayList1));
                //arrayListVillage1.add(0,new Table3Db("All"));
                ArrayAdapter<Table11Db> dataAdapterVillage = new ArrayAdapter<Table11Db>(getActivity(), android.R.layout.simple_spinner_item, table11DbArrayList1);

                // Drop down layout style - list view with radio button
                dataAdapterVillage.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                // attaching data adapter to spinner
                groupMemberName.setAdapter(dataAdapterVillage);
                groupMemberName.setEnabled(true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinnerMemberStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0)
                    statusData = "1";
                else statusData = "0";
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        groupVoPost.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) groupOfficialpostname = "6";
                else if (i == 1) statusData = "1";
                else if (i == 2) statusData = "2";
                else if (i == 3) statusData = "3";
                else if (i == 4) statusData = "4";
                else if (i == 5) statusData = "5";
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        dateOfJoining.setOnClickListener(new View.OnClickListener() {
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
                        dateOfJoining.setText(dateSelected);
                    }
                }, ALyear, ALmonthOfYear, ALdayOfMonth);


                dpd.getDatePicker().setMinDate(System.currentTimeMillis() /*+ 24 * 60 * 60 * 1000*/);
                c.add(Calendar.YEAR, 5);
                dpd.getDatePicker().setMaxDate(c.getTimeInMillis());
                dpd.show();
            }
        });
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, status);
        spinnerMemberStatus.setAdapter(adapter);
        ArrayAdapter<String> adapterMember = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, voPost);
        groupVoPost.setAdapter(adapterMember);
        addMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fatherHusbandName.getText().toString().trim().isEmpty()) {
                    fatherHusbandName.setError("Please enter father/husband name");
                    fatherHusbandName.requestFocus();
                    showError(fatherHusbandName);
                } else if (phoneNumber.getText().toString().trim().isEmpty()) {
                    phoneNumber.setError("Please enter phone number");
                    phoneNumber.requestFocus();
                    showError(phoneNumber);
                } else if (dateOfJoining.getText().toString().trim().isEmpty()) {
                    dateOfJoining.setError("Please enter date of joining");
                    dateOfJoining.requestFocus();
                    showErrorTextview(dateOfJoining);
                } else {
                    String id = UUID.randomUUID().toString();
                    AddMemberModel addMemberModelrec = AddMemberModel.findById(AddMemberModel.class, 1);
                    System.out.println("sdhvdhfjdshv" + new Gson().toJson(addMemberModelrec));
                    if (addMemberModelrec != null) {
                       /* if (!addMemberModelrec.equals(id)) {
                            if (!addMemberModelrec.getStatus().equalsIgnoreCase("1")) {
                                AddMemberModel addMemberModel = new AddMemberModel(Integer.parseInt(prefManager.getPREF_VOCode()), Integer.parseInt(groupCode), Integer.parseInt(prefManager.getPREF_memberID()),
                                        groupNameData, groupMemberNameData, fatherHusbandName.getText().toString(), groupOfficialpostname, phoneNumber.getText().toString(),
                                        dateOfJoining.getText().toString(), statusData, id);
                                addMemberModel.save();
                                Table9Db table9Db = new Table9Db(prefManager.getPREF_VOCode(), statusData, phoneNumber.getText().toString(), groupOfficialpostname,
                                        String.valueOf(groupCode), prefManager.getPREF_memberID(), dateOfJoining.getText().toString(), fatherHusbandName.getText().toString(),
                                        groupMemberNameData, groupNameData, id, "false");
                                table9Db.save();
                                Toast.makeText(getActivity(), "Data Save Successfully", Toast.LENGTH_SHORT).show();
                                getFragmentManager().popBackStack();
                            } else
                                Toast.makeText(getActivity(), "Chairmen Should be One", Toast.LENGTH_SHORT).show();
                            if (!addMemberModelrec.getStatus().equalsIgnoreCase("2")) {
                                AddMemberModel addMemberModel = new AddMemberModel(Integer.parseInt(prefManager.getPREF_VOCode()), Integer.parseInt(groupCode), Integer.parseInt(prefManager.getPREF_memberID()),
                                        groupNameData, groupMemberNameData, fatherHusbandName.getText().toString(), groupOfficialpostname, phoneNumber.getText().toString(),
                                        dateOfJoining.getText().toString(), statusData, id);
                                addMemberModel.save();
                                Table9Db table9Db = new Table9Db(prefManager.getPREF_VOCode(), statusData, phoneNumber.getText().toString(), groupOfficialpostname,
                                        String.valueOf(groupCode), prefManager.getPREF_memberID(), dateOfJoining.getText().toString(), fatherHusbandName.getText().toString(),
                                        groupMemberNameData, groupNameData, id, "false");
                                table9Db.save();
                                Toast.makeText(getActivity(), "Data Save Successfully", Toast.LENGTH_SHORT).show();
                                getFragmentManager().popBackStack();
                            } else
                                Toast.makeText(getActivity(), "Vice Chairman Should be One", Toast.LENGTH_SHORT).show();
                            if (!addMemberModelrec.getStatus().equalsIgnoreCase("3")) {
                                AddMemberModel addMemberModel = new AddMemberModel(Integer.parseInt(prefManager.getPREF_VOCode()), Integer.parseInt(groupCode), Integer.parseInt(prefManager.getPREF_memberID()),
                                        groupNameData, groupMemberNameData, fatherHusbandName.getText().toString(), groupOfficialpostname, phoneNumber.getText().toString(),
                                        dateOfJoining.getText().toString(), statusData, id);
                                addMemberModel.save();
                                Table9Db table9Db = new Table9Db(prefManager.getPREF_VOCode(), statusData, phoneNumber.getText().toString(), groupOfficialpostname,
                                        String.valueOf(groupCode), prefManager.getPREF_memberID(), dateOfJoining.getText().toString(), fatherHusbandName.getText().toString(),
                                        groupMemberNameData, groupNameData, id, "false");
                                table9Db.save();
                                Toast.makeText(getActivity(), "Data Save Successfully", Toast.LENGTH_SHORT).show();
                                getFragmentManager().popBackStack();
                            } else
                                Toast.makeText(getActivity(), "Treasurer Should be One", Toast.LENGTH_SHORT).show();
                            if (!addMemberModelrec.getStatus().equalsIgnoreCase("4")) {
                                AddMemberModel addMemberModel = new AddMemberModel(Integer.parseInt(prefManager.getPREF_VOCode()), Integer.parseInt(groupCode), Integer.parseInt(prefManager.getPREF_memberID()),
                                        groupNameData, groupMemberNameData, fatherHusbandName.getText().toString(), groupOfficialpostname, phoneNumber.getText().toString(),
                                        dateOfJoining.getText().toString(), statusData, id);
                                addMemberModel.save();
                                Table9Db table9Db = new Table9Db(prefManager.getPREF_VOCode(), statusData, phoneNumber.getText().toString(), groupOfficialpostname,
                                        String.valueOf(groupCode), prefManager.getPREF_memberID(), dateOfJoining.getText().toString(), fatherHusbandName.getText().toString(),
                                        groupMemberNameData, groupNameData, id, "false");
                                table9Db.save();
                                Toast.makeText(getActivity(), "Data Save Successfully", Toast.LENGTH_SHORT).show();
                                getFragmentManager().popBackStack();
                            } else
                                Toast.makeText(getActivity(), "Secretary Should be One", Toast.LENGTH_SHORT).show();
                            if (!addMemberModelrec.getStatus().equalsIgnoreCase("5")) {
                                AddMemberModel addMemberModel = new AddMemberModel(Integer.parseInt(prefManager.getPREF_VOCode()), Integer.parseInt(groupCode), Integer.parseInt(prefManager.getPREF_memberID()),
                                        groupNameData, groupMemberNameData, fatherHusbandName.getText().toString(), groupOfficialpostname, phoneNumber.getText().toString(),
                                        dateOfJoining.getText().toString(), statusData, id);
                                addMemberModel.save();
                                Table9Db table9Db = new Table9Db(prefManager.getPREF_VOCode(), statusData, phoneNumber.getText().toString(), groupOfficialpostname,
                                        String.valueOf(groupCode), prefManager.getPREF_memberID(), dateOfJoining.getText().toString(), fatherHusbandName.getText().toString(),
                                        groupMemberNameData, groupNameData, id, "false");
                                table9Db.save();
                                Toast.makeText(getActivity(), "Data Save Successfully", Toast.LENGTH_SHORT).show();
                                getFragmentManager().popBackStack();
                            } else
                                Toast.makeText(getActivity(), "Vice Secretary Should be One", Toast.LENGTH_SHORT).show();


                        } else
                            Toast.makeText(getActivity(), "Please enter different data ", Toast.LENGTH_SHORT).show();*/
                    } else {
                        ArrayList<Table9Db> table9DbArrayList = (ArrayList<Table9Db>) Table9Db.listAll(Table9Db.class);
                        for (int i = 0; i < table9DbArrayList.size(); i++) {
                           /* if (!table9DbArrayList.getStatus().equalsIgnoreCase("1")) {
                                AddMemberModel addMemberModel = new AddMemberModel(Integer.parseInt(prefManager.getPREF_VOCode()), Integer.parseInt(groupCode), Integer.parseInt(prefManager.getPREF_memberID()),
                                        groupNameData, groupMemberNameData, fatherHusbandName.getText().toString(), groupOfficialpostname, phoneNumber.getText().toString(),
                                        dateOfJoining.getText().toString(), statusData, id);
                                addMemberModel.save();
                                Table9Db table9Db = new Table9Db(prefManager.getPREF_VOCode(), statusData, phoneNumber.getText().toString(), groupOfficialpostname,
                                        String.valueOf(groupCode), prefManager.getPREF_memberID(), dateOfJoining.getText().toString(), fatherHusbandName.getText().toString(),
                                        groupMemberNameData, groupNameData, id, "false");
                                table9Db.save();
                                Toast.makeText(getActivity(), "Data Save Successfully", Toast.LENGTH_SHORT).show();
                                getFragmentManager().popBackStack();
                            } else
                                Toast.makeText(getActivity(), "Chairmen Should be One", Toast.LENGTH_SHORT).show();
                            if (!addMemberModelrec.getStatus().equalsIgnoreCase("2")) {
                                AddMemberModel addMemberModel = new AddMemberModel(Integer.parseInt(prefManager.getPREF_VOCode()), Integer.parseInt(groupCode), Integer.parseInt(prefManager.getPREF_memberID()),
                                        groupNameData, groupMemberNameData, fatherHusbandName.getText().toString(), groupOfficialpostname, phoneNumber.getText().toString(),
                                        dateOfJoining.getText().toString(), statusData, id);
                                addMemberModel.save();
                                Table9Db table9Db = new Table9Db(prefManager.getPREF_VOCode(), statusData, phoneNumber.getText().toString(), groupOfficialpostname,
                                        String.valueOf(groupCode), prefManager.getPREF_memberID(), dateOfJoining.getText().toString(), fatherHusbandName.getText().toString(),
                                        groupMemberNameData, groupNameData, id, "false");
                                table9Db.save();
                                Toast.makeText(getActivity(), "Data Save Successfully", Toast.LENGTH_SHORT).show();
                                getFragmentManager().popBackStack();
                            } else
                                Toast.makeText(getActivity(), "Vice Chairman Should be One", Toast.LENGTH_SHORT).show();
                            if (!addMemberModelrec.getStatus().equalsIgnoreCase("3")) {
                                AddMemberModel addMemberModel = new AddMemberModel(Integer.parseInt(prefManager.getPREF_VOCode()), Integer.parseInt(groupCode), Integer.parseInt(prefManager.getPREF_memberID()),
                                        groupNameData, groupMemberNameData, fatherHusbandName.getText().toString(), groupOfficialpostname, phoneNumber.getText().toString(),
                                        dateOfJoining.getText().toString(), statusData, id);
                                addMemberModel.save();
                                Table9Db table9Db = new Table9Db(prefManager.getPREF_VOCode(), statusData, phoneNumber.getText().toString(), groupOfficialpostname,
                                        String.valueOf(groupCode), prefManager.getPREF_memberID(), dateOfJoining.getText().toString(), fatherHusbandName.getText().toString(),
                                        groupMemberNameData, groupNameData, id, "false");
                                table9Db.save();
                                Toast.makeText(getActivity(), "Data Save Successfully", Toast.LENGTH_SHORT).show();
                                getFragmentManager().popBackStack();
                            } else
                                Toast.makeText(getActivity(), "Treasurer Should be One", Toast.LENGTH_SHORT).show();
                            if (!addMemberModelrec.getStatus().equalsIgnoreCase("4")) {
                                AddMemberModel addMemberModel = new AddMemberModel(Integer.parseInt(prefManager.getPREF_VOCode()), Integer.parseInt(groupCode), Integer.parseInt(prefManager.getPREF_memberID()),
                                        groupNameData, groupMemberNameData, fatherHusbandName.getText().toString(), groupOfficialpostname, phoneNumber.getText().toString(),
                                        dateOfJoining.getText().toString(), statusData, id);
                                addMemberModel.save();
                                Table9Db table9Db = new Table9Db(prefManager.getPREF_VOCode(), statusData, phoneNumber.getText().toString(), groupOfficialpostname,
                                        String.valueOf(groupCode), prefManager.getPREF_memberID(), dateOfJoining.getText().toString(), fatherHusbandName.getText().toString(),
                                        groupMemberNameData, groupNameData, id, "false");
                                table9Db.save();
                                Toast.makeText(getActivity(), "Data Save Successfully", Toast.LENGTH_SHORT).show();
                                getFragmentManager().popBackStack();
                            } else
                                Toast.makeText(getActivity(), "Secretary Should be One", Toast.LENGTH_SHORT).show();
                            if (!addMemberModelrec.getStatus().equalsIgnoreCase("5")) {
                                AddMemberModel addMemberModel = new AddMemberModel(Integer.parseInt(prefManager.getPREF_VOCode()), Integer.parseInt(groupCode), Integer.parseInt(prefManager.getPREF_memberID()),
                                        groupNameData, groupMemberNameData, fatherHusbandName.getText().toString(), groupOfficialpostname, phoneNumber.getText().toString(),
                                        dateOfJoining.getText().toString(), statusData, id);
                                addMemberModel.save();
                                Table9Db table9Db = new Table9Db(prefManager.getPREF_VOCode(), statusData, phoneNumber.getText().toString(), groupOfficialpostname,
                                        String.valueOf(groupCode), prefManager.getPREF_memberID(), dateOfJoining.getText().toString(), fatherHusbandName.getText().toString(),
                                        groupMemberNameData, groupNameData, id, "false");
                                table9Db.save();
                                Toast.makeText(getActivity(), "Data Save Successfully", Toast.LENGTH_SHORT).show();
                                getFragmentManager().popBackStack();
                            } else
                                Toast.makeText(getActivity(), "Vice Secretary Should be One", Toast.LENGTH_SHORT).show();*/
                            if (!table9DbArrayList.get(i).getStatus().equals(statusData)) {
                                statusCondition = true;
                                /*if (statusData.equals("6")){
                                    if (!table9DbArrayList.get(i).getGroupcode().equals(groupCode)&& !table9DbArrayList.get(i).getMemberid().equals(prefManager.getPREF_memberID())){
                                        AddMemberModel addMemberModel = new AddMemberModel(Integer.parseInt(prefManager.getPREF_VOCode()), Integer.parseInt(groupCode), Integer.parseInt(prefManager.getPREF_memberID()),
                                                groupNameData, groupMemberNameData, fatherHusbandName.getText().toString(), groupOfficialpostname, phoneNumber.getText().toString(),
                                                dateOfJoining.getText().toString(), statusData, id);
                                        addMemberModel.save();
                                        Table9Db table9Db = new Table9Db(prefManager.getPREF_VOCode(), statusData, phoneNumber.getText().toString(), groupOfficialpostname,
                                                String.valueOf(groupCode), prefManager.getPREF_memberID(), dateOfJoining.getText().toString(), fatherHusbandName.getText().toString(),
                                                groupMemberNameData, groupNameData, id, "false");
                                        table9Db.save();
                                        Toast.makeText(getActivity(), "Data Save Successfully", Toast.LENGTH_SHORT).show();
                                        getFragmentManager().popBackStack();
                                    }else {
                                        Toast.makeText(getActivity(), "Member already Exist", Toast.LENGTH_SHORT).show();
                                        return;
                                    }*/

                                // }

                            } else {
                                switch (Integer.parseInt(statusData)) {
                                    case 1:
                                        Toast.makeText(getActivity(), "Chairmen Should be One", Toast.LENGTH_SHORT).show();
                                        break;
                                    case 2:
                                        Toast.makeText(getActivity(), "Vice Chairmen Should be One", Toast.LENGTH_SHORT).show();
                                        break;
                                    case 3:
                                        Toast.makeText(getActivity(), "Treasurer Should be One", Toast.LENGTH_SHORT).show();
                                        break;
                                    case 4:
                                        Toast.makeText(getActivity(), "Secretary Should be One", Toast.LENGTH_SHORT).show();
                                        break;
                                    case 5:
                                        Toast.makeText(getActivity(), "Vice Secretary Should be One", Toast.LENGTH_SHORT).show();
                                        break;


                                }
                            }
                        }
                        if (statusCondition) {
                            AddMemberModel addMemberModel = new AddMemberModel(Integer.parseInt(prefManager.getPREF_VOCode()), Integer.parseInt(groupCode), Integer.parseInt(prefManager.getPREF_memberID()),
                                    groupNameData, groupMemberNameData, fatherHusbandName.getText().toString(), groupOfficialpostname, phoneNumber.getText().toString(),
                                    dateOfJoining.getText().toString(), statusData, id);
                            addMemberModel.save();
                            Table9Db table9Db = new Table9Db(prefManager.getPREF_VOCode(), statusData, phoneNumber.getText().toString(), groupOfficialpostname,
                                    String.valueOf(groupCode), prefManager.getPREF_memberID(), dateOfJoining.getText().toString(), fatherHusbandName.getText().toString(),
                                    groupMemberNameData, groupNameData, id, "false");
                            table9Db.save();
                            Toast.makeText(getActivity(), "Data Save Successfully", Toast.LENGTH_SHORT).show();
                            statusCondition = false;
                            getFragmentManager().popBackStack();
                        }
                    }
                }
            }
        });
        return rootView;
    }

    private void setId(View rootView) {
        groupName = rootView.findViewById(R.id.groupName);
        groupMemberName = rootView.findViewById(R.id.groupMmberName);
        fatherHusbandName = rootView.findViewById(R.id.fatherhusbandName);
        phoneNumber = rootView.findViewById(R.id.phonenumber);
        spinnerMemberStatus = rootView.findViewById(R.id.memberStatusSpiner);
        groupVoPost = rootView.findViewById(R.id.groupvopost);
        dateOfJoining = rootView.findViewById(R.id.dateofjoining);
        addMember = rootView.findViewById(R.id.addMember);
        getdate();
    }

    String entryDate;

    private void getdate() {
        Calendar cal = Calendar.getInstance();
        Date currentDate = cal.getTime();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDateString = formatter.format(currentDate);
        entryDate = formattedDateString;
        //    tv_date.setText(entryDate);
    }

    private String getProperFormat(int hhORmm) {
        String temp = hhORmm + "";
        if (temp.length() == 1) {
            temp = "0" + temp;
        } else {

        }
        return temp;
    }

    boolean statusCondition = false;

    private void showError(EditText editText) {
        Animation shake = AnimationUtils.loadAnimation(getActivity(), R.anim.shake);
        editText.startAnimation(shake);
    }

    private void showErrorTextview(TextView editText) {
        Animation shake = AnimationUtils.loadAnimation(getActivity(), R.anim.shake);
        editText.startAnimation(shake);
    }
}
