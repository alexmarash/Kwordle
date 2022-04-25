package com.example.kwordle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

class TestFragment extends Fragment {

    public TestFragment(){
        super(R.layout.letter_fragment);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.letter_fragment, container, false);

        Bundle newBundle = getArguments();
        int someInt = requireArguments().getInt("this");
        return view;
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //Bundle newBundle = getArguments();
        int someInt = requireArguments().getInt("this");
        int thisint = someInt;
    }

}
