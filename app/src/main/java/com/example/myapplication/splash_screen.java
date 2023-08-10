package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;

public class splash_screen extends AppCompatActivity {

    SharedPreferences sp;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        sp=getSharedPreferences(commanclass.PREF,MODE_PRIVATE);
        img=findViewById(R.id.img_spalsh);

        AlphaAnimation animation=new AlphaAnimation(0,1);
        animation.setDuration(1500);
        img.startAnimation(animation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (sp.getString(commanclass.REMEMBER,"").equalsIgnoreCase("")){
                    new commanmethod(splash_screen.this,MainActivity.class);
                 }else {
                    new commanmethod(splash_screen.this,Homepage.class);
                }
            }
        },1500);
    }
}