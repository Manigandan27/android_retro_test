package com.marwaeltayeb.souq.retro_items;

import java.util.Observable;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface D_Api {

    String BASE_URL_dash = "https://run.mocky.io/v3/dc04038f-2c3f-47af-9b9c-7e45eff890e1/";
    @GET(".")
    Call<Retro_model_class> getDash();
    @GET(".")
    Call<Retro_model_class> getProduct();


    String BASE_URL_menu_dash = "https://run.mocky.io/v3/62321b98-ad07-41cb-aff7-58a99df61192/";
    @GET(".")
    Call<Retro_model_class> getMenuDash();


    String BASE_URL_banner="https://run.mocky.io/v3/d8acaf5c-2d83-43a4-850e-c9068db0bb8b/";
    @GET(".")
    Call<Retro_model_class> getBannerSlide();



    String BASE_URL_dynamic="https://run.mocky.io/v3/852f2176-4a36-465a-8a61-2cb264feec12/";
    @GET(".")

    Call<Retro_model_class> getDynamicdata();

//    Call<Data_ApiModel> getDynamicdata1();


}
