package com.marwaeltayeb.souq.retro_items;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Apiclient {


    public static final String BASE_URL="https://run.mocky.io/v3/852f2176-4a36-465a-8a61-2cb264feec12/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();        }
        return retrofit;    }

}
