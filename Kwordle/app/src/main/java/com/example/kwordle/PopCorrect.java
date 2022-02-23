package com.example.kwordle;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;

public class PopCorrect extends Activity {
    @Override
    protected void onCreate(Bundle savedInstantState) {
        super.onCreate(savedInstantState);

        setContentView(R.layout.popupccorrect);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width * 0.6),(int)(height * 0.6));
    }


}
