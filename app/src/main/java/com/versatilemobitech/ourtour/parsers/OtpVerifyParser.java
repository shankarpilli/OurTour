package com.versatilemobitech.ourtour.parsers;

import android.content.Context;

import com.versatilemobitech.ourtour.models.Model;
import com.versatilemobitech.ourtour.models.OtpSentModel;
import com.versatilemobitech.ourtour.models.OtpVerifyModel;

import org.json.JSONObject;

/**
 * Created by shankar on 10/16/2016.
 */

public class OtpVerifyParser implements Parser {
    @Override
    public Model parseResponse(String response, Context context) {
        OtpVerifyModel mOtpVerifyModel = new OtpVerifyModel();
        if (response != null) {
            // mOtpVerifyModel.setStatus(true);
            try {
                JSONObject jsonObject = new JSONObject(response);
                mOtpVerifyModel.setMessage(jsonObject.optString("message"));
                if (jsonObject.optString("status").equalsIgnoreCase("0")) {
                    mOtpVerifyModel.setStatus(false);
                } else {
                    mOtpVerifyModel.setStatus(true);
                }
            } catch (Exception e) {
                mOtpVerifyModel.setStatus(false);
            }
            /*successModel.setStatus(true);*/
        } else {
            mOtpVerifyModel.setStatus(false);
            mOtpVerifyModel.setMessage("Opps..! Some problem with loading data");
        }
        return mOtpVerifyModel;
    }
}
