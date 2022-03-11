package com.example.kwordle;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class Opening extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

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

        //Get all the starting words and checking words
        wordLists = new WordLists(this);

        //Pull the databases and get the player list
        archiveHandler = new ArchiveHandler(Opening.this);
        thesePlayers = archiveHandler.getListOfPlayers();

        //Create spinner, and listener for spinner click
        Spinner players = findViewById(R.id.playerSpinner);
        players.setOnItemSelectedListener(this);

        //Create the array adapter for the list of players
        ArrayAdapter ad = new ArrayAdapter(this, android.R.layout.simple_spinner_item, thesePlayers);

        //set simple layout resource file for each item
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Set adapter date to bind spinner data to spinner
        players.setAdapter(ad);
    }

    // Start a new game
    public void newGameClick(View view){
        // Check if no player is selected, if not go to new game popup, if so send a toast warning
        if (!currentPlayer.equals("     ")) {
            startActivity(new Intent(this, NewGamePopUp.class));
        }
        else {
            noPlayerSelected(view);
        }
    }

    public void noPlayerSelected(View view){
        Toast toast = Toast.makeText(getApplicationContext(), "PLEASE SELECT A PLAYER! I CANT SEE YOUR LOVELY FACE", Toast.LENGTH_LONG);
        LinearLayout layout = (LinearLayout) toast.getView();
        layout.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        toast.show();
    }


    // Go to stats page
    public void stats(View view){
        if (!currentPlayer.equals("     ") && !currentPlayer.equals("New Player")) {
            startActivity(new Intent(this, Statistics.class));
        }
        else {
            noPlayerSelected(view);
        }
    }

    // Go to Settings page
    public void settings(View view){
        if (!currentPlayer.equals("     ") && !currentPlayer.equals("New Player")) {
            startActivity(new Intent(this, Settings.class));
        }
        else {
            noPlayerSelected(view);
        }
    }

    //Selecting a player
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        //Set the player to the player on the spinner
        this.currentPlayer = thesePlayers[position];

        //If a player is selected set the hardMode
        if (!currentPlayer.equals("     ") && !currentPlayer.equals("New Player")) {
            playedGameModal = archiveHandler.readPlayedForPlayer(currentPlayer, 5);
            hardMode = archiveHandler.isThisHardMode(playedGameModal);
        }

        //If a new player is selected start the fragment that opens the new player entry
        if (currentPlayer.equals("New Player")) {
            FragmentManager fm = getSupportFragmentManager();
            Fragment fragment = fm.findFragmentById(R.id.new_player_container);

            if (fragment == null) {
                fragment = new PlayerEntry();
                fm.beginTransaction()
                        .add(R.id.new_player_container, fragment)
                        .commit();
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
