package com.example.spotoparkapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.spotoparkapp.downloaders.JSONObjDownloader;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class Reserva extends AppCompatActivity {

    /*JSONObject parking ;
    JSONObject parking_slot ;
    JSONObject parking_slot_type ;

    String park_address;
    String park_coordinates;

    String parking_slot_number;

    String type_name; */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva);

        /*JSONObjDownloader task = new JSONObjDownloader();

        try {
            parking = task.execute("https://spotopark-projeto.herokuapp.com/api/parking/parking_slot").get();
            park_address = parking.getString("address");
            park_coordinates = parking.getString("coordinates");
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        TextView address = (TextView) findViewById(R.id.address);
        TextView coordinates = (TextView) findViewById(R.id.coordinates);

        address.setText("Address: " + park_address);
        coordinates.setText("Coordinates: " + park_coordinates); */

    }
}