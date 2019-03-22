package com.github.kevin.netlibrary.callback;

import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.lang.reflect.ParameterizedType;


public abstract class HttpCallBack<T> implements ICallBack {

    public abstract void onSuccess(T result);

    @Override
    public void onSuccess(String result) {
        //将网络访问框架得到的数据转换成json对象
        Gson gson = new Gson();
        Class<?> clazz = analysisClassInfo(this);
        T obj = (T) gson.fromJson(result, clazz);
        onSuccess(obj);
    }

    @Override
    public void onFailure(String error) {

    }

    private Class<?> analysisClassInfo(Object object) {
        //可以得到包含原始类型、参数化类型、类型变量、基本类型
        Type genType = object.getClass().getGenericSuperclass();
        Type[] actualTypeAguments = ((ParameterizedType) genType).getActualTypeArguments();
        return (Class<?>) actualTypeAguments[0]; //拿到泛型T
    }

}
