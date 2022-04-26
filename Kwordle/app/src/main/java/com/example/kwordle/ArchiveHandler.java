package com.example.kwordle;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


// https://www.geeksforgeeks.org/how-to-read-data-from-sqlite-database-in-android/

public class ArchiveHandler extends SQLiteOpenHelper {

    // creating a constant variables for our database.
    // below variable is for our database name and version
    private static final String DB_NAME = "kwordledb";
    private static final int DB_VERSION = 1;

    // Table for the game word statistics and all individual columns
    private static final String TABLE_NAME_WORD = "WORDSTATS";
    private static final String answer_COL = "Answer";
    private static final String time_COL = "Time";
    private static final String trys_COL = "Trys";
    private static final String correct_COL = "Correct";

    // Table for the player statistics and all individual columns
    private static final String TABLE_NAME_PLAYED_GAME = "PLAYEDGAMESTATS";
    private static final String played_COL = "played";
    private static final String won_COL = "won";
    private static final String curr_Streak_COL = "currentStreak";
    private static final String max_Streak_COL = "maxStreak";
    private static final String min_Time_COL = "minTime";
    private static final String max_Time_COL = "maxTime";
    private static final String oneWon_COL = "oneWon";
    private static final String twoWon_COL = "twoWon";
    private static final String threeWon_COL = "threeWon";
    private static final String fourWon_COL = "fourWon";
    private static final String fiveWon_COL = "fiveWon";
    private static final String sixWon_COL = "sixWon";
    private static final String sevenWon_COL = "sevenWon";
    private static final String eightWon_COL = "eightWon";
    private static final String nineWon_COL = "nineWon";
    private static final String tenWon_COL = "tenWon";
    private static final String hard_COL = "hardMode";

    // Shared columns
    private static final String player_COL = "player";
    private static final String numbOfLetters_COL = "numbLetters";

    // creating a constructor for our database handler.
    public ArchiveHandler(Context context) { super(context, DB_NAME, null, DB_VERSION);
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        // on below line we are creating
        // an sqlite query and we are
        // setting our column names
        // along with their data types.

        // Table for word statistics for players
        String queryWORD = "CREATE TABLE " + TABLE_NAME_WORD + " ("
                + player_COL + " TEXT, "
                + answer_COL + " TEXT, "
                + correct_COL + " TEXT, "
                + time_COL + " REAL, "
                + trys_COL + " INTEGER, "
                + numbOfLetters_COL + " INTEGER)";

        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(queryWORD);

        //Table for player game statistics
        String queryPLAYEDGAME = "CREATE TABLE " + TABLE_NAME_PLAYED_GAME + " ("
                + player_COL + " TEXT, "
                + played_COL + " INTEGER, "
                + won_COL + " INTEGER, "
                + curr_Streak_COL + " INTEGER, "
                + max_Streak_COL + " INTEGER, "
                + min_Time_COL + " REAL, "
                + max_Time_COL + " REAL, "
                + numbOfLetters_COL + " INTEGER, "
                + oneWon_COL + " INTEGER, "
                + twoWon_COL + " INTEGER, "
                + threeWon_COL + " INTEGER, "
                + fourWon_COL + " INTEGER, "
                + fiveWon_COL + " INTEGER, "
                + sixWon_COL + " INTEGER, "
                + sevenWon_COL + " INTEGER, "
                + eightWon_COL + " INTEGER, "
                + nineWon_COL + " INTEGER, "
                + tenWon_COL + " INTEGER,"
                + hard_COL + " TEXT)";

        db.execSQL(queryPLAYEDGAME);

        db.close();
    }

    // this method is use to add played word to the Statistics database.
    public void addWord(String player, String theAnswer, String ifCorrect, double time, Integer trys, Integer letters) {

        //If the word was previously entered, delete it so it can be replaced
        if (checkIfUsed(theAnswer)){
            deleteWord(theAnswer);
        }

        // on below line we are creating a variable for our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a ariable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all value along with its key and value pair.
        values.put(player_COL, player);
        values.put(answer_COL, theAnswer);
        values.put(correct_COL, ifCorrect);
        values.put(time_COL, time);
        values.put(trys_COL, trys);
        values.put(numbOfLetters_COL, letters);

        // after adding all values we are passing content values to our table.
        db.insert(TABLE_NAME_WORD, null, values);

        // at last we are closing our database after adding database.
        db.close();
    }

