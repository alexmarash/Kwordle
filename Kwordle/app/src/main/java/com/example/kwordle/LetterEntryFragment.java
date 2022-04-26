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

    //public static Character letterClicked;
    //public static Integer characterNumber;

    //private AdapterView.OnItemSelectedListener listener;

    //private UponLetterClick viewModel;

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
        //Alphabets alphabet = getArguments().getParcelable("alphabet");
        Alphabets alphabet = (Alphabets) getArguments().getSerializable("alphabet");




        LetterViewModel model = new ViewModelProvider(requireActivity()).get(LetterViewModel.class);

        AlphaWrapper qValue = alphabet.get('Q');
        TextView qButton = (TextView) view.findViewById(qValue.getLetter());
        qButton.setBackgroundColor(qValue.getColor());
        qButton.setOnClickListener(item -> {
            model.setLiveLetter("Q");
        });
        /*
        qButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.setLiveLetter("Q");
            }
            });
        */

        AlphaWrapper wValue = alphabet.get('W');
        TextView wButton = (TextView) view.findViewById(wValue.getLetter());
        wButton.setBackgroundColor(wValue.getColor());
        wButton.setOnClickListener(item -> {
            model.setLiveLetter("W");
        });

        AlphaWrapper eValue = alphabet.get('E');
        TextView eButton = (TextView) view.findViewById(eValue.getLetter());
        eButton.setBackgroundColor(eValue.getColor());
        eButton.setOnClickListener(item -> {
            model.setLiveLetter("E");
        });

        AlphaWrapper rValue = alphabet.get('R');
        TextView rButton = (TextView) view.findViewById(rValue.getLetter());
        rButton.setBackgroundColor(rValue.getColor());
        rButton.setOnClickListener(item -> {
            model.setLiveLetter("R");
        });

        AlphaWrapper tValue = alphabet.get('T');
        TextView tButton = (TextView) view.findViewById(tValue.getLetter());
        tButton.setBackgroundColor(tValue.getColor());
        tButton.setOnClickListener(item -> {
            model.setLiveLetter("T");
        });

        AlphaWrapper yValue = alphabet.get('Y');
        TextView yButton = (TextView) view.findViewById(yValue.getLetter());
        yButton.setBackgroundColor(yValue.getColor());
        yButton.setOnClickListener(item -> {
            model.setLiveLetter("Y");
        });

        AlphaWrapper uValue = alphabet.get('U');
        TextView uButton = (TextView) view.findViewById(uValue.getLetter());
        uButton.setBackgroundColor(uValue.getColor());
        uButton.setOnClickListener(item -> {
            model.setLiveLetter("U");
        });

        AlphaWrapper iValue = alphabet.get('I');
        TextView iButton = (TextView) view.findViewById(iValue.getLetter());
        iButton.setBackgroundColor(iValue.getColor());
        iButton.setOnClickListener(item -> {
            model.setLiveLetter("I");
        });

        AlphaWrapper oValue = alphabet.get('O');
        TextView oButton = (TextView) view.findViewById(oValue.getLetter());
        oButton.setBackgroundColor(oValue.getColor());
        oButton.setOnClickListener(item -> {
            model.setLiveLetter("O");
        });
        AlphaWrapper pValue = alphabet.get('P');
        TextView pButton = (TextView) view.findViewById(pValue.getLetter());
        pButton.setBackgroundColor(pValue.getColor());
        pButton.setOnClickListener(item -> {
            model.setLiveLetter("P");
        });

        AlphaWrapper aValue = alphabet.get('A');
        TextView aButton = (TextView) view.findViewById(aValue.getLetter());
        aButton.setBackgroundColor(aValue.getColor());
        aButton.setOnClickListener(item -> {
            model.setLiveLetter("A");
        });

        AlphaWrapper sValue = alphabet.get('S');
        TextView sButton = (TextView) view.findViewById(sValue.getLetter());
        sButton.setBackgroundColor(sValue.getColor());
        sButton.setOnClickListener(item -> {
            model.setLiveLetter("S");
        });

        AlphaWrapper dValue = alphabet.get('D');
        TextView dButton = (TextView) view.findViewById(dValue.getLetter());
        dButton.setBackgroundColor(dValue.getColor());
        dButton.setOnClickListener(item -> {
            model.setLiveLetter("D");
        });

        AlphaWrapper fValue = alphabet.get('F');
        TextView fButton = (TextView) view.findViewById(fValue.getLetter());
        fButton.setBackgroundColor(fValue.getColor());
        fButton.setOnClickListener(item -> {
            model.setLiveLetter("F");
        });

        AlphaWrapper gValue = alphabet.get('G');
        TextView gButton = (TextView) view.findViewById(gValue.getLetter());
        gButton.setBackgroundColor(gValue.getColor());
        gButton.setOnClickListener(item -> {
            model.setLiveLetter("G");
        });

        AlphaWrapper hValue = alphabet.get('H');
        TextView hButton = (TextView) view.findViewById(hValue.getLetter());
        hButton.setBackgroundColor(hValue.getColor());
        hButton.setOnClickListener(item -> {
            model.setLiveLetter("H");
        });

        AlphaWrapper jValue = alphabet.get('J');
        TextView jButton = (TextView) view.findViewById(jValue.getLetter());
        jButton.setBackgroundColor(jValue.getColor());
        jButton.setOnClickListener(item -> {
            model.setLiveLetter("J");
        });

        AlphaWrapper kValue = alphabet.get('K');
        TextView kButton = (TextView) view.findViewById(kValue.getLetter());
        kButton.setBackgroundColor(kValue.getColor());
        kButton.setOnClickListener(item -> {
            model.setLiveLetter("Kl");
        });

        AlphaWrapper lValue = alphabet.get('L');
        TextView lButton = (TextView) view.findViewById(lValue.getLetter());
        lButton.setBackgroundColor(lValue.getColor());
        lButton.setOnClickListener(item -> {
            model.setLiveLetter("L");
        });

        AlphaWrapper zValue = alphabet.get('Z');
        TextView zButton = (TextView) view.findViewById(zValue.getLetter());
        zButton.setBackgroundColor(zValue.getColor());
        zButton.setOnClickListener(item -> {
            model.setLiveLetter("Z");
        });

        AlphaWrapper xValue = alphabet.get('X');
        TextView xButton = (TextView) view.findViewById(xValue.getLetter());
        xButton.setBackgroundColor(xValue.getColor());
        xButton.setOnClickListener(item -> {
            model.setLiveLetter("X");
        });

        AlphaWrapper cValue = alphabet.get('C');
        TextView cButton = (TextView) view.findViewById(cValue.getLetter());
        cButton.setBackgroundColor(cValue.getColor());
        cButton.setOnClickListener(item -> {
            model.setLiveLetter("C");
        });

        AlphaWrapper vValue = alphabet.get('V');
        TextView vButton = (TextView) view.findViewById(vValue.getLetter());
        vButton.setBackgroundColor(vValue.getColor());
        vButton.setOnClickListener(item -> {
            model.setLiveLetter("V");
        });

        AlphaWrapper bValue = alphabet.get('B');
        TextView bButton = (TextView) view.findViewById(bValue.getLetter());
        bButton.setBackgroundColor(bValue.getColor());
        bButton.setOnClickListener(item -> {
            model.setLiveLetter("B");
        });

        AlphaWrapper nValue = alphabet.get('N');
        TextView nButton = (TextView) view.findViewById(nValue.getLetter());
        nButton.setBackgroundColor(nValue.getColor());
        nButton.setOnClickListener(item -> {
            model.setLiveLetter("N");
        });

        AlphaWrapper mValue = alphabet.get('M');
        TextView mButton = (TextView) view.findViewById(mValue.getLetter());
        mButton.setBackgroundColor(mValue.getColor());
        mButton.setOnClickListener(item -> {
            model.setLiveLetter("M");
        });

        TextView deleteButton = (TextView) view.findViewById(R.id.buttonDelete);
        deleteButton.setOnClickListener(item -> {
            model.setLiveLetter("delete");
        });

        TextView enterButton = (TextView) view.findViewById(R.id.buttonEnter);
        enterButton.setOnClickListener(item -> {
            model.setLiveLetter("enter");
        });

    }

    /*
    public static LetterEntryFragment newInstance(int param1, Parcelable param2) {
        LetterEntryFragment fragment = new LetterEntryFragment();
        Bundle args = new Bundle();
        args.putInt("Test", param1);
        args.putParcelable("alphabet", param2);
        fragment.setArguments(args);
        return fragment;
    }

     */



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



