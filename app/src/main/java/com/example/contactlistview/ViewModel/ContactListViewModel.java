package com.example.contactlistview.ViewModel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import com.example.contactlistview.Controller.ContactListController;
import com.example.contactlistview.events.ContactDetailEvent;
import com.example.contactlistview.model.CardClickListener;
import com.example.contactlistview.model.Contact;
import com.example.contactlistview.model.ContactListVMcallback;
import java.util.List;

public class ContactListViewModel extends ViewModel implements CardClickListener {

    public final MutableLiveData<List<Contact>> contactListLiveData = new MutableLiveData<>();
    public final MutableLiveData<Boolean> errorLiveData = new MutableLiveData<>();
    //
    public final MutableLiveData<ContactDetailEvent> contactDetailEventMutableLiveData = new MutableLiveData<>();

    public ContactListViewModel() {
        loadData();
    }

    public void loadData() {
        ContactListController contactListController = new ContactListController();
        contactListController.getContactList(new ContactListVMcallback() {

            @Override
            public void onSuccess(List<Contact> contactList) {
                contactListLiveData.setValue(contactList);
            }

            @Override
            public void errorResponse(String error) {
                errorLiveData.setValue(true);
            }

        });
    }

    @Override
    public void cardClicked(Contact contact) {
        ContactDetailEvent contactDetailEvent = new ContactDetailEvent(contact.getId(), true);
        contactDetailEventMutableLiveData.setValue(contactDetailEvent);
    }
}

