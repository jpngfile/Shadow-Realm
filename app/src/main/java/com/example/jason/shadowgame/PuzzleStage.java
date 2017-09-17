package com.example.jason.shadowgame;

import static android.widget.ImageView.ScaleType.FIT_XY;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.Manifest;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;


public class PuzzleStage extends AppCompatActivity {
    int level = -1;
    Button stageStart, stageBack;
    TextView stageLevelLabel;
    ImageView stageImage;
    Uri file;


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 0) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                stageStart.setEnabled(true);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();

        if (extras == null || (level = extras.getInt("level")) < 0) {
            Log.w ("PuzzleStage.onCreate", "Invalid level: " + level);
            return;
        }
        setContentView(R.layout.activity_puzzle_stage);

        stageLevelLabel = (TextView) findViewById(R.id.stageLevelLabel);
        stageLevelLabel.setText("Level " + level);

        stageImage = (ImageView) findViewById(R.id.stageImage);
        Util.displayImage(stageImage, BitmapFactory.decodeResource(getResources(), getDrawableId (level)));

        stageStart = (Button) findViewById(R.id.stageStart);
        stageStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File outFile = getOutputMediaFile();
                takePicture();
            }
        });

        stageBack = (Button) findViewById(R.id.stageBack);
        stageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish ();
            }
        });

        Util.checkPermission(this, Manifest.permission.CAMERA);
        Util.checkPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100) {
            if (resultCode == RESULT_OK) {
                trimPicture(file);
                Util.displayImage(stageImage, BitmapFactory.decodeFile(file.getPath()));
                stageImage.setScaleType(FIT_XY);


                Intent i = new Intent(PuzzleStage.this, PuzzleResult.class);
                i.putExtra("fileUrl", file.getPath());
                i.putExtra("levelUrl", getDrawableId(level));
                i.putExtra("level", level);

                startActivity (i);
            }
        }
    }

    private void takePicture() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        file = Uri.fromFile(getOutputMediaFile());
        intent.putExtra(MediaStore.EXTRA_OUTPUT, file);

        startActivityForResult(intent, 100);
    }

    private static File getOutputMediaFile(){
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "CameraDemo");

        if (!mediaStorageDir.exists()){
            if (!mediaStorageDir.mkdirs()){
                return null;
            }
        }
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        return new File(mediaStorageDir.getPath() + File.separator + timeStamp + ".jpg");
    }

    private int getDrawableId (int level) {
        return getResources().getIdentifier("level_" + level, "drawable", "com.example.jason.shadowgame");
    }

    private void trimPicture (Uri uri) {
        Bitmap original = BitmapFactory.decodeFile(uri.getPath());
        int margin = (original.getHeight() - original.getWidth()) / 2;
        Bitmap modified = Bitmap.createBitmap(original, 0, margin, original.getWidth(), original.getWidth());
        Util.outputBitmap(modified, uri.getPath());
    }
}


