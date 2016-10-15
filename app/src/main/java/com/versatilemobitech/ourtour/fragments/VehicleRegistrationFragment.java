package com.versatilemobitech.ourtour.fragments;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.versatilemobitech.ourtour.R;
import com.versatilemobitech.ourtour.activities.DashboardActivity;
import com.versatilemobitech.ourtour.utils.Utility;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

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

    private NestedScrollView scroll;

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

        scroll = (NestedScrollView) rootView.findViewById(R.id.scroll);
        edt_vehicla_make = (EditText) rootView.findViewById(R.id.edt_vehicla_make);
        edt_vehicle_model = (EditText) rootView.findViewById(R.id.edt_vehicle_model);
        edt_seaters = (EditText) rootView.findViewById(R.id.edt_seaters);
        edt_vendor_reg_number = (EditText) rootView.findViewById(R.id.edt_vendor_reg_number);
        edt_vendor_reg_date = (EditText) rootView.findViewById(R.id.edt_vendor_reg_date);
        edt_vendor_exp_date = (EditText) rootView.findViewById(R.id.edt_vendor_exp_date);
        edt_permit_number = (EditText) rootView.findViewById(R.id.edt_permit_number);
        edt_permit_reg_date = (EditText) rootView.findViewById(R.id.edt_permit_reg_date);
        edt_permit_exp_date = (EditText) rootView.findViewById(R.id.edt_permit_exp_date);
        edt_fitness_number = (EditText) rootView.findViewById(R.id.edt_fitness_number);
        edt_fitness_reg_num = (EditText) rootView.findViewById(R.id.edt_fitness_reg_num);
        edt_fitness_exp_num = (EditText) rootView.findViewById(R.id.edt_fitness_exp_num);
        edt_insurance_number = (EditText) rootView.findViewById(R.id.edt_insurance_number);
        edt_insurance_reg_date = (EditText) rootView.findViewById(R.id.edt_insurance_reg_date);
        edt_insurance_exp_date = (EditText) rootView.findViewById(R.id.edt_insurance_exp_date);
        edt_population_number = (EditText) rootView.findViewById(R.id.edt_population_number);
        edt_population_reg_date = (EditText) rootView.findViewById(R.id.edt_population_reg_date);
        edt_population_exp_date = (EditText) rootView.findViewById(R.id.edt_population_exp_date);
        iv_bg = (ImageView) rootView.findViewById(R.id.iv_bg);
        btn_submit = (Button) rootView.findViewById(R.id.btn_submit);

        btn_submit.setOnClickListener(this);

        edt_vendor_reg_date.setOnClickListener(this);
        edt_vendor_exp_date.setOnClickListener(this);

        edt_permit_reg_date.setOnClickListener(this);
        edt_permit_exp_date.setOnClickListener(this);

        edt_fitness_reg_num.setOnClickListener(this);
        edt_fitness_exp_num.setOnClickListener(this);

        edt_insurance_reg_date.setOnClickListener(this);
        edt_insurance_exp_date.setOnClickListener(this);

        edt_population_reg_date.setOnClickListener(this);
        edt_population_exp_date.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        DialogFragment newFragment = null;
        switch (v.getId()) {
            case R.id.edt_vendor_reg_date:
                newFragment = new SelectDateFragment(edt_vendor_reg_date);
                newFragment.show(mParent.getSupportFragmentManager(), "DatePicker");
                break;
            case R.id.edt_vendor_exp_date:
                newFragment = new SelectDateFragment(edt_vendor_exp_date);
                newFragment.show(mParent.getSupportFragmentManager(), "DatePicker");
                break;
            case R.id.edt_permit_reg_date:
                newFragment = new SelectDateFragment(edt_permit_reg_date);
                newFragment.show(mParent.getSupportFragmentManager(), "DatePicker");
                break;
            case R.id.edt_permit_exp_date:
                newFragment = new SelectDateFragment(edt_permit_exp_date);
                newFragment.show(mParent.getSupportFragmentManager(), "DatePicker");
                break;
            case R.id.edt_fitness_exp_num:
                newFragment = new SelectDateFragment(edt_fitness_exp_num);
                newFragment.show(mParent.getSupportFragmentManager(), "DatePicker");
                break;
            case R.id.edt_fitness_reg_num:
                newFragment = new SelectDateFragment(edt_fitness_reg_num);
                newFragment.show(mParent.getSupportFragmentManager(), "DatePicker");
                break;
            case R.id.edt_insurance_reg_date:
                newFragment = new SelectDateFragment(edt_insurance_reg_date);
                newFragment.show(mParent.getSupportFragmentManager(), "DatePicker");
                break;
            case R.id.edt_insurance_exp_date:
                newFragment = new SelectDateFragment(edt_insurance_exp_date);
                newFragment.show(mParent.getSupportFragmentManager(), "DatePicker");
                break;
            case R.id.edt_population_reg_date:
                newFragment = new SelectDateFragment(edt_population_reg_date);
                newFragment.show(mParent.getSupportFragmentManager(), "DatePicker");
                break;
            case R.id.edt_population_exp_date:
                newFragment = new SelectDateFragment(edt_population_exp_date);
                newFragment.show(mParent.getSupportFragmentManager(), "DatePicker");
                break;
            case R.id.btn_submit:
                if (validation()) {
                    Utility.showToastMessage(mParent, "" + validation());
                }else {
                    Utility.showToastMessage(mParent, "validation fail");
                }
                break;
        }
    }

    private boolean validation() {
        boolean isValidated = false;
        if (Utility.isValueNullOrEmpty(edt_vehicla_make.getText().toString().trim())) {
            Utility.setSnackBarEnglish(mParent,edt_vehicla_make,"please enter the vehicle make");

        } else if (Utility.isValueNullOrEmpty(edt_vehicle_model.getText().toString().trim())) {
            Utility.setSnackBarEnglish(mParent,edt_vehicle_model,"please enter the vehicle model");

        } else if (Utility.isValueNullOrEmpty(edt_seaters.getText().toString().trim())) {
            Utility.setSnackBarEnglish(mParent,edt_seaters,"please enter the seaters");

        } else if (Utility.isValueNullOrEmpty(edt_vendor_reg_number.getText().toString().trim())) {
            Utility.setSnackBarEnglish(mParent,edt_vendor_reg_number,"please enter the vendor registration number");

        } else if (Utility.isValueNullOrEmpty(edt_vendor_reg_date.getText().toString().trim())) {
            Utility.setSnackBarEnglish(mParent,edt_vendor_reg_date,"please enter the vendor registration date");

        } else if (Utility.isValueNullOrEmpty(edt_vendor_exp_date.getText().toString().trim())) {
            Utility.setSnackBarEnglish(mParent,edt_vendor_exp_date,"please enter the vendor expair date");

        } else if (Utility.isValueNullOrEmpty(edt_permit_number.getText().toString().trim())) {
            Utility.setSnackBarEnglish(mParent,edt_permit_number,"please enter the permit number");

        } else if (Utility.isValueNullOrEmpty(edt_permit_reg_date.getText().toString().trim())) {
            Utility.setSnackBarEnglish(mParent,edt_permit_reg_date,"please enter the permit registration date");

        } else if (Utility.isValueNullOrEmpty(edt_permit_exp_date.getText().toString().trim())) {
            Utility.setSnackBarEnglish(mParent,edt_permit_exp_date,"please enter the permit expair date");

        } else if (Utility.isValueNullOrEmpty(edt_fitness_number.getText().toString().trim())) {
            Utility.setSnackBarEnglish(mParent,edt_fitness_number,"please enter the fitness number");

        } else if (Utility.isValueNullOrEmpty(edt_fitness_reg_num.getText().toString().trim())) {
            Utility.setSnackBarEnglish(mParent,edt_fitness_reg_num,"please enter the fitness registration date");

        } else if (Utility.isValueNullOrEmpty(edt_fitness_exp_num.getText().toString().trim())) {
            Utility.setSnackBarEnglish(mParent,edt_fitness_exp_num,"please enter the fitness expair date");

        } else if (Utility.isValueNullOrEmpty(edt_insurance_number.getText().toString().trim())) {
            Utility.setSnackBarEnglish(mParent,edt_insurance_number,"please enter the insurance number");

        } else if (Utility.isValueNullOrEmpty(edt_insurance_reg_date.getText().toString().trim())) {
            Utility.setSnackBarEnglish(mParent,edt_insurance_reg_date,"please enter the insurance registration date");

        } else if (Utility.isValueNullOrEmpty(edt_insurance_exp_date.getText().toString().trim())) {
            Utility.setSnackBarEnglish(mParent,edt_insurance_exp_date,"please enter the insurance expair date");

        } else if (Utility.isValueNullOrEmpty(edt_population_number.getText().toString().trim())) {
            Utility.setSnackBarEnglish(mParent,edt_population_number,"please enter the population number");

        } else if (Utility.isValueNullOrEmpty(edt_population_reg_date.getText().toString().trim())) {
            Utility.setSnackBarEnglish(mParent,edt_population_reg_date,"please enter the population registration date");

        } else if (Utility.isValueNullOrEmpty(edt_population_exp_date.getText().toString().trim())) {
            Utility.setSnackBarEnglish(mParent,edt_population_exp_date,"please enter the population expair date");

        }else {
            isValidated = true;
        }
        return isValidated;
    }

    public static class SelectDateFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        EditText editText;

        public SelectDateFragment(EditText mEditText) {
            this.editText = mEditText;
        }
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calendar = Calendar.getInstance();
            int yy = calendar.get(Calendar.YEAR);
            int mm = calendar.get(Calendar.MONTH);
            int dd = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog mDatePickerDialog = new DatePickerDialog(getActivity(), this, yy, mm, dd);
            return mDatePickerDialog;
        }

        public void onDateSet(DatePicker view, int yy, int mm, int dd) {
            populateSetDate(yy, mm + 1, dd);
        }

        public void populateSetDate(int year, int month, int day) {
            //dob.setText(month+"/"+day+"/"+year);
            editText.setText(month + "/" + day + "/" + year);
        }
    }
}
