package com.advent.AoC2021;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Grid {

    Cell[][] cells;

    int L, H;

    public Grid(int L, int H) {
        this.L = L;
        this.H = H;
        cells = cells = new Cell[L][H];
    }

    public void init(List<String> input) {
        initGrid(input);
        buildAdjacents();
    }

    public void init5(List<String> input) {
        initGrid5(input);
        buildAdjacents();
    }

    private void initGrid5(List<String> input) {
        for (int y = 0; y < H; y++) {
            String row = input.get(y%(H/5));
            for (int x = 0; x < L; x++) {
                int v = Character.getNumericValue(row.charAt(x%(L/5)))+x/(L/5)+y/(H/5);
                cells[x][y] = new Cell(v > 9 ? v%10 +1 : v);
            }
        }
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

    public Cell get(int x, int y) {
        return cells[x][y];
    }

    public Cell getBottomRight() {
        return get(L-1,H-1);
    }

    public void dijkstra(Cell start, Cell end) {
        Queue<Cell> toVisit = new PriorityQueue<Cell>((c1, c2) -> (int)(c1.dist - c2.dist));
        start.dist = 0;
        toVisit.add(start);
        boolean isEnd = false;
        int count = 0;
        while (!toVisit.isEmpty() && !isEnd) {
            Cell current = toVisit.poll();
            //System.err.println("visiting cell of val "+current.n);
            //System.err.println(current.dist);
            count++;
            for (Cell adj : current.adjacents) {
                if (!adj.isVisited) {
                    adj.dist = Math.min(adj.dist, current.dist + adj.n);
                    if (!toVisit.contains(adj)) {
                        toVisit.add(adj);
                    }
                }
            }
            current.visit();
            if (current.equals(end)) {
                isEnd = true;
            }
        }
        System.err.println("visits "+count);
    }
}
