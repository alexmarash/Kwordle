package com.example.kwordle;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class LetterEntryFragment extends Fragment {

    public static Character letterClicked;
    public static Integer characterNumber;

    private AdapterView.OnItemSelectedListener listener;

    private UponLetterClick viewModel;

    //public Bundle otherBundle = getArguments();

    /*

    public LetterEntryFragment() {
        super(R.layout.letter_fragment);
        this.otherBundle = getArguments();
    }


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.otherBundle = getArguments();
    }

    */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //View view = inflater.inflate(R.layout.fragment, container, false);
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.letter_fragment, container, false);
        //textView = view.findViewById(R.id.text);
        //textView.setText("first");

        //Bundle newBundle = getArguments();


        return view;
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        Bundle newBundle = getArguments();

        Alphabets alphabet = getArguments().getParcelable("alphabet");
        characterNumber = 0;
        //viewModel = new ViewModelProvider(requireActivity()).get(UponLetterClick.class);
        LetterViewModel model = new ViewModelProvider(requireActivity()).get(LetterViewModel.class);

        /*
        public void setAlphabetColor() {
            for (Map.Entry<Character, Alphabets.alphaWrapper> entry : Alphabets.alphabet.entrySet()  ){
                Alphabets.alphaWrapper currentValue = entry.getValue();
                TextView currentLetter = currentLetter.findViewById();
                TextView currentLetter = findViewById(currentValue.letter);
                currentLetter.setBackgroundColor(currentValue.color);
                currentLetter.setBackgroundColor(currentValue.color);
            }
        }

         */


        /*
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String anotherName = "John Doe";
                model.getCurrentName().setValue(anotherName);
            }
        });

         */

        //TextView qButton = (TextView) view.findViewById(R.id.buttonQ);
        AlphaWrapper qValue = alphabet.get('Q');
        TextView qButton = (TextView) view.findViewById(qValue.getLetter());
        qButton.setBackgroundColor(qValue.getColor());
        qButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.setLiveLetter("q");
            }
            });



        qButton.setOnClickListener(item -> {


                model.setLiveLetter("q");
            });

           /*

            if (characterNumber > 5) {
                LetterPassThrough letterPassThrough = new LetterPassThrough('Q', characterNumber);
                //viewModel.selectItem(letterPassThrough);
                model.setLiveLetter("Q");
            }
        });


            */
    }


    public static LetterEntryFragment newInstance(int param1, Parcelable param2) {
        LetterEntryFragment fragment = new LetterEntryFragment();
        Bundle args = new Bundle();
        args.putInt("Test", param1);
        args.putParcelable("alphabet", param2);
        fragment.setArguments(args);
        return fragment;
    }



}

        /*
        qButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                letterClicked = 'q';
                characterNumber += 1;
                listener.setletterClicked(letterClicked, characterNumber);
                //listener.onLetterClicked(letterClicked, characterNumber);
            }
        });


















    }

    /*
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //View view = inflater.inflate(R.layout.fragment, container, false);
        View view = inflater.inflate(R.layout.letter_fragment, container, false);
        //textView = view.findViewById(R.id.text);
        //textView.setText("first");
        return view;
    }
    */




    /*
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnItemSelectedListener){
            listener = (AdapterView.OnItemSelectedListener) context;
        }
    }

     */

    /*
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

    }

     */

    /*
    public interface OnItemSelectedListener {
        public void onLetterClicked(Character letter, Integer charNumber);
    }

    public interface SetLetterClicked {
        void setletterClicked(Character letter, Integer charNumber);

    }

   @Nullable
   @Override
   public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      View view = inflater.inflate(R.layout.fragment, container, false);
      textView = view.findViewById(R.id.text);
      textView.setText("first");
      return view;


     */

    /*
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.letter_fragment);
    }

     */

    /*
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.letter_fragment, container, false);

        return view;
    }


     */

    /*
    @Override
    //public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    //public View onViewCreated(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

        //View view = inflater.inflate(R.layout.letter_fragment, container, false);

        Alphabets alphabet = savedInstanceState.getParcelable("alphabet");

        characterNumber = 0;

        viewModel = new ViewModelProvider(requireActivity()).get(UponLetterClick.class);


        /*
        public void setAlphabetColor() {
            for (Map.Entry<Character, Alphabets.alphaWrapper> entry : Alphabets.alphabet.entrySet()  ){
                Alphabets.alphaWrapper currentValue = entry.getValue();
                TextView currentLetter = currentLetter.findViewById();
                TextView currentLetter = findViewById(currentValue.letter);
                currentLetter.setBackgroundColor(currentValue.color);
                currentLetter.setBackgroundColor(currentValue.color);
            }
        }

         */


        /*

        //TextView qButton = (TextView) view.findViewById(R.id.buttonQ);
        Alphabets.alphaWrapper qValue = alphabet.get('Q');
        TextView qButton = (TextView) view.findViewById(qValue.letter);
        qButton.setBackgroundColor(qValue.color);
        qButton.setOnClickListener(item -> {
            if (characterNumber > 5) {
                LetterPassThrough letterPassThrough = new LetterPassThrough('Q', characterNumber);
                viewModel.selectItem(letterPassThrough);
            }
        });

        /*
        qButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                letterClicked = 'q';
                characterNumber += 1;
                listener.setletterClicked(letterClicked, characterNumber);
                //listener.onLetterClicked(letterClicked, characterNumber);
            }
        });

         */





        //return view;
    //}






    /*

    //Do for each letter click
    public void letterClick(char let){
        wordEntry[characterNumber] = let;
        TextView letterTextView = (TextView) findViewById(tableIds[currentTry][characterNumber]);
        letterTextView.setText(String.valueOf(let));
        characterNumber += 1;

    }






    public void qClick(View view) {
        if (characterNumber < 5) {
            letterClick('Q');
        }
    }


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

    public void deleteClick(View view) {
        if (characterNumber > 0) {
            characterNumber -= 1;
            wordEntry[characterNumber] = ' ';
            TextView letterTextView = (TextView) findViewById(tableIds[currentTry][characterNumber]);
            letterTextView.setText(" ");
        }
    }


     */



