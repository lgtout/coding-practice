package com.lagostout.leetcode

import spock.lang.Specification
import spock.lang.Unroll

class ReverseStringSpec extends Specification {

    @Unroll
    'reverse string'(String str, String expected) {
        expect:
        new ReverseString().reverseString3(str) == expected
        where:
        [str, expected] << [
                ['hello', 'olleh'],
//                ['','']
        ]
    }
}
