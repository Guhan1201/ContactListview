package com.example.contactlistview.Controller;

import android.util.Log;

import com.example.contactlistview.model.Contact;
import com.example.contactlistview.model.ContactListVMcallback;
import com.example.contactlistview.model.Volleycallback;
import com.example.contactlistview.Network.NetworkManager;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//name change
// name ContactsController

public class ContactListController {

    String response;
    String id;

    public ContactListController(){

    }
    public ContactListController(String id){
        this.id = id;
    }

    public void getContactList(final ContactListVMcallback contactListVMcallback) {

        String url = "https://freshworks-aid816.freshdesk.com/api/v2/contacts";
        NetworkManager networkManager = new NetworkManager(url);

        networkManager.contactList(new Volleycallback() {
            @Override
            public void onSuccess(String result) {
                response = result;
                List<Contact> contactListDetail = new ArrayList<>();
                try {
                    Gson gson = new Gson();
                    JSONArray jsonArray;
                    jsonArray = new JSONArray(response);
                    contactListDetail = Arrays.asList(gson.fromJson(jsonArray.toString(), Contact[].class));
                    Log.e("id",(contactListDetail.get(0).getId()));


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                contactListVMcallback.onSuccess(contactListDetail);
            }

            @Override
            public void errorResponse(String error) {
                contactListVMcallback.errorResponse(error);

            }
        });

    }


}
