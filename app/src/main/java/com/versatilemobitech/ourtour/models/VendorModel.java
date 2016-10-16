package com.versatilemobitech.ourtour.models;

import java.io.Serializable;

/**
 * Created by Madhu on 16-Oct-16.
 */

public class VendorModel implements Serializable {

    private String vendor_name;
    private String vendor_owner;
    private String mobile_number;
    private String email_id;
    private String vendor_registration_number;
    private String bank_name;
    private String bank_number;
    private String ifsc_code;
    private String area_name;
    private String garage_name;
    private String District;
    private String state;

    public String getVendor_name() {
        return vendor_name;
    }

    public void setVendor_name(String vendor_name) {
        this.vendor_name = vendor_name;
    }

    public String getVendor_owner() {
        return vendor_owner;
    }

    public void setVendor_owner(String vendor_owner) {
        this.vendor_owner = vendor_owner;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public String getVendor_registration_number() {
        return vendor_registration_number;
    }

    public void setVendor_registration_number(String vendor_registration_number) {
        this.vendor_registration_number = vendor_registration_number;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getBank_number() {
        return bank_number;
    }

    public void setBank_number(String bank_number) {
        this.bank_number = bank_number;
    }

    public String getIfsc_code() {
        return ifsc_code;
    }

    public void setIfsc_code(String ifsc_code) {
        this.ifsc_code = ifsc_code;
    }

    public String getArea_name() {
        return area_name;
    }

    public void setArea_name(String area_name) {
        this.area_name = area_name;
    }

    public String getGarage_name() {
        return garage_name;
    }

    public void setGarage_name(String garage_name) {
        this.garage_name = garage_name;
    }

    public String getDistrict() {
        return District;
    }

    public void setDistrict(String district) {
        District = district;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
