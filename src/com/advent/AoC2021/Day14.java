package com.advent.AoC2021;

import com.advent.PuzzleSolver;

import java.util.*;

public class Day14 extends PuzzleSolver {

    String polymer;

    Map<String, String> transf;
    Map<String, String> transf10;

    List<Character> atoms;

    String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public Day14() {
        super("src/inputs/polymer.txt");
    }
    @Override
    protected void init() {

        polymer = getNextInput();
        transf = new HashMap<String, String>();
        transf10 = new HashMap<String, String>();
        getNextInput();
        String pair = getNextInput();
        while(!pair.isEmpty()) {
            String[] splits = pair.split(" ");
            //System.err.println(splits[0]);
            transf.put(splits[0], splits[2]);
            transf10.put(splits[0], splits[0]);
            pair = getNextInput();
        }
    }

    @Override
    protected void computeFirstStep() {
        for (String pair : transf10.values()) {
            String init = pair;
            int n=0;
            while (n < 10) {
                StringBuffer temp = new StringBuffer("");
                for (int i = 0; i < pair.length() - 1; i++) {
                    String s = pair.substring(i, i + 2);
                    //System.err.println(pair);
                    if (transf.get(s) != null) {
                        temp.append(s.charAt(0));
                        temp.append(transf.get(s));
                    }
                }
                if (n < 9) {
                    temp.append(pair.charAt(pair.length() - 1));
                }
                pair = temp.toString();
                n++;
                //System.err.println(polymer);
            }
            transf10.put(init ,pair);
        }
        StringBuffer temp = new StringBuffer();
        for (int i =0; i < polymer.length()-1; i++) {
            String pair = polymer.substring(i,i+2);
            //System.err.println(transf10.get(pair));
            temp.append(transf10.get(pair));
        }
        temp.append(polymer.charAt(polymer.length()-1));
        polymer = temp.toString();


        List<Long> values = new ArrayList<Long>();
        for (char c : alphabet.toCharArray()) {
            long v = polymer.chars().filter(ch -> ch == c).count();
            if (v != 0) {
                //System.err.println("occurences of "+c+" is "+v);
                values.add(v);
            }
        }
        Collections.sort(values);
        value = values.get(values.size()-1)- values.get(0);

    }

    @Override
    protected void computeSecondStep() {
        int n=0;
        value = 0;
        while (n < 4) {
            StringBuffer temp = new StringBuffer();
            System.err.println(n);
            for (int i = 0; i < polymer.length() - 1; i++) {
                String pair = polymer.substring(i, i + 2);
                //System.err.println(transf10.get(pair));
                temp.append(transf10.get(pair));
            }
            temp.append(polymer.charAt(polymer.length() - 1));
            polymer = temp.toString();
            n++;
        }

        List<Long> values = new ArrayList<Long>();
        for (char c : alphabet.toCharArray()) {
            long v = polymer.chars().filter(ch -> ch == c).count();
            if (v != 0) {
                //System.err.println("occurences of "+c+" is "+v);
                values.add(v);
            }
        }
        Collections.sort(values);
        value = values.get(values.size()-1)- values.get(0);
        /*for (String pair : transf10.values()) {
            String init = pair;
            while (n < 10) {
                StringBuffer temp = new StringBuffer("");
                for (int i = 0; i < pair.length() - 1; i++) {
                    String s = pair.substring(i, i + 2);
                    //System.err.println(pair);
                    if (transf.get(s) != null) {
                        temp.append(s.charAt(0));
                        temp.append(transf.get(s));
                    }
                }
                temp.append(pair.charAt(pair.length() - 1));
                pair = temp.toString();
                n++;
                //System.err.println(polymer);
            }
            transf10.put(init ,pair);
        }
        String f = "";
        for (int i =0; i < polymer.length()-1; i++) {
            f+= transf10.get(polymer.substring(i,i+2));
        }
        List<Long> values = new ArrayList<Long>();
        for (char c : alphabet.toCharArray()) {
            long v = polymer.chars().filter(ch -> ch == c).count();
            if (v != 0) {
                System.err.println("occurences of "+c+" is "+v);
                values.add(v);
            }
        }
        Collections.sort(values);
        value = values.get(values.size()-1)- values.get(0);*/
    }
}
