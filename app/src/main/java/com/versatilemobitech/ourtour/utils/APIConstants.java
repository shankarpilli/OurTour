package com.versatilemobitech.ourtour.utils;

/**
 * Created by shankar on 10/16/2016.
 */

public class APIConstants {

    public static final String GSON = "Gson";
    public static final String JSON = "Json";
    public static final String STATUS = "status";

    public enum REQUEST_TYPE {
        GET, POST, MULTIPART_GET, MULTIPART_POST, DELETE, PUT, PATCH
    }

    public static final String BASE_URL = "http://ourtour.in/api/service/";
    public static final String STATES = BASE_URL + "states";
    public static final String DISTRICTS = BASE_URL + "districts";
    public static final String VEHICLE_MANUFACTURER = BASE_URL + "vehiclemanufacturer";
    public static final String VENDOR_INFORMATION = BASE_URL + "vendorinformation";
    public static final String VEHICLE_INFORMATION = BASE_URL + "vehicleinformation";
    public static final String VEHICLE_PRICING = BASE_URL + "vehiclepricing";

}