package com.adaptionsoft.games.uglytrivia;

class Player {
    private final String name;
    private int place = 0;
    private int purse = 0;
    private boolean inPenaltyBox = false;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getPlace() {
        return place;
    }

    public void move(int roll, int boardSize) {
        place = (place + roll) % boardSize;
    }

    public int getPurse() {
        return purse;
    }

    public void incrementPurse() {
        purse++;
    }

    public boolean hasWon(int winningCoins) {
        return purse >= winningCoins;
    }

    public boolean isInPenaltyBox() {
        return inPenaltyBox;
    }

    public void setInPenaltyBox(boolean inPenaltyBox) {
        this.inPenaltyBox = inPenaltyBox;
    }
}
