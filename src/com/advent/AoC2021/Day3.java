package com.advent.AoC2021;

import com.advent.PuzzleSolver;

public class Day3 extends PuzzleSolver {

    int[] diff;
    public Day3(String name) {
        super(name);
    }

    @Override
    protected void init() {

    }

    @Override
    protected void computeFirstStep() {
        String diag = getNextInput();
        int d = diag.length();
        diff = new int[d];
        while (!diag.isEmpty()) {
            for (int i=0; i < diag.length(); i++) {
                if (diag.charAt(i) == '1') {
                    diff[i] +=1;
                }
                else {
                    diff[i] -= 1;
                }
            }
            diag = getNextInput();
        }
        StringBuilder gamma = new StringBuilder();
        StringBuilder epsilon = new StringBuilder();
        for (int i=0; i < d; i++) {
            gamma.append(diff[i] > 0 ? "1": "0");
            epsilon.append(diff[i] > 0 ? "0": "1");
        }
        value = Integer.parseInt(gamma.toString(),2)*Integer.parseInt(epsilon.toString(),2);

    }

    @Override
    protected void computeSecondStep() {

    }
}
