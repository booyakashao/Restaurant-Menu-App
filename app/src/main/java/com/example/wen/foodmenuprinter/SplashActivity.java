package com.example.wen.foodmenuprinter;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;


public class SplashActivity extends Activity {

    //Duration of wait
    private final static int SPLASH_SCREEN_DURATION = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent mainMenuActivity = new Intent(SplashActivity.this, MainMenuActivity.class);
                SplashActivity.this.startActivity(mainMenuActivity);
                SplashActivity.this.finish();
            }
        }, SPLASH_SCREEN_DURATION);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Menu is disabled
        return false;
    }
}
