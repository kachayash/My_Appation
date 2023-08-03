package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class create_singup extends AppCompatActivity {
    EditText name,email,contact,password,confirmpassword;
    RadioGroup gender;
    Spinner city;
    String[] cityArray = {"Ahmedabad",
            "Surat",
           "Vadodara (Baroda)",
          "Rajkot",
           "Bhavnagar",
           "Jamnagar",
             "Junagadh",
            "Gandhinagar (Capital city of Gujarat)",
            "Anand",
            "Nadiad",
            "Bharuch",
            "Gandhidham",
            "Vapi",
            "Morbi",
            "Surendranagar",
            "Patan",
            "Navsari",
            "Mehsana",
            "Veraval",
            "Porbandar"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_singup);

//        name=findViewById(R.id.createacc_enterfullname);
//        email=findViewById(R.id.createacc_enteremailid);
//        contact=findViewById(R.id.createacc_enterphonenumber);
//        password=findViewById(R.id.createacc_enterpass);
//        confirmpassword=findViewById(R.id.createacc_enterconfpass);





        //Radio Button
        gender=findViewById(R.id.gender_singup);
        gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int i) {
                RadioButton radioButton = findViewById(i);//i=R.id.singup_male , R.id.singup_female;
                new commanmethod(create_singup.this , radioButton.getText().toString());
            }
        });


        //spinner
        city=findViewById(R.id.spinner_singup);
        ArrayAdapter adapter = new ArrayAdapter(create_singup.this, android.R.layout.simple_list_item_1 , cityArray);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_multiple_choice);
        city.setAdapter(adapter);

        city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                new commanmethod(create_singup.this , cityArray[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }
}