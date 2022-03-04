package com.example.kwordle;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Archive {

    //private static final int MODE_PRIVATE = ;
    SQLiteDatabase archives;// = SQLiteDatabase.openOrCreateDatabase("kwordleArchive",MODE_PRIVATE, null );
    //public static archives.openOrCreateDatabase("kwordleArchive", MODE_PRIVATE, null)

    Archive(Context context){
        //this.archives.openOrCreateDatabase("kwordleArchive", context.MODE_PRIVATE, null);
        this.archives = context.openOrCreateDatabase("kwordleArchive", context.MODE_PRIVATE, null);
        this.archives.execSQL("CREATE TABLE IF NOT EXISTS Statistics(Answer VARCHAR, Correct VARCHAR, Time VARCHAR, Trys VARCHAR, Letters VARCHAR);");

    }

    //TODO need to check if the archive is already populated and if so update the entry instead of adding it
    public void updateArchive(String correct, float Time, int currentTry, int letters, String theAnswer) throws SQLException {

        String finishedTime = String.valueOf((Math.round(System.currentTimeMillis() - Time))/60000);
        String finishedTry = String.valueOf(currentTry);
        String numberOfLetters = String.valueOf(letters);



        //TODO this has not been checked
        if (checkIfAnsweredBefore(theAnswer)){

            //System.out.println("=========================HERE2================================");

            String SQLUpdate = "UPDATE Statistics SET Answer = " + theAnswer + "Correct = ? , " + "Time = ?";
            String url = archives.getPath();
            //Connection conn = null;
            try {
                Connection conn = DriverManager.getConnection(url);
                PreparedStatement pstmt = conn.prepareStatement(SQLUpdate);
                pstmt.setString(1, "true");
                pstmt.setString(2, finishedTime);
                pstmt.executeUpdate();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            //SQLInput = "UPDATE INTO Statistics(Answer, Correct, Time, Trys) VALUES(?, ?, ?, ?)";
        }

        else{
            String SQLInput = "INSERT INTO Statistics(Answer, Correct, Time, Trys, Letters) VALUES(?, ?, ?, ?, ?)";
            String[] args = {theAnswer, correct, finishedTime, finishedTry, numberOfLetters};
            archives.execSQL(SQLInput, args);

            //System.out.println("=========================HERE================================");
        }

    }

    //TODO update archive, this needs to move to the database area
    //Check if word is already in the database
    public Boolean checkIfAnsweredBefore(String possibleWord){

        String[] args = {possibleWord, "true"};
        Cursor cursor = archives.query("Statistics", null, "Answer=? AND Correct=?", args, null, null, null);
        //System.out.println("=========================cursor count=" + cursor.getCount());
        // if cursor count is 0 then it is not in teh database
        return cursor.getCount() > 0;
    }

    public void resetAnswers() throws SQLException {



        System.out.println("========================before===============================");

        getListOfAnswers();

        /*

        this.printSqlTable();

        String SQLUpdate = "UPDATE Statistics Correct = ?";
        String url = archives.getPath();



        //Connection conn = null;
        try {
            Connection conn = DriverManager.getConnection(url);
            PreparedStatement pstmt = conn.prepareStatement(SQLUpdate);
            pstmt.setString(1, "false");
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        */

        System.out.println("========================after===============================");
        //this.printSqlTable();


    }

    public List<Integer> getListOfAnswers(){
        List<Integer> results = new ArrayList<Integer>();
        List<String> answers = new ArrayList<String>();

        String [] columns = {"Answer"};

        Cursor cursor = archives.query("Statistics", columns, null, null, null, null, null);
       // for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext())
       //     {
                //Integer rowId = cursor.getInt(iAnswer);
                //results.add(rowId);
       //         answers.add(rowId);

        //    }

        System.out.println(cursor);

        cursor.close();
        //archives.close();
        return  results;
    }



    public void printSqlTable() throws SQLException {
        //String url = "jdbc:sqlite:" + archives.getPath() +".db";

        String url = "jdbc:sqlite:kwordleArchive.db";
        System.out.println("=============url " + url);

        //DriverManager.registerDriver(new org.sqlite.JDBC());
        //String dbURL = "jdbc:sqlite:product.db";
        //Connection conn = null;
        Connection conn = DriverManager.getConnection(url);
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT Answer, Correct FROM Statistics");
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnsNumber = rsmd.getColumnCount();

        while (rs.next()) {
            for(int i = 1 ; i <= columnsNumber; i++){

                System.out.print(rs.getString(i) + " "); //Print one element of a row

            }

            System.out.println();//Move to the next line to print the next row.

        }
    }



}
