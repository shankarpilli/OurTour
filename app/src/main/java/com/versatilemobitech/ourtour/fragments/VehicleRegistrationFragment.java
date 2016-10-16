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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.versatilemobitech.ourtour.R;
import com.versatilemobitech.ourtour.activities.DashboardActivity;
import com.versatilemobitech.ourtour.asynctask.IAsyncCaller;
import com.versatilemobitech.ourtour.models.Model;
import com.versatilemobitech.ourtour.models.VehicleRegistration;
import com.versatilemobitech.ourtour.utils.Utility;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Shankar Pilli.
 */
public class VehicleRegistrationFragment extends Fragment implements View.OnClickListener, IAsyncCaller, AdapterView.OnItemSelectedListener {

    public static final String TAG = "VehicleRegistrationFragment";
    private DashboardActivity mParent;

    private Toolbar mToolbar;
    private View rootView;

    private EditText edt_vehicle_make;
    private EditText edt_vehicle_model;
    private EditText edt_seaters;

    private EditText edt_vehicle_reg_number;
    private EditText edt_vehicle_reg_date;
    private EditText edt_vehicle_exp_date;

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

    private Spinner spinner_vehicle_make;
    private Spinner spinner_seaters;

    private NestedScrollView scroll;

    private ImageView iv_bg;
    private Button btn_submit;
    private ArrayList<String> spinnerSeater;

    private VehicleRegistration vehicleRegistration;

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
        edt_vehicle_make = (EditText) rootView.findViewById(R.id.edt_vehicla_make);
        edt_vehicle_model = (EditText) rootView.findViewById(R.id.edt_vehicle_model);
        edt_seaters = (EditText) rootView.findViewById(R.id.edt_seaters);
        edt_vehicle_reg_number = (EditText) rootView.findViewById(R.id.edt_vehicle_reg_number);
        edt_vehicle_reg_date = (EditText) rootView.findViewById(R.id.edt_vehicle_reg_date);
        edt_vehicle_exp_date = (EditText) rootView.findViewById(R.id.edt_vehicle_exp_date);
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

        spinner_vehicle_make = (Spinner) rootView.findViewById(R.id.spinner_vehicle_make);
        spinner_seaters = (Spinner) rootView.findViewById(R.id.spinner_seaters);

        spinner_vehicle_make.setOnItemSelectedListener(this);
        spinner_seaters.setOnItemSelectedListener(this);
        btn_submit.setOnClickListener(this);

        edt_vehicle_reg_date.setOnClickListener(this);
        edt_vehicle_exp_date.setOnClickListener(this);

        edt_permit_reg_date.setOnClickListener(this);
        edt_permit_exp_date.setOnClickListener(this);

        edt_fitness_reg_num.setOnClickListener(this);
        edt_fitness_exp_num.setOnClickListener(this);

        edt_insurance_reg_date.setOnClickListener(this);
        edt_insurance_exp_date.setOnClickListener(this);

        edt_population_reg_date.setOnClickListener(this);
        edt_population_exp_date.setOnClickListener(this);

        edt_vehicle_make.setOnClickListener(this);
        edt_seaters.setOnClickListener(this);


        ArrayList<String> spinnerArray = new ArrayList<>();
        for (int i = 0; i < getBrands().size(); i++) {
            spinnerArray.add(getBrands().get(i));
        }
        ArrayAdapter spinnerArrayAdapter = new ArrayAdapter(getActivity(),
                android.R.layout.simple_spinner_dropdown_item,
                spinnerArray);
        spinner_vehicle_make.setAdapter(spinnerArrayAdapter);

