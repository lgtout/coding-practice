package com.lagostout.interviewbit.dynamicprograming.simplearraydp

import com.lagostout.interviewbit.dynamicprogramming.simplearraydp.LengthOfLongestSubsequence
import spock.lang.Specification
import spock.lang.Unroll

class LengthOfLongestSubsequenceSpec extends Specification {

    @Unroll("list: #list expected: #expected")
    def 'lengthOfLongestSubsequence'(List<Integer> list, int expected) {

        expect:
        def arg = ([list.size() as Integer] + list) as int[]
        LengthOfLongestSubsequence.compute(arg) == expected

        where:
        [list, expected] << [
                [[], 0],
                [[1], 1],
                [[2,1], 2],
                [[1,2,1], 3],
                [[1,2], 0],
        ]

    }

}
