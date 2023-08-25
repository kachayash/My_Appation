package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;


public class CartFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<Cartlist> arrayList;

    public  static  Button check;
    public  static  int Totalp=0;

    SQLiteDatabase db;
    SharedPreferences sp;

    public CartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);


        db=getActivity().openOrCreateDatabase("Shopping",Context.MODE_PRIVATE,null);
        String tableQuery = "CREATE TABLE IF NOT EXISTS USER( USERID INTEGER PRIMARY KEY AUTOINCREMENT,NAME VARCHAR(30), EMAIL VARCHAR(30) ,CONTACT INT(10) , PASSWORD VARCHAR(20), CONFPASS VARCHAR(20), DOB VARCHAR(10) ,CITY VARCHAR(20) , GENDER VARCHAR(6))";
        db.execSQL(tableQuery);

        String cartQuery = "CREATE TABLE IF NOT EXISTS CART (CARTID INTEGER PRIMARY KEY AUTOINCREMENT , ORDERID INTEGER(10) ,USERID INTEGER (10), PRODUCTID INTEGER(10) , PRODUCTNAME VARCHAR(30) , PRODUCTIMG VARCHAR(100) , PRODUCTPRICE VARCHAR (20) ,PRODUCTDESC VARCHAR(2000), PRODUCTQTY INTEGER(10) ,TOTALPRICE VARCHAR(20))";
        db.execSQL(cartQuery);

        String wishQuery = "CREATE TABLE IF NOT EXISTS WISH (wishID INTEGER PRIMARY KEY AUTOINCREMENT ,USERID INTEGER (10), PRODUCTID INTEGER(10) , PRODUCTNAME VARCHAR(30) , PRODUCTIMG VARCHAR(100) , PRODUCTPRICE VARCHAR (20),PRODUCTDESC VARCHAR(2000) )";
        db.execSQL(wishQuery);

        sp = getActivity().getSharedPreferences(commanclass.PREF,Context.MODE_PRIVATE);

        recyclerView = view.findViewById(R.id.cart_recyclerview);
        check=view.findViewById(R.id.checkt_cart);

        //Display Data In List
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        String selectQuery = "SELECT * FROM CART WHERE USERID='" + sp.getString(commanclass.ID, "") + "'";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.getCount() > 0) {
            check.setVisibility(View.VISIBLE);
            arrayList = new ArrayList<>();
            while (cursor.moveToNext()) {
                Cartlist list = new Cartlist();
                list.setCartlistid(cursor.getString(0));
                list.setProductid(cursor.getString(3));
                list.setProductname(cursor.getString(4));
                list.setProductimg(cursor.getString(5));
                list.setProductdesc(cursor.getString(7));
                list.setProductprice(cursor.getString(6));
                list.setTotalprice(cursor.getString(9));
                list.setProductqyt(cursor.getString(8));

                arrayList.add(list);

                Totalp += Integer.parseInt(cursor.getString(9));
                sp.edit().putString(commanclass.TotalPrice, String.valueOf(Totalp)).commit();
            }
            CartAdapter adapter = new CartAdapter(getActivity(), arrayList);
            recyclerView.setAdapter(adapter);

            check.setText("Checkout"+commanclass.PRODUCT_PRICE_SYMBOL+Totalp);
        }else{
            check.setVisibility(View.GONE);
        }

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new commanmethod(getActivity() , order.class);
            }
        });

        return view;
    }
}