package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.database.Cursor;
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

public class PhoneDetails extends AppCompatActivity   {


    ImageView img , cart,wish , cartf,wishf;
    TextView txt,price,desc;

    Button btn;



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
        cartf=findViewById(R.id.cart_image_full);
        wish=findViewById(R.id.wish_image_empty);
        wishf=findViewById(R.id.wish_image_full);


        db=openOrCreateDatabase("Shopping",MODE_PRIVATE,null);
        String tableQuery = "CREATE TABLE IF NOT EXISTS USER( USERID INTEGER PRIMARY KEY AUTOINCREMENT,NAME VARCHAR(30), EMAIL VARCHAR(30) ,CONTACT INT(10) , PASSWORD VARCHAR(20), CONFPASS VARCHAR(20), DOB VARCHAR(10) ,CITY VARCHAR(20) , GENDER VARCHAR(6))";
        db.execSQL(tableQuery);

        String cartQuery = "CREATE TABLE IF NOT EXISTS CART (CARTID INTEGER PRIMARY KEY AUTOINCREMENT , ORDERID INTEGER(10) ,USERID INTEGER (10), PRODUCTID INTEGER(10) , PRODUCTNAME VARCHAR(30) , PRODUCTIMG VARCHAR(100) , PRODUCTPRICE VARCHAR (20) ,PRODUCTDESC VARCHAR(2000), PRODUCTQTY INTEGER(10) ,TOTALPRICE VARCHAR(20))";
        db.execSQL(cartQuery);

        String wishQuery = "CREATE TABLE IF NOT EXISTS WISH (wishID INTEGER PRIMARY KEY AUTOINCREMENT ,USERID INTEGER (10), PRODUCTID INTEGER(10) , PRODUCTNAME VARCHAR(30) , PRODUCTIMG VARCHAR(100) , PRODUCTPRICE VARCHAR (20) ,  PRODUCTDESC VARCHAR(2000))";
        db.execSQL(wishQuery);


        String selectQuery = "SELECT * FROM CART WHERE USERID='"+sp.getString(commanclass.ID,"")+"' AND PRODUCTID='"+sp.getString(commanclass.PRODUCT_ID,"")+"'AND ORDERID='0'";
        Cursor cursor = db.rawQuery(selectQuery,null);
        if(cursor.getCount()>0){
            cart.setVisibility(View.GONE);
            cartf.setVisibility(View.VISIBLE);
        }
        else {
            cart.setVisibility(View.VISIBLE);
            cartf.setVisibility(View.GONE);
        }

