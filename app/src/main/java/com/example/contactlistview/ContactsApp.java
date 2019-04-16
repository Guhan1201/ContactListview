package com.example.contactlistview;

import android.app.Application;
import android.content.Context;

public class ContactsApp extends Application {

    private static Context context;

    public void onCreate() {
        super.onCreate();
        ContactsApp.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return ContactsApp.context;
    }

}
