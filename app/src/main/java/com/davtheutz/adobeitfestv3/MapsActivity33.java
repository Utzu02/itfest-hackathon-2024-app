package com.davtheutz.adobeitfestv3;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.davtheutz.adobeitfestv3.databinding.ActivityMaps33Binding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity33 extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMaps33Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMaps33Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        initGhostReporting(mMap);
        ImageButton button = (android.widget.ImageButton) findViewById(R.id.imageButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("BUTTONS", "Adauga fantoma");
            }
        });
        Button zoomInButton = (Button) findViewById(R.id.zoomInBut);
        zoomInButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("BUTTONS", "Adauga fantoma");
            }
        });
        Button zoomOutButton = (Button) findViewById(R.id.zoomOutBut);
        zoomOutButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                zoomOut();
            }
        });

    }

    private void initGhostReporting(GoogleMap mMap)
    {
        // Testing create marker on click
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(@NonNull LatLng latLng) {
                Marker ghostMarker = mMap.addMarker(new MarkerOptions().position(latLng).title("Spotted Ghost"));
                //ghostMarker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.ghost));
            }
        });
    }

    public void zoomIn()
    {
        CameraUpdateFactory.zoomIn();
    }

    public void zoomOut()
    {
        CameraUpdateFactory.zoomOut();
        CameraUpdateFactory.zoomTo(15);
    }

}