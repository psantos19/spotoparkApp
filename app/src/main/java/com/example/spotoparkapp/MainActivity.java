package com.example.spotoparkapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;

public class MainActivity extends AppCompatActivity {

    private Button loginButton;
    private Button registerButton;
    private Button forgotPassword;
    JSONArray arrayWeather = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*DownloadTask task = new DownloadTask();
        try {
            arrayWeather = task.execute("https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22").get();
        } catch (ExecutionException e) {
            e.printStackTrace();
            arrayWeather = null;
        } catch (InterruptedException e) {
            e.printStackTrace();
            arrayWeather = null;
        }*/

        loginButton = findViewById(R.id.loginButton);
        registerButton = findViewById(R.id.registerButton);
        forgotPassword = findViewById(R.id.forgotPassText);
    }

    public void onClickLogin(View v) {
        Intent intent = new Intent(getApplicationContext(), Maps.class);
        startActivity(intent);

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