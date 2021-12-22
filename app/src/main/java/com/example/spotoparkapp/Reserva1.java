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
import java.util.concurrent.ExecutionException;

public class Reserva1 extends AppCompatActivity {

    EditText text;
    Button button;
    ImageView QRcode;
    JSONArray Reserve = null;
    String parkingslot;
    String tipolugar;

    ArrayAdapter<String> adapterType;
    ArrayList<String> listType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva1);

        text = findViewById(R.id.inputText);
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


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //vai ter que pegar o tipo de lugar
                // a hora para que quer reservar
                // por quanto tempo quer reservar
                JSONArrayDownloader task = new JSONArrayDownloader();
                Reserve = new JSONArray();

                tipolugar = type.getSelectedItem().toString();

                if (TextUtils.isEmpty(text.getText().toString())) {
                    text.setError("Type required!");
                }

                try {
                    Reserve = task.execute("https://spotopark-projeto.herokuapp.com/api/parking/parking_slot/1").get();

                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.e("Lugares", ""+Reserve);


                tipolugar = text.getText().toString(); // valor introduzido pelo utilizador

                JSONObject booking;

                /*for (int i = 0; i < Reserve.length(); i++) {
                    booking = Reserve.getJSONObject(i);


                    if (booking.getString("parkingTypeId:1").equals("parkingTypeId:" + text)) {

                        parkingslot = booking.getString("id");
                        break;

                    }
                }*/


                String[] lugares1 = {"11","12","13","14","15","16","17","18","19","20"};
                List<String> lugaresType1 = new ArrayList<>(Arrays.asList(lugares1));
                String[] lugares2 = {"1","2","3","4","5"};
                List<String> lugaresType2 = new ArrayList<>(Arrays.asList(lugares2));
                String[] lugares3 = {"6","7","8","9","10"};
                List<String> lugaresType3 = new ArrayList<>(Arrays.asList(lugares2));









                //Inicializar a multi format writer
               MultiFormatWriter writer = new MultiFormatWriter();
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
                }
            }
        });
    }

                public void onCLickGoParque1 (View v){
                    Intent intent = new Intent(getApplicationContext(), Parque1.class);
                    startActivity(intent);
                }


}