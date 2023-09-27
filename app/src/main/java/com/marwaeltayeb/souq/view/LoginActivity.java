package com.marwaeltayeb.souq.view;


import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.marwaeltayeb.souq.Otp_Activity;
import com.marwaeltayeb.souq.R;
import com.marwaeltayeb.souq.RegisterCheck.RegisterActivity;
import com.marwaeltayeb.souq.databinding.ActivityLoginBinding;
import com.marwaeltayeb.souq.viewmodel.LoginViewModel;

import org.jetbrains.annotations.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;


public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG = "LoginActivity";
    //    private SignInButton signInButton;
    ImageButton signInButton,fb1;

    private GoogleApiClient googleApiClient;
    private static final int RC_SIGN_IN = 1;
    String name, email;
    String idToken;


    private FirebaseAuth firebaseAuthe;
    private FirebaseAuth.AuthStateListener authStateListener;

    private CallbackManager callbackManager;
    private LoginManager loginManager;

    private CallbackManager mCallbackManager;
    private FirebaseAuth mFirebaseAuth;

    LoginButton fbLoginbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.app_name));

        firebaseAuthe = com.google.firebase.auth.FirebaseAuth.getInstance();
        callbackManager = CallbackManager.Factory.create();

        authStateListener = new FirebaseAuth.AuthStateListener(){
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {

                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
            }
        };

        GoogleSignInOptions gso =  new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.web_client_id))//you can also use R.string.default_web_client_id
                .requestEmail()
                .build();

        googleApiClient=new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso).build();

        signInButton = findViewById(R.id.bt_sign_in11);
        fb1 = findViewById(R.id.fb1);

        fbLoginbtn=findViewById(R.id.fbloginbtn);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(intent,RC_SIGN_IN);
            }
        });


        TextView signinotp=findViewById(R.id.otpLogin);

         signinotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this, Otp_Activity.class);
                startActivity(intent);
//                finish();
            }
        });


        ///new line----
        OptionalPendingResult<GoogleSignInResult> login = Auth.GoogleSignInApi.silentSignIn(googleApiClient);

        if (login.isDone()) {
            GoogleSignInResult result = login.get();
            handleSignInResult(result);
        } else {
            login.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(@NonNull GoogleSignInResult googleSignInResult) {
                    handleSignInResult(googleSignInResult);
                }
            });
        }
        printHashKey();


        FacebookSdk.sdkInitialize(LoginActivity.this);
        callbackManager = CallbackManager.Factory.create();


        fbLoginbtn.setReadPermissions("name", "email", "id");


//        fbLoginbtn.setOnClickListener(new View.OnClickListener() {
        fb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
//                facebookLogin();
                loginManager.logInWithReadPermissions(LoginActivity.this, Arrays.asList("email", "public_profile", "user_id"));
