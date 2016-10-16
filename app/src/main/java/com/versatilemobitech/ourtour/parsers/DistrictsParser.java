package com.versatilemobitech.ourtour.parsers;

import android.content.Context;

import com.versatilemobitech.ourtour.models.DistrictModel;
import com.versatilemobitech.ourtour.models.Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by shankar on 10/16/2016.
 */

public class DistrictsParser implements Parser {
    @Override
    public Model parseResponse(String response, Context context) {
        DistrictModel districtModel = new DistrictModel();
        if (response != null) {
            districtModel.setStatus(true);
            try {
                JSONArray jsonArray = new JSONArray(response);
                ArrayList<DistrictModel> districtModels = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.optJSONObject(i);
                    DistrictModel districtModel1 = new DistrictModel();
                    districtModel1.setDistrict(jsonObject.optString("district"));
                    districtModel1.setDistrict_id(jsonObject.optString("district_id"));
                    districtModel1.setState_id(jsonObject.optString("state_id"));
                    districtModels.add(districtModel1);
                }
                districtModel.setDistrictModels(districtModels);
            } catch (JSONException e) {
                districtModel.setStatus(false);
            }
        } else {
            districtModel.setStatus(false);
            districtModel.setMessage("Opps..! Some problem with loading districts");
        }
        return districtModel;
    }
}