    // Add a played game to the player database
    public void addGame(String player, Integer numbOfLetters, Boolean ifWon, double time, Integer numbOfTrys){

        // Create a modal for the player/number of letters
        PlayedGameModal playedList = readPlayedForPlayer(player, numbOfLetters);

        // Add one to get the played games
        Integer numberPlayed = playedList.getPlayed() + 1;

        //Initialize all other items
        Integer numberWon = playedList.getAmountWon();
        Integer newStreak = playedList.getCurrentStreak();
        Integer newMaxStreak = playedList.getMaxStreak();
        double newMinTime = playedList.getMinTime();
        double newMaxTime = playedList.getMaxTime();
        Integer oneWon = playedList.getOneWon();
        Integer twoWon = playedList.getTwoWon();
        Integer threeWon = playedList.getThreeWon();
        Integer fourWon = playedList.getFourWon();
        Integer fiveWon = playedList.getFiveWon();
        Integer sixWon = playedList.getSixWon();
        Integer sevenWon = playedList.getSevenWon();
        Integer eightWon = playedList.getEightWon();
        Integer nineWon = playedList.getNineWon();
        Integer tenWon = playedList.getTenWon();
        String hardMode = Opening.hardMode.toString();

        // If won, add to current streak and won amount
        if (ifWon) {
            numberWon = playedList.getAmountWon() + 1;
            newStreak +=1;
        }
        // If lost set streat to 0
        else {
            newStreak = 0;
        }

        // If the current streak is greater than the max streak update max
        if (newMaxStreak < newStreak){
            newMaxStreak = newStreak;
        }

        //If time is higher than max, or lower than min, update times
        if (newMaxTime < time){
            newMaxTime = time;
        }
        if (newMinTime > time){
            newMinTime = time;
        }

        // Update the number of trys column
        switch (numbOfTrys) {
            case 1: oneWon += 1;
            break;
            case 2: twoWon += 1;
            break;
            case 3: threeWon += 1;
            break;
            case 4: fourWon += 1;
            break;
            case 5: fiveWon +=1;
            break;
            case 6: sixWon +=1;
            break;
            case 7: sevenWon +=1;
            break;
            case 8: eightWon +=1;
            break;
            case 9: nineWon +=1;
            break;
            case 10: tenWon +=1;
            break;
        }

        // on below line we are creating a variable for our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values along with its key and value pair.
        values.put(player_COL, player);
        values.put(played_COL, numberPlayed);
        values.put(won_COL, numberWon);
        values.put(curr_Streak_COL, newStreak);
        values.put(max_Streak_COL, newMaxStreak);
        values.put(min_Time_COL, newMinTime);
        values.put(max_Time_COL, newMaxTime);
        values.put(numbOfLetters_COL, numbOfLetters);
        values.put(oneWon_COL, oneWon);
        values.put(twoWon_COL, twoWon);
        values.put(threeWon_COL, threeWon);
        values.put(fourWon_COL, fourWon);
        values.put(fiveWon_COL, fiveWon);
        values.put(sixWon_COL, sixWon);
        values.put(sevenWon_COL, sevenWon);
        values.put(eightWon_COL, eightWon);
        values.put(nineWon_COL, nineWon);
        values.put(tenWon_COL, tenWon);
        values.put(hard_COL, hardMode);

        // after adding all values we are passing content values to our table.
        String[] args = {player, String.valueOf(numbOfLetters)};
        db.update(TABLE_NAME_PLAYED_GAME, values, player_COL + "=? AND " + numbOfLetters_COL + "=?", args);

        //TODO Debug display
        //displayArchivePlayed();

        // at last we are closing our database after adding database.
        db.close();
    }

