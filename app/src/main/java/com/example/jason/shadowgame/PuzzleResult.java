package com.example.jason.shadowgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;

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
        if (extras.containsKey("pic")) {
            int pic = extras.getInt("pic");
            resultImageView.setImageResource(pic);
        }
        boolean isSuccessful = extras.getBoolean("isSuccessful");
        resultTextView.setText(isSuccessful ? "Congratulations!" : "Try again.");
    }

}