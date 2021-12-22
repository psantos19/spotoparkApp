package com.example.spotoparkapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.spotoparkapp.downloaders.JSONArrayDownloader;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;

public class Reserva2 extends AppCompatActivity {

    EditText text;
    Button button;
    ImageView QRcode;
    JSONArray Reserve = null;
    String parkingslot;
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
        QRcode = findViewById(R.id.imageQR);

        Spinner type = (Spinner) findViewById(R.id.spinnerType);

        listType = new ArrayList<>();

        listType.add("1 - Normal");
        listType.add("2 - Handicap");
        listType.add("3 - Eletric");
        listType.add("4 - Motorcycle");
        listType.add("5 - Covered");


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
                Intent intent = new Intent(getApplicationContext(), pagamento2.class);

                if (type.getSelectedItem().toString().equals(": 1 - Normal"))
                {
                    try
                    {
                        Reserve = task.execute("https://spotopark-projeto.herokuapp.com/api/parking_slot/parking2/1").get();
                        JSONObject obj;
                        lugares = new ArrayList<>();
                        for (int i = 0; i < Reserve.length(); i++)
                        {
                            obj =Reserve.getJSONObject(i);
                            lugares.add(obj.getInt("parkingSlotNumber"));
                        }
                        int randomNumber = r.nextInt(lugares.size());
                        MainActivity.Utilizador[1] = lugares.get(randomNumber).toString();
                        lugares.remove(randomNumber);



                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Log.e("lugares = ", ""+lugares);


                }
                else if (type.getSelectedItem().toString().equals("2 - Handicap"))
                {
                    try
                    {
                        Reserve = task.execute("https://spotopark-projeto.herokuapp.com/api/parking_slot/parking2/2").get();
                        JSONObject obj;
                        lugares = new ArrayList<>();
                        for (int i = 0; i < Reserve.length(); i++)
                        {
                            obj =Reserve.getJSONObject(i);
                            lugares.add(obj.getInt("parkingSlotNumber"));
                        }
                        int randomNumber = r.nextInt(lugares.size());
                        MainActivity.Utilizador[1] = lugares.get(randomNumber).toString();
                        lugares.remove(randomNumber);



                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Log.e("lugares = ", ""+lugares);
                }
                else if (type.getSelectedItem().toString().equals("3 - Eletric"))
                {
                    try
                    {
                        Reserve = task.execute("https://spotopark-projeto.herokuapp.com/api/parking_slot/parking2/3").get();
                        JSONObject obj;
                        lugares = new ArrayList<>();
                        for (int i = 0; i < Reserve.length(); i++)
                        {
                            obj =Reserve.getJSONObject(i);
                            lugares.add(obj.getInt("parkingSlotNumber"));
                        }
                        int randomNumber = r.nextInt(lugares.size());
                        MainActivity.Utilizador[1] = lugares.get(randomNumber).toString();
                        lugares.remove(randomNumber);



                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Log.e("lugares = ", ""+lugares);
                }
                else if (type.getSelectedItem().toString().equals("4 - Motorcycle"))
                {
                    try
                    {
                        Reserve = task.execute("https://spotopark-projeto.herokuapp.com/api/parking_slot/parking2/4").get();
                        JSONObject obj;
                        lugares = new ArrayList<>();
                        for (int i = 0; i < Reserve.length(); i++)
                        {
                            obj =Reserve.getJSONObject(i);
                            lugares.add(obj.getInt("parkingSlotNumber"));
                        }
                        int randomNumber = r.nextInt(lugares.size());
                        MainActivity.Utilizador[1] = lugares.get(randomNumber).toString();
                        lugares.remove(randomNumber);



                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Log.e("lugares = ", ""+lugares);
                }
                else if (type.getSelectedItem().toString().equals("5 - Covered"))
                {
                    try
                    {
                        Reserve = task.execute("https://spotopark-projeto.herokuapp.com/api/parking_slot/parking2/5").get();
                        JSONObject obj;
                        lugares = new ArrayList<>();
                        for (int i = 0; i < Reserve.length(); i++)
                        {
                            obj =Reserve.getJSONObject(i);
                            lugares.add(obj.getInt("parkingSlotNumber"));
                        }
                        int randomNumber = r.nextInt(lugares.size());
                        MainActivity.Utilizador[1] = lugares.get(randomNumber).toString();
                        lugares.remove(randomNumber);



                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Log.e("lugares = ", ""+lugares);
                }
                startActivity(intent);





                //Inicializar a multi format writer
              /* MultiFormatWriter writer = new MultiFormatWriter();
                try {
                    //Inicializar a bit matrix
                    BitMatrix matrix = writer.encode(parkingslot, BarcodeFormat.QR_CODE, 400, 400);
                    //inicializar a barcode encoder
                    BarcodeEncoder encoder = new BarcodeEncoder();
                    //Inicializar bitmap
                    Bitmap bitmap = encoder.createBitmap(matrix);
                    //Colocar o bitmap numa imageview
                    QRcode.setImageBitmap(bitmap);
                    //Inicializar o input manager
                    InputMethodManager manager = (InputMethodManager)  getSystemService(
                            Context.INPUT_METHOD_SERVICE
                    );
                    manager.hideSoftInputFromWindow(text.getApplicationWindowToken(), 0);
                } catch (WriterException e) {
                    e.printStackTrace();
                }*/
            }
        });
    }

    public void onCLickGoParque2(View v) {
        Intent intent = new Intent(getApplicationContext(), Parque2.class);
        startActivity(intent);
    }
}