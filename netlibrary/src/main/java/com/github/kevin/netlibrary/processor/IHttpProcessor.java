package com.github.kevin.netlibrary.processor;

import com.github.kevin.netlibrary.callback.ICallBack;

import java.util.Map;

public interface IHttpProcessor {

    //该类相当于房产中介公司，拥有买房能力。对应网络请求，具有访问网络的能力。
    void post(String url, Map<String, Object> params, ICallBack callBack);


}
