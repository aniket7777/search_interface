package com.example.aniketshrivastava.searchinterface;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class LocationDisplay extends FragmentActivity implements OnMapReadyCallback {

    public static double latitude , longitude;



    void Coordinate (Double lati ,Double lon){
        latitude =  lati;
        longitude = lon;
        System.out.println(lati +" "+lon+"after cinversion "+latitude+" "+longitude);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_display);
        System.out.println("0 after conversion +"+latitude+" "+longitude);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        System.out.println("1 after conversion +"+latitude+" "+longitude);
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {


        // Add a marker in Sydney and move the camera
        LatLng latLng = new LatLng(latitude,longitude);
        System.out.println("3 after conversion +"+latitude+" "+longitude);

        googleMap.addMarker(new MarkerOptions().position(latLng));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMap.animateCamera( CameraUpdateFactory.zoomTo( 17.0f ) );


    }
}
