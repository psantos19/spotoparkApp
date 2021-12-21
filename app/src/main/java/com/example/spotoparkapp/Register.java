package com.example.spotoparkapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.spotoparkapp.downloaders.GetPersons;
import com.example.spotoparkapp.downloaders.JSONArrayDownloader;
import com.example.spotoparkapp.downloaders.PostData;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class Register extends AppCompatActivity {

    EditText nome, password, email, bdate;
    Button BotaoRegisto;
    JSONArray RegisterCredentials;
    JSONArray Credentials;
    String utlizador_name;
    String utilizador_password;
    String utilizador_bdate;
    String utilizador_email;
    String utilizador_coordinates;
    String utilizador_id;
    String postBDate;
    protected LocationManager locationManager;
    protected LocationListener locationListener;
    protected Context context;
    String dayString;
    String monthString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        nome = findViewById(R.id.nomeRegister);
        password = findViewById(R.id.passwordRegister);
        email = findViewById(R.id.emailRegister);
        bdate = findViewById(R.id.bdateRegister);
        BotaoRegisto = findViewById(R.id.Registar);


        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        bdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(Register.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month + 1;
                        dayString = String.valueOf(day);
                        if ( day < 10){
                            dayString = "0" + day;
                        }
                        monthString = String.valueOf(month);
                        if ( month < 10){
                            monthString = "0" + month;
                        }

                        postBDate = year + "-" + monthString + "-" + dayString;
                        String date = year + "-" + monthString + "-" + dayString;
                        bdate.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });



        BotaoRegisto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetPersons getUtilizadores = new GetPersons();
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                try {

                    Credentials = getUtilizadores.execute("https://spotopark-projeto.herokuapp.com/api/utilizador").get();
                    JSONObject aux = new JSONObject(Credentials.get(0).toString());

                    if (nome.getText().toString().isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Favor preencher o campo em vermelho", Toast.LENGTH_SHORT).show();
                        nome.setHintTextColor(Color.RED);
                    }
                    if (bdate.getText().toString().isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Favor preencher o campo em vermelho", Toast.LENGTH_SHORT).show();
                        bdate.setHintTextColor(Color.RED);
                    }
                    if (password.getText().toString().isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Favor preencher o campo em vermelho", Toast.LENGTH_SHORT).show();
                        password.setHintTextColor(Color.RED);
                    }
                    if (email.getText().toString().isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Favor preencher o campo em vermelho", Toast.LENGTH_SHORT).show();
                        email.setHintTextColor(Color.RED);
                    }
                    else{

                        Map<String, String> postData = new HashMap<>();
                        postData.put("email", email.getText().toString());
                        postData.put("bdate", bdate.getText().toString());
                        postData.put("password", password.getText().toString());
                        postData.put("name", nome.getText().toString());

                        PostData task2 = new PostData(postData);
                        task2.execute("https://spotopark-projeto.herokuapp.com/api/utilizador/new");


                        Toast.makeText(getApplicationContext(), "Welcome ! " + nome.getText().toString(), Toast.LENGTH_SHORT).show();
                        Log.e("Id Sign up activity", ""+ postData.toString());

                        startActivity(i);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Credentials = null;
                }
            }
        });
    }

    public void onClickGoMain(View v) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}