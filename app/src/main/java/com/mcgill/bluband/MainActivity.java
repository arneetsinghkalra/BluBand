package com.mcgill.bluband;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN = 1250;
    private TextView slogan;
   private ImageView bluband, mcgill, biodesign;
   private Animation topAnim, bottomAnim;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        slogan = (TextView)findViewById(R.id.sloganID);
        bluband = (ImageView)findViewById(R.id.bluBandLogo);
        mcgill = (ImageView)findViewById(R.id.mcgillID);
        biodesign = (ImageView)findViewById(R.id.biodesignID);
        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        bluband.setAnimation(topAnim);
        slogan.setAnimation(bottomAnim);
        mcgill.setAnimation(bottomAnim);
        biodesign.setAnimation(bottomAnim);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                openLoginActivity();
                finish();
            }
        },SPLASH_SCREEN);
    }

    public void openLoginActivity(){
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}
