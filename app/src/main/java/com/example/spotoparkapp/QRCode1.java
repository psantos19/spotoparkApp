package com.example.spotoparkapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class QRCode1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode1);
    }

    public void onCLickGoPagamento1 (View v){
        Intent intent = new Intent(getApplicationContext(), Pagamento1.class);
        startActivity(intent);
    }
}