//                loginManager.logInWithReadPermissions(LoginActivity.this, Arrays.asList("name", "email", "id"));

            }
        });

        mFirebaseAuth = FirebaseAuth.getInstance();
        mCallbackManager = CallbackManager.Factory.create();
        fbLoginbtn=findViewById(R.id.fbloginbtn);
        fbLoginbtn.setReadPermissions("public_profile","email", "user_birthday");

        fbLoginbtn.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                //Sign in completed
                Log.i(TAG, "onSuccess: logged in successfully");

                handleFacebookAccessToken(loginResult.getAccessToken());

                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {

                        if (object != null) {
                            try {
                                String name = object.getString("name");
                                String email = object.getString("email");
                                String birthday = object.getString("birthday");
                                String fbUserID = object.getString("id");

                                disconnectFromFacebook();
                                finish();
                                startActivity(new Intent(getApplicationContext(), ProductActivity.class));


                            }
                            catch (JSONException | NullPointerException e) {
                                e.printStackTrace();
                            }

                            Toast.makeText(getApplicationContext(), "Login "+email, Toast.LENGTH_SHORT).show();

                        }
                        // Application code
                        Log.i(TAG, "onCompleted: response: " + response.toString());
                        try {
                            String email = object.getString("email");
                            String birthday = object.getString("birthday");

                            Log.i(TAG, "onCompleted: Email: " + email);
                            Log.i(TAG, "onCompleted: Birthday: " + birthday);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.i(TAG, "onCompleted: JSON exception");
                        }
                    }
                });

                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender,birthday");
                request.setParameters(parameters);
                request.executeAsync();

            }
            @Override
            public void onCancel() {
                Log.d(TAG, "facebook:onCancel");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG, "facebook:onError", error);
            }
        });


    }



    public void printHashKey() {
        try {

            PackageInfo info = getPackageManager().getPackageInfo("com.marwaeltayeb.souq", PackageManager.GET_SIGNATURES);

            for (Signature signature : info.signatures) {

                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
        }
        catch (NoSuchAlgorithmException e) {
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);

        if(requestCode==RC_SIGN_IN){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result){
        if(result.isSuccess()){

            Log.e(TAG, "Login Successful. "+result);
            Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();

            GoogleSignInAccount account = result.getSignInAccount();
            idToken = account.getIdToken();
            name = account.getDisplayName();
            email = account.getEmail();
            // you can store user data to SharedPreference
            AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);

            Log.e(TAG, "Login Successful. "+result);
//            Toast.makeText(this, "Login Successful"+email, Toast.LENGTH_SHORT).show();
            firebaseAuthWithGoogle(credential);
        }else{
            // Google Sign In failed, update UI appropriately
            Log.e(TAG, "Login Unsuccessful. "+result);
//            Toast.makeText(this, "Login Unsuccessful", Toast.LENGTH_SHORT).show();
        }
    }
    private void firebaseAuthWithGoogle(AuthCredential credential){

        firebaseAuthe.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithCredential:onComplete:" + task.isSuccessful());
                        if(task.isSuccessful()){
//                            Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                            gotoProfile();
                        }else{
                            Log.w(TAG, "signInWithCredential" + task.getException().getMessage());
                            task.getException().printStackTrace();
                            Toast.makeText(LoginActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    private void gotoProfile(){
        Intent intent = new Intent(LoginActivity.this, ProductActivity.class);//RegisterActivity--checking
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    public void facebookLogin()
    {
        System.out.println("Panchai");
        loginManager = LoginManager.getInstance();
        System.out.println(loginManager);
        callbackManager  = CallbackManager.Factory.create();

        loginManager.registerCallback(callbackManager,new FacebookCallback<LoginResult>() {


            @Override
            public void onSuccess(LoginResult loginResult)
            {
                System.out.println("aaaaaaaaa");


                handleFacebookAccessToken(loginResult.getAccessToken());

                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                    
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response)
                    {

                        if (object != null) {
                            try {
                                String name = object.getString("name");
                                String email = object.getString("email");
                                String fbUserID = object.getString("id");

                                disconnectFromFacebook();
                                finish();
                                startActivity(new Intent(getApplicationContext(), ProductActivity.class));
                            }
                            catch (JSONException | NullPointerException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });


                Bundle parameters = new Bundle();
                parameters.putString("fields", "id, name, email, gender, birthday");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override

            public void onCancel()
            {
                System.out.println("0987654321");
                Log.v("LoginScreen", "---onCancel");
            }

            @Override
            public void onError(@NonNull FacebookException e) {
                System.out.println("ach");
                Log.v("LoginScreen", "----onError: "    + e.getMessage());
            }


             });
    }

    private void handleFacebookAccessToken(AccessToken accessToken) {

        AuthCredential credential = FacebookAuthProvider.getCredential(accessToken.getToken());
        firebaseAuthe.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithCredential:success11");
                            FirebaseUser user = firebaseAuthe.getCurrentUser();
                            Log.i(TAG, "onComplete: login completed with user: " + user.getDisplayName());

                        } else {

                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }



    public void disconnectFromFacebook()
    {
       if (AccessToken.getCurrentAccessToken() == null) {
       return; // already logged out
    }

        new GraphRequest(AccessToken.getCurrentAccessToken(), "/me/permissions/",
               null, HttpMethod.DELETE, new GraphRequest
                .Callback() {
                    @Override
                    public void onCompleted(GraphResponse graphResponse)
                    {
                        LoginManager.getInstance().logOut();
                    }
                })
                .executeAsync();
    }


/*
       LoginButton loginButton = findViewById(R.id.login_button);

        //Setting the permission that we need to read
        loginButton.setReadPermissions("public_profile","email", "user_birthday", "user_friends");

        //Registering callback!
        loginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                //Sign in completed
                Log.i(TAG, "onSuccess: logged in successfully");

                //Getting the user information
                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        // Application code
                        Log.i(TAG, "onCompleted: response: " + response.toString());
                        try {
                            String email = object.getString("email");
                            String birthday = object.getString("birthday");

                            Log.i(TAG, "onCompleted: Email: " + email);
                            Log.i(TAG, "onCompleted: Birthday: " + birthday);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.i(TAG, "onCompleted: JSON exception");
                        }
                    }
                });

                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender,birthday");
                request.setParameters(parameters);
                request.executeAsync();

            }

            @Override
            public void onCancel() {
                Log.d(TAG, "facebook:onCancel");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG, "facebook:onError", error);
            }
        });


 */

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mFirebaseAuth.getCurrentUser();
        if (currentUser != null) {
            Log.i(TAG, "onStart: Someone logged in <3");
        } else {
            Log.i(TAG, "onStart: No one logged in :/");
        }

//        if (authStateListener != null) {
//            FirebaseAuth.getInstance().signOut();
//            // }
//            firebaseAuthe.addAuthStateListener(authStateListener);
//        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (authStateListener != null){
            firebaseAuthe.removeAuthStateListener(authStateListener);
        }
    }

}


//// Get the values entered by the user
//int day = Integer.valueOf(DOBdate.getText().toString());
//int month = Integer.valueOf(DOBmonth.getText().toString()) - 1;
//int year = Integer.valueOf(DOByear.getText().toString());
//
//// Get the Editor object
//SharedPreferences.Editor editor = preferences.edit();
//
//// Store the date of birth data
//editor.putInt("day", day);
//editor.putInt("month", month);
//editor.putInt("year", year);
//
//// Apply the changes
//editor.apply();



//----------

//// Retrieve the date of birth data
//int day = preferences.getInt("day", 0);
//int month = preferences.getInt("month", 0);
//int year = preferences.getInt("year", 0);
//
//// Create a Calendar object and set the date of birth
//Calendar calendar = Calendar.getInstance();
//calendar.set(year, month, day);
//
//// Display the date of birth
//SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
//String dobString = dateFormat.format(calendar.getTime());
//TextView textView = findViewById(R.id.textView);
//textView.setText("Date of Birth: " + dobString);