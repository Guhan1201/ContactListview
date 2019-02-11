package com.example.contactlistview.Model;

import java.util.List;

public interface UIcallback {
    void onSuccess(List<ContactListDetail> contactListDetailList);
    void onSuccessObject(ProfileDetail profileDetail);
    void errorResponse(String error);
    void onClick(int postion);
}
