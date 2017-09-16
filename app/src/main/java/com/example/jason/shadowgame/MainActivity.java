package com.example.jason.shadowgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button resultButton;


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

    }

    public void viewResults(View view) {
        Intent resultIntent = new Intent(this, PuzzleResult.class);
        resultIntent.putExtra("pic", R.drawable.clay_block);
        resultIntent.putExtra("isSuccessful", false);
        startActivity(resultIntent);
    }
}
