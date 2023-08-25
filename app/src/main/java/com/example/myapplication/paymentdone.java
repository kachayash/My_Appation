package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;

public class paymentdone extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paymentdone);

        LottieAnimationView anim = findViewById(R.id.paymentsuccess);
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                anim.setVisibility(View.VISIBLE);
                anim.playAnimation();
            }
        }, 2000);

        anim.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                // Code to run when animation starts
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                new commanmethod(paymentdone.this , deshbord.class);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                // Code to run when animation is cancelled
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                // Code to run when animation repeats
            }
        });

    }
}