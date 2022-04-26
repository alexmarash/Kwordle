package com.example.kwordle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Toast;

public class NewGamePopUp extends Activity {

    public String comingSoon;

    @Override
    protected void onCreate(Bundle savedInstantState) {
        super.onCreate(savedInstantState);
        setContentView(R.layout.new_game_popup);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        //getWindow().setLayout((int)(width * 0.6),(int)(height * 0.6));
        getWindow().setLayout((int) (800), (int) (1200));
    }


    public void fiveLetterStart(View view) {
        startActivity(new Intent(this, FiveLetterBoard.class));
        finish();
    }


    public void doneNewGameClick(View view) {
        finish();
    }

    public void sixLetterStart(View view) {
        Toast.makeText(getApplicationContext(), getString(R.string.comingsoon), Toast.LENGTH_LONG).show();
    }

    public void tetrisStart(View view) {
        Toast.makeText(getApplicationContext(), getString(R.string.comingsoon), Toast.LENGTH_LONG).show();
    }
}



