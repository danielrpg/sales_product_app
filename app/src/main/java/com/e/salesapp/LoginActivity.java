package com.e.salesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onClickSignUpBtn(View v){
        Intent signUpIntent = new Intent(this,SignUpActivity.class);
        startActivity(signUpIntent);
    }

    public void onClickConfirmBtn(View v){
        Intent mainScreenIntent = new Intent(this, MainActivity.class);
        startActivity(mainScreenIntent);
        finish();
    }
}
