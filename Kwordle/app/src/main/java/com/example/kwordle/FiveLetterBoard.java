package com.example.kwordle;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.kwordle.Alphabets.alphaWrapper;

import java.util.Map;


public class FiveLetterBoard extends Opening {

    public static Integer tries = 6;
    public static Integer letters = 5;
    public static Integer characterNumber = 0;
    public static char[] wordEntry = new char[letters];
    public static Integer currentTry;

    public int[][] tableIds= {
            {R.id.rowOne_columnOne, R.id.rowOne_columnTwo, R.id.rowOne_columnThree, R.id.rowOne_columnFour, R.id.rowOne_columnFive},
            {R.id.rowTwo_columnOne, R.id.rowTwo_columnTwo, R.id.rowTwo_columnThree, R.id.rowTwo_columnFour, R.id.rowTwo_columnFive},
            {R.id.rowThree_columnOne, R.id.rowThree_columnTwo, R.id.rowThree_columnThree, R.id.rowThree_columnFour, R.id.rowThree_columnFive},
            {R.id.rowFour_columnOne, R.id.rowFour_columnTwo, R.id.rowFour_columnThree, R.id.rowFour_columnFour, R.id.rowFour_columnFive},
            {R.id.rowFive_columnOne, R.id.rowFive_columnTwo, R.id.rowFive_columnThree, R.id.rowFive_columnFour, R.id.rowFive_columnFive},
            {R.id.rowSix_columnOne, R.id.rowSix_columnTwo, R.id.rowSix_columnThree, R.id.rowSix_columnFour, R.id.rowSix_columnFive}
    };

    public String[][] tableColors = new String[tries][letters];
    public static Integer[][] tableColorsInt = new Integer[tries][letters];

    public char[] currentWord = new char[letters];
    public String[] wordColor = new String[letters];
    public Integer[] wordColorInt = new Integer[letters];
    public static Boolean correct;
    public static String theAnswer;
    public static float Time;
    public static boolean newGame = true;
    public static Alphabets alphabet;


