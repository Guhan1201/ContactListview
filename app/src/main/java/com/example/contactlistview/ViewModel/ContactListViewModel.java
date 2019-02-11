package com.example.contactlistview.ViewModel;

import android.util.Log;

import com.example.contactlistview.Controller.ContactListController;
import com.example.contactlistview.Model.ContactListDetail;
import com.example.contactlistview.Model.ProfileDetail;
import com.example.contactlistview.Model.UIcallback;
import com.example.contactlistview.Model.ViewModelcallback;

import java.util.ArrayList;
import java.util.List;

//ContactViewModel
public class ContactListViewModel {
    String id;

    public ContactListViewModel(String id) {
        this.id = id;
    }
    public ContactListViewModel(){

    }

    public void fetchdata(final UIcallback callback){
       List<ContactListDetail> contactListDetailList = new ArrayList<>();

       ContactListController contactListController = new ContactListController();
       contactListController.getContactList(new ViewModelcallback() {
           String response;

           @Override
           public void onSuccess(List<ContactListDetail> contactListDetailList) {
               contactListDetailList = contactListDetailList;
               callback.onSuccess(contactListDetailList);
           }

           @Override
           public void onSuccessObject(ProfileDetail profileDetail) {

           }

           @Override
           public void errorResponse(String error) {
               callback.errorResponse(error);


           }
       });
   }
   public void getProfile(final UIcallback callback){
       ContactListController contactListController = new ContactListController(id);
       contactListController.getProfileDetails(new ViewModelcallback() {
           @Override
           public void onSuccess(List<ContactListDetail> contactListDetailList) {

           }

           @Override
           public void onSuccessObject(ProfileDetail profileDetail) {
               Log.e("name parsed", profileDetail.getName());
               callback.onSuccessObject(profileDetail);

           }

           @Override
           public void errorResponse(String error) {
               callback.errorResponse(error);
           }
       });

   }
}
