package com.example.kwordle;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

public class PopCorrect extends Activity {

    //@SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstantState) {
        super.onCreate(savedInstantState);
        setContentView(R.layout.popupccorrect);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width * 0.6),(int)(height * 0.6));

        TextView correctTextView = (TextView) findViewById(R.id.correctBox);
        TextView wrongTriesTextView = (TextView) findViewById(R.id.correctBox2);

        TextView answerTextView = (TextView) findViewById(R.id.answerBox);
        answerTextView.setText(FiveLetterBoard.theAnswer);

        TextView answerTriesTextView = (TextView) findViewById(R.id.answerTriesBox);

        if (FiveLetterBoard.correct) {
            correctTextView.setText(R.string.congrats);
            if (FiveLetterBoard.currentTry > 1) {
                answerTriesTextView.setText(String.valueOf(FiveLetterBoard.currentTry) + getString(R.string.tries));
            }
            else {
                answerTriesTextView.setText(R.string.oneTry);
            }
        }
        else{
            correctTextView.setText(R.string.wrongAnswer);
            wrongTriesTextView.setText(getString(R.string.blewIt) + String.valueOf(FiveLetterBoard.currentTry) + getString(R.string.tries));
            answerTriesTextView.setText(R.string.youSuck);
        }
    }

    public void doneCorrectClick(View view) {
        finish();
    }

}
