package com.example.spotoparkapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class QRCode2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode2);
    }

    public void onCLickGoPagamento2 (View v){
        Intent intent = new Intent(getApplicationContext(), Pagamento2.class);
        startActivity(intent);
    }
}