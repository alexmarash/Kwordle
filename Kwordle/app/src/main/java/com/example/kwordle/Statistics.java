package com.example.kwordle;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.DecimalFormat;
import java.util.ArrayList;


public class Statistics extends Opening {

    // variable for our bar chart
    BarChart barChart;

    // variable for our bar data.
    BarData barData;

    // variable for our bar data set.
    BarDataSet barDataSet;

    // array list for storing entries.
    ArrayList<BarEntry> barEntriesArrayList;

    PlayedGameModal playerStats;



    @Override
    protected void onCreate(Bundle savedInstantState) {
        super.onCreate(savedInstantState);
        setContentView(R.layout.statistics);

        //TODO add in a spinner for the amount of letters  - Do when added new number of letter games
        //Integer theLetters = 5;

        int theLetters = getIntent().getExtras().getInt("letters");

        TextView playerText = (TextView) findViewById(R.id.currentPlayer);
        playerText.setText(currentPlayer);
        TextView lettersText = (TextView) findViewById(R.id.numberOfLetters);
        lettersText.setText(theLetters + getString(R.string.letterWords));

        //Pull the stats for the players
        playerStats = archiveHandler.readPlayedForPlayer(currentPlayer, theLetters);

        TextView gamedPlayed = (TextView) findViewById(R.id.played);
        gamedPlayed.setText(String.valueOf(playerStats.getPlayed()));
        TextView wins = (TextView) findViewById(R.id.winPercent);

        double winPrecentage = 0.0;
        if (playerStats.getAmountWon() > 0 && playerStats.getPlayed() > 0){
                winPrecentage = Math.round(100.0 * playerStats.getAmountWon() / playerStats.getPlayed());
            }

        DecimalFormat f = new DecimalFormat("##.00");

        //wins.setText(String.format("%.2f", winPrecentage) + "%");
        wins.setText(f.format(winPrecentage) + "%");

        TextView currentStreak = (TextView) findViewById(R.id.currentStreak);
        currentStreak.setText(String.valueOf(playerStats.getCurrentStreak()));

        TextView maxStreak = (TextView) findViewById(R.id.maxStreak);
        maxStreak.setText(String.valueOf(playerStats.getMaxStreak()));


        double minSeconds = Math.round((playerStats.getMinTime() %1) * 60);
        double minMinutes = playerStats.getMinTime() - (playerStats.getMinTime() %1);


        TextView quickestGame = (TextView) findViewById(R.id.minTime);
        quickestGame.setText((int) minMinutes + "'" + (int) minSeconds +"\"");
        //quickestGame.setText(String.valueOf(playerStats.getMinTime()));


        double maxSeconds = Math.round((playerStats.getMaxTime() %1) * 60);
        double maxMinutes = playerStats.getMaxTime() - (playerStats.getMaxTime() %1);


        TextView longestGame = (TextView) findViewById(R.id.maxTime);
        longestGame.setText((int) maxMinutes + "'" + (int) maxSeconds +"\"");
        //longestGame.setText(String.valueOf(playerStats.getMaxTime()));


        // initializing variable for bar chart.
        barChart = findViewById(R.id.idBarChart);

        // calling method to get bar entries.
        getBarEntries();

        // creating a new bar data set.
        barDataSet = new BarDataSet(barEntriesArrayList, "Guess Distribution");

        // creating a new bar data and
        // passing our bar data set.
        barData = new BarData(barDataSet);

        // below line is to set data
        // to our bar chart.
        barChart.setData(barData);

        // adding color to our bar data set.
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);

        // setting text color.
        barDataSet.setValueTextColor(Color.BLACK);

        // setting text size
        barDataSet.setValueTextSize(16f);
        barChart.getDescription().setEnabled(false);

    }

    private void getBarEntries() {
        // creating a new array list
        barEntriesArrayList = new ArrayList<>();

        // adding new entry to our array list with bar
        // entry and passing x and y axis value to it.
        barEntriesArrayList.add(new BarEntry(1f, playerStats.getOneWon()));
        barEntriesArrayList.add(new BarEntry(2f, playerStats.getTwoWon()));
        barEntriesArrayList.add(new BarEntry(3f, playerStats.getThreeWon()));
        barEntriesArrayList.add(new BarEntry(4f, playerStats.getFourWon()));
        barEntriesArrayList.add(new BarEntry(5f, playerStats.getFiveWon()));
        barEntriesArrayList.add(new BarEntry(6f, playerStats.getSixWon()));
    }

    public void doneStats(View view) {
        //startActivity(new Intent(this, Opening.class));
        finish();

    }
}
