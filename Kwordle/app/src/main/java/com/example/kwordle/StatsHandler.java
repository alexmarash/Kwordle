package com.example.kwordle;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StatsHandler extends S Dy  DQLiteOpenHelper {

    private static final String DB_NAME = "statsdb";

    private static final int DB_VERSION = 1;

    private static final String TABLE_NAME = "fiveLetters";

    private static final String played_COL = "played";

    private static final String won_COL = "won";

    private static final String curr_Streak_COL = "currentStreak";

    private static final String max_Streak_COL = "maxStreak";

    private static final String min_Time_COL = "minTime";

    private static final String max_Time_COL = "maxTime";

    public StatsHandler(Context context) {super(context, DB_NAME, null, DB_VERSION);}

    @Override
    public void onCreate(SQLiteDatabase db){



    }

}
