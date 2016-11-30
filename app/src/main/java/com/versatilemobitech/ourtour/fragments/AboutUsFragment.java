package com.versatilemobitech.ourtour.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.versatilemobitech.ourtour.R;
import com.versatilemobitech.ourtour.activities.DashboardActivity;
import com.versatilemobitech.ourtour.utils.Utility;

/**
 * Created by Rev's Nani on 13-10-2016.
 */
public class AboutUsFragment extends Fragment {

    public static final String TAG = "AboutUsFragment";
    private DashboardActivity mParent;
    private String mToolBarTitle;

    private Toolbar mToolbar;
    private View rootView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mParent = (DashboardActivity) getActivity();
        mToolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        mToolBarTitle =  getArguments().getString("AboutUs");

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mToolbar != null) {
            mToolbar.setVisibility(View.VISIBLE);
        }
        rootView = inflater.inflate(R.layout.fragment_about_us, container, false);
        mParent.txt_our_tour.setText(""+mToolBarTitle);

        TextView tv_about_us = (TextView)rootView.findViewById(R.id.tv_about_us);
        tv_about_us.setTypeface(Utility.setTypeFace_Roboto_Regular(getActivity()));
        return rootView;
    }
}
