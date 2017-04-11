package com.lagostout.elementsofprogramminginterviews.hashtables

import spock.lang.Specification
import spock.lang.Unroll

class IsAnAnonymousLetterConstructibleSpec extends Specification {

    @Unroll("letter: #letter magazine: #magazine expected: #expected")
    def 'letter is constructible from magazine'(
            String letter, String magazine, Boolean expected) {

        expect:
        IsAnAnonymousLetterConstructible.isConstructible(
                letter, magazine) == expected

        where:
        [letter, magazine, expected] << [
                ["", "", true],
                ["", "a", true],
                ["a", "a", true],
                ["a", "", false],
                ["a", "b", false],
                ["aa", "aa", true],
                ["aa", "a", false],
                ["a", "aa", true],
                ["ab", "aa", false],
                ["ab", "", false],
                ["aa", "", false],
                ["b", "aa", false],
                ["bc", "ab", false],
                ["ba", "ab", true],
                ["b", "ab", true],
                ["b", "abcd", true],
                ["b", "abb", true],
                ["cba", "fabbcd", true],
        ]
    }

}
