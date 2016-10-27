package com.versatilemobitech.ourtour.models;

import java.io.Serializable;

/**
 * Created by Madhu on 16-Oct-16.
 */

public class VendorModel implements Serializable {

    private String vendor_firm_type;
    private String vendor_firm_name;
    private String email_id;
    private String owner_name;
    private String phone_number;
    private String vendor_firm_registration_number;
    private String bank_name;
    private String bank_number;
    private String bank_branch;
    private String ifsc_code;
    private String area_name;
    private String garage_name;
    private String District;
    private String state;

    public String getVendor_firm_name() {
        return vendor_firm_name;
    }

    public void setVendor_firm_name(String vendor_firm_name) {
        this.vendor_firm_name = vendor_firm_name;
    }

    public String getOwner_name() {
        return owner_name;
    }

    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public String getVendor_firm_registration_number() {
        return vendor_firm_registration_number;
    }

    public void setVendor_firm_registration_number(String vendor_firm_registration_number) {
        this.vendor_firm_registration_number = vendor_firm_registration_number;
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

    public String getVendor_firm_type() {
        return vendor_firm_type;
    }

    public void setVendor_firm_type(String vendor_firm_type) {
        this.vendor_firm_type = vendor_firm_type;
    }

    public String getBank_branch() {
        return bank_branch;
    }

    public void setBank_branch(String bank_branch) {
        this.bank_branch = bank_branch;
    }
}
