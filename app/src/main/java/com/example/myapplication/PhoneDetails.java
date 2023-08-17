package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.SharedPreferences;
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


    ImageView img;
    TextView txt,price,desc;

    Button btn;

    Checkout checkout;

    SharedPreferences sp;

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


        txt.setText(sp.getString(commanclass.PRODUCT_NAME,""));
        desc.setText(sp.getString(commanclass.PRODUCT_DESC,""));
        price.setText(commanclass.PRODUCT_PRICE_SYMBOL+sp.getString(commanclass.PRODUCT_PRICE,""));
        img.setImageResource(sp.getInt(commanclass.PRODUCT_IMAGE,0));


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

