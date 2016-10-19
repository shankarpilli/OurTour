package com.versatilemobitech.ourtour.parsers;

import android.content.Context;

import com.versatilemobitech.ourtour.models.Model;
import com.versatilemobitech.ourtour.models.SuccessModel;
import com.versatilemobitech.ourtour.models.VechicleSuccessModel;

import org.json.JSONObject;

/**
 * Created by shankar on 10/16/2016.
 */

public class VehicleSuccesParser implements Parser {
    @Override
    public Model parseResponse(String response, Context context) {
        VechicleSuccessModel mVechicleSuccessModel = new VechicleSuccessModel();
        if (response != null) {
            mVechicleSuccessModel.setStatus(true);
            try {
                JSONObject jsonObject = new JSONObject(response);
                mVechicleSuccessModel.setMessage(jsonObject.optString("message"));
            } catch (Exception e) {

            }
        } else {
            mVechicleSuccessModel.setStatus(false);
            mVechicleSuccessModel.setMessage("Opps..! Some problem with loading districts");
        }
        return mVechicleSuccessModel;
    }
}
