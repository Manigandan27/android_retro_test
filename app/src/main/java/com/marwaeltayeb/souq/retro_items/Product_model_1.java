package com.marwaeltayeb.souq.retro_items;

import com.google.gson.annotations.SerializedName;

public class Product_model_1 {

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("url")
    private String url;

    @SerializedName("desc")
    private String desc;

    public Product_model_1(String id, String name, String url,  String desc) {

        this.id=id;
        this.name = name;
        this.url = url;
        this.desc=desc;
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

    public String getDesc(){
        return desc;
    }







}
