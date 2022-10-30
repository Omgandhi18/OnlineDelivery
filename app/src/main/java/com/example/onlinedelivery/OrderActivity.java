package com.example.onlinedelivery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class OrderActivity extends AppCompatActivity {
TextView location;
CardView beverages,bread,canned,milk,wheat,frozen;
    ImageView img1;
Uri personPhoto;
FirebaseAuth mAuth;
FirebaseFirestore db;
    GoogleSignInClient mGoogleSignInClient;
    private GpsTracker gpsTracker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        location=(TextView) findViewById(R.id.loc);
        beverages= (CardView) findViewById(R.id.beverages);
        bread= (CardView) findViewById(R.id.bread);
        canned= (CardView) findViewById(R.id.canned);
        milk= (CardView) findViewById(R.id.milk);
        wheat= (CardView) findViewById(R.id.wheat);
        frozen= (CardView) findViewById(R.id.frozen);
        img1=(ImageView)findViewById(R.id.img1);
        mAuth=FirebaseAuth.getInstance();
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {

            personPhoto = acct.getPhotoUrl();
        }
        if(personPhoto !=null){
            Picasso.get().load(personPhoto).into(img1);
        }
        else
        {
            img1.setImageResource(R.drawable.avatar);
        }


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
        beverages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1=new Intent(OrderActivity.this,FoodList.class);
                startActivity(i1);
            }
        });
        bread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2=new Intent(OrderActivity.this,FoodList.class);
                startActivity(i2);
            }
        });
        canned.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i3=new Intent(OrderActivity.this,FoodList.class);
                startActivity(i3);
            }
        });
        milk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i4=new Intent(OrderActivity.this,FoodList.class);
                startActivity(i4);
            }
        });
        wheat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i5=new Intent(OrderActivity.this,FoodList.class);
                startActivity(i5);
            }
        });
        frozen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i6=new Intent(OrderActivity.this,FoodList.class);
                startActivity(i6);
            }
        });
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(OrderActivity.this,HomePage.class);
                startActivity(intent);
                finish();
            }
        });

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