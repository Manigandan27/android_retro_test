//package com.marwaeltayeb.souq;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//import androidx.appcompat.app.AppCompatActivity;
//import com.google.android.gms.tasks.OnFailureListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslator;
//import com.google.firebase.ml.common.modeldownload.FirebaseModelDownloadConditions;
//import com.google.firebase.ml.naturallanguage.FirebaseNaturalLanguage;
//import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslateLanguage;
//import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslator;
//import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslatorOptions;
//import com.marwaeltayeb.souq.bottomnavigation.Bnav_Account;
//import com.marwaeltayeb.souq.view.ProductActivity;
//
//import org.jetbrains.annotations.NotNull;
//
//public class LanguageDetect extends AppCompatActivity {
//
//    // creating variables for our image view,
//    // text view and two buttons.
//    private EditText edtLanguage;
//    private TextView translateLanguageTV;
//    private Button translateLanguageBtn,homebtn;
//
//    // create a variable for our
//    // firebase language translator.
//    FirebaseTranslator englishGermanTranslator;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.languagedetect);
//
//        // on below line we are creating our firebase translate option.
//        FirebaseTranslatorOptions options =
//                new FirebaseTranslatorOptions.Builder()
//                        // below line we are specifying our source language.
//                        .setSourceLanguage(FirebaseTranslateLanguage.EN)
//                        // in below line we are displaying our target language.
//                        .setTargetLanguage(FirebaseTranslateLanguage.DE)
//                        // after that we are building our options.
//                        .build();
//        // below line is to get instance
//        // for firebase natural language.
//        englishGermanTranslator = FirebaseNaturalLanguage.getInstance().getTranslator(options);
//
//        // on below line we are initializing our variables.
//        edtLanguage = findViewById(R.id.idEdtLanguage);
//        translateLanguageTV = findViewById(R.id.idTVTranslatedLanguage);
//        translateLanguageBtn = findViewById(R.id.idBtnTranslateLanguage);
//
//        homebtn=findViewById(R.id.homepage);
//
//        homebtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                Intent i = new Intent(LanguageDetect.this, ProductActivity.class);
//                startActivity(i);
//            }
//        });
//
//        // adding on click listener for button
//        translateLanguageBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // calling method to download language
//                // modal to which we have to translate.
//                String string = edtLanguage.getText().toString();
//                downloadModal(string);
//            }
//        });
//    }
//
//    private void downloadModal(String input) {
//        // below line is use to download the modal which
//        // we will require to translate in german language
//        FirebaseModelDownloadConditions conditions = new FirebaseModelDownloadConditions.Builder().requireWifi().build();
//
//        // below line is use to download our modal.
//        englishGermanTranslator.downloadModelIfNeeded(conditions).addOnSuccessListener(new OnSuccessListener<Void>() {
//            @Override
//            public void onSuccess(Void aVoid) {
//
//                // this method is called when modal is downloaded successfully.
//                Toast.makeText(LanguageDetect.this, "Please wait language modal is being downloaded.", Toast.LENGTH_SHORT).show();
//
//                // calling method to translate our entered text.
//                translateLanguage(input);
//
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NotNull Exception e) {
//                Toast.makeText(LanguageDetect.this, "Fail to download modal", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//    private void translateLanguage(String input) {
//        englishGermanTranslator.translate(input).addOnSuccessListener(new OnSuccessListener<String>() {
//            @Override
//            public void onSuccess(String s) {
//                translateLanguageTV.setText(s);
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NotNull
//                                          Exception e) {
//                Toast.makeText(LanguageDetect.this, "Fail to translate", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//}
