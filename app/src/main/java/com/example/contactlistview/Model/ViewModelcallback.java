package com.example.contactlistview.Model;

import java.util.List;

public interface ViewModelcallback {
    void onSuccess(List <ContactListDetail> contactListDetailList);
    void onSuccessObject(ProfileDetail profileDetail);
    void errorResponse(String error);
}
