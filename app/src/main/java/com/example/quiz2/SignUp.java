package com.example.quiz2;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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


public class SignUp extends AppCompatActivity {

    FirebaseAuth fauth;
    EditText emails,password,cpassword  ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        emails= findViewById(R.id.email);
        password = findViewById(R.id.passwords);
        cpassword= findViewById(R.id.confirmpassword);

        String emailed=emails.getText().toString();
        String pass=password.getText().toString();
        String cpass=cpassword.getText().toString();



        fauth = FirebaseAuth.getInstance();
        Button btnregister = (Button) findViewById(R.id.regbutton);


        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailed=emails.getText().toString();
                String pass=password.getText().toString();
                String cpass=cpassword.getText().toString();



                if(TextUtils.isEmpty(emailed)){
                    emails.setError("email is required");
                    return;
                }
                if (!emailed.contains("@")){
                    emails.setError("Invalid email!");

                    return;
                }
                if (TextUtils.isEmpty(pass)){
                    password.setError("Required field!");

                    return;
                }
                if (TextUtils.isEmpty(cpass)){
                    cpassword.setError("Required field!");

                    return;
                }
                if (!cpass.equals(pass)){
                    cpassword.setError("Password should be equal to confirm password!");

                    return;
                }

                fauth.createUserWithEmailAndPassword(emailed,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {


                        if (task.isSuccessful()) {


                            fauth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful()) {
                                        Toast.makeText(SignUp.this, "send successfully", Toast.LENGTH_SHORT).show();
                                        Toast.makeText(SignUp.this, "User Created", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(getApplicationContext(),Login.class));







                                        finish();

                                    }

                                    else{

                                        Toast.makeText(SignUp.this, "Error!"+task.getException(), Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });












                        }




                    }

                });








            }
        });






    }
}

