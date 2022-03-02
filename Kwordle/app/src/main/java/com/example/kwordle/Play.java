package com.example.kwordle;


import android.content.Context;
import android.util.Pair;

public class Play  {


    public static Pair<Integer[], Boolean> enter(Context context, char[] currentWord, char[] wordEntry, Integer[] wordColorInt,
                                Integer letters, Alphabets alphabet)
    {
        Boolean correct = true;

        char answerCheck[] = currentWord.clone();  //This is the answer
        char entryCheck[] = wordEntry.clone();  //This what was entered
        Integer currentWordColor[] = wordColorInt.clone();


        //Loop over the letters and set each of the letters in the entry to Gray or Green
        for (int i = 0; i < letters; i++) {
            if (entryCheck[i] == answerCheck[i]) {
                currentWordColor[i] = context.getResources().getColor(R.color.green);
                entryCheck[i] = ' ';
                answerCheck[i] = '>';
                //currentWordColorString[i] = "green";
                Alphabets.alphaWrapper thisLetter = alphabet.get(wordEntry[i]);
                thisLetter.color = context.getResources().getColor(R.color.green);
                alphabet.put(wordEntry[i], thisLetter);

            }
            else {
                correct = false;
                Alphabets.alphaWrapper thisLetter  = alphabet.get(wordEntry[i]);
                if (thisLetter.getColor() != context.getResources().getColor(R.color.yellow) &&
                        thisLetter.getColor() != context.getResources().getColor(R.color.green)) {
                    thisLetter.color = context.getResources().getColor(R.color.gray);
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
                    //currentWordColorString[i] = "yellow";
                    Alphabets.alphaWrapper thisLetter = alphabet.get(wordEntry[i]);
                    //If the letter is already green do not change it in the alphabet
                    if (thisLetter.getColor() != context.getResources().getColor(R.color.green)) {
                        thisLetter.color = context.getResources().getColor(R.color.yellow);
                        alphabet.put(wordEntry[i], thisLetter);

                    }
                }
            }
        }

        return new Pair<Integer[], Boolean>(currentWordColor, correct);


    }

    /*

    public static void entered(Context context, char[] entry, char[] answer, int tries, int characterNumber, int letters, Float time, List wordListArray){

        Boolean correct = true;

        String thisAnswer = new String();
        for (int i = 0; i < letters; i++){
            thisAnswer += entry[i];
        }

        if (importFiveLetterWord.wordListArray.contains(thisAnswer) == false ) {
            Toast.makeText(getApplicationContext(), "THAT IS NOT A WORD!!!", Toast.LENGTH_LONG).show();

            return;
        }


        char answerCheck[] = currentWord.clone();  //This is the answer
        char entryCheck[] = wordEntry.clone();  //This what was entered
        String currentWordColorString[] = wordColor.clone();
        Integer currentWordColor[] = wordColorInt.clone();


        //Loop over the letters and set each of the letters in the entry to Gray or Green
        for (int i = 0; i < letters; i++) {
            if (entryCheck[i] == answerCheck[i]) {
                currentWordColor[i] = getResources().getColor(R.color.green);
                entryCheck[i] = ' ';
                answerCheck[i] = '>';
                currentWordColorString[i] = "green";
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
                //thisLetter.color = getResources().getColor(R.color.gray);
                //alphabet.put(wordEntry[i], thisLetter);
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
                    currentWordColorString[i] = "yellow";
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


        /*


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
            updateArchive(String.valueOf(correct));
            startActivity(new Intent(MainActivity.this,PopCorrect.class));
        }
        return;
    }

    */

    /*

    public static void enterCheck(Context context, char[] entry, char[] answer, int[] wordColorInt, int tries, int characterNumber, int letters, Float time, List wordListArray){



        char answerCheck[] = answer.clone();  //This is the answer
        char entryCheck[] = entry.clone();  //This what was entered
        //String currentWordColorString[] = wordColor.clone();
        int[] currentWordColor = wordColorInt.clone();


        //Loop over the letters and set each of the letters in the entry to Gray or Green
        for (int i = 0; i < letters; i++) {
            if (entryCheck[i] == answerCheck[i]) {
                currentWordColor[i] = context.getResources().getColor(R.color.green);
                entryCheck[i] = ' ';
                answerCheck[i] = '>';
                //currentWordColorString[i] = "green";
                AlphaWrapper thisLetter = alphabet.get(wordEntry[i]);
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
                //thisLetter.color = getResources().getColor(R.color.gray);
                //alphabet.put(wordEntry[i], thisLetter);
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
                    currentWordColorString[i] = "yellow";
                    alphaWrapper thisLetter = alphabet.get(wordEntry[i]);
                    //If the letter is already green do not change it in the alphabet
                    if (thisLetter.getColor() != getResources().getColor(R.color.green)) {
                        thisLetter.color = getResources().getColor(R.color.yellow);
                        alphabet.put(wordEntry[i], thisLetter);

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
            updateArchive(String.valueOf(correct));
            startActivity(new Intent(MainActivity.this,PopCorrect.class));
        }
        return;
    }
    */

}
