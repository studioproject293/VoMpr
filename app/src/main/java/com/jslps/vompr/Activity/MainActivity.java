package com.jslps.vompr.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jslps.vompr.Constant;
import com.jslps.vompr.R;
import com.jslps.vompr.fragment.AddMemberFragment;
import com.jslps.vompr.fragment.HomeFragment;
import com.jslps.vompr.fragment.MemberListFragment;
import com.jslps.vompr.fragment.VillageOrgnationFragment;
import com.jslps.vompr.listener.OnFragmentInteractionListener;
import com.jslps.vompr.model.HeaderData;
import com.jslps.vompr.model.Table8Db;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnFragmentInteractionListener {
    private FragmentManager mFragmentManager;
    private String mFragmentTag;
    private int mCurrentFragment;
    Toolbar toolbar_home;
    TextView textheader;
    Button savenewMember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getSupportActionBar().show();
        setContentView(R.layout.activity_main);
        toolbar_home = findViewById(R.id.toolbar_home);
        textheader = findViewById(R.id.toolbar_title);
        savenewMember = findViewById(R.id.savenewMember);
        onFragmentInteraction(Constant.HOME_FRAGMENT, null);
        savenewMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onFragmentInteraction(Constant.ADD_MEMBER_FRAGMENT, null);
            }
        });
    }

    @Override
    public void onFragmentInteraction(int fragmentId, Object data) {

        mFragmentManager = getSupportFragmentManager();
        mCurrentFragment = fragmentId;
        mFragmentTag = String.valueOf(fragmentId);
        switch (fragmentId) {
            case Constant.HOME_FRAGMENT:
                savenewMember.setVisibility(View.GONE);
                mFragmentManager.beginTransaction().addToBackStack(mFragmentTag).replace(R.id.fragment_main, new HomeFragment(), mFragmentTag).commitAllowingStateLoss();
                break;
            case Constant.VOFRAGMENT:
                savenewMember.setVisibility(View.GONE);
                mFragmentManager.beginTransaction().addToBackStack(mFragmentTag).replace(R.id.fragment_main, new VillageOrgnationFragment().newInstance((Table8Db) data), mFragmentTag).commitAllowingStateLoss();
                break;
            case Constant.MEMBER_LIST_FRAGMENT:
                savenewMember.setVisibility(View.VISIBLE);
                mFragmentManager.beginTransaction().addToBackStack(mFragmentTag).replace(R.id.fragment_main, new MemberListFragment().newInstance((Table8Db) data), mFragmentTag).commitAllowingStateLoss();
                break;
            case Constant.ADD_MEMBER_FRAGMENT:
                savenewMember.setVisibility(View.GONE);
                mFragmentManager.beginTransaction().addToBackStack(mFragmentTag).replace(R.id.fragment_main, new AddMemberFragment().newInstance(), mFragmentTag).commitAllowingStateLoss();
                break;
        }
    }

    @Override
    public void onFragmentUpdate(int type, Object data) {
        switch (type) {
            case Constant.setTitle:
                HeaderData headerData = (HeaderData) data;
                textheader.setVisibility(View.VISIBLE);
                textheader.setText(headerData.getText());
                if (headerData.isLogoRequired())
                    savenewMember.setVisibility(View.VISIBLE);
                else
                    savenewMember.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (count <= 1) {
            closeApp();
            if (mCurrentFragment == Constant.HOME_FRAGMENT) {
                if (HomeFragment.materialDesignFAM.isOpened())
                    HomeFragment.materialDesignFAM.close(true);
            }
        } else
            super.onBackPressed();

    }

    public void closeApp() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage(getString(R.string.exit_message));

        alertDialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                finish();
            }
        });

        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
