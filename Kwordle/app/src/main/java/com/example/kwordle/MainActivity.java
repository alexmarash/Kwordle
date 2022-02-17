package com.example.kwordle;

import androidx.appcompat.app.AppCompatActivity;

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

    public Integer characterNumber = 0;
    public Character wordEntry[] = new Character[5];
    public Integer rowNumber = 0;

    public int[][] tableIds= {
            {R.id.rowOne_columnOne, R.id.rowOne_columnTwo, R.id.rowOne_columnThree, R.id.rowOne_columnFour, R.id.rowOne_columnFive},
            {R.id.rowTwo_columnOne, R.id.rowTwo_columnTwo, R.id.rowTwo_columnThree, R.id.rowTwo_columnFour, R.id.rowTwo_columnFive},
            {R.id.rowThree_columnOne, R.id.rowThere_columnTwo, R.id.rowThree_columnThree, R.id.rowThree_columnFour, R.id.rowThree_columnFive},
            {R.id.rowFour_columnOne, R.id.rowFour_columnTwo, R.id.rowFour_columnThree, R.id.rowFour_columnFour, R.id.rowFour_columnFive},
            {R.id.rowFive_columnOne, R.id.rowFive_columnTwo, R.id.rowFive_columnThree, R.id.rowFive_columnFour, R.id.rowFive_columnFive},
    };
    //findViewById(ids[0]);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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


        public void updateTextView(String toThis) {
            TextView textView = (TextView) findViewById(R.id.textView);
            textView.setText(toThis);
        }



    }

    Random rn = new Random();

    Button newGameButton = findViewById(R.id.newGame);
    ImageButton statsButton = findViewById(R.id.stats);
    ImageButton settingsButton = findViewById(R.id.settings);

    /*
    TextView rowOne_columnOneView = findViewById(R.id.rowOne_columnOne);
        // rowOne_columnOneView.setTextColor(Color.parseColor("#FFFFFF"));
        // rowOne_columnOneView.setBackgroundColor(0x55FF0000);
    TextView rowOne_columnTwoView = findViewById(R.id.rowOne_columnTwo);
    TextView rowOne_columnThreeView = findViewById(R.id.rowOne_columnThree);
    TextView rowOne_columnFourView = findViewById(R.id.rowOne_columnFour);
    TextView rowOne_columnFiveView = findViewById(R.id.rowOne_columnFive);
    TextView rowTwo_columnOneView = findViewById(R.id.rowTwo_columnOne);
    TextView rowTwo_columnTwoView = findViewById(R.id.rowTwo_columnTwo);
    TextView rowTwo_columnThreeView = findViewById(R.id.rowTwo_columnThree);
    TextView rowTwo_columnFourView = findViewById(R.id.rowTwo_columnFour);
    TextView rowTwo_columnFiveView = findViewById(R.id.rowTwo_columnFive);
    TextView rowThree_columnOneView = findViewById(R.id.rowThree_columnOne);
    TextView rowThree_columnTwoView = findViewById(R.id.rowThree_columnTwo);
    TextView rowThree_columnThreeView = findViewById(R.id.rowThree_columnThree);
    TextView rowThree_columnFourView = findViewById(R.id.rowThree_columnFour);
    TextView rowThree_columnFiveView = findViewById(R.id.rowThree_columnFive);
    TextView rowFour_columnOneView = findViewById(R.id.rowFour_columnOne);
    TextView rowFour_columnTwoView = findViewById(R.id.rowFour_columnTwo);
    TextView rowFour_columnThreeView = findViewById(R.id.rowFour_columnThree);
    TextView rowFour_columnFourView = findViewById(R.id.rowFour_columnFour);
    TextView rowFour_columnFiveView = findViewById(R.id.rowFour_columnFive);
    TextView rowFive_columnOneView = findViewById(R.id.rowFive_columnOne);
    TextView rowFive_columnTwoView = findViewById(R.id.rowFive_columnTwo);
    TextView rowFive_columnThreeView = findViewById(R.id.rowFive_columnThree);
    TextView rowFive_columnFourView = findViewById(R.id.rowFive_columnFour);
    TextView rowFive_columnFiveView = findViewById(R.id.rowFive_columnFive);



    Button bButton = findViewById(R.id.buttonB);




    Button cButton = findViewById(R.id.buttonC);
    Button dButton = findViewById(R.id.buttonD);
    Button eButton = findViewById(R.id.buttonE);
    Button fButton = findViewById(R.id.buttonF);
    Button gButton = findViewById(R.id.buttonG);
    Button hButton = findViewById(R.id.buttonH);
    Button iButton = findViewById(R.id.buttonI);
    Button jButton = findViewById(R.id.buttonJ);
    Button kButton = findViewById(R.id.buttonK);
    Button lButton = findViewById(R.id.buttonL);
    Button mButton = findViewById(R.id.buttonM);
    Button nButton = findViewById(R.id.buttonN);
    Button oButton = findViewById(R.id.buttonO);
    Button pButton = findViewById(R.id.buttonP);
    Button qButton = findViewById(R.id.buttonQ);
    Button rButton = findViewById(R.id.buttonR);
    Button sButton = findViewById(R.id.buttonS);
    Button tButton = findViewById(R.id.buttonT);
    Button uButton = findViewById(R.id.buttonU);
    Button vButton = findViewById(R.id.buttonV);
    Button wButton = findViewById(R.id.buttonW);
    Button xButton = findViewById(R.id.buttonX);
    Button yButton = findViewById(R.id.buttonY);
    Button zButton = findViewById(R.id.buttonZ);
    Button enterButton = findViewById(R.id.buttonEnter);
    Button deleteButton = findViewById(R.id.buttonDelete);




     */
    //private AdapterView.OnItemClickListener clickListener;
    //private ItemClickListener gameScoreClickListener;

    Hashtable alphabet = new Hashtable();
    Hashtable entry = new Hashtable();
    //ArrayList currentWord = new ArrayList<Character>();
    //ArrayList listOfWords = new ArrayList<String, Boolean>();
    Character currentWord[] = new Character[5];
    ArrayMap listOfWords = new ArrayMap<String,Boolean>();

    // allows clicks events to be caught
    //public void setClickListener(ItemClickListener itemClickListener) {
     //   this.gameScoreClickListener = itemClickListener;
    //}

    //Initialize alphabet
    public void initializeAlphabet() {
        alphabet.put("A", new Boolean(false));
        alphabet.put("B", new Boolean(false));
        alphabet.put("C", new Boolean(false));
        alphabet.put("D", new Boolean(false));
        alphabet.put("E", new Boolean(false));
        alphabet.put("F", new Boolean(false));
        alphabet.put("G", new Boolean(false));
        alphabet.put("H", new Boolean(false));
        alphabet.put("I", new Boolean(false));
        alphabet.put("J", new Boolean(false));
        alphabet.put("K", new Boolean(false));
        alphabet.put("L", new Boolean(false));
        alphabet.put("M", new Boolean(false));
        alphabet.put("N", new Boolean(false));
        alphabet.put("O", new Boolean(false));
        alphabet.put("P", new Boolean(false));
        alphabet.put("Q", new Boolean(false));
        alphabet.put("R", new Boolean(false));
        alphabet.put("S", new Boolean(false));
        alphabet.put("T", new Boolean(false));
        alphabet.put("U", new Boolean(false));
        alphabet.put("V", new Boolean(false));
        alphabet.put("W", new Boolean(false));
        alphabet.put("X", new Boolean(false));
        alphabet.put("Y", new Boolean(false));
        alphabet.put("Z", new Boolean(false));

    }



}