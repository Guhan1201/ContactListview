package com.example.contactlistview.Network;

import android.support.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.Map;

public class NetworkRequest extends StringRequest {


    public Map<String, String> headers;

    public NetworkRequest(Map<String, String> header, int method, String url, Response.Listener<String> listener, @Nullable Response.ErrorListener errorListener) {

        super(method, url, listener, errorListener);
        this.headers = header;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return headers != null ? headers : super.getHeaders();
    }

}
