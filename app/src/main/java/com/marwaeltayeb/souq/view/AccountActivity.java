package com.marwaeltayeb.souq.view;

import static com.marwaeltayeb.souq.storage.LanguageUtils.getEnglishState;
import static com.marwaeltayeb.souq.storage.LanguageUtils.loadLocale;
import static com.marwaeltayeb.souq.storage.LanguageUtils.setEnglishState;
import static com.marwaeltayeb.souq.storage.LanguageUtils.setLocale;
import static com.marwaeltayeb.souq.utils.CommunicateUtils.rateAppOnGooglePlay;
import static com.marwaeltayeb.souq.utils.CommunicateUtils.shareApp;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

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
import com.marwaeltayeb.souq.R;
import com.marwaeltayeb.souq.databinding.ActivityAccountBinding;
import com.marwaeltayeb.souq.storage.LoginUtils;
import com.marwaeltayeb.souq.utils.FlagsManager;
import com.marwaeltayeb.souq.viewmodel.DeleteUserViewModel;
//import com.marwaeltayeb.souq.viewmodel.FromHistoryViewModel;

import java.io.IOException;

public class AccountActivity extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG = "AccountActivity";
    private DeleteUserViewModel deleteUserViewModel;
//    private FromHistoryViewModel fromHistoryViewModel;


    Button logoutBtn;
    TextView userName, userEmail, userId, userPh;
    ImageView profileImage;
    private GoogleApiClient googleApiClient;
    private GoogleSignInOptions gso;


    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale(this);
        ActivityAccountBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_account);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.my_account));

        deleteUserViewModel = ViewModelProviders.of(this).get(DeleteUserViewModel.class);
//        fromHistoryViewModel = ViewModelProviders.of(this).get(FromHistoryViewModel.class);

        binding.nameOfUser.setText(LoginUtils.getInstance(this).getUserInfo().getName());
        binding.emailOfUser.setText(LoginUtils.getInstance(this).getUserInfo().getEmail());

        binding.myOrders.setOnClickListener(this);
        binding.myWishList.setOnClickListener(this);
        binding.languages.setOnClickListener(this);
        binding.helpCenter.setOnClickListener(this);
        binding.shareWithFriends.setOnClickListener(this);
        binding.rateUs.setOnClickListener(this);
        binding.changePassword.setOnClickListener(this);
        binding.deleteAccount.setOnClickListener(this);


        userName = findViewById(R.id.acname1);
        userEmail = findViewById(R.id.acemail1);

        userId = findViewById(R.id.acuserId);
        profileImage = findViewById(R.id.acprofileImage1);

        userPh=findViewById(R.id.acuserphone);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
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
                Toast.makeText(getApplicationContext(), "", Toast.LENGTH_LONG).show();
            }

        } else {
//            gotoMainActivity();

            NullPointerException e;
          //  Toast.makeText(getApplicationContext(), "", Toast.LENGTH_LONG).show();

//            signOut();
        }
    }

    private void gotoMainActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.account, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_signOut) {

            Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(
                    new ResultCallback<Status>() {
                        @Override
                        public void onResult(Status status) {
                            if (status.isSuccess()) {
//                                gotoMainActivity();
                                signOut();
                            } else {
                                Toast.makeText(getApplicationContext(), "Session not close", Toast.LENGTH_LONG).show();

                            }
                        }
                    });
//            signOut();
            deleteAllProductsInHistory();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void signOut() {

        FirebaseAuth.getInstance().signOut();
        LoginUtils.getInstance(this).clearAll();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);

    }

    private void deleteAllProductsInHistory() {
//       fromHistoryViewModel.removeAllFromHistory().observe(this, responseBody -> Log.d(TAG,getString(R.string.all_removed)));
       FlagsManager.getInstance().setHistoryDeleted(true);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
//            case R.id.myOrders:
////                Intent ordersIntent = new Intent(this, OrdersActivity.class);
////                startActivity(ordersIntent);
//                break;
//            case R.id.myWishList:
//                Intent wishListIntent = new Intent(this, WishListActivity.class);
//                startActivity(wishListIntent);
//                break;
//            case R.id.languages:
//                showCustomAlertDialog();
//                break;
//            case R.id.helpCenter:
//                Intent helpCenterIntent = new Intent(this, HelpActivity.class);
//                startActivity(helpCenterIntent);
//                break;
//            case R.id.shareWithFriends:
//                shareApp(this);
//                break;
//            case R.id.rateUs:
//                rateAppOnGooglePlay(this);
//                break;
//            case R.id.changePassword:
//                Intent passwordIntent = new Intent(this, PasswordActivity.class);
//                startActivity(passwordIntent);
//                break;
//            case R.id.deleteAccount:
//                deleteAccount();
//                break;
            default: // Should not get here
        }
    }

    private void deleteAccount() {
        deleteUserViewModel.deleteUser(LoginUtils.getInstance(this).getUserToken(), LoginUtils.getInstance(this).getUserInfo().getId()).observe(this, responseBody -> {
            if(responseBody!= null){
                LoginUtils.getInstance(getApplicationContext()).clearAll();
                try {
                    Toast.makeText(AccountActivity.this, responseBody.string() + "", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onResponse: delete account" + responseBody.string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                goToLoginActivity();
            }
        });
    }

    private void goToLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void showCustomAlertDialog() {
        final Dialog dialog = new Dialog(AccountActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_language_dialog);

        Button english = dialog.findViewById(R.id.txtEnglish);
        Button arabic = dialog.findViewById(R.id.txtArabic);

        if(getEnglishState(this)){
            english.setEnabled(false);
            english.setAlpha(.5f);
            arabic.setEnabled(true);
        }else {
            arabic.setEnabled(false);
            arabic.setAlpha(.5f);
            english.setEnabled(true);
        }

        english.setOnClickListener(v -> {
            english.setEnabled(true);
            chooseEnglish();
            dialog.cancel();
        });

        arabic.setOnClickListener(v -> {
            arabic.setEnabled(true);
            chooseArabic();
            dialog.cancel();
        });

        dialog.show();
    }

    private void chooseEnglish() {
        setLocale(this,"en");
        recreate();
        Toast.makeText(this, "English", Toast.LENGTH_SHORT).show();
        setEnglishState(this, true);
    }

    private void chooseArabic() {
        setLocale(this,"ar");
        recreate();
        Toast.makeText(this, "Arabic", Toast.LENGTH_SHORT).show();
        setEnglishState(this, false);
    }
}