package com.example.jason.shadowgame;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Daniel on 2017-09-16.
 */

public class SplashScreen extends AppCompatActivity {

    Button splashStart;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        splashStart = (Button) findViewById(R.id.splashStart);

        splashStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SplashScreen.this, PuzzleLevels.class);
                startActivity (i);
            }
        });

    }
}
