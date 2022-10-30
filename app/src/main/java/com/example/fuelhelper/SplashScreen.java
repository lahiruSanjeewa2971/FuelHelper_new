package com.example.fuelhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {
    private static int DELAY_TIME = 2000;

    ImageView imageView;
    TextView app_name;
    Animation topAnim, bottomAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

         imageView = findViewById(R.id.imageView);
         app_name = findViewById(R.id.app_name);

         imageView.setAnimation(topAnim);
         app_name.setAnimation(bottomAnim);

         new Handler().postDelayed(new Runnable() {
             @Override
             public void run() {
                 Intent i = new Intent(SplashScreen.this, MainActivity.class);
                 Pair[] pairs = new Pair[2];
                 pairs[0] = new Pair(imageView, "splash_image");
                 pairs[1] = new Pair(app_name, "splash_text");

                 ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SplashScreen.this,pairs);
                 startActivity(i, options.toBundle());
             }
         }, DELAY_TIME);
    }
}