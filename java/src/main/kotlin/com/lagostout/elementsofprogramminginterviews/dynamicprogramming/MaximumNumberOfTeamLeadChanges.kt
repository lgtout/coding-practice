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
    val cache: MutableList<List<TeamPointCounts>> = mutableListOf()
    (0..firstTeamScore).forEach {
        currentFirstTeamScore ->
        val secondTeamScoresForCurrentFirstTeamScore:
                MutableList<TeamPointCounts> = mutableListOf()
        cache.add(secondTeamScoresForCurrentFirstTeamScore)
        (0..secondTeamScore).forEach secondTeamScoreLoop@ {
            currentSecondTeamScore ->
            if (currentFirstTeamScore == 0 &&
                    currentSecondTeamScore == 0) {
                secondTeamScoresForCurrentFirstTeamScore[currentSecondTeamScore] =
                        TeamPointCounts(0, 0)
                return@secondTeamScoreLoop
            }
            // I'm not sure if I'm happy with this approach to look backward in score.
            // It's much less readable than I'd like.
            // Alternate approach would be to subtract combinations of play points
            // from both scores at once. So we'd be moving in 2 dimensions at once,
            // rather than one dimension as in the current approach. For this we'd
            // need to add 0 to the possible play points to allow for consecutive
            // point scoring by the same team.  But we'd need to not count those 0
            // point scores as lead changes.
            // Is the alternate approach worth exploring? :\  Not sure. Maybe get
            // current approach completely tested then revisit the question.
            fun improveLeadChangeCountIfPossible(
                    teamSubscore: Int,
                    getSubscoreTeamPointCounts: (Int) -> TeamPointCounts,
                    getCurrentScoreTeamPointCounts: (TeamPointCounts) -> TeamPointCounts,
                    teamPointCountsOfSubscoreWithGreatestLeadChangeCount: TeamPointCounts?): TeamPointCounts? {
                if (teamSubscore > 0) {
                    val subscoreTeamPointCounts = getSubscoreTeamPointCounts(teamSubscore)
                    if (teamPointCountsOfSubscoreWithGreatestLeadChangeCount == null) {
                                return subscoreTeamPointCounts
                    }
                    else if (subscoreTeamPointCounts.leadChangeCount >
                            teamPointCountsOfSubscoreWithGreatestLeadChangeCount.leadChangeCount) {
                        return getCurrentScoreTeamPointCounts(
                                teamPointCountsOfSubscoreWithGreatestLeadChangeCount)
                    }
                }
                return null
            }
            var teamPointCountsOfSubscoreWithGreatestLeadChangeCount: TeamPointCounts? = null
            possiblePlayPoints.forEach {
                teamPointCountsOfSubscoreWithGreatestLeadChangeCount =
                        improveLeadChangeCountIfPossible(
                                currentFirstTeamScore - it,
                                { teamSubscore ->
                                    cache[teamSubscore][currentSecondTeamScore]
                                },
                                { currentTeamPointCountsOfSubscoreWithGreatestLeadChangeCount ->
                                    currentTeamPointCountsOfSubscoreWithGreatestLeadChangeCount.copy(
                                            firstTeam =
                                            currentTeamPointCountsOfSubscoreWithGreatestLeadChangeCount.firstTeam + 1)
                                },
                                teamPointCountsOfSubscoreWithGreatestLeadChangeCount)
                teamPointCountsOfSubscoreWithGreatestLeadChangeCount =
                        improveLeadChangeCountIfPossible(
                                currentSecondTeamScore - it,
                                { teamSubscore ->
                                    cache[currentFirstTeamScore][teamSubscore]
                                },
                                { currentTeamPointCountsOfSubscoreWithGreatestLeadChangeCount ->
                                    currentTeamPointCountsOfSubscoreWithGreatestLeadChangeCount.copy(
                                            secondTeam =
                                            currentTeamPointCountsOfSubscoreWithGreatestLeadChangeCount.secondTeam + 1)
                                },
                                teamPointCountsOfSubscoreWithGreatestLeadChangeCount)
            }
        }
    }
    return 0
}