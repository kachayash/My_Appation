package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

public class PhoneDetails extends AppCompatActivity  implements PaymentResultListener {


    ImageView img , cart,wish;
    TextView txt,price,desc;

    Button btn;

    Checkout checkout;

    SharedPreferences sp;

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_details);

        sp=getSharedPreferences(commanclass.PREF,MODE_PRIVATE);
        img=findViewById(R.id.details_phone_image);
        txt=findViewById(R.id.details_phone_text);
        price=findViewById(R.id.details_phone_price);
        desc=findViewById(R.id.details_phone_details);
        btn=findViewById(R.id.buy_now_phonedesc);
        cart=findViewById(R.id.cart_image_empty);
        wish=findViewById(R.id.wish_image_empty);


        txt.setText(sp.getString(commanclass.PRODUCT_NAME,""));
        desc.setText(sp.getString(commanclass.PRODUCT_DESC,""));
        price.setText(commanclass.PRODUCT_PRICE_SYMBOL+sp.getString(commanclass.PRODUCT_PRICE,""));
        img.setImageResource(sp.getInt(commanclass.PRODUCT_IMAGE,0));

        db=openOrCreateDatabase("Shopping",MODE_PRIVATE,null);
        String tableQuery = "CREATE TABLE IF NOT EXISTS USER( USERID INTEGER PRIMARY KEY AUTOINCREMENT,NAME VARCHAR(30), EMAIL VARCHAR(30) ,CONTACT INT(10) , PASSWORD VARCHAR(20), CONFPASS VARCHAR(20), DOB VARCHAR(10) ,CITY VARCHAR(20) , GENDER VARCHAR(6))";
        db.execSQL(tableQuery);



        String cartQuery = "CREATE TABLE IF NOT EXISTS CART (CARTID INTEGER PRIMARY KEY AUTOINCREMENT , ORDERID INTEGER(10) ,USERID INTEGER (10), PRODUCTID INTEGER(10) , PRODUCTNAME VARCHAR(30) , PRODUCTIMG VARCHAR(100) , PRODUCTDEC LONGTEXT , PRODUCTPRICE VARCHAR (20) , PRODUCTQTY INTEGER(10) ,TOTALPRICE VARCHAR(20))";
        db.execSQL(cartQuery);


//        String cartUp = "ALTER TABLE CART ADD  PRODUCTDEC LONGTEXT";
//        db.execSQL(cartUp);


        //cart
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int iQty=3;
                int iTotalPrice= Integer.parseInt(sp.getString(commanclass.PRODUCT_PRICE,""))*iQty;
                String insertQuery = "INSERT INTO CART VALUES(NULL,'0','" + sp.getString(commanclass.ID, "") + "','" + sp.getString(commanclass.PRODUCT_ID, "") + "','" + sp.getString(commanclass.PRODUCT_NAME, "") + "','" + sp.getInt(commanclass.PRODUCT_IMAGE, 0) + "','" + sp.getString(commanclass.PRODUCT_DESC, "") + "','" + sp.getString(commanclass.PRODUCT_PRICE, "") + "','" + iQty + "','" + iTotalPrice + "')";
                db.execSQL(insertQuery);
                new commanmethod(PhoneDetails.this,"Product Add in Cart");


            }
        });


        //pay online



        Checkout.preload(getApplicationContext());


        checkout = new Checkout();

        checkout.setKeyID("rzp_test_Qv5hwbn2kL56Z7");


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startPayment();
            }
        });
    }

    private void startPayment() {

        final Activity activity = this;

        /**
         * Pass your payment options to the Razorpay Checkout as a JSONObject
         */
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

    @Override
    public void onPaymentSuccess(String s) {
        new commanmethod(PhoneDetails.this ,"Payment Success");
    }

    @Override
    public void onPaymentError(int i, String s) {
        new commanmethod(PhoneDetails.this ,"Payment Faild");

    }
}

