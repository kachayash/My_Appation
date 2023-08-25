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

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyHolder> {

    Context context;
    ArrayList<Cartlist> arrayList;
    SharedPreferences sp;
    SQLiteDatabase db;

    public CartAdapter(Context context, ArrayList<Cartlist> arrayList) {
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_cart, parent, false);
        return new MyHolder(view);
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        ImageView imageView,deleteIv,plus,minus;
        TextView name,price,total,qty;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            deleteIv = itemView.findViewById(R.id.delete_btn);
            imageView = itemView.findViewById(R.id.card_image);
            name = itemView.findViewById(R.id.card_text);
            price = itemView.findViewById(R.id.card_text_price);
            total=itemView.findViewById(R.id.card_text_price_total);
            plus=itemView.findViewById(R.id.plus_btn);
            minus=itemView.findViewById(R.id.minus_btn);
            qty=itemView.findViewById(R.id.text_cart);

        }
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.MyHolder holder, int position) {
        holder.imageView.setImageResource(Integer.parseInt(arrayList.get(position).getProductimg()));
        holder.name.setText(arrayList.get(position).getProductname());
        holder.price.setText(commanclass.PRODUCT_PRICE_SYMBOL+arrayList.get(position).getProductprice()+"*"+arrayList.get(position).getProductqyt());
        holder.total.setText(commanclass.PRODUCT_PRICE_SYMBOL+arrayList.get(position).getTotalprice());



        holder.qty.setText(arrayList.get(position).getProductqyt());
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
                String deleteQ="DELETE FROM CART WHERE CARTID='"+arrayList.get(position).getCartlistid()+"'";
                db.execSQL(deleteQ);
                new commanmethod(context,"Remove from cart");
                CartFragment.Totalp -= Integer.parseInt(arrayList.get(position).getTotalprice());
                CartFragment.check.setText("Checkout"+commanclass.PRODUCT_PRICE_SYMBOL+CartFragment.Totalp);

                if(CartFragment.Totalp==0){
                    CartFragment.check.setVisibility(View.GONE);
                }else{
                    CartFragment.check.setVisibility(View.VISIBLE);
                }
                arrayList.remove(position);
                notifyDataSetChanged();


            }
        });

        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int q= Integer.parseInt(arrayList.get(position).getProductqyt())+1;
                int ttl=Integer.parseInt(arrayList.get(position).getProductprice())*q;

                holder.qty.setText(String.valueOf(q));
                holder.price.setText(commanclass.PRODUCT_PRICE_SYMBOL+arrayList.get(position).getProductprice()+"*"+q);
                holder.total.setText(commanclass.PRODUCT_PRICE_SYMBOL+ttl);

                Cartlist list = new Cartlist();
                list.setCartlistid(arrayList.get(position).getCartlistid());
                list.setProductid(arrayList.get(position).getProductid());
                list.setProductname(arrayList.get(position).getProductname());
                list.setProductimg(arrayList.get(position).getProductimg());
                list.setProductdesc(arrayList.get(position).getProductdesc());
                list.setProductprice(arrayList.get(position).getProductprice());
                list.setTotalprice(String.valueOf(ttl));
                list.setProductqyt(String.valueOf(q));
                arrayList.set(position,list);


                CartFragment.Totalp += Integer.parseInt(arrayList.get(position).getProductprice());
                CartFragment.check.setText("Checkout"+commanclass.PRODUCT_PRICE_SYMBOL+CartFragment.Totalp);



                String  update="UPDATE CART SET PRODUCTQTY='"+q+"' ,TOTALPRICE='"+ttl+"' WHERE CARTID='"+arrayList.get(position).getCartlistid()+"'";
                db.execSQL(update);
                notifyDataSetChanged();

            }
        });

        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int q= Integer.parseInt(arrayList.get(position).getProductqyt())-1;
                int ttl=Integer.parseInt(arrayList.get(position).getProductprice())*q;

                CartFragment.Totalp -= Integer.parseInt(arrayList.get(position).getProductprice());
                CartFragment.check.setText("Checkout"+commanclass.PRODUCT_PRICE_SYMBOL+CartFragment.Totalp);
                if(CartFragment.Totalp==0){
                    CartFragment.check.setVisibility(View.GONE);
                }else{
                    CartFragment.check.setVisibility(View.VISIBLE);
                }


                holder.qty.setText(String.valueOf(q));
                holder.price.setText(commanclass.PRODUCT_PRICE_SYMBOL+arrayList.get(position).getProductprice()+"*"+q);
                holder.total.setText(commanclass.PRODUCT_PRICE_SYMBOL+ttl);

                if (q>0){
                    Cartlist list = new Cartlist();
                    list.setCartlistid(arrayList.get(position).getCartlistid());
                    list.setProductid(arrayList.get(position).getProductid());
                    list.setProductname(arrayList.get(position).getProductname());
                    list.setProductimg(arrayList.get(position).getProductimg());
                    list.setProductdesc(arrayList.get(position).getProductdesc());
                    list.setProductprice(arrayList.get(position).getProductprice());
                    list.setTotalprice(String.valueOf(ttl));
                    list.setProductqyt(String.valueOf(q));
                    arrayList.set(position,list);
                    String  update="UPDATE CART SET PRODUCTQTY='"+q+"' ,TOTALPRICE='"+ttl+"' WHERE CARTID='"+arrayList.get(position).getCartlistid()+"'";
                    db.execSQL(update);

                }else {
                    String  delete="DELETE FROM CART WHERE CARTID='"+arrayList.get(position).getCartlistid()+"'";
                    db.execSQL(delete);
                    arrayList.remove(position);

                }



                notifyDataSetChanged();

            }
        });


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
