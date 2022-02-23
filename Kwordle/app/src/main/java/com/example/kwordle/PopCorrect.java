package com.example.kwordle;

import android.app.Activity;
import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class PopCorrect extends Activity {
    //private val windowParams = context.getSystemsService(Context.WINDOW_SERVICE) as WindowManager
    //WindowManager.LayoutParams(PixelFormat.TRANSLUCENT);

    @Override
    protected void onCreate(Bundle savedInstantState) {
        super.onCreate(savedInstantState);
        //overridePendingTransition(1, 1);
        setContentView(R.layout.popupccorrect);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width * 0.6),(int)(height * 0.6));

        TextView correctTextView = (TextView) findViewById(R.id.correctBox);
        TextView wrongTriesTextView = (TextView) findViewById(R.id.correctBox2);

        TextView answerTextView = (TextView) findViewById(R.id.answerBox);
        answerTextView.setText(MainActivity.theAnswer);

        TextView answerTriesTextView = (TextView) findViewById(R.id.answerTriesBox);

        if (MainActivity.correct) {
            correctTextView.setText("Congratulations you are Correct!!!! \n The answer was:");
            if (MainActivity.currentTry > 1) {
                answerTriesTextView.setText(String.valueOf(MainActivity.currentTry) + " tries");
            }
            else {
                answerTriesTextView.setText("1 try");
            }
        }
        else{
            correctTextView.setText("Sorry you were wrong \n The answer was:");
            wrongTriesTextView.setText("You blew your " + String.valueOf(MainActivity.currentTry) + " tries");
            answerTriesTextView.setText("You suck");
        }
    }

    public void doneCorrectClick(View view) {
        finish();
    }

}
