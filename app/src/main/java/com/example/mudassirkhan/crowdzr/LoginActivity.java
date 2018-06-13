package com.example.mudassirkhan.crowdzr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
        onClick();
    }

    public void initViews(){
        btnLogin=(Button)findViewById(R.id.btnSignIn);
    }

    public void onClick(){
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent=new Intent(LoginActivity.this,HomeActivity.class);
                startActivity(myIntent);
            }
        });
    }
}
