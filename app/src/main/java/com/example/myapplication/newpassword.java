package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class newpassword extends AppCompatActivity {

    EditText password,confpass;
    ImageView view1,hide1,view2,hide2;

    Button newbtn;

    SQLiteDatabase db;
    SharedPreferences sp;

    TextView back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newpassword);

        password=findViewById(R.id.new_pass);
        confpass=findViewById(R.id.new_confpass);

        hide1=findViewById(R.id.newpass_password_hideimg);
        view1=findViewById(R.id.newpass_password_viewimg);

        hide2=findViewById(R.id.newpass2_password_hideimg);
        view2=findViewById(R.id.newpass2_password_viewimg);

        newbtn=findViewById(R.id.newpass_button);

        back=findViewById(R.id.backlogin_newpass);



        hide1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hide1.setVisibility(View.GONE);
                view1.setVisibility(View.VISIBLE);

                password.setTransformationMethod(new PasswordTransformationMethod());
            }
        });
        view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hide1.setVisibility(View.VISIBLE);
                view1.setVisibility(View.GONE);

                password.setTransformationMethod(null);
            }
        });



        hide2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hide2.setVisibility(View.GONE);
                view2.setVisibility(View.VISIBLE);

                confpass.setTransformationMethod(new PasswordTransformationMethod());
            }
        });
        view2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hide2.setVisibility(View.VISIBLE);
                view2.setVisibility(View.GONE);

                confpass.setTransformationMethod(null);
            }
        });

        db=openOrCreateDatabase("Shopping",MODE_PRIVATE,null);
        String tableQuery = "CREATE TABLE IF NOT EXISTS USER( USERID INTEGER PRIMARY KEY AUTOINCREMENT,NAME VARCHAR(30), EMAIL VARCHAR(30) ,CONTACT INT(10) , PASSWORD VARCHAR(20), CONFPASS VARCHAR(20), DOB VARCHAR(10) ,CITY VARCHAR(20) , GENDER VARCHAR(6))";
        db.execSQL(tableQuery);

        sp=getSharedPreferences(commanclass.PREF,MODE_PRIVATE);

        newbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (password.getText().toString().trim().equals("")){
                    password.setError("Enter Password");
                }
                else if(password.getText().toString().trim().length()<6){
                    password.setError("Enter 6 char password");
                }  else if (confpass.getText().toString().trim().equals("")) {
                    confpass.setError("Confirm Password Required");
                } else if (confpass.getText().toString().trim().length() < 6) {
                    confpass.setError("Min. 6 Char Confirm Password Required");
                } else if (!confpass.getText().toString().trim().matches(password.getText().toString().trim())) {
                    confpass.setError("Password Does Not Match");
                }else{

                    String update="UPDATE USER SET PASSWORD='"+password.getText().toString()+"' AND CONFPASS = '"+confpass.getText().toString()+"'";
                    db.execSQL(update);
                    new commanmethod(newpassword.this,"Generate Successfully");

                    sp.edit().putString(commanclass.PASSWORD,password.getText().toString()).commit();

                }

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new commanmethod(newpassword.this , MainActivity.class);
            }
        });




    }
}