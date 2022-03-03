package com.example.kwordle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

public class NewGamePopUp extends Activity {
    @Override
    protected void onCreate(Bundle savedInstantState) {
        super.onCreate(savedInstantState);
        setContentView(R.layout.new_game_popup);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width * 0.6),(int)(height * 0.6));

        }


    public void fiveLetterStart(View view) {
        startActivity(new Intent(this, FiveLetterBoard.class));
    }


    public void doneNewGameClick(View view) {
        finish();
    }

}

