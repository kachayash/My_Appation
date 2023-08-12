package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class CreateAccount extends AppCompatActivity {

    TextView login1,login2;

    Button singup;

    ImageView backbutton;
    EditText name,email,contact,password,confirmpassword , dob;
    RadioGroup gender;
    Spinner city;

    String scity,sgender;
    String emailp="[a-zA-Z0-9.-]+@[a-z]+\\.+[a-z]+";

    ArrayList<String> arrayList;

    Calendar calendar;

    SQLiteDatabase db;





//    String[] cityArray = {"Ahmedabad",
//            "Surat",
//           "Vadodara (Baroda)",
//          "Rajkot",
//           "Bhavnagar",
//           "Jamnagar",
//             "Junagadh",
//            "Gandhinagar (Capital city of Gujarat)",
//            "Anand",
//            "Nadiad",
//            "Bharuch",
//            "Gandhidham",
//            "Vapi",
//            "Morbi",
//            "Surendranagar",
//            "Patan",
//            "Navsari",
//            "Mehsana",
//            "Veraval",
//            "Porbandar"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);

        name=findViewById(R.id.createacc_enterfullname);
        email=findViewById(R.id.createacc_enteremailid);
        contact=findViewById(R.id.createacc_enterphonenumber);
        password=findViewById(R.id.createacc_enterpass);
        confirmpassword=findViewById(R.id.createacc_enterconfpass);
        singup=findViewById(R.id.create_button);





        //Radio Button
        gender=findViewById(R.id.gender_singup);
        gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int i) {
                RadioButton radioButton = findViewById(i);//i=R.id.singup_male , R.id.singup_female;
                sgender=radioButton.getText().toString();
                new commanmethod(CreateAccount.this ,sgender);
            }
        });


        //spinner
        city=findViewById(R.id.spinner_singup);

        arrayList= new ArrayList<>();
        arrayList.add("Select City");
        arrayList.add("Ahmedabad");
        arrayList.add("Surat");
        arrayList.add("Vadodara (Baroda)");
        arrayList.add("Rajkot");
        arrayList.add("Bhavnagar");
        arrayList.add("Jamnagar");
        arrayList.add("Junagadh");
        arrayList.add("Gandhinagar (Capital city of Gujarat");
        arrayList.add("Anand");
        arrayList.add("Nadiad");
        arrayList.add("Bharuch");
        arrayList.add("Gandhidham");
        arrayList.add("Vapi");
        arrayList.add("Morbi");
        arrayList.add("Surendranagar");
        arrayList.add("Patan");
        arrayList.add("Navsari");
        arrayList.add("Mehsana");
        arrayList.add("Veraval");
        arrayList.add("Porbandar");





        ArrayAdapter adapter = new ArrayAdapter(CreateAccount.this, android.R.layout.simple_list_item_1 , arrayList);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_multiple_choice);
        city.setAdapter(adapter);

        city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0){
                    scity = "";
                }else{
                    scity=arrayList.get(i);
                    new commanmethod(CreateAccount.this , arrayList.get(i));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        //dob

        dob=findViewById(R.id.singup_dob);
        calendar = Calendar.getInstance();

       DatePickerDialog.OnDateSetListener dateclick= new DatePickerDialog.OnDateSetListener() {
           @Override
           public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
               calendar.set(Calendar.YEAR,i);
               calendar.set(Calendar.MONTH,i1);
               calendar.set(Calendar.DAY_OF_MONTH,i2);

               SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
               dob.setText(sdf.format(calendar.getTime()));

           }
       };


        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(CreateAccount.this,dateclick,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
                //datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });
        //DATABASE
        db=openOrCreateDatabase("Shopping",MODE_PRIVATE,null);
        String tableQuery = "CREATE TABLE IF NOT EXISTS USER( USERID INTEGER PRIMARY KEY AUTOINCREMENT,NAME VARCHAR(30), EMAIL VARCHAR(30) ,CONTACT INT(10) , PASSWORD VARCHAR(20), CONFPASS VARCHAR(20), DOB VARCHAR(10) ,CITY VARCHAR(20) , GENDER VARCHAR(6))";
        db.execSQL(tableQuery);

        //check validation

        singup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name.getText().toString().trim().equals("")) {
                    name.setError("Name Required");

                    //email
                } else if (email.getText().toString().trim().equals("")) {
                    email.setError("Email Id Required");
                } else if (!email.getText().toString().trim().matches(emailp)) {
                    email.setError("Valid Email Id Required");
                }
                //contact number

                else if (contact.getText().toString().trim().equals("")) {
                    contact.setError("Contact No. Required");
                } else if (contact.getText().toString().trim().length() < 10) {
                    contact.setError("Valid Contact No. Required");
                }
                //password
                else if (password.getText().toString().trim().equals("")) {
                    password.setError("Password Required");
                } else if (password.getText().toString().trim().length() < 6) {
                    password.setError("Min. 6 Char Password Required");
                }
                //conf password
                else if (confirmpassword.getText().toString().trim().equals("")) {
                    confirmpassword.setError("Confirm Password Required");
                } else if (confirmpassword.getText().toString().trim().length() < 6) {
                    confirmpassword.setError("Min. 6 Char Confirm Password Required");
                } else if (!confirmpassword.getText().toString().trim().matches(password.getText().toString().trim())) {
                    confirmpassword.setError("Password Does Not Match");
                }
                //gender
                else if (gender.getCheckedRadioButtonId() == -1) {
                    new commanmethod(CreateAccount.this,"Please Select Gender");
                }
                //city
                else if(scity.equals("")){
                    new commanmethod(CreateAccount.this,"Please Select City");
                }

                //dob
                else if(dob.getText().toString().trim().equals("")){
                    dob.setError("Please Select Date of Birth");
                }
                else {

                    String select ="SELECT * FROM USER WHERE EMAIL='"+email.getText().toString()+"' AND CONTACT='"+contact.getText().toString()+"'";
                    Cursor cursor=db.rawQuery(select,null);
                    if (cursor.getCount()>0){
                        new commanmethod(CreateAccount.this ,"Email ID, Contact Number Alredy Registered" );

                    }
                    else{
                    String insertQuery ="INSERT INTO USER VALUES(NULL ,'"+name.getText().toString()+"' ,'"+email.getText().toString()+"' ,'"+contact.getText().toString()+"' ,'"+password.getText().toString()+"' ,'"+confirmpassword.getText().toString()+"' ,'"+dob.getText().toString()+"' ,'"+scity+"' ,'"+sgender+"' )";
                    db.execSQL(insertQuery);
                    System.out.println("Signup Successfully");
                    //Toast.makeText(MainActivity.this, "Login Successfully", Toast.LENGTH_LONG).show();
                    new commanmethod(CreateAccount.this, "Signup Successfully");
                    /*Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);*/
                    onBackPressed();
                    }
                }
            }
        });


        // backbutton
        backbutton=findViewById(R.id.createacc_backbtn);

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        login1=findViewById(R.id.login_text1);
        login2=findViewById(R.id.login_text2);

        login1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new commanmethod(CreateAccount.this , LoginActivity.class);
            }
        });
        login2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new commanmethod(CreateAccount.this , LoginActivity.class);
            }
        });

//
    }
}