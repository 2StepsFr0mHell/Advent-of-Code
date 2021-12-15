package com.advent.AoC2020;

import com.advent.PuzzleSolver;

import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        /*File file =
                new File("src/inputs/xmas.txt");
        Scanner sc = new Scanner(file);
        Day9 d = new Day9();
        d.read2(sc);*/
        PuzzleSolver solver = PuzzleSolver.get(14);
        solver.solve();
        /*int n = 0;
        //Map<String, Bag> bags = new HashMap<String, Bag>();
        //dark olive bags contain 3 faded blue bags, 4 dotted black bags.
        List<Instruction> program = new ArrayList<Instruction>();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String pattern = "^(acc|jmp|nop) (.)(\\d+)$";
            String type = line.replaceAll(pattern, "$1");
            int a = Integer.parseInt(line.replaceAll(pattern, "$3"));
            if (line.replaceAll(pattern, "$2").equals("-")) {
                a*=-1;
            }
            program.add(new Instruction(type,a));
        }
        for (int index = 0; index < program.size(); index++) {
            Instruction current = program.get(0);
            if (!program.get(index).type.equals("acc")) {
                //System.err.println("trying "+index);
                int v = 0;
                int i = 0;
                while (!current.isVisited) {
                    current.isVisited = true;
                    if ("jmp".equals(current.type)) {
                        if (i != index) {
                            i += current.n;
                        }
                        else {
                            i++;
                        }

                    } else if ("acc".equals(current.type)) {
                        v += current.n;
                        i++;
                    }
                    else {
                        if (i == index) {
                            i+=current.n;
                        }
                        else {
                            i++;
                        }
                    }
                    current = program.get(i);
                    if (i == program.size() - 1) {
                        System.err.println(v);
                    }
                }
                for (Instruction ins : program) {
                    ins.isVisited = false;
                }
            }
        }

            /*
            String id = line.replaceAll("^(.*?)( bags contain).*","$1");
            System.err.println(id);
            Bag current = bags.get(id);
            if (current == null) {
                current = new Bag(id);
                bags.put(id, current);
            }
            String pattern = "(\\d)\\s(.*?)( bags?)";
            Matcher m = Pattern.compile(pattern).matcher(line);
            while (m.find()) {
                String group = m.group();
                //System.err.println(group);
                String bag1 = group.replaceAll(pattern, "$2");
                int nb1 = Integer.parseInt(group.replaceAll(pattern, "$1"));
                System.err.println(bag1 + " " + nb1);
                Bag container1 = bags.get(bag1);
                if (container1 == null) {
                    container1 = new Bag(bag1);
                    bags.put(bag1, container1);
                }
                current.containers.put(container1, nb1);
            }
        }
        Bag b = bags.get("shiny gold");*/
        /*List<Bag> children = new ArrayList<Bag>();
        children.addAll(b.containers.keySet());
        List<Bag> shinyBags = new ArrayList<Bag>();
        while (!children.isEmpty()) {
            List<Bag> temp = new ArrayList<Bag>();
            for (Bag parent : parents) {
                if (!shinyBags.contains(parent)) {
                    temp.addAll(parent.containers.keySet());
                    shinyBags.add(parent);
                    System.err.println(parent.id);
                    n+=1;
                }
            }
            parents.clear();
            parents.addAll(temp);
        }*/
        //n = b.getBagsNb();
        //System.err.println(v);
        /*
        byr (Birth Year) - four digits; at least 1920 and at most 2002.
iyr (Issue Year) - four digits; at least 2010 and at most 2020.
eyr (Expiration Year) - four digits; at least 2020 and at most 2030.
hgt (Height) - a number followed by either cm or in:
If cm, the number must be at least 150 and at most 193.
If in, the number must be at least 59 and at most 76.
hcl (Hair Color) - a # followed by exactly six characters 0-9 or a-f.
ecl (Eye Color) - exactly one of: amb blu brn gry grn hzl oth.
pid (Passport ID) - a nine-digit number, including leading zeroes.

        boolean hasBY, hasIY, hasH, hasHC, hasEC, hasEY, hasPID;
        hasBY = hasEC = hasEY = hasH = hasHC = hasIY = hasPID = false;
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            System.err.println(line);
            if (line.isEmpty()) {
                System.err.println("check");
                if (hasBY && hasIY && hasH && hasHC && hasEC && hasEY && hasPID) {
                    n+=1;
                    System.err.println("tada");
                }
                hasBY = hasEC = hasEY = hasH = hasHC = hasIY = hasPID = false;
            }
            else {
                String parts[] = line.split(" ");
                for (String s : parts) {
                    String id = s.split(":")[0];
                    String v = s.split(":")[1];
                    //System.err.println(id);
                    if ("byr".equals(id)) {
                        int d = Integer.parseInt(v);
                        if (d >= 1920 && d <= 2002) {
                            hasBY = true;System.err.println("correct");
                        }
                    }
                    if ("eyr".equals(id)) {
                        int d = Integer.parseInt(v);
                        if (d >= 2020 && d <= 2030) {
                            hasEY = true;

                        }
                    }
                    if ("iyr".equals(id)) {
                        int d = Integer.parseInt(v);
                        if (d >= 2010 && d <= 2020) {
                            hasIY = true;
                        }
                    }
                    if ("hgt".equals(id)) {
                        String pattern = "(\\d+)(cm|in)";
                        if (v.matches(pattern)) {
                            int d = Integer.parseInt(v.replaceAll(pattern,"$1"));
                            String type = v.replaceAll(pattern,"$2");
                            if (("cm".equals(type) && d >= 150 && d <= 193) || ("in".equals(type) && d >= 59 && d<= 76)) {
                                hasH = true;
                            }
                        }
                    }
                    if ("ecl".equals(id) && v.matches("amb|blu|brn|gry|grn|hzl|oth")) {
                        hasEC = true;
                    }
                    if ("hcl".equals(id) && v.matches("\\#[0-9a-f]{6}")) {
                        hasHC = true;
                    }
                    if ("pid".equals(id) && v.matches("^\\d{9}$")) {
                        hasPID = true;
                    }
                }
            }

        }
        if (hasBY && hasIY && hasH && hasHC && hasEC && hasEY && hasPID) {
            n+=1;
            System.err.println("tada");
        }
        */


    }
}

class Instruction {

    boolean isVisited;

    String type;

    int n;

    public Instruction(String type, int n) {
        this.type = type;
        this.n = n;
    }
}

class Bag {

    String id;

    Map<Bag, Integer> containers;

    public Bag(String id) {
        this.id = id;
        this.containers = new HashMap<Bag, Integer>();
    }

    public int getBagsNb() {
        if (containers.isEmpty()) {
            return 0;
        }
        int n = 0;
        for (Map.Entry<Bag, Integer> entry: containers.entrySet()) {
            n+=entry.getValue() * (entry.getKey().getBagsNb()+1);
        }
        return n;
    }
}