package com.example.onlinedelivery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class RegisterActivity extends Activity {
    EditText etMail,etPass,etMobile,etName,etBdate,etCpass;
    Button btReg,btCan;
    FirebaseFirestore db;
    String email="",password="";
    private FirebaseAuth mAuth;
    private String TAG="RegisterActivity";
//    DatabaseReference dbRef=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        db = FirebaseFirestore.getInstance();

        etMail = findViewById(R.id.etMail);
        etPass = findViewById(R.id.etPass);
        etCpass = findViewById(R.id.etCpass);
        etMobile = findViewById(R.id.etMobile);
        etBdate = findViewById(R.id.etBdate);
        etName = findViewById(R.id.etName);





        btReg = findViewById(R.id.btRegister);
        btCan = findViewById(R.id.btCancel);

        mAuth=FirebaseAuth.getInstance();

        btReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email=etMail.getText().toString();
                password=etPass.getText().toString();
                System.out.println(email);
                System.out.println(password);
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "createUserWithEmail:success");
                                    validation();
                                    Intent intent=new Intent(RegisterActivity.this,HomePage.class);
                                    startActivity(intent);
//                                   FirebaseUser user = mAuth.getCurrentUser();
//                                   updateUI(user);`
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(RegisterActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
//                                    updateUI(null);
                                }
                            }
                        });
            }
        });
        btCan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


        //it will create new node name "student" in real time database.



    }
    private void validation() {
        /** Database connection **/
        String uname = etName.getText().toString();
        String phone = etMobile.getText().toString();
//        String address = Address.getText().toString();
        String pass=etPass.getText().toString();
        String cpass=etCpass.getText().toString();
        String email = etMail.getText().toString();

        if (uname.isEmpty()){
            etName.setError("Please Fill this Filed");
            etName.requestFocus();
        }
        if (phone.isEmpty()){
            etMobile.setError("Please Fill this Filed");
            etMobile.requestFocus();
        }

        if (email.isEmpty()){
            etMail.setError("Please Fill this Filed");
            etMail.requestFocus();
        }
        if (pass.isEmpty()){
            etPass.setError("Please Fill this Filed");
            etPass.requestFocus();
        }
        if (cpass.isEmpty()){
            etCpass.setError("Please Fill this Filed");
            etCpass.requestFocus();
        }
        else {
            Map<String,Object> user = new HashMap<>();
            user.put("Name",uname);
            user.put("Phone Number",phone);
            user.put("Email",email);
            // user.put("Confirm Password",c_pass);
            db.collection("user")
                    .add(user)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(RegisterActivity.this, "Successfull", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(RegisterActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    });

        }
    }
}