package com.example.kwordle;

import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;


public class FiveLetterBoard extends Opening {

    public static Integer tries = 6;
    public static Integer letters = 5;
    public static Integer characterNumber = 0;
    public static char[] wordEntry = new char[letters];
    public static Integer currentTry;

    public int[][] tableIds= {
            {R.id.rowOne_columnOne, R.id.rowOne_columnTwo, R.id.rowOne_columnThree, R.id.rowOne_columnFour, R.id.rowOne_columnFive},
            {R.id.rowTwo_columnOne, R.id.rowTwo_columnTwo, R.id.rowTwo_columnThree, R.id.rowTwo_columnFour, R.id.rowTwo_columnFive},
            {R.id.rowThree_columnOne, R.id.rowThree_columnTwo, R.id.rowThree_columnThree, R.id.rowThree_columnFour, R.id.rowThree_columnFive},
            {R.id.rowFour_columnOne, R.id.rowFour_columnTwo, R.id.rowFour_columnThree, R.id.rowFour_columnFour, R.id.rowFour_columnFive},
            {R.id.rowFive_columnOne, R.id.rowFive_columnTwo, R.id.rowFive_columnThree, R.id.rowFive_columnFour, R.id.rowFive_columnFive},
            {R.id.rowSix_columnOne, R.id.rowSix_columnTwo, R.id.rowSix_columnThree, R.id.rowSix_columnFour, R.id.rowSix_columnFive}
    };

    public static Integer[][] tableColorsInt = new Integer[tries][letters];
    public char[] currentWord = new char[letters];
    public String[] wordColor = new String[letters];
    public Integer[] wordColorInt = new Integer[letters];
    public static Boolean correct;
    public static String theAnswer;
    public static float Time;
    public static boolean newGame = true;
    public static Alphabets alphabet;
    public static String thePlayer;
    //public static char[] hints = new char[letters];
    public static ArrayList<Character> hintList = new ArrayList<Character>();

    private UponLetterClick viewModel;

    public SetLetterClicked setLetterClicked;

    Fragment fragment;

