package com.example.kwordle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

public class Settings extends Activity  {

    @Override
    protected void onCreate(Bundle savedInstantState) {
        super.onCreate(savedInstantState);
        setContentView(R.layout.settings_popup);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * 0.6), (int) (height * 0.6));

        //RelativeLayout layout = (RelativeLayout)findViewById(R.id.r_layout);
        //Switch hardSet = new Switch(this);
        //hardSet.setTextOff("OFF");
        //hardSet.setTextOn("ON");

        Switch hard = (Switch) findViewById(R.id.hardSwitch);
        //hard.setChecked(Boolean.valueOf(Opening.playedGameModal.getHardMode()));
        hard.setChecked(Boolean.valueOf(Opening.hardMode));
        //layout.addView(sb);



    //Switch hard = (Switch) findViewById(R.id.hardSwitch);
    hard.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()

        {
            public void onCheckedChanged (CompoundButton buttonView,boolean isChecked){
            if (isChecked) {
                Opening.playedGameModal.setHardMode("true");
                Opening.hardMode = true;
                Opening.archiveHandler.setPlayerHardMode(Opening.playedGameModal, true);

                //System.out.println("=========set hard true");


            } else {
                Opening.playedGameModal.setHardMode("false");
                Opening.hardMode = false;
                Opening.archiveHandler.setPlayerHardMode(Opening.playedGameModal, false);

                //System.out.println("=========set hard false");
            }
        }
        });

    }

    public void resetWordsClick(View view) {
        //System.out.println("=========================BEFORE==============================================");
        //Opening.archiveHandler.displayArchive();

        //Opening.archiveHandler.deleteAllAnswers();
        Opening.archiveHandler.deleteAllAnswersForPlayer(Opening.currentPlayer);

        //System.out.println("=========================AFTER==============================================");
        //Opening.archiveHandler.displayArchive();


    }

    public void doneSettingsClick(View view) {
        finish();
    }

    public void changePlayerClick(View view) {
        startActivity(new Intent(this,Opening.class));



    }
}
