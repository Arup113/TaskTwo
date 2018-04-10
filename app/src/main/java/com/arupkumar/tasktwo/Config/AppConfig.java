package com.arupkumar.tasktwo.Config;

import android.content.Context;
import com.android.volley.Request;
import com.arupkumar.tasktwo.Controller.AppController;
import com.arupkumar.tasktwo.R;

/**
 * Created by Arup Kumar Pramanik on 04/10/2018.
 */

public class AppConfig {
    private static final Context context = AppController.getContext();
    public static final String USER_LIST_URL = context.getString(R.string.josn_download_link);
    public static final String USER_MEN_URL = context.getString(R.string.user_men_url);
    public static final String USER_WOMEN_URL = context.getString(R.string.user_women_url);
}