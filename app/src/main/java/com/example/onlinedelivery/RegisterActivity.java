package com.example.onlinedelivery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class RegisterActivity extends Activity {
    EditText etMail,etPass,etMobile,etName,etBdate,etCpass;
    Button btReg,btCan;
    FirebaseAuth fAuth;
    DatabaseReference dbRef=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etMail = findViewById(R.id.etMail);
        etPass = findViewById(R.id.etPass);
        etCpass = findViewById(R.id.etCpass);
        etMobile = findViewById(R.id.etMobile);
        etBdate = findViewById(R.id.etBdate);
        etName = findViewById(R.id.etName);


        btReg = findViewById(R.id.btRegister);
        btCan = findViewById(R.id.btCancel);

        fAuth = FirebaseAuth.getInstance();
        dbRef = FirebaseDatabase.getInstance().getReference("User");
        //it will create new node name "student" in real time database.

        btReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mail = etMail.getText().toString();
                String pass = etPass.getText().toString();
                String cpass = etCpass.getText().toString();
                String name = etName.getText().toString();
                String mobile = etMobile.getText().toString();
                int bdate = Integer.parseInt(etBdate.getText().toString());

                String uid = dbRef.push().getKey();


                User st = new User(uid,mail,pass,name,mobile,bdate,cpass);

                dbRef.child(uid).setValue(st);


                fAuth.createUserWithEmailAndPassword(mail,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful())
                        {
                            Toast.makeText(getApplicationContext(),"User Registered Successfully",Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"User Registration error ",Toast.LENGTH_LONG).show();
                        }


                    }
                });



            }
        });

    }
}