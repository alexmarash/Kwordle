package com.example.kwordle;

import android.os.Bundle;
import android.view.View;

public class Statistics extends Opening {

    @Override
    protected void onCreate(Bundle savedInstantState) {
        super.onCreate(savedInstantState);
        setContentView(R.layout.statistics);


    }




    public void doneStats(View view) {
        //startActivity(new Intent(this, Opening.class));
        finish();

    }
}
