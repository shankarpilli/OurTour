package com.versatilemobitech.ourtour.fragments;


import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.versatilemobitech.ourtour.R;
import com.versatilemobitech.ourtour.activities.DashboardActivity;
import com.versatilemobitech.ourtour.models.SpinnerModel;
import com.versatilemobitech.ourtour.utils.Utility;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class VendorPriceFragment extends Fragment implements View.OnClickListener{
    public static final String TAG = "VendorPriceFragment";
    private DashboardActivity mParent;

    private Toolbar mToolbar;
    private View rootView;


    private Button btn_submit;

    private EditText et_vehicle_make;
    private EditText et_seaters;
    private EditText et_kms_price;
    private EditText et_driver_owner_name;
    private EditText et_mobile;

    private ArrayList<SpinnerModel> mDialodList;
    public VendorPriceFragment() {
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
        rootView = inflater.inflate(R.layout.fragment_vendor_price, container, false);
        initUI();
        return rootView;
    }

    private void initUI() {

        btn_submit = (Button)rootView.findViewById(R.id.btn_submit);

        et_vehicle_make = (EditText)rootView.findViewById(R.id.et_vehicle_make);
        et_seaters = (EditText)rootView.findViewById(R.id.et_seaters);
        et_kms_price = (EditText)rootView.findViewById(R.id.et_kms_price);
        et_driver_owner_name = (EditText)rootView.findViewById(R.id.et_driver_owner_name);
        et_mobile = (EditText)rootView.findViewById(R.id.et_mobile);

        btn_submit.setOnClickListener(this);
        et_vehicle_make.setOnClickListener(this);
        et_seaters.setOnClickListener(this);
        et_kms_price.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.et_vehicle_make:
                Utility.showSpinnerDialog(mParent, "Vehicle Make", et_vehicle_make, VendorRegistrationFragment.getDataToSpinner(), 1);
                break;
            case R.id.et_seaters:
                mDialodList = Utility.dialogList(mParent, null, "seaters");
                Utility.showSpinnerDialog(mParent, "Seaters", et_seaters, mDialodList, 1);
                break;
            case R.id.et_kms_price:
                mDialodList = Utility.dialogList(mParent, null, "kmsprice");
                Utility.showSpinnerDialog(mParent, "kmsprice", et_kms_price, mDialodList, 1);
                break;
            case R.id.btn_submit:
                if(validation()){
                    showSubmitDialog();
                }
                break;
        }
    }
    private void showSubmitDialog() {
       final Dialog mResetDialog = new Dialog(getActivity());
        mResetDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mResetDialog.setContentView(R.layout.dialog_submit);
        mResetDialog.getWindow().setBackgroundDrawable(
                new ColorDrawable(android.graphics.Color.TRANSPARENT));
        mResetDialog.setCanceledOnTouchOutside(false);
        mResetDialog.setCancelable(false);


        final Button btn_ok = (Button) mResetDialog.findViewById(R.id.btn_ok);
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mResetDialog.cancel();
            }
        });

        mResetDialog.show();
    }

    private boolean validation() {
        boolean isValidated = false;
        if (Utility.isValueNullOrEmpty(et_vehicle_make.getText().toString().trim())) {
            Utility.setSnackBarEnglish(mParent, et_vehicle_make, "Please enter the vehicle make");

        } else if (Utility.isValueNullOrEmpty(et_seaters.getText().toString().trim())) {
            Utility.setSnackBarEnglish(mParent, et_seaters, "Please select the number of seaters");

        } else if (Utility.isValueNullOrEmpty(et_kms_price.getText().toString().trim())) {
            Utility.setSnackBarEnglish(mParent, et_kms_price, "Please enter the seaters");

        } else if (Utility.isValueNullOrEmpty(et_driver_owner_name.getText().toString().trim())) {
            Utility.setSnackBarEnglish(mParent, et_driver_owner_name, "Please enter the driver or owner name");
            et_driver_owner_name.requestFocus();

        } else if (Utility.isValueNullOrEmpty(et_mobile.getText().toString().trim())) {
            Utility.setSnackBarEnglish(mParent, et_mobile, "Please enter the mobile number");
            et_mobile.requestFocus();

        } else {
            isValidated = true;
        }
        return isValidated;
    }
}
