package com.example.kwordle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class Opening extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    //public static SQLiteDatabase archivest;
    //public static Archive archives;
    public static WordLists wordLists ;
    public static ArchiveHandler archiveHandler;
    public String[] thesePlayers;
    public static String currentPlayer;
    public static Boolean hardMode = true;
    public static PlayedGameModal playedGameModal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opening);
        //archives = new Archive(this);
        //archivest = openOrCreateDatabase("kwordleArchive",MODE_PRIVATE, null );
        wordLists = new WordLists(this);

        archiveHandler = new ArchiveHandler(Opening.this);


        //String[] players = new String[archiveHandler.getCountOfPlayers()];
        thesePlayers = archiveHandler.getListOfPlayers();

        System.out.println(thesePlayers.length);
        System.out.println(thesePlayers);

        //archiveHandler.newPlayer("Test");
        /*
        if (thesePlayers.length == 0){

            archiveHandler.newPlayer("New Player");

            //TODO remove when you can add new players
            //archiveHandler.newPlayer("Katheryn");
            //archiveHandler.newPlayer("Alex");

            thesePlayers = archiveHandler.getListOfPlayers();

            //System.out.println("===========================ADDED NEW PLAYERS================================");
            //System.out.println(thesePlayers.length);
        }
        */
        //Create spinner, and listener for spinner click
        Spinner players = findViewById(R.id.playerSpinner);
        players.setOnItemSelectedListener(this);

        //Create the array adapter for the list of players
        ArrayAdapter ad = new ArrayAdapter(this, android.R.layout.simple_spinner_item, thesePlayers);

        //set simple layout resource file for each item
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        //TODO need to default to player with most games played

        //Set adapter date to bind spinner data to spinner
        players.setAdapter(ad);

        //currentPlayer = players.getSelectedItem().toString();


    }


    public void newGameClick(View view){
        //currentPlayer = players.getSelectedItem().toString();
        if (!currentPlayer.equals("     ")) {
            startActivity(new Intent(this, NewGamePopUp.class));
        }
        else {
            Toast.makeText(getApplicationContext(), "PLEASE SELECT A PLAYER \nYOUR GLOW IS TOO RADIANT FOR ME TO SEE YOUR FACE", Toast.LENGTH_LONG).show();
        }
    }

    public void stats(View view){
        startActivity(new Intent(this, Statistics.class));
    }

    public void settings(View view){
        startActivity(new Intent(this, Settings.class));

    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {




        this.currentPlayer = thesePlayers[position];

        //TODO this is for DEBUG
        Toast.makeText(getApplicationContext(), currentPlayer, Toast.LENGTH_LONG).show();

        if (!currentPlayer.equals("     ") && !currentPlayer.equals("New Player")) {

            playedGameModal = archiveHandler.readPlayedForPlayer(currentPlayer, 5);
            hardMode = archiveHandler.isThisHardMode(playedGameModal);
        }

        if (currentPlayer.equals("New Player")) {
            //startActivity(new Intent(this, PlayerEntry.class));
            //startActivity(new Intent(this.getApplicationContext(), PlayerEntry.class));
            //startActivity(new Intent(Opening.this, PlayerEntry.class));

            FragmentManager fm = getSupportFragmentManager();
            //Fragment fragment = fm.findFragmentById(R.id.new_player_container);
            Fragment fragment = fm.findFragmentById(R.id.new_player_container);

            if (fragment == null) {
                fragment = new PlayerEntry();
                fm.beginTransaction()
                        .add(R.id.new_player_container, fragment)
                        .commit();
                //System.out.println("888888888888888888888888888888888888888888");

            }


        }




    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
