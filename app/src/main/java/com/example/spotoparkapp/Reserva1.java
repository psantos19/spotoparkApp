package com.example.spotoparkapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.spotoparkapp.downloaders.JSONArrayDownloader;
import com.example.spotoparkapp.downloaders.JSONObjDownloader;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;

public class Reserva1 extends AppCompatActivity {

    EditText text;
    Button button;
    ImageView QRcode;
    JSONArray Reserve = null;
    String tipolugar;

    ArrayAdapter<String> adapterType;
    ArrayList<String> listType;

    private ArrayList<Integer> lugares;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva1);

        //text = findViewById(R.id.inputText);
        button = findViewById(R.id.btQR);
        //QRcode = findViewById(R.id.imageQR);

        Spinner type = (Spinner) findViewById(R.id.spinnerType);

        listType = new ArrayList<>();
        listType.add("Escolha tipo de parque");
        listType.add("1 - Normal");
        listType.add("2 - Handicap");
        listType.add("3 - Eletric");
        /*listType.add("4 - Motorcycle");
        listType.add("5 - Covered"); */


        adapterType = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listType);
        adapterType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type.setAdapter(adapterType);

        tipolugar = type.getSelectedItem().toString();
        Log.e("Tipo Lugar = ", ""+tipolugar);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //vai ter que pegar o tipo de lugar
                // a hora para que quer reservar
                // por quanto tempo quer reservar
                JSONArrayDownloader task = new JSONArrayDownloader();
                Reserve = new JSONArray();

                Random r = new Random();
                Intent intent = new Intent(getApplicationContext(), Pagamento1.class);

                if (type.getSelectedItem().toString().equals("1 - Normal"))
                {
                    try
                    {
                        Reserve = task.execute("https://spotopark-projeto.herokuapp.com/api/parking_slot/parking1/1").get();
                        JSONObject obj;
                        lugares = new ArrayList<>();
                        for (int i = 0; i < Reserve.length(); i++)
                        {
                            obj =Reserve.getJSONObject(i);
                            lugares.add(obj.getInt("parkingSlotNumber"));
                        }
                        int randomNumber = r.nextInt(lugares.size());
                        MainActivity.Utilizador[1] = lugares.get(randomNumber).toString();



                    } catch (ExecutionException | InterruptedException | JSONException e) {
                        e.printStackTrace();
                    }
                    startActivity(intent);
                    Log.e("lugares = ", ""+lugares);


                }
                else if (type.getSelectedItem().toString().equals("2 - Para deficientes"))
                {
                    try
                    {
                        Reserve = task.execute("https://spotopark-projeto.herokuapp.com/api/parking_slot/parking1/2").get();
                        JSONObject obj;
                        lugares = new ArrayList<>();
                        for (int i = 0; i < Reserve.length(); i++)
                        {
                            obj =Reserve.getJSONObject(i);
                            lugares.add(obj.getInt("parkingSlotNumber"));
                        }
                        int randomNumber = r.nextInt(lugares.size());
                        MainActivity.Utilizador[1] = lugares.get(randomNumber).toString();



                    } catch (ExecutionException | InterruptedException | JSONException e) {
                        e.printStackTrace();
                    }
                    startActivity(intent);
                    Log.e("lugares = ", ""+lugares);
                }
                else if (type.getSelectedItem().toString().equals("3 - Elétrico"))
                {
                    try
                    {
                        Reserve = task.execute("https://spotopark-projeto.herokuapp.com/api/parking_slot/parking1/3").get();
                        JSONObject obj;
                        lugares = new ArrayList<>();
                        for (int i = 0; i < Reserve.length(); i++)
                        {
                            obj =Reserve.getJSONObject(i);
                            lugares.add(obj.getInt("parkingSlotNumber"));
                        }
                        int randomNumber = r.nextInt(lugares.size());
                        MainActivity.Utilizador[1] = lugares.get(randomNumber).toString();



                    } catch (ExecutionException | InterruptedException | JSONException e) {
                        e.printStackTrace();
                    }
                    startActivity(intent);
                    Log.e("lugares = ", ""+lugares);
                }
                else if (type.getSelectedItem().toString().equals("Escolha tipo de parque"))
                    Toast.makeText(Reserva1.this, "Escolha um tipo de parque!", Toast.LENGTH_SHORT).show();

                /*else if (type.getSelectedItem().toString().equals("4 - Motorcycle"))
                {
                    try
                    {
                        Reserve = task.execute("https://spotopark-projeto.herokuapp.com/api/parking_slot/parking1/4").get();
                        JSONObject obj;
                        lugares = new ArrayList<>();
                        for (int i = 0; i < Reserve.length(); i++)
                        {
                            obj =Reserve.getJSONObject(i);
                            lugares.add(obj.getInt("parkingSlotNumber"));
                        }
                        int randomNumber = r.nextInt(lugares.size());
                        MainActivity.Utilizador[1] = lugares.get(randomNumber).toString();



                    } catch (ExecutionException | InterruptedException | JSONException e) {
                        e.printStackTrace();
                    }
                    Log.e("lugares = ", ""+lugares);
                }
                else if (type.getSelectedItem().toString().equals("5 - Covered"))
                {
                    try
                    {
                        Reserve = task.execute("https://spotopark-projeto.herokuapp.com/api/parking_slot/parking1/5").get();
                        JSONObject obj;
                        lugares = new ArrayList<>();
                        for (int i = 0; i < Reserve.length(); i++)
                        {
                            obj =Reserve.getJSONObject(i);
                            lugares.add(obj.getInt("parkingSlotNumber"));
                        }
                        int randomNumber = r.nextInt(lugares.size());
                        MainActivity.Utilizador[1] = lugares.get(randomNumber).toString();



                    } catch (ExecutionException | InterruptedException | JSONException e) {
                        e.printStackTrace();
                    }
                    Log.e("lugares = ", ""+lugares);
                }*/

            }
        });
    }

    public void onCLickGoParque1 (View v){
        Intent intent = new Intent(getApplicationContext(), Parque1.class);
        startActivity(intent);
    }

}