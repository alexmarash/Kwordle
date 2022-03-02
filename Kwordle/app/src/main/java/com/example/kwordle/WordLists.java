package com.example.kwordle;

import android.content.Context;
import android.content.res.AssetManager;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class WordLists {


    //public static Map<String, Boolean> wordList = new HashMap<>();
    //private static DBHandler dbHandler;
    //public static String[][] wordListArray = new String[20000][2];
    public static List<String> fiveWordListArray = new ArrayList<String>();
    //public static List<String> usedWords = new ArrayList<String>();
    public static List<String> fiveStartingWords = new ArrayList<String>();
    public static int numberOfLetters;
    //public AssetManager assetManager;
    //public Context fromContext;


    WordLists(Context context){
        //this.numberOfLetters = letters;
        //String wordListName = new String();
        //String startListName = new String();
        //this.fromContext = context;
        //System.out.println("========= context 2" + context);
        //this.assetManager = context.getAssets();
        //if (letters == 5){
        //    this.word
        //}
        this.fiveWordListArray = getWordListArray("five", context);
        //System.out.println("========= Word List" + wordListArray.size());
        this.fiveStartingWords = getStartingWords("five", context);
        //System.out.println("========= Starting" + startingWords.size());

        //this.fromContext = context;


    }

    /*

    public static Map getWordListDBOriginal(Context context) {

        AssetManager assetManager = context.getAssets();

        //ContentValues cv = new ContentValues();
        //cv.put("Field1","Bob");
        //cv.put("Field2","19");
        //myDB.update(MY_TABLE_NAME, cv, "_id = ?", new String[]{id});


        dbHandler = new DBHandler(context);

        try {
            //InputStream csvInputStream = getResources().openRawResource(R.raw.calendario_bolsa);
            InputStream csvStream = assetManager.open("fiveLeterWords.csv");
            InputStreamReader csvStreamReader = new        InputStreamReader(csvStream);
            CSVReader reader = new CSVReader(csvStreamReader);
            //CSVReader reader = new CSVReader(new FileReader("fiveLeterWords.csv"));
            String[] nextLine;
            boolean state;
            while ((nextLine = reader.readNext()) != null) {
                String word = new String();
                if (nextLine[1].equals('t')){
                    state = true;
                    dbHandler.addWord(nextLine[0], "t");
                }
                else {state = false;}
                //word = nextLine[0] + nextLine[1] + nextLine[2] + nextLine[3] + nextLine[4];
                wordList.put(nextLine[0], state);
                // nextLine[] is an array of values from the line

                dbHandler.addWord(nextLine[0], "f");

            }
        } catch (IOException | CsvValidationException e) {

        }
        return wordList;
    }


    public static Map getWordListOrginal(Context context) {

        AssetManager assetManager = context.getAssets();


        try {
            //InputStream csvInputStream = getResources().openRawResource(R.raw.calendario_bolsa);
            InputStream csvStream = assetManager.open("fiveLeterWords.csv");
            InputStreamReader csvStreamReader = new        InputStreamReader(csvStream);
            CSVReader reader = new CSVReader(csvStreamReader);
            //CSVReader reader = new CSVReader(new FileReader("fiveLeterWords.csv"));
            String[] nextLine;
            boolean state;
            while ((nextLine = reader.readNext()) != null) {
                String word = new String();
                if (nextLine[1].equals('t')){
                    state = true;
                }
                else {state = false;}
                //word = nextLine[0] + nextLine[1] + nextLine[2] + nextLine[3] + nextLine[4];
                wordList.put(nextLine[0], state);
                // nextLine[] is an array of values from the line


            }
        } catch (IOException | CsvValidationException e) {

        }
        return wordList;
    }




     */
    public List getWordListArray(String letters, Context context) {

        String filename = letters + "LetterWords.csv";

        List<String> wordListArray = new ArrayList<String>();

        //AssetManager assetManager = fromContext.getAssets();
        AssetManager assetManager = context.getAssets();
        //assetManager = inm.assetManager.getAssets();
        //assetManager = this.assetManager;
        //System.out.println("========= context" + context);
        try {
            //InputStream csvInputStream = getResources().openRawResource(R.raw.calendario_bolsa);
            InputStream csvStream = assetManager.open(filename);
            InputStreamReader csvStreamReader = new        InputStreamReader(csvStream);
            CSVReader reader = new CSVReader(csvStreamReader);
            //CSVReader reader = new CSVReader(new FileReader("fiveLeterWords.csv"));
            String[] nextLine;
            boolean state;
            while ((nextLine = reader.readNext()) != null) {
                wordListArray.add(nextLine[0]);
            }
        } catch (IOException | CsvValidationException e) {

        }
        return wordListArray;
    }
    /*
    public static List getWordListArrayOut(Context context) {

        AssetManager assetManager = context.getAssets();
        //assetManager = inm.assetManager.getAssets();
        //assetManager = this.assetManager;
        //System.out.println("========= context" + context);
        try {
            //InputStream csvInputStream = getResources().openRawResource(R.raw.calendario_bolsa);
            InputStream csvStream = assetManager.open("fiveLetterWords.csv");
            InputStreamReader csvStreamReader = new        InputStreamReader(csvStream);
            CSVReader reader = new CSVReader(csvStreamReader);
            //CSVReader reader = new CSVReader(new FileReader("fiveLeterWords.csv"));
            String[] nextLine;
            boolean state;
            while ((nextLine = reader.readNext()) != null) {
                wordListArray.add(nextLine[0]);
            }
        } catch (IOException | CsvValidationException e) {
            System.out.println("=============================NOT GETTING WORDS==========");
        }
        return wordListArray;
    }
    */

    /*

    public static List getUsedListArray(Context context) {
        AssetManager assetManager = context.getAssets();

        try {
            //InputStream csvInputStream = getResources().openRawResource(R.raw.calendario_bolsa);
            InputStream csvStream = assetManager.open("usedWords.csv");
            InputStreamReader csvStreamReader = new        InputStreamReader(csvStream);
            CSVReader reader = new CSVReader(csvStreamReader);
            //CSVReader reader = new CSVReader(new FileReader("fiveLeterWords.csv"));
            String[] nextLine;
            boolean state;
            while ((nextLine = reader.readNext()) != null) {
                usedWords.add(nextLine[0]);
            }
        } catch (IOException | CsvValidationException e) {

        }
        return usedWords;
    }
     */


    public List getStartingWords(String letters, Context context){
        //AssetManager assetManager = context.getAssets();

        String filename = letters + "LetterWords.csv";


        List<String> startingWords = new ArrayList<String>();

        //AssetManager assetManager = fromContext.getAssets();
        AssetManager assetManager = context.getAssets();

        try {
            InputStream csvStream = assetManager.open(filename);
            InputStreamReader csvStreamReader = new  InputStreamReader(csvStream);
            CSVReader reader = new CSVReader(csvStreamReader);
            String[] nextLine;
            boolean state;
            while ((nextLine = reader.readNext()) != null) {
                startingWords.add(nextLine[0]);
            }
        } catch (IOException | CsvValidationException e) {
            //System.out.println("=============================HERE=================================");
        }
        return startingWords;
    }


    /*
    public static CSVReader getWordReader(Context context) {

        AssetManager assetManager = context.getAssets();

        CSVReader reader = null;

        try {
            //InputStream csvInputStream = getResources().openRawResource(R.raw.calendario_bolsa);
            InputStream csvStream = assetManager.open("fiveLeterWords.csv");
            InputStreamReader csvStreamReader = new        InputStreamReader(csvStream);
            reader = new CSVReader(csvStreamReader);

        } catch (IOException e) {

        }
  
        return reader;
    }
    */


}
