package com.example.kwordle;

import static android.R.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.R.color;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.ArrayMap;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
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

    public Integer tries = 6;
    public Integer letters = 5;
    public Integer characterNumber = 0;
    public Character wordEntry[] = new Character[letters];
    //public Integer rowNumber = 0;
    public Integer currentTry;

    public int[][] tableIds= {
            {R.id.rowOne_columnOne, R.id.rowOne_columnTwo, R.id.rowOne_columnThree, R.id.rowOne_columnFour, R.id.rowOne_columnFive},
            {R.id.rowTwo_columnOne, R.id.rowTwo_columnTwo, R.id.rowTwo_columnThree, R.id.rowTwo_columnFour, R.id.rowTwo_columnFive},
            {R.id.rowThree_columnOne, R.id.rowThree_columnTwo, R.id.rowThree_columnThree, R.id.rowThree_columnFour, R.id.rowThree_columnFive},
            {R.id.rowFour_columnOne, R.id.rowFour_columnTwo, R.id.rowFour_columnThree, R.id.rowFour_columnFour, R.id.rowFour_columnFive},
            {R.id.rowFive_columnOne, R.id.rowFive_columnTwo, R.id.rowFive_columnThree, R.id.rowFive_columnFour, R.id.rowFive_columnFive},
    };

    public String[][] tableColors = new String[tries][letters];
    public Integer[][] tableColorsInt = new Integer[tries][letters];

    public Hashtable entry = new Hashtable();
    public Hashtable alphabet = new Hashtable();
    public Character[] currentWord = new Character[letters];
    public String[] wordColor = new String[letters];
    public Integer[] wordColorInt = new Integer[letters];

    //public Random rn = new Random();
    //public Button newGameButton = findViewById(R.id.newGame);
    //public ImageButton statsButton = findViewById(R.id.stats);
    //public ImageButton settingsButton = findViewById(R.id.settings);



    //findViewById(ids[0]);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alphabet = initializeAlphabet();
        currentWord = getNewWord();
        initializeTableColors();
        initializeWordColor();
        currentTry = 0;


        /*
        Button aButton = (Button)findViewById(R.id.buttonA);
        aButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (characterNumber < 5) {
                    wordEntry[characterNumber] = 'A';
                    characterNumber += 1;

                    TextView letterTextView = (TextView) findViewById(tableIds[rowNumber][characterNumber]);
                    letterTextView.setText('A');

                }
            }

        });

        Button bButton = (Button)findViewById(R.id.buttonB);
        bButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (characterNumber < 5) {
                    wordEntry[characterNumber] = 'B';
                    characterNumber += 1;

                    TextView letterTextView = (TextView) findViewById(tableIds[rowNumber][characterNumber]);
                    letterTextView.setText('B');

                }
            }

        });

        Button cButton = (Button)findViewById(R.id.buttonC);
        cButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (characterNumber < 5) {
                    wordEntry[characterNumber] = 'C';
                    characterNumber += 1;

                    TextView letterTextView = (TextView) findViewById(tableIds[rowNumber][characterNumber]);
                    letterTextView.setText('C');

                }
            }

        });

        Button dButton = (Button)findViewById(R.id.buttonD);
        dButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (characterNumber < 5) {
                    wordEntry[characterNumber] = 'D';
                    characterNumber += 1;

                    TextView letterTextView = (TextView) findViewById(tableIds[rowNumber][characterNumber]);
                    letterTextView.setText('D');

                }
            }

        });

        Button eButton = (Button)findViewById(R.id.buttonE);
        eButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (characterNumber < 5) {
                    wordEntry[characterNumber] = 'E';
                    characterNumber += 1;

                    TextView letterTextView = (TextView) findViewById(tableIds[rowNumber][characterNumber]);
                    letterTextView.setText('E');

                }
            }

        });

        Button fButton = (Button)findViewById(R.id.buttonF);
        fButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (characterNumber < 5) {
                    wordEntry[characterNumber] = 'F';
                    characterNumber += 1;

                    TextView letterTextView = (TextView) findViewById(tableIds[rowNumber][characterNumber]);
                    letterTextView.setText('F');

                }
            }

        });

        Button gButton = (Button)findViewById(R.id.buttonG);
        gButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (characterNumber < 5) {
                    wordEntry[characterNumber] = 'G';
                    characterNumber += 1;

                    TextView letterTextView = (TextView) findViewById(tableIds[rowNumber][characterNumber]);
                    letterTextView.setText('G');

                }
            }

        });


        //public void updateTextView(String toThis) {
        //    TextView textView = (TextView) findViewById(R.id.textView);
        //    textView.setText(toThis);
        //}

        */

    }

    public void qClick(View view) {
        if (characterNumber < 5) {
            wordEntry[characterNumber] = 'Q';
            TextView letterTextView = (TextView) findViewById(tableIds[currentTry][characterNumber]);
            //TextView letterTextView = (TextView) findViewById(tableIds[characterNumber][currentTry]);
            //letterTextView.setText(String.valueOf(currentTry));
            //letterTextView.setTextColor(Color.parseColor("#bdbdbd"));
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

    public void deleteClick(View view) {
        if (characterNumber > 0) {
            characterNumber -= 1;
            wordEntry[characterNumber] = ' ';
            TextView letterTextView = (TextView) findViewById(tableIds[currentTry][characterNumber]);
            letterTextView.setText(" ");
        }
    }

    public void enterClick(View view) {
        if (characterNumber != 5) {
            return;
        }
        Character entryCheck[] = currentWord.clone();
        //String currentWordColor[] = wordColor.clone();
        Integer currentWordColor[] = wordColorInt.clone();

        for (int i = 0; i < letters; i++) {
            if (wordEntry[i] == currentWord[i]) {
                //currentWordColor[i] = "green";
                currentWordColor[i] = getResources().getColor(R.color.green);
                entryCheck[i] = ' ';
                alphabet.put(wordEntry[i], getResources().getColor(R.color.green));

            } else {
                for (int j = 0; j < letters; j++) {
                    if (wordEntry[i] == entryCheck[j]) {
                        //currentWordColor[i] = "yellow";
                        currentWordColor[i] = getResources().getColor(R.color.yellow);
                        entryCheck[j] = ' ';
                        Object thisObject = alphabet.get('A');
                        Object greenColor = getResources().getColor(R.color.green);
                        if (alphabet.get(wordEntry[i]) != greenColor) {
                            alphabet.put(wordEntry[i], getResources().getColor(R.color.yellow));
                        }
                    }
                    else {
                        alphabet.put(wordEntry[i], getResources().getColor(R.color.gray));
                    }

                }
            }
        }

        for (int i = 0; i < letters; i++) {
            tableColorsInt[currentTry][i] = currentWordColor[i];
            //tableIds[i][currentTry].setBackgroundDrawable(getResources().getDrawable(R.drawable.cellborder) );
            TextView currentLetter = findViewById(tableIds[currentTry][i]);
            //currentLetter.setBackground(context.getResources().getColor(android.R.color.holo_green_light));
            //currentLetter.setBackgroundColor(getResources().getColor(R.color.black));
            //currentLetter.setBackgroundColor(tableColorInt[i][currentTry]);
            currentLetter.setBackgroundColor(currentWordColor[i]);
        }

        currentTry += 1;
        characterNumber = 0;
        //TextView letterTextView = (TextView) findViewById(tableIds[4][4]);
        //letterTextView.setText(String.valueOf(currentTry));
        return;
    }



    //Initialize alphabet
    public Hashtable initializeAlphabet() {
        Hashtable alphabet = new Hashtable();

        alphabet.put('A', getResources().getColor(R.color.white));
        alphabet.put('B', getResources().getColor(R.color.white));
        alphabet.put('C', getResources().getColor(R.color.white));
        alphabet.put('D', getResources().getColor(R.color.white));
        alphabet.put('E', getResources().getColor(R.color.white));
        alphabet.put('F', getResources().getColor(R.color.white));
        alphabet.put('G', getResources().getColor(R.color.white));
        alphabet.put('H', getResources().getColor(R.color.white));
        alphabet.put('I', getResources().getColor(R.color.white));
        alphabet.put('J', getResources().getColor(R.color.white));
        alphabet.put('K', getResources().getColor(R.color.white));
        alphabet.put('L', getResources().getColor(R.color.white));
        alphabet.put('M', getResources().getColor(R.color.white));
        alphabet.put('N', getResources().getColor(R.color.white));
        alphabet.put('O', getResources().getColor(R.color.white));
        alphabet.put('P', getResources().getColor(R.color.white));
        alphabet.put('Q', getResources().getColor(R.color.white));
        alphabet.put('R', getResources().getColor(R.color.white));
        alphabet.put('S', getResources().getColor(R.color.white));
        alphabet.put('T', getResources().getColor(R.color.white));
        alphabet.put('U', getResources().getColor(R.color.white));
        alphabet.put('V', getResources().getColor(R.color.white));
        alphabet.put('W', getResources().getColor(R.color.white));
        alphabet.put('X', getResources().getColor(R.color.white));
        alphabet.put('Y', getResources().getColor(R.color.white));
        alphabet.put('Z', getResources().getColor(R.color.white));

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

    public void setAlphabetColor(){



    }



    public Character[] getNewWord(){

        Character newWord[] = {'S', 'T', 'A', 'R', 'T'};

        return newWord;
    }
}