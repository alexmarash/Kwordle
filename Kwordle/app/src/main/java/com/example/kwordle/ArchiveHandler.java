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

    private static final String TABLE_NAME_PLAYED_GAME = "PLAYEDGAMESTATS";

    //private static final String player_COL = "player";

    //private static final String numberOfLetters_COL = "numOfLetters";

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


    // Shared columns
    // Who is the player
    private static final String player_COL = "player";

    // below variable is for our how many letters column
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
                + tenWon_COL + " INTEGER)";

        /*
        String queryPLAYER = "CREATE TABLE " + TABLE_NAME_PLAYED + " ("
                + player_COL + " TEXT, "
                + "asdf" + " INTEGER, "
                + played_COL + " INTEGER, "
                + won_COL + " INTEGER, "
                + curr_Streak_COL + " INTEGER,"
                + max_Streak_COL + " INTEGER, "
                + min_Time_COL + " REAL, "
                + max_Time_COL + " REAL)";
        */
        // method to execute above sql query
        db.execSQL(queryPLAYEDGAME);
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
        values.put(numbOfLetters_COL, letters);

        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_NAME_WORD, null, values);

        // at last we are closing our
        // database after adding database.





        db.close();
    }


    public void addGame(String player, Integer numbOfLetters, Boolean ifWon, double time, Integer numbOfTrys){

        /*
        Integer theplayer = 0;
        Integer theletters = 1;
        Integer played = 2;
        Integer won = 3;
        Integer currentStreak = 4;
        Integer maxStreak = 5;
        Integer minTime = 6;
        Integer maxTime = 7;
        */

        System.out.println("adding game " + player + " " + String.valueOf(numbOfLetters) + " " +
                String.valueOf(ifWon) + " " + String.valueOf(time) + " " + String.valueOf(numbOfTrys));


        PlayedGameModal playedList = readPlayedForPlayer(player, numbOfLetters);

        Integer numberPlayed = playedList.getPlayed() + 1;
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


        System.out.println("adding game 2 " + player + " " + String.valueOf(numbOfLetters) + " " +
                String.valueOf(ifWon) + " " + String.valueOf(time) + " " + String.valueOf(numbOfTrys));


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

        // after adding all values we are passing
        // content values to our table.
        //db.insert(TABLE_NAME_PLAYED, null, values);
        String[] args = {player, String.valueOf(numbOfLetters)};
        db.update(TABLE_NAME_PLAYED_GAME, values, player_COL + "=? AND " + numbOfLetters_COL + "=?", args);

        // at last we are closing our
        // database after adding database.

        displayArchivePlayed();

        db.close();
    }


    public void newPlayer(String player){

        /*
        Integer theplayer = 0;
        Integer theletters = 1;
        Integer played = 2;
        Integer won = 3;
        Integer currentStreak = 4;
        Integer maxStreak = 5;
        Integer minTime = 6;
        Integer maxTime = 7;
        */

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();


        /*
                        + player_COL + " TEXT, "
                + played_COL + " INTEGER, "
                + won_COL + " INTEGER, "
                + curr_Streak_COL + " INTEGER, "
                + max_Streak_COL + " INTEGER, "
                + min_Time_COL + " REAL, "
                + max_Time_COL + " REAL, "
                + numberOfLetters_COL + " INTEGER, "
                + oneWon_COL + " INTEGER, "
                + twoWon_COL + " INTEGER, "
                + threeWon_COL + " INTEGER, "
                + fourWon_COL + " INTEGER, "
                + fiveWon_COL + " INTEGER, "
                + sixWon_COL + " INTEGER, "
                + sevenWon_COL + " INTEGER, "
                + eightWon_COL + " INTEGER, "
                + nineWon_COL + " INTEGER, "
                + tenWon_COL + " INTEGER)";
         */

        for (Integer i = 4; i < 10; i++){

            // on below line we are creating a
            // variable for content values.
            ContentValues values = new ContentValues();

            // on below line we are passing all values
            // along with its key and value pair.
            values.put(player_COL, player);
            values.put(played_COL, 0);
            values.put(won_COL, 0);
            values.put(curr_Streak_COL, 0);
            values.put(max_Streak_COL, 0);
            values.put(min_Time_COL, 0.0);
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

            // after adding all values we are passing
            // content values to our table.
            db.insert(TABLE_NAME_PLAYED_GAME, null, values);

        }

        // at last we are closing our
        // database after adding database.
        db.close();
    }




    public ArrayList<PlayedGameModal> readPlayed(){
        //Create the db for reading
        SQLiteDatabase db = this.getReadableDatabase();

        //Cursor query
        Cursor cursorArchive = db.rawQuery("SELECT * FROM " + TABLE_NAME_PLAYED_GAME, null);

        Integer player = 0;
        Integer played = 1;
        Integer amountWon = 2;
        Integer currentStreak = 3;
        Integer maxStreak = 4;
        Integer minTime = 5;
        Integer maxTime = 6;
        Integer numbLetters = 7;
        Integer oneWon = 8;
        Integer twoWon = 9;
        Integer threeWon = 10;
        Integer fourWon = 11;
        Integer fiveWon = 12;
        Integer sixWon = 13;
        Integer sevenWon = 14;
        Integer eightWon = 15;
        Integer nineWon = 16;
        Integer tenWon = 17;


        //Create new array list to hold query
        ArrayList<PlayedGameModal> playedArrayList = new ArrayList<>();

        //Move to first position
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
                        cursorArchive.getInt(tenWon)));

            } while (cursorArchive.moveToNext());
        }

        cursorArchive.close();
        return playedArrayList;
    }


    public String[] getListOfPlayers(){

        //Create the db for reading
        SQLiteDatabase db = this.getReadableDatabase();

        //Cursor query
        Cursor cursorArchive = db.rawQuery("SELECT DISTINCT " + player_COL + " FROM " + TABLE_NAME_PLAYED_GAME, null);

        Integer player = 0;

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

    /*
    public int getCountOfPlayers(){
        //Create the db for reading
        SQLiteDatabase db = this.getReadableDatabase();

        //Cursor query
        Cursor cursorArchive = db.rawQuery("SELECT * FROM " + TABLE_NAME_PLAYED_GAME, null);

        return cursorArchive.getCount();
    }
     */


    public PlayedGameModal readPlayedForPlayer(String player, Integer theLetters ){
        //Create the db for reading
        SQLiteDatabase db = this.getReadableDatabase();

        String[] args = {player, String.valueOf(theLetters)};
        //Cursor query
        //Cursor cursorArchive = db.rawQuery("SELECT * FROM " + TABLE_NAME_PLAYED + " WHERE ? ", args);

        Cursor cursorArchive = db.rawQuery("SELECT * FROM " + TABLE_NAME_PLAYED_GAME + " WHERE " + player_COL + "=? AND " +
                numbOfLetters_COL + "=?", args);


        Integer thisplayer = 0;
        Integer played = 1;
        Integer amountWon = 2;
        Integer currentStreak = 3;
        Integer maxStreak = 4;
        Integer minTime = 5;
        Integer maxTime = 6;
        Integer numbLetters = 7;
        Integer oneWon = 8;
        Integer twoWon = 9;
        Integer threeWon = 10;
        Integer fourWon = 11;
        Integer fiveWon = 12;
        Integer sixWon = 13;
        Integer sevenWon = 14;
        Integer eightWon = 15;
        Integer nineWon = 16;
        Integer tenWon = 17;

        //System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + player + " " + " " +  theLetters + " " + cursorArchive.getCount());
        cursorArchive.moveToFirst();
        //System.out.println("========================" + cursorArchive.getString(0));
        //System.out.println("++++++++++++++++++++++++" + cursorArchive.getInt(1));



        //Create new array list to hold query
        PlayedGameModal thisPlayedGameModal = new PlayedGameModal(cursorArchive.getString(thisplayer),
                        cursorArchive.getInt(played), cursorArchive.getInt(amountWon),
                        cursorArchive.getInt(currentStreak), cursorArchive.getInt(maxStreak),
                        cursorArchive.getDouble(minTime), cursorArchive.getDouble(maxTime),
                        cursorArchive.getInt(numbLetters), cursorArchive.getInt(oneWon),
                        cursorArchive.getInt(twoWon), cursorArchive.getInt(threeWon),
                        cursorArchive.getInt(fourWon), cursorArchive.getInt(fiveWon),
                        cursorArchive.getInt(sixWon), cursorArchive.getInt(sevenWon),
                        cursorArchive.getInt(eightWon), cursorArchive.getInt(nineWon),
                        cursorArchive.getInt(tenWon));


        //System.out.println("===================coutn"+ cursorArchive.getCount());


        //displayArchivePlayed();

        //System.out.println("==============================" + player + cursorArchive.getInt(numbLetters));
        //System.out.println("==============================" + player + cursorArchive.getDouble(maxTime));

        //PlayedModal thisPlayerModal = new PlayedModal(cursorArchive.getString(thisplayer), cursorArchive.getInt(numbLetters),

        //        cursorArchive.getInt(played), cursorArchive.getInt(amountWon),
        //        cursorArchive.getInt(currentStreak), cursorArchive.getInt(maxStreak),
        //        cursorArchive.getDouble(minTime), cursorArchive.getDouble(maxTime));

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
        return thisPlayedGameModal;
        //return thisPlayerModal;
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
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_PLAYED_GAME);
        onCreate(db);
    }

}