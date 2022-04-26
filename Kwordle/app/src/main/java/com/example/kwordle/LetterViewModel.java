package com.example.kwordle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LetterViewModel extends ViewModel {
    private MutableLiveData<String> thisLetter;

    public LiveData<String> getLetter() {
        if (thisLetter == null) {
            thisLetter = new MutableLiveData<String>();
            loadLetter();
        }
        return thisLetter;
    }

    public void loadLetter() {

    }

    public void setLiveLetter(String letter){
        thisLetter.setValue(letter);
    }


}
