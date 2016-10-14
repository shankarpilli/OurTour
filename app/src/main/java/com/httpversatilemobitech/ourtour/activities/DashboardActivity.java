package com.httpversatilemobitech.ourtour.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.httpversatilemobitech.ourtour.R;
import com.httpversatilemobitech.ourtour.fragments.HomeFragment;
import com.httpversatilemobitech.ourtour.utils.Utility;

/**
 * Created by Rev's Nani on 13-10-2016.
 */
public class DashboardActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        Utility.navigateDashBoardFragment(new HomeFragment(), HomeFragment.TAG, null, DashboardActivity.this);
    }
}
