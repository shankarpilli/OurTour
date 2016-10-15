package com.versatilemobitech.ourtour.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.versatilemobitech.ourtour.R;
import com.versatilemobitech.ourtour.activities.DashboardActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class VehicleRegistrationFragment extends Fragment implements View.OnClickListener {

    public static final String TAG = "VehicleRegistrationFragment";
    private DashboardActivity mParent;

    private Toolbar mToolbar;
    private View rootView;

    private EditText edt_vehicla_make;
    private EditText edt_vehicle_model;
    private EditText edt_seaters;

    private EditText edt_vendor_reg_number;
    private EditText edt_vendor_reg_date;
    private EditText edt_vendor_exp_date;

    private EditText edt_permit_number;
    private EditText edt_permit_reg_date;
    private EditText edt_permit_exp_date;

    private EditText edt_fitness_number;
    private EditText edt_fitness_reg_num;
    private EditText edt_fitness_exp_num;

    private EditText edt_insurance_number;
    private EditText edt_insurance_reg_date;
    private EditText edt_insurance_exp_date;

    private EditText edt_population_number;
    private EditText edt_population_reg_date;
    private EditText edt_population_exp_date;

    private ScrollView scroll;

    private ImageView iv_bg;
    private Button btn_submit;

    public VehicleRegistrationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mParent = (DashboardActivity) getActivity();
        mToolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (mToolbar != null) {
            mToolbar.setVisibility(View.VISIBLE);
        }
        rootView = inflater.inflate(R.layout.fragment_vehicle, container, false);
        initUI();
        return rootView;

    }

    private void initUI() {

        scroll = (ScrollView)rootView.findViewById(R.id.scroll);
        edt_vehicla_make = (EditText)rootView.findViewById(R.id.edt_vehicla_make);
        edt_vehicle_model = (EditText)rootView.findViewById(R.id.edt_vehicle_model);
        edt_seaters = (EditText)rootView.findViewById(R.id.edt_seaters);
        edt_vendor_reg_number = (EditText)rootView.findViewById(R.id.edt_vendor_reg_number);
        edt_vendor_reg_date = (EditText)rootView.findViewById(R.id.edt_vendor_reg_date);
        edt_vendor_exp_date = (EditText)rootView.findViewById(R.id.edt_vendor_exp_date);
        edt_permit_number = (EditText)rootView.findViewById(R.id.edt_permit_number);
        edt_permit_reg_date = (EditText)rootView.findViewById(R.id.edt_permit_reg_date);
        edt_permit_exp_date = (EditText)rootView.findViewById(R.id.edt_permit_exp_date);
        edt_fitness_number = (EditText)rootView.findViewById(R.id.edt_fitness_number);
        edt_fitness_reg_num = (EditText)rootView.findViewById(R.id.edt_fitness_reg_num);
        edt_fitness_exp_num = (EditText)rootView.findViewById(R.id.edt_fitness_exp_num);
        edt_insurance_number = (EditText)rootView.findViewById(R.id.edt_insurance_number);
        edt_insurance_reg_date = (EditText)rootView.findViewById(R.id.edt_insurance_reg_date);
        edt_insurance_exp_date = (EditText)rootView.findViewById(R.id.edt_insurance_exp_date);
        edt_population_number = (EditText)rootView.findViewById(R.id.edt_population_number);
        edt_population_reg_date = (EditText)rootView.findViewById(R.id.edt_population_reg_date);
        edt_population_exp_date = (EditText)rootView.findViewById(R.id.edt_population_exp_date);
        iv_bg = (ImageView) rootView.findViewById(R.id.iv_bg);
        btn_submit = (Button) rootView.findViewById(R.id.btn_submit);

        btn_submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_submit:
                break;
        }
    }
}
