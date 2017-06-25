package com.lagostout.elementsofprogramminginterviews.dynamicprogramming

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.on
import org.jetbrains.spek.api.dsl.it
import kotlin.test.assertEquals

private data class DataRow(val firstTeamScore:Int,
                   val secondTeamScore: Int,
                   val sequenceCount: Int)

class CountNumberOfDistinctSequencesOfPlayPointsOfScoreOfGameWithTwoTeamsSpek : Spek({
    describe("countOfDistinctSequences") {
        allData.forEach {
            given("the game score for two teams  ${it.firstTeamScore}, ${it.secondTeamScore}") {
                it("computes ${it.sequenceCount} as the number of distinct sequences " +
                        "of play points that result in the score") {
                    val sequenceCount = countOfDistinctSequences(
                                    it.firstTeamScore, it.secondTeamScore, listOf(2,3,4))
                    assertEquals(it.sequenceCount, sequenceCount)
                }
            }
        }
    }
})

class CountNumberOfDistinctSequencesOfPlayPointsOfScoreOfGameWithTwoTeamsUsingBruteForceSpek : Spek({
    describe("countOfDistinctSequencesUsingBruteForce") {
        allData.forEach {
            given("the game score for two teams ${it.firstTeamScore}, ${it.secondTeamScore}") {
                it("computes ${it.sequenceCount} as the number of distinct sequences " +
                        "of play points that result in the score") {
                    val sequenceCount = countOfDistinctSequencesUsingBruteForce(
                                    it.firstTeamScore, it.secondTeamScore, listOf(2,3,4))
                    assertEquals(it.sequenceCount, sequenceCount)
                }
            }
        }
    }
})

private val allData = listOf(
        DataRow(0, 0, 1),
        DataRow(0, 1, 0),
        DataRow(0, 2, 1),
        DataRow(0, 3, 1),
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
