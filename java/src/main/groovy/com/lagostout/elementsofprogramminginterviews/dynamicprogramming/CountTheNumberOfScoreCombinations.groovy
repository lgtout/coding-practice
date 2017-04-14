package com.lagostout.elementsofprogramminginterviews.dynamicprogramming

/**
 * Source: EPIJ - Dynamic Programming (ch 17) - problem 17.1
 */
class CountTheNumberOfScoreCombinations {

    static int scoreCombinationCount(int score, int[] points) {
        int[][] scores = new int[score+1][points.length]
        scores[0] = [1,1,1]
        println scores
        println "i j k"
        for (int i = 1; i < scores.length; i++) {
            for (int j = 0; j < points.size(); j++) {
                int ways = 0
                for (int k = j; k >= 0 ; k--) {
                    int subScore = i - points[k]
                    if (subScore < 0) continue
                    ways += scores[subScore][k]
                }
                scores[i][j] += ways
            }
        }
        scores[score][points.length - 1]
    }

}
