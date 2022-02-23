package com.example.kwordle;

import static android.R.*;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.R.color;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.ArrayMap;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;


public class MainActivity extends AppCompatActivity {

    //public static Object theAnswer;
    public Integer tries = 6;
    public Integer letters = 5;
    public Integer characterNumber = 0;
    public Character wordEntry[] = new Character[letters];
    //public Integer rowNumber = 0;
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
    public Integer[][] tableColorsInt = new Integer[tries][letters];

    public Hashtable entry = new Hashtable();
    public Map<Character, alphaWrapper> alphabet = new HashMap<>();
    public Character[] currentWord = new Character[letters];
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

        alphabet = initializeAlphabet();
        currentWord = getNewWord();
        initializeTableColors();
        initializeWordColor();
        currentTry = 0;
    }

    public void newGameClick(View view) {
        startActivity(new Intent(MainActivity.this, MainActivity.class));
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

    public void enterClick(View view) {

        correct = true;

        if (characterNumber != 5) {
            return;
        }
        Character entryCheck[] = currentWord.clone();
        String currentWordColorString[] = wordColor.clone();
        Integer currentWordColor[] = wordColorInt.clone();

        for (int i = 0; i < letters; i++) {
            alphaWrapper thisLetter  = alphabet.get(wordEntry[i]);
            thisLetter.color = getResources().getColor(R.color.gray);
            alphabet.put(wordEntry[i], thisLetter);
        }

        for (int i = 0; i < letters; i++) {
            if (wordEntry[i] == currentWord[i]) {

                //currentWordColor[i] = "green";
                currentWordColor[i] = getResources().getColor(R.color.green);
                entryCheck[i] = ' ';
                currentWordColorString[i] = "green";
                alphaWrapper thisLetter  = alphabet.get(wordEntry[i]);
                thisLetter.color = getResources().getColor(R.color.green);
                alphabet.put(wordEntry[i], thisLetter);

            } else {
                correct = false;
                for (int j = 0; j < letters; j++) {
                    if (wordEntry[i] == entryCheck[j]) {
                        //currentWordColor[i] = "yellow";
                        currentWordColor[i] = getResources().getColor(R.color.yellow);
                        entryCheck[j] = ' ';
                        currentWordColorString[i] = "yellow";
                        alphaWrapper thisLetter  = alphabet.get(wordEntry[i]);
                        if (thisLetter.getColor() != getResources().getColor(R.color.green)) {
                            thisLetter.color = getResources().getColor(R.color.yellow);
                            alphabet.put(wordEntry[i], thisLetter);
                        }
                    }
                }
            }
        }


        for (int i = 0; i < letters; i++) {
            tableColorsInt[currentTry][i] = currentWordColor[i];
            TextView currentLetter = findViewById(tableIds[currentTry][i]);
            currentLetter.setBackgroundColor(currentWordColor[i]);
        }


        currentTry += 1;
        characterNumber = 0;

        setAlphabetColor();
        //resetAlphabetColor();

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
        Map<Character, alphaWrapper> alphabet = new HashMap<>();

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

    public Character[] getNewWord(){

        theAnswer = "START";
        Character newWord[] = {'S', 'T', 'A', 'R', 'T'};

        return newWord;
    }
}