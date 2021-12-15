package com.advent.AoC2021;

import com.advent.PuzzleSolver;

public class Day2 extends PuzzleSolver {

    public Day2(String name) {
        super(name);
    }

    @Override
    protected void init() {

    }

    @Override
    protected void computeFirstStep() {

        Position p = new Position(0,0);
        String orders = getNextInput();
        while (!orders.isEmpty()) {
            String[] parts = orders.split(" ");
            p.move(Integer.parseInt(parts[1]), parts[0]);
            orders = getNextInput();
        }
        value = p.x*p.y;
    }

    @Override
    protected void computeSecondStep() {

        Position p = new Position(0,0);
        String orders = getNextInput();
        while (!orders.isEmpty()) {
            String[] parts = orders.split(" ");
            p.submove(Integer.parseInt(parts[1]), parts[0]);
            orders = getNextInput();
        }
        value = p.x*p.y;
    }
}
