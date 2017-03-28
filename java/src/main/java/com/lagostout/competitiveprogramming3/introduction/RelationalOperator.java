package com.lagostout.competitiveprogramming3.introduction;

import java.util.Scanner;

class RelationalOperator {

    public static void main(String[] args) {
        RelationalOperator ro = new RelationalOperator();
        ro.run();
    }

    private void run() {
        Scanner scanner = new Scanner(System.in);
        int inputSetsCount = scanner.nextInt();
        char[] ops = new char[inputSetsCount];
        for (int i = 0; i < inputSetsCount; i++) {
            long a = scanner.nextLong();
            long b = scanner.nextLong();
            char op;
            if (a == b) op = '=';
            else if (a < b) op = '<';
            else op = '>';
            ops[i] = op;
        }
        for (char op : ops) {
            System.out.println(op);
        }
    }

}
