package com.advent;

import com.advent.AoC2021.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public abstract class PuzzleSolver {

    String inputFileName;

    Scanner sc;

    protected long value;

    public PuzzleSolver(String name) {
        inputFileName = name;
        value = 0L;
        File file =
                new File(inputFileName);
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.err.println("this file does not exist");
        }
    }

   protected String getNextInput() {
        if (sc.hasNextLine()) {
            return sc.nextLine();
        }
        return "";
   }

   public void solve() {
        init();
        computeFirstStep();
        printResult();
        computeSecondStep();
        printResult();
   }

   public static PuzzleSolver get(int n) {
        switch (n) {
            case 1:
                return new Day1("src/inputs/depth1.txt");
            case 2:
                return new Day2("src/inputs/orders.txt");
            case 3:
                return new Day3("src/inputs/diagnostic.txt");
            case 7:
                return new Day7();
            case 6 :
                return new Day6();
            case 9 :
                return new Day9();
            case 10:
                return new Day10();
            case 14:
                return new Day14();
            case 15:
                return new Day15();
            case 18:
                return new FormulaSolver("src/inputs/formulas.txt");
        }
        return null;
    }

    protected abstract void init();
    protected abstract void computeFirstStep();
    protected abstract void computeSecondStep();
    protected void printResult() {
        System.err.println("result is "+value);
    }
}
