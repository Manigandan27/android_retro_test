package com.marwaeltayeb.souq.retro_items;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.marwaeltayeb.souq.retro_items.dynamic_data.DashboardProductList;

import java.util.ArrayList;

public class Data_Dynamic_Model {


    @SerializedName("dashboard_product_lists")
    @Expose
//    private List<DashboardProductList> dashboardProductLists;
    public ArrayList<DashboardProductList> dashboardProductLists;

    DashboardProductList d=new DashboardProductList("","","","","");
    public Data_Dynamic_Model() {
        super();
    }


    public Data_Dynamic_Model(ArrayList<DashboardProductList> dashboardProductLists) {
        super();
        this.dashboardProductLists = dashboardProductLists;
    }


    public ArrayList<DashboardProductList> getDashboardProductLists() {
        return dashboardProductLists;
    }

    public void setDashboardProductLists(ArrayList<DashboardProductList> dashboardProductLists) {
        this.dashboardProductLists = dashboardProductLists;
    }


//    private ArrayList<Data_ApiModel> dynamicapi;
//
//    @SerializedName("id")
//    private String id;
//
//    @SerializedName("Title")
//    private String Title;
//
//    @SerializedName("name")
//    private String name;
//
//    @SerializedName("desc")
//    private String desc;
//
//    @SerializedName("url")
//    private String url;
//
//
//    public Data_Dynamic_Model(String id, String title, String name, String desc, String url) {
//
//        this.id=id;
//        this.Title = title;
//        this.name = name;
//        this.desc=desc;
//        this.url = url;
//    }
//
////    public Dynamic_model() {
////
////    }
//
//
//    public String getId() {
//        return id;
//    }
//
//    public String getTitle() {
//        return Title;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public String getDesc(){
//        return desc;
//    }
//
//    public String getUrl() {
//        return url;
//    }
//
//
//
//    public ArrayList<Dynamic_model> getDynamicapi() {
//        return null;
//    }

}
