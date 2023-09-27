package com.marwaeltayeb.souq.retro_items;

import com.google.gson.annotations.SerializedName;

public class Dash_menu_model {

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("desc")
    private String desc;

    @SerializedName("url")
    private String url;



    public Dash_menu_model(String id, String name, String desc,  String url) {

        this.id=id;
        this.name = name;
        this.desc=desc;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDesc(){
        return desc;
    }

    public String getUrl() {
        return url;
    }







}
