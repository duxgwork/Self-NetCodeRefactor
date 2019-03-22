package com.github.kevin.netlibrary.processor.impl;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.kevin.netlibrary.callback.ICallBack;
import com.github.kevin.netlibrary.processor.IHttpProcessor;

import java.util.Map;

public class VolleyProcessor implements IHttpProcessor {

    private static RequestQueue mQueue;

    public VolleyProcessor(Context context) {
        mQueue = Volley.newRequestQueue(context);
    }

    @Override
    public void post(String url, Map<String, Object> params, final ICallBack callBack) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        callBack.onSuccess(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBack.onFailure("Volley请求失败！");
            }
        });
        mQueue.add(stringRequest);
        Log.e("IHttpProcessor", "Volley请求");
    }

}
