package com.advent.AoC2020;

import java.util.*;
import java.util.Scanner;

public class Day10 {

    List<Long> adapters;

    Map<Integer, Long> ways;

    int totalWays;

    public Day10() {
    this.adapters = new ArrayList<Long>();
    ways = new HashMap<Integer, Long>();
    }

    public void read(Scanner in) {
        while (in.hasNextLine()) {
            Long next = Long.parseLong(in.nextLine());
            adapters.add(next);
        }
        Collections.sort(adapters);
        int a = 0;
        int b = 0;
        Long previous = 0L;
        for (Long l : adapters) {

            if (l-previous == 1) {
                a+=1;
            }
            else {
                b+=1;
            }
            previous = l;
        }
        b+=1;
        System.err.println(a*b);
    }

    public void read2(Scanner in) {
        while (in.hasNextLine()) {
            Long next = Long.parseLong(in.nextLine());
            adapters.add(next);
        }
        Collections.sort(adapters);
        System.err.println(findNext(0)*2);
    }

    public Long findNext(int i) {

        if (i == adapters.size() -1) {
            totalWays+=1;
            //System.err.println("ways++");
            return 1L;
        }
        if (ways.get(i) != null) {
            return ways.get(i);
        }
        Long w = 0L;
        int a = 1;
        long current = adapters.get(i);
        while (a < 4 && i + a < adapters.size()) {
            long l = adapters.get(i + a);
        /*System.err.println("level "+i+" mod "+a);
        System.err.println(current);
        System.err.println("diff "+(l-current));*/
            if (l - current < 4) {
                w+=findNext(a + i);
            }
            a++;
        }
        ways.put(i, w);
        System.err.println("level "+i+" value "+current+" ways "+w);
        return w;
    }
}
