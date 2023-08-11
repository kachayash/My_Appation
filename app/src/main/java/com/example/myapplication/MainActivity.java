package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {


    Button login;
    EditText email,password;
    TextView singup,singup2,forget;
    String emailp="[a-zA-Z0-9.-]+@[a-z]+\\.+[a-z]+";

    SQLiteDatabase db;

    SharedPreferences sp;
    CheckBox remember;

    ImageView hide,viewimg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login=findViewById(R.id.main_button);

        email=findViewById(R.id.main_email);
        password=findViewById(R.id.main_password);
        singup= findViewById(R.id.singup_main);
        singup2=findViewById(R.id.singup_main2);

        sp=getSharedPreferences(commanclass.PREF,MODE_PRIVATE);
        remember=findViewById(R.id.main_checkbox);

        forget=findViewById(R.id.main_forgetpassword);

        //image ppassword
        hide=findViewById(R.id.main_password_hideimg);
        viewimg=findViewById(R.id.main_password_viewimg);

        hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hide.setVisibility(View.GONE);
                viewimg.setVisibility(View.VISIBLE);

                password.setTransformationMethod(new PasswordTransformationMethod());
            }
        });
        viewimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hide.setVisibility(View.VISIBLE);
                viewimg.setVisibility(View.GONE);

                password.setTransformationMethod(null);
            }
        });


        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new commanmethod(MainActivity.this, forgetpassword.class);
            }
        });

        //DATABASE
        db=openOrCreateDatabase("Shopping",MODE_PRIVATE,null);
        String tableQuery = "CREATE TABLE IF NOT EXISTS USER( USERID INTEGER PRIMARY KEY AUTOINCREMENT,NAME VARCHAR(30), EMAIL VARCHAR(30) ,CONTACT INT(10) , PASSWORD VARCHAR(20), CONFPASS VARCHAR(20), DOB VARCHAR(10) ,CITY VARCHAR(20) , GENDER VARCHAR(6))";
        db.execSQL(tableQuery);

        //check validation

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
                    String select="SELECT * FROM USER WHERE EMAIL='"+email.getText().toString()+"' AND PASSWORD='"+password.getText().toString()+"'";
                    Cursor cursor=db.rawQuery(select,null);
//                    Log.d("Cursor",String.valueOf(cursor.getCount()));
                    if(cursor.getCount()>0){
                        while (cursor.moveToNext()) {
                            String sUserId = cursor.getString(0);
                            String sName = cursor.getString(1);
                            String sEmail = cursor.getString(2);
                            String sContact = cursor.getString(3);
                            String sPassword = cursor.getString(4);
                            String sDob = cursor.getString(6);
                            String sCity = cursor.getString(7);
                            String sGender = cursor.getString(8);

                            sp.edit().putString(commanclass.ID,sUserId).commit();
                            sp.edit().putString(commanclass.NAME,sName).commit();
                            sp.edit().putString(commanclass.CONTACT,sContact).commit();
                            sp.edit().putString(commanclass.EMAIL,sEmail).commit();
                            sp.edit().putString(commanclass.PASSWORD,sPassword).commit();
                            sp.edit().putString(commanclass.DOB,sDob).commit();
                            sp.edit().putString(commanclass.GENDER,sGender).commit();
                            sp.edit().putString(commanclass.CITY,sCity).commit();

                            if(remember.isChecked()){
                                sp.edit().putString(commanclass.REMEMBER,"Yes").commit();
                            }else{
                                sp.edit().putString(commanclass.REMEMBER,"").commit();
                            }




                            Log.d("USER_DATA", sUserId + "\n" + sName + "\n" + sContact + "\n" + sEmail + "\n" + sPassword + "\n" + sDob + "\n" + sGender + "\n" + sCity);

                        }
                    System.out.println("success");
                    new commanmethod(MainActivity.this , "Login Succesfull");
                    new commanmethod(MainActivity.this , deshbord.class);
                    }else{
                        new commanmethod(MainActivity.this,"Login Unsuccesfull");
                    }
                }
            }

        });
        singup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new commanmethod(MainActivity.this , create_singup.class);
            }
        });

        singup2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new commanmethod(MainActivity.this , create_singup.class);
            }
        });

    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
            finishAffinity();
    }
}