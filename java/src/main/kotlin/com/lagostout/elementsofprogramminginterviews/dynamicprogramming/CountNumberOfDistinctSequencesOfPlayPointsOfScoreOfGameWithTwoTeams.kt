package com.lagostout.elementsofprogramminginterviews.dynamicprogramming

/**
 * Problem 17.1.5 page 313
 */
fun countOfDistinctSequences(firstTeamScore: Int, secondTeamScore: Int,
                             possiblePlayPoints: List<Int>): Int {
    val sequenceCounts = mutableListOf<MutableList<Int>>()
    (0..firstTeamScore).forEach { currentFirstTeamScore ->
        sequenceCounts.add(mutableListOf())
        (0..secondTeamScore).forEach { currentSecondTeamScore ->
            sequenceCounts[currentFirstTeamScore].add(
                    if (currentFirstTeamScore == 0 && currentSecondTeamScore == 0) 1
                    else possiblePlayPoints.fold(0) {
                        accumulatedSequenceCount, playPoints ->
                        var sequenceCount = accumulatedSequenceCount
                        val firstTeamPreviousScore = currentFirstTeamScore - playPoints
                        val secondTeamPreviousScore = currentSecondTeamScore - playPoints
                        sequenceCount += if (firstTeamPreviousScore < 0) 0
                        else sequenceCounts[firstTeamPreviousScore][currentSecondTeamScore]
                        sequenceCount += if (secondTeamPreviousScore < 0) 0
                        else sequenceCounts[currentFirstTeamScore][secondTeamPreviousScore]
                        sequenceCount
                    })
        }
    }
    return sequenceCounts[firstTeamScore][secondTeamScore]
}

fun countOfDistinctSequencesUsingBruteForce(firstTeamScore: Int, secondTeamScore: Int,
                                            possiblePlayPoints: List<Int>): Int {
    // 0 ways to get a negative score.
    if (firstTeamScore < 0 || secondTeamScore < 0) return 0
    if (firstTeamScore == 0 && secondTeamScore == 0) return 1
    val sequenceCount = possiblePlayPoints.fold(0) {
        sequenceCount, playPoints ->
        val firstTeamSequenceCount = if (firstTeamScore == 0) 0
        else countOfDistinctSequencesUsingBruteForce(
                firstTeamScore - playPoints,
                secondTeamScore, possiblePlayPoints)
        val secondTeamSequenceCount = if (secondTeamScore == 0) 0
        else countOfDistinctSequencesUsingBruteForce(
                firstTeamScore,
                secondTeamScore - playPoints,
                possiblePlayPoints)
        sequenceCount + firstTeamSequenceCount +
                secondTeamSequenceCount
    }
    return sequenceCount
}
