package com.example.kwordle;

public class PlayedModal {

    private String player;
    private Integer letters;
    private Integer played;
    private Integer amountWon;
    private Integer currentStreak;
    private Integer maxStreak;
    private double minTime;
    private double maxTime;


    //Constructor
    public PlayedModal(String player, Integer letters, Integer played, Integer amountWon, Integer currentStreak, Integer maxStreak,
                       double minTime, double maxTime){
        this.player = player;
        this.letters = letters;
        this.played = played;
        this.amountWon = amountWon;
        this.currentStreak = currentStreak;
        this.maxStreak = maxStreak;
        this.minTime = minTime;
        this.maxTime = maxTime;

    }
    /*
    //Constructor
    public PlayedModal(Integer letters, Integer played, Integer amountWon, Integer currentStreak, Integer maxStreak,
                       double minTime, double maxTime){
        this.letters = letters;
        this.played = played;
        this.amountWon = amountWon;
        this.currentStreak = currentStreak;
        this.maxStreak = maxStreak;
        this.minTime = minTime;
        this.maxTime = maxTime;

    }
    */




    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public Integer getLetters() {
        return letters;
    }

    public void setLetters(Integer letters) {
        this.letters = letters;
    }

    public Integer getPlayed() {
        return played;
    }

    public void setPlayed(Integer played) {
        this.played = played;
    }

    public Integer getCurrentStreak() {
        return currentStreak;
    }

    public void setCurrentStreak(Integer currentStreak) {
        this.currentStreak = currentStreak;
    }

    public Integer getMaxStreak() {
        return maxStreak;
    }

    public void setMaxStreak(Integer maxStreak) {
        this.maxStreak = maxStreak;
    }

    public double getMinTime() {
        return minTime;
    }

    public void setMinTime(double minTime) {
        this.minTime = minTime;
    }

    public double getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(double maxTime) {
        this.maxTime = maxTime;
    }

    public Integer getAmountWon() {
        return amountWon;
    }

    public void setAmountWon(Integer amountWon) {
        this.amountWon = amountWon;
    }
}
