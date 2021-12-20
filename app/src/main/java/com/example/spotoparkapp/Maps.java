package com.example.spotoparkapp;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
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
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import com.example.spotoparkapp.databinding.ActivityMapsBinding;
import com.example.spotoparkapp.directions.DirectionResponses;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.PolyUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class Maps extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMap.OnInfoWindowClickListener, GoogleMap.OnMyLocationChangeListener  {

    GoogleMap mMap;
    MapView mapView;
    LocationManager locationManager;
    LocationListener locationListener;
    double lati, longi;
    SearchView searchView;
    private LatLng USERLOCATION;
    private ImageButton directions;
    private Marker markerOne;
    private Marker markerTwo;
    private PolylineOptions polyline1;
    private GoogleMap googleMap;
    ActivityMapsBinding binding;
    private ArrayList<Marker> markers;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());

        mapView = findViewById(R.id.mapView);
        mapView.getMapAsync(Maps.this);
        mapView.onCreate(savedInstanceState);

        directions = findViewById(R.id.imageButton32);
        directions = binding.imageButton32;

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
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        mMap.setOnMyLocationChangeListener((GoogleMap.OnMyLocationChangeListener) Maps.this);

        markers = new ArrayList<>();

        //When map is loaded
        LatLng parque1 = new LatLng(38.706984, -9.151735);
        markerOne = googleMap.addMarker(new MarkerOptions().position(parque1).title("Fazer Reserva 1").snippet("SpoToPark Parque 1")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));


        LatLng parque2 = new LatLng(38.708030, -9.147979);
        markerTwo = googleMap.addMarker(new MarkerOptions().position(parque2).title("Fazer Reserva 2").snippet("SpoToPark Parque 2")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

        markers.add(markerOne);
        markers.add(markerTwo);

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
                 Toast.makeText(this, "You need to insert a destination", Toast.LENGTH_SHORT).show();
             }
                 break;
         }
     }

    @Override
    public void onInfoWindowClick(Marker marker) {

        if (marker.getTitle().equals("Fazer Reserva 1")) {
            Intent intent = new Intent(getApplicationContext(), Parque1.class);
            startActivity(intent);
        }else if (marker.getTitle().equals("Fazer Reserva 2")) {
            Intent intent = new Intent(getApplicationContext(), Parque2.class);
            startActivity(intent);
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

    @Override
    public boolean onMarkerClick(@NonNull Marker marker) {

        //directions.setVisibility(View.VISIBLE);

        for (int i = 0; i < markers.size(); i++) {
            markerOne.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
            if (markers.get(i).getId().equals(marker.getId())) {
                LatLng location = markerOne.getPosition();
            }
        }
        marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));

        directions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.e("USERLOCATION", ""+USERLOCATION);
                String userlocationString = String.valueOf(USERLOCATION.latitude) + "," + String.valueOf(USERLOCATION.longitude);
                String end = String.valueOf(marker.getPosition().latitude) + "," + String.valueOf(marker.getPosition().longitude);

                ApiServices apiServices = RetrofitClient.apiServices(getApplicationContext());
                apiServices.getDirection(userlocationString, end, getString(R.string.api_key))
                        .enqueue(new Callback<DirectionResponses>() {

                            @Override
                            public void onResponse(Call<DirectionResponses> call, retrofit2.Response<DirectionResponses> response) {
                                drawPolyline(response);
                                Log.d("ok", response.message());
                            }

                            @Override
                            public void onFailure(@NonNull Call<DirectionResponses> call, @NonNull Throwable t) {
                                Log.e("error", t.getLocalizedMessage());
                            }
                        });
            }
        });

        return false;
    }

    @Override
    public void onMyLocationChange(@NonNull Location location) {
        USERLOCATION = new LatLng(location.getLatitude(), location.getLongitude());
        //Log.e("Sending updates", "" + USERLOCATION);
    }

    private void drawPolyline(@NonNull Response<DirectionResponses> response) {
        if (response.body() != null) {
            String shape = response.body().getRoutes().get(0).getOverviewPolyline().getPoints();
            polyline1 = new PolylineOptions()
                    .addAll(PolyUtil.decode(shape))
                    .width(8f)
                    .color(Color.RED);
            googleMap.addPolyline(polyline1);
        }
    }

    private interface ApiServices {
        @GET("maps/api/directions/json")
        Call<DirectionResponses> getDirection(@Query("origin") String origin,
                                              @Query("destination") String destination,
                                              @Query("key") String apiKey);
    }

    private static class RetrofitClient {
        static ApiServices apiServices(Context context) {
            Retrofit retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(context.getResources().getString(R.string.base_url))
                    .build();

            return retrofit.create(ApiServices.class);
        }
    }

    public void onClickGoMenu(View v) {
        Intent intent = new Intent(getApplicationContext(), Menu.class);
        startActivity(intent);
    }
}