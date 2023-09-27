package com.marwaeltayeb.souq.bottomnavigation;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.marwaeltayeb.souq.R;
import com.marwaeltayeb.souq.view.ProductActivity;

//public class Fragment2 extends Fragment {

public class Bnav_Profile extends AppCompatActivity {

    String gender = "male";
//    private boolean maleSelected = false;
//    private boolean femaleSelected = false;

    private ImageView Male;
    private ImageView Female;
    private boolean maleSelected;
    private boolean femaleSelected;

    public Bnav_Profile() {

    }

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//       requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.btm_profile);


        Toolbar toolbar = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back_white);//adds  (<--default) icon on tool bar
        getSupportActionBar().setDisplayShowTitleEnabled(false);    //removes title in toolbar


//        toolbar.inflateMenu(R.menu.profile_toolbar);

        Imageactions();

    }

    private void Imageactions() {


             Male = findViewById(R.id.mlogimgvie);
             Female = findViewById(R.id.flogimgvie);

        LinearLayout layout = findViewById(R.id.layoutprofile);


        ImageView photo1 = findViewById(R.id.logimgvie1);//photo//mlogimgvie1
        ImageView photo2 = findViewById(R.id.logimgvie2);

//        ImageView icon1=findViewById(R.id.prof_change1);//prof cng btn
//        ImageView icon2=findViewById(R.id.prof_change2);

        TextView textView = findViewById(R.id.prof_text);

        CardView cardview1 = findViewById(R.id.prof_card1);  //layer 1 male
        CardView cardview2 = findViewById(R.id.prof_card2); //layer 1 female


/*    if (gender.equals("male")) {
            male.setVisibility(View.VISIBLE);
            female.setVisibility(View.GONE);
        } else {
            male.setVisibility(View.GONE);
            female.setVisibility(View.VISIBLE);

         */


 /*       male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gender = (String) v.getTag();
                // Store the selected gender in shared preferences
                SharedPreferences preferences = getSharedPreferences("profile", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("gender", gender);
                editor.apply();
                // Display the selected gender in the profile page
                displaySelectedGender(gender);
            }
        });

        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gender = (String) v.getTag();
                // Store the selected gender in shared preferences
                SharedPreferences preferences = getSharedPreferences("profile", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("gender", gender);
                editor.apply();
                // Display the selected gender in the profile page
                displaySelectedGender(gender);
            }
        });
*/
/*
        Male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                maleSelected = true;
                femaleSelected = false;
                // Store the selected gender in shared preferences
                SharedPreferences preferences = getSharedPreferences("profile", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("gender", "male");
                editor.apply();
                // Display the selected gender in the profile page
                displaySelectedGender("male");
            }
        });

        Female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                maleSelected = false;
                femaleSelected = true;
                // Store the selected gender in shared preferences
                SharedPreferences preferences = getSharedPreferences("profile", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("gender", "female");
                editor.apply();
                // Display the selected gender in the profile page
                displaySelectedGender("female");
            }
        });


 */

        Male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((ImageView)view).getDrawable().getConstantState() == getResources().getDrawable(R.drawable.mprofilelogo).getConstantState()) {
                    ((ImageView)view).setImageResource(R.drawable.wprofilelogo);
                } else {
                    ((ImageView)view).setImageResource(R.drawable.mprofilelogo);
                }
            }
        });


    }

    private void displaySelectedGender(String gender) {
        // Implementation for displaying the selected gender in the profile page goes here

        if (gender.equals("male")) {
            Male.setImageResource(R.drawable.mprofilelogo);
            Female.setImageResource(R.drawable.wprofilelogo);
        } else if (gender.equals("female")) {
            Male.setImageResource(R.drawable.mprofilelogo);
            Female.setImageResource(R.drawable.wprofilelogo);
        }
    }




//                textView.setVisibility(View.GONE);
//                cardview2.setVisibility(View.GONE)
//
//                public void onGenderSelected(View v) {
//        String gender = (String) v.getTag();
//        // Store the selected gender in shared preferences
//        SharedPreferences preferences = getSharedPreferences("profile", MODE_PRIVATE);
//        SharedPreferences.Editor editor = preferences.edit();
//        editor.putString("gender", gender);
//        editor.apply();
//        // Display the selected gender in the profile page
//        displaySelectedGender(gender);
//    }
     /*           if (layout.getVisibility() == View.GONE) {
                    layout.setVisibility(View.VISIBLE);

//                    photo2.setImageResource(R.drawable.wprofilelogo);
//
////                    mButton.setText("Show");
                } else {
                    layout.setVisibility(View.GONE);
                    male.setImageResource(R.drawable.mprofilelogo);
                    female.setImageResource(R.drawable.wprofilelogo);

//                    female.setImageResource(R.drawable.profile_picture);
//                    female.setVisibility(View.GONE);

//                    photo2.setImageResource(R.drawable.wprofilelogo);
                }
//                    textView.setVisibility(View.VISIBLE);
////                    mButton.setText("Hide");
//                }
//                photo1.setImageResource(R.drawable.mprofilelogo);
//                photo2.setImageResource(R.drawable.wprofilelogo);

            }
        });*/

//        male.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                layout.setVisibility(View.GONE);
//                male.setImageResource(R.drawable.profile_picture);
////                male.setVisibility(View.GONE);
//                female.setImageResource(R.drawable.wprofilelogo);
//
////                textView.setVisibility(View.GONE);
////                cardview1.setVisibility(View.GONE);
////
////                if (cardview1.getVisibility() == View.VISIBLE
////                        && cardview2.getVisibility() == View.VISIBLE
////                        && textView.getVisibility() == View.VISIBLE) {
//////
//////                    photo1.setImageResource(R.drawable.mprofilelogo);
//////                    photo2.setImageResource(R.drawable.wprofilelogo);
//////
////                } else {
////
////                    photo1.setImageResource(R.drawable.mprofilelogo);
////                }
////////                textView.setVisibility(View.VISIBLE);
//////                cardview1.setVisibility(View.VISIBLE);
////                cardview2.setVisibility(View.VISIBLE);
////                cardview1.setVisibility(View.GONE);
////                textView.setVisibility(View.GONE);
////
//////                photo1.setImageResource(R.drawable.mprofilelogo);
////                photo2.setImageResource(R.drawable.wprofilelogo);
//            }
//        });


   // }

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

                Toast.makeText(Bnav_Profile.this, "Home item selected", Toast.LENGTH_SHORT).show();

                // Add action for first icon here
                return true;
            case R.id.profile_cart:
                // Add action for second icon here
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


 /*   @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_search) {

            // Do something
            Toast.makeText(Bnav_Profile.this, "item selected", Toast.LENGTH_SHORT).show();

            return true;
        }
        if (id == R.id.profile_cart) {

            // Do something
            return true;
        }

        return super.onOptionsItemSelected(item);
}*/
}