package com.jslps.vompr.services;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiServices {

    @GET("TabletDownloadDataDistrict_Block_Village")
    Call<String> getProduct(@Query("Username") String storeKey, @Query("Password") String password);

    @GET("TabletUpload_VOMPR_Data")
    Call<String> saveMemberData(@Query("sData") String data);


}
