package com.example.contactlistview.model;

public interface ContactDetailVMCallBack {
    void errorResponse(String error);
    void onSuccessObject(ContactDetail contactDetail);
}
