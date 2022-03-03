package com.example.kwordle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Opening extends AppCompatActivity {

    //public static SQLiteDatabase archivest;
    public static Archive archives;
    public static WordLists wordLists ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opening);
        archives = new Archive(this);
        //archivest = openOrCreateDatabase("kwordleArchive",MODE_PRIVATE, null );
        wordLists = new WordLists(this);
    }

    public void newGameClick(View view){
        startActivity(new Intent(Opening.this, FiveLetterBoard.class));
    }

}
