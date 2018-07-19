package com.lingxiao.thefirst.mine.retrofit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lingxiao.thefirst.R;
import com.lingxiao.thefirst.base.BaseActivity;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitTestActivity extends BaseActivity {
    @BindView(R.id.tv_content)
    TextView mTvContent;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_retrofit_test;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        setTitle("RetrofitTest");
    }

    @Override
    protected void handleData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.weather.com.cn/")
                //这句话要加上，否则在json转化时会出问题
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitService service = retrofit.create(RetrofitService.class);

        Call<WeatherBean> call = service.getWeatherInfo();

        //异步请求
        call.enqueue(new Callback<WeatherBean>() {
            @Override
            public void onResponse(Call<WeatherBean> call, Response<WeatherBean> response) {
                WeatherBean weatherBean = response.body();
                mTvContent.setText(new Gson().toJson(weatherBean));
            }

            @Override
            public void onFailure(Call<WeatherBean> call, Throwable t) {
                showToast("获取天气信息失败");
            }
        });

        // 发送网络请求（同步）
        // Response<WeatherBean> response = call.execute();
    }
}
