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
    // below variable is for our database name.
    private static final String DB_NAME = "kwordledb";

    // below int is our database version
    private static final int DB_VERSION = 1;


    // Table for the game word statistics

    // below variable is for our table name.
    private static final String TABLE_NAME_WORD = "WORDSTATS";

    // below variable is for our Answer column.
    private static final String answer_COL = "Answer";

    //private static final String player_COL = "player";

    // below variable is for our Time column
    private static final String time_COL = "Time";

    // below variable is for our Trys column
    private static final String trys_COL = "Trys";

    // below variable is for our how many letters column
    //private static final String letters_COL = "Letters";

    // below variable is for our if correct column
    private static final String correct_COL = "Correct";


    private static final String TABLE_NAME_PLAYED = "PLAYERSTATS";

    //private static final String player_COL = "player";

    private static final String numberOfLetters_Col = "numOfLetters";

    private static final String played_COL = "played";

    private static final String won_COL = "won";

    private static final String curr_Streak_COL = "currentStreak";

    private static final String max_Streak_COL = "maxStreak";

    private static final String min_Time_COL = "minTime";

    private static final String max_Time_COL = "maxTime";


    // Shared columns
    // Who is the player
    private static final String player_COL = "player";

    // below variable is for our how many letters column
    private static final String letters_COL = "Letters";

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

        String queryWORD = "CREATE TABLE " + TABLE_NAME_WORD + " ("
                + player_COL + " TEXT, "
                + answer_COL + " TEXT, "
                + correct_COL + " TEXT, "
                + time_COL + " REAL, "
                + trys_COL + " INTEGER, "
                + letters_COL + " INTEGER)";

        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(queryWORD);

        String queryPLAYER = "CREATE TABLE " + TABLE_NAME_PLAYED + " ("
                + player_COL + " TEXT, "
                + letters_COL + " INTEGER, "
                + played_COL + " INTERGER, "
                + won_COL + " INTEGER, "
                + curr_Streak_COL + " INTEGER,"
                + max_Streak_COL + " INTEGER, "
                + min_Time_COL + " REAL, "
                + max_Time_COL + " REAL)";

        // method to execute above sql query
        db.execSQL(queryPLAYER);
    }

    // this method is use to add new course to our sqlite database.
    public void addWord(String player, String theAnswer, String ifCorrect, double time, Integer trys, Integer letters) {

        if (checkIfUsed(theAnswer)){
            deleteWord(theAnswer);
        }


        //System.out.println(theAnswer + " " + ifCorrect + " " + time + " " + trys + " " + letters);

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(player_COL, player);
        values.put(answer_COL, theAnswer);
        values.put(correct_COL, ifCorrect);
        values.put(time_COL, time);
        values.put(trys_COL, trys);
        values.put(letters_COL, letters);

        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_NAME_WORD, null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
    }


    public void addGame(String player, Integer numbOfLetters, Boolean ifWon, float time){


        Integer theplayer = 0;
        Integer theletters = 1;
        Integer played = 2;
        Integer won = 3;
        Integer currentStreak = 4;
        Integer maxStreak = 5;
        Integer minTime = 6;
        Integer maxTime = 7;


        PlayedModal playedList = readPlayedForPlayer(player);

        Integer numberPlayed = playedList.getPlayed() + 1;
        Integer numberWon = playedList.getAmountWon();
        Integer newStreak = playedList.getCurrentStreak();
        Integer newMaxStreak = playedList.getMaxStreak();
        double newMinTime = playedList.getMinTime();
        double newMaxTime = playedList.getMaxTime();


        if (ifWon) {
            numberWon = playedList.getAmountWon() + 1;
            newStreak = playedList.getCurrentStreak() + 1;
        }

        if (newMaxStreak < newStreak){
            newMaxStreak = newStreak;
        }

        if (newMaxTime < time){
            newMaxTime = time;
        }

        if (newMinTime > time){
            newMinTime = time;
        }



        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(player_COL, player);
        values.put(letters_COL, numbOfLetters);
        values.put(played_COL, numberPlayed);
        values.put(won_COL, numberWon);
        values.put(curr_Streak_COL, newStreak);
        values.put(max_Streak_COL, newMaxStreak);
        values.put(min_Time_COL, newMinTime);
        values.put(max_Time_COL, newMaxTime);

        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_NAME_PLAYED, null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
    }


    public void newPlayer(String player){


        Integer theplayer = 0;
        Integer theletters = 1;
        Integer played = 2;
        Integer won = 3;
        Integer currentStreak = 4;
        Integer maxStreak = 5;
        Integer minTime = 6;
        Integer maxTime = 7;


        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();


        for (int i = 4; i < 10; i++){

            // on below line we are creating a
            // variable for content values.
            ContentValues values = new ContentValues();

            // on below line we are passing all values
            // along with its key and value pair.
            values.put(player_COL, player);
            values.put(letters_COL, i);
            values.put(played_COL, 0);
            values.put(won_COL, 0);
            values.put(curr_Streak_COL, 0);
            values.put(max_Streak_COL, 0);
            values.put(min_Time_COL, 0.0);
            values.put(max_Time_COL, 0.0);

            // after adding all values we are passing
            // content values to our table.
            db.insert(TABLE_NAME_PLAYED, null, values);

        }

        // at last we are closing our
        // database after adding database.
        db.close();
    }




    public ArrayList<PlayedModal> readPlayed(){
        //Create the db for reading
        SQLiteDatabase db = this.getReadableDatabase();

        //Cursor query
        Cursor cursorArchive = db.rawQuery("SELECT * FROM " + TABLE_NAME_PLAYED, null);

        Integer player = 0;
        Integer letters = 1;
        Integer played = 2;
        Integer amountWon = 3;
        Integer currentStreak = 4;
        Integer maxStreak = 5;
        Integer minTime = 6;
        Integer maxTime = 7;

        //Create new array list to hold query
        ArrayList<PlayedModal> playedArrayList = new ArrayList<>();

        //Move to first position
        if (cursorArchive.moveToFirst()) {
            do {
                playedArrayList.add(new PlayedModal(cursorArchive.getString(player), cursorArchive.getInt(letters),
                        cursorArchive.getInt(played), cursorArchive.getInt(amountWon),
                        cursorArchive.getInt(currentStreak), cursorArchive.getInt(maxStreak),
                        cursorArchive.getDouble(minTime), cursorArchive.getDouble(maxTime)));

            } while (cursorArchive.moveToNext());
        }

        cursorArchive.close();
        return playedArrayList;
    }


    public String[] getListOfPlayers(){

        //Create the db for reading
        SQLiteDatabase db = this.getReadableDatabase();

        //Cursor query
        Cursor cursorArchive = db.rawQuery("SELECT DISTINCT " + player_COL + " FROM " + TABLE_NAME_PLAYED, null);

        Integer player = 0;
        Integer letters = 1;
        Integer played = 2;
        Integer amountWon = 3;
        Integer currentStreak = 4;
        Integer maxStreak = 5;
        Integer minTime = 6;
        Integer maxTime = 7;

        //Create new array list to hold query
        String[] thePlayers = new String[cursorArchive.getCount()];

        //Move to first position

        Integer i = 0;
        if (cursorArchive.moveToFirst()) {
            do {
                thePlayers[i] = cursorArchive.getString(player);
                i++;

            } while (cursorArchive.moveToNext());
        }

        cursorArchive.close();
        return thePlayers;
    }

    public int getCountOfPlayers(){
        //Create the db for reading
        SQLiteDatabase db = this.getReadableDatabase();

        //Cursor query
        Cursor cursorArchive = db.rawQuery("SELECT * FROM " + TABLE_NAME_PLAYED, null);

        return cursorArchive.getCount();
    }


    public PlayedModal readPlayedForPlayer(String player){
        //Create the db for reading
        SQLiteDatabase db = this.getReadableDatabase();

        String[] args = {player};
        //Cursor query
        Cursor cursorArchive = db.rawQuery("SELECT * FROM " + TABLE_NAME_PLAYED + " WHERE ? ", args);

        //Integer player = 0;
        Integer letters = 1;
        Integer played = 2;
        Integer amountWon = 3;
        Integer currentStreak = 4;
        Integer maxStreak = 5;
        Integer minTime = 6;
        Integer maxTime = 7;

        //Create new array list to hold query
        //ArrayList<PlayedModal> playedArrayList = new ArrayList<>();

        PlayedModal thisPlayerModal = new PlayedModal(player, cursorArchive.getInt(letters),
                cursorArchive.getInt(played), cursorArchive.getInt(amountWon),
                cursorArchive.getInt(currentStreak), cursorArchive.getInt(maxStreak),
                cursorArchive.getDouble(minTime), cursorArchive.getDouble(maxTime));

        /*
        //Move to first position
        if (cursorArchive.moveToFirst()) {
            do {
                playedArrayList.add(new PlayedModal(player, cursorArchive.getInt(letters),
                        cursorArchive.getInt(played), cursorArchive.getInt(amountWon),
                        cursorArchive.getInt(currentStreak), cursorArchive.getInt(maxStreak),
                        cursorArchive.getDouble(minTime), cursorArchive.getDouble(maxTime)));

            } while (cursorArchive.moveToNext());
        }
        */
        cursorArchive.close();

        return thisPlayerModal;
        //return playedArrayList;
    }


    public ArrayList<ArchiveModal> readArchiveForPlayer(String player) {

        //Create the db for reading
        SQLiteDatabase db = this.getReadableDatabase();

        //Cursor query
        Cursor cursorArchive = db.rawQuery("SELECT * FROM " + TABLE_NAME_WORD + " WHERE " + player + "=?", null);

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
        return archiveModalArrayList;
    }


    public ArrayList<ArchiveModal> readArchiveofPlayer(String player) {

        //Create the db for reading
        SQLiteDatabase db = this.getReadableDatabase();

        //Cursor query
        Cursor cursorArchive = db.rawQuery("SELECT * FROM " + TABLE_NAME_WORD + " WHERE " + player + "=?", null);

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
        return archiveModalArrayList;
    }


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
        return archiveModalArrayList;
    }


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


    public void deleteWord(String theAnswer){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME_WORD, answer_COL+"=?", new String[]{theAnswer});
        db.close();
    }

    public void deleteAllAnswersForPlayer(String player){
        ArrayList<ArchiveModal> archiveModalArrayList = readArchive();
        SQLiteDatabase db = this.getWritableDatabase();
        for (int i = 0; i < archiveModalArrayList.size(); i++){
            ArchiveModal item = archiveModalArrayList.get(i);
            db.delete(TABLE_NAME_WORD, answer_COL+"=? AND " + player_COL+"=?", new String[]{item.getTheAnswer(), player});

        }
        db.close();
    }

    public void deleteAll(){
        ArrayList<ArchiveModal> archiveModalArrayList = readArchive();
        SQLiteDatabase db = this.getWritableDatabase();
        for (int i = 0; i < archiveModalArrayList.size(); i++){
            ArchiveModal item = archiveModalArrayList.get(i);
            db.delete(TABLE_NAME_WORD, answer_COL+"=?", new String[]{item.getTheAnswer()});

        }
        db.close();
    }


    public boolean checkIfUsed(String possibleWord){

        SQLiteDatabase db = this.getReadableDatabase();
        String[] args = {possibleWord, "true"};
        //Cursor query
        Cursor cursor = db.query(TABLE_NAME_WORD, null, "Answer=? AND Correct=?", args, null, null, null);

        return cursor.getCount() > 0;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_WORD);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_PLAYED);
        onCreate(db);
    }

}