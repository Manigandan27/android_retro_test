package com.marwaeltayeb.souq.retro_items.dynamic_data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class DashboardProductList {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("Title")
    @Expose
    private String title;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("desc")
    @Expose
    private String desc;
    @SerializedName("url")
    @Expose
    private String url;


    public DashboardProductList(String id,String title,String name,String desc,String url) {
        super();
        this.id = id;
        this.title = title;
        this.name = name;
        this.desc = desc;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}