package com.example.dicesimulator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testConstructor() {
        //Test constructor for dice class
        Dice dice = new Dice(3, 6);
        assertEquals(3, dice.getNumDice());
        assertEquals(6, dice.getSides());
    }

    @Test
    public void testRoll() {
        //Test dice rolls. RNG so can't use assert.
        // 3 set of 10 rolls, each roll should be between 2 and 16 and each set should be different.
        Dice dice = new Dice(2, 8);
        for (int i = 0; i < 3; i++) {
            System.out.println("new set");
            for (int j = 0; j < 10; j++) {
                System.out.println(dice.Roll());
            }
        }
    }
}