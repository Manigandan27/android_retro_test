package com.marwaeltayeb.souq.recycle;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiFlip {

    @GET("get_top_banner")
    Call<Flipmodel> getFlips();
}
