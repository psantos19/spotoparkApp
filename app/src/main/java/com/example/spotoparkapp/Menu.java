package com.example.spotoparkapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void onClickGoMaps(View v) {
        Intent intent = new Intent(getApplicationContext(), Maps.class);
        startActivity(intent);
    }

    public void onClickGoProfile(View v) {
        Intent intent = new Intent(getApplicationContext(), Perfil.class);
        startActivity(intent);
    }

    public void onClickGoPagamento(View v) {
        Intent intent = new Intent(getApplicationContext(), Pagamento.class);
        startActivity(intent);
    }

    public void onClickGoSuporte(View v) {
        Intent intent = new Intent(getApplicationContext(), Suporte.class);
        startActivity(intent);
    }

    public void onClickGoAcerca(View v) {
        Intent intent = new Intent(getApplicationContext(), Acerca.class);
        startActivity(intent);
    }
}