    // Add a new player to the player database
    public void newPlayer(String player){
        // on below line we are creating a variable for our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // For each of 4 - 10 word games
        for (int i = 4; i < 10; i++){
            // on below line we are creating a variable for content values.
            ContentValues values = new ContentValues();

            // on below line we are passing all values along with its key and value pair.
            values.put(player_COL, player);
            values.put(played_COL, 0);
            values.put(won_COL, 0);
            values.put(curr_Streak_COL, 0);
            values.put(max_Streak_COL, 0);
            values.put(min_Time_COL, Double.POSITIVE_INFINITY);
            values.put(max_Time_COL, 0.0);
            values.put(numbOfLetters_COL, i);
            values.put(oneWon_COL, 0);
            values.put(twoWon_COL, 0);
            values.put(threeWon_COL, 0);
            values.put(fourWon_COL, 0);
            values.put(fiveWon_COL, 0);
            values.put(sixWon_COL, 0);
            values.put(sevenWon_COL, 0);
            values.put(eightWon_COL, 0);
            values.put(nineWon_COL, 0);
            values.put(tenWon_COL, 0);
            values.put(hard_COL, "false");

            // after adding all values we are passing content values to our table.
            db.insert(TABLE_NAME_PLAYED_GAME, null, values);
        }

        // at last we are closing our database after adding database.
        db.close();
    }



    // Read the information from the played games database
    public ArrayList<PlayedGameModal> readPlayed(){
        //Create the db for reading
        SQLiteDatabase db = this.getReadableDatabase();

        //Cursor query
        Cursor cursorArchive = db.rawQuery("SELECT * FROM " + TABLE_NAME_PLAYED_GAME, null);

        //Set up column numbers
        int player = 0;
        int played = 1;
        int amountWon = 2;
        int currentStreak = 3;
        int maxStreak = 4;
        int minTime = 5;
        int maxTime = 6;
        int numbLetters = 7;
        int oneWon = 8;
        int twoWon = 9;
        int threeWon = 10;
        int fourWon = 11;
        int fiveWon = 12;
        int sixWon = 13;
        int sevenWon = 14;
        int eightWon = 15;
        int nineWon = 16;
        int tenWon = 17;

        //Create new array list to hold query
        ArrayList<PlayedGameModal> playedArrayList = new ArrayList<>();

        //Move to first position and get all infomration into array list
        if (cursorArchive.moveToFirst()) {
            do {
                playedArrayList.add(new PlayedGameModal(cursorArchive.getString(player),
                        cursorArchive.getInt(played), cursorArchive.getInt(amountWon),
                        cursorArchive.getInt(currentStreak), cursorArchive.getInt(maxStreak),
                        cursorArchive.getDouble(minTime), cursorArchive.getDouble(maxTime),
                        cursorArchive.getInt(numbLetters), cursorArchive.getInt(oneWon),
                        cursorArchive.getInt(twoWon), cursorArchive.getInt(threeWon),
                        cursorArchive.getInt(fourWon), cursorArchive.getInt(fiveWon),
                        cursorArchive.getInt(sixWon), cursorArchive.getInt(sevenWon),
                        cursorArchive.getInt(eightWon), cursorArchive.getInt(nineWon),
                        cursorArchive.getInt(tenWon), cursorArchive.getString(18)));

            } while (cursorArchive.moveToNext());
        }

        //Close cursor
        cursorArchive.close();
        db.close();
        return playedArrayList;
    }


    //Get a list of players
    public String[] getListOfPlayers(){

        //Create the db for reading
        SQLiteDatabase db = this.getReadableDatabase();

        //Cursor query
        Cursor cursorArchive = db.rawQuery("SELECT DISTINCT " + player_COL + " FROM " + TABLE_NAME_PLAYED_GAME, null);

        //Set column number for getting player from cursor
        int player = 0;

        //Create new array list to hold query
        String[] thePlayers = new String[cursorArchive.getCount() + 2];

        //Set the blank player for the start of the list
        thePlayers[0] = "     ";

        //Index for the list of players
        int i = 1;

        //Move to first position
        if (cursorArchive.moveToFirst()) {
            do {
                //add player to the list of players and update index
                thePlayers[i] = cursorArchive.getString(player);
                i++;

            } while (cursorArchive.moveToNext());
        }

        cursorArchive.close();
        db.close();

        //Add New Player to the end of the list
        thePlayers[i] = "New Player";

        return thePlayers;
    }

