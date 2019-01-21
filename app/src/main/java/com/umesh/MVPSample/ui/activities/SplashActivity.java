package com.umesh.MVPSample.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.umesh.MVPSample.R;


public class SplashActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        Thread t = new Thread() {
            public void run() {
                try {
                    sleep(2000);
                    gotoLogin();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();
    }

    public void gotoLogin() {

        Intent intent;
        intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
