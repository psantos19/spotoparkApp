package com.example.spotoparkapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Parque2 extends AppCompatActivity {

    public static String PARK_ID = "2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parque2);
    }

    public void onClickGoMaps(View v) {
        Intent intent = new Intent(getApplicationContext(), Maps.class);
        startActivity(intent);
    }

    public void onCLickGoReserva2(View v) {
        Intent intent = new Intent(getApplicationContext(), Reserva2.class);
        startActivity(intent);
    }
}