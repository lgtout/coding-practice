package com.lagostout.coderust

import org.apache.commons.lang3.Range
import org.apache.commons.math3.random.RandomDataGenerator
import spock.lang.Specification
import spock.lang.Unroll

class BinarySearchSpec extends Specification {

    @Unroll("#integers \n key: #key expected: #expected")
    'finds the index of a key'(List<Integer> integers,
                               int key, int expected) {
        expect:
        BinarySearch.findIndexOf(integers, key) == Optional.of(expected)

        where:
        [integers, key, expected] << generateRandomCases(
                2, Range.between(1, 5), Range.between(1, 1))
    }

    /**
     * Generates random test cases.
     *
     * @param caseCount Number of cases to generate.
     * @param integerRange The range of integers from which to
     * select values for the array.
     * @param arrayRelativeSizeRange Array size, relative to
     * size of <code>integerRange</code>. For example: Range
     * (1, 5) means array size should be between 1 and 1/5
     * times the size of <code>integerRange</code>.
     * @return A set of randomly generated test cases. Each
     * case contains the input array, the key to search for,
     * and the expected result.
     */
    private static List<List<Object>> generateRandomCases(
            int caseCount, Range<Integer> integerRange,
            Range<Integer> arrayRelativeSizeRange) {
        def random = new RandomDataGenerator()
        random.reSeed(1)
        def cases = []
        caseCount.times {
            def arraySizeRelativeToIntegerRangeSize =
                    random.nextInt(
                            arrayRelativeSizeRange.minimum,
                            arrayRelativeSizeRange.maximum)
            def integerRangeSize = integerRange.maximum -
                    integerRange.minimum + 1
            def arraySize = (int) (integerRangeSize /
                    arraySizeRelativeToIntegerRangeSize)
            def array = []
            def usedIntegers = new HashSet<Integer>()
            while (array.size() < arraySize) {
                def integer = random.nextInt(
                        integerRange.minimum,
                        integerRange.maximum)
                if (usedIntegers.contains(integer)) continue
                array << integer
                usedIntegers << integer
            }
            array = array.sort()
            def key = array[random.nextInt(0, arraySize - 1)]
            def expected = Collections.binarySearch(array, key)
            cases << [array, key, expected]
        }
        cases
    }

}

