package com.example.kwordle;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Alphabets {

    public static Map<Character, alphaWrapper> alphabet = new HashMap<>();
    public static Context context;


    //Initialize alphabet
    Alphabets(Context context) {
        //Map<Character, alphaWrapper> alphabet = new HashMap<>();
        this.context = context;
        this.alphabet = alphabet;

        this.alphabet.put('A', new alphaWrapper(R.id.buttonA, context.getResources().getColor(R.color.white)));
        this.alphabet.put('B', new alphaWrapper(R.id.buttonB, context.getResources().getColor(R.color.white)));
        this.alphabet.put('C', new alphaWrapper(R.id.buttonC, context.getResources().getColor(R.color.white)));
        this.alphabet.put('D', new alphaWrapper(R.id.buttonD, context.getResources().getColor(R.color.white)));
        this.alphabet.put('E', new alphaWrapper(R.id.buttonE, context.getResources().getColor(R.color.white)));
        this.alphabet.put('F', new alphaWrapper(R.id.buttonF, context.getResources().getColor(R.color.white)));
        this.alphabet.put('G', new alphaWrapper(R.id.buttonG, context.getResources().getColor(R.color.white)));
        this.alphabet.put('H', new alphaWrapper(R.id.buttonH, context.getResources().getColor(R.color.white)));
        this.alphabet.put('I', new alphaWrapper(R.id.buttonI, context.getResources().getColor(R.color.white)));
        this.alphabet.put('J', new alphaWrapper(R.id.buttonJ, context.getResources().getColor(R.color.white)));
        this.alphabet.put('K', new alphaWrapper(R.id.buttonK, context.getResources().getColor(R.color.white)));
        this.alphabet.put('L', new alphaWrapper(R.id.buttonL, context.getResources().getColor(R.color.white)));
        this.alphabet.put('M', new alphaWrapper(R.id.buttonM, context.getResources().getColor(R.color.white)));
        this.alphabet.put('N', new alphaWrapper(R.id.buttonN, context.getResources().getColor(R.color.white)));
        this.alphabet.put('O', new alphaWrapper(R.id.buttonO, context.getResources().getColor(R.color.white)));
        this.alphabet.put('P', new alphaWrapper(R.id.buttonP, context.getResources().getColor(R.color.white)));
        this.alphabet.put('Q', new alphaWrapper(R.id.buttonQ, context.getResources().getColor(R.color.white)));
        this.alphabet.put('R', new alphaWrapper(R.id.buttonR, context.getResources().getColor(R.color.white)));
        this.alphabet.put('S', new alphaWrapper(R.id.buttonS, context.getResources().getColor(R.color.white)));
        this.alphabet.put('T', new alphaWrapper(R.id.buttonT, context.getResources().getColor(R.color.white)));
        this.alphabet.put('U', new alphaWrapper(R.id.buttonU, context.getResources().getColor(R.color.white)));
        this.alphabet.put('V', new alphaWrapper(R.id.buttonV, context.getResources().getColor(R.color.white)));
        this.alphabet.put('W', new alphaWrapper(R.id.buttonW, context.getResources().getColor(R.color.white)));
        this.alphabet.put('X', new alphaWrapper(R.id.buttonX, context.getResources().getColor(R.color.white)));
        this.alphabet.put('Y', new alphaWrapper(R.id.buttonY, context.getResources().getColor(R.color.white)));
        this.alphabet.put('Z', new alphaWrapper(R.id.buttonZ, context.getResources().getColor(R.color.white)));

    }

    /*
                    alphaWrapper thisLetter = alphabet.get(wordEntry[i]);
                thisLetter.color = getResources().getColor(R.color.green);
                alphabet.put(wordEntry[i], thisLetter);
     */


    public alphaWrapper get(char thisCharacter){
        return this.alphabet.get(thisCharacter);
    }

    public void put(char thisCharacter, alphaWrapper thisWrapper){
        this.alphabet.put(thisCharacter, thisWrapper);
    }

    public Set entrySet(){
        return this.alphabet.entrySet();
    }

    /*
    public void setAlphabetColor() {
        for (Map.Entry<Character, MainActivity.alphaWrapper> entry : alphabet.entrySet()  ){
            MainActivity.alphaWrapper currentValue = entry.getValue();
            TextView currentLetter = findViewById(currentValue.letter);
            currentLetter.setBackgroundColor(currentValue.color);
        }
    }

    public void resetAlphabetColor() {
        for (Map.Entry<Character, MainActivity.alphaWrapper> entry : alphabet.entrySet()  ){
            MainActivity.alphaWrapper currentValue = entry.getValue();
            Character currentKey = entry.getKey();
            if (currentValue.color == getResources().getColor(R.color.yellow) || currentValue.color == getResources().getColor(R.color.green)) {
                currentValue.color = getResources().getColor(R.color.gray);
                alphabet.put(currentKey, currentValue);
            }
        }


    }
    */




    public class alphaWrapper {
        public int letter;
        public int color;

        public alphaWrapper(int letter, int color) {
            this.letter = letter;
            this.color = color;
        }

        public int getColor() {return this.color;}
        public int getLetter() {return this.letter;}

    }




}
