package com.lagostout.leetcode

import spock.lang.Specification
import spock.lang.Unroll
import com.lagostout.leetcode.NestedListWeightSum.NestedInteger

class NestedListWeightSumSpec extends Specification {

    @Unroll("#nestedIntegers depth: #depth")
    'sum of all integers weighted by depth'(
            List<NestedInteger> nestedIntegers,
            int depth) {
        expect:
        new NestedListWeightSum().depthSum(toNestedIntegers(nestedIntegers)) == depth
        where:
        [nestedIntegers, depth] << [
                [[1,1], 2],
                [[1,1,[2]], 6],
                [[[1,1],2,[1,1]], 10],
                [[1,[4,[6]]], 27]
        ]
    }

    List<NestedInteger> toNestedIntegers(List<Object> integers) {
        List<NestedInteger> nestedIntegers = new ArrayList<>()
        for (Object o : integers) {
            def nestedInteger
            if (o.class == Integer.class) {
                nestedInteger = new NestedInteger(o as Integer)
            } else {
                List<NestedInteger> list =
                        toNestedIntegers(o as List<Object>)
                nestedInteger = new NestedInteger(list)
            }
            nestedIntegers.add(nestedInteger)
        }
        nestedIntegers
    }

}
