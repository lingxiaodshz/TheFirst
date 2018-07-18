package com.lingxiao.thefirst.mine.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitService {
    //    @GET("users/{user}/repos")
//    Call<List<Repo>> listRepos(@Path("user") String user);
    @GET("data/sk/101010100.html")
    Call<WeatherBean> getWeatherInfo();
}
