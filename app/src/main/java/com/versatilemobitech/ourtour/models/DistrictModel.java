package com.versatilemobitech.ourtour.models;

import java.util.ArrayList;

/**
 * Created by Madhu on 16-Oct-16.
 */

public class DistrictModel extends Model {
    private String district_id;
    private String state_id;
    private String district;
    private ArrayList<DistrictModel> districtModels;

    public String getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(String district_id) {
        this.district_id = district_id;
    }

    public String getState_id() {
        return state_id;
    }

    public void setState_id(String state_id) {
        this.state_id = state_id;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public ArrayList<DistrictModel> getDistrictModels() {
        return districtModels;
    }

    public void setDistrictModels(ArrayList<DistrictModel> districtModels) {
        this.districtModels = districtModels;
    }
}
