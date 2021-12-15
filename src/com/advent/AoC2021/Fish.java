package com.advent.AoC2021;

public class Fish {

    int count;

    public Fish() {
        count = 6;
    }

    public Fish(int n) {
        count = n;
    }

    public void live() {
        if (count == 0) {
            count = 6;
        }
        else {
            count--;
        }
    }
}
