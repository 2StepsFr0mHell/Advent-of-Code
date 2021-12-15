package com.advent.AoC2021;

import java.util.*;

public class Cell {

    int n = -1;

    boolean isWall;

    List<Cell> adjacents;

    boolean isVisited;

    public Cell(int n) {
        this.n = n;
        this.isWall = n == 9;
        adjacents = new ArrayList<Cell>();
    }

    public void addAdjacent(Cell c) {
        adjacents.add(c);
        c.adjacents.add(this);
    }

    public void visit() {
        isVisited = true;
    }

    public void debug() {
        System.err.println("cell "+n+" "+isVisited);
    }
}
