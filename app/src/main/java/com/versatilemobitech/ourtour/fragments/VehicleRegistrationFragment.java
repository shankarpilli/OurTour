package com.versatilemobitech.ourtour.fragments;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.versatilemobitech.ourtour.R;
import com.versatilemobitech.ourtour.activities.DashboardActivity;
import com.versatilemobitech.ourtour.models.SpinnerModel;
import com.versatilemobitech.ourtour.models.VehicleRegistration;
import com.versatilemobitech.ourtour.utils.Utility;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Shankar Pilli.
 */
public class VehicleRegistrationFragment extends Fragment implements View.OnClickListener {

    public static final String TAG = "VehicleRegistrationFragment";
    private DashboardActivity mParent;

    public static boolean isPromoCodeApplied;
    private Toolbar mToolbar;
    private View rootView;

    private static EditText edt_vehicle_make;
    private static EditText edt_vehicle_model;
    private static EditText edt_seaters;

    private static EditText edt_vehicle_reg_number;
    private static EditText edt_vehicle_reg_date;
    private static EditText edt_vehicle_exp_date;

    private static EditText edt_permit_number;
    private static EditText edt_permit_reg_date;
    private static EditText edt_permit_exp_date;

    private static EditText edt_fitness_number;
    private static EditText edt_fitness_reg_num;
    private static EditText edt_fitness_exp_num;

    private static EditText edt_insurance_number;
    private static EditText edt_insurance_reg_date;
    private static EditText edt_insurance_exp_date;

    private static EditText edt_population_number;
    private static EditText edt_population_reg_date;
    private static EditText edt_population_exp_date;
    private ArrayList<SpinnerModel> mDialodList;
    private ArrayList<SpinnerModel> mVehicleModelList;

    private NestedScrollView scroll;

    private ImageView iv_bg;
    private Button btn_submit;

    public static VehicleRegistration vehicleRegistration;

    private static int mRegVclYear;
    private static int mRegVclMonth;
    private static int mRegVclDay;

    private static int mRegPerYear;
    private static int mRegPerMonth;
    private static int mRegPerDay;

    private static int mRegFitYear;
    private static int mRegFitMonth;
    private static int mRegFitDay;

    private static int mRegIncYear;
    private static int mRegIncMonth;
    private static int mRegIncDay;

    private static int mRegPolYear;
    private static int mRegPolMonth;
    private static int mRegPolDay;
    private static long mPastDate;


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
        edt_vehicle_reg_number.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
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
        edt_vehicle_model.setOnClickListener(this);
        edt_seaters.setOnClickListener(this);

