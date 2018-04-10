package com.arupkumar.tasktwo.Volley;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.arupkumar.tasktwo.Config.AppConfig;
import com.arupkumar.tasktwo.Model.UserDetails;
import com.arupkumar.tasktwo.Volley.Implementation.IRequestResponseCallBack;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Arup Kumar Pramanik on 04/10/2018.
 */

public class OrderListRequest {
    private static final String TAG = OrderListRequest.class.getName().toString();
    private String requestUrl;
    private String requestUnqTag;
    private JSONObject requestParam;
    private IRequestResponseCallBack callBack;

    public OrderListRequest(String requestUnqTag, JSONObject requestParam, IRequestResponseCallBack callBack) {
        this.requestUrl = AppConfig.USER_LIST_URL;
        this.requestUnqTag = requestUnqTag;
        this.requestParam = requestParam;
        this.callBack = callBack;
    }

    public void doIt(){
        Map<String, String> params = new HashMap<>();
        Map<String, String> headers = new HashMap<>();

        ObjectRequest<UserDetails> userDetailsObjectRequest = new ObjectRequest<>(Request.Method.POST, AppConfig.USER_LIST_URL, headers, params,
                new Response.Listener<UserDetails>() {
                    @Override
                    public void onResponse(UserDetails response) {
                        if (response.isDemo() == true) {
                            UserDetails userDetailsList = response;
                            callBack.onSuccess(userDetailsList);
                        }
                        else{
                            UserDetails userDetailsList = null;
                            callBack.onSuccess(userDetailsList);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                callBack.onError(volleyError);
                volleyError.printStackTrace();
            }
        }, UserDetails.class);

        VolleyManager.getInstance().addToRequestQueue(userDetailsObjectRequest, requestUnqTag);
    }
}
