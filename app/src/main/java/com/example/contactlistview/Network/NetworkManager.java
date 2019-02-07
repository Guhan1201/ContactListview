package com.example.contactlistview.Network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.contactlistview.ContactList;
import com.example.contactlistview.Model.Volleycallback;

import java.util.HashMap;
import java.util.Map;

public class NetworkManager {

    String url;
    Map<String, String> header = new HashMap<String, String>();

    public NetworkManager(String url) {
        header.put("Authorization", "Basic cGY2QW5BZm1vQVRlWmZhVmhDaDM6eA==");
        this.url = url;
    }

    public void contactList(final Volleycallback callback) {


        if (isInternetConnection()) {

            RequestQueue queue = Volley.newRequestQueue(ContactList.getAppContext());

            final NetworkRequest stringRequest = new NetworkRequest(header, Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // responseout = response;
                            Log.e("response", response);
                            callback.onSuccess(response);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("catch", error+"");

                }
            });
            queue.add(stringRequest);
        } else{
            callback.errorResponse("error");
        }

    }

    public void profleDetail(final Volleycallback callback){
        if(isInternetConnection()) {
            RequestQueue queue = (RequestQueue) Volley.newRequestQueue(ContactList.getAppContext());
            final NetworkRequest stringRequest = new NetworkRequest(header, Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // responseout = response;
                            Log.i("response 2 ", response);
                            //callback.onSuccess(response);
                            callback.onSuccess(response);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("catch", "error no response");

                }
            });
            queue.add(stringRequest);
        }
        else{
            callback.errorResponse("error");
            //Toast.makeText(ContactList.getAppContext(),"No Internet connection",Toast.LENGTH_LONG).show();
        }
    }

    public boolean isInternetConnection()
    {
        boolean enabled = true;

        ConnectivityManager connectivityManager = (ConnectivityManager) ContactList.getAppContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();

        if ((info == null || !info.isConnected() || !info.isAvailable())) {

            return false;
        } else {
            return true;
        }

    }
}
