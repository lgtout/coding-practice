package com.lagostout.elementsofprogramminginterviews.dynamicprogramming

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import kotlin.test.assertEquals

class MaximumNumberOfTeamLeadChangesSpek : Spek({
    describe("maximumNumberOfTeamLeadChanges") {
        testCases.forEach {
            (score, expected, possiblePlayPoints) ->
            given("$score") {
                it("computes $expected as the maximum " +
                        "possible number of team lead changes") {
                    assertEquals(expected, MaximumNumberOfTeamLeadChanges.
                            maximumNumberOfTeamLeadChanges(
                                    score.firstTeam, score.secondTeam, possiblePlayPoints))
                }
            }
        }
    }
}) {
    companion object {
        private data class GameScore(val firstTeam:Int = 0, val secondTeam:Int = 0)
        private data class TestCase(val gameScore: GameScore, val expected:Int) {
            val possiblePlayPoints = listOf(2,3,4)
            operator fun component3() = possiblePlayPoints
        }
        private val testCases = listOf(
                TestCase(GameScore(), 0),
                TestCase(GameScore(1,0), 0),
                TestCase(GameScore(2,0), 0),
                TestCase(GameScore(4,0), 0),
                TestCase(GameScore(0,1), 0),
                TestCase(GameScore(1,1), 0),
                TestCase(GameScore(2,1), 0),
                TestCase(GameScore(4,1), 0),
                TestCase(GameScore(2,2), 1),
                TestCase(GameScore(2,3), 1),
                TestCase(GameScore(4,3), 2),
                TestCase(GameScore(3,4), 2),
                TestCase(GameScore(2,4), 2),
                TestCase(GameScore(4,4), 3),
                TestCase(GameScore(3,7), 2),
                TestCase(GameScore(10,6), 6),
                null
        ).filterNotNull()
    }
}