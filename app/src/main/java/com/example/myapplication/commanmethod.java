package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class commanmethod {

    commanmethod(Context context , String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    commanmethod(Context context , Class<?> nextClass){
        Intent intent = new Intent(context , nextClass);
        context.startActivity(intent);
    }

}
