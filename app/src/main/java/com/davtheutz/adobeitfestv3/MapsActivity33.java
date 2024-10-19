package com.davtheutz.adobeitfestv3;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.davtheutz.adobeitfestv3.databinding.ActivityMaps33Binding;
import com.davtheutz.adobeitfestv3.utils.PermissionUtils;
import com.davtheutz.adobeitfestv3.utils.ReportingUtils;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapColorScheme;
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
        if(mBluetoothAdapter == null) return;
        if(!mBluetoothAdapter.isEnabled())
        {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }

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
    private final static int REQUEST_ENABLE_BT=1;
    BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        mMap.setMapColorScheme(MapColorScheme.DARK);
        zoomIn();zoomIn();zoomIn();zoomIn();zoomIn();zoomIn();zoomIn();
        ImageButton button = (android.widget.ImageButton) findViewById(R.id.imageButton);



        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(findViewById(R.id.navbarmonstri).getVisibility()==View.VISIBLE)
                {
                    findViewById(R.id.navbarmonstri).setVisibility(View.GONE);
                    return;
                }
                findViewById(R.id.navbarmonstri).setVisibility(View.VISIBLE);
            }
        });
        Button zoomInButton = (Button) findViewById(R.id.zoomInBut);
        zoomInButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                zoomIn();
            }
        });
        Button zoomOutButton = (Button) findViewById(R.id.zoomOutBut);
        zoomOutButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                zoomOut();
    Log.d("BUTTONS", "User tapped the Supabutton");
            }
        });
        Button closeNavButton = (Button) findViewById(R.id.closeNav);
        closeNavButton.setOnClickListener(new View.OnClickListener() {
            public  void onClick(View v) {
                findViewById(R.id.navbarmonstri).setVisibility(View.GONE);
            }
        });
        findViewById(R.id.vampir).setOnClickListener(new View.OnClickListener(){
            public  void onClick(View v) {
                clickButoane(R.drawable.vampir);
            }
        });
        findViewById(R.id.skeleton).setOnClickListener(new View.OnClickListener(){
            public  void onClick(View v) {
                clickButoane(R.drawable.skeleton);
            }
        });
        findViewById(R.id.fantoma).setOnClickListener(new View.OnClickListener(){
            public  void onClick(View v) {
                clickButoane(R.drawable.ghost_modified);
            }
        });
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        enableMyLocation(mMap);
    }
    public void clickButoane(int d) {
        BitmapDrawable bitmapDrawable = (BitmapDrawable) getResources().getDrawable(d);
        Bitmap bitmap = bitmapDrawable.getBitmap();
        Bitmap resized = Bitmap.createScaledBitmap(bitmap, 60, 60, false);
        BitmapDescriptor customMarker = BitmapDescriptorFactory.fromBitmap(resized);

        Marker ghostMarker = mMap.addMarker(new MarkerOptions().position(mMap.getCameraPosition().target).title("Spotted Ghost at " + ReportingUtils.getCurrentTimeFormatted()));
        ghostMarker.setIcon(customMarker);
    }
    @SuppressLint("MissingPermission")
    private void enableMyLocation(GoogleMap map) {
        // 1. Check if permissions are granted, if so, enable the my location layer
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            map.setMyLocationEnabled(true);
            return;
        }

        // 2. Otherwise, request location permissions from the user.
        ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode != 1) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            return;
        }

        if (PermissionUtils.isPermissionGranted(permissions, grantResults,
                Manifest.permission.ACCESS_FINE_LOCATION) || PermissionUtils
                .isPermissionGranted(permissions, grantResults,
                        Manifest.permission.ACCESS_COARSE_LOCATION)) {
            // Enable the my location layer if the permission has been granted.
            enableMyLocation(mMap);
        } else {
            // Permission was denied. Display an error message
            // ...
        }
    }

    public void zoomIn()
    {
        mMap.moveCamera(CameraUpdateFactory.zoomIn());
    }

    public void zoomOut()
    {
        mMap.moveCamera(CameraUpdateFactory.zoomOut());
    }

}