package com.example.kwordle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

import androidx.appcompat.widget.SwitchCompat;

public class Settings extends Activity  {

    @Override
    protected void onCreate(Bundle savedInstantState) {
        super.onCreate(savedInstantState);
        setContentView(R.layout.settings_popup);

        //Set window size
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        //getWindow().setLayout((int) (width * 0.6), (int) (height * 0.6));
        getWindow().setLayout((int) (800), (int) (1200));

        //Initialize switch
        SwitchCompat hard = (SwitchCompat) findViewById(R.id.hardSwitch);

        //Set switch based on hardmode
        hard.setChecked(Opening.hardMode);

        //Create listener for switch
        //Based on swtich set hard mode in modal, opening and update player in database
        hard.setOnCheckedChangeListener((buttonView, isChecked) -> {
        if (isChecked) {
            Opening.playedGameModal.setHardMode("true");
            Opening.hardMode = true;
            Opening.archiveHandler.setPlayerHardMode(Opening.playedGameModal, true);
        } else {
            Opening.playedGameModal.setHardMode("false");
            Opening.hardMode = false;
            Opening.archiveHandler.setPlayerHardMode(Opening.playedGameModal, false);

        }
    });
    }

    //Reset all words buttong
    public void resetWordsClick(View view) {
        Opening.archiveHandler.deleteAllAnswersForPlayer(Opening.currentPlayer);
    }

    //Change player button
    public void changePlayerClick(View view) {
        startActivity(new Intent(this,Opening.class));
    }

    //Return button
    public void doneSettingsClick(View view) {
        finish();
    }
}
