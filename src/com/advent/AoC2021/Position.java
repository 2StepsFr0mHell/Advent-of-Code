package com.advent.AoC2021;

public class Position {

    int x,y, aim;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
        this.aim = 0;
    }

    public void move(int n, String d) {
        switch (d) {
            case "up" :
                y-=n;
                break;
            case "down" :
                y+=n;
                break;
            case "forward" :
                x+=n;
                break;
        }
    }

    public void submove(int n, String d) {
        switch (d) {
            case "up":
                aim-=n;
                break;
            case "down":
                aim+=n;
                break;
            case "forward":
                x+=n;
                y+=aim*n;
        }
    }
}
