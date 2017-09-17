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

    private static final String TAG = "ImagePHASH";
    private int size = 32;
    private int smallerSize = 8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle_result);
        initCoefficients();
        resultImageView = (ImageView) findViewById(R.id.result_image);
        resultTextView = (TextView) findViewById(R.id.result_text);
        Bundle extras = getIntent().getExtras();
        int pic = extras.getInt("pic");
        
        resultImageView.setImageResource(pic);
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

    public Bitmap resize(Bitmap bm, int newHeight, int newWidth) {
        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = null;
        try {
            resizedBitmap = Bitmap.createScaledBitmap(bm, newWidth, newHeight, false);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return resizedBitmap;
    }

    private static int getBlue(Bitmap img, int x, int y) {
        return (img.getPixel(x, y)) & 0xff;
    }

    private Bitmap blackWhite(Bitmap originalBitmap) {
        Bitmap grayBitmap = grayscale(originalBitmap);
        int width = grayBitmap.getWidth();
        int height = grayBitmap.getHeight();

        int[]pixels = new int[width * height];
        grayBitmap.getPixels(pixels, 0, width, 0, 0, width, height);

        //iterate over pixels
        //This can be optimized with multi-threading
        for (int y = 0; y < height; y++){
            for (int x = 0; x < width; x++){
                int pixel = grayBitmap.getPixel(x, y);
                int lowestByte = pixel & 0xff;
                if (lowestByte < 128) {
                    pixels[(y * width) + x] = Color.BLACK;
                } else {
                    pixels[(y * width) + x] = Color.WHITE;
                }
            }
        }
        grayBitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return grayBitmap;
    }

    public int[] imageHash(Bitmap bmp) {
        double sum = 0;
        int width = bmp.getWidth();
        int height = bmp.getHeight();
        int [] pixels = new int[width * height];
        bmp.getPixels(pixels, 0, width, 0, 0, width, height);
        int size = pixels.length;
        double [] result = new double[size];
        double gray, avg;
        int r, g, b, c, i;
        for (i = 0; i < size; i++) {
            r = (pixels[i] >> 16) & 0xFF;
            g = (pixels[i] >> 8) & 0xFF;
            b = (pixels[i]) & 0xFF;
            gray = 0.2126 * r + 0.7152 * g + 0.0722 * b;
            sum += gray;
            result[i] = gray;
        }
        avg = sum / size;
        int[] returnArr = new int[size];
        for (i = 0; i < size; i++) {
            returnArr[i] = result[i] > avg ? 1 : 0;
        }
        return returnArr;
    }

    public static double getSimilarity(int[] hash1, int[] hash2) {

        int N = hash1.length;

        int[] x_coeffs = hash1;
        int[] y_coeffs = hash2;

        double r[] = new double[N];
        double sumx = 0.0;
        double sumy = 0.0;
        for (int i=0;i < N;i++){
            sumx += x_coeffs[i] & 0xFF;
            sumy += y_coeffs[i] & 0xFF;
        }
        double meanx = sumx/N;
        double meany = sumy/N;
        double max = 0;
        for (int d=0;d<N;d++){
            double num = 0.0;
            double denx = 0.0;
            double deny = 0.0;
            for (int i=0;i<N;i++){
                num  += (x_coeffs[i]-meanx)*(y_coeffs[(N+i-d)%N]-meany);
                denx += Math.pow((x_coeffs[i] - meanx), 2);
                deny += Math.pow((y_coeffs[(N + i - d) % N] - meany), 2);
            }
            r[d] = num/Math.sqrt(denx * deny);
            if (r[d] > max)
                max = r[d];
        }
        return max;  //To change body of created methods use File | Settings | File Templates.
    }
    public int distance(String s1, String s2) {
        if (s1 != null && s2 != null) {
            if (s1.length() == s2.length() && s1.length() != 0 && s2.length() != 0) {
                int counter = 0;
                for (int k = 0; k < s1.length(); k++) {
                    if (s1.charAt(k) != s2.charAt(k)) {
                        counter++;
                    }
                }
                Log.d(TAG, "Distance: " + counter + " from " + s1.length());
                return counter;
            } else {
                Log.d(TAG, "Length of strings not equal: s1 = " + s1.length() + " and s2 = " + s2.length() + " or smaller then 0");
                return -1;
            }
        }
        return -1;
    }

    // Returns a 'binary string' (like. 001010111011100010) which is easy to do a hamming distance on.
    public String calcPHash(Bitmap img) {

        /* 1. Reduce size.
         * Like Average Hash, pHash starts with a small image.
         * However, the image is larger than 8x8; 32x32 is a good size.
         * This is really done to simplify the DCT computation and not
         * because it is needed to reduce the high frequencies.
         */
        img = resize(img, size, size);

        /* 2. Reduce color.
         * The image is reduced to a grayscale just to further simplify
         * the number of computations.
         */
        String hash = "";
        if (img != null) {
            img = grayscale(img);

            double[][] vals = new double[size][size];

            for (int x = 0; x < img.getWidth(); x++) {
                for (int y = 0; y < img.getHeight(); y++) {
                    vals[x][y] = getBlue(img, x, y);
                }
            }

        /* 3. Compute the DCT.
         * The DCT separates the image into a collection of frequencies
         * and scalars. While JPEG uses an 8x8 DCT, this algorithm uses
         * a 32x32 DCT.
         */
            long start = System.currentTimeMillis();
            double[][] dctVals = applyDCT(vals);
            Log.d(TAG, String.valueOf((System.currentTimeMillis() - start)));

        /* 4. Reduce the DCT.
         * This is the magic step. While the DCT is 32x32, just keep the
         * top-left 8x8. Those represent the lowest frequencies in the
         * picture.
         */
        /* 5. Compute the average value.
         * Like the Average Hash, compute the mean DCT value (using only
         * the 8x8 DCT low-frequency values and excluding the first term
         * since the DC coefficient can be significantly different from
         * the other values and will throw off the average).
         */
            double total = 0;

            for (int x = 0; x < smallerSize; x++) {
                for (int y = 0; y < smallerSize; y++) {
                    total += dctVals[x][y];
                }
            }
            total -= dctVals[0][0];

            double avg = total / (double) ((smallerSize * smallerSize) - 1);

        /* 6. Further reduce the DCT.
         * This is the magic step. Set the 64 hash bits to 0 or 1
         * depending on whether each of the 64 DCT values is above or
         * below the average value. The result doesn't tell us the
         * actual low frequencies; it just tells us the very-rough
         * relative scale of the frequencies to the mean. The result
         * will not vary as long as the overall structure of the image
         * remains the same; this can survive gamma and color histogram
         * adjustments without a problem.
         */


            for (int x = 0; x < smallerSize; x++) {
                for (int y = 0; y < smallerSize; y++) {
                    if (x != 0 && y != 0) {
                        hash += (dctVals[x][y] > avg ? "1" : "0");
                    }
                }
            }
            Log.d(TAG, "HASH result: " + hash);
        } else {
            return null;
        }
        return hash;
    }
    // DCT function stolen from http://stackoverflow.com/questions/4240490/problems-with-dct-and-idct-algorithm-in-java

    private double[] c;

    private void initCoefficients() {
        c = new double[size];

        for (int i = 1; i < size; i++) {
            c[i] = 1;
        }
        c[0] = 1 / Math.sqrt(2.0);
    }

    private double[][] applyDCT(double[][] f) {
        int N = size;

        double[][] F = new double[N][N];
        for (int u = 0; u < N; u++) {
            for (int v = 0; v < N; v++) {
                double sum = 0.0;
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        sum += Math.cos(((2 * i + 1) / (2.0 * N)) * u * Math.PI) * Math.cos(((2 * j + 1) / (2.0 * N)) * v * Math.PI) * (f[i][j]);
                    }
                }
                sum *= ((c[u] * c[v]) / 4.0);
                F[u][v] = sum;
            }
        }
        return F;
    }
}