package com.example.jason.shadowgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class PuzzleResult extends AppCompatActivity {

    ImageView resultImageView;
    TextView resultTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle_result);
        resultImageView = (ImageView) findViewById(R.id.result_image);
        resultTextView = (TextView) findViewById(R.id.result_text);
        Bundle extras = getIntent().getExtras();
        int pic = extras.getInt("pic");
        resultImageView.setImageResource(pic);
        boolean isSuccessful = extras.getBoolean("isSuccessful");
        resultTextView.setText(isSuccessful ? "Congratulations!" : "Try again.");
    }
}