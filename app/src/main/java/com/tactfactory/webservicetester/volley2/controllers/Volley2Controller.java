package com.tactfactory.webservicetester.volley2.controllers;

import android.content.Context;
import android.support.annotation.NonNull;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by tactfactory on 11/12/17.
 */

public class Volley2Controller {
    private static Volley2Controller mInstance;
    private static Context mCtx;
    private RequestQueue mRequestQueue;

    private Volley2Controller(Context context) {
        mCtx = context;
        mRequestQueue = getRequestQueue();
    }

    // if an instance is already create , it will return it . if no instance was created , it will create a new one then reurn it
    public static synchronized Volley2Controller getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new Volley2Controller(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }

    public  void addToRequestQueue(@NonNull final Request request) {
        getRequestQueue().add(request);
    }

    public  void addToRequestQueueWithTag(@NonNull final Request request, String tag) {
        request.setTag(tag);
        getRequestQueue().add(request);
    }
}
