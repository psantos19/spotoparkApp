package com.example.spotoparkapp;

import android.content.Intent;
import android.net.Credentials;
import android.net.wifi.hotspot2.pps.Credential;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.spotoparkapp.downloaders.JSONArrayDownloader;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    //puxar do design
    private Button loginButton;
    private Button registerButton;
    private Button forgotPassword;
    EditText email, password;
    JSONArray LoginCredentials = null;
    public static String USER_ID;
    public static String[] Utilizador = new String[4];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ligar ao codigo
        loginButton = findViewById(R.id.loginButton);
        registerButton = findViewById(R.id.registerButton);
        forgotPassword = findViewById(R.id.forgotPassText);
        email = findViewById(R.id.emailLogin);
        password = findViewById(R.id.passwordLogin);

        //set texts para corrermos a aplicação sem termos de
        //estar sempre a introduzir um utilizador registado na base de dados
        email.setText("pedrosantos@gmail.com");
        password.setText("pedro2725");

        email.setText("utilizador@gmail.com");
        password.setText("utilizador");


        JSONArrayDownloader task = new JSONArrayDownloader();

        LoginCredentials = new JSONArray();
        try {
            LoginCredentials = task.execute("https://spotopark-projeto.herokuapp.com/api/utilizador").get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        Log.e("credentials:" , ""+ LoginCredentials);

    }

    public void onClickLogin(View v) throws JSONException {

        JSONArrayDownloader task = new JSONArrayDownloader();

        LoginCredentials = new JSONArray();
        try {
            LoginCredentials = task.execute("https://spotopark-projeto.herokuapp.com/api/utilizador").get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.e("credentials:" , ""+ LoginCredentials) ;

        JSONArray test = new JSONArray();
        String Email = email.getText().toString();
        String Password = password.getText().toString();

        if (TextUtils.isEmpty(Email)) {
            email.setError("Email required!");
        }
        if (Password.length() < 6 || TextUtils.isEmpty(Password)) {
            password.setError("Password is to short");
        }

        JSONObject utilizador;
        //vamos verificar se dentro do array existem as strings que o utilizador inseriu
        for (int i = 0; i < LoginCredentials.length(); i++) {
            utilizador = LoginCredentials.getJSONObject(i);


            if (utilizador.getString("email").equals(Email) && utilizador.getString("password").equals(Password)) {

                USER_ID = utilizador.getString("id");

                Intent intent = new Intent(getApplicationContext(), Maps.class);
                startActivity(intent);
                Log.e(String.valueOf(this), LoginCredentials.get(i).toString());

            } else if (utilizador.getString("email").isEmpty() && utilizador.getString("password").isEmpty()) {
                Toast.makeText(this, "Credenciais erradas!!! Verifique se está tudo bem!!!", Toast.LENGTH_SHORT).show();
            }else if (utilizador.getString("email").isEmpty()){
                Toast.makeText(this, "Credenciais erradas!!! Verifique se está tudo bem!!!", Toast.LENGTH_SHORT).show();
            }else if (utilizador.getString("password").isEmpty()){
                Toast.makeText(this, "Credenciais erradas!!! Verifique se está tudo bem!!!", Toast.LENGTH_SHORT).show();
            }else if (Email.isEmpty() || Password.isEmpty()) {
                Toast.makeText(this, "Credenciais erradas!!! Verifique se está tudo bem!!!", Toast.LENGTH_SHORT).show();
            }
            Utilizador[0] = USER_ID;
            Log.e("",""+Utilizador[0]);

        }

    }


        public void onClickRegister(View v){
            Intent intent = new Intent(getApplicationContext(), Register.class);
            startActivity(intent);

        }

        public void onClickForgotPassword(View v){
            Intent intent = new Intent(getApplicationContext(), ForgotPassword.class);
            startActivity(intent);
        }
    }