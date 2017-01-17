package com.lagostout.cp3.introduction;

import java.io.InputStream;
import java.util.Iterator;
import java.util.Scanner;

public class CostCutting {

    public static class TestCaseIterator implements Iterator<int[]> {

        private int hasNextTestCase = -1;
        private Scanner scanner;

        TestCaseIterator(InputStream tests) {
            scanner = new Scanner(tests);
        }

        @Override
        public boolean hasNext() {
            if (hasNextTestCase == -1)
                hasNextTestCase = scanner.nextInt();
            return hasNextTestCase > 0;
        }

        @SuppressWarnings("Duplicates")
        @Override
        public int[] next() {
            if (hasNext()) {
                int[] salaries =  new int[] {
                        scanner.nextInt(),
                        scanner.nextInt(),
                        scanner.nextInt()};
                onTestCaseRead();
                return salaries;
            } else throw new IndexOutOfBoundsException();
        }

        private void onTestCaseRead() {
            hasNextTestCase--;
        }

    }

    private static int solve(int[] salaries) {
        int least = 0;
        int most = 0;
        for (int i = 0; i < salaries.length; i++) {
            int salary = salaries[i];
            least = Math.min(salaries[least], salary) != salaries[least] ? i : least;
            most = Math.max(salaries[most], salary) != salaries[most] ? i : most;
        }
        for (int i = 0; i < salaries.length; i++) {
            if (i != least && i != most) {
                return salaries[i];
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        TestCaseIterator iterator = new TestCaseIterator(System.in);
        for (int i = 0; iterator.hasNext(); i++) {
            int[] salaries = iterator.next();
            System.out.println(String.format("Case %d: %d", i+1, solve(salaries)));
        }
    }

}
