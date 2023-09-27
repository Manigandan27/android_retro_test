package com.marwaeltayeb.souq.retro_items;

import com.google.gson.annotations.SerializedName;

public class Dashboard_model_1 {

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("url")
    private String url;

    public Dashboard_model_1(String name, String url, String id) {
        this.name = name;
        this.url = url;
        this.id=id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getId() {
        return id;
    }




}
