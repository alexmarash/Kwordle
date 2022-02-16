package com.example.kwordle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.ArrayMap;
import android.view.View;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    Random rn = new Random();

    Button newGameButton = findViewById(R.id.newGame);
    ImageButton statsButton = findViewById(R.id.stats);
    ImageButton settingsButton = findViewById(R.id.settings);

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

    Button aButton = findViewById(R.id.buttonA);
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

    Hashtable alphabet = new Hashtable();
    Hashtable entry = new Hashtable();
    //ArrayList currentWord = new ArrayList<Character>();
    //ArrayList listOfWords = new ArrayList<String, Boolean>();
    Character currentWord[] = new Character[5];
    ArrayMap listOfWords = new ArrayMap<String,Boolean>();


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