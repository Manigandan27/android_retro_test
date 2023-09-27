package com.marwaeltayeb.souq.view;

import static com.marwaeltayeb.souq.storage.LanguageUtils.loadLocale;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.marwaeltayeb.souq.R;
import com.marwaeltayeb.souq.storage.LoginUtils;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_TIME_OUT = 3000;
    public int ANIM_TIME_OUT = 2000;
    LottieAnimationView anim;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        loadLocale(this);
        setContentView(R.layout.activity_splash);

        anim = (LottieAnimationView) findViewById(R.id.animationView);
        anim.setAnimation("lottie_animation.json");
        anim.setAnimation("lottie_animation.json");
        anim.getDuration();
//        anim.setVisibility(ANIM_TIME_OUT);
//        LottieAnimationView animationView=anim.setFrame(ANIM_TIME_OUT);
//        anim.getDuration();
        anim.loop(true);

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            Intent i = new Intent(SplashActivity.this, ProductActivity.class);
//            Intent i = new Intent(SplashActivity.this, ProductActivity.class);

            startActivity(i);

            // Close this activity
            finish();
            // If user does not log in before, go to LoginActivity
            if (!LoginUtils.getInstance(SplashActivity.this).isLoggedIn()) {
              //  anim.setAnimation("lottie_animation.json");
                Intent intent = new Intent(SplashActivity.this, ProductActivity.class);//Loginactivity

                //            Intent i = new Intent(SplashActivity.this, ProductActivity.class);
                startActivity(intent);
            }


        }, SPLASH_TIME_OUT);
    }


//    public void animate(View view) {
//        anim.loop(true);
//        anim.playAnimation();
//    }
}
