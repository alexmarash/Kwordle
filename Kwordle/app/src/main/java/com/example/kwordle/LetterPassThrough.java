package com.example.kwordle;

public class LetterPassThrough {

    Character letter;
    Integer characterNumber;

    public LetterPassThrough (char letter, Integer characterNumber){
        this.letter = letter;
        this.characterNumber = characterNumber;
    }

    public char getLetter(){
        return letter;
    }

    public Integer getCharacterNumber(){
        return characterNumber;
    }

}