        spinnerSeater = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            spinnerSeater.add("" + i);
        }
        ArrayAdapter spinnerSeaterArray = new ArrayAdapter(getActivity(),
                android.R.layout.simple_spinner_dropdown_item,
                spinnerSeater);
        spinner_seaters.setAdapter(spinnerSeaterArray);
    }

    private ArrayList<String> getBrands() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("Maruti");
        strings.add("Hundai");
        strings.add("Toyota");
        strings.add("Honda");
        strings.add("Tata");
        strings.add("Ford");
        strings.add("Chevrolet");
        return strings;
    }

    @Override
    public void onClick(View v) {
        DialogFragment newFragment = null;
        switch (v.getId()) {
            case R.id.edt_vehicle_reg_date:
                newFragment = new SelectDateFragment(edt_vehicle_reg_date);
                newFragment.show(mParent.getSupportFragmentManager(), "DatePicker");
                break;
            case R.id.edt_vehicle_exp_date:
                newFragment = new SelectDateFragment(edt_vehicle_exp_date);
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
                    vehicleRegistration = new VehicleRegistration();
                    vehicleRegistration.setVendor_id("");
                    vehicleRegistration.setVehicle_make(edt_vehicle_make.getText().toString());
                    vehicleRegistration.setVehicle_model(edt_vehicle_model.getText().toString());
                    vehicleRegistration.setVehicle_type("");
                    vehicleRegistration.setSeater(edt_seaters.getText().toString());

                    vehicleRegistration.setVehicle_registration_number(edt_vehicle_reg_number.getText().toString());
                    vehicleRegistration.setVehicle_registration_date(edt_vehicle_reg_date.getText().toString());
                    vehicleRegistration.setVehicle_registration_expiry(edt_vehicle_exp_date.getText().toString());

                    vehicleRegistration.setPermit_number(edt_permit_number.getText().toString());
                    vehicleRegistration.setPermit_date(edt_permit_reg_date.getText().toString());
                    vehicleRegistration.setPermit_expiry(edt_permit_exp_date.getText().toString());

                    vehicleRegistration.setFitness_number(edt_fitness_number.getText().toString());
                    vehicleRegistration.setFitness_date(edt_fitness_reg_num.getText().toString());
                    vehicleRegistration.setFitness_expiry(edt_fitness_exp_num.getText().toString());

                    vehicleRegistration.setInsurance_number(edt_insurance_number.getText().toString());
                    vehicleRegistration.setInsurance_date(edt_insurance_reg_date.getText().toString());
                    vehicleRegistration.setInsurance_expiry(edt_insurance_exp_date.getText().toString());

                    vehicleRegistration.setPollution_number(edt_population_number.getText().toString());
                    vehicleRegistration.setPollution_date(edt_population_reg_date.getText().toString());
                    vehicleRegistration.setPollution_expiry(edt_population_exp_date.getText().toString());

                    AddCarFragment.tabLayout.getTabAt(2).select();
                }
                break;
            case R.id.edt_vehicla_make:
                spinner_vehicle_make.performClick();
                break;
            case R.id.edt_seaters:
                spinner_seaters.performClick();
                break;
        }
    }

    private boolean validation() {
        boolean isValidated = false;
        if (Utility.isValueNullOrEmpty(edt_vehicle_make.getText().toString().trim())) {
            Utility.setSnackBarEnglish(mParent, edt_vehicle_make, "Please enter the vehicle make");
            edt_vehicle_make.requestFocus();

        } else if (Utility.isValueNullOrEmpty(edt_vehicle_model.getText().toString().trim())) {
            Utility.setSnackBarEnglish(mParent, edt_vehicle_model, "Please enter the vehicle model");
            edt_vehicle_model.requestFocus();

        } else if (Utility.isValueNullOrEmpty(edt_seaters.getText().toString().trim())) {
            Utility.setSnackBarEnglish(mParent, edt_seaters, "Please enter the seaters");
            edt_seaters.requestFocus();

        } else if (Utility.isValueNullOrEmpty(edt_vehicle_reg_number.getText().toString().trim())) {
            Utility.setSnackBarEnglish(mParent, edt_vehicle_reg_number, "Please enter the vendor registration number");
            edt_vehicle_reg_number.requestFocus();

        } else if (Utility.isValueNullOrEmpty(edt_vehicle_reg_date.getText().toString().trim())) {
            Utility.setSnackBarEnglish(mParent, edt_vehicle_reg_date, "Please enter the vendor registration date");
            edt_vehicle_reg_date.requestFocus();

        } else if (Utility.isValueNullOrEmpty(edt_vehicle_exp_date.getText().toString().trim())) {
            Utility.setSnackBarEnglish(mParent, edt_vehicle_exp_date, "Please enter the vendor expire date");
            edt_vehicle_exp_date.requestFocus();

        } else if (Utility.isValueNullOrEmpty(edt_permit_number.getText().toString().trim())) {
            Utility.setSnackBarEnglish(mParent, edt_permit_number, "Please enter the permit number");
            edt_permit_number.requestFocus();

        } else if (Utility.isValueNullOrEmpty(edt_permit_reg_date.getText().toString().trim())) {
            Utility.setSnackBarEnglish(mParent, edt_permit_reg_date, "Please enter the permit registration date");
            edt_permit_reg_date.requestFocus();

        } else if (Utility.isValueNullOrEmpty(edt_permit_exp_date.getText().toString().trim())) {
            Utility.setSnackBarEnglish(mParent, edt_permit_exp_date, "Please enter the permit expire date");
            edt_permit_exp_date.requestFocus();

        } else if (Utility.isValueNullOrEmpty(edt_fitness_number.getText().toString().trim())) {
            Utility.setSnackBarEnglish(mParent, edt_fitness_number, "Please enter the fitness number");
            edt_fitness_number.requestFocus();

        } else if (Utility.isValueNullOrEmpty(edt_fitness_reg_num.getText().toString().trim())) {
            Utility.setSnackBarEnglish(mParent, edt_fitness_reg_num, "Please enter the fitness registration date");
            edt_fitness_reg_num.requestFocus();

        } else if (Utility.isValueNullOrEmpty(edt_fitness_exp_num.getText().toString().trim())) {
            Utility.setSnackBarEnglish(mParent, edt_fitness_exp_num, "Please enter the fitness expire date");
            edt_fitness_exp_num.requestFocus();

        } else if (Utility.isValueNullOrEmpty(edt_insurance_number.getText().toString().trim())) {
            Utility.setSnackBarEnglish(mParent, edt_insurance_number, "Please enter the insurance number");
            edt_insurance_number.requestFocus();

        } else if (Utility.isValueNullOrEmpty(edt_insurance_reg_date.getText().toString().trim())) {
            Utility.setSnackBarEnglish(mParent, edt_insurance_reg_date, "Please enter the insurance registration date");
            edt_insurance_reg_date.requestFocus();

        } else if (Utility.isValueNullOrEmpty(edt_insurance_exp_date.getText().toString().trim())) {
            Utility.setSnackBarEnglish(mParent, edt_insurance_exp_date, "Please enter the insurance expire date");
            edt_insurance_exp_date.requestFocus();

        } else if (Utility.isValueNullOrEmpty(edt_population_number.getText().toString().trim())) {
            Utility.setSnackBarEnglish(mParent, edt_population_number, "Please enter the population number");
            edt_population_number.requestFocus();

        } else if (Utility.isValueNullOrEmpty(edt_population_reg_date.getText().toString().trim())) {
            Utility.setSnackBarEnglish(mParent, edt_population_reg_date, "Please enter the population registration date");
            edt_population_reg_date.requestFocus();

        } else if (Utility.isValueNullOrEmpty(edt_population_exp_date.getText().toString().trim())) {
            Utility.setSnackBarEnglish(mParent, edt_population_exp_date, "Please enter the population expire date");
            edt_population_exp_date.requestFocus();

        } else {
            isValidated = true;
        }
        return isValidated;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.spinner_vehicle_make:
                edt_vehicle_make.setText(getBrands().get(position));
                break;
            case R.id.spinner_seaters:
                edt_seaters.setText(spinnerSeater.get(position));
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onComplete(Model model) {
        if (model != null) {
            if (model.isStatus()) {

            }
        }
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
