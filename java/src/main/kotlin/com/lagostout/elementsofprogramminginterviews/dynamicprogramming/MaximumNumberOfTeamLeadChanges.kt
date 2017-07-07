package com.lagostout.elementsofprogramminginterviews.dynamicprogramming

/**
 * Problem 17.1.5 page 313
 */

// Strictly speaking, it's not necessary to compute unreachable scores as null.
// But it allows the cache to contain information about both the lead change
// count and reachability.  That's all the better, though not strictly relevant.

class  MaximumNumberOfTeamLeadChanges {

    companion object {

        enum class Team {
            A, B
        }

        data class GameScoreLeadChanges(
                val teamThatMostRecentlyScored: Team? = null,
                val leadChangeCount: Int = 0,
                val teamAPlayScoreCount: Int = 0,
                val teamBPlayScoreCount: Int = 0,
                val teamAScore: Int,
                val teamBScore: Int) {

            fun smallerPlayScoreCount(): Int {
                return minOf(teamAPlayScoreCount, teamBPlayScoreCount)
            }

            fun playScoredBy(
                    team: Team, teamAScore: Int, teamBScore: Int):
                   GameScoreLeadChanges {
                return copy(teamThatMostRecentlyScored = team,
                        leadChangeCount =
                            leadChangeCount +
                                if (teamThatMostRecentlyScored != null &&
                                        teamThatMostRecentlyScored != team)
                                    1 else 0,
                        teamAPlayScoreCount =  teamAPlayScoreCount + if (team == Team.A) 1 else 0,
                        teamBPlayScoreCount =  teamBPlayScoreCount + if (team == Team.B) 1 else 0,
                        teamAScore = teamAScore, teamBScore = teamBScore)
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
                            leadChanges?.playScoredBy(Team.A, teamAScore, teamBScore)
                        }
                    },
                    possiblePlayScores.map {
                        val teamBSubscore = teamBScore - it
                        if (teamBSubscore < 0) null
                        else {
                            val leadChanges = cacheOfGameScoreLeadChanges[teamAScore][teamBSubscore]
                            leadChanges?.playScoredBy(Team.B, teamAScore, teamBScore)
                        }
                    }).flatten().filterNotNull().let {
                        if (it.isEmpty())
                            null
                        else it.reduce {
                            acc, value ->
                            val playScoreCount = if (value.teamAPlayScoreCount == 0 ||
                                    value.teamBPlayScoreCount == 0) {
                                maxOf(value.teamAPlayScoreCount, value.teamBPlayScoreCount)
                            } else value.smallerPlayScoreCount()
                            if (value.smallerPlayScoreCount() >
                                    acc.smallerPlayScoreCount()) value else acc
                        }
                    })
                }
            }
            println(cacheOfGameScoreLeadChanges.joinToString(separator = "\n"))
            return cacheOfGameScoreLeadChanges[
                    teamAGameScore][teamBGameScore]?.leadChangeCount ?: 0
        }
    }
}
