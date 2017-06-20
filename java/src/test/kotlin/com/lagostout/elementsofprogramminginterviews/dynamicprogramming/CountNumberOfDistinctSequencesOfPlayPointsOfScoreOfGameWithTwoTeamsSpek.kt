package com.lagostout.elementsofprogramminginterviews.dynamicprogramming

import com.lagostout.elementsofprogramminginterviews.dynamicprogramming.CountNumberOfDistinctSequencesOfPlayPointsOfScoreOfGameWithTwoTeams.Team.*
import com.lagostout.elementsofprogramminginterviews.dynamicprogramming.CountNumberOfDistinctSequencesOfPlayPointsOfScoreOfGameWithTwoTeams.PlayPoints
import org.jetbrains.spek.api.Spek

data class DataRow(val firstTeamScore:Int, val secondTeamScore: Int,
                   val sequenceCount: Int, val possiblePlayPoints: List<Int>,
                   val sequences: List<List<PlayPoints>>?)

class CountNumberOfDistinctSequencesOfPlayPointsOfScoreOfGameWithTwoTeamsSpek : Spek({
    given("the game score for two teams") {
        allData.forEach {
            on("computing the number of distinct sequences of play points that result in the score") {
                it("returns the number of distinct sequences") {

                }
            }
        }
    }
})

private val allPlayPoints = listOf(2,3,4)
private val allData = listOf(
        DataRow(0, 0, 1, allPlayPoints, listOf(listOf())),
        DataRow(0, 1, 0, allPlayPoints, null),
        DataRow(0, 2, 1, allPlayPoints, listOf(listOf(PlayPoints(2,B)))),
        DataRow(0, 3, 1, allPlayPoints, listOf(listOf(PlayPoints(3,B)))),
        DataRow(0, 4, 1, allPlayPoints,
                listOf(listOf(PlayPoints(4,B)),
                        listOf(PlayPoints(2,B), PlayPoints(2,B)))),
        DataRow(0, 5, 2, allPlayPoints,
                listOf(listOf(PlayPoints(2, B), PlayPoints(3,B)),
                        listOf(PlayPoints(3,B), PlayPoints(2,B)))),
        DataRow(1, 0, 0, allPlayPoints, null),
        DataRow(1, 2, 0, allPlayPoints, null),
        DataRow(1, 4, 0, allPlayPoints, null),
        DataRow(2, 0, 1, allPlayPoints, listOf(listOf(PlayPoints(2,A)))),
        DataRow(2, 1, 0, allPlayPoints, null),
        DataRow(2, 2, 2, allPlayPoints, listOf(
                listOf(PlayPoints(2,A), PlayPoints(2,B)),
                listOf(PlayPoints(2,A), PlayPoints(2,B)))
        )
)
