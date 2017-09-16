package com.example.jason.shadowgame;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

/**
 * Created by Daniel on 2017-09-16.
 */

public class StartingActivity extends AppCompatActivity {

    Button playButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.starting_activity);

        playButton = (Button) findViewById(R.id.playButton);
    }
}
