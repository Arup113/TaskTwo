package com.arupkumar.tasktwo.Controller;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.arupkumar.tasktwo.Volley.VolleyManager;

/**
 * Created by Arup Kumar Pramanik on 04/10/2018.
 */


public class AppController extends Application {

    public static final String TAG = AppController.class.getSimpleName();

    private static AppController mInstance;

    public static Context getContext() {
        return getInstance().getApplicationContext();
    }

    public static synchronized AppController getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        //Initialize volley http library
        VolleyManager.init(this);
    }

}