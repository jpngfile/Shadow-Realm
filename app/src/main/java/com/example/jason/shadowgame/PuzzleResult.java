package com.example.jason.shadowgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class PuzzleResult extends AppCompatActivity {

    TextView resultText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle_result);
        resultText = (TextView) findViewById(R.id.result_text);

        Bundle extras = getIntent().getExtras();
        String pic = extras.getString("pic");
        resultText.setText(pic);
    }
}