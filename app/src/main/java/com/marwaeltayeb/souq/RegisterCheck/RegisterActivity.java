
package com.marwaeltayeb.souq.RegisterCheck;;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.marwaeltayeb.souq.R;
import com.marwaeltayeb.souq.view.LoginActivity;
import com.marwaeltayeb.souq.view.ProductActivity;


import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;



import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;


public class RegisterActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    Button logoutBtn;
    TextView userName, userEmail, userId;
    ImageView profileImage;
    private GoogleApiClient googleApiClient;
    private GoogleSignInOptions gso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_register);

        logoutBtn = findViewById(R.id.logoutBtn);
        userName = findViewById(R.id.name);
        userEmail = findViewById(R.id.email);
        userId = findViewById(R.id.userId);
        profileImage = findViewById(R.id.profileImage);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();


        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(
                        new ResultCallback<Status>() {
                            @Override
                            public void onResult(Status status) {
                                if (status.isSuccess()) {
                                    gotoMainActivity();
                                } else {
                                    Toast.makeText(getApplicationContext(), "Session not close", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(googleApiClient);
        if (opr.isDone()) {
            GoogleSignInResult result = opr.get();
            handleSignInResult(result);
        } else {
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(@NonNull GoogleSignInResult googleSignInResult) {
                    handleSignInResult(googleSignInResult);
                }
            });
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            GoogleSignInAccount account = result.getSignInAccount();
            userName.setText(account.getDisplayName());
            userEmail.setText(account.getEmail());
            userId.setText(account.getId());
            try {
                Glide.with(this).load(account.getPhotoUrl()).into(profileImage);
            } catch (NullPointerException e) {
                Toast.makeText(getApplicationContext(), "image not found", Toast.LENGTH_LONG).show();
            }

        } else {
            gotoMainActivity();
        }
    }

    private void gotoMainActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
















 /*   private EditText fullName;
    private EditText user_name;//edun
    private EditText email;//ede_m
    private EditText password;
    private EditText conFirmPassword;
    private Button signUpBtn;

    private final int GALLERY = 1;
    private final int CAMERA = 2;

    public static String str_val1 = "";
    public static String apiUrl1 = "http://192.168.1.12/api/index.php";

    Bitmap FixBitmap;
    ImageView ShowSelectedImage;
    ImageButton capt;


    CheckBox otpcheckbox, checkbox2;
    Button otpLogin;

    LinearLayout linearLayoutotp;

    TextView date, date1;
    private Calendar calendar;
    private int year, month, day;

    int mYear, mMonth, mDay, mHour, mMinute, mSeconds, new_f = 0;


    SharedPreferences Shared_pref;//n
    String mypreference = "mypref";//n
    Intent intent;


    public static final String TAG = "GoogleSignIn";
    TextView tvUserName;
    TextView tvUserEmail;
    ImageView userImageView;
    Button btnSignOut;

    //  Intent intent;

    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback;
    private String verificationCode;


    private FirebaseAuth mAuth;
    private String verificationId;
    private EditText edtPhone, edtOTP;
    private TextView generateOTPBtn, verifyOTPBtn;

    SignInButton btSignIn;
    GoogleSignInClient googleSignInClient;
    FirebaseAuth firebaseAuth;

    Button disconnectbtn,signoutbtn;
//
//    SignInButton signInButton;
//    private GoogleApiClient googleApiClient;
//    TextView textView;
//    private static final int RC_SIGN_IN = 1;

    //    private static final String TAG = "GoogleActivity";
    private static final int RC_SIGN_IN = 9001;
    GoogleSignInClient mGoogleSignInClient;


//    private FirebaseAuth mAuth;

    //    private FirebaseAuth mAuth;//
//    private EditText edtPhone, edtOTP;//
//
//    private Button verifyOTPBtn, generateOTPBtn;//
//
//    private String verificationId;//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_register);

        Shared_pref = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        Shared_pref = getSharedPreferences("user_details", MODE_PRIVATE);


//  intent  = new Intent(RegisterActivity.this, Activity_main.class);
        // Toast.makeText(RegisterActivity.this, "data available", Toast.LENGTH_SHORT).show();

/*

 // intent = new Intent(String.valueOf(this));

       // Toast.makeText(RegisterActivity.this, "data available", Toast.LENGTH_SHORT).show();



//
////        intent = new Intent(String.valueOf(RegisterActivity.this));
////        Toast.makeText(RegisterActivity.this, "user_name saved", Toast.LENGTH_SHORT).show();
////        if (Shared_pref.contains(String.valueOf(user_name))){
////            startActivity(intent);
////        }
//
//
////        intent  = new Intent(this, Activity_main.class);
////        Toast.makeText(RegisterActivity.this, "user_name saved", Toast.LENGTH_SHORT).show();
//
////        if (Shared_pref.contains("username") && Shared_pref.contains("email")) {
////            startActivity(intent);
////        }


// /*
//
//        if (sharedPreferences.contains(UserName)) {
//            user_name.setText(sharedPreferences.getString(UserName, ""));
//        }
//
//        if (sharedPreferences.contains(Email)) {
//            email.setText(sharedPreferences.getString(Email, ""));
//        }
//

//******************

        init();

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();

            }
        });
        // note = new Note();
//        noteDatabase = NoteDatabase.getInstance(this);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String currentDate = dateFormat.format(new Date());

//        date =  findViewById(R.id.date);
        date.setText(currentDate.toString());

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        signUpBtn = findViewById(R.id.btnCreateAccount);    // db login
        otpLogin = findViewById(R.id.btnOtpAccount);          //otp login

        otpcheckbox = findViewById(R.id.otpcheckBox);
        // checkbox2=findViewById(R.id.checkBox);
        mAuth = FirebaseAuth.getInstance();
        edtPhone = findViewById(R.id.edt_mob);//Ph.No
        edtOTP = findViewById(R.id.et_otp);//otptxtid

        linearLayoutotp = findViewById(R.id.otplayout);
        otpLogin.setVisibility(View.GONE);
        linearLayoutotp.setVisibility(View.GONE);


        //  generateOTPBtn = findViewById(R.id.btn_generate_otp);
        //   verifyOTPBtn = findViewById(R.id.btn_sign_in);//otpgenerator  move to next page

        signUpBtn.setEnabled(false);
        password.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub

                if (s.toString().trim().length() < 5) {
                    Toast.makeText(RegisterActivity.this, "Password must contain 6 letters", Toast.LENGTH_SHORT).show();

                    signUpBtn.setEnabled(false);
//                    signUpBtn.setBackground();
                } else {
                    signUpBtn.setEnabled(true);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub

            }
        });


        mAuth = FirebaseAuth.getInstance();
        otpcheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    if (TextUtils.isEmpty(edtPhone.getText().toString())) {
                        Toast.makeText(RegisterActivity.this, "Please enter a valid phone number.", Toast.LENGTH_SHORT).show();
                    } else {
                        String phone = "+91" + edtPhone.getText().toString();
                        sendVerificationCode(phone);//----------
                    }

                    signUpBtn.setVisibility(View.GONE);//db login
                    otpLogin.setVisibility(View.VISIBLE);
                    linearLayoutotp.setVisibility(View.VISIBLE);

                } else {

                    signUpBtn.setVisibility(View.VISIBLE);  //db login
                    otpLogin.setVisibility(View.GONE);
                    linearLayoutotp.setVisibility(View.GONE);

                }
            }
        });
        otpLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(edtOTP.getText().toString())) {
                    Toast.makeText(RegisterActivity.this, "Please enter OTP", Toast.LENGTH_SHORT).show();
                } else {
                    verifyCode(edtOTP.getText().toString());
                }
            }

        });


        date.setOnClickListener(new View.OnClickListener() {

            @SuppressWarnings("deprecation")
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                showDialog(DATE_PICKER_TO);
            }
        });

        ShowSelectedImage = findViewById(R.id.imageView);

        //new image capture added 19/10/2022
        ShowSelectedImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, CAMERA);
                //showPictureDialog();
            }
        });

        ///------------------google signin----------------------------------------------------------------------------------------

        findViewById(R.id.bt_sign_in).setOnClickListener(this);

        findViewById(R.id.signOutButton).setOnClickListener(this);
        findViewById(R.id.disconnectButton).setOnClickListener(this);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestIdToken(getString(R.string.default_web_client_id))//Default_web_client_id will be matched with the
                .requestEmail()
                .build();


        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        SignInButton btSignIn = findViewById(R.id.bt_sign_in);
        btSignIn.setSize(SignInButton.SIZE_WIDE);


        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {
            Log.d(TAG, "Currently Signed in: " + currentUser.getEmail());
            Toast.makeText(RegisterActivity.this, "Currently Logged in: " + currentUser.getEmail(), Toast.LENGTH_LONG).show();
        }
    }

    //Calling onActivityResult to use the information about the sign-in user contains in the object.
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Toast.makeText(this, "Google Sign in Succeeded", Toast.LENGTH_LONG).show();
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                Log.w(TAG, "Google sign in failed", e);
                Toast.makeText(this, "Google Sign in Failed " + e, Toast.LENGTH_LONG).show();
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());
        //Calling get credential from the oogleAuthProviderG
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    //Override th onComplete() to see we are successful or not.
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            FirebaseUser user = mAuth.getCurrentUser();
                            Log.d(TAG, "signInWithCredential:success: currentUser: " + user.getEmail());
                            Toast.makeText(RegisterActivity.this, "Firebase Authentication Succeeded ", Toast.LENGTH_LONG).show();
                        } else {
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(RegisterActivity.this, "Firebase Authentication failed:" + task.getException(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    public void signInToGoogle() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void signOut() {
        FirebaseAuth.getInstance().signOut();

        mGoogleSignInClient.signOut().addOnCompleteListener(this,
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(getApplicationContext(), "Signed out of google", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void revokeAccess() {
        FirebaseAuth.getInstance().signOut();

        mGoogleSignInClient.revokeAccess().addOnCompleteListener(this,
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Log.w(TAG, "Revoked Access");
                    }
                });
    }


    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.bt_sign_in) {
            signInToGoogle();
        }
        else if (i == R.id.signOutButton) {
            signOut();
        }
        else if (i == R.id.disconnectButton) {
            revokeAccess();
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {


}

*/

/////////////////////////////////////////////////////////////////////////////////////


//        GoogleSignInOptions googleSignInOptions=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN
//        ).requestIdToken("438431947620-ecpi41uk3dhhf4mv8g8q993k3vs49ltm.apps.googleusercontent.com")
//                .requestEmail().build();
//        googleSignInClient= GoogleSignIn.getClient(RegisterActivity.this,googleSignInOptions);
//        btSignIn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=googleSignInClient.getSignInIntent();
//                startActivityForResult(intent,100);
//            }
//        });


//        firebaseAuth=FirebaseAuth.getInstance();
//        FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
//        if(firebaseUser!=null)
//        {
//         startActivity(new Intent(RegisterActivity.this,ProductActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
//        }


//        tvUserName = findViewById(R.id.userName);
//        tvUserEmail = findViewById(R.id.userEmail);
////        userImageView = findViewById(R.id.userImage);
//        btnSignOut = findViewById(R.id.btnLogout);



//        SharedPreferences preferences = this.getSharedPreferences("user_details", MODE_PRIVATE);
//        String userName = preferences.getString("username","");
//        String userEmail = preferences.getString("useremail", "");
////        String userImageUrl = preferences.getString("userPhoto","");
//        tvUserName.setText(userName);
//        tvUserEmail.setText(userEmail);
//        Glide.with(this).load(userImageUrl).into(userImageView);
//        btnSignOut.setOnClickListener(view -> {
//            FirebaseAuth.getInstance().signOut();
//            goToMainActivity();
//        });
  //  }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode==100)
//        {
//            Task<GoogleSignInAccount> signInAccountTask=GoogleSignIn.getSignedInAccountFromIntent(data);
//            if(signInAccountTask.isSuccessful())
//            {
//                String s="Google sign in successful";
//                displayToast(s);
//                try {
//                    GoogleSignInAccount googleSignInAccount=signInAccountTask.getResult(ApiException.class);
//                    if(googleSignInAccount!=null)
//                    {
//                        AuthCredential authCredential= GoogleAuthProvider.getCredential(googleSignInAccount.getIdToken()
//                                ,null);
//                        firebaseAuth.signInWithCredential(authCredential)
//                                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                                    @Override
//                                    public void onComplete(@NonNull Task<AuthResult> task) {
//                                        if(task.isSuccessful()) {
//                                            startActivity(new Intent(RegisterActivity.this,ProductActivity.class)
//                                                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
//                                            displayToast("Firebase authentication successful");
//                                        }
//                                        else
//                                        {
//                                            displayToast("Authentication Failed :"+task.getException().getMessage());
//                                        }
//                                    }
//                                });
//
//                    }
//                }
//                catch (ApiException e)
//                {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
//    private void displayToast(String s) {
//        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
  //  }




////----------------------------------------------------------------------------------------------------------------------------





//    private void goToMainActivity() {
//        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
//    }


//        if (!new Activity_main.SharedPref(this).isUserLogedOut());
//        saveLoginDetails(email, password);
//
//        if (ContextCompat.checkSelfPermission(RegisterActivity.this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                requestPermissions(new String[]{android.Manifest.permission.CAMERA}, 5);
//
//            }
//        }
//    }
//
//    private void saveLoginDetails(EditText email, EditText password) {
//        new Activity_main.SharedPref(this).saveLoginDetails(email, password);
//    }

//    /}

    ///----------------------------------------------
//    private void sharedpref(View.OnClickListener onClickListener) {
//        String dob = date.getText().toString();
//        String ph = edtPhone.getText().toString();
//
//      //  SharedPreferences.Editor editor = shar.edit();
//       // editor.putString(UserName, n);
//      //  editor.putString(Email, e);
//     //   editor.putString(Dob, dob);
//       // editor.putString(PhNo, ph);
//
//       // setVisible(false);
//       // editor.commit();
//    }

    //-------------------------------
    //-----------------------------------
/*

    private void signInWithCredential(PhoneAuthCredential credential) {

        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            //   Toast.makeText(RegisterActivity.this, "OTP Verified Successfully", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(RegisterActivity.this, ProductActivity.class);
                            startActivity(i);
                            finish();
                        } else {
                            Toast.makeText(RegisterActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
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
            Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
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


//    private void setResult(Note note, int flag, long id){
//
//        setResult(flag,new Intent().putExtra("note",note));
//        Intent intent = new Intent(this, Activity_main.class);
//
//        intent.putExtra("name", note.getDisplay_name());
//        intent.putExtra("username", note.getUsername());
//        intent.putExtra("password", note.getPassword());
//        intent.putExtra("email", note.getEmail());
//
////        intent.putExtra("date", note.getDob());
////        intent.putExtra("date",note.getDob());
//
//        startActivity(intent);
//        finish();
//    }

    private void validate() {


//        SharedPreferences.Editor editor = Shared_pref.edit();
//        String username_ = RegisterActivity.this.user_name.getText().toString();//sharedpref
//        String email_ = email.getText().toString();
//        String datedob_ = date.getText().toString();
//        String Pass_=password.getText().toString();

        //  String n = user_name.getText().toString();
        // String e = email.getText().toString();
        //   boolean cancel = false;
        //  View focusView = null;

        String name = fullName.getText().toString().trim();
        String email_in = email.getText().toString().trim();
        String password_in = password.getText().toString().trim();
        String re_password = conFirmPassword.getText().toString().trim();
        String username = user_name.getText().toString().trim();
        String dob_ = date.getText().toString().trim();

        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(username) && !TextUtils.isEmpty(email_in) && !TextUtils.isEmpty(password_in)  && !TextUtils.isEmpty(dob_)){

            if (email_in.contains("@")){
                if (!username.contains(" "))  {
                    // username.isEmpty() |||| username.length() < 10
                    // if ( username.length() > 5 ) {
                    if (password_in.equals(re_password) || password_in.length() > 5){
//                        startRegistration(name, email_in, password_in, username);
                        startRegistration(name, email_in, password_in, username );

                        SharedPreferences.Editor editor = Shared_pref.edit();

                        editor.putString("username", username);
                        editor.putString("email", email_in);
                        editor.putString("Full name", name);
                        editor.putString("password", password_in);
                        editor.putString("date", dob_);

                        editor.commit();

//                        if (Shared_pref.contains("password") && Shared_pref.contains("email")) {
//                            Toast.makeText(RegisterActivity.this, "data available "+password_in+email_in +dob_, Toast.LENGTH_LONG).show();
//                        }

                    }else {
                        Toast.makeText(this, getString(R.string.password_mismatch), Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(this, getString(R.string.no_space), Toast.LENGTH_LONG).show();
                }
            }else {
                Toast.makeText(this, getString(R.string.email_not_valid), Toast.LENGTH_LONG).show();
            }
        }else {
            Toast.makeText(this, getString(R.string.check_fields), Toast.LENGTH_LONG).show();
        }
    }

    private void startRegistration(String name, String email_in, String password_in, String username) {

//    private void startRegistration( ) {
        if (Shared_pref.contains("password") && Shared_pref.contains("email")) {

            startActivity(new Intent(RegisterActivity.this, ProductActivity.class));

        }


//        Toast.makeText(RegisterActivity.this, "registration successfull page ", Toast.LENGTH_LONG).show();
    }

    private void startMainActivity(long id) {
        // DashboardActivity.start(this);
    }

    private void init() {
        fullName = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);//--     password
        conFirmPassword = findViewById(R.id.confirm_password);
        signUpBtn = findViewById(R.id.btnCreateAccount);
        user_name = findViewById(R.id.username);

        date =  findViewById(R.id.date);
    }

    public void login(View view) {
        startActivity(new Intent(this, ProductActivity.class));
    }



  /*  @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // check that it is the SecondActivity with an OK result
        if (requestCode == 100) {
            if (resultCode == RESULT_OK) {

            }
        }

        //   super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED) {
            return;
        }

        if (requestCode == CAMERA) {
            FixBitmap = (Bitmap) data.getExtras().get("data");
            ShowSelectedImage.setImageBitmap(FixBitmap);
        }
    }//     * /

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 5) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Now user should be able to use camera

            } else {

                Toast.makeText(RegisterActivity.this, "Unable to use Camera..Please Allow us to use Camera", Toast.LENGTH_LONG).show();

            }
        }
    }

    public static String pad(int c)
    {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }

    int DATE_PICKER_TO = 0;
    int DATE_PICKER_FROM = 1;

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == DATE_PICKER_TO) {
            //  return  new DatePickerDialog(this, myDateListener, year, month, day);
            DatePickerDialog dialog = new DatePickerDialog(this, myDateListener, year, month, day);
            dialog.getDatePicker().setMaxDate(System.currentTimeMillis());
            return dialog;
        }  else if(id == DATE_PICKER_FROM)
        {
            // return new DatePickerDialog(this, myDateListener1, year, month, day);
            DatePickerDialog dialog = new DatePickerDialog(this, myDateListener1, year, month, day);
            dialog.getDatePicker().setMaxDate(System.currentTimeMillis());
            return dialog;
        }
        return null;
    }
    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
            // arg1 = year
            // arg2 = month
            // arg3 = day
            //newly format added on 29/01/2020
//            date.getText().toString();
            date.setText((new StringBuilder().append(pad(arg3)).append("/").append(pad(arg2 + 1)).append("/").append(arg1)).toString());

        }
    };

    private DatePickerDialog.OnDateSetListener myDateListener1 = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
            // arg1 = year
            // arg2 = month
            // arg3 = day

            date1.setText((new StringBuilder().append(pad(arg3)).append("/").append(pad(arg2 + 1)).append("/").append(arg1)).toString());
//            date1.getText().toString();
//            date.getText().toString();



        }
    };

}


*/



//////////////////////////////////////////////////////////////////////////////////////////////////////////





















/*

 @BindView(R.id.field_phone_number)
    EditText fieldPhoneNumber;
    @BindView(R.id.field_verification_code)
    EditText fieldVerificationCode;
    @BindView(R.id.button_start_verification)
    Button buttonStartVerification;
    @BindView(R.id.button_verify_phone)
    Button buttonVerifyPhone;
    @BindView(R.id.button_resend)
    Button buttonResend;

    private FirebaseAuth mAuth;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    String mVerificationId;
    private static final String TAG = "PhoneAuthActivity";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_auth);
        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {
                Log.d("", "onVerificationCompleted:" + credential);
                signInWithPhoneAuthCredential(credential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Log.w("", "onVerificationFailed", e);
                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    fieldPhoneNumber.setError("Invalid phone number.");
                } else if (e instanceof FirebaseTooManyRequestsException) {

                }
            }

            @Override
            public void onCodeSent(String verificationId, PhoneAuthProvider.ForceResendingToken token) {
                Log.d("", "onCodeSent:" + verificationId);
                mVerificationId = verificationId;
                mResendToken = token;
            }
        };

    }


    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = task.getResult().getUser();
                            startActivity(new Intent(PhoneAuthActivity.this, HomeActivity.class).putExtra("phone", user.getPhoneNumber()));
                            finish();
                        } else {
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                fieldVerificationCode.setError("Invalid code.");
                            }
                        }
                    }
                });
    }

    private void getOtp(String phoneNumber) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks
    }

    private void verifyPhoneNumberWithCode(String verificationId, String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        signInWithPhoneAuthCredential(credential);
    }

    private void resendVerificationCode(String phoneNumber,
                                        PhoneAuthProvider.ForceResendingToken token) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks,         // OnVerificationStateChangedCallbacks
                token);             // ForceResendingToken from callbacks
    }

    @OnClick(R.id.button_start_verification)
    public void clickStartVerification(){
        getOtp(fieldPhoneNumber.getText().toString());
    }

    @OnClick(R.id.button_verify_phone)
    public void clickVerifyPhone(){
        String code = fieldVerificationCode.getText().toString();
        if (TextUtils.isEmpty(code)) {
            fieldVerificationCode.setError("Cannot be empty.");
            return;
        }
        verifyPhoneNumberWithCode(mVerificationId, code);
    }

    @OnClick(R.id.button_resend)
    public void clickResend(){
        resendVerificationCode(fieldPhoneNumber.getText().toString(), mResendToken);
    }

}

 */









/*

//package com.marwaeltayeb.souq.RegisterCheck;
//
//
//
//
//
//import android.app.DatePickerDialog;
//import android.app.Dialog;
//import android.content.Context;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.content.pm.PackageManager;
//import android.graphics.Bitmap;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.provider.MediaStore;
//import android.text.Editable;
//import android.text.TextUtils;
//import android.text.TextWatcher;
//import android.util.Log;
//import android.view.Gravity;
//import android.view.View;
//import android.widget.Button;
//import android.widget.CheckBox;
//import android.widget.CompoundButton;
//import android.widget.DatePicker;
//import android.widget.EditText;
//import android.widget.ImageButton;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.FirebaseException;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.PhoneAuthCredential;
//import com.google.firebase.auth.PhoneAuthOptions;
//import com.google.firebase.auth.PhoneAuthProvider;
//import com.marwaeltayeb.souq.R;
//import com.marwaeltayeb.souq.view.ProductActivity;
//
//
//import java.lang.ref.WeakReference;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.concurrent.TimeUnit;
//
//public class RegisterActivity extends AppCompatActivity {
//
//    private EditText fullName;
//    private EditText user_name;//edun
//    private EditText email;//ede_m
//    private EditText password;
//    private EditText conFirmPassword;
//    private Button signUpBtn;
//
//
//    private final int GALLERY = 1;
//    private final int CAMERA = 2;
//
//    public static String str_val1 = "";
//    public static String apiUrl1 = "http://192.168.1.12/api/index.php";
//
//    Bitmap FixBitmap;
//    ImageView ShowSelectedImage;
//    ImageButton capt;
//
//    private FirebaseAuth mAuth;
//    private String verificationId;
//    private EditText edtPhone, edtOTP;
//    private TextView generateOTPBtn,verifyOTPBtn ;
//
//    CheckBox otpcheckbox,checkbox2;
//    Button otpLogin;
//
//    LinearLayout linearLayoutotp;
//
//    TextView date,date1;
//    private Calendar calendar;
//    private int year, month, day;
//
//    int mYear, mMonth, mDay, mHour, mMinute, mSeconds, new_f = 0;
//
//
//    SharedPreferences Shared_pref;//n
//    String mypreference = "mypref";//n
//    Intent intent;
//
//
//    public static final String TAG = "GoogleSignIn";
//    TextView tvUserName;
//    TextView tvUserEmail;
//    ImageView userImageView;
//    Button btnSignOut;
//
//    //  Intent intent;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_new_register);
//
//        Shared_pref = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
//        Shared_pref = getSharedPreferences("user_details", MODE_PRIVATE);
//
//
//        init();
//
//        signUpBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                validate();
//
//            }
//        });
//        // note = new Note();
////        noteDatabase = NoteDatabase.getInstance(this);
//
//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//        String currentDate = dateFormat.format(new Date());
//
////        date =  findViewById(R.id.date);
//        date.setText(currentDate.toString());
//
//        calendar = Calendar.getInstance();
//        year = calendar.get(Calendar.YEAR);
//
//        month = calendar.get(Calendar.MONTH);
//        day = calendar.get(Calendar.DAY_OF_MONTH);
//
//        signUpBtn = findViewById(R.id.btnCreateAccount);    // db login
//        otpLogin = findViewById(R.id.btnOtpAccount);          //otp login
//
//        otpcheckbox = findViewById(R.id.otpcheckBox);
//        // checkbox2=findViewById(R.id.checkBox);
//        mAuth = FirebaseAuth.getInstance();
//        edtPhone = findViewById(R.id.edt_mob);//Ph.No
//        edtOTP = findViewById(R.id.et_otp);//otptxtid
//
//        linearLayoutotp = findViewById(R.id.otplayout);
//        otpLogin.setVisibility(View.GONE);
//        linearLayoutotp.setVisibility(View.GONE);
//
//        //  generateOTPBtn = findViewById(R.id.btn_generate_otp);
//        //   verifyOTPBtn = findViewById(R.id.btn_sign_in);//otpgenerator  move to next page
//
//        signUpBtn.setEnabled(false);
//        password.addTextChangedListener(new TextWatcher() {
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                // TODO Auto-generated method stub
//
//                if(s.toString().trim().length()<5){
//                    Toast.makeText(RegisterActivity.this, "Password must contain 6 letters", Toast.LENGTH_SHORT).show();
//
//                    signUpBtn.setEnabled(false);
////                    signUpBtn.setBackground();
//                } else {
//                    signUpBtn.setEnabled(true);
//                }
//            }
//
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count,
//                                          int after) {
//                // TODO Auto-generated method stub
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                // TODO Auto-generated method stub
//
//            }
//        });
//
//
//        otpcheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//
//                if (isChecked) {
//                    if (TextUtils.isEmpty(edtPhone.getText().toString())) {
//                        Toast.makeText(RegisterActivity.this, "Please enter a valid phone number.", Toast.LENGTH_SHORT).show();
//                    } else {
//                        String phone = "+91" + edtPhone.getText().toString();
//                        sendVerificationCode(phone);
//                    }
//
//                    signUpBtn.setVisibility(View.GONE);//db login
//                    otpLogin.setVisibility(View.VISIBLE);
//                    linearLayoutotp.setVisibility(View.VISIBLE);
//
//                } else {
//
//                    signUpBtn.setVisibility(View.VISIBLE);  //db login
//                    otpLogin.setVisibility(View.GONE);
//                    linearLayoutotp.setVisibility(View.GONE);
//
//                }
//            }
//        });
//        otpLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if (TextUtils.isEmpty(edtOTP.getText().toString())) {
//                    Toast.makeText(RegisterActivity.this, "Please enter OTP", Toast.LENGTH_SHORT).show();
//                } else {
//                    verifyCode(edtOTP.getText().toString());
//                }
//            }
//
//        });
//
//
//        date.setOnClickListener(new View.OnClickListener() {
//
//            @SuppressWarnings("deprecation")
//            @Override
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//                showDialog(DATE_PICKER_TO);
//            }
//        });
//
//        ShowSelectedImage = findViewById(R.id.imageView);
//
//        //new image capture added 19/10/2022
//        ShowSelectedImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivityForResult(intent, CAMERA);
//                //showPictureDialog();
//            }
//        });
//
//
//
////        tvUserName = findViewById(R.id.userName);
////        tvUserEmail = findViewById(R.id.userEmail);
//////        userImageView = findViewById(R.id.userImage);
////        btnSignOut = findViewById(R.id.btnLogout);
//
//
//
////        SharedPreferences preferences = this.getSharedPreferences("user_details", MODE_PRIVATE);
////        String userName = preferences.getString("username","");
////        String userEmail = preferences.getString("useremail", "");
//////        String userImageUrl = preferences.getString("userPhoto","");
////        tvUserName.setText(userName);
////        tvUserEmail.setText(userEmail);
////        Glide.with(this).load(userImageUrl).into(userImageView);
////        btnSignOut.setOnClickListener(view -> {
////            FirebaseAuth.getInstance().signOut();
////            goToMainActivity();
////        });
//    }
////    private void goToMainActivity() {
////        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
////    }
//
//
////        if (!new Activity_main.SharedPref(this).isUserLogedOut());
////        saveLoginDetails(email, password);
////
////        if (ContextCompat.checkSelfPermission(RegisterActivity.this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
////            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
////                requestPermissions(new String[]{android.Manifest.permission.CAMERA}, 5);
////
////            }
////        }
////    }
////
////    private void saveLoginDetails(EditText email, EditText password) {
////        new Activity_main.SharedPref(this).saveLoginDetails(email, password);
////    }
//
////    /}
//
//    ///----------------------------------------------
////    private void sharedpref(View.OnClickListener onClickListener) {
////        String dob = date.getText().toString();
////        String ph = edtPhone.getText().toString();
////
////      //  SharedPreferences.Editor editor = shar.edit();
////       // editor.putString(UserName, n);
////      //  editor.putString(Email, e);
////     //   editor.putString(Dob, dob);
////       // editor.putString(PhNo, ph);
////
////       // setVisible(false);
////       // editor.commit();
////    }
//
//    //-------------------------------
//    //-----------------------------------
//
//
//    private void signInWithCredential(PhoneAuthCredential credential) {
//
//        mAuth.signInWithCredential(credential)
//                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//
//                            //   Toast.makeText(RegisterActivity.this, "OTP Verified Successfully", Toast.LENGTH_SHORT).show();
//                            Intent i = new Intent(RegisterActivity.this, ProductActivity.class);
//                            startActivity(i);
//                            finish();
//                        } else {
//                            Toast.makeText(RegisterActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
//                        }
//                    }
//                });
//    }
//
//    private void sendVerificationCode(String number) {
//
//        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(mAuth)
//                .setPhoneNumber(number)         // Phone number to verify
//                .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
//                .setActivity(this)                 // Activity (for callback binding)
//                .setCallbacks(mCallBack)         // OnVerificationStateChangedCallbacks
//                .build();
//        PhoneAuthProvider.verifyPhoneNumber(options);
//    }
//
//    private PhoneAuthProvider.OnVerificationStateChangedCallbacks
//            mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
//
//        @Override
//        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
//            super.onCodeSent(s, forceResendingToken);
//
//            verificationId = s;
//        }
//
//        @Override
//        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
//
//            final String code = phoneAuthCredential.getSmsCode();
//            if (code != null) {
//                edtOTP.setText(code);
//
//                verifyCode(code);
//            }
//
//        }
//
//        @Override
//        public void onVerificationFailed(@NonNull FirebaseException e) {
//            Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
//        }
//    };
//
//    private void verifyCode(String code) {
//        try {
//            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
//            signInWithCredential(credential);
//
//        }catch (Exception e){
//            Toast toast = Toast.makeText(this, "Verification Code is wrong", Toast.LENGTH_SHORT);
//            toast.setGravity(Gravity.CENTER,0,0);
//            toast.show();
//        }
//    }
//
//
////    private void setResult(Note note, int flag, long id){
////
////        setResult(flag,new Intent().putExtra("note",note));
////        Intent intent = new Intent(this, Activity_main.class);
////
////        intent.putExtra("name", note.getDisplay_name());
////        intent.putExtra("username", note.getUsername());
////        intent.putExtra("password", note.getPassword());
////        intent.putExtra("email", note.getEmail());
//
////        intent.putExtra("date", note.getDob());
////        intent.putExtra("date",note.getDob());
//
////        startActivity(intent);
////        finish();
////    }
//
//    private void validate() {
//
//
////        SharedPreferences.Editor editor = Shared_pref.edit();
////        String username_ = RegisterActivity.this.user_name.getText().toString();//sharedpref
////        String email_ = email.getText().toString();
////        String datedob_ = date.getText().toString();
////        String Pass_=password.getText().toString();
//
//        //  String n = user_name.getText().toString();
//        // String e = email.getText().toString();
//        //   boolean cancel = false;
//        //  View focusView = null;
//
//        String name = fullName.getText().toString().trim();
//        String email_in = email.getText().toString().trim();
//        String password_in = password.getText().toString().trim();
//        String re_password = conFirmPassword.getText().toString().trim();
//        String username = user_name.getText().toString().trim();
//        String dob_ = date.getText().toString().trim();
//
//        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(username) && !TextUtils.isEmpty(email_in) && !TextUtils.isEmpty(password_in)  && !TextUtils.isEmpty(dob_)){
//
//            if (email_in.contains("@")){
//                if (!username.contains(" "))  {
//                    // username.isEmpty() |||| username.length() < 10
//               // if ( username.length() > 5 ) {
//                    if (password_in.equals(re_password) || password_in.length() > 5){
////                        startRegistration(name, email_in, password_in, username);
//                        startRegistration(name, email_in, password_in, username );
//
//                        SharedPreferences.Editor editor = Shared_pref.edit();
//
//                        editor.putString("username", username);
//                        editor.putString("email", email_in);
//                        editor.putString("Full name", name);
//                        editor.putString("password", password_in);
//                        editor.putString("date", dob_);
//
//                        editor.commit();
//
//                        if (Shared_pref.contains("password") && Shared_pref.contains("email")) {
//                            Toast.makeText(RegisterActivity.this, "data available "+password_in+email_in +dob_, Toast.LENGTH_LONG).show();
//                        }
//
//                    }else {
//                        Toast.makeText(this, getString(R.string.password_mismatch), Toast.LENGTH_LONG).show();
//                    }
//                }else {
//                    Toast.makeText(this, getString(R.string.no_space), Toast.LENGTH_LONG).show();
//                }
//            }else {
//                Toast.makeText(this, getString(R.string.email_not_valid), Toast.LENGTH_LONG).show();
//            }
//        }else {
//            Toast.makeText(this, getString(R.string.check_fields), Toast.LENGTH_LONG).show();
//        }
//    }
//
//    private void startRegistration(String name, String email_in, String password_in, String username) {
////
////        note = new Note(name, username, password_in, email_in);//dob_
////        new InsertTask(this, note).execute();
//
//    }
//
//    private void startMainActivity(long id) {
////         DashboardActivity.start(this);
//    }
//
//    private void init() {
//        fullName = findViewById(R.id.name);
//        email = findViewById(R.id.email);
//        password = findViewById(R.id.password);//--     password
//        conFirmPassword = findViewById(R.id.confirm_password);
//        signUpBtn = findViewById(R.id.btnCreateAccount);
//        user_name = findViewById(R.id.username);
//
//        date =  findViewById(R.id.date);
//    }
//
//    public void login(View view) {
//        startActivity(new Intent(this, ProductActivity.class));
//    }
//
////    private static class InsertTask extends AsyncTask<Void, Void, Boolean> {
////
////        private WeakReference<RegisterActivity> activityReference;
//////        private Note note;
////        public long j;
////
////        // only retain a weak reference to the activity
////        InsertTask(RegisterActivity context, Note note) {
////            activityReference = new WeakReference<>(context);
////            this.note = note;
////        }
////
////        // doInBackground methods runs on a worker thread
////        @Override
////        protected Boolean doInBackground(Void... objs) {
////            // retrieve auto incremented note id
////            j = activityReference.get().noteDatabase.getNoteDao().insertNote(note);
////            note.setNote_id(j);
////            Log.e("ID ", "doInBackground: "+j );
////            return true;
////        }
//
//        // onPostExecute runs on main thread
////        @Override
////        protected void onPostExecute(Boolean bool) {
////            if (bool){
////                activityReference.get().setResult(note, 1, j);
////                activityReference.get().finish();
////            }
////        }
////    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        // check that it is the SecondActivity with an OK result
//        if (requestCode == 100) {
//            if (resultCode == RESULT_OK) {
//
//            }
//        }
//
//        //   super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == RESULT_CANCELED) {
//            return;
//        }
//
//        if (requestCode == CAMERA) {
//            FixBitmap = (Bitmap) data.getExtras().get("data");
//            ShowSelectedImage.setImageBitmap(FixBitmap);
//        }
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == 5) {
//            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                // Now user should be able to use camera
//
//            } else {
//
//                Toast.makeText(RegisterActivity.this, "Unable to use Camera..Please Allow us to use Camera", Toast.LENGTH_LONG).show();
//
//            }
//        }
//    }
//
//    public static String pad(int c)
//    {
//        if (c >= 10)
//            return String.valueOf(c);
//        else
//            return "0" + String.valueOf(c);
//    }
//
//    int DATE_PICKER_TO = 0;
//    int DATE_PICKER_FROM = 1;
//
//    @Override
//    protected Dialog onCreateDialog(int id) {
//        // TODO Auto-generated method stub
//        if (id == DATE_PICKER_TO) {
//            //  return  new DatePickerDialog(this, myDateListener, year, month, day);
//            DatePickerDialog dialog = new DatePickerDialog(this, myDateListener, year, month, day);
//            dialog.getDatePicker().setMaxDate(System.currentTimeMillis());
//            return dialog;
//        }  else if(id == DATE_PICKER_FROM)
//        {
//            // return new DatePickerDialog(this, myDateListener1, year, month, day);
//            DatePickerDialog dialog = new DatePickerDialog(this, myDateListener1, year, month, day);
//            dialog.getDatePicker().setMaxDate(System.currentTimeMillis());
//            return dialog;
//        }
//        return null;
//    }
//    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
//        @Override
//        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
//            // TODO Auto-generated method stub
//            // arg1 = year
//            // arg2 = month
//            // arg3 = day
//            //newly format added on 29/01/2020
////            date.getText().toString();
//            date.setText((new StringBuilder().append(pad(arg3)).append("/").append(pad(arg2 + 1)).append("/").append(arg1)).toString());
//
//        }
//    };
//
//    private DatePickerDialog.OnDateSetListener myDateListener1 = new DatePickerDialog.OnDateSetListener() {
//        @Override
//        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
//            // TODO Auto-generated method stub
//            // arg1 = year
//            // arg2 = month
//            // arg3 = day
//
//            date1.setText((new StringBuilder().append(pad(arg3)).append("/").append(pad(arg2 + 1)).append("/").append(arg1)).toString());
////            date1.getText().toString();
////            date.getText().toString();
//
//
//
//        }
//    };
//
//}
 */