    //OnCreate of the page
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.five_letter_main);

        //Set teh player
        thePlayer = Opening.currentPlayer;

        //Create and initialize the alphabet and all colors and entries
        alphabet = new Alphabets(this);
        initializeWordColor();
        //initializeHints();
        hintList = new ArrayList<Character>();

        //Get current answer
        currentWord = getNewWord();

        //Set current try to zero
        currentTry = 0;
        newGame = true;



        LetterViewModel model = new ViewModelProvider(this).get(LetterViewModel.class);

        //Create keyboard fragment - this is the same fragment in all games
        //Create a bundle and pass the current alphabet to color it correctly
        if (savedInstanceState == null) {
            Bundle bundle = new Bundle();
            bundle.putParcelable("alphabet", alphabet);
            //bundle.putInt("this", 1);

            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.letter_container, LetterEntryFragment.class, bundle)
                    .commit();
        }


        model.getLetter().observe(this, String ->{

            //TODO UPDATE UI HERE
        });

        // Create the observer which updates the UI.
        final Observer<String> nameObserver = new Observer<String>() {
            @Override
            public void onChanged(@Nullable final String newInput) {

                if (newInput == "delete" && characterNumber > 0) {
                        deleteClickNoView();
                }
                else if (newInput == "enter" && characterNumber.equals(letters)){
                        enterClick();
                }
                else if (newInput != "delete" && newInput != "enter" && characterNumber < letters) {
                        letterClick(newInput.charAt(0));
                }
            }
        };

        model.getLetter().observe(this, nameObserver);

            /*
            for (Map.Entry<Character, AlphaWrapper> entry){

            }
            for (alpha : alphabet){


            }
            */

            /*
            Fragment fragment = new EpgEventListFragment();
            fragment.setArguments(arguments);
            fm.beginTransaction()
                    .replace(placeholder, fragment, tabId)
                    .commit();

            */


        //FragmentManager fm = getSupportFragmentManager();

        //Fragment letterEntry = LetterEntryFragment.newInstance(1, alphabet);
        //Fragment letterEntry = new LetterEntryFragment();
        //Fragment letterEntry = new TestFragment();
        //data.putInt("current_day", 1);

        //letterEntry.setArguments(data);



        //FragmentTransaction ft = fm.beginTransaction();



        //ft.add(R.id.letter_container, letterEntry);
        //ft.commit();
            /*
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    //.add(R.id.letter_container, LetterEntryFragment.class, null)
                    //.add(R.id.letter_container, letterEntry, null)
                    .replace(R.id.letter_container, letterEntry)
                    .commit();

             */

                    /*
            FragmentManager fm = getSupportFragmentManager();
            Fragment fragment = fm.findFragmentById(R.id.letter_container);

            if (fragment == null){
                fragment = new LetterEntryFragment();
                fm.beginTransaction()
                .add(R.id.letter_container, fragment)
                .commit();
            }
            */

            /*
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            fragmentTransaction.replace(R.id.letter_fragment_view, first);
            fragmentTransaction.commit();

            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.letter_fragment_view, LetterEntryFragment.class, bundle)
                    .commit();
             */

        //fragment = (Fragment) getSupportFragmentManager().findFragmentById(R.layout.letter_fragment);







        /*
        viewModel = new ViewModelProvider(this).get(UponLetterClick.class);
        viewModel.getSelectedItem().observe(this, item -> {
            characterNumber = item.getCharacterNumber();
            char thisLetter = item.getLetter();
            uponletterClick(thisLetter, characterNumber);
        });
         */

        /* TODO holding for possible use for savedInstances
        if (savedInstanceState != null){} else { }
        */

    }

    //Save the state of the word entry, what character, what try and what the answer is
    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        savedInstanceState.putInt("Key_characterNumber", characterNumber);
        savedInstanceState.putCharArray("Key_wordEntry", wordEntry);
        savedInstanceState.putString("Key_theAnswer", theAnswer);
        savedInstanceState.putInt("Key_currentTry", currentTry);

        //TODO may need to call savedStates of the alphabet later
        //savedInstanceState.putSerializable("Key_alphabet", (Serializable) alphabet);
        //savedInstanceState.putSerializable("Key_tablecolor", tableColorsInt);

        super.onSaveInstanceState(savedInstanceState);
    }

    /* TODO holding for possible use for savedInstances
    public void onViewStateRestored(@NonNull Bundle savedInstanceState){
    }
    protected void onRestore(@NonNull Bundle savedInstanceState) {
    }
    protected void onStart(@NonNull Bundle savedInstanceState){
    }
    protected void onResume(@NonNull Bundle savedInstanceState){
    }
     */

    //Opening new game popup
    public void newGameClick(View view) {
        startActivity(new Intent(this,NewGamePopUp.class));
    }

    //Check to see if the word is real
    public Boolean wordIsReal(){
        //Create a word from the characters
        StringBuilder thisAnswer = new StringBuilder();
        for (int i = 0; i < letters; i++){
            thisAnswer.append(wordEntry[i]);
        }

        //Check if word is in the word list
        if (!WordLists.fiveWordListArray.contains(thisAnswer.toString())) {
            Random randWord = new Random();
            int randWordNumber = randWord.nextInt(10);
            String randWordString = "word_" + String.valueOf(randWordNumber);
            int resourceWord = getResources().getIdentifier(randWordString, "string", "com.example.kwordle");

            Toast.makeText(getApplicationContext(), resourceWord , Toast.LENGTH_LONG).show();

            return false;
        }
        return true;
    }

    //Enter button actions
    //public void enterClick(View view) {
    public void enterClick() {

        //Check to see if there are enough characters selected
        if (!characterNumber.equals(letters)) {
            return;
        }

        //Check to see if this is a new game and if so start the time
        if (newGame) {
            Time = System.currentTimeMillis();
            newGame = false;
        }

        //Check if this is actually a word
        if (!wordIsReal()){return;}

        //Check if hardmode, and if so check to see if all hints are used
        Boolean hardCheck = true;
        String hardCheckString = new String(wordEntry);
        if (hardMode) {
            for (int i = 0; i < hintList.size(); i++) {
                if (!hardCheckString.contains(String.valueOf(hintList.get(i)))) {
                    hardCheck = false;
                }
            }

            //If failed hard check send toast
            if (!hardCheck) {

                Random randHint = new Random();
                int randHintNumber = randHint.nextInt(10);
                String randHintString = "hints_" + String.valueOf(randHintNumber);
                int resourceHint = getResources().getIdentifier(randHintString, "string", "com.example.kwordle");

                Toast.makeText(getApplicationContext(), resourceHint, Toast.LENGTH_LONG).show();
                return;
            }
        }

        //Creete the tuple of the word colors and correct check
        Pair<Integer[], Boolean> thisPlay = Play.enter(this, currentWord.clone(), wordEntry.clone(), wordColorInt, letters, alphabet);
        Integer[] currentWordColor = thisPlay.first;
        correct = thisPlay.second;

        if (!correct) {
            Random randNiceChoice = new Random();
            int randNiceNumberChoice = randNiceChoice.nextInt(4);

            if (randNiceNumberChoice == 0) {
                Random randNice = new Random();
                int randNiceNumber = randNice.nextInt(10);
                String randNiceString = "nice_" + String.valueOf(randNiceNumber);
                int resourceNice = getResources().getIdentifier(randNiceString, "string", "com.example.kwordle");

                Toast.makeText(getApplicationContext(), resourceNice, Toast.LENGTH_LONG).show();
            }
        }


        //Set the colors for entry row
        setRowColor(currentWordColor);

        //Clear hintlist
        hintList = new ArrayList<Character>();
        for (int i = 0; i< letters; i++){
            if (!currentWordColor[i].equals(this.getResources().getColor(R.color.gray))){
                hintList.add(wordEntry[i]);
            }
        }

        //Increment the current try and reset the character number
        currentTry += 1;
        characterNumber = 0;

        //Set the alphabet colors for the entry
        setAlphabetColor();

        //Check if correct or game complete and initiate popup
        checkComplete(correct, currentTry);

    }

    //Check to see if the game is complete, and update the archive, and trigger pop up if it is
    public void checkComplete(boolean correct, Integer currentTry){
        if (correct || currentTry > 5) {
            //Set the finished time
            double finishedTime = (Math.round(System.currentTimeMillis() - Time))/60000.0;
            archiveHandler.addWord(thePlayer, theAnswer, String.valueOf(correct), finishedTime, currentTry, letters);
            archiveHandler.addGame(thePlayer, letters, correct, finishedTime, currentTry);

            startActivity(new Intent(FiveLetterBoard.this,PopCorrect.class));
        }
    }


    //For the current entry set the colors in the table
    public void setRowColor(Integer[] currentWordColor){
        for (int i = 0; i < letters; i++) {
            tableColorsInt[currentTry][i] = currentWordColor[i];
            TextView currentLetter = findViewById(tableIds[currentTry][i]);
            currentLetter.setBackgroundColor(currentWordColor[i]);
        }
    }


    public void initializeWordColor(){
        for (int i = 0; i < letters; i++){
            wordColor[i] = "gray";
            wordColorInt[i] = getResources().getColor(R.color.gray);
        }
    }

    /*
    public void initializeHints(){
        for (int i = 0; i < letters; i++){
            hints[i] = '-';
        }
    }
    */


    public void setAlphabetColor() {
        for (Map.Entry<Character, AlphaWrapper> entry : Alphabets.alphabet.entrySet()  ){
                AlphaWrapper currentValue = entry.getValue();
                TextView currentLetter = findViewById(currentValue.getLetter());
                currentLetter.setBackgroundColor(currentValue.getColor());
        }
    }

    //Get a new word
    public char[] getNewWord(){

        //Get size of starting word list
        Integer sizeOfStartingWords = WordLists.fiveStartingWords.size();

        //If all words are used reset the list
        archiveHandler.resetIfAllUsed(sizeOfStartingWords, thePlayer);

        //While the word chosen has already been used, choose another word
        Boolean wordUsed = true;
        while (wordUsed) {
            int index = (int) (Math.random() * sizeOfStartingWords);
            theAnswer = WordLists.fiveStartingWords.get(index);

            wordUsed = archiveHandler.checkIfUsed(theAnswer);
        }

        //Create the character array to check against
        char[] newWord = new char[letters];
        for (int i = 0; i < letters; i++) {
            newWord[i] = theAnswer.charAt(i);
        }

        //TODO Debugging items
        //theAnswer = "START";
        //char newWord[] = {'S', 'T', 'A', 'R', 'T'};
        //System.out.println("===============================" + theAnswer);

        return newWord;
    }

    //Do for each letter click
    public void letterClick(char let){
        if (characterNumber < 5) {
            wordEntry[characterNumber] = let;
            TextView letterTextView = (TextView) findViewById(tableIds[currentTry][characterNumber]);
            letterTextView.setText(String.valueOf(let));
            characterNumber += 1;
        }

    }

