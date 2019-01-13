package com.example.mudassirkhan.crowdzr.ui.signup;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mudassirkhan.crowdzr.HomeActivity;
import com.example.mudassirkhan.crowdzr.LoginActivity;
import com.example.mudassirkhan.crowdzr.R;
import com.example.mudassirkhan.crowdzr.model.signup.SignupModel;
import com.example.mudassirkhan.crowdzr.viewModel.SignUpViewModel;
import com.google.gson.JsonObject;
import com.wang.avi.AVLoadingIndicatorView;

public class SignupActivity extends AppCompatActivity {

    EditText etFName,etLName,etUserName,etEmail,etPassword,etRepeatPassword;
    SignUpViewModel mSignUpViewModel;
    Button btnSignup;
    private AVLoadingIndicatorView mProgressView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        initViews();
        setupViewModel();
        onClick();
    }

    private void initViews(){
        etFName=findViewById(R.id.edit_first_name);
        etLName=findViewById(R.id.edit_last_name);
        etUserName=findViewById(R.id.edit_user_name);
        etEmail=findViewById(R.id.edit_email);
        etPassword=findViewById(R.id.edit_password);
        btnSignup=findViewById(R.id.btnSignUp);
        mProgressView=findViewById(R.id.signup_progress_loading);
    }

    private void onClick(){
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateSignupData();
            }
        });
    }

    private void setupViewModel(){
        mSignUpViewModel= ViewModelProviders.of(this).get(SignUpViewModel.class);
    }
    private void validateSignupData(){
      etEmail.setError(null);
      etPassword.setError(null);
//      etRepeatPassword.setError(null);
      boolean cancel=false;
      View focusView=null;
      String firstName=etFName.getText().toString();
      String lastName=etLName.getText().toString();
      String userName=etUserName.getText().toString();
      String email=etEmail.getText().toString();
      String password=etPassword.getText().toString();
//      String repeatPassword=etRepeatPassword.getText().toString();
        if (TextUtils.isEmpty(firstName)){
            etFName.setError(getString(R.string.error_password));
            focusView=etFName;
            cancel=true;
        }
        if (TextUtils.isEmpty(lastName)){
            etLName.setError(getString(R.string.error_field_required));
            focusView=etLName;
            cancel=true;
        }
        if (TextUtils.isEmpty(email)){
            etEmail.setError(getString(R.string.error_field_required));
            focusView=etEmail;
            cancel=true;
        }
        if (TextUtils.isEmpty(password)){
            etPassword.setError(getString(R.string.error_field_required));
            focusView=etPassword;
            cancel=true;
        }
        if (TextUtils.isEmpty(userName)){
            etUserName.setError(getString(R.string.error_field_required));
            focusView=etUserName;
            cancel=true;
        }
        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgressBar();
            signUp(firstName,lastName,userName,email,password);
        }

    }

    private void signUp(String firstName,String lastName,String userName,String email,String password){

        JsonObject jsonObjectSignup=new JsonObject();
        jsonObjectSignup.addProperty("firstName",firstName);
        jsonObjectSignup.addProperty("lastName",lastName);
        jsonObjectSignup.addProperty("username",userName);
        jsonObjectSignup.addProperty("email",email);
        jsonObjectSignup.addProperty("password",password);
        LiveData<SignupModel> signupModelLiveData=mSignUpViewModel.signUp(jsonObjectSignup);
        signupModelLiveData.observe(this, new Observer<SignupModel>() {
            @Override
            public void onChanged(@Nullable SignupModel signupModel) {
                 hideProgressBar();
                Log.d("SignupActvity","sign up response "+signupModel);
                   navigateMainActivity(signupModel);
            }
        });
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

    private void navigateMainActivity(SignupModel loginResponse) {
        Intent intent = new Intent(SignupActivity.this, HomeActivity.class);
        intent.putExtra("userId", loginResponse.getUser().getEmail());
        startActivity(intent);
        finish();
    }
}
