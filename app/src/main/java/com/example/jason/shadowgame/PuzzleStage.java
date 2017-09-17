package com.example.jason.shadowgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class PuzzleStage extends AppCompatActivity {

    int level = -1;
    Button stageStart, stageBack;
    TextView stageLabel;
    ImageView stageImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();

        if (extras == null || (level = extras.getInt("level")) < 0) {
            Log.w ("PuzzleStage.onCreate", "Invalid level: " + level);
            return;
        }
        setContentView(R.layout.activity_puzzle_stage);

        stageLabel = (TextView) findViewById(R.id.stageLevelLabel);
        stageLabel.setText("Level " + level);

        stageImage = (ImageView) findViewById(R.id.stageImage);
        stageImage.setImageResource(getDrawableId (level));

        stageStart = (Button) findViewById(R.id.stageStart);
        stageStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PuzzleStage.this, PuzzleCamera.class);
                startActivity (i);
            }
        });

        stageBack = (Button) findViewById(R.id.stageBack);
        stageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish ();
            }
        });
    }

    private int getDrawableId (int level) {
        return getResources().getIdentifier("level_" + level, "drawable", "com.example.jason.shadowgame");
    }
}
