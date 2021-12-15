package com.advent.AoC2021;

import com.advent.PuzzleSolver;

import java.util.*;

public class Day7 extends PuzzleSolver {
    public Day7() {
        super("src/inputs/fuel.txt");
    }

    @Override
    protected void init() {}

    @Override
    protected void computeFirstStep() {
        String input = getNextInput();
        List<Integer> l = new ArrayList<Integer>();
        for (String s : input.split(",")) {
            l.add(Integer.parseInt(s));
        }
        Collections.sort(l);
        int med = l.get(l.size()/2);
        for (int crab : l) {
            value += Math.abs(crab-med);
        }

    }

    @Override
    protected void computeSecondStep() {

    }
}
