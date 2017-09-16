package com.example.jason.shadowgame;
    import static android.widget.ImageView.ScaleType.CENTER;
    import static android.widget.ImageView.ScaleType.CENTER_CROP;
    import static android.widget.ImageView.ScaleType.CENTER_INSIDE;
    import static android.widget.ImageView.ScaleType.FIT_CENTER;
    import static android.widget.ImageView.ScaleType.FIT_START;
    import static android.widget.ImageView.ScaleType.FIT_XY;
    import static com.example.jason.shadowgame.R.attr.background;

    import android.Manifest;
    import android.content.Intent;
    import android.content.pm.PackageManager;
    import android.net.Uri;
    import android.os.Environment;
    import android.provider.MediaStore;
    import android.support.v4.app.ActivityCompat;
    import android.support.v4.content.ContextCompat;
    import android.support.v7.app.AppCompatActivity;
    import android.os.Bundle;
    import android.view.View;
    import android.view.ViewManager;
    import android.widget.Button;
    import android.widget.ImageView;
    import android.widget.TextView;

    import java.io.File;
    import java.text.SimpleDateFormat;
    import java.util.Date;


public class PuzzleStage extends AppCompatActivity {
    private Button takePictureButton;
    private ImageView imageView;
    private ImageView border;
    private TextView prompt;
    Uri file;
    static int x = 0;
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 0) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                takePictureButton.setEnabled(true);
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle_stage);

        takePictureButton = (Button) findViewById(R.id.button_image);
        imageView = (ImageView) findViewById(R.id.imageview);
        border = (ImageView) findViewById(R.id.border);
        prompt = (TextView) findViewById(R.id.prompt);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            takePictureButton.setEnabled(false);
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }
    }


    public void takePicture(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        file = Uri.fromFile(getOutputMediaFile());
        intent.putExtra(MediaStore.EXTRA_OUTPUT, file);

        startActivityForResult(intent, 100);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100) {
            if (resultCode == RESULT_OK) {


                imageView.setImageURI(file);

                imageView.setRotation(90);

                imageView.setScaleType(FIT_XY);
                //imageView.setScaleType(FIT_CENTER);
                border.setVisibility(View.INVISIBLE);
                prompt.setVisibility(View.INVISIBLE);
            }
        }
    }


    private static File getOutputMediaFile(){
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "CameraDemo");

        if (!mediaStorageDir.exists()){
            if (!mediaStorageDir.mkdirs()){
                return null;
            }
        }
        x++;
//        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        return new File(mediaStorageDir.getPath() + File.separator +
                "test"+x+".jpg");
    }
}


