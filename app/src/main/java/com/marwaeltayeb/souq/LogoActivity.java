package com.marwaeltayeb.souq;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.marwaeltayeb.souq.RegisterCheck.RegisterActivity;
import com.marwaeltayeb.souq.storage.LoginUtils;
import com.marwaeltayeb.souq.view.LoginActivity;
import com.marwaeltayeb.souq.view.ProductActivity;
import com.marwaeltayeb.souq.view.SignUpActivity;
import com.marwaeltayeb.souq.view.SplashActivity;

public class LogoActivity extends AppCompatActivity {

    private static final int SPLASH_TIME_OUT = 1000;

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_logo);

        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(LogoActivity.this, ProductActivity.class);//ProductActivity//SignUpActivity for checking
                startActivity(intent);//LoginActivity--google signin demo
                finish();
            }
        },SPLASH_TIME_OUT);


        
    }


}