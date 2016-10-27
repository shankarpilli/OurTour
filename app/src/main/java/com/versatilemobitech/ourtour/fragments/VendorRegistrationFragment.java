package com.versatilemobitech.ourtour.fragments;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.versatilemobitech.ourtour.R;
import com.versatilemobitech.ourtour.activities.DashboardActivity;
import com.versatilemobitech.ourtour.asynctask.IAsyncCaller;
import com.versatilemobitech.ourtour.asynctask.ServerIntractorAsync;
import com.versatilemobitech.ourtour.models.DistrictModel;
import com.versatilemobitech.ourtour.models.Model;
import com.versatilemobitech.ourtour.models.SpinnerModel;
import com.versatilemobitech.ourtour.models.StateModel;
import com.versatilemobitech.ourtour.models.VechilemakeModel;
import com.versatilemobitech.ourtour.models.VendorModel;
import com.versatilemobitech.ourtour.parsers.DistrictsParser;
import com.versatilemobitech.ourtour.parsers.StatesParser;
import com.versatilemobitech.ourtour.parsers.VehicleMakeParser;
import com.versatilemobitech.ourtour.utils.APIConstants;
import com.versatilemobitech.ourtour.utils.Utility;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Created by Shankar Pilli.
 */
public class VendorRegistrationFragment extends Fragment implements View.OnClickListener, IAsyncCaller {
    public static EditText et_vendor;
    private static EditText et_email;
    private static EditText et_phone_number;
    private static EditText et_owner;
    public static EditText et_registration_number;
    private static EditText et_bank;
    private static EditText et_bank_acc;
    private static EditText et_branch;
    private static EditText et_ifsc;
    private static EditText et_area_name;
    private static EditText et_guarage;
    private static AutoCompleteTextView et_district;
    private static AutoCompleteTextView et_state;
    private static EditText et_firm_individual;


    private Button btn_next;
    private NestedScrollView scroll;

    public static final String TAG = "VendorRegistrationFragment";
    private Toolbar mToolbar;
    private DashboardActivity mParent;
    private View rootView;
    public static VendorModel vendorModel;
    private static DistrictModel districtModel;
    private static StateModel stateModel;
    public static VechilemakeModel vechilemakeModel;

    private ArrayList<SpinnerModel> mDialogList;


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
        et_district = (AutoCompleteTextView) rootView.findViewById(R.id.et_district);
        et_state = (AutoCompleteTextView) rootView.findViewById(R.id.et_state);
        et_firm_individual = (EditText) rootView.findViewById(R.id.et_firm_individual);
        btn_next = (Button) rootView.findViewById(R.id.btn_next);


        getDistrictData();

