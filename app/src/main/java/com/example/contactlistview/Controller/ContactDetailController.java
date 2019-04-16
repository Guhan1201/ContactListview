package com.example.contactlistview.Controller;

import android.util.Log;

import com.example.contactlistview.Network.NetworkManager;
import com.example.contactlistview.model.ContactDetail;
import com.example.contactlistview.model.ContactDetailVMCallBack;
import com.example.contactlistview.model.Volleycallback;
import com.google.gson.Gson;

public class ContactDetailController {
    public final String id;

    public ContactDetailController(String id) {
        this.id = id;
    }

    public void getContsctDetails(final ContactDetailVMCallBack contactListVMcallback) {
        String url = "https://freshworks-aid816.freshdesk.com/api/contacts/" + id;
        NetworkManager networkManager = new NetworkManager(url);
        networkManager.profleDetail(new Volleycallback() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                ContactDetail contactDetail = gson.fromJson(result, ContactDetail.class);
                contactListVMcallback.onSuccessObject(contactDetail);
            }

            @Override
            public void errorResponse(String error) {
                Log.e("controler", error);
                contactListVMcallback.errorResponse(error);
            }
        });

    }
}
