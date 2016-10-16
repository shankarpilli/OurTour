package com.versatilemobitech.ourtour.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.versatilemobitech.ourtour.R;
import com.versatilemobitech.ourtour.activities.DashboardActivity;
import com.versatilemobitech.ourtour.utils.Utility;

/**
 * Created by Shankar Pilli.
 */
public class VendorRegistrationFragment extends Fragment  implements View.OnClickListener{
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

        et_vendor = (EditText)rootView.findViewById(R.id.et_vendor);
        et_email = (EditText)rootView.findViewById(R.id.et_email);
        et_phone_number = (EditText)rootView.findViewById(R.id.et_phone_number);
        et_registration_number = (EditText)rootView.findViewById(R.id.et_registration_number);
        et_bank = (EditText) rootView.findViewById(R.id.et_bank);
        et_bank_acc = (EditText)rootView.findViewById(R.id.et_bank_acc);
        et_branch = (EditText)rootView.findViewById(R.id.et_branch);
        et_owner = (EditText)rootView.findViewById(R.id.et_owner);
        et_ifsc = (EditText)rootView.findViewById(R.id.et_ifsc);
        et_area_name = (EditText)rootView.findViewById(R.id.et_area_name);
        et_guarage = (EditText)rootView.findViewById(R.id.et_guarage);
        et_district = (EditText)rootView.findViewById(R.id.et_district);
        et_state = (EditText)rootView.findViewById(R.id.et_state);
        btn_next = (Button) rootView.findViewById(R.id.btn_next);

        btn_next.setOnClickListener( this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_next:
                if (validation()) {
                    Utility.showToastMessage(mParent, "" + validation());
                } else {
                    Utility.showToastMessage(mParent, "validation fail");
                }
                break;
        }



    }
    public boolean validation(){
        boolean isValidated = false;
        if (Utility.isValueNullOrEmpty(et_vendor.getText().toString().trim())) {
            Utility.setSnackBarEnglish(mParent,et_vendor,"please enter the vendor firm name");

        } else if (Utility.isValueNullOrEmpty(et_email.getText().toString().trim())) {
            Utility.setSnackBarEnglish(mParent,et_email,"please enter email");

        } else if (Utility.isValueNullOrEmpty(et_phone_number.getText().toString().trim())) {
            Utility.setSnackBarEnglish(mParent,et_phone_number,"please enter phonenumber");

        } else if (Utility.isValueNullOrEmpty(et_registration_number.getText().toString().trim())) {
            Utility.setSnackBarEnglish(mParent,et_registration_number,"please enter the registration number");
        } else if (Utility.isValueNullOrEmpty(et_bank.getText().toString().trim())) {
            Utility.setSnackBarEnglish(mParent,et_bank,"please enter the bank name");

        } else if (Utility.isValueNullOrEmpty(et_bank_acc.getText().toString().trim())) {
            Utility.setSnackBarEnglish(mParent,et_bank_acc,"please enter the bank account number");

        } else if (Utility.isValueNullOrEmpty(et_branch.getText().toString().trim())) {
            Utility.setSnackBarEnglish(mParent,et_branch,"please enter the branch");

        } else if (Utility.isValueNullOrEmpty(et_owner.getText().toString().trim())) {
            Utility.setSnackBarEnglish(mParent,et_owner,"please enter the owner name");

        } else if (Utility.isValueNullOrEmpty(et_ifsc.getText().toString().trim())) {
            Utility.setSnackBarEnglish(mParent,et_ifsc,"please enter the ifsc code");

        } else if (Utility.isValueNullOrEmpty(et_area_name.getText().toString().trim())) {
            Utility.setSnackBarEnglish(mParent,et_area_name,"please enter the area name");

        } else if (Utility.isValueNullOrEmpty(et_guarage.getText().toString().trim())) {
            Utility.setSnackBarEnglish(mParent,et_guarage,"please enter the guarage name");

        } else if (Utility.isValueNullOrEmpty(et_district.getText().toString().trim())) {
            Utility.setSnackBarEnglish(mParent,et_district,"please enter the district");

        } else if (Utility.isValueNullOrEmpty(et_state.getText().toString().trim())) {
            Utility.setSnackBarEnglish(mParent,et_state,"please enter the state");

        }else {
            isValidated = true;
        }
        return isValidated;



    }
}
