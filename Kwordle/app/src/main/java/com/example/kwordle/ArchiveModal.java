package com.example.kwordle;

public class ArchiveModal {

    //Variables to recieve from Archive
    private String theAnswer;
    private String ifCorrect;
    private double finishedTime;
    private Integer amountOftrys;
    private Integer amountOfLetters;


    public String getTheAnswer() {
        return theAnswer;
    }

    public void setTheAnswer(String theAnswer) {
        this.theAnswer = theAnswer;
    }

    public String getIfCorrect() {
        return ifCorrect;
    }

    public void setIfCorrect(String ifCorrect) {
        this.ifCorrect = ifCorrect;
    }

    public double getFinishedTime() {
        return finishedTime;
    }

    public void setFinishedTime(double finishedTime) {
        this.finishedTime = finishedTime;
    }

    public Integer getAmountOftrys() {
        return amountOftrys;
    }

    public void setAmountOftrys(Integer amountOftrys) {
        this.amountOftrys = amountOftrys;
    }

    public Integer getAmountOfLetters() {
        return amountOfLetters;
    }

    public void setAmountOfLetters(Integer amountOfLetters) {
        this.amountOfLetters = amountOfLetters;
    }

    //Constructor
    public ArchiveModal(String theAnswer, String ifCorrect, double finishedTime, Integer amountOftrys, Integer amountOfLetters){
        this.theAnswer = theAnswer;
        this.amountOfLetters = amountOfLetters;
        this.amountOftrys = amountOftrys;
        this.finishedTime = finishedTime;
        this.ifCorrect = ifCorrect;
    }

}
