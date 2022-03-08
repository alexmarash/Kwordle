package com.example.kwordle;

public class PlayedGameModal {

    private String player;
    private Integer letters;
    private Integer played;
    private Integer amountWon;
    private Integer currentStreak;
    private Integer maxStreak;
    private double minTime;
    private double maxTime;
    private Integer oneWon;
    private Integer twoWon;
    private Integer threeWon;
    private Integer fourWon;
    private Integer fiveWon;
    private Integer sixWon;
    private Integer sevenWon;
    private Integer eightWon;
    private Integer nineWon;
    private Integer tenWon;


    //Constructor
    public PlayedGameModal(String player, Integer played, Integer amountWon, Integer currentStreak, Integer maxStreak,
                           double minTime, double maxTime, Integer letters, Integer oneWon, Integer twoWon, Integer threeWon, Integer fourWon,
                            Integer fiveWon, Integer sixWon, Integer sevenWon, Integer eightWon, Integer nineWon, Integer tenWon){
        this.player = player;
        this.played = played;
        this.amountWon = amountWon;
        this.currentStreak = currentStreak;
        this.maxStreak = maxStreak;
        this.minTime = minTime;
        this.maxTime = maxTime;
        this.letters = letters;
        this.oneWon = oneWon;
        this.twoWon = twoWon;
        this.threeWon = threeWon;
        this.fourWon = fourWon;
        this.fiveWon = fiveWon;
        this.sixWon = sixWon;
        this.sevenWon = sevenWon;
        this.eightWon = eightWon;
        this.nineWon = nineWon;
        this.tenWon = tenWon;

    }

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

    public Integer getOneWon() {
        return oneWon;
    }

    public void setOneWon(Integer oneWon) {
        this.oneWon = oneWon;
    }

    public Integer getTwoWon() {
        return twoWon;
    }

    public void setTwoWon(Integer twoWon) {
        this.twoWon = twoWon;
    }

    public Integer getThreeWon() {
        return threeWon;
    }

    public void setThreeWon(Integer threeWon) {
        this.threeWon = threeWon;
    }

    public Integer getFourWon() {
        return fourWon;
    }

    public void setFourWon(Integer fourWon) {
        this.fourWon = fourWon;
    }

    public Integer getFiveWon() {
        return fiveWon;
    }

    public void setFiveWon(Integer fiveWon) {
        this.fiveWon = fiveWon;
    }

    public Integer getSixWon() {
        return sixWon;
    }

    public void setSixWon(Integer sixWon) {
        this.sixWon = sixWon;
    }

    public Integer getSevenWon() {
        return sevenWon;
    }

    public void setSevenWon(Integer sevenWon) {
        this.sevenWon = sevenWon;
    }

    public Integer getEightWon() {
        return eightWon;
    }

    public void setEightWon(Integer eightWon) {
        this.eightWon = eightWon;
    }

    public Integer getNineWon() {
        return nineWon;
    }

    public void setNineWon(Integer nineWon) {
        this.nineWon = nineWon;
    }

    public Integer getTenWon() {
        return tenWon;
    }

    public void setTenWon(Integer tenWon) {
        this.tenWon = tenWon;
    }
}