/*
    public void uponletterClick(char let, Integer thisCharacterNumber){
        wordEntry[characterNumber] = let;
        TextView letterTextView = (TextView) findViewById(tableIds[currentTry][thisCharacterNumber]);
        letterTextView.setText(String.valueOf(let));
    }


 */
    /*

    public void updateLetter(SetLetterClicked listener) {
        setLetterClicked = listener;
    }

     */

    /*
    //public void onLetterClick(Character inputLetter, Integer inputCharacterNumber){
    public void onLetterClick() {
        Character thisLetter = setLetterClicked.setletterClicked();
        //letter = setLetterClicked.letter;
        setLetterClicked.setletterClicked(inputLetter, inputCharacterNumber);
        characterNumber = inputCharacterNumber;
        if (characterNumber < 5) {
            letterClick(inputLetter);
        }
    }

     */



    /*
    @Override
    public void onLetterClicked(Character inputLetter, Integer inputCharacterNumber){
        characterNumber = inputCharacterNumber;
        if (characterNumber < 5) {
            letterClick(inputLetter);
        }
    }


     */




  //  public void qClick(View view) {
  //      if (characterNumber < 5) {
  //          letterClick('Q');
  //      }
  //  }

/*
    public void wClick(View view) {
        if (characterNumber < 5) {
            letterClick('W');
        }
    }

    public void eClick(View view) {
        if (characterNumber < 5) {
            letterClick('E');
        }
    }

    public void rClick(View view) {
        if (characterNumber < 5) {
            letterClick('R');
        }
    }

    public void tClick(View view) {
        if (characterNumber < 5) {
            letterClick('T');
        }
    }

    public void yClick(View view) {
        if (characterNumber < 5) {
            letterClick('Y');
        }
    }

    public void uClick(View view) {
        if (characterNumber < 5) {
            letterClick('U');
        }
    }

    public void iClick(View view) {
        if (characterNumber < 5) {
            letterClick('I');
        }
    }

    public void oClick(View view) {
        if (characterNumber < 5) {
            letterClick('O');
        }
    }

    public void pClick(View view) {
        if (characterNumber < 5) {
            letterClick('P');
        }
    }

    public void aClick(View view) {
        if (characterNumber < 5) {
            letterClick('A');
        }
    }

    public void sClick(View view) {
        if (characterNumber < 5) {
            letterClick('S');
        }
    }

    public void dClick(View view) {
        if (characterNumber < 5) {
            letterClick('D');
        }
    }

    public void fClick(View view) {
        if (characterNumber < 5) {
            letterClick('F');
        }
    }

    public void gClick(View view) {
        if (characterNumber < 5) {
            letterClick('G');
        }
    }

    public void hClick(View view) {
        if (characterNumber < 5) {
            letterClick('H');
        }
    }

    public void jClick(View view) {
        if (characterNumber < 5) {
            letterClick('J');
        }
    }

    public void kClick(View view) {
        if (characterNumber < 5) {
            letterClick('K');
        }
    }

    public void lClick(View view) {
        if (characterNumber < 5) {
            letterClick('L');
        }
    }

    public void zClick(View view) {
        if (characterNumber < 5) {
            letterClick('Z');
        }
    }

    public void xClick(View view) {
        if (characterNumber < 5) {
            letterClick('X');
        }
    }

    public void cClick(View view) {
        if (characterNumber < 5) {
            letterClick('C');
        }
    }

    public void vClick(View view) {
        if (characterNumber < 5) {
            letterClick('V');
        }
    }

    public void bClick(View view) {
        if (characterNumber < 5) {
            letterClick('B');
        }
    }

    public void nClick(View view) {
        if (characterNumber < 5) {
            letterClick('N');
        }
    }

    public void mClick(View view) {
        if (characterNumber < 5) {
            letterClick('M');
        }
    }

 */

    /*
    public void deleteClick(View view) {
        if (characterNumber > 0) {
            characterNumber -= 1;
            wordEntry[characterNumber] = ' ';
            TextView letterTextView = (TextView) findViewById(tableIds[currentTry][characterNumber]);
            letterTextView.setText(" ");
        }


    }

     */
/*
    public void qClickNoView() {
        if (characterNumber < 5) {
            letterClick('Q');
        }
    }


 */
    public void deleteClickNoView() {
        if (characterNumber > 0) {
            characterNumber -= 1;
            wordEntry[characterNumber] = ' ';
            TextView letterTextView = (TextView) findViewById(tableIds[currentTry][characterNumber]);
            letterTextView.setText(" ");
        }


    }


    //Goto stats page
    public void stats(View view) {
        startActivity((new Intent(this, Statistics.class)));
    }

    //Goto settings popup
    public void settings(View view) {
        startActivity((new Intent(this, Settings.class)));
    }
}