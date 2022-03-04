package com.example.kwordle;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

public class Settings extends Activity {

    @Override
    protected void onCreate(Bundle savedInstantState) {
        super.onCreate(savedInstantState);
        setContentView(R.layout.settings_popup);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width * 0.6),(int)(height * 0.6));

    }

    public void resetWordsClick(View view) {
        //System.out.println("=========================BEFORE==============================================");
        //Opening.archiveHandler.displayArchive();

        Opening.archiveHandler.deleteAllAnswers();

        //System.out.println("=========================AFTER==============================================");
        //Opening.archiveHandler.displayArchive();


    }

    public void doneSettingsClick(View view) {
        finish();
    }
}
