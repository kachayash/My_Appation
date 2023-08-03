package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class create_singup extends AppCompatActivity {
    EditText name,email,contact,password,confirmpassword;
    RadioGroup gender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        name=findViewById(R.id.createacc_enterfullname);
        email=findViewById(R.id.createacc_enteremailid);
        contact=findViewById(R.id.createacc_enterphonenumber);
        password=findViewById(R.id.createacc_enterpass);
        confirmpassword=findViewById(R.id.createacc_enterconfpass);
        gender=findViewById(R.id.gender_singup);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_singup);

        gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radioButton = findViewById(i);//i=R.id.singup_male , R.id.singup_female;
                new commanmethod(create_singup.this,radioButton.getText().toString());
            }
        });
    }
}