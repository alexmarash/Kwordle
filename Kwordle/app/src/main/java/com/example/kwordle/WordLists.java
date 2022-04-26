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
    //Create five letter word lists
    public static List<String> fiveWordListArray = new ArrayList<>();
    public static List<String> fiveStartingWords = new ArrayList<>();

    WordLists(Context context){
        //Initialize five letter word lists - done once upon opening app
        fiveWordListArray = getWordListArray("five", context);  //All 5 letter words
        fiveStartingWords = getStartingWords("five", context);  //Answer 5 letter words

    }

    //Get the word list to check against
    public List<String> getWordListArray(String letters, Context context) {
        //Create file name based on how many letters
        String filename = letters + "LetterWords.csv";
        List<String> wordListArray = new ArrayList<>();

        //Create asset manager based on context from opening screen
        AssetManager assetManager = context.getAssets();

        //Pull csv file from assets based on number of letters
        try {
            InputStream csvStream = assetManager.open(filename);
            InputStreamReader csvStreamReader = new InputStreamReader(csvStream);
            CSVReader reader = new CSVReader(csvStreamReader);
            //Read each line until null and add the word to the array
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                wordListArray.add(nextLine[0]);
            }
        } catch (IOException | CsvValidationException e) {
            return wordListArray;
        }

        //Return completed array
        return wordListArray;
    }

    //Get the word list to use as the answers
    public List<String> getStartingWords(String letters, Context context){
        //Create file name based on how many letters
        String filename = letters + "StartWords.csv";
        List<String> startingWords = new ArrayList<>();

        //Create asset manager based on context from opening screen
        AssetManager assetManager = context.getAssets();

        //Pull csv file from assets based on number of letters
        try {
            InputStream csvStream = assetManager.open(filename);
            InputStreamReader csvStreamReader = new  InputStreamReader(csvStream);
            CSVReader reader = new CSVReader(csvStreamReader);

            //Read each line until null and add the word to the array
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                startingWords.add(nextLine[0]);
            }
        } catch (IOException | CsvValidationException e) {
            return startingWords;
        }
        //Return completed array

        return startingWords;
    }
}
