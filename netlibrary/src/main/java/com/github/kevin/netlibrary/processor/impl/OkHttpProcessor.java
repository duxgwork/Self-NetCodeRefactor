package com.github.kevin.netlibrary.processor.impl;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.github.kevin.netlibrary.callback.ICallBack;
import com.github.kevin.netlibrary.processor.IHttpProcessor;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpProcessor implements IHttpProcessor {
    private OkHttpClient mOkHttpClient;
    private Handler mHandler;

    public OkHttpProcessor() {
        mOkHttpClient = new OkHttpClient();
        mHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void post(String url, Map<String, Object> params, final ICallBack callBack) {
        RequestBody requestBody = appendBody(params);
        final Request request = new Request.Builder().url(url).post(requestBody).build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.onFailure("OkHttp请求失败！");
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (response.isSuccessful()) {
                            String str = null;
                            try {
                                str = response.body().string();
                                callBack.onSuccess(str);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            callBack.onFailure("OkHttp请求失败！");
                        }
                    }
                });
            }
        });
        Log.e("IHttpProcessor", "OkHttp请求");
    }

    private RequestBody appendBody(Map<String, Object> params) {
        FormBody.Builder body = new FormBody.Builder();
        if (params == null || params.isEmpty()) {
            return body.build();
        }
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            body.add(entry.getKey(), entry.getValue().toString());
        }
        return body.build();
    }

}
