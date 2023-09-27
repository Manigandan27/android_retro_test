package com.marwaeltayeb.souq.retro_items;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Dynamic_model {


    private ArrayList<Data_Dynamic_Model> dynamicapi;

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



    public Dynamic_model(String id, String title,String name, String desc, String url) {

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



    public ArrayList<Dynamic_model> getDynamicapi() {
        return null;
    }


}
