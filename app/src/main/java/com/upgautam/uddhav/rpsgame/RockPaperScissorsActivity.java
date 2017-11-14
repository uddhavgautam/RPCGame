package com.upgautam.uddhav.rpsgame;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import static android.os.StrictMode.ThreadPolicy;
import static android.os.StrictMode.setThreadPolicy;

public class RockPaperScissorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setThreadPolicy(new ThreadPolicy.Builder().permitAll().build()); //you are allowed to do anything from this main thread
        setContentView(R.layout.rockpaperscissors_activity);


    }
}
