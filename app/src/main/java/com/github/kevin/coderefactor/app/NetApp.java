package com.github.kevin.coderefactor.app;

import android.app.Application;

import com.github.kevin.netlibrary.HttpHelper;
import com.github.kevin.netlibrary.processor.impl.OkHttpProcessor;
import com.github.kevin.netlibrary.processor.impl.VolleyProcessor;

public class NetApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //实例化Volley框架
        HttpHelper.init(new VolleyProcessor(this));
//        //实例化OkHttp框架
//        HttpHelper.init(new OkHttpProcessor());
    }

}
