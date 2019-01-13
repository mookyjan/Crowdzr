package com.example.mudassirkhan.crowdzr;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mudassirkhan.crowdzr.api.eventmessages.NetworkErrorLiveEvent;
import com.example.mudassirkhan.crowdzr.api.prefs.AccessTokenPrefs;
import com.example.mudassirkhan.crowdzr.api.prefs.AppPreferencesHelper;
import com.example.mudassirkhan.crowdzr.model.login.LoginResponse;
import com.example.mudassirkhan.crowdzr.ui.signup.SignupActivity;
import com.example.mudassirkhan.crowdzr.viewModel.LoginViewModel;
import com.google.gson.JsonObject;
import com.wang.avi.AVLoadingIndicatorView;


public class LoginActivity extends AppCompatActivity {
    public final String TAG=LoginActivity.class.getSimpleName();
    Button btnLogin;
    TextView txt_sign_up_here;
    AVLoadingIndicatorView mProgressView;
    EditText etEmail,etPassword;
    LoginViewModel mLoginViewModel;
    ConstraintLayout mContainerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        if (AppPreferencesHelper.get().getAccessToken()!=null)
//        if (!AppPreferencesHelper.get().getAccessToken().isEmpty()) {
//            Log.e(TAG,"token "+AppPreferencesHelper.get().getAccessToken());
//            navigateMainActivity(AppPreferencesHelper.get().getCurrentUserId());
//        } else {
            initViews();
            setupNetworkErrorMessage();
            onClick();
        //}
    }

    public void initViews(){
        etEmail=findViewById(R.id.edit_email);
        etPassword=findViewById(R.id.edit_password);
        btnLogin=(Button)findViewById(R.id.btnSignIn);
        txt_sign_up_here=findViewById(R.id.txt_sign_up_here);
        mProgressView=findViewById(R.id.signin_progress_loading);
        mContainerLayout=findViewById(R.id.login_layout);
        mLoginViewModel= ViewModelProviders.of(this).get(LoginViewModel.class);
    }

    private void validateLoginData(){
        etEmail.setError(null);
        etPassword.setError(null);
//      etRepeatPassword.setError(null);
        boolean cancel=false;
        View focusView=null;
        String username=etEmail.getText().toString();
        String password=etPassword.getText().toString();
        if (TextUtils.isEmpty(username)){
            etEmail.setError(getString(R.string.error_field_required));
            focusView=etEmail;
            cancel=true;
        }
        if (TextUtils.isEmpty(password)){
            etPassword.setError(getString(R.string.error_field_required));
            focusView=etPassword;
            cancel=true;
        }
        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.

            login(username,password);
        }
    }

    public void onClick(){
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               validateLoginData();
//                Intent myIntent=new Intent(LoginActivity.this,MainActivity.class);
//                startActivity(myIntent);
            }
        });

        txt_sign_up_here.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent=new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(myIntent);
                finish();
            }
        });
    }

    /**
     * login method
     */
    private void login(String email,String password){
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("username",email);
        jsonObject.addProperty("password",password);
        showProgressBar();
        LiveData<LoginResponse> signInModelLiveData=mLoginViewModel.login(jsonObject);
        signInModelLiveData.observe(this, new Observer<LoginResponse>() {
            @Override
            public void onChanged(@Nullable LoginResponse loginResponse) {
                Log.d(TAG,"login response "+loginResponse.getUser());
                if (loginResponse!=null) {
                    hideProgressBar();
                    if (loginResponse.isNetworkError()) {
                        Toast.makeText(LoginActivity.this,"Network Error ",Toast.LENGTH_SHORT).show();
                    } else if (loginResponse.getErrorMessage() == null) {
                       // if (AppPreferencesHelper.get().getAccessToken().equals("")) {
                            //Save Authorization token in shared preferences
                            AppPreferencesHelper.get().setAccessToken(loginResponse.getToken());
                            AppPreferencesHelper.get().setCurrentUserId(loginResponse.getUser().getId());
                            AppPreferencesHelper.get().setCurrentUserName(loginResponse.getUser().getDisplayName());
                            AppPreferencesHelper.get().setCurrentUserProfilePicUrl(loginResponse.getUser().getProfileImageURL());
                      //  }

                        Log.d(TAG,"token "+loginResponse.getToken());
                        navigateMainActivity(loginResponse.getUser().getId());
                    }

                }


            }
        });

    }

    private void setupNetworkErrorMessage() {
        mLoginViewModel.getObservableNetworkMessage().observe(this, new NetworkErrorLiveEvent.NetworkErrorObserver<String>() {

            @Override
            public void onNetworkError(String title, String message) {
                hideProgressBar();
                showNetworkError(message);
            }

            @Override
            public void onUnauthenticatedError(String title, String message) {
                hideProgressBar();
                showUnauthenticatedError(message);
            }

            @Override
            public void onClientError(String title, String message) {
                hideProgressBar();
                showClientError(message);
            }

            @Override
            public void onUnexpectedError(String title, String message) {
                hideProgressBar();
                showServerError(message);
            }
        });
    }


    private void showNetworkError(String message) {
        final Snackbar snackbar = Snackbar.make(mContainerLayout
                , message, Snackbar.LENGTH_LONG);

        View snackView = snackbar.getView();
        TextView tv = snackView.findViewById(
                android.support.design.R.id.snackbar_text);
        tv.setMaxLines(5);

        snackbar.setAction(getString(R.string.settings), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
                startActivity(new Intent(Settings.ACTION_SETTINGS));
            }
        });
        snackbar.setActionTextColor(getResources().getColor(R.color.colorAccent));

        snackbar.show();
    }

    private void showServerError(String message) {
        Snackbar.make(mContainerLayout, message, Snackbar.LENGTH_LONG).show();
    }

    private void showClientError(String message) {
        etEmail.setError(message);
        etEmail.requestFocus();
    }

    private void showUnauthenticatedError(String message) {
        etPassword.setError(message);
        etPassword.requestFocus();
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    void showProgressBar(){
        mProgressView.smoothToShow();
    }

    void hideProgressBar(){
        mProgressView.smoothToHide();
    }

    private void navigateMainActivity(String userId) {
        Log.d(TAG,"token user id "+userId);
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        intent.putExtra("userId", userId);
        startActivity(intent);
        finish();
    }

}
