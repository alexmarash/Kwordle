package com.example.kwordle;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StatsHandler extends SQLiteOpenHelper {

    private static final String DB_NAME = "statsdb";

    private static final int DB_VERSION = 1;

    private static final String TABLE_NAME = "PlayerStats";

    private static final String player_COL = "player";

    private static final String numberOfLetters_Col = "numOfLetters";

    private static final String played_COL = "played";

    private static final String won_COL = "won";

    private static final String curr_Streak_COL = "currentStreak";

    private static final String max_Streak_COL = "maxStreak";

    private static final String min_Time_COL = "minTime";

    private static final String max_Time_COL = "maxTime";

    public StatsHandler(Context context) {super(context, DB_NAME, null, DB_VERSION);}

    @Override
    public void onCreate(SQLiteDatabase db){SQLiteDatabase db) {
        String query = "CREATE TABLE" + TABLE_NAME + " ("
                + player_COL + " TEXT, "
                + numberOfLetters_Col + " INTEGER, "
                + played_COL + " INTERGER, "
                + won_COL + " INTEGER, "
                + curr_Streak_COL + " INTEGER,"
                + max_Streak_COL + " INTEGER, "
                + min_Time_COL + " REAL, "
                + max_Time_COL + " REAL)";



        db.execSQL(query);
    }



    }

}
