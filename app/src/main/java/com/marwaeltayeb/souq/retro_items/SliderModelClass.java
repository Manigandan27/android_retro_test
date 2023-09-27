package com.marwaeltayeb.souq.retro_items;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


    public class SliderModelClass {

        @SerializedName("id")
        private String name;

        @SerializedName("banner_url")
        private String url;

//        public SliderModelClass() {
////            this.name = name;
////            this.url = url;
//        }

        public String getName() {
            return name;
        }

        public void setDescription(String description) {
            this.name = description;
        }


        public String getUrl() {
            return url;
        }

        public void setImageUrl(String imageUrl) {
            this.url = imageUrl;
        }


    }








