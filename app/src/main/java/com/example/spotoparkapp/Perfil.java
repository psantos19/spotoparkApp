package com.example.spotoparkapp;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.example.spotoparkapp.downloaders.*;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class Perfil extends AppCompatActivity {

    JSONObject utilizador ;
    public static String utilizador_name;
    public static String utilizador_password;
    public static String utilizador_bdate;
    public static String utilizador_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        JSONObjDownloader task = new JSONObjDownloader();

        try {
            utilizador = task.execute("https://spotopark-projeto.herokuapp.com/api/utilizador/" + MainActivity.USER_ID).get();
            utilizador_name = utilizador.getString("name");
            utilizador_email = utilizador.getString("email");
            utilizador_bdate = utilizador.getString("bdate");
            Log.e("",""+ utilizador_name);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        TextView name = (TextView) findViewById(R.id.name);
        //TextView password = (TextView) findViewById(R.id.password);
        TextView bdate = (TextView) findViewById(R.id.bdate);
        TextView email = (TextView) findViewById(R.id.email);

        name.setText("Name: " + utilizador_name);
        //password.setText("Password: " + utilizador_password);
        bdate.setText("Data de Nascimento: " + utilizador_bdate);
        email.setText("Email: " + utilizador_email);

    }

    public void onClickGoMenu(View v) {
        Intent intent = new Intent(getApplicationContext(), Menu.class);
        startActivity(intent);
    }

}