    //OnCreate of the page
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.five_letter_main);

        //Create and initialize the alphabet and all colors and entries
        alphabet = new Alphabets(this);
        //initializeTableColors();
        initializeWordColor();
        //initializeWordEntry();

        //Get current answer
        currentWord = getNewWord();

        //Set current try to zero
        currentTry = 0;

        /* TODO holding for possible use for savedInstances
        if (savedInstanceState != null){} else { }
        */

    }

    //Save the state of the word entry, what character, what try and what the answer is
    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        savedInstanceState.putInt("Key_characterNumber", characterNumber);
        savedInstanceState.putCharArray("Key_wordEntry", wordEntry);
        savedInstanceState.putString("Key_theAnswer", theAnswer);
        savedInstanceState.putInt("Key_currentTry", currentTry);


        //TODO may need to call savedStates of the alphabet later
        //savedInstanceState.putSerializable("Key_alphabet", (Serializable) alphabet);
        //savedInstanceState.putSerializable("Key_tablecolor", tableColorsInt);

        super.onSaveInstanceState(savedInstanceState);
    }

    /* TODO holding for possible use for savedInstances
    public void onViewStateRestored(@NonNull Bundle savedInstanceState){
    }
    protected void onRestore(@NonNull Bundle savedInstanceState) {
    }
    protected void onStart(@NonNull Bundle savedInstanceState){
    }
    protected void onResume(@NonNull Bundle savedInstanceState){
    }
     */

    //TODO new game start from button on screen, this needs to change to popup
    public void newGameClick(View view) {
        newGame = true;
        startActivity(new Intent(FiveLetterBoard.this, FiveLetterBoard.class));
    }

    //TODO update archive, this needs to move to the database area
    //TODO need to check if the archive is already populated and if so update the entry instead of adding it
    public void updateArchive(String correct){

        String finishedTime = String.valueOf((Math.round(System.currentTimeMillis() - Time))/60000);
        String finishedTry = String.valueOf(currentTry);

        archives.execSQL("CREATE TABLE IF NOT EXISTS Statistics(Answer VARCHAR, Correct VARCHAR, Time VARCHAR, Trys VARCHAR);");
        String SQLInput = "INSERT INTO Statistics(Answer, Correct, Time, Trys) VALUES(?, ?, ?, ?)";

        //TODO this has not been checked
        //if (checkIfAnsweredBefore(theAnswer)){
        //    SQLInput = "UPDATE Statistics(Answer, Correct, Time, Trys) VALUES(?, ?, ?, ?)";
        //}

        String[] args = {theAnswer, correct, finishedTime, finishedTry};
        archives.execSQL(SQLInput, args);
    }

    //TODO update archive, this needs to move to the database area
    //Check if word is already in the database
    public Boolean checkIfAnsweredBefore(String possibleWord){

        String[] args = {possibleWord, "true"};
        Cursor cursor = archives.query("Statistics", null, "Answer=? AND Correct=?", args, null, null, null);

        // if cursor count is 0 then it is not in teh database
        return cursor.getCount() > 0;
    }

    //Check to see if the word is real
    public Boolean wordIsReal(){
        //Create a word from the characters
        String thisAnswer = "";
        for (int i = 0; i < letters; i++){
            thisAnswer += wordEntry[i];
        }

        //Check if word is in the word list
        if (!WordLists.fiveWordListArray.contains(thisAnswer)) {
            Toast.makeText(getApplicationContext(), "THAT IS NOT A WORD!!!", Toast.LENGTH_LONG).show();

            return false;
        }
        return true;
    }

    //Enter button actions
    public void enterClick(View view) {

        //Check to see if there are enough characters selected
        if (!characterNumber.equals(letters)) {
            return;
        }

        //Check to see if this is a new game and if so start the time
        if (newGame) {
            Time = System.currentTimeMillis();
            newGame = false;
        }

        //Check if this is actually a word
        if (!wordIsReal()){return;}

        //Creete the tuple of the word colors and correct check
        Pair<Integer[], Boolean> thisPlay = Play.enter(this, currentWord.clone(), wordEntry.clone(), wordColorInt, letters, alphabet);
        Integer[] currentWordColor = thisPlay.first;
        correct = thisPlay.second;

        //Set the colors for entry row
        setRowColor(currentWordColor);

        //Increment the current try and reset the character number
        currentTry += 1;
        characterNumber = 0;

        //Set the alphabet colors for the entry
        setAlphabetColor();

        //Check if correct or game complete and initiate popup
        checkComplete(correct, currentTry);

        return;
    }

    //Check to see if the game is complete, and update the archive, and trigger pop up if it is
    public void checkComplete(boolean correct, Integer currentTry){
        if (correct || currentTry > 5) {
            updateArchive(String.valueOf(correct));
            startActivity(new Intent(FiveLetterBoard.this,PopCorrect.class));
        }
    }


    //For the current entry set the colors in the table
    public void setRowColor(Integer[] currentWordColor){
        for (int i = 0; i < letters; i++) {
            tableColorsInt[currentTry][i] = currentWordColor[i];
            TextView currentLetter = findViewById(tableIds[currentTry][i]);
            currentLetter.setBackgroundColor(currentWordColor[i]);
        }
    }

    /*
    public void initializeTableColors(){
        for (int i = 0; i < tries; i++) {
            for (int j = 0; j < letters; j++) {
                tableColors[i][j] = "gray";
                tableColorsInt[i][j] = getResources().getColor(R.color.gray);
            }
        }
    }
    */

    public void initializeWordColor(){
        for (int i = 0; i < letters; i++){
            wordColor[i] = "gray";
            wordColorInt[i] = getResources().getColor(R.color.gray);
        }
    }

    public void initializeWordEntry(){
        for (int i = 0; i < letters; i++){
            wordEntry[i] = ' ';
        }
    }

    /*
    public void setWordEntry(){
        for (int i = 0; i < letters; i++){
            TextView letterTextView = (TextView) findViewById(tableIds[currentTry][i]);
            letterTextView.setText(wordEntry[i]);
        }
    }
    */


    public void setAlphabetColor() {
        for (Map.Entry<Character, alphaWrapper> entry : Alphabets.alphabet.entrySet()  ){
                alphaWrapper currentValue = entry.getValue();
                TextView currentLetter = findViewById(currentValue.letter);
                currentLetter.setBackgroundColor(currentValue.color);
        }
    }

    //Get a new word
    public char[] getNewWord(){

        //While the word chosen has already been used, choose another word
        Boolean wordUsed = true;
        while (wordUsed) {
            int index = (int) (Math.random() * WordLists.fiveStartingWords.size());
            theAnswer = WordLists.fiveStartingWords.get(index);
            wordUsed = checkIfAnsweredBefore(theAnswer);
        }

        //Create the character array to check against
        char[] newWord = new char[letters];
        for (int i = 0; i < letters; i++) {
            newWord[i] = theAnswer.charAt(i);
        }

        //TODO Debugging items
        //theAnswer = "START";
        //char newWord[] = {'S', 'T', 'A', 'R', 'T'};
        System.out.println("===============================" + theAnswer);

        return newWord;
    }



    public void qClick(View view) {
        if (characterNumber < 5) {
            wordEntry[characterNumber] = 'Q';
            TextView letterTextView = (TextView) findViewById(tableIds[currentTry][characterNumber]);
            letterTextView.setText("Q");
            characterNumber += 1;
        }

    }

    public void wClick(View view) {
        if (characterNumber < 5) {
            wordEntry[characterNumber] = 'W';
            TextView letterTextView = (TextView) findViewById(tableIds[currentTry][characterNumber]);
            letterTextView.setText("W");
            characterNumber += 1;
        }
    }

    public void eClick(View view) {
        if (characterNumber < 5) {
            wordEntry[characterNumber] = 'E';
            TextView letterTextView = (TextView) findViewById(tableIds[currentTry][characterNumber]);
            letterTextView.setText("E");
            characterNumber += 1;
        }
    }

    public void rClick(View view) {
        if (characterNumber < 5) {
            wordEntry[characterNumber] = 'R';
            TextView letterTextView = (TextView) findViewById(tableIds[currentTry][characterNumber]);
            letterTextView.setText("R");
            characterNumber += 1;
        }
    }

    public void tClick(View view) {
        if (characterNumber < 5) {
            wordEntry[characterNumber] = 'T';
            TextView letterTextView = (TextView) findViewById(tableIds[currentTry][characterNumber]);
            letterTextView.setText("T");
            characterNumber += 1;
        }
    }

    public void yClick(View view) {
        if (characterNumber < 5) {
            wordEntry[characterNumber] = 'Y';
            TextView letterTextView = (TextView) findViewById(tableIds[currentTry][characterNumber]);
            letterTextView.setText("Y");
            characterNumber += 1;
        }
    }

    public void uClick(View view) {
        if (characterNumber < 5) {
            wordEntry[characterNumber] = 'U';
            TextView letterTextView = (TextView) findViewById(tableIds[currentTry][characterNumber]);
            letterTextView.setText("U");
            characterNumber += 1;
        }
    }

    public void iClick(View view) {
        if (characterNumber < 5) {
            wordEntry[characterNumber] = 'I';
            TextView letterTextView = (TextView) findViewById(tableIds[currentTry][characterNumber]);
            letterTextView.setText("I");
            characterNumber += 1;
        }
    }

    public void oClick(View view) {
        if (characterNumber < 5) {
            wordEntry[characterNumber] = 'O';
            TextView letterTextView = (TextView) findViewById(tableIds[currentTry][characterNumber]);
            letterTextView.setText("O");
            characterNumber += 1;
        }
    }

    public void pClick(View view) {
        if (characterNumber < 5) {
            wordEntry[characterNumber] = 'P';
            TextView letterTextView = (TextView) findViewById(tableIds[currentTry][characterNumber]);
            letterTextView.setText("P");

            characterNumber += 1;
        }
    }

    public void aClick(View view) {
        if (characterNumber < 5) {
            wordEntry[characterNumber] = 'A';
            TextView letterTextView = (TextView) findViewById(tableIds[currentTry][characterNumber]);
            letterTextView.setText("A");

            characterNumber += 1;
        }
    }

    public void sClick(View view) {
        if (characterNumber < 5) {
            wordEntry[characterNumber] = 'S';
            TextView letterTextView = (TextView) findViewById(tableIds[currentTry][characterNumber]);
            letterTextView.setText("S");

            characterNumber += 1;
        }
    }

    public void dClick(View view) {
        if (characterNumber < 5) {
            wordEntry[characterNumber] = 'D';
            TextView letterTextView = (TextView) findViewById(tableIds[currentTry][characterNumber]);
            letterTextView.setText("D");
            characterNumber += 1;
        }
    }

    public void fClick(View view) {
        if (characterNumber < 5) {
            wordEntry[characterNumber] = 'F';
            TextView letterTextView = (TextView) findViewById(tableIds[currentTry][characterNumber]);
            letterTextView.setText("F");
            characterNumber += 1;
        }
    }

    public void gClick(View view) {
        if (characterNumber < 5) {
            wordEntry[characterNumber] = 'G';
            TextView letterTextView = (TextView) findViewById(tableIds[currentTry][characterNumber]);
            letterTextView.setText("G");
            characterNumber += 1;
        }
    }

    public void hClick(View view) {
        if (characterNumber < 5) {
            wordEntry[characterNumber] = 'H';
            TextView letterTextView = (TextView) findViewById(tableIds[currentTry][characterNumber]);
            letterTextView.setText("H");
            characterNumber += 1;
        }
    }

    public void jClick(View view) {
        if (characterNumber < 5) {
            wordEntry[characterNumber] = 'J';
            TextView letterTextView = (TextView) findViewById(tableIds[currentTry][characterNumber]);
            letterTextView.setText("J");
            characterNumber += 1;
        }
    }

    public void kClick(View view) {
        if (characterNumber < 5) {
            wordEntry[characterNumber] = 'K';
            TextView letterTextView = (TextView) findViewById(tableIds[currentTry][characterNumber]);
            letterTextView.setText("K");
            characterNumber += 1;
        }
    }

    public void lClick(View view) {
        if (characterNumber < 5) {
            wordEntry[characterNumber] = 'L';
            TextView letterTextView = (TextView) findViewById(tableIds[currentTry][characterNumber]);
            letterTextView.setText("L");
            characterNumber += 1;
        }
    }

    public void zClick(View view) {
        if (characterNumber < 5) {
            wordEntry[characterNumber] = 'Z';
            TextView letterTextView = (TextView) findViewById(tableIds[currentTry][characterNumber]);
            letterTextView.setText("Z");
            characterNumber += 1;
        }
    }

    public void xClick(View view) {
        if (characterNumber < 5) {
            wordEntry[characterNumber] = 'X';
            TextView letterTextView = (TextView) findViewById(tableIds[currentTry][characterNumber]);
            letterTextView.setText("X");
            characterNumber += 1;
        }
    }

    public void cClick(View view) {
        if (characterNumber < 5) {
            wordEntry[characterNumber] = 'C';
            TextView letterTextView = (TextView) findViewById(tableIds[currentTry][characterNumber]);
            letterTextView.setText("C");
            characterNumber += 1;
        }
    }

    public void vClick(View view) {
        if (characterNumber < 5) {
            wordEntry[characterNumber] = 'V';
            TextView letterTextView = (TextView) findViewById(tableIds[currentTry][characterNumber]);
            letterTextView.setText("V");
            characterNumber += 1;
        }
    }

    public void bClick(View view) {
        if (characterNumber < 5) {
            wordEntry[characterNumber] = 'B';
            TextView letterTextView = (TextView) findViewById(tableIds[currentTry][characterNumber]);
            letterTextView.setText("B");
            characterNumber += 1;
        }
    }

    public void nClick(View view) {
        if (characterNumber < 5) {
            wordEntry[characterNumber] = 'N';
            TextView letterTextView = (TextView) findViewById(tableIds[currentTry][characterNumber]);
            letterTextView.setText("N");
            characterNumber += 1;
        }
    }

    public void mClick(View view) {
        if (characterNumber < 5) {
            wordEntry[characterNumber] = 'M';
            TextView letterTextView = (TextView) findViewById(tableIds[currentTry][characterNumber]);
            letterTextView.setText("M");
            characterNumber += 1;
        }
    }

    public void deleteClick(View view) {
        if (characterNumber > 0) {
            characterNumber -= 1;
            wordEntry[characterNumber] = ' ';
            TextView letterTextView = (TextView) findViewById(tableIds[currentTry][characterNumber]);
            letterTextView.setText(" ");
        }
    }




}