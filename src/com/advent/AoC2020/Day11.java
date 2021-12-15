package com.advent.AoC2020;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day11 {

        Seat[][] seats;

        Seat noSeat = new Seat(true, false);

    int occupied;
    int width = 91;
    int height = 90;

    public Day11 () {
        seats = new Seat[width][height];

    }

    public void read(Scanner in) {
        int y =0;
        while (in.hasNextLine()) {
            String row = in.nextLine();
            System.err.println(row.length());
            for (int x = 0; x < width; x++) {
                char c = row.charAt(x);
                occupied+= (c == '#')?1:0;
                seats[x][y] = new Seat(row.charAt(x) == 'L', c != '.');
            }
            y++;
        }
        //debug();

        for (y = 0; y < height; y++) {
           for (int x = 0; x < width; x++) {
               Seat s = seats[x][y];
               if (x > 0) {
                   s.add(seats[x-1][y]);
               }
               if (x > 0 && y > 0) {
                   s.add(seats[x-1][y-1]);
               }
               if (y > 0) {
                   s.add(seats[x][y-1]);
               }
               if (x < width -1 && y > 0) {
                   s.add(seats[x+1][y-1]);
               }
           }
        }
        boolean hasChanged = true;
        while (hasChanged) {
            hasChanged = false;
            for (y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    Seat current = seats[x][y];
                    if (current.isSeat) {
                        if (current.isEmpty && current.countNear() == 0) {
                            current.next = false;
                            hasChanged = true;
                            occupied += 1;
                        }
                        if (!current.isEmpty && current.countNear() >= 4) {
                            current.next = true;
                            hasChanged = true;
                            occupied -= 1;
                        }
                    }
                }
            }
            for (y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    seats[x][y].update();
                }
            }
            //debug();
        }
        System.err.println(occupied);
    }

    private void debug() {
        for (int y = 0; y < height; y++) {
            StringBuffer s = new StringBuffer();
            for (int x = 0; x < width; x++) {
                Seat seat = seats[x][y];
                s.append(seat.isSeat ? seat.isEmpty ? "L":"#" : ".");
            }
            System.err.println(s);
        }
        System.err.println(occupied);
    }
}

class Seat {

    boolean isSeat;

    boolean isEmpty;

    boolean next;

    List<Seat> adjacents;

    public Seat(boolean isEmpty, boolean isSeat) {
        this.isEmpty = isEmpty;
        this.isSeat = isSeat;
        this.adjacents = new ArrayList<Seat>();
        next = isEmpty;
    }

    public void add(Seat s) {
        adjacents.add(s);
        s.adjacents.add(this);
    }

    public int countNear() {
        int n = 0;
        for (Seat s : adjacents) {
            if (s.isSeat && !s.isEmpty) {
                n++;
            }
        }
        return n;
    }

    public void update() {
        if (isSeat) {
            isEmpty = next;
        }
    }
}
