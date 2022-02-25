package com.example.kwordle;

import android.content.Context;
import android.content.res.AssetManager;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class importFiveLetterWord {


    public static Map<String, Boolean> wordList = new HashMap<>();


    public static Map getWordList(Context context) {

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


}
