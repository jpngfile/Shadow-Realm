package com.example.jason.shadowgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button resultButton;
    Button levelsButton;
    Button stageButton;
    Button cameraButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        resultButton = (Button) findViewById(R.id.resultButton);
        resultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               viewResults(v);
            }
        });
        levelsButton = (Button) findViewById(R.id.levelsButton);
        levelsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewResults(v);
            }
        });
        stageButton = (Button) findViewById(R.id.stageButton);
        stageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewResults(v);
            }
        });
        cameraButton = (Button) findViewById(R.id.cameraButton);
        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewResults(v);
            }
        });

    }

    public void viewResults(View view) {
        Intent resultIntent = new Intent(this, PuzzleResult.class);
        resultIntent.putExtra("pic", R.drawable.clay_block);
        resultIntent.putExtra("isSuccessful", false);
        startActivity(resultIntent);
    }

    public void viewLevels(View view) {
        Intent levelsIntent = new Intent(this, PuzzleLevels.class);
        startActivity(levelsIntent);
    }

    public void viewStage(View view) {
        Intent stageIntent = new Intent(this, PuzzleStage.class);
        startActivity(stageIntent);
    }

    public void viewCamera(View view) {
        Intent cameraIntent = new Intent(this, PuzzleCamera.class);
        startActivity(cameraIntent);
    }
}
