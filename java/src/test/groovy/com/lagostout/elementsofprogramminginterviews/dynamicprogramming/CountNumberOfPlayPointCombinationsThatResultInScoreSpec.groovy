package com.lagostout.elementsofprogramminginterviews.dynamicprogramming

import spock.lang.Specification
import spock.lang.Unroll

class CountNumberOfPlayPointCombinationsThatResultInScoreSpec extends Specification {

    @Unroll("score: #score, points: #points, count: #expectedCount")
    'counts the number of score combinations'(
            int score, int[] points, int expectedCount) {
        expect:
        CountNumberOfPlayPointCombinationsThatResultInScore
                .scoreCombinationCount(score, points) == expectedCount
        where:
        [score, points, expectedCount] << [
                [0, [0,0,0], 1],
                // points: 3,5,6
                [3, [3,5,6], 1],
                [4, [3,5,6], 0],
                [5, [3,5,6], 1],
                [6, [3,5,6], 2],
                [7, [3,5,6], 0],
                [8, [3,5,6], 1],
                [9, [3,5,6], 2],
                [10, [3,5,6], 1],
                [11, [3,5,6], 2],
                [12, [3,5,6], 3],
                [13, [3,5,6], 1],
                [14, [3,5,6], 2],
                // points: 2,3,7
                [12, [2,3,7], 4],
                // points: 2,3,4
                [0, [2,3,4], 1],
                [1, [2,3,4], 0],
                [2, [2,3,4], 1],
                [3, [2,3,4], 1],
                [4, [2,3,4], 2],
                [5, [2,3,4], 1],
                [6, [2,3,4], 3],
                [7, [2,3,4], 2],
                [8, [2,3,4], 4],
                [9, [2,3,4], 3],
                [10, [2,3,4], 5],
                [11, [2,3,4], 4],
                [12, [2,3,4], 7],
                [13, [2,3,4], 5],
                [14, [2,3,4], 8],
                [15, [2,3,4], 7],
                [16, [2,3,4], 10],
                [17, [2,3,4], 8],
                [18, [2,3,4], 12],
                [19, [2,3,4], 10],
                [20, [2,3,4], 14],
        ]
    }
}
