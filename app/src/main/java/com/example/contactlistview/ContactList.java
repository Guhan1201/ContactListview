package com.example.contactlistview;

import android.app.Application;
import android.content.Context;

public class ContactList extends Application {

    private static Context context;

    public void onCreate() {
        super.onCreate();
        ContactList.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return ContactList.context;
    }

}
