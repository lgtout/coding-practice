package com.lagostout.elementsofprogramminginterviews.dynamicprogramming

/**
 * Problem 17.1.5 page 313
 */
fun maximumNumberOfTeamLeadChanges(firstTeamScore: Int, secondTeamScore: Int,
                                   possiblePlayPoints: List<Int>): Int {
    data class TeamPointCounts(val firstTeam: Int, val secondTeam: Int) {
        val leadChangeCount:Int
            get() {
                return Math.min(firstTeam, secondTeam) + 1
            }
    }
    val cache: MutableList<List<TeamPointCounts?>> = mutableListOf()
    (0..firstTeamScore).forEach {
        currentFirstTeamScore ->
        val secondTeamScoresForCurrentFirstTeamScore:
                MutableList<TeamPointCounts?> = mutableListOf()
        cache.add(secondTeamScoresForCurrentFirstTeamScore)
        (0..secondTeamScore).forEach secondTeamScoreLoop@ {
            currentSecondTeamScore ->
            if (currentFirstTeamScore == 0 &&
                    currentSecondTeamScore == 0) {
                secondTeamScoresForCurrentFirstTeamScore[
                        currentSecondTeamScore] = TeamPointCounts(0, 0)
                return@secondTeamScoreLoop
            }
            // Alternate approach would be to subtract combinations of play points
            // from both scores at once. So we'd be moving in 2 dimensions at once,
            // rather than one dimension as in the current approach. For this we'd
            // need to add 0 to the possible play points to allow for consecutive
            // point scoring by the same team.  But we'd need to not count those 0
            // point scores as lead changes.
            secondTeamScoresForCurrentFirstTeamScore[currentSecondTeamScore] = listOf(
                    possiblePlayPoints.map {
                        currentFirstTeamScore - it
                    }.map {
                        if (it < 0) null
                        else {
                            val teamPointCounts = cache[it][currentSecondTeamScore]
                            teamPointCounts?.copy(firstTeam = teamPointCounts.firstTeam + 1)
                        }
                    },
                    possiblePlayPoints.map {
                        currentSecondTeamScore - it
                    }.map {
                        if (it < 0) null
                        else {
                            val teamPointCounts = cache[currentFirstTeamScore][it]
                            teamPointCounts?.copy(secondTeam = teamPointCounts.secondTeam + 1)
                        }
                    }
            ).flatten().filterNotNull().reduce { acc, value ->
                if (value.leadChangeCount > acc.leadChangeCount) value else acc
            }
        }
    }
    return 0
}