package com.advent.AoC2020;

public class Day15 {

    int[] numbers;

    public Day15() {
        numbers = new int[100000000];
        numbers[0] = 4;
        numbers[1] = 5;
        numbers[2] = 6;
        numbers[9] = 2;
        numbers[11] = 3;
        numbers[20] = 1;
        int turn = 7;
        int previous = 0;
        int temp;
        while (turn < 30000000) {


                if (numbers[previous] == 0) {
                    temp = 0;
                }
                else {

                    temp = turn - numbers[previous];
                }
            numbers[previous] = turn;
                previous = temp;
            turn++;
            System.err.println(turn + " " + previous);

        }

    }
}
