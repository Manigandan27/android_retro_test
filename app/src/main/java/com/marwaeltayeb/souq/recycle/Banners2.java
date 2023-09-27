package com.marwaeltayeb.souq.recycle;

import com.google.gson.annotations.SerializedName;
import com.marwaeltayeb.souq.retro_items.SliderModelClass;

import java.util.ArrayList;

public class Banners2 {

    @SerializedName("Banners")
    private ArrayList<Banners> banners;

    public Banners2(){

    }

    public ArrayList<Banners> getBanners(){
        return banners;
    }

    private ArrayList<SliderModelClass> banners1;

    public ArrayList<SliderModelClass>getBanners1(){
        return banners1;
    }



}
