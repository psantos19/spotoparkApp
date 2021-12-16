package com.example.spotoparkapp;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import com.example.spotoparkapp.downloaders.JSONArrayDownloader;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class Maps extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMap.OnInfoWindowClickListener {

    GoogleMap mMap;
    MapView mapView;
    LocationManager locationManager;
    LocationListener locationListener;
    double lati, longi;
    SearchView searchView;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);


        mapView = findViewById(R.id.mapView);
        mapView.getMapAsync(this);
        mapView.onCreate(savedInstanceState);






        // 1 - Criar o location Manager para ir buscar a localização do nosso dispositivo
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        // 2 - Criar um location Listener para detetar mudanças na nossa localização
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                LatLng userLocation = new LatLng(location.getLatitude(), location.getLongitude());
            }


            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        // 3 - Detetar se o utilizador nos deu permissões para obter a localização
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    1);
        } else {
            locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    0,
                    0,
                    locationListener
            );
        }
    }


    // 4 - Verificar se temos permissões ao executar a nossa Activity
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                locationManager.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER,
                        0,
                        0,
                        locationListener
                );
            }
        }
    }


    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        LatLng santos = new LatLng(38.7071236,-9.1525369);
        LatLng userLocation = new LatLng(lati, longi);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(santos, 15));

        //When map is loaded
        LatLng parque1 = new LatLng(38.706984, -9.151735);
        Marker markerOne = googleMap.addMarker(new MarkerOptions().position(parque1).title("Fazer Reserva").snippet("SpoToPark Parque 1")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        this.mMap.setOnInfoWindowClickListener((GoogleMap.OnInfoWindowClickListener) this);

        LatLng parque2 = new LatLng(38.708030, -9.147979);
        Marker markerTwo = googleMap.addMarker(new MarkerOptions().position(parque2).title("Fazer Reserva").snippet("SpoToPark Parque 2")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        this.mMap.setOnInfoWindowClickListener((GoogleMap.OnInfoWindowClickListener) this);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        mMap.setMyLocationEnabled(true);
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        googleMap.getUiSettings().setMapToolbarEnabled(true);
        googleMap.getUiSettings().setCompassEnabled(true);
        googleMap.getUiSettings().setZoomGesturesEnabled(true);

    }

     public void onClickSearch(View v)
     {
         switch (v.getId())
         {
             case R.id.imageButton3:
                 EditText locationsearch = (EditText) findViewById(R.id.SearchBar);
                 String location = locationsearch.getText().toString();

                 List<Address> addressList = null;


                 if (!TextUtils.isEmpty(location))
             {
                 Geocoder geocoder = new Geocoder(this);

                 try {
                     addressList = geocoder.getFromLocationName(location, 6);

                     if (addressList != null) {

                         for (int i = 0; i < addressList.size(); i++) {
                             Address address = addressList.get(i);
                             LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                             mMap.addMarker(new MarkerOptions().position(latLng).title(location));
                             mMap.animateCamera(CameraUpdateFactory.zoomTo(20));
                             mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));


                         }
                     }else
                         {
                         Toast.makeText(this, "Location not found", Toast.LENGTH_SHORT).show();
                     }
                 }
                 catch(IOException e)
                 {
                     e.printStackTrace();
                 }
             }
                 else
             {
                 Toast.makeText(this, "You need to insert destination", Toast.LENGTH_SHORT).show();
             }
                 break;
         }
     }



    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    public void onClickGoMenu(View v) {
        Intent intent = new Intent(getApplicationContext(), Menu.class);
        startActivity(intent);
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Intent intent = new Intent(getApplicationContext(), Reserva.class);
        startActivity(intent);
    }

    @Override
    public boolean onMarkerClick(@NonNull Marker marker) {
        return false;
    }
}