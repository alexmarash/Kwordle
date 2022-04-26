package com.example.kwordle;


import android.content.Context;
import android.util.Pair;

public class Play  {

    public static Pair<Integer[], Boolean> enter(Context context, char[] currentWord, char[] wordEntry, Integer[] wordColorInt,
                                Integer letters, Alphabets alphabet)
    {
        boolean correct = true;

        char[] answerCheck = currentWord.clone();  //This is the answer
        char[] entryCheck = wordEntry.clone();  //This is what was entered
        Integer[] currentWordColor = wordColorInt.clone();


        //Loop over the letters and set each of the letters in the entry to Gray or Green
        for (int i = 0; i < letters; i++) {
            if (entryCheck[i] == answerCheck[i]) {
                currentWordColor[i] = context.getResources().getColor(R.color.green);
                entryCheck[i] = ' ';
                answerCheck[i] = '>';
                AlphaWrapper thisLetter = alphabet.get(wordEntry[i]);
                thisLetter.setColor(context.getResources().getColor(R.color.green));
                alphabet.put(wordEntry[i], thisLetter);

            }
            else {
                correct = false;
                AlphaWrapper thisLetter  = alphabet.get(wordEntry[i]);
                if (thisLetter.getColor() != context.getResources().getColor(R.color.yellow) &&
                        thisLetter.getColor() != context.getResources().getColor(R.color.green)) {
                    thisLetter.setColor(context.getResources().getColor(R.color.gray));
                    alphabet.put(wordEntry[i], thisLetter);}
            }
        }
        for (int i = 0; i < letters; i++) {
            for (int j = 0; j < letters; j++) {
                //Loop over the letters to see if they are correct, but in the wrong spot and set to yellow in the alphabet and current guess array
                //also set the entry check to blank to allow for repeated letters
                if (entryCheck[i] == answerCheck[j]) {
                    currentWordColor[i] = context.getResources().getColor(R.color.yellow);
                    entryCheck[i] = ' ';
                    answerCheck[j] = '>';
                    AlphaWrapper thisLetter = alphabet.get(wordEntry[i]);
                    //If the letter is already green do not change it in the alphabet
                    if (thisLetter.getColor() != context.getResources().getColor(R.color.green)) {
                        thisLetter.setColor(context.getResources().getColor(R.color.yellow));
                        alphabet.put(wordEntry[i], thisLetter);
                    }
                }
            }
        }


        return new Pair<>(currentWordColor, correct);


    }
}
