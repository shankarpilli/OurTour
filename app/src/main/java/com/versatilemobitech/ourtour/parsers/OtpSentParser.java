package com.versatilemobitech.ourtour.parsers;

import android.content.Context;

import com.versatilemobitech.ourtour.models.Model;
import com.versatilemobitech.ourtour.models.OtpSentModel;
import com.versatilemobitech.ourtour.models.SuccessModel;

import org.json.JSONObject;

/**
 * Created by shankar on 10/16/2016.
 */

public class OtpSentParser implements Parser {
    @Override
    public Model parseResponse(String response, Context context) {
        OtpSentModel mOtpSentModel = new OtpSentModel();
        if (response != null) {
            mOtpSentModel.setStatus(true);
            try {
                JSONObject jsonObject = new JSONObject(response);
                mOtpSentModel.setMessage(jsonObject.optString("message"));
            } catch (Exception e) {
                mOtpSentModel.setStatus(false);
            }
            /*successModel.setStatus(true);*/
        } else {
            mOtpSentModel.setStatus(false);
            mOtpSentModel.setMessage("Opps..! Some problem with loading data");
        }
        return mOtpSentModel;
    }
}
