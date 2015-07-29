package com.example.wen.foodmenuprinter;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class SplashActivity extends ActionBarActivity {

    //Duration of wait
    private final static int SPLASH_SCREEN_DURATION = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent loginActivity = new Intent(SplashActivity.this, DatabaseTestingActivity.class);
                SplashActivity.this.startActivity(loginActivity);
                SplashActivity.this.finish();
            }
        }, SPLASH_SCREEN_DURATION);
    }
}
