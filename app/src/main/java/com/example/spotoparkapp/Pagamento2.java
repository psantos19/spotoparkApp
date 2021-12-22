package com.example.spotoparkapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Pagamento2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagamento2);
    }

    public void onCLickGoReserva2 (View v){
        Intent intent = new Intent(getApplicationContext(), Reserva2.class);
        startActivity(intent);
    }

    public void onCLickGoQRCode2 (View v){
        Intent intent = new Intent(getApplicationContext(), QRCode2.class);
        startActivity(intent);
    }
}