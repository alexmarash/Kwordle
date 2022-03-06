package com.example.kwordle;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Statistics extends Opening {

    @Override
    protected void onCreate(Bundle savedInstantState) {
        super.onCreate(savedInstantState);
        setContentView(R.layout.statistics);

        PlayedModal playerStats = archiveHandler.readPlayedForPlayer(currentPlayer);

        TextView playerText = (TextView) findViewById(R.id.currentPlayer);
        playerText.setText(currentPlayer);
        TextView lettersText = (TextView) findViewById(R.id.numberOfLetters);
        lettersText.setText("5" + " Letter Words");

        





    }









    public void doneStats(View view) {
        //startActivity(new Intent(this, Opening.class));
        finish();

    }
}
