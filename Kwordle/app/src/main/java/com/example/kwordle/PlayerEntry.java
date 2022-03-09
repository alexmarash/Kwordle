package com.example.kwordle;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class PlayerEntry extends Fragment  {
    private EditText newPlayerName;
    private String thisPlayerName = "";


    @Override
    public void onCreate(Bundle savedInstanceState) {

        System.out.println("[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[");

        super.onCreate(savedInstanceState);
        //setContentView(R.layout.player_entry);
        //newPlayerName = (EditText) findViewById(R.id.newPlayerInput);




    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle
    savedInstanceState){
        System.out.println("[[[[[[[[[[[[[[[[===================[[[[[[[[[[[[[[[[[[[[[[[[[[");
        View view = inflater.inflate(R.layout.player_entry, container);

        System.out.println("[[[[[[[[[[[[[[[[+++++++++++++++++[[[[[[[[[[[[[[[[[[[[[[[[[[");

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
    /*

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
    }






    public void enterPlayer (View view) {
        //System.out.println("+++++++++++++++++++++++++++++++++++++" + newPlayerName.getText().toString());
        this.thisPlayerName = newPlayerName.getText().toString();
        TextView newPlayerTitle = (TextView) view.findViewById(R.id.newPlayerEntryTitle);
        newPlayerTitle.setText(thisPlayerName);
        System.out.println("+++++++++++++++++++++++++++++++++++++" + newPlayerName.getText().toString());
        System.out.println("+++++++++++++++++++++++++++++++++++++" + this.thisPlayerName);

    }

   //@Override
    //public View OnCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
     //   View v = inflater.inflate(R.layout.player_entry, container, false);
    /*

        newPlayerName = (EditText) findViewById(R.id.newPlayerInput);
        newPlayerName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                thisPlayerName = charSequence.toString();

                System.out.println("+++++++++++++++++++++++++++++++++++++" + thisPlayerName);

                Opening.archiveHandler.newPlayer(thisPlayerName);
                TextView newPlayerTitle = (TextView) v.findViewById(R.id.newPlayerEntryTitle);
                newPlayerTitle.setText(thisPlayerName);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    return v;

    }


     */
    public void submitClick(View view) {

        System.out.println("ooooooooooooooooooooooooooooo" + this.thisPlayerName);

        if (!this.thisPlayerName.equals("")) {


            System.out.println("ooooooooooooooooooooooooooooo" + newPlayerName.getText().toString());

            Opening.archiveHandler.newPlayer(this.thisPlayerName);
        }


        startActivity(new Intent(getActivity(), Opening.class));

        //thesePlayers = archiveHandler.getListOfPlayers();
        //finish();
    }

}
