package com.marwaeltayeb.souq.recycle;

import com.google.gson.annotations.SerializedName;

public class Flipmodel {

        @SerializedName("id")
        private String id;

        @SerializedName("banner_url")
        private String url;

        public Flipmodel(String id, String url) {
            this.id = id;
            this.url = url;
        }


    public String getId() {
        return id;
    }


    public String getUrl() {
        return url;
    }


}
