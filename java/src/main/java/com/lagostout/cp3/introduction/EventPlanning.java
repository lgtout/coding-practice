package com.lagostout.cp3.introduction;

import java.io.InputStream;
import java.util.Iterator;
import java.util.Scanner;

public class EventPlanning {

    static class TestCase {
        final int participantCount;
        final int budget;
        int[] prices;
        int[][] bedsPerWeekend;

        TestCase(int participantCount, int budget) {
            this.participantCount = participantCount;
            this.budget = budget;
        }
    }

    public static class TestCaseIterator implements Iterator<TestCase> {

        private Boolean hasNext = null;
        private Scanner scanner;

        TestCaseIterator(InputStream stream) {
            scanner = new Scanner(stream);
        }

        @Override
        public boolean hasNext() {
            if (hasNext == null)
                hasNext = scanner.hasNext();
            if (!hasNext)
                scanner.close();
            return hasNext;
        }

        @SuppressWarnings("Duplicates")
        @Override
        public TestCase next() {
            if (hasNext()) {
                TestCase testCase = new TestCase(
                        scanner.nextInt(), scanner.nextInt());
                int hotelCount = scanner.nextInt();
                int weekCount = scanner.nextInt();
                int[] prices = new int[hotelCount];
                int[][] bedsPerWeekend = new int[hotelCount][];
                for (int i = 0; i < hotelCount; i++) {
                    prices[i] = scanner.nextInt();
                    bedsPerWeekend[i] = new int[weekCount];
                    for (int j = 0; j < weekCount; j++) {
                        bedsPerWeekend[i][j] = scanner.nextInt();
                    }
                }
                hasNext = null;
                testCase.bedsPerWeekend = bedsPerWeekend;
                testCase.prices = prices;
                return testCase;
            } else throw new IndexOutOfBoundsException();
        }

    }

    private static String makeChoice(int participantCount, int budget,
                                     int[] prices, int[][] bedsPerWeekend) {
        Integer bestPrice = Integer.MAX_VALUE;
        for (int i = 0; i < prices.length; i++) {
            int price = prices[i];
            if (price > bestPrice) continue;
            for (int j = 0; j < bedsPerWeekend[0].length; j++) {
                if (bedsPerWeekend[i][j] < participantCount) continue;
                if (bestPrice > price && price * participantCount < budget) bestPrice = price;
            }
        }
        return bestPrice == Integer.MAX_VALUE ? "stay home" : String.valueOf(bestPrice * participantCount);
    }

    public static void main(String[] args) {
        TestCaseIterator testCaseIterator = new TestCaseIterator(System.in);
        while (testCaseIterator.hasNext()) {
            TestCase testCase = testCaseIterator.next();
            String choice = makeChoice(testCase.participantCount,
                    testCase.budget, testCase.prices,
                    testCase.bedsPerWeekend);
            System.out.println(choice);
        }
    }
}
