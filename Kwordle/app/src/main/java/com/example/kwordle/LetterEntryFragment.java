package com.example.kwordle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class LetterEntryFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Return the view
        return inflater.inflate(R.layout.letter_fragment, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //Pull alphabet from game
        assert getArguments() != null;
        Alphabets alphabet = (Alphabets) getArguments().getSerializable("alphabet");

        //Create model to pass information back to activity
        LetterViewModel model = new ViewModelProvider(requireActivity()).get(LetterViewModel.class);

        //For each letter, pull the and set the letter color, and create a listener to send click to model
        AlphaWrapper qValue = alphabet.get('Q');
        TextView qButton = (TextView) view.findViewById(qValue.getLetter());
        qButton.setBackgroundColor(qValue.getColor());
        qButton.setOnClickListener(item -> model.setLiveLetter("Q"));

        AlphaWrapper wValue = alphabet.get('W');
        TextView wButton = (TextView) view.findViewById(wValue.getLetter());
        wButton.setBackgroundColor(wValue.getColor());
        wButton.setOnClickListener(item -> model.setLiveLetter("W"));

        AlphaWrapper eValue = alphabet.get('E');
        TextView eButton = (TextView) view.findViewById(eValue.getLetter());
        eButton.setBackgroundColor(eValue.getColor());
        eButton.setOnClickListener(item -> model.setLiveLetter("E"));

        AlphaWrapper rValue = alphabet.get('R');
        TextView rButton = (TextView) view.findViewById(rValue.getLetter());
        rButton.setBackgroundColor(rValue.getColor());
        rButton.setOnClickListener(item -> model.setLiveLetter("R"));

        AlphaWrapper tValue = alphabet.get('T');
        TextView tButton = (TextView) view.findViewById(tValue.getLetter());
        tButton.setBackgroundColor(tValue.getColor());
        tButton.setOnClickListener(item -> model.setLiveLetter("T"));

        AlphaWrapper yValue = alphabet.get('Y');
        TextView yButton = (TextView) view.findViewById(yValue.getLetter());
        yButton.setBackgroundColor(yValue.getColor());
        yButton.setOnClickListener(item -> model.setLiveLetter("Y"));

        AlphaWrapper uValue = alphabet.get('U');
        TextView uButton = (TextView) view.findViewById(uValue.getLetter());
        uButton.setBackgroundColor(uValue.getColor());
        uButton.setOnClickListener(item -> model.setLiveLetter("U"));

        AlphaWrapper iValue = alphabet.get('I');
        TextView iButton = (TextView) view.findViewById(iValue.getLetter());
        iButton.setBackgroundColor(iValue.getColor());
        iButton.setOnClickListener(item -> model.setLiveLetter("I"));

        AlphaWrapper oValue = alphabet.get('O');
        TextView oButton = (TextView) view.findViewById(oValue.getLetter());
        oButton.setBackgroundColor(oValue.getColor());
        oButton.setOnClickListener(item -> model.setLiveLetter("O"));
        AlphaWrapper pValue = alphabet.get('P');
        TextView pButton = (TextView) view.findViewById(pValue.getLetter());
        pButton.setBackgroundColor(pValue.getColor());
        pButton.setOnClickListener(item -> model.setLiveLetter("P"));

        AlphaWrapper aValue = alphabet.get('A');
        TextView aButton = (TextView) view.findViewById(aValue.getLetter());
        aButton.setBackgroundColor(aValue.getColor());
        aButton.setOnClickListener(item -> model.setLiveLetter("A"));

        AlphaWrapper sValue = alphabet.get('S');
        TextView sButton = (TextView) view.findViewById(sValue.getLetter());
        sButton.setBackgroundColor(sValue.getColor());
        sButton.setOnClickListener(item -> model.setLiveLetter("S"));

        AlphaWrapper dValue = alphabet.get('D');
        TextView dButton = (TextView) view.findViewById(dValue.getLetter());
        dButton.setBackgroundColor(dValue.getColor());
        dButton.setOnClickListener(item -> model.setLiveLetter("D"));

        AlphaWrapper fValue = alphabet.get('F');
        TextView fButton = (TextView) view.findViewById(fValue.getLetter());
        fButton.setBackgroundColor(fValue.getColor());
        fButton.setOnClickListener(item -> model.setLiveLetter("F"));

        AlphaWrapper gValue = alphabet.get('G');
        TextView gButton = (TextView) view.findViewById(gValue.getLetter());
        gButton.setBackgroundColor(gValue.getColor());
        gButton.setOnClickListener(item -> model.setLiveLetter("G"));

        AlphaWrapper hValue = alphabet.get('H');
        TextView hButton = (TextView) view.findViewById(hValue.getLetter());
        hButton.setBackgroundColor(hValue.getColor());
        hButton.setOnClickListener(item -> model.setLiveLetter("H"));

        AlphaWrapper jValue = alphabet.get('J');
        TextView jButton = (TextView) view.findViewById(jValue.getLetter());
        jButton.setBackgroundColor(jValue.getColor());
        jButton.setOnClickListener(item -> model.setLiveLetter("J"));

        AlphaWrapper kValue = alphabet.get('K');
        TextView kButton = (TextView) view.findViewById(kValue.getLetter());
        kButton.setBackgroundColor(kValue.getColor());
        kButton.setOnClickListener(item -> model.setLiveLetter("Kl"));

        AlphaWrapper lValue = alphabet.get('L');
        TextView lButton = (TextView) view.findViewById(lValue.getLetter());
        lButton.setBackgroundColor(lValue.getColor());
        lButton.setOnClickListener(item -> model.setLiveLetter("L"));

        AlphaWrapper zValue = alphabet.get('Z');
        TextView zButton = (TextView) view.findViewById(zValue.getLetter());
        zButton.setBackgroundColor(zValue.getColor());
        zButton.setOnClickListener(item -> model.setLiveLetter("Z"));

        AlphaWrapper xValue = alphabet.get('X');
        TextView xButton = (TextView) view.findViewById(xValue.getLetter());
        xButton.setBackgroundColor(xValue.getColor());
        xButton.setOnClickListener(item -> model.setLiveLetter("X"));

        AlphaWrapper cValue = alphabet.get('C');
        TextView cButton = (TextView) view.findViewById(cValue.getLetter());
        cButton.setBackgroundColor(cValue.getColor());
        cButton.setOnClickListener(item -> model.setLiveLetter("C"));

        AlphaWrapper vValue = alphabet.get('V');
        TextView vButton = (TextView) view.findViewById(vValue.getLetter());
        vButton.setBackgroundColor(vValue.getColor());
        vButton.setOnClickListener(item -> model.setLiveLetter("V"));

        AlphaWrapper bValue = alphabet.get('B');
        TextView bButton = (TextView) view.findViewById(bValue.getLetter());
        bButton.setBackgroundColor(bValue.getColor());
        bButton.setOnClickListener(item -> model.setLiveLetter("B"));

        AlphaWrapper nValue = alphabet.get('N');
        TextView nButton = (TextView) view.findViewById(nValue.getLetter());
        nButton.setBackgroundColor(nValue.getColor());
        nButton.setOnClickListener(item -> model.setLiveLetter("N"));

        AlphaWrapper mValue = alphabet.get('M');
        TextView mButton = (TextView) view.findViewById(mValue.getLetter());
        mButton.setBackgroundColor(mValue.getColor());
        mButton.setOnClickListener(item -> model.setLiveLetter("M"));

        TextView deleteButton = (TextView) view.findViewById(R.id.buttonDelete);
        deleteButton.setOnClickListener(item -> model.setLiveLetter("delete"));

        TextView enterButton = (TextView) view.findViewById(R.id.buttonEnter);
        enterButton.setOnClickListener(item -> model.setLiveLetter("enter"));

    }
}