        btn_next.setOnClickListener(this);
/*        et_district.setOnClickListener(this);*/
        /*et_state.setOnClickListener(this);*/
        et_firm_individual.setOnClickListener(this);
        setTypeface();

    }

    private void setTypeface() {
        et_vendor.setTypeface(Utility.setTypeFace_Roboto_Regular(getActivity()));
        et_email.setTypeface(Utility.setTypeFace_Roboto_Regular(getActivity()));
        et_phone_number.setTypeface(Utility.setTypeFace_Roboto_Regular(getActivity()));
        et_registration_number.setTypeface(Utility.setTypeFace_Roboto_Regular(getActivity()));
        et_bank.setTypeface(Utility.setTypeFace_Roboto_Regular(getActivity()));
        et_bank_acc.setTypeface(Utility.setTypeFace_Roboto_Regular(getActivity()));
        et_branch.setTypeface(Utility.setTypeFace_Roboto_Regular(getActivity()));
        et_owner.setTypeface(Utility.setTypeFace_Roboto_Regular(getActivity()));
        et_ifsc.setTypeface(Utility.setTypeFace_Roboto_Regular(getActivity()));
        et_area_name.setTypeface(Utility.setTypeFace_Roboto_Regular(getActivity()));
        et_guarage.setTypeface(Utility.setTypeFace_Roboto_Regular(getActivity()));
        et_district.setTypeface(Utility.setTypeFace_Roboto_Regular(getActivity()));
        et_state.setTypeface(Utility.setTypeFace_Roboto_Regular(getActivity()));
        et_firm_individual.setTypeface(Utility.setTypeFace_Roboto_Regular(getActivity()));
        btn_next.setTypeface(Utility.setTypeFace_Roboto_Regular(getActivity()));
    }

    private void getDistrictData() {
        LinkedHashMap<String, String> paramMap = new LinkedHashMap<>();
        DistrictsParser mParser = new DistrictsParser();
        ServerIntractorAsync serverIntractorAsync = new ServerIntractorAsync(getActivity(), Utility.getResourcesString(getActivity(),
                R.string.please_wait), false,
                APIConstants.DISTRICTS, paramMap,
                APIConstants.REQUEST_TYPE.GET, this, mParser);
        Utility.execute(serverIntractorAsync);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.et_district:
                mDialogList = Utility.dialogList(mParent, districtModel, "districts");
                Utility.showSpinnerDialog(mParent, "Districts", et_district, mDialogList, 1);
                break;
            case R.id.et_state:
                mDialogList = Utility.dialogList(mParent, stateModel, "states");
                Utility.showSpinnerDialog(mParent, "States", et_state, mDialogList, 1);
                break;
            case R.id.et_firm_individual:
                mDialogList = Utility.dialogVendorTypeList();
                Utility.showSpinnerDialog(mParent, "Vendor Type", et_firm_individual, mDialogList, 1);
                break;
            case R.id.btn_next:
                if (isValidFields()) {
                    showSearchAgainDialog(getActivity(),
                            Utility.getResourcesString(getActivity(), R.string.are_you_sure_entered_details_are_correct),
                            Utility.getResourcesString(getActivity(), R.string.app_name)
                    );
                }
                break;
        }
    }

    private static String getDistrictId(String district) {
        String mDistrictId = "";
        for (int i = 0; i < districtModel.getDistrictModels().size(); i++) {
            if (districtModel.getDistrictModels().get(i).getDistrict().equals(district)) {
                mDistrictId = districtModel.getDistrictModels().get(i).getDistrict_id();
            }
        }
        return mDistrictId;
    }

    private static String getStateID(String state) {
        String mStateID = "";
        for (int i = 0; i < stateModel.getStateModels().size(); i++) {
            if (stateModel.getStateModels().get(i).getState().equals(state)) {
                mStateID = stateModel.getStateModels().get(i).getState_id();
            }
        }
        return mStateID;
    }

    public static String getVehicleID(String vehicle) {
        String mStateID = "";
        for (int i = 0; i < vechilemakeModel.getVechilemakeModels().size(); i++) {
            if (vechilemakeModel.getVechilemakeModels().get(i).getManufacturer().equals(vehicle)) {
                mStateID = vechilemakeModel.getVechilemakeModels().get(i).getVehicle_make_id();
            }
        }
        return mStateID;
    }

    public boolean isValidFields() {
        boolean isValidated = false;
        if (Utility.isValueNullOrEmpty(et_firm_individual.getText().toString().trim())) {
            Utility.setSnackBarEnglish(mParent, et_firm_individual, "Please select the vendor type");
            et_firm_individual.requestFocus();
        } else if (Utility.isValueNullOrEmpty(et_vendor.getText().toString().trim())) {
            Utility.setSnackBarEnglish(mParent, et_vendor, "Please enter the vendor firm name");
            et_vendor.requestFocus();
        } else if (Utility.isValueNullOrEmpty(et_email.getText().toString().trim())) {
            Utility.setSnackBarEnglish(mParent, et_email, "Please enter email");
            et_email.requestFocus();
        } else if (!et_email.getText().toString().trim().matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z]+)*(\\.[A-Za-z]{2,})$")) {
            Utility.setSnackBarEnglish(mParent, et_email, "Please enter valid email");
            et_email.requestFocus();
        } else if (Utility.isValueNullOrEmpty(et_owner.getText().toString().trim())) {
            Utility.setSnackBarEnglish(mParent, et_owner, "Please enter owner name");
            et_owner.requestFocus();
        } else if (Utility.isValueNullOrEmpty(et_phone_number.getText().toString().trim())) {
            Utility.setSnackBarEnglish(mParent, et_phone_number, "Please enter phone number");
            et_phone_number.requestFocus();
        } else if (et_phone_number.getText().toString().trim().length() != 10) {
            Utility.setSnackBarEnglish(mParent, et_phone_number, "Phone number must me 10 characteristics");
            et_phone_number.requestFocus();
        } else if (et_registration_number.getVisibility() == View.VISIBLE && Utility.isValueNullOrEmpty(et_registration_number.getText().toString().trim())) {
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

    @Override
    public void onComplete(Model model) {
        if (model != null) {
            if (model.isStatus()) {
                if (model instanceof DistrictModel) {
                    districtModel = (DistrictModel) model;
                    ArrayList<String> mList = Utility.dialogList(mParent, districtModel);
                    autoComplete(mList, "district");
                    getStatesData();
                } else if (model instanceof StateModel) {
                    stateModel = (StateModel) model;
                    ArrayList<String> mList = Utility.dialogList(mParent, stateModel);
                    autoComplete(mList, "state");
                    getVehicleMakeDetails();
                } else if (model instanceof VechilemakeModel) {
                    vechilemakeModel = (VechilemakeModel) model;
                    // setDataToSpinner();
                }
            }
        }
    }

    public static ArrayList<SpinnerModel> getDataToSpinner() {
        ArrayList<SpinnerModel> mList = new ArrayList<>();
        for (int i = 0; i < vechilemakeModel.getVechilemakeModels().size(); i++) {
            mList.add(new SpinnerModel(vechilemakeModel.getVechilemakeModels().get(i).getManufacturer()));
        }
        return mList;
    }

    private void getVehicleMakeDetails() {
        LinkedHashMap<String, String> paramMap = new LinkedHashMap<>();
        VehicleMakeParser mParser = new VehicleMakeParser();
        ServerIntractorAsync serverIntractorAsync = new ServerIntractorAsync(getActivity(), Utility.getResourcesString(getActivity(),
                R.string.please_wait), false,
                APIConstants.VEHICLE_MANUFACTURER, paramMap,
                APIConstants.REQUEST_TYPE.GET, this, mParser);
        Utility.execute(serverIntractorAsync);
    }

    private void getStatesData() {
        LinkedHashMap<String, String> paramMap = new LinkedHashMap<>();
        StatesParser mParser = new StatesParser();
        ServerIntractorAsync serverIntractorAsync = new ServerIntractorAsync(getActivity(), Utility.getResourcesString(getActivity(),
                R.string.please_wait), false,
                APIConstants.STATES, paramMap,
                APIConstants.REQUEST_TYPE.GET, this, mParser);
        Utility.execute(serverIntractorAsync);
    }

    private void autoComplete(ArrayList<String> mList, String mFrom) {
        if (mList != null && mList.size() > 0) {
            ArrayAdapter<String> adapter =
                    new ArrayAdapter<String>(mParent, android.R.layout.simple_list_item_1, mList);
            if (mFrom.equalsIgnoreCase("state")) {
                et_state.setAdapter(adapter);
            } else {
                et_district.setAdapter(adapter);
            }
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
                                vendorModel = new VendorModel();
                                vendorModel.setVendor_firm_name(et_vendor.getText().toString());
                                vendorModel.setOwner_name(et_owner.getText().toString());
                                vendorModel.setPhone_number(et_phone_number.getText().toString());
                                vendorModel.setEmail_id(et_email.getText().toString());
                                vendorModel.setVendor_firm_registration_number(et_registration_number.getText().toString());
                                vendorModel.setBank_name(et_bank.getText().toString());
                                vendorModel.setBank_number(et_bank_acc.getText().toString());
                                vendorModel.setIfsc_code(et_ifsc.getText().toString());
                                vendorModel.setArea_name(et_area_name.getText().toString());
                                vendorModel.setGarage_name(et_guarage.getText().toString());
                                vendorModel.setDistrict(getDistrictId(et_district.getText().toString()));
                                vendorModel.setState(getStateID(et_state.getText().toString()));
                                vendorModel.setState(getStateID(et_firm_individual.getText().toString()));
                                AddCarFragment.tabLayout.getTabAt(1).select();
                            }
                        }).show();

        ((TextView) d.findViewById(android.R.id.message))
                .setMovementMethod(LinkMovementMethod.getInstance());
    }

}
