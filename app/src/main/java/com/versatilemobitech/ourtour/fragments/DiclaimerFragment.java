package com.versatilemobitech.ourtour.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.versatilemobitech.ourtour.R;
import com.versatilemobitech.ourtour.activities.DashboardActivity;

/**
 * Created by Rev's Nani on 13-10-2016.
 */
public class DiclaimerFragment extends Fragment {

    public static final String TAG = "DiclaimerFragment";
    private DashboardActivity mParent;
    private String mToolBarTitle;
    private Toolbar mToolbar;
    private View rootView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mParent = (DashboardActivity) getActivity();
        mToolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        mToolBarTitle = getArguments().getString("Diclaimer");

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mToolbar != null) {
            mToolbar.setVisibility(View.VISIBLE);
        }
        rootView = inflater.inflate(R.layout.fragment_diclaimer, container, false);
        mParent.txt_our_tour.setText(""+mToolBarTitle);
        return rootView;
    }
}
