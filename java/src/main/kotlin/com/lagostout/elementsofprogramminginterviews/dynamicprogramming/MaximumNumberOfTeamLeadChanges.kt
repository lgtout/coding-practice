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
                secondTeamScoresForCurrentFirstTeamScore.add(TeamPointCounts(0, 0))
                return@secondTeamScoreLoop
            }
            // Alternate approach would be to subtract combinations of play points
            // from both scores at once. So we'd be moving in 2 dimensions at once,
            // rather than one dimension as in the current approach. For this we'd
            // need to add 0 to the possible play points to allow for consecutive
            // point scoring by the same team.  But we'd need to not count those 0
            // point scores as lead changes.
            secondTeamScoresForCurrentFirstTeamScore.add(listOf(
                    possiblePlayPoints.map {
                        currentFirstTeamScore - it
                    }.map {
                        firstTeamSubscore ->
                        if (firstTeamSubscore < 0) null
                        else {
                            val teamPointCounts = cache[firstTeamSubscore][currentSecondTeamScore]
                            teamPointCounts?.copy(firstTeam = teamPointCounts.firstTeam + 1)
                        }
                    },
                    possiblePlayPoints.map {
                        currentSecondTeamScore - it
                    }.map {
                        secondTeamSubscore ->
                        if (secondTeamSubscore < 0) null
                        else {
                            val teamPointCounts = cache[currentFirstTeamScore][secondTeamSubscore]
                            teamPointCounts?.copy(secondTeam = teamPointCounts.secondTeam + 1)
                        }
                    }
            ).flatten().filterNotNull().let {
                if (it.isEmpty()) null
                else it.reduce {
                    acc, value ->
                    if (value.leadChangeCount > acc.leadChangeCount) value else acc
                }
            })
        }
    }
    return 0
}