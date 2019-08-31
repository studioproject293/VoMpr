package com.jslps.vompr.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.jslps.vompr.Constant;
import com.jslps.vompr.PrefManager;
import com.jslps.vompr.R;
import com.jslps.vompr.adapter.MemberListRecyclerAdapter;
import com.jslps.vompr.model.AddMemberModel;
import com.jslps.vompr.model.HeaderData;
import com.jslps.vompr.model.StateModelDb;
import com.jslps.vompr.model.Table8Db;
import com.jslps.vompr.model.Table9Db;

import java.util.ArrayList;

public class MemberListFragment extends BaseFragment {

    RecyclerView recyclerViewMemberList;
    MemberListRecyclerAdapter memberListRecyclerAdapter;
    private SearchView mSearchView;
    PrefManager prefManager;

    public MemberListFragment() {
        // Required empty public constructor
    }

    static Table8Db table8DbRec;

    public static MemberListFragment newInstance(Table8Db table8Db) {
        table8DbRec = table8Db;
        return new MemberListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.vomemberlist, container, false);
        prefManager = PrefManager.getInstance();

        recyclerViewMemberList = rootView.findViewById(R.id.recyclerViewMember);
        mSearchView = rootView.findViewById(R.id.inputSearch);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerViewMemberList.setHasFixedSize(true);
        recyclerViewMemberList.setLayoutManager(mLayoutManager);
        recyclerViewMemberList.setItemAnimator(new DefaultItemAnimator());
        ArrayList<Table9Db> table9DbArrayList = (ArrayList<Table9Db>) Table9Db.listAll(Table9Db.class);
        prefManager.setPREF__memberID(table9DbArrayList.get(0).getMemberid());
        prefManager.setPREF_VOCode(table9DbArrayList.get(0).getVoCode());

        mSearchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSearchView.setIconified(false);
            }
        });
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newEnteredText) {
                memberListRecyclerAdapter.getCustomContactFilter().filter(newEnteredText);
                return true;
            }
        });
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mListener.onFragmentUpdate(Constant.setTitle, new HeaderData(true, table8DbRec.getFedrationname()));
        ArrayList<Table9Db> table9DbArrayList = (ArrayList<Table9Db>) Table9Db.listAll(Table9Db.class);
        AddMemberModel addMemberModelrec = AddMemberModel.findById(AddMemberModel.class, 1);
       /* if (addMemberModelrec!=null) {
             Table9Db table9Db = new Table9Db(prefManager.getPREF_VOCode(), addMemberModelrec.getStatus(), addMemberModelrec.getPhoneno(), addMemberModelrec.getGroupofofficerpostname(),
                     String.valueOf(addMemberModelrec.getGroupcode()), String.valueOf(addMemberModelrec.getMemberid()), addMemberModelrec.getDateofjoin(), addMemberModelrec.getFatherhushbandname(), addMemberModelrec.getGroupmembername(),
                     addMemberModelrec.getGroupname());
             table9Db.save();
            *//* for (int i = 0; i < table9DbArrayList.size(); i++) {
                *//**//* if (!table9DbArrayList.get(i).getUuid().equals(addMemberModelrec.getUuid())) {
                     table9DbArrayList.add(table9Db);
                 }*//**//*
             }*//*
         }*/
        memberListRecyclerAdapter = new MemberListRecyclerAdapter(getActivity(), table9DbArrayList);
        recyclerViewMemberList.setAdapter(memberListRecyclerAdapter);
        System.out.println("sdhvdhfjdshv" + new Gson().toJson(addMemberModelrec));
    }
}
