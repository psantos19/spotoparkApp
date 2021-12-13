package com.example.spotoparkapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.spotoparkapp.downloaders.JSONArrayDownloader;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

public class Register extends AppCompatActivity {
    EditText nome, password, email, bdate;
    Button BotaoRegisto;
    JSONArray RegisterCredentials;
    String utlizador_name;
    String utilizador_password;
    String utilizador_bdate;
    String utilizador_email;
    String utilizador_coordinates;
    String utilizador_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        nome = findViewById(R.id.nomeRegister);
        password = findViewById(R.id.passwordRegister);
        email = findViewById(R.id.emailRegister);
        bdate = findViewById(R.id.bdateRegister);

    }

    public void onClickGoMain(View v) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    public void onClickRegComplete(View v) {
        String Nome = nome.getText().toString().trim();
        String Password = password.getText().toString().trim();
        String Email = email.getText().toString().trim();
        String Bdate = bdate.getText().toString().trim();

        if (TextUtils.isEmpty(Nome)) {
            nome.setError("Name required!");
        }
        if (TextUtils.isEmpty(Email)) {
            email.setError("Email required!");
        }
        if (Password.length() < 6) {
            password.setError("Password is required");
        }
        if (TextUtils.isEmpty(Bdate)) {
            bdate.setError("Insert date of birth");
        }
        // JSON array downloader
        JSONArrayDownloader task = new JSONArrayDownloader();

        //download spots
        try {
            RegisterCredentials = task.execute("https://spotopark-projeto.herokuapp.com/api/utilizador").get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        /*JSONObject obj;
        utlizador_name = new ArrayList<>();
        utilizador_password = new ArrayList<>();
        utilizador_bdate = new ArrayList<>();
        utilizador_email = new ArrayList<>();
        utilizador_coordinates = new ArrayList<>();
        utilizador_id = new ArrayList<>();
        if(RegisterCredentials != null) {
            for(int i = 0; i < RegisterCredentials.length(); i++) {
                try {
                    obj = RegisterCredentials.getJSONObject(i);
                    String spotname1 = obj.getString("name");
                    String spotdescription = obj.getString("description");
                    spotId.add(obj.getInt("id"));
                    spotName.add(obj.getString("name"));
                    spotLatitude.add(obj.getDouble("latitude"));
                    spotLongitude.add(obj.getDouble("longitude"));
                    spotDescription.add(obj.getString("description"));
                    LatLng Spots = new LatLng(spotLatitude.get(i), spotLongitude.get(i));
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }*/
    }
}