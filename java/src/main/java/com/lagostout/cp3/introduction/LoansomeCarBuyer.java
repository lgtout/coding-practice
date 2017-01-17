package com.lagostout.cp3.introduction;

import java.io.InputStream;
import java.util.*;

public class LoansomeCarBuyer {

    public static class TestCaseIterator implements Iterator<TestCase> {

        private Integer loanDuration;
        private Scanner scanner;

        TestCaseIterator(InputStream tests) {
            scanner = new Scanner(tests);
        }

        @Override
        public boolean hasNext() {
            if (loanDuration == null)
                loanDuration = scanner.nextInt();
            return loanDuration > 0;
        }

        @SuppressWarnings("Duplicates")
        @Override
        public TestCase next() {
            if (hasNext()) {
                TestCase testCase = new TestCase(loanDuration,
                        scanner.nextDouble(), scanner.nextDouble());
                int depreciationCount = scanner.nextInt();
                Map<Integer, Double> depreciation = new HashMap<>();
                for (int i = 0; i < depreciationCount; i++) {
                    depreciation.put(scanner.nextInt(), scanner.nextDouble());
                }
                testCase.depreciation = depreciation;
                onTestCaseRead();
                return testCase;
            } else throw new IndexOutOfBoundsException();
        }

        private void onTestCaseRead() {
            loanDuration = scanner.nextInt();
        }

    }

    static class TestCase {
        final int loanDuration;
        final double downPayment;
        final double loanAmount;
        Map<Integer, Double> depreciation;

        TestCase(int loanDuration, double downPayment, double loanAmount) {
            this.loanDuration = loanDuration;
            this.downPayment = downPayment;
            this.loanAmount = loanAmount;
        }
    }

    public Integer computeNumberOfMonthsToOweLessThanCarIsWorth(
            int loanDuration, double downPayment,
            double loan, Map<Integer, Double> depreciation) {
        double monthlyLoanPayment = loan/loanDuration;
        double carValue = loan + downPayment;
        double remainingValuePercentage = 1 - depreciation.get(0);
        carValue = carValue * remainingValuePercentage;
        int currentDepreciationIndex = 0;
        if (loan < carValue) return 0;
        int i;
        for (i = 1; i <= loanDuration ; i++) {
            currentDepreciationIndex = depreciation.containsKey(i) ?
                    i : currentDepreciationIndex;
            remainingValuePercentage = 1 - depreciation.get(currentDepreciationIndex);
            carValue = carValue * remainingValuePercentage;
            loan = loan - monthlyLoanPayment;
            if (loan <= carValue) break;
        }
        return i;
    }

    public static void main(String[] args) {
        TestCaseIterator testCases = new TestCaseIterator(System.in);
        LoansomeCarBuyer buyer = new LoansomeCarBuyer();
        List<Integer> numberOfMonthsToOweLessThanCarWorth = new ArrayList<>();
        while (testCases.hasNext()) {
            TestCase testCase = testCases.next();
            numberOfMonthsToOweLessThanCarWorth.add(
                    buyer.computeNumberOfMonthsToOweLessThanCarIsWorth(
                            testCase.loanDuration, testCase.downPayment,
                            testCase.loanAmount, testCase.depreciation));
        }
        numberOfMonthsToOweLessThanCarWorth.forEach(i -> {
                String format = "%d month";
                if (i > 1 || i == 0) {
                    format += 's';
                }
                System.out.println(String.format(format, i));
            });

    }

}
