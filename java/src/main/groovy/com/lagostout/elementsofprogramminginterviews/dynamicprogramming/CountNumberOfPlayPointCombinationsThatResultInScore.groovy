package com.lagostout.elementsofprogramminginterviews.dynamicprogramming

/**
 * Problem 17.1 page 311
 */
class CountNumberOfPlayPointCombinationsThatResultInScore {

    /**
     * Faster implementation: O(sn)
     * @param score
     * @param points
     * @return
     */
    static int scoreCombinationCount(int score, int[] points) {
        int[][] scores = new int[score+1][points.length]
        scores[0] = [1,1,1]
        for (int i = 1; i < scores.length; i++) {
            for (int j = 0; j < points.size(); j++) {
                int prevScoreIndexWithCurrPoints = i - points[j]
                int ways = prevScoreIndexWithCurrPoints >= 0 ?
                        scores[prevScoreIndexWithCurrPoints][j] : 0
                int prevPointsIndexWithCurrPoints = j - 1
                ways += prevPointsIndexWithCurrPoints >= 0 ?
                        scores[i][prevPointsIndexWithCurrPoints] : 0
                scores[i][j] += ways
            }
        }
        scores[score][points.length - 1]
    }

    /**
     * Slower implementation: O(sn^2)
     * @param score
     * @param points
     * @return
     */
    static int scoreCombinationCount_v1(int score, int[] points) {
        int[][] scores = new int[score+1][points.length]
        scores[0] = [1,1,1]
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
