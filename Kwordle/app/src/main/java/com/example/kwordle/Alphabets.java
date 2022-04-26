package com.example.kwordle;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Alphabets implements Serializable {

    public static Map<Character, AlphaWrapper> alphabet = new HashMap<>();
    //public static Context context;


    //Initialize alphabet
    Alphabets(Context context) {
        //this.context = context;

        alphabet.put('A', new AlphaWrapper(R.id.buttonA, context.getResources().getColor(R.color.white)));
        alphabet.put('B', new AlphaWrapper(R.id.buttonB, context.getResources().getColor(R.color.white)));
        alphabet.put('C', new AlphaWrapper(R.id.buttonC, context.getResources().getColor(R.color.white)));
        alphabet.put('D', new AlphaWrapper(R.id.buttonD, context.getResources().getColor(R.color.white)));
        alphabet.put('E', new AlphaWrapper(R.id.buttonE, context.getResources().getColor(R.color.white)));
        alphabet.put('F', new AlphaWrapper(R.id.buttonF, context.getResources().getColor(R.color.white)));
        alphabet.put('G', new AlphaWrapper(R.id.buttonG, context.getResources().getColor(R.color.white)));
        alphabet.put('H', new AlphaWrapper(R.id.buttonH, context.getResources().getColor(R.color.white)));
        alphabet.put('I', new AlphaWrapper(R.id.buttonI, context.getResources().getColor(R.color.white)));
        alphabet.put('J', new AlphaWrapper(R.id.buttonJ, context.getResources().getColor(R.color.white)));
        alphabet.put('K', new AlphaWrapper(R.id.buttonK, context.getResources().getColor(R.color.white)));
        alphabet.put('L', new AlphaWrapper(R.id.buttonL, context.getResources().getColor(R.color.white)));
        alphabet.put('M', new AlphaWrapper(R.id.buttonM, context.getResources().getColor(R.color.white)));
        alphabet.put('N', new AlphaWrapper(R.id.buttonN, context.getResources().getColor(R.color.white)));
        alphabet.put('O', new AlphaWrapper(R.id.buttonO, context.getResources().getColor(R.color.white)));
        alphabet.put('P', new AlphaWrapper(R.id.buttonP, context.getResources().getColor(R.color.white)));
        alphabet.put('Q', new AlphaWrapper(R.id.buttonQ, context.getResources().getColor(R.color.white)));
        alphabet.put('R', new AlphaWrapper(R.id.buttonR, context.getResources().getColor(R.color.white)));
        alphabet.put('S', new AlphaWrapper(R.id.buttonS, context.getResources().getColor(R.color.white)));
        alphabet.put('T', new AlphaWrapper(R.id.buttonT, context.getResources().getColor(R.color.white)));
        alphabet.put('U', new AlphaWrapper(R.id.buttonU, context.getResources().getColor(R.color.white)));
        alphabet.put('V', new AlphaWrapper(R.id.buttonV, context.getResources().getColor(R.color.white)));
        alphabet.put('W', new AlphaWrapper(R.id.buttonW, context.getResources().getColor(R.color.white)));
        alphabet.put('X', new AlphaWrapper(R.id.buttonX, context.getResources().getColor(R.color.white)));
        alphabet.put('Y', new AlphaWrapper(R.id.buttonY, context.getResources().getColor(R.color.white)));
        alphabet.put('Z', new AlphaWrapper(R.id.buttonZ, context.getResources().getColor(R.color.white)));

    }

    protected Alphabets(Parcel in) {
    }






    /*
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeSerializable((Serializable) alphabet);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Alphabets> CREATOR = new Creator<Alphabets>() {
        @Override
        public Alphabets createFromParcel(Parcel in) {
            return new Alphabets(in);
        }

        @Override
        public Alphabets[] newArray(int size) {
            return new Alphabets[size];
        }
    };




     */











    /*
        protected Alphabets(Parcel in) {
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {

        }

        @Override
        public int describeContents() {
            return 0;
        }

        public static final Creator<Alphabets> CREATOR = new Creator<Alphabets>() {
            @Override
            public Alphabets createFromParcel(Parcel in) {
                return new Alphabets(in);
            }

            @Override
            public Alphabets[] newArray(int size) {
                return new Alphabets[size];
            }
        };


         */
    public AlphaWrapper get(char thisCharacter){
        return alphabet.get(thisCharacter);
    }

    public void put(char thisCharacter, AlphaWrapper thisWrapper){
        alphabet.put(thisCharacter, thisWrapper);
    }

    public Set<Map.Entry<Character, AlphaWrapper>> entrySet(){
        return alphabet.entrySet();
    }



    /*
    public static class AlphaWrapper {
        public int letter;
        public int color;

        public AlphaWrapper(int letter, int color) {
            this.letter = letter;
            this.color = color;
        }

        public int getColor() {return this.color;}
        public int getLetter() {return this.letter;}

    }

     */


    /*
    public void readFromParcel(Parcel source) {
        this.alphabet = (HashMap<Character, AlphaWrapper>) source.readSerializable();
    }

     */

}
