package com.lagostout.elementsofprogramminginterviews.dynamicprogramming

/**
 * Problem 17.1.3 page 313
 */
class CountNumberOfUniquePlayPointSequencesThatResultInScore {

    static int sequenceCount(int score, List<Integer> allPlayPoints) {
        List<Integer> cache = [1]
        if (score == 0) return cache[0]
        1.upto(score) { currentScore ->
//            println "currentScore $currentScore"
            int currentScoreSequenceCount = 0
            cache[currentScore] = currentScoreSequenceCount
            for (int playPoints in allPlayPoints) {
                int previousScore = currentScore - playPoints
                if (previousScore >= 0) {
                    currentScoreSequenceCount += cache[previousScore]
                }
            }
            cache[currentScore] = currentScoreSequenceCount
        }
        cache[score]
    }

}
