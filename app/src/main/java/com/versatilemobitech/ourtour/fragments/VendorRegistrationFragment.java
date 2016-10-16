package com.versatilemobitech.ourtour.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.versatilemobitech.ourtour.R;
import com.versatilemobitech.ourtour.activities.DashboardActivity;
import com.versatilemobitech.ourtour.models.VendorModel;
import com.versatilemobitech.ourtour.utils.Utility;

/**
 * Created by Shankar Pilli.
 */
public class VendorRegistrationFragment extends Fragment implements View.OnClickListener {
    private EditText et_vendor;
    private EditText et_email;
    private EditText et_phone_number;
    private EditText et_owner;
    private EditText et_registration_number;
    private EditText et_bank;
    private EditText et_bank_acc;
    private EditText et_branch;
    private EditText et_ifsc;
    private EditText et_area_name;
    private EditText et_guarage;
    private EditText et_district;
    private EditText et_state;

    private Button btn_next;
    private NestedScrollView scroll;

    public static final String TAG = "VendorRegistrationFragment";
    private Toolbar mToolbar;
    private DashboardActivity mParent;
    private View rootView;
    public static VendorModel vendorModel;

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
        rootView = inflater.inflate(R.layout.fragment_vendor_registration, container, false);
        initUI();
        return rootView;
    }

    private void initUI() {
        scroll = (NestedScrollView) rootView.findViewById(R.id.scrollView);

        et_vendor = (EditText) rootView.findViewById(R.id.et_vendor);
        et_email = (EditText) rootView.findViewById(R.id.et_email);
        et_phone_number = (EditText) rootView.findViewById(R.id.et_phone_number);
        et_registration_number = (EditText) rootView.findViewById(R.id.et_registration_number);
        et_bank = (EditText) rootView.findViewById(R.id.et_bank);
        et_bank_acc = (EditText) rootView.findViewById(R.id.et_bank_acc);
        et_branch = (EditText) rootView.findViewById(R.id.et_branch);
        et_owner = (EditText) rootView.findViewById(R.id.et_owner);
        et_ifsc = (EditText) rootView.findViewById(R.id.et_ifsc);
        et_area_name = (EditText) rootView.findViewById(R.id.et_area_name);
        et_guarage = (EditText) rootView.findViewById(R.id.et_guarage);
        et_district = (EditText) rootView.findViewById(R.id.et_district);
        et_state = (EditText) rootView.findViewById(R.id.et_state);
        btn_next = (Button) rootView.findViewById(R.id.btn_next);

        btn_next.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_next:
                if (isValidFields()) {
                    vendorModel = new VendorModel();
                    vendorModel.setVendor_name(et_vendor.getText().toString());
                    vendorModel.setVendor_owner(et_owner.getText().toString());
                    vendorModel.setMobile_number(et_phone_number.getText().toString());
                    vendorModel.setEmail_id(et_email.getText().toString());
                    vendorModel.setVendor_registration_number(et_registration_number.getText().toString());
                    vendorModel.setBank_name(et_bank.getText().toString());
                    vendorModel.setBank_number(et_bank_acc.getText().toString());
                    vendorModel.setIfsc_code(et_ifsc.getText().toString());
                    vendorModel.setArea_name(et_area_name.getText().toString());
                    vendorModel.setGarage_name(et_guarage.getText().toString());
                    vendorModel.setDistrict(et_district.getText().toString());
                    vendorModel.setState(et_state.getText().toString());

                    AddCarFragment.tabLayout.getTabAt(1).select();
                }
                break;
        }
    }

    public boolean isValidFields() {
        boolean isValidated = false;
        if (Utility.isValueNullOrEmpty(et_vendor.getText().toString().trim())) {
            Utility.setSnackBarEnglish(mParent, et_vendor, "Please enter the vendor firm name");
            et_vendor.requestFocus();
        } else if (Utility.isValueNullOrEmpty(et_email.getText().toString().trim())) {
            Utility.setSnackBarEnglish(mParent, et_email, "Please enter email");
            et_email.requestFocus();
        }  else if (!et_email.getText().toString().trim().matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z]+)*(\\.[A-Za-z]{2,})$")) {
            Utility.setSnackBarEnglish(mParent, et_email, "Please enter valid email");
            et_email.requestFocus();
        } else if (Utility.isValueNullOrEmpty(et_phone_number.getText().toString().trim())) {
            Utility.setSnackBarEnglish(mParent, et_phone_number, "Please enter phone number");
            et_phone_number.requestFocus();
        } else if (Utility.isValueNullOrEmpty(et_owner.getText().toString().trim())) {
            Utility.setSnackBarEnglish(mParent, et_owner, "Please enter owner name");
            et_phone_number.requestFocus();
        } else if (Utility.isValueNullOrEmpty(et_registration_number.getText().toString().trim())) {
            Utility.setSnackBarEnglish(mParent, et_registration_number, "Please enter the registration number");
            et_registration_number.requestFocus();
        } else if (Utility.isValueNullOrEmpty(et_bank.getText().toString().trim())) {
            Utility.setSnackBarEnglish(mParent, et_bank, "Please enter the bank name");
            et_bank.requestFocus();
        } else if (Utility.isValueNullOrEmpty(et_bank_acc.getText().toString().trim())) {
            Utility.setSnackBarEnglish(mParent, et_bank_acc, "Please enter the bank account number");
            et_bank_acc.requestFocus();
        } else if (Utility.isValueNullOrEmpty(et_branch.getText().toString().trim())) {
            Utility.setSnackBarEnglish(mParent, et_branch, "Please enter the branch");
            et_branch.requestFocus();
        } else if (Utility.isValueNullOrEmpty(et_owner.getText().toString().trim())) {
            Utility.setSnackBarEnglish(mParent, et_owner, "Please enter the owner name");
            et_owner.requestFocus();
        } else if (Utility.isValueNullOrEmpty(et_ifsc.getText().toString().trim())) {
            Utility.setSnackBarEnglish(mParent, et_ifsc, "Please enter the ifsc code");
            et_ifsc.requestFocus();
        } else if (Utility.isValueNullOrEmpty(et_area_name.getText().toString().trim())) {
            Utility.setSnackBarEnglish(mParent, et_area_name, "Please enter the area name");
            et_area_name.requestFocus();
        } else if (Utility.isValueNullOrEmpty(et_guarage.getText().toString().trim())) {
            Utility.setSnackBarEnglish(mParent, et_guarage, "Please enter the guarage name");
            et_guarage.requestFocus();
        } else if (Utility.isValueNullOrEmpty(et_district.getText().toString().trim())) {
            Utility.setSnackBarEnglish(mParent, et_district, "Please enter the district");
            et_district.requestFocus();
        } else if (Utility.isValueNullOrEmpty(et_state.getText().toString().trim())) {
            Utility.setSnackBarEnglish(mParent, et_state, "Please enter the state");
            et_state.requestFocus();
        } else {
            isValidated = true;
        }
        return isValidated;


    }
}
