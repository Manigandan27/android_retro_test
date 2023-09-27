package com.marwaeltayeb.souq.retro_items;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.marwaeltayeb.souq.retro_items.dynamic_data.Datum;

import java.util.ArrayList;

public class Retro_model_class {


    @SerializedName("dashboard_menus")
    private ArrayList<Dashboard_model_1> dBoard;

    public ArrayList<Dashboard_model_1> getDboard() {
        return dBoard;
    }


    @SerializedName("dashboard_menu_priority1")
    private ArrayList<Product_model_1> dProduct;

    public ArrayList<Product_model_1> getdProduct() {
        return dProduct;
    }


    @SerializedName("dashboard_menu_priority2")
    private ArrayList<Dash_menu_model> dmenuProduct;

    public ArrayList<Dash_menu_model> getDmenuProduct() {
        return dmenuProduct;
    }


    @SerializedName("Banners")
    private ArrayList<SliderModelClass> banner;

    public ArrayList<SliderModelClass> getBanner() {
        return banner;
    }


    @SerializedName("status")
    @Expose
    String status;
    @SerializedName("message")
    @Expose
    String message;
    @SerializedName("Data")
    @Expose
    ArrayList<Datum> data;


    public Retro_model_class(String status, String message, ArrayList<Datum> data) {
        super();
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<Datum> getData() {
        return data;
    }

    public void setData(ArrayList<Datum> data) {
        this.data = data;
    }





    private ArrayList<Dynamic_model> dynamicmodel;

    public ArrayList<Dynamic_model> getDynamicmodel() {
        return dynamicmodel;
    }

    public void setDynamicmodel(ArrayList<Dynamic_model> carsArray) {
        this.dynamicmodel = carsArray;
    }


}



//    private String Data_ApiModel;

//    @SerializedName("status")
//    private String status;
//    public String getStatus() {
//        return status;
//    }
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//
//    @SerializedName("message")
//    private String message;
//    public String getMessage() {
//        return message;
//    }
//    public void setMessage(String message) {
//        this.message = message;
//    }
//
//
//    @SerializedName("Data")
//    private ArrayList<Data_ApiModel> dynamicapi1 ;                          //dynamicapi
//    public ArrayList<Data_ApiModel> getDynamicapi1(){
//        return dynamicapi1;
//    }
//    public void setData(ArrayList<Data_ApiModel> data) {
//        this.dynamicapi1 = dynamicapi1;//dynamicapi
//    }

//-----------------------------------------


//------------------------------------