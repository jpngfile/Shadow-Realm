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

        Bitmap bmp = ((BitmapDrawable) getResources()
                .getDrawable(pic)).getBitmap();
        Bitmap blackWhiteBmp = blackWhite(bmp);
        resultImageView.setImageBitmap(blackWhiteBmp);
        boolean isSuccessful = extras.getBoolean("isSuccessful");
        resultTextView.setText(isSuccessful ? "Congratulations!" : "Try again.");
    }

    private Bitmap grayscale(Bitmap orginalBitmap) {
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0);

        ColorMatrixColorFilter colorMatrixFilter = new ColorMatrixColorFilter(colorMatrix);

        Bitmap blackAndWhiteBitmap = orginalBitmap.copy(Bitmap.Config.ARGB_8888, true);

        Paint paint = new Paint();
        paint.setColorFilter(colorMatrixFilter);

        Canvas canvas = new Canvas(blackAndWhiteBitmap);
        canvas.drawBitmap(blackAndWhiteBitmap, 0, 0, paint);

        return blackAndWhiteBitmap;
    }

    private Bitmap blackWhite(Bitmap originalBitmap) {
        Bitmap grayBitmap = grayscale(originalBitmap);
        int width = grayBitmap.getWidth();
        int height = grayBitmap.getHeight();

        int[]pixels = new int[width * height];
        grayBitmap.getPixels(pixels, 0, width, 0, 0, width, height);

        //iterate over pixels
        for (int y = 0; y < height; y++){
            for (int x = 0; x < width; x++){
                int pixel = grayBitmap.getPixel(x, y);
                int lowestByte = pixel & 0xff;
                if (lowestByte < 128) {
                    grayBitmap.setPixel(x, y, Color.BLACK);
                } else {
                    grayBitmap.setPixel(x, y, Color.WHITE);
                }
            }
        }
        return grayBitmap;
    }
}