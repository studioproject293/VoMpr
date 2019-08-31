package com.jslps.vompr;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jslps.vompr.services.ApiServices;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Constant {
    public static final String API_BASE_URL = "http://swalekha.in/webServiceModalPopup.asmx/";
    //Screen Id
    public static final int HOME_FRAGMENT = 101;
    public static final int VOFRAGMENT = 102;
    public static final int MEMBER_LIST_FRAGMENT = 103;
    public static final int ADD_MEMBER_FRAGMENT = 104;
    public static final int setTitle=1111;
    private static ApiServices apiServices;
    public static ApiServices getAPIService() {
        Gson gson = new GsonBuilder().setLenient().create();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //comment in live build and uncomment in uat
        builder.interceptors().add(interceptor);

        builder.connectTimeout(30, TimeUnit.SECONDS);
        builder.readTimeout(90, TimeUnit.SECONDS);

        OkHttpClient client = builder.build();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constant.API_BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).client(client).build();
        apiServices = retrofit.create(ApiServices.class);
        return apiServices;
    }

}
