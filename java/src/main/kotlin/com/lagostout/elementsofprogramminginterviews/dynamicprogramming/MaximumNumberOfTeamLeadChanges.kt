package com.lagostout.elementsofprogramminginterviews.dynamicprogramming

/**
 * Problem 17.1.5 page 313
 */

// Strictly speaking, it's not necessary to compute unreachable scores as null.
// But it allows the cache to contain information about both the lead change
// count and reachability.  That's all the better, though not strictly relevant.
// As an alternative approach, we could have GameScoreLeadChanges return null
// leadChangeCount() for unreachable game scores.

class  MaximumNumberOfTeamLeadChanges {

    companion object {

        enum class Team {
            A, B
        }

        data class GameScoreLeadChanges(
                val teamThatMostRecentlyScored: Team? = null,
                val teamAPlayScoreCount: Int = 0,
                val teamBPlayScoreCount: Int = 0,
                // Next fields for debugging only
                val teamAScore: Int = 0,
                val teamBScore: Int = 0) {

            val leadChangeCount: Int
                get() {
                    val pairCount = Math.min(teamAPlayScoreCount, teamBPlayScoreCount)
                    val changesWithinPairs = pairCount
                    val changesBetweenPairs = if (pairCount > 0) pairCount - 1 else 0
                    val changesBetweenPairsAndNonPairs =
                            if (pairCount > 0 && teamAPlayScoreCount != teamBPlayScoreCount) 1 else 0
                    val changes = changesWithinPairs + changesBetweenPairs +
                            changesBetweenPairsAndNonPairs
                    return changes
                }

            fun smallerPlayScoreCount(): Int {
                return minOf(teamAPlayScoreCount, teamBPlayScoreCount)
            }

            fun playScoredBy(team: Team, playScore: Int):
                   GameScoreLeadChanges {
                return copy(teamThatMostRecentlyScored = team,
                        teamAPlayScoreCount =  teamAPlayScoreCount + if (team == Team.A) 1 else 0,
                        teamBPlayScoreCount =  teamBPlayScoreCount + if (team == Team.B) 1 else 0,
                        teamAScore = teamAScore + if (team == Team.A) playScore else 0,
                        teamBScore = teamBScore + if (team == Team.B) playScore else 0)
            }
        }

        fun maximumNumberOfTeamLeadChanges(teamAGameScore: Int, teamBGameScore: Int,
                                           possiblePlayScores: List<Int>): Int {
            val cacheOfGameScoreLeadChanges: MutableList<List<GameScoreLeadChanges?>> =
                    mutableListOf()
            (0..teamAGameScore).forEach {
                teamAScore ->
                val leadChangesForCurrentTeamAScore: MutableList<GameScoreLeadChanges?> =
                        mutableListOf()
                cacheOfGameScoreLeadChanges.add(leadChangesForCurrentTeamAScore)
                (0..teamBGameScore).forEach teamBScoreLoop@ {
                    teamBScore ->
                    if (teamAScore == 0 && teamBScore == 0) {
                        leadChangesForCurrentTeamAScore.add(GameScoreLeadChanges(
                                teamAScore = teamAScore, teamBScore = teamBScore))
                        return@teamBScoreLoop
                    }
                    // Alternate approach would be to subtract combinations of play points
                    // from both scores at once. So we'd be moving in 2 dimensions at once,
                    // rather than one dimension as in the current approach. For this we'd
                    // need to include 0 in the possible play points to allow for consecutive
                    // point scoring by the same team.  But later we'd need to ignore those
                    // 0 point scores as lead changes.
                    leadChangesForCurrentTeamAScore.add(listOf(possiblePlayScores.map {
                        val teamASubscore = teamAScore - it
                        if (teamASubscore < 0) null
                        else {
                            val leadChanges = cacheOfGameScoreLeadChanges[teamASubscore][teamBScore]
                            leadChanges?.playScoredBy(Team.A, it)
                        }
                    },
                    possiblePlayScores.map {
                        val teamBSubscore = teamBScore - it
                        if (teamBSubscore < 0) null
                        else {
                            val leadChanges = cacheOfGameScoreLeadChanges[teamAScore][teamBSubscore]
                            leadChanges?.playScoredBy(Team.B, it)
                        }
                    }).flatten().map{
                        it // For debugging
                    }.filterNotNull().let {
                        if (it.isEmpty())
                            null
                        else it.reduce {
                            acc, value ->
                            if ((value.teamAPlayScoreCount == 0 &&
                                    (value.teamBPlayScoreCount > acc.teamBPlayScoreCount)) ||
                                    (value.teamAPlayScoreCount == 0 &&
                                            (value.teamAPlayScoreCount > acc.teamAPlayScoreCount)) ||
                                    (value.smallerPlayScoreCount() > acc.smallerPlayScoreCount())) {
                                value
                            } else acc
                        }
                    }.also {
                        it // For debugging
                    })
                }
            }
//            println(cacheOfGameScoreLeadChanges.joinToString(separator = "\n"))
            return cacheOfGameScoreLeadChanges[
                    teamAGameScore][teamBGameScore]?.leadChangeCount ?: 0
        }
    }
}
