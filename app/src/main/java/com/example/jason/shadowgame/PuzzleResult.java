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
    ImagePHash pHash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle_result);
        pHash = new ImagePHash();
        resultImageView = (ImageView) findViewById(R.id.result_image);
        resultTextView = (TextView) findViewById(R.id.result_text);
        Bundle extras = getIntent().getExtras();
        if (extras.containsKey("pic")) {
            int pic = extras.getInt("pic");
            resultImageView.setImageResource(pic);
        }
        if (extras.containsKey("fileUrl")) {
            double percent = 0;
            String defaultHash = "0000101100000111110110111111000010110000010101010";
            String fileUrl = extras.getString("fileUrl");
            // Bitmap bmp = bitmap from fileUrl
            //percent = getPercentSimilar(bmp, defaultHash);
            resultTextView.setText("Percentage: " + percent);
        }

    }

    private double getPercentSimilar(Bitmap img, String hash){
        String imgHash = pHash.calcPHash(img);
        return pHash.distance(imgHash, hash) / imgHash.length();
    }

}