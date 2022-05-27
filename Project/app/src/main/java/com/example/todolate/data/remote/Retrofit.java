package com.example.todolate.data.remote;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retrofit {
    private static retrofit2.Retrofit retrofit;

    public static retrofit2.Retrofit getClient(String base_url) {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(logging);
        retrofit = new retrofit2.Retrofit.Builder().baseUrl(base_url).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        return retrofit;
    }

}
