package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import de.hdodenhof.circleimageview.CircleImageView;

public class CategoryAdapter extends  RecyclerView.Adapter<CategoryAdapter.MyHolder>  {

    SharedPreferences sp;

    Context context;

    String[] catnameArray;
    int[] catImgArray;
    public CategoryAdapter(Context context, int[] catImgArray, String[] catnameArray) {
        this.context=context;
        this.catnameArray=catnameArray;
        this.catImgArray=catImgArray;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item,parent,false);
        return new MyHolder(view);
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        CircleImageView img;
        TextView txt;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.img_cat);
            txt=itemView.findViewById(R.id.txt_cat);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.img.setImageResource(catImgArray[position]);
        holder.txt.setText(catnameArray[position]);

    }

    @Override
    public int getItemCount() {
        return catnameArray.length;
    }


}
