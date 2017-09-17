package com.example.jason.shadowgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PuzzleLevels extends AppCompatActivity {

    Button e1, e2, e3, e4, e5, m1, m2, m3, m4, m5, h1, h2, h3, h4, h5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle_levels);
        e1 = (Button) findViewById(R.id.e1);
        e2 = (Button) findViewById(R.id.e2);
        e3 = (Button) findViewById(R.id.e3);
        e4 = (Button) findViewById(R.id.e4);
        e5 = (Button) findViewById(R.id.e5);
        e1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                e(v);
            }
        });
        e2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                e(v);
            }
        });
        e3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                e(v);
            }
        });
        e4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                e(v);
            }
        });
        e5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                e(v);
            }
        });

        m1 = (Button) findViewById(R.id.m1);
        m2 = (Button) findViewById(R.id.m2);
        m3 = (Button) findViewById(R.id.m3);
        m4 = (Button) findViewById(R.id.m4);
        m5 = (Button) findViewById(R.id.m5);
        m1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                m(v);
            }
        });
        m2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                m(v);
            }
        });
        m3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                m(v);
            }
        });
        m4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                m(v);
            }
        });
        m5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                m(v);
            }
        });

        h1 = (Button) findViewById(R.id.h1);
        h2 = (Button) findViewById(R.id.h2);
        h3 = (Button) findViewById(R.id.h3);
        h4 = (Button) findViewById(R.id.h4);
        h5 = (Button) findViewById(R.id.h5);
        h1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                h(v);
            }
        });
        h2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                h(v);
            }
        });
        h3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                h(v);
            }
        });
        h4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                h(v);
            }
        });
        h5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                h(v);
            }
        });
    }

    public void e(View view) {
        Intent resultIntent = new Intent(this, PuzzleStage.class);
        resultIntent.putExtra("level", 0);
        startActivity(resultIntent);
    }

    public void m(View view) {
        Intent resultIntent = new Intent(this, PuzzleStage.class);
        resultIntent.putExtra("level", 1);
        startActivity(resultIntent);
    }

    public void h(View view) {
        Intent resultIntent = new Intent(this, PuzzleStage.class);
        resultIntent.putExtra("level", 2);
        startActivity(resultIntent);
    }
}