package com.versatilemobitech.ourtour.parsers;

import android.content.Context;

import com.versatilemobitech.ourtour.models.DistrictModel;
import com.versatilemobitech.ourtour.models.Model;
import com.versatilemobitech.ourtour.models.VechilemakeModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by shankar on 10/16/2016.
 */

public class VehicleMakeParser implements Parser {
    @Override
    public Model parseResponse(String response, Context context) {
        VechilemakeModel mVechilemakeModel = new VechilemakeModel();
        if (response != null) {
            mVechilemakeModel.setStatus(true);
            try {
                JSONArray jsonArray = new JSONArray(response);
                ArrayList<VechilemakeModel> vechilemakeModels = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.optJSONObject(i);
                    VechilemakeModel mVechilemakeModel1 = new VechilemakeModel();
                    mVechilemakeModel1.setVehicle_make_id(jsonObject.optString("vehicle_make_id"));
                    mVechilemakeModel1.setManufacturer(jsonObject.optString("manufacturer"));
                    vechilemakeModels.add(mVechilemakeModel1);
                }
                mVechilemakeModel.setVechilemakeModels(vechilemakeModels);
            } catch (JSONException e) {
                mVechilemakeModel.setStatus(false);
            }
        } else {
            mVechilemakeModel.setStatus(false);
            mVechilemakeModel.setMessage("Opps..! Some problem with loading districts");
        }
        return mVechilemakeModel;
    }
}