        setTypeface();
    }

    private void setTypeface() {
        edt_vehicle_make.setTypeface(Utility.setTypeFace_Roboto_Regular(getActivity()));
        edt_vehicle_model.setTypeface(Utility.setTypeFace_Roboto_Regular(getActivity()));
        edt_seaters.setTypeface(Utility.setTypeFace_Roboto_Regular(getActivity()));
        edt_vehicle_reg_number.setTypeface(Utility.setTypeFace_Roboto_Regular(getActivity()));
        edt_vehicle_reg_date.setTypeface(Utility.setTypeFace_Roboto_Regular(getActivity()));
        edt_vehicle_exp_date.setTypeface(Utility.setTypeFace_Roboto_Regular(getActivity()));
        edt_permit_number.setTypeface(Utility.setTypeFace_Roboto_Regular(getActivity()));
        edt_permit_reg_date.setTypeface(Utility.setTypeFace_Roboto_Regular(getActivity()));
        edt_permit_exp_date.setTypeface(Utility.setTypeFace_Roboto_Regular(getActivity()));
        edt_fitness_number.setTypeface(Utility.setTypeFace_Roboto_Regular(getActivity()));
        edt_fitness_reg_num.setTypeface(Utility.setTypeFace_Roboto_Regular(getActivity()));
        edt_fitness_exp_num.setTypeface(Utility.setTypeFace_Roboto_Regular(getActivity()));
        edt_insurance_number.setTypeface(Utility.setTypeFace_Roboto_Regular(getActivity()));
        edt_insurance_reg_date.setTypeface(Utility.setTypeFace_Roboto_Regular(getActivity()));
        edt_insurance_exp_date.setTypeface(Utility.setTypeFace_Roboto_Regular(getActivity()));
        edt_population_number.setTypeface(Utility.setTypeFace_Roboto_Regular(getActivity()));
        edt_population_reg_date.setTypeface(Utility.setTypeFace_Roboto_Regular(getActivity()));
        edt_population_exp_date.setTypeface(Utility.setTypeFace_Roboto_Regular(getActivity()));
        btn_submit.setTypeface(Utility.setTypeFace_Roboto_Regular(getActivity()));
    }

    @Override
    public void onClick(View v) {
        DialogFragment newFragment = null;
        switch (v.getId()) {
            case R.id.edt_vehicle_reg_date:
                newFragment = new SelectDateFragment(edt_vehicle_reg_date, "vehicle_reg_date");
                newFragment.show(mParent.getSupportFragmentManager(), "DatePicker");
                break;
            case R.id.edt_vehicle_exp_date:
                if (!edt_vehicle_reg_date.getText().toString().equals("")) {
                    newFragment = new SelectDateFragment(edt_vehicle_exp_date, "vehicle_exp_date");
                    newFragment.show(mParent.getSupportFragmentManager(), "DatePicker");
                } else {
                    Utility.setSnackBarEnglish(mParent, edt_vehicle_reg_date, "Please enter the vehicle registration date");
                }
                break;
            case R.id.edt_permit_reg_date:
                newFragment = new SelectDateFragment(edt_permit_reg_date, "permit_reg_date");
                newFragment.show(mParent.getSupportFragmentManager(), "DatePicker");
                break;
            case R.id.edt_permit_exp_date:
                if (!edt_permit_reg_date.getText().toString().equals("")) {
                    newFragment = new SelectDateFragment(edt_permit_exp_date, "permit_exp_date");
                    newFragment.show(mParent.getSupportFragmentManager(), "DatePicker");
                } else {
                    Utility.setSnackBarEnglish(mParent, edt_vehicle_reg_date, "Please enter the permit registration date");
                }
                break;
            case R.id.edt_fitness_exp_num:
                if (!edt_fitness_reg_num.getText().toString().equals("")) {
                    newFragment = new SelectDateFragment(edt_fitness_exp_num, "fitness_exp_num");
                    newFragment.show(mParent.getSupportFragmentManager(), "DatePicker");
                } else {
                    Utility.setSnackBarEnglish(mParent, edt_vehicle_reg_date, "Please enter the fitness registration date");
                }

                break;
            case R.id.edt_fitness_reg_num:
                newFragment = new SelectDateFragment(edt_fitness_reg_num, "fitness_reg_num");
                newFragment.show(mParent.getSupportFragmentManager(), "DatePicker");
                break;
            case R.id.edt_insurance_reg_date:
                newFragment = new SelectDateFragment(edt_insurance_reg_date, "insurance_reg_date");
                newFragment.show(mParent.getSupportFragmentManager(), "DatePicker");
                break;
            case R.id.edt_insurance_exp_date:
                if (!edt_insurance_reg_date.getText().toString().equals("")) {
                    newFragment = new SelectDateFragment(edt_insurance_exp_date, "insurance_exp_date");
                    newFragment.show(mParent.getSupportFragmentManager(), "DatePicker");
                } else {
                    Utility.setSnackBarEnglish(mParent, edt_insurance_reg_date, "Please enter the insurance registration date");
                }

                break;
            case R.id.edt_population_reg_date:
                newFragment = new SelectDateFragment(edt_population_reg_date, "population_reg_date");
                newFragment.show(mParent.getSupportFragmentManager(), "DatePicker");
                break;
            case R.id.edt_population_exp_date:
                if (!edt_population_reg_date.getText().toString().equals("")) {
                    newFragment = new SelectDateFragment(edt_population_exp_date, "population_exp_date");
                    newFragment.show(mParent.getSupportFragmentManager(), "DatePicker");
                } else {
                    Utility.setSnackBarEnglish(mParent, edt_population_reg_date, "Please enter the pollution registration date");
                }

                break;
            case R.id.btn_submit:
                if (validation()) {
                    showSearchAgainDialog(getActivity(),
                            Utility.getResourcesString(getActivity(), R.string.are_you_sure_entered_details_vehicle_are_correct),
                            Utility.getResourcesString(getActivity(), R.string.app_name)
                    );
                }
                break;
            case R.id.edt_vehicla_make:
                /*mVechicleModels = Utility.dialogList(mParent, null, "vehicle");*/
                Utility.showSpinnerDialog(mParent, "Vehicle Make", edt_vehicle_make, VendorRegistrationFragment.getDataToSpinner(), 1);
                break;
            case R.id.edt_seaters:
                mDialodList = Utility.dialogList(mParent, null, "seaters");
                Utility.showSpinnerDialog(mParent, "Seaters", edt_seaters, mDialodList, 1);
                break;
            case R.id.edt_vehicle_model:
                mDialodList = Utility.dialogVehicleModelList();
                Utility.showSpinnerDialog(mParent, "Vehicle Model", edt_vehicle_model, mDialodList, 1);
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


    public static class SelectDateFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        EditText editText;
        String mFrom;


        public SelectDateFragment(EditText mEditText, String mFrom) {
            this.editText = mEditText;
            this.mFrom = mFrom;
        }


        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calendar = Calendar.getInstance();
            int yy = calendar.get(Calendar.YEAR);
            int mm = calendar.get(Calendar.MONTH);
            int dd = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog mDatePickerDialog = new DatePickerDialog(getActivity(), this, yy, mm, dd);
            if (mFrom.equals("population_reg_date") || mFrom.equals("insurance_reg_date") ||
                    mFrom.equals("fitness_reg_num") || mFrom.equals("permit_reg_date") ||
                    mFrom.equals("vehicle_reg_date")) {
                mDatePickerDialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
            }
            Calendar mCalendar = Calendar.getInstance();
            if (mFrom.equals("vehicle_exp_date")) {
                mCalendar.set(Calendar.YEAR, mRegVclYear);
                mCalendar.set(Calendar.MONTH, mRegVclMonth);
                mCalendar.set(Calendar.DAY_OF_MONTH, mRegVclDay);
                mCalendar.set(Calendar.HOUR_OF_DAY, 0);
                mCalendar.set(Calendar.MINUTE, 0);
                mCalendar.set(Calendar.SECOND, 0);
                mDatePickerDialog.getDatePicker().setMinDate(mCalendar.getTimeInMillis());
            } else if (mFrom.equals("permit_exp_date")) {
                mCalendar.set(Calendar.YEAR, mRegPerYear);
                mCalendar.set(Calendar.MONTH, mRegPerMonth);
                mCalendar.set(Calendar.DAY_OF_MONTH, mRegPerDay);
                mCalendar.set(Calendar.HOUR_OF_DAY, 0);
                mCalendar.set(Calendar.MINUTE, 0);
                mCalendar.set(Calendar.SECOND, 0);
                mDatePickerDialog.getDatePicker().setMinDate(mCalendar.getTimeInMillis());
            } else if (mFrom.equals("fitness_exp_num")) {
                mCalendar.set(Calendar.YEAR, mRegFitYear);
                mCalendar.set(Calendar.MONTH, mRegFitMonth);
                mCalendar.set(Calendar.DAY_OF_MONTH, mRegFitDay);
                mCalendar.set(Calendar.HOUR_OF_DAY, 0);
                mCalendar.set(Calendar.MINUTE, 0);
                mCalendar.set(Calendar.SECOND, 0);
                mDatePickerDialog.getDatePicker().setMinDate(mCalendar.getTimeInMillis());
            } else if (mFrom.equals("insurance_exp_date")) {
                mCalendar.set(Calendar.YEAR, mRegIncYear);
                mCalendar.set(Calendar.MONTH, mRegIncMonth);
                mCalendar.set(Calendar.DAY_OF_MONTH, mRegIncDay);
                mCalendar.set(Calendar.HOUR_OF_DAY, 0);
                mCalendar.set(Calendar.MINUTE, 0);
                mCalendar.set(Calendar.SECOND, 0);
                mDatePickerDialog.getDatePicker().setMinDate(mCalendar.getTimeInMillis());
            } else if (mFrom.equals("population_exp_date")) {
                mCalendar.set(Calendar.YEAR, mRegPolYear);
                mCalendar.set(Calendar.MONTH, mRegPolMonth);
                mCalendar.set(Calendar.DAY_OF_MONTH, mRegPolDay);
                mCalendar.set(Calendar.HOUR_OF_DAY, 0);
                mCalendar.set(Calendar.MINUTE, 0);
                mCalendar.set(Calendar.SECOND, 0);
                mDatePickerDialog.getDatePicker().setMinDate(mCalendar.getTimeInMillis());
            }
            return mDatePickerDialog;
        }

        public void onDateSet(DatePicker view, int yy, int mm, int dd) {
            populateSetDate(yy, mm + 1, dd);
        }

        public void populateSetDate(int year, int month, int day) {
            //dob.setText(month+"/"+day+"/"+year);

            if (mFrom.equals("vehicle_reg_date")) {
                mRegVclDay = day;
                mRegVclYear = year;
                mRegVclMonth = month - 1;
            } else if (mFrom.equals("permit_reg_date")) {
                mRegPerDay = day;
                mRegPerYear = year;
                mRegPerMonth = month - 1;
            } else if (mFrom.equals("fitness_reg_num")) {
                mRegFitDay = day;
                mRegFitYear = year;
                mRegFitMonth = month - 1;
            } else if (mFrom.equals("insurance_reg_date")) {
                mRegIncDay = day;
                mRegIncYear = year;
                mRegIncMonth = month - 1;
            } else if (mFrom.equals("permit_reg_date")) {
                mRegPolDay = day;
                mRegPolYear = year;
                mRegPolMonth = month - 1;
            }
            editText.setText(month + "/" + day + "/" + year);

        }
    }

    public static void showSearchAgainDialog(final Context context, String msg,
                                             String title) {
        SpannableString s = new SpannableString(msg);
        Linkify.addLinks(s, Linkify.ALL);

        AlertDialog d = new AlertDialog.Builder(context)
                .setMessage(s)
                .setTitle(title)
                .setNegativeButton(R.string.no,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {
                                dialog.cancel();
                            }
                        })
                .setPositiveButton(R.string.yes,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {
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
                        }).show();

        ((TextView) d.findViewById(android.R.id.message))
                .setMovementMethod(LinkMovementMethod.getInstance());
    }

}
