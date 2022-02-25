package com.example.kwordle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    //public static Object theAnswer;
    public static Integer tries = 6;
    public static Integer letters = 5;
    public static Integer characterNumber = 0;
    public static char wordEntry[] = new char[letters];
    //public Integer rowNumber = 0;
    public static Integer currentTry;
    public static Map<String, Boolean> wordList = new HashMap<>();

    public int[][] tableIds= {
            {R.id.rowOne_columnOne, R.id.rowOne_columnTwo, R.id.rowOne_columnThree, R.id.rowOne_columnFour, R.id.rowOne_columnFive},
            {R.id.rowTwo_columnOne, R.id.rowTwo_columnTwo, R.id.rowTwo_columnThree, R.id.rowTwo_columnFour, R.id.rowTwo_columnFive},
            {R.id.rowThree_columnOne, R.id.rowThree_columnTwo, R.id.rowThree_columnThree, R.id.rowThree_columnFour, R.id.rowThree_columnFive},
            {R.id.rowFour_columnOne, R.id.rowFour_columnTwo, R.id.rowFour_columnThree, R.id.rowFour_columnFour, R.id.rowFour_columnFive},
            {R.id.rowFive_columnOne, R.id.rowFive_columnTwo, R.id.rowFive_columnThree, R.id.rowFive_columnFour, R.id.rowFive_columnFive},
            {R.id.rowSix_columnOne, R.id.rowSix_columnTwo, R.id.rowSix_columnThree, R.id.rowSix_columnFour, R.id.rowSix_columnFive}
    };

    //public static int[][] tableColors = new int[letters][tries];

    public String[][] tableColors = new String[tries][letters];
    public static Integer[][] tableColorsInt = new Integer[tries][letters];

    public Hashtable entry = new Hashtable();
    public static Map<Character, alphaWrapper> alphabet = new HashMap<>();
    public char[] currentWord = new char[letters];
    public String[] wordColor = new String[letters];
    public Integer[] wordColorInt = new Integer[letters];
    public static Boolean correct;
    public static String theAnswer = new String();


    //public Random rn = new Random();
    //public Button newGameButton = findViewById(R.id.newGame);
    //public ImageButton statsButton = findViewById(R.id.stats);
    //public ImageButton settingsButton = findViewById(R.id.settings);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        if (savedInstanceState != null){
            characterNumber = savedInstanceState.getInt("Key_characterNumber");
            wordEntry = savedInstanceState.getCharArray("Key_wordEntry");
            theAnswer = savedInstanceState.getString("Key_theAnswer");
            //alphabet = (Map<Character, alphaWrapper>) savedInstanceState.getSerializable("Key_alphabet");
            //tableColorsInt = (Integer[][]) savedInstanceState.getSerializable("Key_tablecolor");
            currentTry = savedInstanceState.getInt("Key_currentTry");

            //setAlphabetColor();
            //setTableColorsInt();

            initializeAlphabet();
            initializeTableColors();


            setWordEntry();
            initializeWordColor();  //TODO not sure about this
        }
        else {
        */
            //alphabet = initializeAlphabet();
            initializeAlphabet();
            currentWord = getNewWord();
            initializeTableColors();
            initializeWordColor();
            initializeWordEntry();
            currentTry = 0;
        //}

        //public void onStart

    }

    /*
    protected void onResume(@NonNull Bundle savedInstanceState){
        characterNumber = savedInstanceState.getInt("Key_characterNumber");
        wordEntry = savedInstanceState.getCharArray("Key_wordEntry");
        theAnswer = savedInstanceState.getString("Key_theAnswer");
        //alphabet = (Map<Character, alphaWrapper>) savedInstanceState.getSerializable("Key_alphabet");
        //tableColorsInt = (Integer[][]) savedInstanceState.getSerializable("Key_tablecolor");
        currentTry = savedInstanceState.getInt("Key_currentTry");

        //setAlphabetColor();
        //setTableColorsInt();

        initializeAlphabet();
        initializeTableColors();

        setWordEntry();
        initializeWordColor();  //TODO not sure about this
    }


     */
    /*
    protected void onStart(@NonNull Bundle savedInstanceState){
        characterNumber = savedInstanceState.getInt("Key_characterNumber");
        wordEntry = savedInstanceState.getCharArray("Key_wordEntry");
        theAnswer = savedInstanceState.getString("Key_theAnswer");
        //alphabet = (Map<Character, alphaWrapper>) savedInstanceState.getSerializable("Key_alphabet");
        //tableColorsInt = (Integer[][]) savedInstanceState.getSerializable("Key_tablecolor");
        currentTry = savedInstanceState.getInt("Key_currentTry");

        //setAlphabetColor();
        //setTableColorsInt();

        initializeAlphabet();
        initializeTableColors();

        setWordEntry();
        initializeWordColor();  //TODO not sure about this
    }
    */

    /*
    protected void onRestore(@NonNull Bundle savedInstanceState) {
        characterNumber = savedInstanceState.getInt("Key_characterNumber");
        wordEntry = savedInstanceState.getCharArray("Key_wordEntry");
        theAnswer = savedInstanceState.getString("Key_theAnswer");
        //alphabet = (Map<Character, alphaWrapper>) savedInstanceState.getSerializable("Key_alphabet");
        //tableColorsInt = (Integer[][]) savedInstanceState.getSerializable("Key_tablecolor");
        currentTry = savedInstanceState.getInt("Key_currentTry");

        //setAlphabetColor();
        //setTableColorsInt();

        initializeAlphabet();
        initializeTableColors();

        setWordEntry();
        initializeWordColor();  //TODO not sure about this

    }
    */

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
        characterNumber = savedInstanceState.getInt("Key_characterNumber");
        wordEntry = savedInstanceState.getCharArray("Key_wordEntry");
        theAnswer = savedInstanceState.getString("Key_theAnswer");
        //alphabet = (Map<Character, alphaWrapper>) savedInstanceState.getSerializable("Key_alphabet");
        //tableColorsInt = (Integer[][]) savedInstanceState.getSerializable("Key_tablecolor");
        currentTry = savedInstanceState.getInt("Key_currentTry");

        //setAlphabetColor();
        //setTableColorsInt();

        initializeAlphabet();
        initializeTableColors();

        setWordEntry();
        initializeWordColor();  //TODO not sure about this

    }

     */

    public void newGameClick(View view) {
        startActivity(new Intent(MainActivity.this, MainActivity.class));
    }


    public void enterClick(View view) {

        correct = true;

        if (characterNumber != 5) {
            return;
        }

        char entryCheck[] = currentWord.clone();
        String currentWordColorString[] = wordColor.clone();
        Integer currentWordColor[] = wordColorInt.clone();

        //Loop over the letters and set each of the letters in the entry to Gray
        for (int i = 0; i < letters; i++) {
            alphaWrapper thisLetter  = alphabet.get(wordEntry[i]);
            thisLetter.color = getResources().getColor(R.color.gray);
            alphabet.put(wordEntry[i], thisLetter);
        }

        // Loop over the letters in the current entry
        for (int i = 0; i < letters; i++) {
            //Check to see if the letter is in the correct spot and set the letter to green in the alphabet and in the current guess array
            //also set the entry check to blank to allow for repeated letters
            if (wordEntry[i] == currentWord[i]) {
                currentWordColor[i] = getResources().getColor(R.color.green);
                entryCheck[i] = ' ';
                currentWordColorString[i] = "green";
                alphaWrapper thisLetter  = alphabet.get(wordEntry[i]);
                thisLetter.color = getResources().getColor(R.color.green);
                alphabet.put(wordEntry[i], thisLetter);

            //If not in the correct spot set the correct flag to false
            } else {
                correct = false;
                //Loop over the letters to see if they are correct, but in the wrong spot and set to yellow in the alphabet and current guess array
                //also set the entry check to blank to allow for repeated letters
                for (int j = 0; j < letters; j++) {
                    if (wordEntry[i] == entryCheck[j]) {
                        currentWordColor[i] = getResources().getColor(R.color.yellow);
                        entryCheck[j] = ' ';
                        currentWordColorString[i] = "yellow";
                        alphaWrapper thisLetter  = alphabet.get(wordEntry[i]);
                        //If the letter is already green do not change it in the alphabet
                        if (thisLetter.getColor() != getResources().getColor(R.color.green)) {
                            thisLetter.color = getResources().getColor(R.color.yellow);
                            alphabet.put(wordEntry[i], thisLetter);
                        }
                    }
                }
            }
        }

        //For the current guess set the colors
        for (int i = 0; i < letters; i++) {
            tableColorsInt[currentTry][i] = currentWordColor[i];
            TextView currentLetter = findViewById(tableIds[currentTry][i]);
            currentLetter.setBackgroundColor(currentWordColor[i]);
        }

        //Increment the current try and reset the character number
        currentTry += 1;
        characterNumber = 0;

        //Set the alphabet colors for the entry
        setAlphabetColor();
        //resetAlphabetColor();

        //Check if correct or game complete and initiate popup
        if (correct || currentTry > 5) {
            startActivity(new Intent(MainActivity.this,PopCorrect.class));
        }
        return;
    }


    class alphaWrapper {
        //private final int letter;
        //private final int color;

        public alphaWrapper(int letter, int color) {
            this.letter = letter;
            this.color = color;
        }

        public int getColor() {return this.color;}
        public int getLetter() {return this.letter;}

        private int letter;
        private int color;
    }


    //Initialize alphabet
    public Map initializeAlphabet() {
        //Map<Character, alphaWrapper> alphabet = new HashMap<>();

        alphabet.put('A', new alphaWrapper(R.id.buttonA, getResources().getColor(R.color.white)));
        alphabet.put('B', new alphaWrapper(R.id.buttonB, getResources().getColor(R.color.white)));
        alphabet.put('C', new alphaWrapper(R.id.buttonC, getResources().getColor(R.color.white)));
        alphabet.put('D', new alphaWrapper(R.id.buttonD, getResources().getColor(R.color.white)));
        alphabet.put('E', new alphaWrapper(R.id.buttonE, getResources().getColor(R.color.white)));
        alphabet.put('F', new alphaWrapper(R.id.buttonF, getResources().getColor(R.color.white)));
        alphabet.put('G', new alphaWrapper(R.id.buttonG, getResources().getColor(R.color.white)));
        alphabet.put('H', new alphaWrapper(R.id.buttonH, getResources().getColor(R.color.white)));
        alphabet.put('I', new alphaWrapper(R.id.buttonI, getResources().getColor(R.color.white)));
        alphabet.put('J', new alphaWrapper(R.id.buttonJ, getResources().getColor(R.color.white)));
        alphabet.put('K', new alphaWrapper(R.id.buttonK, getResources().getColor(R.color.white)));
        alphabet.put('L', new alphaWrapper(R.id.buttonL, getResources().getColor(R.color.white)));
        alphabet.put('M', new alphaWrapper(R.id.buttonM, getResources().getColor(R.color.white)));
        alphabet.put('N', new alphaWrapper(R.id.buttonN, getResources().getColor(R.color.white)));
        alphabet.put('O', new alphaWrapper(R.id.buttonO, getResources().getColor(R.color.white)));
        alphabet.put('P', new alphaWrapper(R.id.buttonP, getResources().getColor(R.color.white)));
        alphabet.put('Q', new alphaWrapper(R.id.buttonQ, getResources().getColor(R.color.white)));
        alphabet.put('R', new alphaWrapper(R.id.buttonR, getResources().getColor(R.color.white)));
        alphabet.put('S', new alphaWrapper(R.id.buttonS, getResources().getColor(R.color.white)));
        alphabet.put('T', new alphaWrapper(R.id.buttonT, getResources().getColor(R.color.white)));
        alphabet.put('U', new alphaWrapper(R.id.buttonU, getResources().getColor(R.color.white)));
        alphabet.put('V', new alphaWrapper(R.id.buttonV, getResources().getColor(R.color.white)));
        alphabet.put('W', new alphaWrapper(R.id.buttonW, getResources().getColor(R.color.white)));
        alphabet.put('X', new alphaWrapper(R.id.buttonX, getResources().getColor(R.color.white)));
        alphabet.put('Y', new alphaWrapper(R.id.buttonY, getResources().getColor(R.color.white)));
        alphabet.put('Z', new alphaWrapper(R.id.buttonZ, getResources().getColor(R.color.white)));

        return alphabet;
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

    public void setWordEntry(){
        for (int i = 0; i < letters; i++){
            TextView letterTextView = (TextView) findViewById(tableIds[currentTry][i]);
            letterTextView.setText(wordEntry[i]);
        }
    }

    public void setAlphabetColor() {
        for (Map.Entry<Character, alphaWrapper> entry : alphabet.entrySet()  ){
                alphaWrapper currentValue = entry.getValue();
                TextView currentLetter = findViewById(currentValue.letter);
                currentLetter.setBackgroundColor(currentValue.color);
        }
    }

    public void resetAlphabetColor() {
        for (Map.Entry<Character, alphaWrapper> entry : alphabet.entrySet()  ){
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



    public char[] getNewWord(){

        Map<String, Boolean> currentWordList = new HashMap<>();
        currentWordList = importFiveLetterWord.getWordList(this);

        boolean wordUsed = true;
        Random R = new Random();
        //Set<String> theKeys = currentWordList.keySet();
        //String[] keyArray = theKeys.toArray();

        List<String> theKeys = new ArrayList<>(currentWordList.keySet());

        while (wordUsed){
            int randomKey = R.nextInt(theKeys.size());
            wordUsed = currentWordList.get(theKeys.get(randomKey));
            theAnswer = theKeys.get(randomKey);
        }

        char newWord[] = new char[letters];

        for (int i = 0; i < letters; i++) {
            newWord[i] = theAnswer.charAt(i);
        }

        //theAnswer = "START";
        //Character newWord[] = {'S', 'T', 'A', 'R', 'T'};

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