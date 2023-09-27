package com.marwaeltayeb.souq;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.marwaeltayeb.souq.RegisterCheck.RegisterActivity;
import com.marwaeltayeb.souq.view.LoginActivity;
import com.marwaeltayeb.souq.view.ProductActivity;
import com.marwaeltayeb.souq.view.SignUpActivity;
import java.util.concurrent.TimeUnit;


public class Otp_Activity extends AppCompatActivity {

    private static final String TAG = "OtpActivity";

    EditText phonenumber,otpnumber;
    Button verifybtn ,generatebtn;
    TextView resendotp,olduser;

    private FirebaseAuth mAuth;
    private String verificationId;
    private EditText edtPhone, edtOTP;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        edtPhone=findViewById(R.id.user_PhoneNumber);   // eT
        edtOTP=findViewById(R.id.otp_verify);

        generatebtn=findViewById(R.id.generate_btn);    // btn
        verifybtn=findViewById(R.id.verify_btn);

        resendotp=findViewById(R.id.resend_otp_btn);
        olduser=findViewById(R.id.textOtpLogin);     // textOtpLogin

        mAuth = FirebaseAuth.getInstance();

        LinearLayout otplayer=findViewById(R.id.otpLayerLinear);
        otplayer.setVisibility(View.GONE);

        LinearLayout generateotplayer=findViewById(R.id.generateotplayer);

        olduser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//              Intent i = new Intent(Otp_Activity.this, SignUpActivity.class);
                Intent i = new Intent(Otp_Activity.this, ProductActivity.class);
                startActivity(i);

            }
        });


        resendotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(edtPhone.getText().toString())) {
                    Toast.makeText(Otp_Activity.this, "Enter Phone Number", Toast.LENGTH_SHORT).show();
                } else {
                    String phone = "+91" + edtPhone.getText().toString();
                    sendVerificationCode(phone);
                }
            }
        });

        generatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(edtPhone.getText().toString())) {
                    Toast.makeText(Otp_Activity.this, "Enter Phone Number", Toast.LENGTH_SHORT).show();
                } else {
                    otplayer.setVisibility(View.VISIBLE);
                    generateotplayer.setVisibility(View.GONE);
                    String phone = "+91" + edtPhone.getText().toString();
                    sendVerificationCode(phone);
                }
            }
        });


        verifybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(edtOTP.getText().toString())) {
                    Toast.makeText(Otp_Activity.this, "Please enter OTP", Toast.LENGTH_SHORT).show();
                } else {
                    verifyCode(edtOTP.getText().toString());
                }
            }

        });

    }

    private void signInWithCredential(PhoneAuthCredential credential) {

        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //   Toast.makeText(RegisterActivity.this, "OTP Verified Successfully", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(Otp_Activity.this, ProductActivity.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);//newww
                            startActivity(i);
                            finish();
                        } else {
//                            Toast.makeText(Otp_Activity.this, "Enter Correct OTP", Toast.LENGTH_SHORT).show();

                            Log.w(TAG, "signInWithCredential" + task.getException().getMessage());

                            Toast.makeText(Otp_Activity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }


    private void sendVerificationCode(String number) {

        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(mAuth)
                .setPhoneNumber(number)         // Phone number to verify
                .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                .setActivity(this)                 // Activity (for callback binding)
                .setCallbacks(mCallBack)         // OnVerificationStateChangedCallbacks
                .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks
            mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);

            verificationId = s;
        }

        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

            final String code = phoneAuthCredential.getSmsCode();
            if (code != null) {
                edtOTP.setText(code);
                verifyCode(code);
            }
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Toast.makeText(Otp_Activity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    };

    private void verifyCode(String code) {
        try {
            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
            signInWithCredential(credential);

        }catch (Exception e){
            Toast toast = Toast.makeText(this, "Verification Code is wrong", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();
        }
    }
}