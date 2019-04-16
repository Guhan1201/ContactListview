package com.example.contactlistview.model;

import java.util.List;

public interface ContactListVMcallback {
    void onSuccess(List<Contact> contactList);
    void errorResponse(String error);
}
