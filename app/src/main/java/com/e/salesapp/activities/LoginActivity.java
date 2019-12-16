package com.e.salesapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.e.salesapp.R;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity implements Response.ErrorListener, Response.Listener<JSONObject> {

    public static final String URL = "http://192.168.1.50:8080/token/login";
    private EditText mEt_username;
    private EditText mEt_password;

    private RequestQueue mRequestQueue;
    private JsonObjectRequest mJsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEt_username = findViewById(R.id.et_username_Li);
        mEt_password = findViewById(R.id.et_password_Li);

        mRequestQueue = Volley.newRequestQueue(this);
    }

    public void onClickSignUpBtn(View v) {
        Intent signUpIntent = new Intent(this, SignUpActivity.class);
        startActivity(signUpIntent);
    }

    public void onClickConfirmBtn(View v) {
        /*try {
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("username", mEt_username.getText().toString());
            jsonBody.put("password", mEt_password.getText().toString());
            final String mRequestBody = jsonBody.toString();

            Log.i("LoginError", mRequestBody);

            mJsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL, jsonBody, this, this) {
                @Override
                public String getBodyContentType() {
                    return "application/json";
                }
            };
            mRequestQueue.add(mJsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }*/

        //TODO
        Intent mainScreenIntent = new Intent(this, MainActivity.class);
        startActivity(mainScreenIntent);
        finish();
    }

    @Override
    public void onResponse(JSONObject response) {
        Log.e("Response", response.toString());
        Intent mainScreenIntent = new Intent(this, MainActivity.class);
        startActivity(mainScreenIntent);
        finish();
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.e("Response", error.toString());
        Toast.makeText(this, "Invalid data", Toast.LENGTH_SHORT).show();
    }
}
