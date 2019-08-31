package com.jslps.vompr.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.jslps.vompr.Constant;
import com.jslps.vompr.PrefManager;
import com.jslps.vompr.R;
import com.jslps.vompr.model.HeaderData;
import com.jslps.vompr.model.Table8Db;

public class VillageOrgnationFragment extends BaseFragment {

    private static Table8Db table8DbRecive;
    TextView textViewDistrict, textViewBlock, textViewPanchyat, textViewVillage1, textViewVillage2,
            textViewVillage3, textViewVillage4, textViewVillage5, textViewVOName, textViewVOCode, textViewFormationDate,
            textViewFedrationDate, textViewAct, textViewPromotedBy, textViewCompolusorySaving, textViewRateOfIntrestOnCIF,
            textViewMeetingFrequency, textViewDate, textViewDay, textViewVoOfficeType, textViewAddress, textViewContactName,
            textViewContactNumber, textViewTotalSCGInFedration, textViewTotalSCGInVillage;
    RadioGroup radioGroup;
    RadioButton radioButtonNew, radioButtonExisting, radioButtonYes, radioButtonNo;
    PrefManager prefManager;
    Button addMember;
    public VillageOrgnationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        mListener.onFragmentUpdate(Constant.setTitle, new HeaderData(false,"Village Organisation Details"));
    }

    public static VillageOrgnationFragment newInstance(Table8Db table8Db) {
        table8DbRecive = table8Db;
        return new VillageOrgnationFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_village_orgnation, container, false);
        setId(rootView);
        prefManager = PrefManager.getInstance();
        textViewDistrict.setText(prefManager.getDistictNme());
        textViewBlock.setText(prefManager.getPrefBlockName());
        textViewVillage2.setText(table8DbRecive.getVillagecode2());
        textViewVillage1.setText(table8DbRecive.getVillagename());
        textViewPanchyat.setText(table8DbRecive.getClustername());
        textViewVillage3.setText(table8DbRecive.getVillagecode2());
        textViewVillage4.setText(table8DbRecive.getVillagecode4());
        textViewVillage5.setText(table8DbRecive.getVillagecode5());
        textViewVOName.setText(table8DbRecive.getFedrationname());
        textViewVOCode.setText(table8DbRecive.getVocode());
        textViewFormationDate.setText(table8DbRecive.getFormationdate());
        textViewAct.setText(table8DbRecive.getAct());
        textViewPromotedBy.setText(table8DbRecive.getAgencyname());
        textViewCompolusorySaving.setText(table8DbRecive.getCompulsarysaving());
        textViewRateOfIntrestOnCIF.setText(table8DbRecive.getRateofintrestoncif());
        textViewMeetingFrequency.setText(table8DbRecive.getMeetingfrequency());
        textViewDay.setText("");
        textViewVoOfficeType.setText(table8DbRecive.getVoOfficetype());
        textViewContactNumber.setText(table8DbRecive.getContactnumber());
        textViewTotalSCGInFedration.setText(table8DbRecive.getTotalshginfedration());
        textViewTotalSCGInVillage.setText(table8DbRecive.getTotalshginvillage());
        textViewAddress.setText(table8DbRecive.getVoaddress());
        textViewContactName.setText("");
        textViewDate.setText("");
        radioButtonYes.setEnabled(false);
        radioButtonNo.setEnabled(false);
        radioButtonNew.setEnabled(false);
        radioButtonExisting.setEnabled(false);
        if (table8DbRecive.getRegistration().equals("No"))
            radioButtonNo.setChecked(true);
        else
            radioButtonYes.setChecked(true);
        if (table8DbRecive.getType().equals("No"))
            radioButtonNew.setChecked(true);
        else
            radioButtonExisting.setChecked(true);
        addMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onFragmentInteraction(Constant.MEMBER_LIST_FRAGMENT, table8DbRecive);
            }
        });
        return rootView;
    }

    private void setId(View rootView) {
        textViewDistrict = rootView.findViewById(R.id.district);
        textViewBlock = rootView.findViewById(R.id.sppinerBlock);
        textViewPanchyat = rootView.findViewById(R.id.sppinerPanchayat);
        textViewVillage1 = rootView.findViewById(R.id.sppinerVillage1);
        textViewVillage2 = rootView.findViewById(R.id.sppinerVillage2);
        textViewVillage3 = rootView.findViewById(R.id.sppinerVillage3);
        textViewVillage4 = rootView.findViewById(R.id.sppinerVillage4);
        textViewVillage5 = rootView.findViewById(R.id.sppinerVillage5);
        textViewVOName = rootView.findViewById(R.id.textViewVoName);
        textViewVOCode = rootView.findViewById(R.id.textVoCode);
        textViewFormationDate = rootView.findViewById(R.id.textViewFormationDate);
        textViewFedrationDate = rootView.findViewById(R.id.fedrationDate);
        textViewMeetingFrequency = rootView.findViewById(R.id.meetingFrequency);
        textViewDate = rootView.findViewById(R.id.date);
        textViewDay = rootView.findViewById(R.id.day);
        textViewVoOfficeType = rootView.findViewById(R.id.voOfficeType);
        textViewAddress = rootView.findViewById(R.id.text_address);
        textViewContactName = rootView.findViewById(R.id.contactName);
        textViewDay = rootView.findViewById(R.id.day);
        textViewContactNumber = rootView.findViewById(R.id.textViewContactNumber);
        textViewTotalSCGInFedration = rootView.findViewById(R.id.totalscgmember);
        textViewTotalSCGInVillage = rootView.findViewById(R.id.textViewTotalSCGInVillage);
        textViewAct = rootView.findViewById(R.id.act);
        textViewPromotedBy = rootView.findViewById(R.id.promotedBy);
        textViewCompolusorySaving = rootView.findViewById(R.id.compolsourySaving);
        textViewRateOfIntrestOnCIF = rootView.findViewById(R.id.rateofintrestcif);
        radioButtonNew = rootView.findViewById(R.id.radioNew);
        radioButtonExisting = rootView.findViewById(R.id.radioExisting);
        radioButtonYes = rootView.findViewById(R.id.radioyes);
        radioButtonNo = rootView.findViewById(R.id.radiono);
        radioGroup = rootView.findViewById(R.id.radio_group);
        addMember = rootView.findViewById(R.id.addMember);
    }

}
