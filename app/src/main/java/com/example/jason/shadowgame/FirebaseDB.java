package com.example.jason.shadowgame;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
/**
 * Created by Jennifer on 9/16/2017.
 */

public class FirebaseDatabase extends AppCompatActivity {
    private FirebaseDatabase database;
    private DatabaseReference databaseRef;



    @Override protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        database = FirebaseDatabase.getInstance();
        databaseRef = database.getReference("message");

        databaseRef.setValue("hello world");

    }


}