        cartf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String deletc="DELETE FROM CART WHERE USERID='"+sp.getString(commanclass.ID,"")+"' AND PRODUCTID='"+sp.getString(commanclass.PRODUCT_ID,"")+"'AND ORDERID='0'";
                db.execSQL(deletc);
                new commanmethod(PhoneDetails.this , "Remove Item in Cart");
                cart.setVisibility(View.VISIBLE);
                cartf.setVisibility(View.GONE);
            }
        });
        //cart

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String select ="SELECT * FROM CART WHERE USERID='"+sp.getString(commanclass.ID,"")+"' AND PRODUCTID='"+sp.getString(commanclass.PRODUCT_ID,"")+"'AND ORDERID='0'";
                Cursor cursor=db.rawQuery(select,null);
                if (cursor.getCount()>0){
                    new commanmethod(PhoneDetails.this,"Product Alredy Added");
                }
                else {
                    int iQty = 1;
                    int iTotalPrice = Integer.parseInt(sp.getString(commanclass.PRODUCT_PRICE, "")) * iQty;
                    String insertQuery = "INSERT INTO CART VALUES(NULL,'0','" + sp.getString(commanclass.ID, "") + "','" + sp.getString(commanclass.PRODUCT_ID, "") + "','" + sp.getString(commanclass.PRODUCT_NAME, "") + "','" + sp.getInt(commanclass.PRODUCT_IMAGE, 0) + "','" + sp.getString(commanclass.PRODUCT_PRICE, "") + "','"+sp.getString(commanclass.PRODUCT_DESC,"")+"','" + iQty + "','" + iTotalPrice + "')";
                    db.execSQL(insertQuery);
                    new commanmethod(PhoneDetails.this, "Product Add in Cart");
                    cart.setVisibility(View.GONE);
                    cartf.setVisibility(View.VISIBLE);
                }
            }
        });


        //wish

        String selectQueryw = "SELECT * FROM WISH WHERE USERID='"+sp.getString(commanclass.ID,"")+"' AND PRODUCTID='"+sp.getString(commanclass.PRODUCT_ID,"")+"'";
        Cursor cursorw = db.rawQuery(selectQueryw,null);
        if(cursorw.getCount()>0){
            wish.setVisibility(View.GONE);
            wishf.setVisibility(View.VISIBLE);
        }
        else {
            wish.setVisibility(View.VISIBLE);
            wishf.setVisibility(View.GONE);
        }

        wishf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String deletw="DELETE FROM WISH WHERE USERID='"+sp.getString(commanclass.ID,"")+"' AND PRODUCTID='"+sp.getString(commanclass.PRODUCT_ID,"")+"'";
                db.execSQL(deletw);
                new commanmethod(PhoneDetails.this , "Remove Item in Cart");
                wish.setVisibility(View.VISIBLE);
                wishf.setVisibility(View.GONE);

            }


        });
        wish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String selectt ="SELECT * FROM WISH WHERE USERID='"+sp.getString(commanclass.ID,"")+"' AND PRODUCTID='"+sp.getString(commanclass.PRODUCT_ID,"")+"'";
                Cursor cursor=db.rawQuery(selectt,null);
                if (cursor.getCount()>0){
                    new commanmethod(PhoneDetails.this,"Product Alredy Added");
                }
                else {
                    String insertQueryt = "INSERT INTO WISH VALUES(NULL,'" + sp.getString(commanclass.ID, "") + "','" + sp.getString(commanclass.PRODUCT_ID, "") + "','" + sp.getString(commanclass.PRODUCT_NAME, "") + "','" + sp.getInt(commanclass.PRODUCT_IMAGE, 0) + "','" + sp.getString(commanclass.PRODUCT_PRICE, "") + "' , '"+sp.getString(commanclass.PRODUCT_DESC,"")+"')";
                    db.execSQL(insertQueryt);
                    new commanmethod(PhoneDetails.this, "Product Add in Wish");
                    wish.setVisibility(View.GONE);
                    wishf.setVisibility(View.VISIBLE);
                }

            }
        });


        txt.setText(sp.getString(commanclass.PRODUCT_NAME,""));
        desc.setText(sp.getString(commanclass.PRODUCT_DESC,""));
        price.setText(commanclass.PRODUCT_PRICE_SYMBOL+sp.getString(commanclass.PRODUCT_PRICE,""));
        img.setImageResource(sp.getInt(commanclass.PRODUCT_IMAGE,0));



        //pay online







        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              new commanmethod (PhoneDetails.this , order.class);
            }
        });
    }
//
//    private void startPayment() {
//
//        final Activity activity = this;
//
//        /**
//         * Pass your payment options to the Razorpay Checkout as a JSONObject
//         */
//        try {
//            JSONObject options = new JSONObject();
//
//            options.put("name", "Yash Kacha");
//            options.put("description", "Reference No. #123456");
//            options.put("image", R.drawable.my_img);
//            options.put("send_sms_hash", true);
//            options.put("allow_rotation", true);
//            options.put("theme.color", "#3399cc");
//            options.put("amount", Integer.parseInt(sp.getString(commanclass.PRODUCT_PRICE,""))*100);//pass amount in currency subunits
//
//
//            JSONObject pre = new JSONObject();
//            pre.put("email", sp.getString(commanclass.EMAIL,""));
//            pre.put("contact", sp.getString(commanclass.CONTACT,""));
//
//            options.put("pre",pre);
//
//            checkout.open(activity, options);
//
//        } catch(Exception e) {
//            Log.e("RESPONCE", "Error in starting Razorpay Checkout", e);
//        }
//    }
//
//    @Override
//    public void onPaymentSuccess(String s) {
//        new commanmethod(PhoneDetails.this ,"Payment Success");
//    }
//
//    @Override
//    public void onPaymentError(int i, String s) {
//        new commanmethod(PhoneDetails.this ,"Payment Faild");
//
//    }
}

