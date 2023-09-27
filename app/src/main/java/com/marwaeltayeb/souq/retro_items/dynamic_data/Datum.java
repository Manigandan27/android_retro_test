package com.marwaeltayeb.souq.retro_items.dynamic_data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Datum  {


    @SerializedName("dashboard_product_lists")
    @Expose
//    private List<DashboardProductList> dashboardProductLists;
    public ArrayList<DashboardProductList> dashboardProductLists;

//    public Datum() {
//
//    }

    public Datum( ArrayList<DashboardProductList> dashboardProductLists) {
        this.dashboardProductLists = dashboardProductLists;
    }

    public ArrayList<DashboardProductList> getDashboardProductLists() {

        return dashboardProductLists;
    }

    public void setDashboardProductLists(ArrayList<DashboardProductList> dashboardProductLists) {
        this.dashboardProductLists = dashboardProductLists;
    }

}
