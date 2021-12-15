package com.advent.AoC2020;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day9 {

    List<Long> nb;

    int size;
    long result = 22477624;

    public Day9() {
        //sums = new ArrayList<Sum>();
        size = 25;
        nb = new ArrayList<Long>();
    }

    public void read2(Scanner in) {
        long sum = Long.parseLong(in.nextLine());
        nb.add(sum);
        int min= 0;
        int max = 0;
        while (in.hasNextLine()) {
            Long next = Long.parseLong(in.nextLine());
            nb.add(next);
            sum+=next;
            max+=1;
            checkSum(sum, min, max);
            while (sum > result && min < max-1) {
                sum-=nb.get(min);
                min+=1;
            }
            checkSum(sum, min, max);
        }
    }

    private void checkSum(long sum, int iMin, int iMax) {
        if (sum == result) {
            long max  = 0;
            long min = result;
            for (int i = iMin; i <= iMax; i++) {
                if (nb.get(i) > max) {
                    max = nb.get(i);
                }
                if (nb.get(i) < min) {
                    min = nb.get(i);
                }
            }
            System.err.println(min+max);
        }
    }

    public void read(Scanner in) {
        int i = 0;

        while (in.hasNextLine()) {
            Long next = Long.parseLong(in.nextLine());
            nb.add(next);
            if (i >= size) {
                int a = 0;
                boolean isValid = false;
                while (a < size && !isValid) {
                    int b = a+1;
                   while (b < size && !isValid) {
                       long n = nb.get(nb.size()-1-size+a)+ nb.get(nb.size()-1 - size + b);
                       if (n == next) {
                           isValid = true;
                       }
                       b++;
                   }
                   a++;
                }
                if (!isValid) {
                    System.err.println(next);
                    break;
                }

            }
            i++;
        }

    }
}

class Sum {

    int n;

    List<Integer> sum;

    public Sum(int n) {
        this.n =n;
        this.sum = new ArrayList<Integer>();
    }

    public void add(int a) {
        sum.add(a+n);
    }
}
