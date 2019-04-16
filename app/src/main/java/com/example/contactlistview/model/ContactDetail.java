package com.example.contactlistview.model;

import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.widget.ImageView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.google.gson.annotations.SerializedName;

public class ContactDetail extends ObservableField<ContactDetail> {

    public int color;
    ColorGenerator generator = ColorGenerator.MATERIAL;

    @SerializedName("id")
    public String id;
    @SerializedName("address")
    public String address;
    @SerializedName("company_id")
    public String CompanyID;
    @SerializedName("email")
    public String email;
    @SerializedName("language")
    public String language;
    @SerializedName("name")
    public String name;
    @SerializedName("mobile")
    public String mobile;
    @SerializedName("time_zone")
    public String time_zone;


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

    @BindingAdapter({"android:src"})
    public static void setImageDrawable(ImageView imageView, String name){
        if(name!=null){
            String letter = String.valueOf(name.charAt(0));
            ColorGenerator generator = ColorGenerator.MATERIAL;
            int color = generator.getRandomColor();
            TextDrawable drawable = TextDrawable.builder()
                    .buildRound(letter, color);
            imageView.setImageDrawable(drawable);
        }
    }

}
