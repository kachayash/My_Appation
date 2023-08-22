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

import java.util.ArrayList;


public class WishFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<WishList> arrayList;

    SQLiteDatabase db;
    SharedPreferences sp;

    public WishFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_wish, container, false);


        db=getActivity().openOrCreateDatabase("Shopping",Context.MODE_PRIVATE,null);
        String tableQuery = "CREATE TABLE IF NOT EXISTS USER( USERID INTEGER PRIMARY KEY AUTOINCREMENT,NAME VARCHAR(30), EMAIL VARCHAR(30) ,CONTACT INT(10) , PASSWORD VARCHAR(20), CONFPASS VARCHAR(20), DOB VARCHAR(10) ,CITY VARCHAR(20) , GENDER VARCHAR(6))";
        db.execSQL(tableQuery);

        String cartQuery = "CREATE TABLE IF NOT EXISTS CART (CARTID INTEGER PRIMARY KEY AUTOINCREMENT , ORDERID INTEGER(10) ,USERID INTEGER (10), PRODUCTID INTEGER(10) , PRODUCTNAME VARCHAR(30) , PRODUCTIMG VARCHAR(100) , PRODUCTPRICE VARCHAR (20) ,PRODUCTDESC VARCHAR(2000), PRODUCTQTY INTEGER(10) ,TOTALPRICE VARCHAR(20))";
        db.execSQL(cartQuery);

        String wishQuery = "CREATE TABLE IF NOT EXISTS WISH (wishID INTEGER PRIMARY KEY AUTOINCREMENT ,USERID INTEGER (10), PRODUCTID INTEGER(10) , PRODUCTNAME VARCHAR(30) , PRODUCTIMG VARCHAR(100) , PRODUCTPRICE VARCHAR (20) ,PRODUCTDESC VARCHAR(2000))";
        db.execSQL(wishQuery);

        sp = getActivity().getSharedPreferences(commanclass.PREF,Context.MODE_PRIVATE);

        recyclerView = view.findViewById(R.id.wish_recyclerview);

        //Display Data In List
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        String selectQuery = "SELECT * FROM WISH WHERE USERID='" + sp.getString(commanclass.ID, "") + "'";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.getCount() > 0) {
            arrayList = new ArrayList<>();
            while (cursor.moveToNext()) {
                WishList list = new WishList();
                list.setWishlistid(cursor.getString(0));
                list.setProductid(cursor.getString(2));
                list.setProductname(cursor.getString(3));
                list.setProductimg(cursor.getString(4));
                list.setProductdesc(cursor.getString(6));
                list.setProductprice(cursor.getString(5));
                arrayList.add(list);
            }
            WishAdapter adapter = new WishAdapter(getActivity(), arrayList);
            recyclerView.setAdapter(adapter);
        }

        return view;
    }
}