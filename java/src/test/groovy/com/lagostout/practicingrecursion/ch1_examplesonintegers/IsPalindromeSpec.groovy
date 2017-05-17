package com.lagostout.practicingrecursion.ch1_examplesonintegers

import spock.lang.Specification
import spock.lang.Unroll

class IsPalindromeSpec extends Specification {

    @Unroll('#n #length #expected')
    def 'is palindrome'(int n, int length, boolean expected) {
        expect:
        P21_IsPalindrome.isPalindrome(n, length) == expected
        where:
        [n, length, expected] << [
                [1, 1, true],
                [12, 2, false],
                [121, 3, true],
                [2222, 4, true],
                [22, 2, true],
        ]
    }

}
