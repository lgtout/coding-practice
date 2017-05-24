package com.lagostout.elementsofprogramminginterviews.dynamicprogramming

import spock.lang.Specification
import spock.lang.Unroll

class CountNumberOfUniquePlayPointSequencesThatResultInScoreSpec extends Specification {

    @Unroll
    """counts number of possible unique sequences of
       plays that result in a score"""(
            int score, int expected, List<Integer> possiblePlayPoints) {

        expect:
        CountNumberOfUniquePlayPointSequencesThatResultInScore
                .sequenceCount(score, possiblePlayPoints) == expected

        where:
        [score, expected, possiblePlayPoints] << [
                [0, 1],
                [1, 0],
                [2, 1],
                [3, 1],
                [4, 2],
                [5, 2],
                [6, 4],
                [7, 5],
                [8, 8],
                [9, 11]
        ].collect {
            it << [2,3,4]
        }
    }

}
