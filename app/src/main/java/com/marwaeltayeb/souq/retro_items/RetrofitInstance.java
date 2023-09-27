package com.marwaeltayeb.souq.retro_items;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {


    private static RetrofitInstance instance = null;
    private D_Api myApi, menuApi;
    private D_Api bannerApi;

    private D_Api dynamicApi;


    String BASE_URL_dash = "https://18c3978a-8918-4ea1-b44b-d76724b70c59.mock.pstmn.io/";

    private RetrofitInstance() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(D_Api.BASE_URL_dash).addConverterFactory(GsonConverterFactory.create()).build();
        myApi = retrofit.create(D_Api.class);

        Retrofit retrofitmenu = new Retrofit.Builder().baseUrl(D_Api.BASE_URL_menu_dash).addConverterFactory(GsonConverterFactory.create()).build();
        menuApi = retrofitmenu.create(D_Api.class);

        Retrofit retrofitbanner = new Retrofit.Builder().baseUrl(D_Api.BASE_URL_banner).addConverterFactory(GsonConverterFactory.create()).build();
        bannerApi = retrofitbanner.create(D_Api.class);

        Retrofit retrofitdynamic = new Retrofit.Builder().baseUrl(D_Api.BASE_URL_dynamic).addConverterFactory(GsonConverterFactory.create()).build();
        dynamicApi = retrofitdynamic.create(D_Api.class);


    }

    public static synchronized RetrofitInstance getInstance() {
        if (instance == null) {
            instance = new RetrofitInstance();
        }
        return instance;
    }

    public D_Api getMyApi() {
        return myApi;
    }


//----------retro menu api
    public static synchronized RetrofitInstance getInstancemenu() {
        if (instance == null) {
            instance = new RetrofitInstance();
        }
        return instance;
    }

    public D_Api getMyApimenu() {
        return menuApi;
    }

//dash menu

    public static synchronized RetrofitInstance getInstanceBanner() {
        if (instance == null) {
            instance = new RetrofitInstance();
        }
        return instance;
    }
    public D_Api getBannerApi() {
        return bannerApi;
    }



    //dynamic data

    public static synchronized RetrofitInstance getInstanceDynamic() {
        if (instance == null) {
            instance = new RetrofitInstance();
        }
        return instance;
    }
    public D_Api getDynamicApi() {
        return dynamicApi;
    }

}
