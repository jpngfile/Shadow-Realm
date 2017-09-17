package com.example.jason.shadowgame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.Random;

/**
 * Created by Daniel on 2017-09-16.
 */

public class MainMenu extends AppCompatActivity {


    Button resultButton;
    Button levelsButton;
    Button stageButton;
    Button cameraButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        resultButton = (Button) findViewById(R.id.resultButton);
        resultButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent(MainMenu.this, PuzzleResult.class);
                resultIntent.putExtra("pic", R.drawable.man_shadow);
                startActivity(resultIntent);
            }
        });

        levelsButton = (Button) findViewById(R.id.levelsButton);
        levelsButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent levelsIntent = new Intent(MainMenu.this, PuzzleLevels.class);
                startActivity(levelsIntent);
            }
        });

        stageButton = (Button) findViewById(R.id.stageButton);
        stageButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainMenu.this, PuzzleStage.class);
                //Random r = new Random ();
                i.putExtra("level", 1);
                startActivity (i);
            }
        });
    }
}
