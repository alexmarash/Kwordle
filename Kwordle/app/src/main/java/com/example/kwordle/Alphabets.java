package com.example.kwordle;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Alphabets {

    public static Map<Character, alphaWrapper> alphabet = new HashMap<>();
    //public static Context context;


    //Initialize alphabet
    Alphabets(Context context) {
        //this.context = context;

        alphabet.put('A', new alphaWrapper(R.id.buttonA, context.getResources().getColor(R.color.white)));
        alphabet.put('B', new alphaWrapper(R.id.buttonB, context.getResources().getColor(R.color.white)));
        alphabet.put('C', new alphaWrapper(R.id.buttonC, context.getResources().getColor(R.color.white)));
        alphabet.put('D', new alphaWrapper(R.id.buttonD, context.getResources().getColor(R.color.white)));
        alphabet.put('E', new alphaWrapper(R.id.buttonE, context.getResources().getColor(R.color.white)));
        alphabet.put('F', new alphaWrapper(R.id.buttonF, context.getResources().getColor(R.color.white)));
        alphabet.put('G', new alphaWrapper(R.id.buttonG, context.getResources().getColor(R.color.white)));
        alphabet.put('H', new alphaWrapper(R.id.buttonH, context.getResources().getColor(R.color.white)));
        alphabet.put('I', new alphaWrapper(R.id.buttonI, context.getResources().getColor(R.color.white)));
        alphabet.put('J', new alphaWrapper(R.id.buttonJ, context.getResources().getColor(R.color.white)));
        alphabet.put('K', new alphaWrapper(R.id.buttonK, context.getResources().getColor(R.color.white)));
        alphabet.put('L', new alphaWrapper(R.id.buttonL, context.getResources().getColor(R.color.white)));
        alphabet.put('M', new alphaWrapper(R.id.buttonM, context.getResources().getColor(R.color.white)));
        alphabet.put('N', new alphaWrapper(R.id.buttonN, context.getResources().getColor(R.color.white)));
        alphabet.put('O', new alphaWrapper(R.id.buttonO, context.getResources().getColor(R.color.white)));
        alphabet.put('P', new alphaWrapper(R.id.buttonP, context.getResources().getColor(R.color.white)));
        alphabet.put('Q', new alphaWrapper(R.id.buttonQ, context.getResources().getColor(R.color.white)));
        alphabet.put('R', new alphaWrapper(R.id.buttonR, context.getResources().getColor(R.color.white)));
        alphabet.put('S', new alphaWrapper(R.id.buttonS, context.getResources().getColor(R.color.white)));
        alphabet.put('T', new alphaWrapper(R.id.buttonT, context.getResources().getColor(R.color.white)));
        alphabet.put('U', new alphaWrapper(R.id.buttonU, context.getResources().getColor(R.color.white)));
        alphabet.put('V', new alphaWrapper(R.id.buttonV, context.getResources().getColor(R.color.white)));
        alphabet.put('W', new alphaWrapper(R.id.buttonW, context.getResources().getColor(R.color.white)));
        alphabet.put('X', new alphaWrapper(R.id.buttonX, context.getResources().getColor(R.color.white)));
        alphabet.put('Y', new alphaWrapper(R.id.buttonY, context.getResources().getColor(R.color.white)));
        alphabet.put('Z', new alphaWrapper(R.id.buttonZ, context.getResources().getColor(R.color.white)));

    }


    public alphaWrapper get(char thisCharacter){
        return alphabet.get(thisCharacter);
    }

    public void put(char thisCharacter, alphaWrapper thisWrapper){
        alphabet.put(thisCharacter, thisWrapper);
    }

    public Set<Map.Entry<Character, alphaWrapper>> entrySet(){
        return alphabet.entrySet();
    }


    public static class alphaWrapper {
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
