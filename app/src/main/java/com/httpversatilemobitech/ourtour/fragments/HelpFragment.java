package com.httpversatilemobitech.ourtour.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.httpversatilemobitech.ourtour.R;
import com.httpversatilemobitech.ourtour.activities.DashboardActivity;

/**
 * Created by Rev's Nani on 13-10-2016.
 */
public class HelpFragment extends Fragment {

    public static final String TAG = "HelpFragment";
    private DashboardActivity mParent;

    private Toolbar mToolbar;
    private View rootView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mParent = (DashboardActivity) getActivity();
        mToolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mToolbar != null) {
            mToolbar.setVisibility(View.VISIBLE);
        }
        rootView = inflater.inflate(R.layout.fragment_help, container, false);

        return rootView;
    }
}
