package com.example.cs_300_project_vaggelis_chasiotis_20200101;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

        private static int SPLASH_SCREEN = 4000;

        //Variables
        Animation animat;
        ImageView image;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
            setContentView(R.layout.activity_main);

            //Animation
            animat = AnimationUtils.loadAnimation(this, R.anim.animation);

            //Hooks
            image = findViewById(R.id.WelcomingImage);

            image.setAnimation(animat);

            new Handler().postDelayed (new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(MainActivity.this, LoginLayout.class);
                    startActivity(intent);
                    finish();
                }

            }, SPLASH_SCREEN);
        }

}