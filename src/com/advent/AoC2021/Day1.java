package com.advent.AoC2021;

import com.advent.PuzzleSolver;

public class Day1 extends PuzzleSolver {

    public Day1(String name) {
        super(name);
    }

    @Override
    protected void init() {}

    @Override
    protected void computeFirstStep() {
        int previous = Integer.parseInt(getNextInput());
        String depth = getNextInput();
        while (!depth.isEmpty()) {
            int d = Integer.parseInt(depth);
            if (d > previous) {
                value++;
            }
            previous = d;
            depth = getNextInput();
        }
    }

    @Override
    protected void computeSecondStep() {
        int first = Integer.parseInt(getNextInput());
        int second = Integer.parseInt(getNextInput());
        int third = Integer.parseInt(getNextInput());
        String depth = getNextInput();
        while (!depth.isEmpty()) {
            int d = Integer.parseInt(depth);
            if (d > first) {
                value += 1;
            }
            first = second;
            second = third;
            third = d;
            depth = getNextInput();
        }
    }
}