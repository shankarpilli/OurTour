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
public class HomeFragment extends Fragment implements View.OnClickListener {

    public static final String TAG = "HomeFragment";
    private DashboardActivity mParent;
    private String mToolBarTitle;
    private Toolbar mToolbar;
    private View rootView;
    private TextView txt_add_your_car;
    private TextView tv_home_content;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mParent = (DashboardActivity) getActivity();
        mToolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        mToolBarTitle = getArguments().getString("Home");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mToolbar != null) {
            mToolbar.setVisibility(View.VISIBLE);
        }
        rootView = inflater.inflate(R.layout.fragment_home, container, false);
        mParent.txt_our_tour.setText("" + mToolBarTitle);
        mParent.txt_our_tour.setTypeface(Utility.setTypeFace_Roboto_Bold(getActivity()));
        initUI();
        return rootView;
    }

    private void initUI() {
        txt_add_your_car = (TextView) rootView.findViewById(R.id.txt_add_your_car);
        tv_home_content = (TextView) rootView.findViewById(R.id.tv_home_content);
        tv_home_content.setTypeface(Utility.setTypeFace_Roboto_Regular(getActivity()));
        txt_add_your_car.setTypeface(Utility.setTypeFace_Roboto_Regular(getActivity()));
        txt_add_your_car.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txt_add_your_car:
                Utility.navigateDashBoardFragment(new AddCarFragment(), AddCarFragment.TAG, null, mParent);
                break;
        }
    }
}
