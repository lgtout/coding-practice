package com.lagostout.elementsofprogramminginterviews.hashtables

import spock.lang.Specification
import spock.lang.Unroll

class TestForPalindromicPermutationsSpec extends Specification  {

    @Unroll("string #str expected #expected")
    'tests if letters of string can be permuted to form a palindrome'(
            String str, boolean expected) {
        expect:
        TestForPalindromicPermutations.
                canBePermutedToFormPalindrome(str) == expected
        where:
        [str, expected] << [
                ["a", true],
                ["ab", false],
                ["aab", true],
                ["aba", true],
                ["abca", false],
                ["abac", false],
                ["abc", false],
                ["abcab", true],
                ["abcba", true],
                ["abba", true],
                ["baba", true],
                ["bacbad", false],
                ["bacbacd", true],
                ["bacbacdd", true],
                ["level", true],
                ["edified", true],
        ]
    }

}
