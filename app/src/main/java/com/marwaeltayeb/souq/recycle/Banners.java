package com.marwaeltayeb.souq.recycle;

import com.google.gson.annotations.SerializedName;

public class Banners {

    @SerializedName("id")
    private String name;

    @SerializedName("banner_url")
    private String url;

    public Banners(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }



}
