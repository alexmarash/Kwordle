package com.example.kwordle;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

public class PopCorrect extends Activity {

    @Override
    protected void onCreate(Bundle savedInstantState) {
        super.onCreate(savedInstantState);
        setContentView(R.layout.popupccorrect);

        String thisAnswer = getIntent().getExtras().getString("answer");
        int thisTry = getIntent().getExtras().getInt("try");
        boolean thisCorrect = getIntent().getExtras().getBoolean("correct");

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * 0.6), (int) (height * 0.6));

        TextView correctTextView = (TextView) findViewById(R.id.correctBox);
        TextView wrongTriesTextView = (TextView) findViewById(R.id.correctBox2);

        TextView answerTextView = (TextView) findViewById(R.id.answerBox);
        answerTextView.setText(thisAnswer);
        //answerTextView.setText(FiveLetterBoard.theAnswer);

        TextView answerTriesTextView = (TextView) findViewById(R.id.answerTriesBox);

        if (thisCorrect) {
            correctTextView.setText(R.string.congrats);
            if (thisTry > 1) {
                answerTriesTextView.setText(thisTry + getString(R.string.tries));
            } else {
                answerTriesTextView.setText(R.string.oneTry);
            }
        } else {
            correctTextView.setText(R.string.wrongAnswer);
            wrongTriesTextView.setText(getString(R.string.blewIt) + thisTry + getString(R.string.tries));
            answerTriesTextView.setText(R.string.youSuck);
        }

        /*
        if (FiveLetterBoard.correct) {
            correctTextView.setText(R.string.congrats);
            if (FiveLetterBoard.currentTry > 1) {
                answerTriesTextView.setText(FiveLetterBoard.currentTry + getString(R.string.tries));
            }
            else {
                answerTriesTextView.setText(R.string.oneTry);
            }
        }
        else{
            correctTextView.setText(R.string.wrongAnswer);
            wrongTriesTextView.setText(getString(R.string.blewIt) + FiveLetterBoard.currentTry + getString(R.string.tries));
            answerTriesTextView.setText(R.string.youSuck);
        }
         */
    }

    public void doneCorrectClick(View view) {
        finish();
    }

}
