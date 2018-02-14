package com.example.abc.cmart;

/**
 * Created by ABC on 11-02-2018.
 */

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    public static final String BASE_URL = "http://heart-stricken-ring.000webhostapp.com/posts/";
    private static Retrofit retrofit = null;

    public static Retrofit getApiClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}