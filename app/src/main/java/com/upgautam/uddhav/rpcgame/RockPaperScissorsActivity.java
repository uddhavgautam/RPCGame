package com.upgautam.uddhav.rpcgame;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import static android.os.StrictMode.ThreadPolicy;
import static android.os.StrictMode.setThreadPolicy;

public class RockPaperScissorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setThreadPolicy(new ThreadPolicy.Builder().permitAll().build());
        setContentView(R.layout.rockpaperscissors_activity);
    }
}
