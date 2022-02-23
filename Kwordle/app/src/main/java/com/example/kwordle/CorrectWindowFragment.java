package com.example.kwordle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class CorrectWindowFragment extends Fragment {
    private static final String correctAnswer = MainActivity.theAnswer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TextView answerButton = getView().findViewById(R.id.answerBox);
        answerButton.setText(correctAnswer);

        return null;
    }

}
