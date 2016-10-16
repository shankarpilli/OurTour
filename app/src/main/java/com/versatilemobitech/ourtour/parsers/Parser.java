package com.versatilemobitech.ourtour.parsers;


import android.content.Context;

import com.versatilemobitech.ourtour.models.Model;


/**
 * Created by Shankar Rao on 10/16/2016.
 */
public interface Parser<T extends Model> {

    T parseResponse(String response, Context context);

    //public ArrayList<Model> parse(String resp) throws JSONException;
}