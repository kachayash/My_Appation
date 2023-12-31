package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.razorpay.Checkout;

import org.json.JSONObject;

import java.util.ArrayList;

public class order extends AppCompatActivity {
    Checkout checkout;


    EditText name,phone,area,landamrk,pincode;
    Spinner country,state,city;
    Button btn,edit;

    ArrayList<String> countryname;
    ArrayList<String> statename;
    ArrayList<String> cityname;

    String scity,sstate,scountry;

    SharedPreferences sp;

    SQLiteDatabase db;

    RadioGroup pytmethod;
    RadioButton cod;
    String pyt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        name= findViewById(R.id.name_order);
        phone= findViewById(R.id.contact_order);
        area= findViewById(R.id.area_order);
        landamrk= findViewById(R.id.landmark_order);
        pincode= findViewById(R.id.pincode_order);
        country= findViewById(R.id.spinner_countary_order);
        state= findViewById(R.id.spinner_state_order);
        city= findViewById(R.id.spinner_city_order);
        btn= findViewById(R.id.button_order);
        cod=findViewById(R.id.cod_radio);
        pytmethod=findViewById(R.id.radiogroup_pyt);
        Checkout.preload(getApplicationContext());
        checkout = new Checkout();
        checkout.setKeyID("rzp_test_Qv5hwbn2kL56Z7");

//        edit= findViewById(R.id.newadress_button_order);
        sp=getSharedPreferences(commanclass.PREF,MODE_PRIVATE);


//        country
        countryname=new ArrayList<>();
        countryname.add("Select Country");
        countryname.add("India");
        countryname.add("China");
        countryname.add("United States");
        countryname.add("Indonesia");
        countryname.add("Pakistan");

        //state
        statename=new ArrayList<>();
        statename.add("Select State");
        statename.add("Andhra Pradesh");
        statename.add("Arunachal Pradesh");
        statename.add("Assam");
        statename.add("Bihar");
        statename.add("Chhattisgarh");
        statename.add("Goa");
        statename.add("Gujarat");
        statename.add("Haryana");
        statename.add("Himachal Pradesh");
        statename.add("Jharkhand");
        statename.add("Karnataka");
        statename.add("Kerala");
        statename.add("Madhya Pradesh");
        statename.add("Maharashtra");
        statename.add("Meghalaya");
        statename.add("Mizoram");
        statename.add("Nagaland");
        statename.add("Odisha");
        statename.add("Punjab");
        statename.add("Rajasthan");
        statename.add("Sikkim");
        statename.add("Tamil Nadu");
        statename.add("Telangana");
        statename.add("Tripura");
        statename.add("Uttar Pradesh");
        statename.add("Uttarakhand");
        statename.add("West Bengal");

        //city
        cityname= new ArrayList<>();
        cityname.add("Select City");
        cityname.add("Ahmedabad");
        cityname.add("Surat");
        cityname.add("Vadodara (Baroda)");
        cityname.add("Rajkot");
        cityname.add("Bhavnagar");
        cityname.add("Jamnagar");
        cityname.add("Junagadh");
        cityname.add("Gandhinagar (Capital city of Gujarat");
        cityname.add("Anand");
        cityname.add("Nadiad");
        cityname.add("Bharuch");
        cityname.add("Gandhidham");
        cityname.add("Vapi");
        cityname.add("Morbi");
        cityname.add("Surendranagar");
        cityname.add("Patan");
        cityname.add("Navsari");
        cityname.add("Mehsana");
        cityname.add("Veraval");
        cityname.add("Porbandar");


//
//        ArrayAdapter adaptercountry = ArrayAdapter.createFromResource(this,R.array.countryname,R.layout.spinnner_color);
//        adaptercountry.setDropDownViewResource(android.R.layout.simple_list_item_multiple_choice);

        ArrayAdapter adaptercountry = new ArrayAdapter(order.this, R.layout.spinner_list , countryname);
        adaptercountry.setDropDownViewResource(android.R.layout.simple_list_item_multiple_choice);
        country.setAdapter(adaptercountry);

