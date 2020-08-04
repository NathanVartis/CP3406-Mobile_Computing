package com.example.dicesimulator;

import java.util.Random;

class Dice {

    private final int numDice;
    private final int sides;

    public int getNumDice() {
        return numDice;
    }

    public int getSides() {
        return sides;
    }

    public Dice(int numDice, int sides) {
        this.numDice = numDice;
        this.sides = sides;
    }

    public int Roll() {
        //Uses Random methods to simulate dice roll for given dice set.
        int rollTotal = 0;
        Random random = new Random();
        for (int i = 0; i < numDice; i++) {
            rollTotal += (random.nextInt(sides) + 1);
        }
        return rollTotal;
    }
}
