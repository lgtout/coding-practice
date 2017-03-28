package com.lagostout.competitiveprogramming3.introduction;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

class DivisionOfNlogonia {

    public static class TestCase {
        int[] divisionPoint;
        List<int[]> queries = new ArrayList<>();
        int queryCount() {
            return queries.size();
        }
    }

    public static class TestCaseIterator implements Iterator<TestCase> {

        Integer queryCount;
        Scanner scanner;

        TestCaseIterator(InputStream tests) {
            scanner = new Scanner(tests);
        }

        @Override
        public boolean hasNext() {
            if (queryCount == null)
                queryCount = scanner.nextInt();
            return queryCount != 0;
        }

        @Override
        public TestCase next() {
            if (hasNext()) {
                TestCase testCase = new TestCase();
                testCase.divisionPoint = new int[] {
                        scanner.nextInt(), scanner.nextInt()};
                for (int i = 0; i < queryCount; i++) {
                    testCase.queries.add(
                            new int[] {scanner.nextInt(), scanner.nextInt()});
                }
                queryCount = scanner.nextInt();
                return testCase;
            }
            throw new IndexOutOfBoundsException();
        }
    }

    public static List<String> run(
            int[] divisionPoint, List<int[]> queries) {
        List<String> locations = new ArrayList<>();
        for (int[] query : queries) {
            String location = "";
            if (query[0] == divisionPoint[0] ||
                    query[1] == divisionPoint[1]) location = "divisa";
            else {
                if (query[1] > divisionPoint[1]) location += "N";
                else location += "S";
                if (query[0] < divisionPoint[0]) location += "O";
                else location += "E";
            }
            locations.add(location);
        }
        return locations;
    }

    public static void main(String[] args) throws IOException {
        TestCaseIterator iterator = new TestCaseIterator(System.in);
        while (iterator.hasNext()) {
            TestCase testCase = iterator.next();
            List<String> locations = run(
                    testCase.divisionPoint, testCase.queries);
            for (String location : locations) {
                System.out.println(location);
            }
        }
    }
}
