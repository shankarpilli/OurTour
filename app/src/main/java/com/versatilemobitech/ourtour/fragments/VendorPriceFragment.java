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
import android.widget.TextView;

import com.versatilemobitech.ourtour.R;
import com.versatilemobitech.ourtour.activities.DashboardActivity;
import com.versatilemobitech.ourtour.asynctask.IAsyncCaller;
import com.versatilemobitech.ourtour.asynctask.ServerIntractorAsync;
import com.versatilemobitech.ourtour.interfaces.communicate;
import com.versatilemobitech.ourtour.models.Model;
import com.versatilemobitech.ourtour.models.SpinnerModel;
import com.versatilemobitech.ourtour.models.SuccessModel;
import com.versatilemobitech.ourtour.models.VechicleSuccessModel;
import com.versatilemobitech.ourtour.models.VehiclePricing;
import com.versatilemobitech.ourtour.models.VendorPriceSuccessModel;
import com.versatilemobitech.ourtour.parsers.SuccesParser;
import com.versatilemobitech.ourtour.parsers.VehicleSuccesParser;
import com.versatilemobitech.ourtour.parsers.VendorPriceSuccesParser;
import com.versatilemobitech.ourtour.utils.APIConstants;
import com.versatilemobitech.ourtour.utils.Constants;
import com.versatilemobitech.ourtour.utils.Utility;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class VendorPriceFragment extends Fragment implements View.OnClickListener, IAsyncCaller, communicate {
    public static final String TAG = "VendorPriceFragment";
    private DashboardActivity mParent;

    private Toolbar mToolbar;
    private View rootView;


    private Button btn_submit;

    private EditText et_vehicle_make;
    private EditText et_seaters;
    private EditText et_tour_packages;
    private EditText et_driver_owner_name;
    private EditText et_mobile;
    public static EditText et_amount;
    public static EditText et_extra_km;

    private SuccessModel mSuccessModel;
    private VechicleSuccessModel mVechicleSuccessModel;
    private VendorPriceSuccessModel mVendorPriceSuccessModel;

    private ArrayList<SpinnerModel> mDialodList;
    public static VehiclePricing mVehiclePricing;
    private static communicate iCommunicate;

    public VendorPriceFragment() {
        // Required empty public constructor
    }

    public static communicate getInstance() {
        return iCommunicate;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mParent = (DashboardActivity) getActivity();
        mToolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        iCommunicate = this;
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

        btn_submit = (Button) rootView.findViewById(R.id.btn_submit);

        et_vehicle_make = (EditText) rootView.findViewById(R.id.et_vehicle_make);
        et_seaters = (EditText) rootView.findViewById(R.id.et_seaters);
        et_tour_packages = (EditText) rootView.findViewById(R.id.et_tour_packages);
        et_driver_owner_name = (EditText) rootView.findViewById(R.id.et_driver_owner_name);
        et_mobile = (EditText) rootView.findViewById(R.id.et_mobile);

        et_amount = (EditText) rootView.findViewById(R.id.et_amount);
        et_extra_km = (EditText) rootView.findViewById(R.id.et_extra_km);

        btn_submit.setOnClickListener(this);
        //et_vehicle_make.setOnClickListener(this);
        //et_seaters.setOnClickListener(this);
        et_tour_packages.setOnClickListener(this);
        setTypeface();
    }

    private void setTypeface() {

        btn_submit.setTypeface(Utility.setTypeFace_Roboto_Regular(getActivity()));
        et_vehicle_make.setTypeface(Utility.setTypeFace_Roboto_Regular(getActivity()));
        et_seaters.setTypeface(Utility.setTypeFace_Roboto_Regular(getActivity()));
        et_tour_packages.setTypeface(Utility.setTypeFace_Roboto_Regular(getActivity()));
        et_driver_owner_name.setTypeface(Utility.setTypeFace_Roboto_Regular(getActivity()));
        et_mobile.setTypeface(Utility.setTypeFace_Roboto_Regular(getActivity()));
        et_amount.setTypeface(Utility.setTypeFace_Roboto_Regular(getActivity()));
        et_extra_km.setTypeface(Utility.setTypeFace_Roboto_Regular(getActivity()));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.et_vehicle_make:
                Utility.showSpinnerDialog(mParent, "Vehicle Make", et_vehicle_make, VendorRegistrationFragment.getDataToSpinner(), 1);
                break;
            case R.id.et_seaters:
                mDialodList = Utility.dialogList(mParent, null, "seaters");
                Utility.showSpinnerDialog(mParent, "Seaters", et_seaters, mDialodList, 1);
                break;
            case R.id.et_tour_packages:
                mDialodList = Utility.dialogList(mParent, null, "Tour Packages");
                Utility.showSpinnerDialog(mParent, "Tour Packages", et_tour_packages, mDialodList, 1);
                break;
            case R.id.btn_submit:
                if (validation()) {
                    mVehiclePricing = new VehiclePricing();
                    mVehiclePricing.setVehicle_id(getVechileId(et_vehicle_make.getText().toString()));
                    mVehiclePricing.setAc_price(et_tour_packages.getText().toString());
                    mVehiclePricing.setDriver_name(et_driver_owner_name.getText().toString());
                    mVehiclePricing.setDriver_bhatta("");
                    mVehiclePricing.setMobile_number(et_mobile.getText().toString());
                    postVendorInformation();
                }
                break;
        }
    }

    private String getVechileId(String vechileMake) {
        String mVechileId = "";
        for (int i = 0; i < VendorRegistrationFragment.vechilemakeModel.getVechilemakeModels().size(); i++) {
            if (VendorRegistrationFragment.vechilemakeModel.getVechilemakeModels().get(i).getManufacturer().equals(vechileMake)) {
                mVechileId = VendorRegistrationFragment.vechilemakeModel.getVechilemakeModels().get(i).getVehicle_make_id();
            }
        }
        return mVechileId;
    }

    private void postVendorInformation() {
        LinkedHashMap<String, String> paramMap = new LinkedHashMap<>();
        paramMap.put("vendor_type", VendorRegistrationFragment.vendorModel.getVendor_firm_type());
        paramMap.put("vendor_name", VendorRegistrationFragment.vendorModel.getVendor_firm_name());
        paramMap.put("vendor_owner", VendorRegistrationFragment.vendorModel.getOwner_name());
        paramMap.put("mobile_number", VendorRegistrationFragment.vendorModel.getPhone_number());
        paramMap.put("email_id", VendorRegistrationFragment.vendorModel.getEmail_id());
        paramMap.put("vendor_registration_number", VendorRegistrationFragment.vendorModel.getVendor_firm_registration_number());
        paramMap.put("bank_name", VendorRegistrationFragment.vendorModel.getBank_name());
        paramMap.put("bank_number", VendorRegistrationFragment.vendorModel.getBank_number());
        paramMap.put("ifsc_code", VendorRegistrationFragment.vendorModel.getIfsc_code());
        paramMap.put("area_name", VendorRegistrationFragment.vendorModel.getArea_name());
        paramMap.put("garage_name", VendorRegistrationFragment.vendorModel.getGarage_name());
        paramMap.put("District", VendorRegistrationFragment.vendorModel.getDistrict());
        paramMap.put("state", VendorRegistrationFragment.vendorModel.getState());
        SuccesParser mParser = new SuccesParser();
        ServerIntractorAsync serverIntractorAsync = new ServerIntractorAsync(getActivity(), Utility.getResourcesString(getActivity(),
                R.string.please_wait), true,
                APIConstants.VENDOR_INFORMATION, paramMap,
                APIConstants.REQUEST_TYPE.POST, this, mParser);
        Utility.execute(serverIntractorAsync);

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
        TextView txt_ss_added = (TextView) mResetDialog.findViewById(R.id.txt_ss_added);
        txt_ss_added.setTypeface(Utility.setTypeFace_Roboto_Regular(getActivity()));
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mResetDialog.cancel();
                mParent.onBackPressed();
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

        } else if (Utility.isValueNullOrEmpty(et_tour_packages.getText().toString().trim())) {
            Utility.setSnackBarEnglish(mParent, et_tour_packages, "Please select Tour Packages");

        } else if (Utility.isValueNullOrEmpty(et_driver_owner_name.getText().toString().trim())) {
            Utility.setSnackBarEnglish(mParent, et_driver_owner_name, "Please enter the driver or owner name");
            et_driver_owner_name.requestFocus();

        } else if (Utility.isValueNullOrEmpty(et_mobile.getText().toString().trim())) {
            Utility.setSnackBarEnglish(mParent, et_mobile, "Please enter the mobile number");
            et_mobile.requestFocus();
        } else if (et_mobile.getText().toString().trim().length() != 10) {
            Utility.setSnackBarEnglish(mParent, et_mobile, "Mobile number must me 10 characteristics");
            et_mobile.requestFocus();
        } else {
            isValidated = true;
        }
        return isValidated;
    }

    @Override
    public void onComplete(Model model) {
        if (model != null) {
            if (model.isStatus()) {
                if (model instanceof SuccessModel) {
                    mSuccessModel = (SuccessModel) model;
                    Utility.showToastMessage(getActivity(), mSuccessModel.getMessage());
                    Utility.setSharedPrefStringData(getActivity(), Constants.VENDOR_FIRM_TYPE, VendorRegistrationFragment.vendorModel.getVendor_firm_type());
                    Utility.setSharedPrefStringData(getActivity(), Constants.VENDOR_FIRM_NAME, VendorRegistrationFragment.vendorModel.getVendor_firm_name());
                    Utility.setSharedPrefStringData(getActivity(), Constants.VENDOR_E_MAIL, VendorRegistrationFragment.vendorModel.getEmail_id());
                    Utility.setSharedPrefStringData(getActivity(), Constants.VENDOR_OWNER_NAME, VendorRegistrationFragment.vendorModel.getOwner_name());
                    Utility.setSharedPrefStringData(getActivity(), Constants.VENDOR_PHONE_NUMBER, VendorRegistrationFragment.vendorModel.getPhone_number());
                    Utility.setSharedPrefStringData(getActivity(), Constants.VENDOR_FIRM_REGISTRATION_NUMBER,
                            VendorRegistrationFragment.vendorModel.getVendor_firm_registration_number());
                    Utility.setSharedPrefStringData(getActivity(), Constants.VENDOR_BANK_NAME, VendorRegistrationFragment.vendorModel.getBank_name());
                    Utility.setSharedPrefStringData(getActivity(), Constants.VENDOR_BANK_AC, VendorRegistrationFragment.vendorModel.getBank_number());
                    Utility.setSharedPrefStringData(getActivity(), Constants.VENDOR_BANK_BRANCH, VendorRegistrationFragment.vendorModel.getBank_branch());
                    Utility.setSharedPrefStringData(getActivity(), Constants.VENDOR_BANK_IFSC_CODE, VendorRegistrationFragment.vendorModel.getIfsc_code());
                    Utility.setSharedPrefStringData(getActivity(), Constants.VENDOR_AREA_NAME, VendorRegistrationFragment.vendorModel.getArea_name());
                    Utility.setSharedPrefStringData(getActivity(), Constants.VENDOR_GARAGE_NAME, VendorRegistrationFragment.vendorModel.getGarage_name());
                    Utility.setSharedPrefStringData(getActivity(), Constants.VENDOR_DISTRICT, VendorRegistrationFragment.vendorModel.getDistrict());
                    Utility.setSharedPrefStringData(getActivity(), Constants.VENDOR_STATE, VendorRegistrationFragment.vendorModel.getState());
                    postVehicleInformation();
                } else if (model instanceof VechicleSuccessModel) {
                    mVechicleSuccessModel = (VechicleSuccessModel) model;
                    Utility.showToastMessage(getActivity(), mVechicleSuccessModel.getMessage());
                    postVendorPrice();
                } else if (model instanceof VendorPriceSuccessModel) {
                    mVendorPriceSuccessModel = (VendorPriceSuccessModel) model;
                    //Utility.setSharedPrefStringData(getActivity(), Constants.VENDOR_TYPE, );
                    showSubmitDialog();
                }
            }
        }
    }

    private void postVendorPrice() {
        LinkedHashMap<String, String> paramMap = new LinkedHashMap<>();
        paramMap.put("vehicle_id", mVehiclePricing.getVehicle_id());
        paramMap.put("ac_price", mVehiclePricing.getAc_price());
        paramMap.put("non_ac_price", mVehiclePricing.getNon_ac_price());
        paramMap.put("airport_drop_pick", mVehiclePricing.getAirport_drop_pick());
        paramMap.put("driver_bhatta", mVehiclePricing.getDriver_bhatta());
        paramMap.put("driver_name", mVehiclePricing.getDriver_name());
        paramMap.put("mobile_number", mVehiclePricing.getMobile_number());
        VendorPriceSuccesParser mParser = new VendorPriceSuccesParser();
        ServerIntractorAsync serverIntractorAsync = new ServerIntractorAsync(getActivity(), Utility.getResourcesString(getActivity(),
                R.string.please_wait), true,
                APIConstants.VEHICLE_PRICING, paramMap,
                APIConstants.REQUEST_TYPE.POST, this, mParser);
        Utility.execute(serverIntractorAsync);
    }

    private void postVehicleInformation() {
        LinkedHashMap<String, String> paramMap = new LinkedHashMap<>();
        paramMap.put("vendor_id", VehicleRegistrationFragment.vehicleRegistration.getVendor_id());
        paramMap.put("vehicle_make", VehicleRegistrationFragment.vehicleRegistration.getVehicle_make());
        paramMap.put("vehicle_model", VehicleRegistrationFragment.vehicleRegistration.getVehicle_model());
        paramMap.put("vehicle_type", VehicleRegistrationFragment.vehicleRegistration.getVehicle_type());
        paramMap.put("seater", VehicleRegistrationFragment.vehicleRegistration.getSeater());
        paramMap.put("vehicle_registration_number", VehicleRegistrationFragment.vehicleRegistration.getVehicle_registration_number());
        paramMap.put("vehicle_registration_date", VehicleRegistrationFragment.vehicleRegistration.getVehicle_registration_date());
        paramMap.put("vehicle_registration_expiry", VehicleRegistrationFragment.vehicleRegistration.getVehicle_registration_expiry());
        paramMap.put("permit_number", VehicleRegistrationFragment.vehicleRegistration.getPermit_number());
        paramMap.put("permit_date", VehicleRegistrationFragment.vehicleRegistration.getPermit_date());
        paramMap.put("permit_expiry", VehicleRegistrationFragment.vehicleRegistration.getPermit_expiry());
        paramMap.put("fitness_number", VehicleRegistrationFragment.vehicleRegistration.getFitness_number());
        paramMap.put("fitness_date", VehicleRegistrationFragment.vehicleRegistration.getFitness_date());
        paramMap.put("fitness_expiry", VehicleRegistrationFragment.vehicleRegistration.getFitness_expiry());
        paramMap.put("insurance_number", VehicleRegistrationFragment.vehicleRegistration.getInsurance_number());
        paramMap.put("insurance_date", VehicleRegistrationFragment.vehicleRegistration.getInsurance_date());
        paramMap.put("insurance_expiry", VehicleRegistrationFragment.vehicleRegistration.getInsurance_expiry());
        paramMap.put("pollution_number", VehicleRegistrationFragment.vehicleRegistration.getPollution_number());
        paramMap.put("pollution_date", VehicleRegistrationFragment.vehicleRegistration.getPollution_date());
        paramMap.put("pollution_expiry", VehicleRegistrationFragment.vehicleRegistration.getPollution_expiry());
        VehicleSuccesParser mParser = new VehicleSuccesParser();
        ServerIntractorAsync serverIntractorAsync = new ServerIntractorAsync(getActivity(), Utility.getResourcesString(getActivity(),
                R.string.please_wait), true,
                APIConstants.VEHICLE_INFORMATION, paramMap,
                APIConstants.REQUEST_TYPE.POST, this, mParser);
        Utility.execute(serverIntractorAsync);
    }

    @Override
    public void sendData(String make, String seaters) {
        if (VehicleRegistrationFragment.vehicleRegistration != null) {
            et_vehicle_make.setText("" + VehicleRegistrationFragment.vehicleRegistration.getVehicle_make());
            et_seaters.setText("" + VehicleRegistrationFragment.vehicleRegistration.getSeater());
        }
    }
}
