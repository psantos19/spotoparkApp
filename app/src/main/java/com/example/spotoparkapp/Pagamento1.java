package com.example.spotoparkapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Pagamento1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagamento1);
    }

    public void onCLickGoReserva1 (View v){
        Intent intent = new Intent(getApplicationContext(), Reserva1.class);
        startActivity(intent);
    }

    public void onCLickGoQRCode1 (View v){
        Intent intent = new Intent(getApplicationContext(), QRCode1.class);
        startActivity(intent);
    }
}