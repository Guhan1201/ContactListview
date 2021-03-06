package com.example.contactlistview.Controller;

import android.util.Log;

import com.example.contactlistview.Model.ContactListDetail;
import com.example.contactlistview.Model.ProfileDetail;
import com.example.contactlistview.Model.ViewModelcallback;
import com.example.contactlistview.Model.Volleycallback;
import com.example.contactlistview.Network.NetworkManager;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//name change
// name ContactListController

public class ContactListController {

    String response;
    String id;
    public ContactListController(){

    }
    public ContactListController(String id){
        this.id = id;
    }

    public void getContactList(final ViewModelcallback viewModelcallback) {

        String url = "https://freshworks-aid816.freshdesk.com/api/v2/contacts";
        NetworkManager networkManager = new NetworkManager(url);

        networkManager.contactList(new Volleycallback() {
            @Override
            public void onSuccess(String result) {
                response = result;
                List<ContactListDetail> contactListDetailList = new ArrayList<>();


                try {
                    Gson gson = new Gson();
                    JSONArray jsonArray;
                    jsonArray = new JSONArray(response);
                    contactListDetailList = Arrays.asList(gson.fromJson(jsonArray.toString(), ContactListDetail[].class));
                    Log.e("id",(contactListDetailList.get(0).getId()));


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                viewModelcallback.onSuccess(contactListDetailList);
            }

            @Override
            public void errorResponse(String error) {
                viewModelcallback.errorResponse(error);

            }
        });

    }
    public void getProfileDetails(final ViewModelcallback viewModelcallback){
        String  url = "https://freshworks-aid816.freshdesk.com/api/contacts/"+id;
        NetworkManager networkManager = new NetworkManager(url);
        networkManager.profleDetail(new Volleycallback() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();

                ProfileDetail profileDetail = gson.fromJson(result, ProfileDetail.class);

                viewModelcallback.onSuccessObject(profileDetail);
            }

            @Override
            public void errorResponse(String error) {
                Log.e("controler",error);
                viewModelcallback.errorResponse(error);

            }
        });

    }

}
