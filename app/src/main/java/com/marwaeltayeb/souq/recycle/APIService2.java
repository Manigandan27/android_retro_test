package com.marwaeltayeb.souq.recycle;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService2 {


    @GET("get_top_banner")

    Call<Banners2> getBanners();
}
