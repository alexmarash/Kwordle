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

import java.util.Hashtable;
import java.util.Map;


public class FiveLetterBoard extends Opening {

    public static Integer tries = 6;
    public static Integer letters = 5;
    public static Integer characterNumber = 0;
    public static char wordEntry[] = new char[letters];
    public static Integer currentTry;
    //public static Map<String, Boolean> wordList = new HashMap<>();


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

    public Hashtable entry = new Hashtable();
    //public static Map<Character, alphaWrapper> alphabet = new HashMap<>();
    public char[] currentWord = new char[letters];
    public String[] wordColor = new String[letters];
    public Integer[] wordColorInt = new Integer[letters];
    public static Boolean correct;
    public static String theAnswer = new String();
    //public static SQLiteDatabase archives;
    public static float Time;
    public static boolean newGame = true;
    public static Alphabets alphabet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.five_letter_main);

        //archives = openOrCreateDatabase("kwordleArchive",MODE_PRIVATE, null );
        alphabet = new Alphabets(this);

        WordLists fiveLetterWord = new WordLists(this, letters);
        currentWord = getNewWord();
        initializeTableColors();
        initializeWordColor();
        initializeWordEntry();
        currentTry = 0;

        /*
        if (savedInstanceState != null){} else { }
        */

    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        savedInstanceState.putInt("Key_characterNumber", characterNumber);
        savedInstanceState.putCharArray("Key_wordEntry", wordEntry);
        savedInstanceState.putString("Key_theAnswer", theAnswer);
        //savedInstanceState.putSerializable("Key_alphabet", (Serializable) alphabet);  //TODO This may need to do as an intent
        //savedInstanceState.putSerializable("Key_tablecolor", tableColorsInt);
        savedInstanceState.putInt("Key_currentTry", currentTry);

        super.onSaveInstanceState(savedInstanceState);
    }

    /*
    public void onViewStateRestored(@NonNull Bundle savedInstanceState){
    }
    protected void onRestore(@NonNull Bundle savedInstanceState) {
    }
    protected void onStart(@NonNull Bundle savedInstanceState){
    }
    protected void onResume(@NonNull Bundle savedInstanceState){
    }
     */


    public void newGameClick(View view) {
        newGame = true;
        startActivity(new Intent(FiveLetterBoard.this, FiveLetterBoard.class));
    }

    public void updateArchive(String correct){

        String finishedTime = String.valueOf((Math.round(System.currentTimeMillis() - Time))/60000);
        String finishedTry = String.valueOf(currentTry);

        archives.execSQL("CREATE TABLE IF NOT EXISTS Statistics(Answer VARCHAR, Correct VARCHAR, Time VARCHAR, Trys VARCHAR);");

        //Cursor cursor = archives.query("Statistics", null, null, null, null, null, null);
        String SQLinput = "INSERT INTO Statistics(Answer, Correct, Time, Trys) VALUES(?, ?, ?, ?)";
        String[] args = {theAnswer, correct, finishedTime, finishedTry};
        archives.execSQL(SQLinput, args);
    }

    public Boolean checkIfAnsweredBefore(String possibleWord){

        String[] args = {possibleWord, "true"};
        Cursor cursor = archives.query("Statistics", null, "Answer=? AND Correct=?", args, null, null, null);

        if (cursor.getCount() > 0){
            return true;
        }
        else { return false;}
    }

    public Boolean wordIsReal(){
        String thisAnswer = new String();
        for (int i = 0; i < letters; i++){
            thisAnswer += wordEntry[i];
        }

        if (WordLists.fiveWordListArray.contains(thisAnswer) == false ) {
            Toast.makeText(getApplicationContext(), "THAT IS NOT A WORD!!!", Toast.LENGTH_LONG).show();

            return false;
        }
        return true;
    }

    public void enterClick(View view) {
        //correct = true;


        if (characterNumber != letters) {
            return;
        }

        if (newGame) {
            Time = System.currentTimeMillis();
            newGame = false;
        }

        //Check if this is actually a word
        if (!wordIsReal()){return;}

        //currentWordColor = Play.enter(this, currentWord, wordEntry, wordColorInt, letters, alphabet, correct);

        //char answerCheck[] = currentWord.clone();  //This is the answer
        //char entryCheck[] = wordEntry.clone();  //This what was entered
        //String currentWordColorString[] = wordColor.clone();
        //Integer[] currentWordColor = wordColorInt.clone();
        Pair<Integer[], Boolean> thisPlay = Play.enter(this, currentWord.clone(), wordEntry.clone(), wordColorInt, letters, alphabet);
        //Integer currentWordColor[] = Play.enter(this, currentWord, wordEntry, wordColorInt, letters, alphabet, correct);

        Integer[] currentWordColor = thisPlay.first;
        correct = thisPlay.second;


        /*

        //Loop over the letters and set each of the letters in the entry to Gray or Green
        for (int i = 0; i < letters; i++) {
            if (entryCheck[i] == answerCheck[i]) {
                currentWordColor[i] = getResources().getColor(R.color.green);
                entryCheck[i] = ' ';
                answerCheck[i] = '>';
                //currentWordColorString[i] = "green";
                alphaWrapper thisLetter = alphabet.get(wordEntry[i]);
                thisLetter.color = getResources().getColor(R.color.green);
                alphabet.put(wordEntry[i], thisLetter);

            }
            else {
                correct = false;
                alphaWrapper thisLetter  = alphabet.get(wordEntry[i]);
                if (thisLetter.getColor() != getResources().getColor(R.color.yellow) &&
                        thisLetter.getColor() != getResources().getColor(R.color.green)) {
                    thisLetter.color = getResources().getColor(R.color.gray);
                    alphabet.put(wordEntry[i], thisLetter);}
            }
        }
        for (int i = 0; i < letters; i++) {
            for (int j = 0; j < letters; j++) {
                //Loop over the letters to see if they are correct, but in the wrong spot and set to yellow in the alphabet and current guess array
                //also set the entry check to blank to allow for repeated letters
                if (entryCheck[i] == answerCheck[j]) {
                    currentWordColor[i] = getResources().getColor(R.color.yellow);
                    entryCheck[i] = ' ';
                    answerCheck[j] = '>';
                    //currentWordColorString[i] = "yellow";
                    alphaWrapper thisLetter = alphabet.get(wordEntry[i]);
                    //If the letter is already green do not change it in the alphabet
                    if (thisLetter.getColor() != getResources().getColor(R.color.green)) {
                        thisLetter.color = getResources().getColor(R.color.yellow);
                        alphabet.put(wordEntry[i], thisLetter);

                    }
                }
            }
        }


         */

        //For the current guess set the colors
        setRowColor(currentWordColor);


        /*
        for (int i = 0; i < letters; i++) {
            tableColorsInt[currentTry][i] = currentWordColor[i];
            TextView currentLetter = findViewById(tableIds[currentTry][i]);
            currentLetter.setBackgroundColor(currentWordColor[i]);
        }
         */


        //Increment the current try and reset the character number
        currentTry += 1;
        characterNumber = 0;

        //Set the alphabet colors for the entry
        setAlphabetColor();

        //Check if correct or game complete and initiate popup
        checkComplete(correct, currentTry);

        /*
        if (correct || currentTry > 5) {
            updateArchive(String.valueOf(correct));
            startActivity(new Intent(MainActivity.this,PopCorrect.class));
        }
        */

        return;
    }


    public void checkComplete(boolean correct, Integer currentTry){
        if (correct || currentTry > 5) {
            updateArchive(String.valueOf(correct));
            startActivity(new Intent(FiveLetterBoard.this,PopCorrect.class));
        }
    }


    //For the current guess set the colors
    public void setRowColor(Integer[] currentWordColor){
        for (int i = 0; i < letters; i++) {
            tableColorsInt[currentTry][i] = currentWordColor[i];
            TextView currentLetter = findViewById(tableIds[currentTry][i]);
            currentLetter.setBackgroundColor(currentWordColor[i]);
        }
    }

    public void initializeTableColors(){
        for (int i = 0; i < tries; i++) {
            for (int j = 0; j < letters; j++) {
                tableColors[i][j] = "gray";
                tableColorsInt[i][j] = getResources().getColor(R.color.gray);
            }
        }
    }

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

    /*
    public void resetAlphabetColor() {
        for (Map.Entry<Character, alphaWrapper> entry : Alphabets.alphabet.entrySet()  ){
            alphaWrapper currentValue = entry.getValue();
            Character currentKey = entry.getKey();
            if (currentValue.color == getResources().getColor(R.color.yellow) || currentValue.color == getResources().getColor(R.color.green)) {
                currentValue.color = getResources().getColor(R.color.gray);
                alphabet.put(currentKey, currentValue);
            }
        }
    }

    public void setTableColorsInt() {
        for (int i = 0; i < tries; i++){
            for (int j = 0; j < letters; j++){
                TextView currentSquare = findViewById(tableIds[j][i]);
                currentSquare.setBackgroundColor(tableColorsInt[j][i]);
            }
        }


    }
    */

    public char[] getNewWord(){

        //List<String> usedWordList = importFiveLetterWord.getUsedListArray(this);

        Boolean wordUsed = true;

        while (wordUsed) {

            int index = (int) (Math.random() * WordLists.fiveStartingWords.size());
            theAnswer = WordLists.fiveStartingWords.get(index);
            wordUsed = checkIfAnsweredBefore(theAnswer);
        }

        char newWord[] = new char[letters];

        for (int i = 0; i < letters; i++) {
            newWord[i] = theAnswer.charAt(i);
        }

        //theAnswer = "START";
        //char newWord[] = {'S', 'T', 'A', 'R', 'T'};

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