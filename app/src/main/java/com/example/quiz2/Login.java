package com.example.quiz2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    EditText email,password1 ;

    FirebaseAuth fauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button Login = (Button) findViewById(R.id.Login);
        Button signup = (Button) findViewById(R.id.Signup);
        email = findViewById(R.id.emails);
        password1 = findViewById(R.id.password);

        String emails=email.getText().toString();
        String password=password1.getText().toString();


        fauth = FirebaseAuth.getInstance();
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                String emails=email.getText().toString();
                String password=password1.getText().toString();

                if(emails.isEmpty()){
                    email.setError("Email is Required");
                    return;
                }

                if(password.isEmpty()){
                    password1.setError("Password is Required");
                    return;
                }

                if(password.length()<6){
                    password1.setError("Password is too weak");
                    return;
                }



                fauth.signInWithEmailAndPassword(emails,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()) {
                            Toast.makeText(Login.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Login.this,MainActivity.class));

                        }

                        else{
                            Toast.makeText(Login.this, "Error!"+task.getException(), Toast.LENGTH_SHORT).show();
                        }



                    }
                });






            }
        });



        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login.this, SignUp.class); startActivity(i);
            }
        });







    }
}