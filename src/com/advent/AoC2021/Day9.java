package com.advent.AoC2021;

import com.advent.PuzzleSolver;
import java.util.*;

public class Day9 extends PuzzleSolver {
    Cell[][] cells;
    int L, H;

    public Day9() {
        super("src/inputs/lava.txt");

    }

    @Override
    protected void init() {
        List<String> input = new ArrayList<String>();
        String next = getNextInput();
        while (!next.isEmpty()) {
            input.add(next);
            next = getNextInput();
        }
        L = input.get(0).length();
        H = input.size();
        cells = new Cell[L][H];
        initGrid(input);
        buildAdjacents();
    }

    private void initGrid(List<String> input) {
        for (int y = 0; y < H; y++) {
            String row = input.get(y);
            for (int x = 0; x < L; x++) {
                cells[x][y] = new Cell(Character.getNumericValue(row.charAt(x)));
            }
        }
    }

    private void buildAdjacents() {
        for (int y = 0; y < H; y++) {
            for (int x = 0; x < L; x++) {
                if (x > 0) {
                    cells[x][y].addAdjacent(cells[x - 1][y]);
                }
                if (y > 0) {
                    cells[x][y].addAdjacent(cells[x][y - 1]);
                }
            }
        }
    }

    private void floodFill() {
        List<Integer> sizes = new ArrayList<Integer>();
        for (int y = 0; y < H; y++) {
            for (int x = 0; x < L; x++) {
                Cell c = cells[x][y];
                if (!c.isVisited && !c.isWall) {
                    Queue<Cell> queue = new LinkedList<Cell>();
                    queue.add(c);
                    c.visit();
                    List<Cell> lake = new ArrayList<Cell>();
                    lake.add(c);
                    while (!queue.isEmpty()) {
                        Cell next = queue.poll();
                        for (Cell adj : next.adjacents) {
                            if (!adj.isWall && !adj.isVisited) {
                                queue.add(adj);
                                lake.add(adj);
                                adj.visit();
                            }
                        }
                    }
                    sizes.add(lake.size());
                    System.err.println("size basin "+lake.size());
                }
            }
        }
        Collections.sort(sizes, Collections.reverseOrder());
        value = sizes.get(0) * sizes.get(1) * sizes.get(2);
    }

    @Override
    protected void computeFirstStep() {
        for (int y = 0; y < H; y++) {
            for (int x = 0; x < L; x++) {

                Cell c = cells[x][y];
                //System.err.println("checking "+c.n+" "+c.adjacents.size());
                boolean isSmaller = true;
                for (Cell adj : c.adjacents) {
                    if (adj.n <= c.n) {
                        isSmaller = false;
                    }
                }
                if (isSmaller) {
                    value += c.n + 1;
                    //System.err.println("smaller");
                }
            }
        }
    }

    @Override
    protected void computeSecondStep() {
        value = 0;
        floodFill();
    }
}
