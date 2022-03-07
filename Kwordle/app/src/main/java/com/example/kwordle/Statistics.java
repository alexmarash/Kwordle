package com.example.kwordle;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Statistics extends Opening {

    @Override
    protected void onCreate(Bundle savedInstantState) {
        super.onCreate(savedInstantState);
        setContentView(R.layout.statistics);


        Integer theLetters = 5;

        TextView playerText = (TextView) findViewById(R.id.currentPlayer);
        playerText.setText(currentPlayer);
        TextView lettersText = (TextView) findViewById(R.id.numberOfLetters);
        lettersText.setText(String.valueOf(theLetters) + " Letter Words");



        PlayedModal playerStats = archiveHandler.readPlayedForPlayer(currentPlayer, theLetters);

        TextView gamedPlayed = (TextView) findViewById(R.id.played);
        gamedPlayed.setText(String.valueOf(playerStats.getPlayed()));
        TextView wins = (TextView) findViewById(R.id.winPercent);

        double winPrecentage = Math.round(100 * playerStats.getAmountWon()/playerStats.getPlayed());
        wins.setText(String.valueOf(winPrecentage) + "%");

        TextView currentStreak = (TextView) findViewById(R.id.currentStreak);
        currentStreak.setText(playerStats.getCurrentStreak());

        TextView maxStreak = (TextView) findViewById(R.id.maxStreak);
        maxStreak.setText(playerStats.getMaxStreak());

        TextView quickestGame = (TextView) findViewById(R.id.minTime);
        quickestGame.setText(String.valueOf(playerStats.getMinTime()));

        TextView longestGame = (TextView) findViewById(R.id.maxTime);
        longestGame.setText(String.valueOf(playerStats.getMaxTime()));




    }









    public void doneStats(View view) {
        //startActivity(new Intent(this, Opening.class));
        finish();

    }
}
