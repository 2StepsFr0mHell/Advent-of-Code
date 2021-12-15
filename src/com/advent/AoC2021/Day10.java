package com.advent.AoC2021;

import com.advent.PuzzleSolver;

import java.util.*;

public class Day10 extends PuzzleSolver {


    Map<Character, Character> brackets;

    Map<Character, Integer> val, val2;

    List<String> inputs;

    List<Long> values;

    public Day10() {
        super("src/inputs/brackets.txt");

    }

    @Override
    protected void init() {
        brackets = new HashMap<Character, Character>();
        brackets.put('{','}');
        brackets.put('[',']');
        brackets.put('(',')');
        brackets.put('<','>');
        val = new HashMap<Character, Integer>();
        val.put(')',3);
        val.put(']',57);
        val.put('}',1197);
        val.put('>',25137);
        val2 = new HashMap<Character, Integer>();
        val2.put(')',1);
        val2.put(']',2);
        val2.put('}',3);
        val2.put('>',4);
        inputs = new ArrayList<String>();
        String input = getNextInput();
        while(!input.isEmpty()) {
            inputs.add(input);
            input = getNextInput();
        }
        values = new ArrayList<Long>();
    }

    @Override
    protected void computeFirstStep() {
        for (String expression : inputs) {
            //System.err.println("input "+expression);
            Stack<Character> s = new Stack<Character>();
            boolean issueFound = false;
            int i = 0;
            while (!issueFound && i < expression.length()) {
                char c = expression.charAt(i);
                if (brackets.containsKey(c)) {
                    s.push(c);
                }
                if (brackets.containsValue(c)) {
                    if (s.isEmpty() || c != brackets.get(s.pop())) {
                        issueFound = true;
                        value += val.get(c);
                        //System.err.println("issue "+c);
                    }
                }
                i++;
            }
        }
    }

    @Override
    protected void computeSecondStep() {
        value = 0;
        for (String expression : inputs) {
            //System.err.println("input "+expression);
            Stack<Character> s = new Stack<Character>();
            boolean issueFound = false;
            int i = 0;
            while (!issueFound && i < expression.length()) {
                char c = expression.charAt(i);
                if (brackets.containsKey(c)) {
                    s.push(c);
                }
                if (brackets.containsValue(c)) {
                    if (s.isEmpty() || c != brackets.get(s.pop())) {
                        issueFound = true;
                        //value += val.get(c);
                        //System.err.println("issue "+c);
                    }
                }
                i++;
            }
            if (!issueFound) {
                long val = 0;
                while (!s.isEmpty()) {
                    val*= 5;
                    val+= val2.get(brackets.get(s.pop()));
                }
                values.add(val);
            }
        }
        Collections.sort(values);
        value = values.get((values.size()-1)/2);
    }
}
