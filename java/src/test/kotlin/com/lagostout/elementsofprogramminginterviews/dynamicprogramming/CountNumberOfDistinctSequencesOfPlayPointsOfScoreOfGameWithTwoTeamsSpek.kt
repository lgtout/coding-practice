package com.lagostout.elementsofprogramminginterviews.dynamicprogramming

import org.jetbrains.spek.api.Spek
import kotlin.test.assertEquals

data class DataRow(val firstTeamScore:Int,
                   val secondTeamScore: Int,
                   val sequenceCount: Int)

class CountNumberOfDistinctSequencesOfPlayPointsOfScoreOfGameWithTwoTeamsSpek : Spek({
    allData.forEach {
        given("the game score for two teams  ${it.firstTeamScore}, ${it.secondTeamScore}") {
            on("computing the number of distinct sequences of play points that " +
                    "result in the score") {
                val sequenceCount = CountNumberOfDistinctSequencesOfPlayPointsOfScoreOfGameWithTwoTeams.
                        countOfDistinctSequences(
                        it.firstTeamScore, it.secondTeamScore, listOf(2,3,4))
                it("returns the number of distinct sequences") {
                    assertEquals(it.sequenceCount, sequenceCount)
                }
            }
        }
    }
})

class CountNumberOfDistinctSequencesOfPlayPointsOfScoreOfGameWithTwoTeamsUsingBruteForceSpek : Spek({
    allData.forEach {
        given("the game score for two teams") {
            on("computing the number of distinct sequences of play points that " +
                    "result in the score using brute force") {
                val sequenceCount = CountNumberOfDistinctSequencesOfPlayPointsOfScoreOfGameWithTwoTeams.
                        countOfDistinctSequencesUsingBruteForce(
                        it.firstTeamScore, it.secondTeamScore, listOf(2,3,4))
                it("returns the number of distinct sequences") {
                    assertEquals(it.sequenceCount, sequenceCount)
                }
            }
        }
    }
})

private val allData = listOf(
        DataRow(0, 0, 1),
        DataRow(0, 1, 0),
        DataRow(0, 2, 1)
        ,DataRow(0, 3, 1),
        DataRow(0, 4, 2),
        DataRow(0, 5, 2),
        DataRow(1, 0, 0),
        DataRow(1, 2, 0),
        DataRow(1, 4, 0),
        DataRow(2, 0, 1),
        DataRow(2, 1, 0),
        DataRow(2, 2, 2),
        DataRow(2, 4, 5),
        DataRow(2, 4, 5),
        DataRow(4, 2, 5),
        DataRow(5, 2, 6),
        DataRow(4, 5, 18),
        DataRow(5, 5, 24)
)
