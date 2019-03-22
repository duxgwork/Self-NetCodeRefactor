package com.github.kevin.coderefactor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.github.kevin.netlibrary.HttpHelper;
import com.github.kevin.coderefactor.bean.ResponseBean;
import com.github.kevin.netlibrary.callback.HttpCallBack;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    //    private static final String URL = "http://v.juhe.cn/historyWeather/citys?province_id=2&key=bb52107206585ab074f5e59a8c73875b";
    private static final String URL = "http://v.juhe.cn/historyWeather/citys";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void requestHttp(View view) {
        Map<String, Object> params = new HashMap<>();
        params.put("province_id", 2);
        params.put("key", "bb52107206585ab074f5e59a8c73875b");
        HttpHelper.getInstance().post(URL, params, new HttpCallBack<ResponseBean>() {
            @Override
            public void onSuccess(ResponseBean result) {
                Toast.makeText(MainActivity.this, "网络请求结果：\n" + result.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(String error) {
                Toast.makeText(MainActivity.this, "网络请求结果：\n" + error, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
