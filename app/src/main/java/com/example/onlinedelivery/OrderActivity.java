package com.example.onlinedelivery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class OrderActivity extends AppCompatActivity {
TextView location;
    private GpsTracker gpsTracker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        location=(TextView) findViewById(R.id.loc);
        try {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 101);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Geocoder geocoder;
        List<Address> addresses;
        String city = "";
        String main = "";
        String desc = "";
        double latitude = 0;
        double longitude = 0;
        geocoder = new Geocoder(this, Locale.getDefault());
        latitude = getLat(latitude);
        longitude = getLon(longitude);
        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 1);
            city = addresses.get(0).getLocality();// Here 1 represent max location result to returned, by documents it recommended 1 to 5
        } catch (IOException e) {
            e.printStackTrace();
        }
        // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
        location.setText(city);
    }
    public double getLat(double lat) {
        gpsTracker = new GpsTracker(OrderActivity.this);
        if (gpsTracker.canGetLocation()) {
            lat = gpsTracker.getLatitude();
        } else {
            gpsTracker.showSettingsAlert();
        }
        return lat;
    }

    public double getLon(double lon) {
        gpsTracker = new GpsTracker(OrderActivity.this);
        if (gpsTracker.canGetLocation()) {
            lon = gpsTracker.getLongitude();
        } else {
            gpsTracker.showSettingsAlert();
        }
        return lon;
    }
}