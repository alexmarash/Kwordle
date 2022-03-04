package com.example.kwordle;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


// https://www.geeksforgeeks.org/how-to-read-data-from-sqlite-database-in-android/

public class DBHandler extends SQLiteOpenHelper {

    // creating a constant variables for our database.
    // below variable is for our database name.
    private static final String DB_NAME = "kwordledb";

    // below int is our database version
    private static final int DB_VERSION = 1;

    // below variable is for our table name.
    private static final String TABLE_NAME = "Statistics";

    // below variable is for our Answer column.
    private static final String answer_COL = "Answer";

    // below variable is for our Time column
    private static final String time_COL = "Time";

    // below variable is for our Trys column
    private static final String trys_COL = "Trys";

    // below variable is for our how many letters column
    private static final String letters_COL = "Letters";

    // below variable is for our if correct column
    private static final String correct_COL = "Correct";



    // creating a constructor for our database handler.
    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        // on below line we are creating
        // an sqlite query and we are
        // setting our column names
        // along with their data types.
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + answer_COL + " TEXT, "
                + correct_COL + " TEXT, "
                + time_COL + " REAL, "
                + trys_COL + " INTEGER, "
                + letters_COL + " INTEGER)";

        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query);
    }

    // this method is use to add new course to our sqlite database.
    public void addWord(String theAnswer, String ifCorrect, double time, Integer trys, Integer letters) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(answer_COL, theAnswer);
        values.put(correct_COL, ifCorrect);
        values.put(time_COL, time);
        values.put(trys_COL, trys);
        values.put(letters_COL, letters);

        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_NAME, null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
    }


    public ArrayList<ArchiveModal> readArchive() {

        //Create the db for reading
        SQLiteDatabase db = this.getReadableDatabase();

        //Cursor query
        Cursor cursorArchive = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        //Create new array list to hold query
        ArrayList<ArchiveModal> archiveModalArrayList = new ArrayList<>();

        //Move to first position
        if (cursorArchive.moveToFirst()) {
            do {
                archiveModalArrayList.add(new ArchiveModal(cursorArchive.getString(1), cursorArchive.getString(2),
                        cursorArchive.getDouble(3), cursorArchive.getInt(4), cursorArchive.getInt(5)));
            } while (cursorArchive.moveToNext());
        }

        cursorArchive.close();
        return archiveModalArrayList;
    }


    public void displayArchive(){
        ArrayList<ArchiveModal> archiveModalArrayList = readArchive();

        System.out.println("=========================START==============================================");
        System.out.println("Answer | Correct | Time | Trys | Letters");

        for (int i = 0; i < archiveModalArrayList.size(); i++){
            ArchiveModal item = archiveModalArrayList.get(i);
            System.out.println("-----------------------------------------------------------------------------------");
            System.out.println(item.getTheAnswer() + " | " + item.getIfCorrect() + " | " + String.valueOf(item.getFinishedTime())
            + " | " + String.valueOf(item.getAmountOftrys()) + " | " + String.valueOf(item.getAmountOfLetters()));

        }
        System.out.println("===========================================END========================================");
    }


    public void deleteWord(String theAnswer){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, answer_COL+"=?", new String[]{theAnswer});
        db.close();
    }

    public void deleteAllAnswers(){
        ArrayList<ArchiveModal> archiveModalArrayList = readArchive();
        SQLiteDatabase db = this.getWritableDatabase();
        for (int i = 0; i < archiveModalArrayList.size(); i++){
            ArchiveModal item = archiveModalArrayList.get(i);
            db.delete(TABLE_NAME, answer_COL+"=?", new String[]{item.getTheAnswer()});

        }
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

}