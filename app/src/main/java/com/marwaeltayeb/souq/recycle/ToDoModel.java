package com.marwaeltayeb.souq.recycle;

import android.net.Uri;

public class ToDoModel {

    public String name2;
    public String name;
    public int imageid;

    public ToDoModel(Integer imageId){
        this.imageid=imageId;

    }


    public int getImageId(){return imageid;}
    public void setImageId(int imageId) {
        this.imageid = imageId;
    }


}

