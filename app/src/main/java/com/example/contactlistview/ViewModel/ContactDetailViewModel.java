package com.example.contactlistview.ViewModel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.example.contactlistview.Controller.ContactDetailController;
import com.example.contactlistview.model.ContactDetail;
import com.example.contactlistview.model.ContactDetailVMCallBack;

public class ContactDetailViewModel extends ViewModel {
    public final String id;
    public final MutableLiveData<ContactDetail> contactDetailMutableLD = new MutableLiveData<>();
    public final MutableLiveData<Boolean> errorMessage = new MutableLiveData<>();

    public ContactDetailViewModel(String id) {
        this.id = id;
        fetchDetail();
    }

    private void fetchDetail() {
        ContactDetailController contactDetailController = new ContactDetailController(id);
        contactDetailController.getContsctDetails(new ContactDetailVMCallBack() {
            @Override
            public void errorResponse(String error) {
                errorMessage.setValue(true);
            }

            @Override
            public void onSuccessObject(ContactDetail contactDetail) {
                contactDetailMutableLD.setValue(contactDetail);
            }
        });
    }

    public static class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {
        private final String id;

        public ViewModelFactory(String id) {
            this.id = id;
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            return (T) new ContactDetailViewModel(id);
        }

    }
}
