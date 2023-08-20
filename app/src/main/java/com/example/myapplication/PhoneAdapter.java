package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class PhoneAdapter extends RecyclerView.Adapter<PhoneAdapter.MyHolder> {
        SharedPreferences sp;

        Context context;
    String[] phoneArray;
    String[] priceArray;
    String[] phone_desc;
    String[] idArray;


    int[] phone_imageArray;
    public PhoneAdapter(Context context, String[] phoneArray, int[] phone_imageArray, String[] priceArray, String[] phone_desc, String[] idArray) {
            this.context = context;
            this.phoneArray=phoneArray;
            this.phone_imageArray=phone_imageArray;
            this.priceArray=priceArray;
            this.phone_desc=phone_desc;
            this.idArray=idArray;
            sp= context.getSharedPreferences(commanclass.PREF,Context.MODE_PRIVATE);

    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_phone,parent,false);

        return new MyHolder(view);
    }
    public class MyHolder extends RecyclerView.ViewHolder {

        ImageView img;
        TextView txt,price,desc;
        public MyHolder(@NonNull View itemView) {
            super(itemView);

            img=itemView.findViewById(R.id.card_image);
            txt=itemView.findViewById(R.id.card_text);
            price=itemView.findViewById(R.id.card_text_price);
            desc=itemView.findViewById(R.id.details_phone_details);
        }
    }
    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        holder.img.setImageResource(phone_imageArray[position]);
        holder.txt.setText(phoneArray[position]);
        holder.price.setText(commanclass.PRODUCT_PRICE_SYMBOL+priceArray[position]);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.edit().putString(commanclass.PRODUCT_NAME,phoneArray[position]).commit();
                sp.edit().putString(commanclass.PRODUCT_PRICE,priceArray[position]).commit();
                sp.edit().putString(commanclass.PRODUCT_DESC,phone_desc[position]).commit();
                sp.edit().putInt(commanclass.PRODUCT_IMAGE,phone_imageArray[position]).commit();
                sp.edit().putString(commanclass.PRODUCT_ID,idArray[position]).commit();
                new commanmethod(context,PhoneDetails.class);
            }
        });
    }

    @Override
    public int getItemCount() {
        return phoneArray.length;
    }


}
