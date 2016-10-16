package com.versatilemobitech.ourtour.parsers;

import android.content.Context;

import com.versatilemobitech.ourtour.models.DistrictModel;
import com.versatilemobitech.ourtour.models.Model;
import com.versatilemobitech.ourtour.models.StateModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by shankar on 10/16/2016.
 */

public class StatesParser implements Parser {
    @Override
    public Model parseResponse(String response, Context context) {
        StateModel stateModel = new StateModel();
        if (response != null) {
            stateModel.setStatus(true);
            try {
                JSONArray jsonArray = new JSONArray(response);
                ArrayList<StateModel> stateModels = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.optJSONObject(i);
                    StateModel mStateModel = new StateModel();
                    mStateModel.setState(jsonObject.optString("state"));
                    mStateModel.setState_id(jsonObject.optString("state_id"));
                    stateModels.add(mStateModel);
                }
                stateModel.setStateModels(stateModels);
            } catch (JSONException e) {
                stateModel.setStatus(false);
            }
        } else {
            stateModel.setStatus(false);
            stateModel.setMessage("Opps..! Some problem with loading districts");
        }
        return stateModel;
    }
}
