package com.example.kwordle;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.Arrays;

public class PlayerEntry extends Fragment implements View.OnClickListener {
    private String thisPlayerName = "";
    private Button submitButton;
    public static Integer position = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle
    savedInstanceState){
        View view = inflater.inflate(R.layout.player_entry, container, false);
        EditText newPlayerName = (EditText) view.findViewById(R.id.newPlayerInput);
        submitButton = (Button) view.findViewById(R.id.submitButton);
        submitButton.setOnClickListener(this);

        newPlayerName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                thisPlayerName = newPlayerName.getText().toString();
                TextView newPlayerTitle = (TextView) view.findViewById(R.id.newPlayerEntryTitle);
                newPlayerTitle.setText(thisPlayerName);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }


        });

        return view;

    }


    @Override
    public void onClick(View view) {
        if (!this.thisPlayerName.equals("")) {
            Opening.archiveHandler.newPlayer(this.thisPlayerName);
            String[] thesePlayers;thesePlayers = Opening.archiveHandler.getListOfPlayers();
            this.position = Arrays.asList(thesePlayers).indexOf(thisPlayerName);
        }

        startActivity(new Intent(getActivity(), Opening.class));
    }




}
