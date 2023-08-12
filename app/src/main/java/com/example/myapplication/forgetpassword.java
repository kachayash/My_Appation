package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class forgetpassword extends AppCompatActivity {
    EditText email;
    ImageView check;
    Button submit;
    TextView back;
    SQLiteDatabase db;
    SharedPreferences sp;

    String emailp="[a-zA-Z0-9.-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword);


            email=findViewById(R.id.email_forget);
            submit=findViewById(R.id.forget_button);
            back=findViewById(R.id.backlogin_forget);
            check=findViewById(R.id.check_forget);


        db=openOrCreateDatabase("Shopping",MODE_PRIVATE,null);
        String tableQuery = "CREATE TABLE IF NOT EXISTS USER( USERID INTEGER PRIMARY KEY AUTOINCREMENT,NAME VARCHAR(30), EMAIL VARCHAR(30) ,CONTACT INT(10) , PASSWORD VARCHAR(20), CONFPASS VARCHAR(20), DOB VARCHAR(10) ,CITY VARCHAR(20) , GENDER VARCHAR(6))";
        db.execSQL(tableQuery);

        sp=getSharedPreferences(commanclass.PREF,MODE_PRIVATE);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (email.getText().toString().trim().equals("")) {
                    email.setError("Email Id Required");
                } else if (!email.getText().toString().trim().matches(emailp)) {
                    email.setError("Valid Email Id Required");
                }else {
                    String select="SELECT * FROM USER WHERE EMAIL='"+email.getText().toString()+"'";
                    Cursor cursor=db.rawQuery(select,null);
                    if(cursor.getCount()>0){
                        check.setVisibility(View.VISIBLE);
                        new commanmethod(forgetpassword.this, newpassword.class);
                    }
                    else {
                        email.setError("Enter Valid Email Id");
                        new commanmethod(forgetpassword.this,"Try again latter");

                    }
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new commanmethod(forgetpassword.this, LoginActivity.class);
            }
        });
    }
}