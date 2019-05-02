package com.joslabs.majidigitalapp;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {
    public static ApiService apiService;
    static {setRetrofitClient();}

    private static void setRetrofitClient() {
        Gson gson= new Gson();
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://95.216.48.57/waterbilling/index.php/master/api/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        apiService=retrofit.create(ApiService.class);
    }

    public static ApiService getApiService(){return apiService;}
}
