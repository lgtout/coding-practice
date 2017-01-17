package com.lagostout.cp3.introduction;

import java.util.*;
import java.util.stream.Collectors;

public class HorrorDash {

    static class TestCase {

    }

    static class TestCaseIterator implements Iterator<int[]> {

        Integer testCaseCount = null;
        Scanner scanner = new Scanner(System.in);

        @Override
        public boolean hasNext() {
            if (testCaseCount == null) {
                testCaseCount = scanner.nextInt();
                scanner.nextLine();
            }
            return testCaseCount > 0;
        }

        @Override
        public int[] next() {
            if (hasNext()) {
                String line = scanner.nextLine();
                int[] speeds = Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray();
                testCaseCount--;
                return speeds;
            }
            return null;
        }

    }

    static public void main(String[] args) {
        TestCaseIterator iterator = new TestCaseIterator();
        List<Integer> clownSpeeds = new ArrayList<>();
        while (iterator.hasNext()) {
            int[] speeds = iterator.next();
            int clownSpeed = 0;
            for (int i = 0; i < speeds.length; i++) {
                clownSpeed = Math.max(clownSpeed, speeds[i]);
            }
            clownSpeeds.add(clownSpeed);
        }
        for (int i = 0; i < clownSpeeds.size(); i++) {
            System.out.printf("Case %d: %d\n", i+1, clownSpeeds.get(i));
        }
    }

}
