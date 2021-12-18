package com.example.spotoparkapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Parque1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parque1);
    }

    public void onClickGoMaps(View v) {
        Intent intent = new Intent(getApplicationContext(), Maps.class);
        startActivity(intent);
    }

    public void onCLickGoReserva1(View v) {
        Intent intent = new Intent(getApplicationContext(), Reserva1.class);
        startActivity(intent);
    }
}