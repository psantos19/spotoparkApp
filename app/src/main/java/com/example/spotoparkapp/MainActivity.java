package com.example.spotoparkapp;

import android.content.Intent;
import android.net.Credentials;
import android.net.wifi.hotspot2.pps.Credential;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.spotoparkapp.downloaders.JSONArrayDownloader;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    //puxar do design
    private Button loginButton;
    private Button registerButton;
    private Button forgotPassword;
    EditText email, password;
    JSONArray LoginCredentials = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ligar ao codigo
        loginButton = findViewById(R.id.loginButton);
        registerButton = findViewById(R.id.registerButton);
        forgotPassword = findViewById(R.id.forgotPassText);
        email = findViewById(R.id.emailLogin);
        password = findViewById(R.id.passwordLogin);
    }

    public void onClickLogin(View v) throws JSONException {
        String Email = email.getText().toString().trim();
        String Password = password.getText().toString().trim();

        if (TextUtils.isEmpty(Email)) {
            email.setError("Email required!");
        }
        if (Password.length() < 6 || TextUtils.isEmpty(Password)) {
            password.setError("Password is required");
        }
        // JSON array downloader
        JSONArrayDownloader task = new JSONArrayDownloader();

        //download spots
        try {
            LoginCredentials = task.execute("https://spotopark-projeto.herokuapp.com/api/utilizador").get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        JSONObject obj;
       /* utlizador_name = new ArrayList<>();
        utilizador_password = new ArrayList<>();
        utilizador_bdate = new ArrayList<>();
        utilizador_email = new ArrayList<>();
        utilizador_coordinates = new ArrayList<>();
        utilizador_id = new ArrayList<>();*/
        if (LoginCredentials != null) {
            for (int i = 0; i < LoginCredentials.length(); i++) {
                    /*if (LoginCredentials.get(i).toString().equals(Email) & LoginCredentials.get(i).toString().equals(Password)){
                        Intent intent = new Intent(getApplicationContext(), Maps.class);
                        startActivity(intent);*/
                Log.e(String.valueOf(this), LoginCredentials.get(i).toString());
                }
            }
        }


    public void onClickRegister(View v) {
        Intent intent = new Intent(getApplicationContext(), Register.class);
        startActivity(intent);

    }

    public void onClickForgotPassword(View v) {
        Intent intent = new Intent(getApplicationContext(), ForgotPassword.class);
        startActivity(intent);
    }
}