    // Read played games database for stats
    public PlayedGameModal readPlayedForPlayer(String player, Integer theLetters ){
        //Create the db for reading
        SQLiteDatabase db = this.getReadableDatabase();

        //Set args for the player and the amount of letters for the game
        String[] args = {player, String.valueOf(theLetters)};

        //Cursor query
        Cursor cursorArchive = db.rawQuery("SELECT * FROM " + TABLE_NAME_PLAYED_GAME + " WHERE " + player_COL + "=? AND " +
                numbOfLetters_COL + "=?", args);

        //Set column numbers
        int thisPlayer = 0;
        int played = 1;
        int amountWon = 2;
        int currentStreak = 3;
        int maxStreak = 4;
        int minTime = 5;
        int maxTime = 6;
        int numbLetters = 7;
        int oneWon = 8;
        int twoWon = 9;
        int threeWon = 10;
        int fourWon = 11;
        int fiveWon = 12;
        int sixWon = 13;
        int sevenWon = 14;
        int eightWon = 15;
        int nineWon = 16;
        int tenWon = 17;
        int hardMode = 18;

        //Move to first - even though there is just one line this is needed
        cursorArchive.moveToFirst();

        //Create new array list to hold query
        PlayedGameModal thisPlayedGameModal = new PlayedGameModal(cursorArchive.getString(thisPlayer),
                        cursorArchive.getInt(played), cursorArchive.getInt(amountWon),
                        cursorArchive.getInt(currentStreak), cursorArchive.getInt(maxStreak),
                        cursorArchive.getDouble(minTime), cursorArchive.getDouble(maxTime),
                        cursorArchive.getInt(numbLetters), cursorArchive.getInt(oneWon),
                        cursorArchive.getInt(twoWon), cursorArchive.getInt(threeWon),
                        cursorArchive.getInt(fourWon), cursorArchive.getInt(fiveWon),
                        cursorArchive.getInt(sixWon), cursorArchive.getInt(sevenWon),
                        cursorArchive.getInt(eightWon), cursorArchive.getInt(nineWon),
                        cursorArchive.getInt(tenWon), cursorArchive.getString(hardMode));

        cursorArchive.close();
        db.close();
        return thisPlayedGameModal;
    }

    //Check if the player is in hard mode - pulled from Opening
    public Boolean isThisHardMode (PlayedGameModal thisPlayedGameModal){
        return thisPlayedGameModal.getHardMode().equals("true");
    }

    public void setPlayerHardMode (PlayedGameModal playedList, Boolean hard){

        //Set new hardmode
        String hardMode = hard.toString();

        //Copy all other data
        String player = playedList.getPlayer();
        Integer numberPlayed = playedList.getPlayed();
        Integer numberWon = playedList.getAmountWon();
        Integer newStreak = playedList.getCurrentStreak();
        Integer newMaxStreak = playedList.getMaxStreak();
        double newMinTime = playedList.getMinTime();
        double newMaxTime = playedList.getMaxTime();
        Integer oneWon = playedList.getOneWon();
        Integer twoWon = playedList.getTwoWon();
        Integer threeWon = playedList.getThreeWon();
        Integer fourWon = playedList.getFourWon();
        Integer fiveWon = playedList.getFiveWon();
        Integer sixWon = playedList.getSixWon();
        Integer sevenWon = playedList.getSevenWon();
        Integer eightWon = playedList.getEightWon();
        Integer nineWon = playedList.getNineWon();
        Integer tenWon = playedList.getTenWon();
        Integer numbOfLetters = playedList.getLetters();

        // on below line we are creating a variable for our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a ariable for content values.
        ContentValues values = new ContentValues();

        // Place all the same data back, with updated hard mode
        values.put(player_COL, player);
        values.put(played_COL, numberPlayed);
        values.put(won_COL, numberWon);
        values.put(curr_Streak_COL, newStreak);
        values.put(max_Streak_COL, newMaxStreak);
        values.put(min_Time_COL, newMinTime);
        values.put(max_Time_COL, newMaxTime);
        values.put(numbOfLetters_COL, numbOfLetters);
        values.put(oneWon_COL, oneWon);
        values.put(twoWon_COL, twoWon);
        values.put(threeWon_COL, threeWon);
        values.put(fourWon_COL, fourWon);
        values.put(fiveWon_COL, fiveWon);
        values.put(sixWon_COL, sixWon);
        values.put(sevenWon_COL, sevenWon);
        values.put(eightWon_COL, eightWon);
        values.put(nineWon_COL, nineWon);
        values.put(tenWon_COL, tenWon);
        values.put(hard_COL, hardMode);

        // after adding all values we are passing content values to our table for the player and number of letters.
        String[] args = {player, String.valueOf(numbOfLetters)};
        db.update(TABLE_NAME_PLAYED_GAME, values, player_COL + "=? AND " + numbOfLetters_COL + "=?", args);

        //TODO for debug
        //displayArchivePlayed();

        db.close();
    }

