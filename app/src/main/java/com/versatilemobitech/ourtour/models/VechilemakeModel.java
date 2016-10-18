package com.versatilemobitech.ourtour.models;

import java.util.ArrayList;

/**
 * Created by Shankar on 10/18/2016.
 */
public class VechilemakeModel extends Model {
    private String vehicle_make_id;
    private String manufacturer;
    private ArrayList<VechilemakeModel> vechilemakeModels;

    public String getVehicle_make_id() {
        return vehicle_make_id;
    }

    public void setVehicle_make_id(String vehicle_make_id) {
        this.vehicle_make_id = vehicle_make_id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public ArrayList<VechilemakeModel> getVechilemakeModels() {
        return vechilemakeModels;
    }

    public void setVechilemakeModels(ArrayList<VechilemakeModel> vechilemakeModels) {
        this.vechilemakeModels = vechilemakeModels;
    }
}
