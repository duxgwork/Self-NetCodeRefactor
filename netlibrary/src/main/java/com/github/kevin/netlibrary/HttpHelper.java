package com.github.kevin.netlibrary;

import com.github.kevin.netlibrary.callback.ICallBack;
import com.github.kevin.netlibrary.processor.IHttpProcessor;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

public class HttpHelper implements IHttpProcessor {
    private static IHttpProcessor mIHttpProcessor;
    private static volatile HttpHelper instance;

    private HttpHelper() {

    }

    public static HttpHelper getInstance() {
        if (instance == null) {
            synchronized (HttpHelper.class) {
                if (instance == null) {
                    instance = new HttpHelper();
                }
            }
        }
        return instance;
    }

    /**
     * 需要持有一个有房的人，针对这个例子，相当于持有某个第三方网络访问框架
     *
     * @param httpProcessor
     */
    public static void init(IHttpProcessor httpProcessor) {
        mIHttpProcessor = httpProcessor;
    }

    @Override
    public void post(String url, Map<String, Object> params, ICallBack callBack) {
        String finalUrl = appendParams(url, params);
        mIHttpProcessor.post(finalUrl, params, callBack);
    }

    private String appendParams(String url, Map<String, Object> params) {
        if (params == null || params.isEmpty()) {
            return url;
        }
        StringBuilder builder = new StringBuilder(url);
        if (builder.indexOf("?") <= 0) {
            builder.append("?");
        } else {
            if (builder.toString().endsWith("?")) {
                builder.append("&");
            }
        }
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            builder.append("&" + entry.getKey()).append("=").append(encode(entry.getValue().toString()));
        }
        return builder.toString();
    }

    private String encode(String s) {
        try {
            return URLEncoder.encode(s, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

}
