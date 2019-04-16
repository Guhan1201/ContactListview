package com.example.contactlistview.model;

import java.util.List;

public interface ViewModelcallback {
    void onSuccess(List <Contact> contactListDetail);
    void errorResponse(String error);

    void onSuccessObject(ContactDetail contactDetail);
}
