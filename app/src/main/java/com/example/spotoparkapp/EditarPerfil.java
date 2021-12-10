package com.example.spotoparkapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class EditarPerfil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_perfil);
    }

    public void onClickGoProfile(View v) {
        Intent intent = new Intent(getApplicationContext(), Profile.class);
        startActivity(intent);
    }
}