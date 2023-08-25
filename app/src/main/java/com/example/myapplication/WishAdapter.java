package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class WishAdapter extends RecyclerView.Adapter<WishAdapter.MyHolder> {

    Context context;
    ArrayList<WishList> arrayList;
    SharedPreferences sp;
    SQLiteDatabase db;

    public WishAdapter(Context context, ArrayList<WishList> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        sp = context.getSharedPreferences(commanclass.PREF,Context.MODE_PRIVATE);


        db=context.openOrCreateDatabase("Shopping",Context.MODE_PRIVATE,null);
        String tableQuery = "CREATE TABLE IF NOT EXISTS USER( USERID INTEGER PRIMARY KEY AUTOINCREMENT,NAME VARCHAR(30), EMAIL VARCHAR(30) ,CONTACT INT(10) , PASSWORD VARCHAR(20), CONFPASS VARCHAR(20), DOB VARCHAR(10) ,CITY VARCHAR(20) , GENDER VARCHAR(6))";
        db.execSQL(tableQuery);

        String cartQuery = "CREATE TABLE IF NOT EXISTS CART (CARTID INTEGER PRIMARY KEY AUTOINCREMENT , ORDERID INTEGER(10) ,USERID INTEGER (10), PRODUCTID INTEGER(10) , PRODUCTNAME VARCHAR(30) , PRODUCTIMG VARCHAR(100) , PRODUCTPRICE VARCHAR (20) ,PRODUCTDESC VARCHAR(2000), PRODUCTQTY INTEGER(10) ,TOTALPRICE VARCHAR(20))";
        db.execSQL(cartQuery);

        String wishQuery = "CREATE TABLE IF NOT EXISTS WISH (wishID INTEGER PRIMARY KEY AUTOINCREMENT ,USERID INTEGER (10), PRODUCTID INTEGER(10) , PRODUCTNAME VARCHAR(30) , PRODUCTIMG VARCHAR(100) , PRODUCTPRICE VARCHAR (20) ,PRODUCTDESC VARCHAR(2000))";
        db.execSQL(wishQuery);

    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_wish, parent, false);
        return new MyHolder(view);
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        ImageView imageView,deleteIv;
        TextView name,price;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            deleteIv = itemView.findViewById(R.id.delete_btn_wish);
            imageView = itemView.findViewById(R.id.card_image_wish);
            name = itemView.findViewById(R.id.card_text_wish);
            price = itemView.findViewById(R.id.card_text_price_wish);

        }
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.imageView.setImageResource(Integer.parseInt(arrayList.get(position).getProductimg()));
        holder.name.setText(arrayList.get(position).getProductname());
        holder.price.setText(commanclass.PRODUCT_PRICE_SYMBOL+arrayList.get(position).getProductprice());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.edit().putString(commanclass.PRODUCT_ID,arrayList.get(position).getProductid()).commit();
                sp.edit().putString(commanclass.PRODUCT_NAME,arrayList.get(position).getProductname()).commit();
                sp.edit().putInt(commanclass.PRODUCT_IMAGE, Integer.parseInt(arrayList.get(position).getProductimg())).commit();
                sp.edit().putString(commanclass.PRODUCT_PRICE,arrayList.get(position).getProductprice()).commit();
                sp.edit().putString(commanclass.PRODUCT_DESC,arrayList.get(position).getProductdesc()).commit();
                new commanmethod(context, PhoneDetails.class);
            }
        });

        holder.deleteIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String deleteQ="DELETE FROM WISH WHERE wishID='"+arrayList.get(position).getWishlistid()+"'";
                db.execSQL(deleteQ);
                new commanmethod(context,"Remove from cart");
                arrayList.remove(position);
                notifyDataSetChanged();
            }
        });
//

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