        ArrayAdapter adapterstate = new ArrayAdapter(order.this, R.layout.spinner_list , statename);
        adapterstate.setDropDownViewResource(android.R.layout.simple_list_item_multiple_choice);
        state.setAdapter(adapterstate);

        ArrayAdapter adaptercity = new ArrayAdapter(order.this,  R.layout.spinner_list , cityname);
        adaptercity.setDropDownViewResource(android.R.layout.simple_list_item_multiple_choice);
        city.setAdapter(adaptercity);

        country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0){
                    scountry = "";
                }else{
                    scountry=countryname.get(i);
                    new commanmethod(order.this , countryname.get(i));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0){
                    sstate = "";
                }else{
                    sstate=statename.get(i);
                    new commanmethod(order.this , statename.get(i));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0){
                    scity = "";
                }else{
                    scity=cityname.get(i);
                    new commanmethod(order.this , cityname.get(i));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        db=openOrCreateDatabase("Shopping",MODE_PRIVATE,null);
        String order = "CREATE TABLE IF NOT EXISTS ORDERADDRESS(ORDERA INTEGER PRIMARY KEY AUTOINCREMENT ,USERID INTEGER (10),CONUNTRY VARCHAR(20),STATE VARCHAR(20),CITY VARCHAR(30) , NAME VARCHAR(20) , CONTACT VARCHAR(10) , AREA VARCHAR(40) , LANDMARK VARCHAR(40) , PINCODE VARCHAR(6) , PAYMENTMETHOD VARCHAR(30) ,TRANSACATIONID TEXT)";
        db.execSQL(order);

        String tableQuery = "CREATE TABLE IF NOT EXISTS USER( USERID INTEGER PRIMARY KEY AUTOINCREMENT,NAME VARCHAR(30), EMAIL VARCHAR(30) ,CONTACT INT(10) , PASSWORD VARCHAR(20), CONFPASS VARCHAR(20), DOB VARCHAR(10) ,CITY VARCHAR(20) , GENDER VARCHAR(6))";
        db.execSQL(tableQuery);

        String cartQuery = "CREATE TABLE IF NOT EXISTS CART (CARTID INTEGER PRIMARY KEY AUTOINCREMENT , ORDERID INTEGER(10) ,USERID INTEGER (10), PRODUCTID INTEGER(10) , PRODUCTNAME VARCHAR(30) , PRODUCTIMG VARCHAR(100) , PRODUCTPRICE VARCHAR (20) ,PRODUCTDESC VARCHAR(2000), PRODUCTQTY INTEGER(10) ,TOTALPRICE VARCHAR(20))";
        db.execSQL(cartQuery);

        String wishQuery = "CREATE TABLE IF NOT EXISTS WISH (wishID INTEGER PRIMARY KEY AUTOINCREMENT ,USERID INTEGER (10), PRODUCTID INTEGER(10) , PRODUCTNAME VARCHAR(30) , PRODUCTIMG VARCHAR(100) , PRODUCTPRICE VARCHAR (20) ,  PRODUCTDESC VARCHAR(2000))";
        db.execSQL(wishQuery);

//        String del="DELETE FROM ORDERADDRESS";
//        db.execSQL(del);

        //radio
        pytmethod.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radioButton = findViewById(i);//i=R.id.singup_male , R.id.singup_female;
                pyt=radioButton.getText().toString();
                new commanmethod(order.this ,pyt);
            }
        });



        name.setText(sp.getString(commanclass.NAME,""));
        phone.setText(sp.getString(commanclass.CONTACT,""));

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(scountry.equals("")){
                    new commanmethod(order.this,"Select Country");
                } else if (statename.equals("")) {
                    new commanmethod(order.this,"Select State");
                } else if (scity.equals("")) {
                    new commanmethod(order.this,"Select City");
                } else if (name.getText().toString().trim().equals("")) {
                    name.setError("Enter Name");
                } else if (phone.getText().toString().trim().equals("")) {
                    phone.setError("Enter Contact Number");
                }else if (phone.getText().toString().trim().length() < 10){
                    phone.setError("Valid Contact No. Required");
                }else if (area.getText().toString().trim().equals("")) {
                    area.setError("Enter Area");
                }else if (landamrk.getText().toString().trim().equals("")) {
                    landamrk.setError("Enter Landmark");
                }else if (pincode.getText().toString().trim().equals("")) {
                    pincode.setError("Enter Pincode");
                }else if (phone.getText().toString().trim().length() < 6){
                    phone.setError("Valid Pincode No. Required");
                }  else if (pytmethod.getCheckedRadioButtonId() == -1) {
                    new commanmethod(order.this,"Please Select Gender");
                } else{
                    if(pyt.equalsIgnoreCase("Cash on Delivery")){
                        createOrder("");
                    }
                    else {
                        startPayment();
                    }
                    }
                }
        });
    }

    private void createOrder(String transactionid) {

        String inset = "INSERT INTO ORDERADDRESS VALUES(NULL,'" + sp.getString(commanclass.ID, "") + "' , '" + scountry + "' , '" + sstate + "' , '" + scity + "' , '" + name.getText().toString() + "' , '" + phone.getText().toString() + "' , '" + area.getText().toString() + "' , '" + landamrk.getText().toString() + "','" + pincode.getText().toString() + "' , '"+pyt+"' , '"+transactionid+"')";
        db.execSQL(inset);

        String select="SELECT MAX(ORDERA) FROM ORDERADDRESS LIMIT 1";
        Cursor cursor = db.rawQuery(select,null);
        if(cursor.getCount()>0){
            while (cursor.moveToNext()){
                String sOrderid = cursor.getString(0);
                String update="UPDATE CART  SET ORDERID='"+sOrderid+"' WHERE USERID='"+sp.getString(commanclass.ID,"")+"' AND ORDERID='0' ";
                db.execSQL(update);
//                            String sCount = cursor.getString(2);
//                            String sState = cursor.getString(3);
//                            String sCity = cursor.getString(4);
//                            String sName = cursor.getString(5);
//                            String sContact = cursor.getString(6);
//                            String sArea = cursor.getString(7);
//                            String sLandamrk = cursor.getString(8);
//                            String sPincode = cursor.getString(9);

                sp.edit().putString(commanclass.ORDER_ID,sOrderid).commit();
//                            sp.edit().putString(commanclass.ORDER_COUNRTY,sCount).commit();
//                            sp.edit().putString(commanclass.ORDER_STATE,sState).commit();
//                            sp.edit().putString(commanclass.ORDER_CITY,sCity).commit();
//                            sp.edit().putString(commanclass.ORDER_NAME,sName).commit();
//                            sp.edit().putString(commanclass.ORDER_CONTACT,sContact).commit();
//                            sp.edit().putString(commanclass.ORDER_AREA,sArea).commit();
//                            sp.edit().putString(commanclass.ORDER_LANDMARK,sLandamrk).commit();
//                            sp.edit().putString(commanclass.ORDER_PINCODE,sPincode).commit();
//                            Log.d("ORDER", sOrderid + "\n" + sCount + "\n" + sState + "\n" + sCity + "\n" + sName + "\n" + sContact + "\n" + sArea + "\n" + sLandamrk+"\n"+sPincode);


            }
        }
        new commanmethod(com.example.myapplication.order.this, "Order Placed" );
        new commanmethod(com.example.myapplication.order.this, paymentdone.class );

    }

    private void startPayment() {

        final Activity activity = this;

        try {
            JSONObject options = new JSONObject();

            options.put("name", "Yash Kacha");
            options.put("description", "Reference No. #123456");
            options.put("image", R.drawable.upi);
            options.put("send_sms_hash", true);
            options.put("allow_rotation", true);
            options.put("theme.color", "#3399cc");
            options.put("amount", CartFragment.Totalp*100);//pass amount in currency subunits


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
        createOrder(s);
        new commanmethod(order.this , paymentdone.class);
    }

    public void onPaymentError(int i, String s) {
        new commanmethod(order.this ,"Payment Faild");

    }
}