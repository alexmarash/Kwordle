package com.example.kwordle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UponLetterClick extends ViewModel {
    private final MutableLiveData<LetterPassThrough> selectedItem = new MutableLiveData<LetterPassThrough>();
    public void selectItem(LetterPassThrough letterPassThrough){
        selectedItem.setValue(letterPassThrough);
    }
    public LiveData<LetterPassThrough> getSelectedItem(){
        return selectedItem;
    }

}
