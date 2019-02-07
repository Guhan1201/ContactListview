package com.example.contactlistview.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.google.gson.annotations.SerializedName;

public class ProfileDetail {

    private int color;
    ColorGenerator generator = ColorGenerator.MATERIAL;

    @SerializedName("id")
    private String id;
    @SerializedName("address")
    private String address;
    @SerializedName("company_id")
    private String CompanyID;
    @SerializedName("email")
    private String email;
    @SerializedName("language")
    private String language;
    @SerializedName("name")
    private String name;
    @SerializedName("mobile")
    private String mobile;
    @SerializedName("time_zone")
    private String time_zone;


    public ColorGenerator getGenerator() {
        return generator;
    }

    public void setGenerator(ColorGenerator generator) {
        this.generator = generator;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCompany_id() {
        return CompanyID;
    }

    public void setCompany_id(String company_id) {
        this.CompanyID = company_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTime_zone() {
        return time_zone;
    }

    public void setTime_zone(String time_zone) {
        this.time_zone = time_zone;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }


}
