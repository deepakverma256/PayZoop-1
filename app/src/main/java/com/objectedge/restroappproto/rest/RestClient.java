package com.objectedge.restroappproto.rest;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by michaelkatkov on 8/4/16.
 */
public class RestClient {

    public static final String BASE_URL = "https://prototype-app-demo.herokuapp.com/";
    //public static final String BASE_URL = "http://oeis-d41:8080/";
    private final Retrofit mRetrofit;


    public RestClient() {
        mRetrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL).build();
    }


    public <S> S createService(Class<S> serviceClass) {
        return mRetrofit.create(serviceClass);
    }
}
