package com.lagostout.elementsofprogramminginterviews.dynamicprogramming

import org.apache.commons.math3.util.CombinatoricsUtils

/**
 * Problem 17.2 p313
 */
class CountTheNumberOfPlayPointCombinationsThatMakeAScoreUsingLessSpace {

    static int combinationCount(int scoreToFindNumberOfWaysToCombinePlayPointsOf,
                                Set<Integer> playPointsToCombine) {
        def numberOfPlayPointsToChooseFrom = playPointsToCombine.size()
        def cacheOfNumberOfWaysToCombinePlayPointsIntoScores = [1]
        // Build the cache from scoreToFindNumberOfWaysToCombinePlayPointsOf
        // of 1 up to scoreToFindNumberOfWaysToCombinePlayPointsOf.
        if (scoreToFindNumberOfWaysToCombinePlayPointsOf > 0) {
            1.upto(scoreToFindNumberOfWaysToCombinePlayPointsOf) {
                currentScore ->
                    // Each time we start computing a new
                    // scoreToFindNumberOfWaysToCombinePlayPointsOf, we reset the
                    // number of points to pick from the ones provided.
                    def numberOfPlayPointsToChoose = 1
                    def numberOfWaysToGetAScoreFromPlayPoints = 0
                    while (numberOfPlayPointsToChoose <=
                            numberOfPlayPointsToChooseFrom) {
                        def iteratorOfCombinationsOfPlayPointIndices =
                                CombinatoricsUtils.
                                        combinationsIterator(
                                                numberOfPlayPointsToChooseFrom,
                                                numberOfPlayPointsToChoose)
                        def sumOfNumberOfCombinationsOfPlayPointsToGetCurrentScore = 0
                        for (combinationOfPlayPointIndices in
                                iteratorOfCombinationsOfPlayPointIndices) {
                            def combinationOfPlayPoints =
                                    combinationOfPlayPointIndices.collect {
                                        playPointsToCombine[it]
                                    } as HashSet
                            def currentScoreBeforeCurrentNumberOfCombinationsOfPlayPointsAdded =
                                    currentScore - (int) combinationOfPlayPoints.sum()
                            sumOfNumberOfCombinationsOfPlayPointsToGetCurrentScore +=
                                    (currentScoreBeforeCurrentNumberOfCombinationsOfPlayPointsAdded >= 0 ?
                                            cacheOfNumberOfWaysToCombinePlayPointsIntoScores[
                                                    currentScoreBeforeCurrentNumberOfCombinationsOfPlayPointsAdded] : 0)
                        }
                        numberOfWaysToGetAScoreFromPlayPoints +=
                                ((numberOfPlayPointsToChoose % 2 == 1 ? 1 : -1) *
                                        sumOfNumberOfCombinationsOfPlayPointsToGetCurrentScore)
                        numberOfPlayPointsToChoose++
                    }
                    cacheOfNumberOfWaysToCombinePlayPointsIntoScores[currentScore] =
                            numberOfWaysToGetAScoreFromPlayPoints
            }
        }
        return cacheOfNumberOfWaysToCombinePlayPointsIntoScores.last()
    }

}
