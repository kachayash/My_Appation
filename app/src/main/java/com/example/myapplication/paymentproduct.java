package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.razorpay.Checkout;

import org.json.JSONObject;

public class paymentproduct extends AppCompatActivity {


    RadioGroup pytmethod;
    Button btn;
    String pyt;
    Checkout checkout;
    SharedPreferences sp;


    SQLiteDatabase db;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paymentproduct);

        Checkout.preload(getApplicationContext());
        checkout = new Checkout();
        checkout.setKeyID("rzp_test_Qv5hwbn2kL56Z7");

        pytmethod=findViewById(R.id.radiogroup_pyt_pp);
        btn=findViewById(R.id.continue_btn_pp);

        sp=getSharedPreferences(commanclass.PREF,MODE_PRIVATE);


//        db=openOrCreateDatabase("Shopping",MODE_PRIVATE,null);
//        String order = "CREATE TABLE IF NOT EXISTS ORDERADDRESS(ORDERA INTEGER PRIMARY KEY AUTOINCREMENT ,USERID INTEGER (10),CONUNTRY VARCHAR(20),STATE VARCHAR(20),CITY VARCHAR(30) , NAME VARCHAR(20) , CONTACT VARCHAR(10) , AREA VARCHAR(40) , LANDMARK VARCHAR(40) , PINCODE VARCHAR(6) , PAYMENTMETHOD VARCHAR(30))";
//        db.execSQL(order);
//
//        String tableQuery = "CREATE TABLE IF NOT EXISTS USER( USERID INTEGER PRIMARY KEY AUTOINCREMENT,NAME VARCHAR(30), EMAIL VARCHAR(30) ,CONTACT INT(10) , PASSWORD VARCHAR(20), CONFPASS VARCHAR(20), DOB VARCHAR(10) ,CITY VARCHAR(20) , GENDER VARCHAR(6))";
//        db.execSQL(tableQuery);

        pytmethod.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radioButton = findViewById(i);//i=R.id.singup_male , R.id.singup_female;
                pyt=radioButton.getText().toString();
                new commanmethod(paymentproduct.this ,pyt);


                if(i==R.id.upi_radio_pp){
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            new commanmethod(paymentproduct.this , "Not Available");
                        }
                    });
                }else if (i==R.id.cardit_card_radio_pp){
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            new commanmethod(paymentproduct.this , "Not Available");
                        }
                    });
                }else if (i==R.id.emi_radio_pp){
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            new commanmethod(paymentproduct.this , "Not Available");
                        }
                    });
                }else if (i==R.id.netbanking_radio_pp){
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            new commanmethod(paymentproduct.this , "Not Available");
                        }
                    });
                }else if (i==R.id.razorpay_radio_pp){
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startPayment();
                        }
                    });
                }else if (i==R.id.cod_radio_pp){
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            new commanmethod(paymentproduct.this , "Not Available");
                        }
                    });
                }
            }
        });


    }
    private void startPayment() {

        final Activity activity = this;

        try {
            JSONObject options = new JSONObject();

            options.put("name", "Yash Kacha");
            options.put("description", "Reference No. #123456");
            options.put("image", R.drawable.my_img);
            options.put("send_sms_hash", true);
            options.put("allow_rotation", true);
            options.put("theme.color", "#3399cc");
            options.put("amount", Integer.parseInt(sp.getString(commanclass.PRODUCT_PRICE,""))*100);//pass amount in currency subunits


            JSONObject pre = new JSONObject();
            pre.put("email", sp.getString(commanclass.EMAIL,""));
            pre.put("contact", sp.getString(commanclass.CONTACT,""));

            options.put("pre",pre);

            checkout.open(activity, options);

        } catch(Exception e) {
            Log.e("RESPONCE", "Error in starting Razorpay Checkout", e);
        }
    }

    public void onPaymentSuccess(String s) {
        new commanmethod(paymentproduct.this ,"Payment Success");
    }

    public void onPaymentError(int i, String s) {
        new commanmethod(paymentproduct.this ,"Payment Faild");

    }
}