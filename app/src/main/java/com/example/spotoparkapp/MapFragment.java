/*package com.example.spotoparkapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;


public class MapFragment extends Fragment {

    HashMap<String, String> markerMap = new HashMap<String, String>();
    double lati, longi;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Initialize view
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        //Initialize map fragment
        SupportMapFragment supportMapFragment = (SupportMapFragment)
                getChildFragmentManager().findFragmentById(R.id.google_map);

        //Async map
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(final GoogleMap googleMap) {
                LatLng santos = new LatLng(38.7071236,-9.1525369);
                LatLng userLocation = new LatLng(lati, longi);
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(santos, 14));

                //When map is loaded
                LatLng parque1 = new LatLng(38.706984, -9.151735);
                Marker markerOne = googleMap.addMarker(new MarkerOptions().position(parque1).title("Parque 1").snippet("SpoToPark"));

                LatLng parque2 = new LatLng(38.708030, -9.147979);
                Marker markerTwo = googleMap.addMarker(new MarkerOptions().position(parque2).title("Parque 2").snippet("SpoToPark"));

                googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

                    @Override
                    public void onMapClick(LatLng latLng) {
                        //When clicked on map
                        //Initialize marker options
                        MarkerOptions markerOptions = new MarkerOptions();
                        //Set position of marker
                        markerOptions.position(latLng);
                        //Set title of marker
                        markerOptions.title(latLng.latitude + " : " + latLng.longitude);

                        String firstid = markerOne.getId();
                        markerMap.put(firstid, "action_first");

                        String secondid = markerOne.getId();
                        markerMap.put(secondid, "action_second");

                        //Remove all marker
                        googleMap.clear();
                        //Animating to zoom the marker
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
                        //Add marker on map
                        googleMap.addMarker(markerOptions);
                    }
                });
            }
        });

        //Return view
        return view;
    }

    public void onClickForgotPassword(View v) {
        Intent intent = new Intent(getContext(), Menu.class);
        startActivity(intent);
    }
}*/