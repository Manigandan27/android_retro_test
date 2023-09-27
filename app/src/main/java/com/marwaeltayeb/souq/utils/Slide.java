package com.marwaeltayeb.souq.utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.bumptech.glide.Glide;
import com.marwaeltayeb.souq.R;
import com.marwaeltayeb.souq.recycle.APIService2;
import com.marwaeltayeb.souq.recycle.Banners;
import com.marwaeltayeb.souq.recycle.Banners2;
import com.marwaeltayeb.souq.recycle.FlipperAdapter2;

import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.temporal.TemporalAdjuster;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Url;

import static com.marwaeltayeb.souq.view.ProductActivity.BASE_URL2;

@RequiresApi(api = Build.VERSION_CODES.O)
public class Slide {

    private Slide(){}
    static String url = "";



    private static final List<Integer> slides = new ArrayList<>();



    static {

        slides.add(R.drawable.slide);
        slides.add(R.drawable.slide);
        slides.add(R.drawable.slide);
        slides.add(R.drawable.slide);
        slides.add(R.drawable.slide);
        slides.add(R.drawable.slide);
//        slides.add(R.drawable.slide);
    }

    public static List<Integer> getSlides() {
        return slides;
    }








}
