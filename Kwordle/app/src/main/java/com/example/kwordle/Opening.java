package com.example.kwordle;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Opening extends AppCompatActivity {

    public static SQLiteDatabase archives;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opening);
        archives = openOrCreateDatabase("kwordleArchive",MODE_PRIVATE, null );

    }

    public void newGameClick(View view){
        startActivity(new Intent(Opening.this, FiveLetterBoard.class));
    }

}
