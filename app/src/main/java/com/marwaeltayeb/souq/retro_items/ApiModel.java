package com.marwaeltayeb.souq.retro_items;

import com.google.gson.annotations.SerializedName;
import com.marwaeltayeb.souq.recycle.Banners;

import java.util.ArrayList;

public class ApiModel {

    @SerializedName("dashboard_product_lists")
    private ArrayList<Dynamic_model> dynamic;


    public ApiModel(){
    }

    public ArrayList<Dynamic_model> getDynamic(){
        return dynamic;
    }

    @SerializedName("id")
    private String id;

    @SerializedName("Title")
    private String Title;

    @SerializedName("name")
    private String name;

    @SerializedName("desc")
    private String desc;

    @SerializedName("url")
    private String url;

    public ApiModel(String id, String title,String name, String desc, String url) {

        this.id=id;
        this.Title = title;
        this.name = name;
        this.desc=desc;
        this.url = url;
    }


    public String getId() {
        return id;
    }

    public String getTitle() {
        return Title;
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
