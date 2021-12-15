package com.advent.AoC2021;

import com.advent.PuzzleSolver;
import java.util.*;

public class Day6 extends PuzzleSolver {

    String[] initialFishes ;
    public Day6() {
        super("src/inputs/fish.txt");
    }

    @Override
    protected void init() {

    }

    @Override
    protected void computeFirstStep() {
        List<Fish> fishes = new ArrayList<Fish>();
        initialFishes = getNextInput().split(",");
        for (String s : initialFishes) {
            fishes.add(new Fish(Integer.parseInt(s)));
        }
        int n = 80;
        while (n > 0) {
            int length = fishes.size();
            for (int i = 0; i < length; i++) {
                Fish f = fishes.get(i);
                if (f.count == 0) {
                    fishes.add(new Fish(8));
                }
                f.live();
            }
            n--;
        }
        value = fishes.size();
    }

    @Override
    protected void computeSecondStep() {
        long[] fishes = new long[9];
        value = 0;
        for (String s : initialFishes) {
            fishes[Integer.parseInt(s)] += 1;
        }
        long newborns = 0;
        int n = 256;
        while (n > 0) {
            newborns = fishes[0];
            fishes[0] = fishes[1];
            fishes[1] = fishes[2];
            fishes[2] = fishes[3];
            fishes[3] = fishes[4];
            fishes[4] = fishes[5];
            fishes[5] = fishes[6];
            fishes[6] = fishes[7] + newborns;
            fishes[7] = fishes[8];
            fishes[8] = newborns;
            n--;
        }
        for (int i = 0; i < 9; i++) {
            value+=fishes[i];
        }
    }
}
