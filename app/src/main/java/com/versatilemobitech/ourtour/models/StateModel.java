package com.versatilemobitech.ourtour.models;

import java.util.ArrayList;

/**
 * Created by mydu on 16-Oct-16.
 */

public class StateModel extends Model{
    private String  state_id;
    private String state;
    private ArrayList<StateModel> stateModels;

    public String getState_id() {
        return state_id;
    }

    public void setState_id(String state_id) {
        this.state_id = state_id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public ArrayList<StateModel> getStateModels() {
        return stateModels;
    }

    public void setStateModels(ArrayList<StateModel> stateModels) {
        this.stateModels = stateModels;
    }
}
