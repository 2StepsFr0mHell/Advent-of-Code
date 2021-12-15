package com.advent.AoC2021;

import com.advent.PuzzleSolver;

import java.util.*;

public class Day15 extends PuzzleSolver {

    Grid g;

    Grid g2;

    public Day15() {
        super("src/inputs/risks.txt");
    }

    @Override
    protected void init() {
        List<String> input = new ArrayList<String>();
        String next = getNextInput();
        while (!next.isEmpty()) {
            input.add(next);
            next = getNextInput();
        }
        g = new Grid(input.get(0).length(), input.size());
        g.init(input);
        g2 = new Grid(input.get(0).length()*5, input.size()*5);
        g2.init5(input);
    }

    @Override
    protected void computeFirstStep() {
        g.dijkstra(g.get(0,0), g.getBottomRight());
        value = g.getBottomRight().dist;
    }

    @Override
    protected void computeSecondStep() {
        g2.dijkstra(g2.get(0,0), g2.getBottomRight());
        value = g2.getBottomRight().dist;
    }

}
