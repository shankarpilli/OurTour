package com.versatilemobitech.ourtour.parsers;

import android.content.Context;

import com.versatilemobitech.ourtour.models.Model;
import com.versatilemobitech.ourtour.models.SuccessModel;

import org.json.JSONObject;

/**
 * Created by shankar on 10/16/2016.
 */

public class SuccesParser implements Parser {
    @Override
    public Model parseResponse(String response, Context context) {
        SuccessModel successModel = new SuccessModel();
        if (response != null) {
            successModel.setStatus(true);
            try {
                JSONObject jsonObject = new JSONObject(response);
                successModel.setMessage(jsonObject.optString("message"));
                JSONObject jsonDataObject = jsonObject.optJSONObject("data");
                successModel.setVendor_id(jsonDataObject.optString("vendor_id"));
            } catch (Exception e) {
                successModel.setStatus(false);
            }
            /*successModel.setStatus(true);*/
        } else {
            successModel.setStatus(false);
            successModel.setMessage("Opps..! Some problem with loading data");
        }
        return successModel;
    }
}
