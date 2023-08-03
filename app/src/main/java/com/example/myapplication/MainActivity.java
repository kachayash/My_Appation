package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;


public class MainActivity extends AppCompatActivity {


    Button login;
    EditText email,password;
    TextView singup;
    String emailp="[a-zA-Z0-9.-]+@[a-z]+\\.+[a-z]+";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login=findViewById(R.id.main_button);

        email=findViewById(R.id.main_email);
        password=findViewById(R.id.main_password);
        singup= findViewById(R.id.singup_main);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (email.getText().toString().trim().equals("")){
                    email.setError("Email id Required");
                }

                else if(!email.getText().toString().trim().matches(emailp)){
                    email.setError("Valid Email id required");
                }
                else if (password.getText().toString().trim().equals("")){
                    password.setError("Password is Required");
                }
                else if(password.getText().toString().trim().length()<6){
                    password.setError("Enter 6 char password");
                }

                else{
                    System.out.println("success");
                    new commanmethod(MainActivity.this , "Login Succesfull");
                    new commanmethod(MainActivity.this , Homepage.class);
                }
            }

        });
        singup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new commanmethod(MainActivity.this , create_singup.class);
            }
        });
    }
}