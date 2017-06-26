package com.lagostout.elementsofprogramminginterviews.dynamicprogramming

/**
 * Problem 17.1.5 page 313
 */
fun maximumNumberOfTeamLeadChanges(firstTeamScore: Int, secondTeamScore: Int): Int {
    data class TeamPointCounts(val firstTeam: Int, val secondTeam: Int)
    val cache: MutableList<List<TeamPointCounts>> = mutableListOf()
    (0..firstTeamScore).forEach {
        firstTeamSubscore ->
        val row: MutableList<TeamPointCounts> = mutableListOf()
        cache.add(row)
        secondTeamScoreLoop@ (0..secondTeamScore).forEach  {
            secondTeamSubscore ->
            if (firstTeamSubscore == 0 && secondTeamSubscore == 0) {
                row[secondTeamSubscore] = TeamPointCounts(0, 0)
                return@secondTeamScoreLoop
            }
//            for
        }
    }
    return 0
}