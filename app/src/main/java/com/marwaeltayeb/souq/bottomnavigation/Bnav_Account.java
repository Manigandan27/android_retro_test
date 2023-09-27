package com.marwaeltayeb.souq.bottomnavigation;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.marwaeltayeb.souq.R;
import com.marwaeltayeb.souq.view.LoginActivity;

//public class Fragment2 extends Fragment {

public class Bnav_Account extends AppCompatActivity {


    LinearLayout editProfile,logOut;



    public Bnav_Account() {

    }

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//       requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.btm_myaccount);


        Toolbar toolbar = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back_white);//adds  (<--default) icon on tool bar
//        getSupportActionBar().setDisplayShowTitleEnabled(false);    //removes title in toolbar

        editProfile=findViewById(R.id.p_editprofile);

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Bnav_Account.this, Bnav_Profile.class);
                startActivity(i);
//                Toast.makeText(Bnav_Account.this, "Profile ", Toast.LENGTH_SHORT).show();
            }
        });

        logOut=findViewById(R.id.p_logout);

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Bnav_Account.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        toolbar.inflateMenu(R.menu.profile_toolbar);
        getMenuInflater().inflate(R.menu.profile_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:

                Toast.makeText(Bnav_Account.this, "Home item selected", Toast.LENGTH_SHORT).show();

                // Add action for first icon here
                return true;
            case R.id.profile_cart:
                // Add action for second icon here
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}