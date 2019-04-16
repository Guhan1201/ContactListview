package com.example.contactlistview.ViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.util.Log;
import android.widget.Toast;
import com.example.contactlistview.ContactsApp;
import com.example.contactlistview.Controller.ContactListController;
import com.example.contactlistview.events.ContactDetailEvent;
import com.example.contactlistview.model.CardClickListener;
import com.example.contactlistview.model.Contact;
import com.example.contactlistview.model.ViewModelcallback;

import java.util.List;

//ContactViewModel
//RULE NO 1
//VIEWMODEL SHOULD KNOW NOTHING ABOUT ACTIVITY or FRAGMENT or ADAPTER, or any Kind of a view, INTENT.

public class ContactViewModel extends ViewModel implements CardClickListener {

    //TODO ALl variables should be private & final unless there is no other choice.
    public final String id;
    //CHange name please. to contactlist.
    public final MutableLiveData<List<Contact>> contactListLiveData = new MutableLiveData<>();
    public final MutableLiveData<Boolean> errorLiveData  = new MutableLiveData<Boolean>();
    public final MutableLiveData<ContactDetailEvent> contactDetailEventMutableLiveData = new MutableLiveData<ContactDetailEvent>();


    public ContactViewModel(String id) {
        this.id = id;
        loadData();
        // this ld, will be observed by UI.    WANT TO BE DONE
    }

    //TODO Please remove the function
    public void fetchdata() {
        if (contactListLiveData == null) {
            loadData();
        }
    }

    public void loadData() {
        ContactListController contactListController = new ContactListController();
        contactListController.getContactList(new ViewModelcallback() {

            @Override
            public void onSuccess(List<Contact> contactList) {
                contactListLiveData.setValue(contactList);
            }

            //TODO Remove profileDetail Handling here.     DONE
            //TODO Name change from ProfileDetail to ContactDetail.     DONE

            @Override
            public void errorResponse(String error) {
                errorLiveData.setValue(true);
            }
        });

    }
    //Create a folder events.
    //Class ContactDetailEvent() { String id, boolean navigate;
    //}
    //IN THIS VM, MLD<ContactDetailEvent>
    //

    @Override
    public void cardClicked(Contact contact) {
        //LD Set. openDetailevent.
        //COntactDE evn = new CDE(cd.id, true)
        //ld.setvalu(evn)
        //
        //TODO Use live data for navigation. boolean LD. set value to true. observe in UI, and if true, navigate to activity.
        //navigateToProfileLd.setValue(true)    TO BE DONE
        Toast.makeText(ContactsApp.getAppContext(), contact.getEmail().toString(), Toast.LENGTH_LONG).show();
        ContactDetailEvent contactDetailEvent = new ContactDetailEvent(contact.getId(),true);
        contactDetailEventMutableLiveData.setValue(contactDetailEvent);

    }
    public static class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {
        private final String id;

        public ViewModelFactory(String id) {
            this.id = id;
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            return (T) new ContactViewModel(id);
        }

    }


}

