package com.lagostout.elementsofprogramminginterviews.dynamicprogramming

object CountNumberOfDistinctSequencesOfPlayPointsOfScoreOfGameWithTwoTeams {

    // TODO Start with recursive solution.  Then add in caching.
    fun numberOfDistinctSequences():Int {
        return 0
    }

    fun toPlayPointsSequence(): List<PlayPoints> {
        return listOf()
    }

    enum class Team {
        A, B
    }

    data class PlayPoints(val points: Int, val team: Team)

}




