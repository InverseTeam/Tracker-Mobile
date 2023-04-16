package com.example.inversetracker.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.inversetracker.R;

public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_SCREEN = 3500;
    private ImageView textSplashScreen;
    private Animation topAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen_activity);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        init();
    }

    private void init(){
        SharedPreferences sPref1 = getSharedPreferences("saveToken", MODE_PRIVATE);
        String entryFirst = sPref1.getString("tokenSave", "");
        topAnim = AnimationUtils.loadAnimation(this, R.anim.splash_screen_animation_top);
        textSplashScreen = findViewById(R.id.textSplashScreen);
        textSplashScreen.setAnimation(topAnim);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (entryFirst.isEmpty()){
                    Intent intent = new Intent(SplashScreen.this, WelcomeActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Intent intent = new Intent(SplashScreen.this, MainMenuActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        }, SPLASH_SCREEN);
    }

}