    //Read the archive of words
    public ArrayList<ArchiveModal> readArchive() {

        //Create the db for reading
        SQLiteDatabase db = this.getReadableDatabase();

        //Cursor query
        Cursor cursorArchive = db.rawQuery("SELECT * FROM " + TABLE_NAME_WORD, null);

        //Create new array list to hold query
        ArrayList<ArchiveModal> archiveModalArrayList = new ArrayList<>();

        //Move to first position
        if (cursorArchive.moveToFirst()) {
            do {
                archiveModalArrayList.add(new ArchiveModal(cursorArchive.getString(0), cursorArchive.getString(1), cursorArchive.getString(2),
                        cursorArchive.getDouble(3), cursorArchive.getInt(4), cursorArchive.getInt(5)));
            } while (cursorArchive.moveToNext());
        }

        cursorArchive.close();
        db.close();
        return archiveModalArrayList;
    }

    //Display the archive for debug
    public void displayArchive(){
        ArrayList<ArchiveModal> archiveModalArrayList = readArchive();

        System.out.println("=========================START==============================================");
        System.out.println("Player | Answer | Correct | Time | Trys | Letters");

        for (int i = 0; i < archiveModalArrayList.size(); i++){
            ArchiveModal item = archiveModalArrayList.get(i);
            System.out.println("-----------------------------------------------------------------------------------");
            System.out.println(item.getPlayer() + " | " + item.getTheAnswer() + " | " + item.getIfCorrect() + " | " + String.valueOf(item.getFinishedTime())
            + " | " + item.getAmountOftrys() + " | " + String.valueOf(item.getAmountOfLetters()));

        }
        System.out.println("===========================================END========================================");
    }

    //Display the archive for debug
    public void displayArchivePlayed(){
        ArrayList<PlayedGameModal> playedGameModalArrayList = readPlayed();

        System.out.println("=========================START==============================================");
        System.out.println("Player | Played | Won | Currnet Streat | MAx streat | Min time | max time | Letters | tenwon");

        for (int i = 0; i < playedGameModalArrayList.size(); i++){
            PlayedGameModal item = playedGameModalArrayList.get(i);
            System.out.println("-----------------------------------------------------------------------------------");
            System.out.println(item.getPlayer() + " | " + item.getPlayed() + " | " + item.getAmountWon()
                    + " | " + item.getCurrentStreak() + " | " + String.valueOf(item.getMaxStreak()) + " | " + String.valueOf(item.getMinTime()) + " | " + String.valueOf(item.getMaxTime())
            + " | " + item.getLetters() + " | " + item.getTenWon());

        }
        System.out.println("===========================================END========================================");
    }

    //Delete a word from the statitics table
    public void deleteWord(String theAnswer){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME_WORD, answer_COL+"=?", new String[]{theAnswer});
        db.close();
    }

    //Delete all the words played for a player
    public void deleteAllAnswersForPlayer(String player){
        ArrayList<ArchiveModal> archiveModalArrayList = readArchive();
        SQLiteDatabase db = this.getWritableDatabase();
        for (int i = 0; i < archiveModalArrayList.size(); i++){
            ArchiveModal item = archiveModalArrayList.get(i);
            db.delete(TABLE_NAME_WORD, answer_COL+"=? AND " + player_COL+"=?", new String[]{item.getTheAnswer(), player});

        }
        db.close();
    }


    // Reset all words to unused
    public void resetIfAllUsed(Integer startingWords, String player){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] args = {"true"};
        //Cursor query
        try (Cursor cursor = db.query(TABLE_NAME_WORD, null, "Correct=?", args, null, null, null)) {

            if (cursor.getCount() == startingWords) {
                deleteAllAnswersForPlayer(player);
                cursor.close();
            }
        }

        db.close();
    }

    //Check if a words has been used and was answered correctly
    public boolean checkIfUsed(String possibleWord){

        SQLiteDatabase db = this.getReadableDatabase();
        String[] args = {possibleWord, "true"};
        //Cursor query
        try (Cursor cursor = db.query(TABLE_NAME_WORD, null, "Answer=? AND Correct=?", args, null, null, null)) {

            return cursor.getCount() > 0;
        }

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_WORD);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_PLAYED_GAME);
        onCreate(db);
        db.close();
    }

}