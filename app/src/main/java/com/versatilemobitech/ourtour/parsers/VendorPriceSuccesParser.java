package com.versatilemobitech.ourtour.parsers;

import android.content.Context;

import com.versatilemobitech.ourtour.models.Model;
import com.versatilemobitech.ourtour.models.VechicleSuccessModel;
import com.versatilemobitech.ourtour.models.VendorPriceSuccessModel;

import org.json.JSONObject;

/**
 * Created by shankar on 10/16/2016.
 */

public class VendorPriceSuccesParser implements Parser {
    @Override
    public Model parseResponse(String response, Context context) {
        VendorPriceSuccessModel mVendorPriceSuccessModel = new VendorPriceSuccessModel();
        if (response != null) {
            mVendorPriceSuccessModel.setStatus(true);
            try {
                JSONObject jsonObject = new JSONObject(response);
                mVendorPriceSuccessModel.setMessage(jsonObject.optString("message"));
            } catch (Exception e) {

            }
        } else {
            mVendorPriceSuccessModel.setStatus(false);
            mVendorPriceSuccessModel.setMessage("Opps..! Some problem with loading districts");
        }
        return mVendorPriceSuccessModel;
    }
}
