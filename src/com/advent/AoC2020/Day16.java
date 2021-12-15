package com.advent.AoC2020;

import java.util.*;
import java.util.stream.Collectors;

public class Day16 {

    List<Rule> rules;

    public Day16() {
        rules = new ArrayList<Rule>();
    }

    public void read(Scanner in) {
        String row = in.nextLine();
        while (!row.isEmpty()) {

            //String pattern = "\\:\\s(\\d+)\\-(\\d+)\\s+(\\d+)\\-(\\d+)";
            String pattern = ".*?: (\\d+)-(\\d+)\\sor\\s(\\d+)-(\\d+)";
            System.err.println(row);
            rules.add(new Rule(Integer.parseInt(row.replaceAll(pattern, "$1")),
                    Integer.parseInt(row.replaceAll(pattern, "$2")),
                            Integer.parseInt(row.replaceAll(pattern, "$3")),
                                    Integer.parseInt(row.replaceAll(pattern, "$4"))));
            row = in.nextLine();
        }
        in.nextLine();
        //in.nextLine();
        row = in.nextLine();
        System.err.println(row);
        List<Integer> myTicket =  Arrays.stream(row.split(",")).map(s -> Integer.parseInt(s)).collect(Collectors.toList());

        in.nextLine();
        in.nextLine();
        int n = 0;
        List<List<Integer>> validTickets = new ArrayList<List<Integer>>();
        while (in.hasNextLine()) {
            row = in.nextLine();
            System.err.println(row);
            List<Integer> ticket = Arrays.stream(row.split(",")).map(s -> Integer.parseInt(s)).collect(Collectors.toList());
            boolean isValidTicket = true;
            for (int i = 0 ; i < ticket.size(); i++) {
                //rules.get(i).debug();
                boolean isValid = false;
                for (Rule r : rules) {
                    if (r.check(ticket.get(i))) {
                        isValid = true;
                    }
                }
                if (!isValid) {
                    //n+= ticket.get(i);
                    isValidTicket = false;
                    System.err.println("bug "+n);
                }
            }
            if (isValidTicket) {
                validTickets.add(ticket);
            }
        }
        List<Set<Integer>> rulesOptions = new ArrayList<Set<Integer>>();
        for (int i = 0; i < rules.size(); i++) {
            Set<Integer> options = new HashSet<Integer>();
            for (int j = 0; j < rules.size(); j++) {
                options.add(j);
            }
            rulesOptions.add(options);
        }
        for (int i = 0; i < rules.size(); i++) {
            Rule r = rules.get(i);
            System.err.println("rule "+i);
            int a =0;
            for (List<Integer> ticket : validTickets) {
                for (int j = 0; j < ticket.size(); j++) {
                    if (rulesOptions.get(i).contains(j) && !r.check(ticket.get(j))) {
                        r.debug();
                        System.err.println(a+" "+ticket.get(j));
                        rulesOptions.get(i).remove(j);
                    }
                }
                if (rulesOptions.get(i).size() == 1) {
                    r.index = rulesOptions.get(i).iterator().next();
                    System.err.println("found index for rule "+i+" : "+r.index);
                    boolean shouldCheck = true;
                    for (int k = 0; k < rulesOptions.size(); k++) {
                        if (i != k) {
                            rulesOptions.get(k).remove(r.index);
                        }
                    }
                    while (shouldCheck) {
                        shouldCheck = false;
                        for (int p = 0; p < rules.size(); p++) {
                            if (rules.get(p).index == -1 && rulesOptions.get(p).size() == 1) {
                                rules.get(p).index = rulesOptions.get(p).iterator().next();
                                shouldCheck = true;
                                for (int k = 0; k < rulesOptions.size(); k++) {
                                    if (p != k) {
                                        rulesOptions.get(k).remove(rules.get(p).index);
                                    }
                                }
                            }
                        }
                    }
                    break;
                }
                a++;
            }
            System.err.println("options size "+rulesOptions.get(i));
        }

        System.err.println((long)myTicket.get(rules.get(0).index)*myTicket.get(rules.get(1).index)
        *myTicket.get(rules.get(2).index)*myTicket.get(rules.get(3).index)*myTicket.get(rules.get(4).index)
        *myTicket.get(rules.get(5).index));
    }
}

class Rule {

    int min1;

    int max1;

    int min2;

    int max2;

    int index = -1;

    public Rule(int a, int b, int c, int d) {
        min1=a;
        max1 = b;
        min2 = c;
        max2 = d;
    }

    public boolean check(int n) {
        return (n >= min1 && n <= max1) || (n >= min2 && n <= max2);
    }

    public void debug() {
        System.err.println("rule "+min1+" "+max1+" "+min2+" "+max2);
    }
}
