package com.arupkumar.tasktwo.Volley.Implementation;

import com.android.volley.VolleyError;

/**
 * Created by Arup Kumar Pramanik on 04/10/2018.
 */

public interface IRequestResponseCallBack<T> {
    void onSuccess(T... t);
    void onError(VolleyError volleyError);
}
