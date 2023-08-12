package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class update_activity extends Fragment {





    Button update,logout,edit;

    ImageView backbutton;
    EditText name,email,contact, dob;
    RadioGroup gender;
    RadioButton male,female;
    Spinner city;

    String scity,sgender;
    String emailp="[a-zA-Z0-9.-]+@[a-z]+\\.+[a-z]+";

    ArrayList<String> arrayList;

    Calendar calendar;

    SQLiteDatabase db;
    SharedPreferences sp;

    public update_activity() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.update_actitvity, container, false);



        name=view.findViewById(R.id.homepage_enterfullname);
        email=view.findViewById(R.id.homepage_enteremailid);
        contact=view.findViewById(R.id.homepage_enterphonenumber);
        gender=view.findViewById(R.id.gender_homepage);
        city=view.findViewById(R.id.spinner_singup_homepage);
        dob=view.findViewById(R.id.singup_dob_homepage);
        update=view.findViewById(R.id.update_button_homepage);
        logout=view.findViewById(R.id.logout_button_homepage);
        edit=view.findViewById(R.id.edit_button_homepage);
        sp=getActivity().getSharedPreferences(commanclass.PREF, Context.MODE_PRIVATE);
        male=view.findViewById(R.id.singup_male_homepage);
        female=view.findViewById(R.id.singup_female_homepage);


        //Radio Button
        gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int i) {
                RadioButton radioButton = view.findViewById(i);//i=R.id.singup_male , R.id.singup_female;
                sgender=radioButton.getText().toString();
                new commanmethod(getActivity() ,sgender);
            }
        });


        //spinner


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





        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1 , arrayList);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_multiple_choice);
        city.setAdapter(adapter);

        city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0){
                    scity = "";
                }else{
                    scity=arrayList.get(i);
                    new commanmethod(getActivity() , arrayList.get(i));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        //dob


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
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),dateclick,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
                //datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });
        //DATABASE
        db=getActivity().openOrCreateDatabase("Shopping",Context.MODE_PRIVATE,null);
        String tableQuery = "CREATE TABLE IF NOT EXISTS USER( USERID INTEGER PRIMARY KEY AUTOINCREMENT,NAME VARCHAR(30), EMAIL VARCHAR(30) ,CONTACT INT(10) , PASSWORD VARCHAR(20), CONFPASS VARCHAR(20), DOB VARCHAR(10) ,CITY VARCHAR(20) , GENDER VARCHAR(6))";
        db.execSQL(tableQuery);

        //button

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setData(true);
            }
        });



        //logout
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.edit().clear().commit();
                new commanmethod(getActivity(), LoginActivity.class);
            }
        });

        //check validation

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //name
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

                //gender
                else if (gender.getCheckedRadioButtonId() == -1) {
                    new commanmethod(getActivity(),"Please Select Gender");
                }
                //city
                else if(scity.equals("")){
                    new commanmethod(getActivity(),"Please Select City");
                }

                //dob
                else if(dob.getText().toString().trim().equals("")){
                    dob.setError("Please Select Date of Birth");
                }
                else {

                    String select ="SELECT * FROM USER WHERE USERID='"+sp.getString(commanclass.ID,"")+"' ";
                    Cursor cursor=db.rawQuery(select,null);
                    if (cursor.getCount()>0){
                        String update="UPDATE USER SET NAME='"+name.getText().toString()+"',EMAIL='"+email.getText().toString()+"',CONTACT='"+contact.getText().toString()+"',GENDER='"+sgender+"',CITY='"+scity+"',DOB='"+dob.getText().toString()+"' WHERE USERID='"+sp.getString(commanclass.ID,"")+"'";
                        db.execSQL(update);
                        new commanmethod(getActivity(),"Update Succesfully");
                        //update sp
                        sp.edit().putString(commanclass.NAME,name.getText().toString()).commit();
                        sp.edit().putString(commanclass.EMAIL,email.getText().toString()).commit();
                        sp.edit().putString(commanclass.CONTACT,contact.getText().toString()).commit();
                        sp.edit().putString(commanclass.DOB,dob.getText().toString()).commit();
                        sp.edit().putString(commanclass.GENDER,sgender).commit();
                        sp.edit().putString(commanclass.CITY,scity).commit();

                        setData(false);
                    }
                    else{
                        new commanmethod(getActivity(),"Invalid UserId");
//                        String insertQuery ="INSERT INTO USER VALUES(NULL ,'"+name.getText().toString()+"' ,'"+email.getText().toString()+"' ,'"+contact.getText().toString()+"' ,'"+dob.getText().toString()+"' ,'"+scity+"' ,'"+sgender+"' )";
//                        db.execSQL(insertQuery);
//                        System.out.println("Signup Successfully");
//                        new commanmethod(Homepage.this, "Signup Successfully");
//                        onBackPressed();
                    }
                }
            }
        });


        // backbutton


            setData(false);
            return  view;
    }

    private void setData(boolean isEnable) {
        name.setEnabled(isEnable);
        contact.setEnabled(isEnable);
        email.setEnabled(isEnable);
        dob.setEnabled(isEnable);

        male.setEnabled(isEnable);
        female.setEnabled(isEnable);

        city.setEnabled(isEnable);

        if(isEnable){
            edit.setVisibility(View.GONE);
            update.setVisibility(View.VISIBLE);
        }
        else{
            edit.setVisibility(View.VISIBLE);
            update.setVisibility(View.GONE);
        }

        name.setText(sp.getString(commanclass.NAME,""));
        contact.setText(sp.getString(commanclass.CONTACT,""));
        email.setText(sp.getString(commanclass.EMAIL,""));
        dob.setText(sp.getString(commanclass.DOB,""));

        sgender=sp.getString(commanclass.GENDER,"");

        if(sgender.equalsIgnoreCase("Male")){
            male.setChecked(true);
        }else if(sgender.equalsIgnoreCase("Female")){
            female.setChecked(true);
        }else{

        }
        scity=sp.getString(commanclass.CITY,"");
        int icitypo=0;
        for(int i=0 ; i<arrayList.size();i++){
            if(scity.equalsIgnoreCase(arrayList.get(i))){
                icitypo=i;
            }
        }
        city.setSelection(icitypo);

    }


}