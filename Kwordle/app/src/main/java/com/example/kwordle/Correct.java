package com.example.kwordle;

//import android.app.Activity;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

public class Correct extends DialogFragment {
    //private static Activity view;

    //private Correct(){}

    public static final String TITLE = "datakey";

    private String thisAnswer;

    public static Correct newInstance(String theAnswer){
        Correct frag = new Correct();
        Bundle args = new Bundle();
        args.putString(TITLE, theAnswer);
        frag.setArguments(args);
        return frag;
        //TextView answerTextView = (TextView) view.findViewById(R.id.answerBox);
        //answerTextView.setText(theAnswer);
        //Correct frag = new Correct();
        //Bundle args = new Bundle();
        //return frag;
    }

    public void setOndoneCorrectClick(String theAnswer) {
        this.thisAnswer = theAnswer;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String mDataRecieved = getArguments().getString(TITLE,"defaultTitle");

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.correct, null);

        TextView mTextView = (TextView) view.findViewById(R.id.answerBox);
        mTextView.setText(mDataRecieved);
        setCancelable(false);


       // @Override
        //public void doneCorrectClick(DialogInterface arg0, int arg1){
        //    dialog.dismiss();
        //}



        builder.setView(view);
        Dialog dialog = builder.create();

        dialog.getWindow().setBackgroundDrawable(
                new ColorDrawable(Color.TRANSPARENT));





        return dialog;

    }

    public void showDialog() {
        DialogFragment newFragment = Correct.newInstance(MainActivity.theAnswer);
        newFragment.show(getFragmentManager(), "dialog");
    }

    //public void doneCorrectClick(View view) {
    //    dialog.dismiss();

    //}
    /*

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        //String thisAnswer = "ASD";
        return inflater.inflate(R.layout.correct, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstantState){
        super.onViewCreated(view, savedInstantState);
        //TextView answerTextView = (TextView) view.findViewById(R.id.answerBox);
        //answerTextView.setText(thisAnswer);


    }





    public void show(FragmentManager fm, int correct) {
    }


     */
}



