package com.example.kwordle;

import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.kwordle.Alphabets.alphaWrapper;

import java.sql.SQLException;
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

    //public String[][] tableColors = new String[tries][letters];
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
        newGame = true;

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
        //newGame = true;
        //startActivity(new Intent(FiveLetterBoard.this, FiveLetterBoard.class));
        startActivity(new Intent(this,NewGamePopUp.class));
    }


    //Check to see if the word is real
    public Boolean wordIsReal(){
        //Create a word from the characters
        StringBuilder thisAnswer = new StringBuilder();
        for (int i = 0; i < letters; i++){
            thisAnswer.append(wordEntry[i]);
        }

        //Check if word is in the word list
        if (!WordLists.fiveWordListArray.contains(thisAnswer.toString())) {
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

    }

    //Check to see if the game is complete, and update the archive, and trigger pop up if it is
    public void checkComplete(boolean correct, Integer currentTry){
        if (correct || currentTry > 5) {
            try {
                archives.updateArchive(String.valueOf(correct), Time, currentTry, letters, theAnswer);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            //updateArchive(String.valueOf(correct));
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

    /*
    public void initializeWordEntry(){
        for (int i = 0; i < letters; i++){
            wordEntry[i] = ' ';
        }
    }

     */

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
            wordUsed = archives.checkIfAnsweredBefore(theAnswer);
        }


        //Create the character array to check against
        char[] newWord = new char[letters];
        for (int i = 0; i < letters; i++) {
            newWord[i] = theAnswer.charAt(i);
        }



        //TODO Debugging items
        //theAnswer = "START";
        //char newWord[] = {'S', 'T', 'A', 'R', 'T'};
        //System.out.println("===============================" + theAnswer);

        return newWord;
    }


    public void letterClick(char let){
        wordEntry[characterNumber] = let;
        TextView letterTextView = (TextView) findViewById(tableIds[currentTry][characterNumber]);
        letterTextView.setText(String.valueOf(let));
        characterNumber += 1;

    }
    public void qClick(View view) {
        if (characterNumber < 5) {
            letterClick('Q');
        }
    }

    /*
    public void qClick(View view) {
        if (characterNumber < 5) {
            wordEntry[characterNumber] = 'Q';
            TextView letterTextView = (TextView) findViewById(tableIds[currentTry][characterNumber]);
            letterTextView.setText("Q");
            characterNumber += 1;
        }

    }
     */

    public void wClick(View view) {
        if (characterNumber < 5) {
            letterClick('W');
        }
    }

    public void eClick(View view) {
        if (characterNumber < 5) {
            letterClick('E');
        }
    }

    public void rClick(View view) {
        if (characterNumber < 5) {
            letterClick('R');
        }
    }

    public void tClick(View view) {
        if (characterNumber < 5) {
            letterClick('T');
        }
    }

    public void yClick(View view) {
        if (characterNumber < 5) {
            letterClick('Y');
        }
    }

    public void uClick(View view) {
        if (characterNumber < 5) {
            letterClick('U');
        }
    }

    public void iClick(View view) {
        if (characterNumber < 5) {
            letterClick('I');
        }
    }

    public void oClick(View view) {
        if (characterNumber < 5) {
            letterClick('O');
        }
    }

    public void pClick(View view) {
        if (characterNumber < 5) {
            letterClick('P');
        }
    }

    public void aClick(View view) {
        if (characterNumber < 5) {
            letterClick('A');
        }
    }

    public void sClick(View view) {
        if (characterNumber < 5) {
            letterClick('S');
        }
    }

    public void dClick(View view) {
        if (characterNumber < 5) {
            letterClick('D');
        }
    }

    public void fClick(View view) {
        if (characterNumber < 5) {
            letterClick('F');
        }
    }

    public void gClick(View view) {
        if (characterNumber < 5) {
            letterClick('G');
        }
    }

    public void hClick(View view) {
        if (characterNumber < 5) {
            letterClick('H');
        }
    }

    public void jClick(View view) {
        if (characterNumber < 5) {
            letterClick('J');
        }
    }

    public void kClick(View view) {
        if (characterNumber < 5) {
            letterClick('K');
        }
    }

    public void lClick(View view) {
        if (characterNumber < 5) {
            letterClick('L');
        }
    }

    public void zClick(View view) {
        if (characterNumber < 5) {
            letterClick('Z');
        }
    }

    public void xClick(View view) {
        if (characterNumber < 5) {
            letterClick('X');
        }
    }

    public void cClick(View view) {
        if (characterNumber < 5) {
            letterClick('C');
        }
    }

    public void vClick(View view) {
        if (characterNumber < 5) {
            letterClick('V');
        }
    }

    public void bClick(View view) {
        if (characterNumber < 5) {
            letterClick('B');
        }
    }

    public void nClick(View view) {
        if (characterNumber < 5) {
            letterClick('N');
        }
    }

    public void mClick(View view) {
        if (characterNumber < 5) {
            letterClick('M');
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