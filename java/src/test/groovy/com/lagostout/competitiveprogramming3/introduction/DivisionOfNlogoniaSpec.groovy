package com.lagostout.competitiveprogramming3.introduction

import com.lagostout.competitiveprogramming3.introduction.DivisionOfNlogonia.TestCaseIterator
import com.lagostout.common.Util
import spock.lang.Specification
import spock.lang.Unroll

class DivisionOfNlogoniaSpec extends Specification {

    @Unroll()
    'find locations'(DivisionOfNlogonia.TestCase testcase,
                     List<String> expected) {

        when:
        DivisionOfNlogonia don = new DivisionOfNlogonia()

        then:
        don.run(testcase.divisionPoint, testcase.queries) == expected

        where:
        [testcase, expected] << (new DataIterator(
                new TestCaseIterator(
                        Util.createStream("tests/DivisionOfNLogonia.txt")),
                new ExpectedResultsIterator(
                        Util.getFile("expectedresults/DivisionOfNLogonia.txt"))).collect())
    }

    private static class DataIterator implements Iterator<List> {

        ExpectedResultsIterator expectedResultsIterator
        TestCaseIterator testCaseIterator

        DataIterator(TestCaseIterator testCaseIterator,
                     ExpectedResultsIterator expectedResultsIterator) {
            this.testCaseIterator = testCaseIterator
            this.expectedResultsIterator = expectedResultsIterator
        }

        @Override
        boolean hasNext() {
            return testCaseIterator.hasNext()
        }

        @Override
        List next() {
            if (hasNext()) {
                //noinspection ChangeToOperator
                DivisionOfNlogonia.TestCase testCase = testCaseIterator.next()
                // For each TestCase, take its expected results
                return [testCase, expectedResultsIterator.take(testCase.queryCount()).collect()]
            }
            throw new IndexOutOfBoundsException()
        }
    }

    private static class ExpectedResultsIterator implements Iterator {

        Scanner scanner

        ExpectedResultsIterator(File file) {
            scanner = new Scanner(file)
        }

        @Override
        boolean hasNext() {
            return scanner.hasNext()
        }

        @Override
        String next() {
            if (hasNext()) {
                //noinspection ChangeToOperator
                return scanner.next()
            }
            throw new IndexOutOfBoundsException()
        }
    }

//    private static class GenericDataIterator<ERI extends Iterator<String>, TCI extends Iterator<TC>, TC> implements Iterator {
//
//        TCI expectedResultsIterator
//        ERI testCaseIterator
//
//        GenericDataIterator(TestCaseIterator testCaseIterator,
//                            ExpectedResultsIterator expectedResultsIterator) {
//            this.testCaseIterator = testCaseIterator
//            this.expectedResultsIterator = expectedResultsIterator
//        }
//
//        @Override
//        boolean hasNext() {
//            return testCaseIterator.hasNext()
//        }
//
//        @Override
//        List next() {
//            if (hasNext()) {
//                //noinspection ChangeToOperator
//                DivisionOfNlogonia.TestCase testCase = testCaseIterator.next()
//                return [testCase, expectedResultsIterator.take(testCase.queryCount()).collect()]
//            }
//            throw new IndexOutOfBoundsException()
//        }
//    }
}
