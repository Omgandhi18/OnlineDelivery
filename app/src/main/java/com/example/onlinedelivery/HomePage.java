package com.example.onlinedelivery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.squareup.picasso.Picasso;

public class HomePage extends AppCompatActivity {
    Button logout;
    ImageView img;
    TextView name,email;
    GoogleSignInClient mGoogleSignInClient;
    String personName, personGivenName, personFamilyName, personEmail, personId;
    Uri personPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        logout = (Button) findViewById(R.id.logout);
        name=(TextView)findViewById(R.id.name);
        email=(TextView)findViewById(R.id.email);
        img=(ImageView)findViewById(R.id.img);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            personName = acct.getDisplayName();
            personGivenName = acct.getGivenName();
            personFamilyName = acct.getFamilyName();
            personEmail = acct.getEmail();
            personId = acct.getId();
            personPhoto = acct.getPhotoUrl();
        }
        if(personPhoto !=null){
            Picasso.get().load(personPhoto).into(img);
        }
        else
        {
            img.setImageResource(R.drawable.avatar);
        }
        if(personName!=null){
            name.setText(personName);
        }
        else
        {
            name.setText("Please Sign in");
        }
        if(personEmail!=null){
            email.setText(personEmail);
        }
        else
        {
            email.setText("Please Sign in");
        }

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOut();
            }
        });
    }

    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Intent intent = new Intent(HomePage.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                        // ...
                    }
                });
    }
}