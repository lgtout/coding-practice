package com.lagostout.elementsofprogramminginterviews.dynamicprogramming

/**
 * Problem 17.1.5 page 313
 */

// Strictly speaking, it's not necessary to compute unreachable scores as null.
// But it allows the cache to contain information about both the lead change
// count and reachability.  That's all the better, though not strictly relevant.

fun maximumNumberOfTeamLeadChanges(firstTeamGameScore: Int, secondTeamGameScore: Int,
                                   possiblePlayScores: List<Int>): Int {
    data class GameScoreLeadChanges(val countOfFirstTeamScoringPlays: Int,
                                    val countOfSecondTeamScoringPlays: Int) {
        val leadChangeCount:Int
            get() {
                return if (countOfFirstTeamScoringPlays == 0 ||
                        countOfSecondTeamScoringPlays == 0)
                    0
                else if (countOfFirstTeamScoringPlays ==
                        countOfSecondTeamScoringPlays)
                    countOfFirstTeamScoringPlays
                else
                    Math.min(countOfFirstTeamScoringPlays,
                        countOfSecondTeamScoringPlays) + 1
            }
    }
    val cacheOfGameScoreLeadChanges: MutableList<List<GameScoreLeadChanges?>> =
            mutableListOf()
    (0..firstTeamGameScore).forEach {
        firstTeamScore ->
        val secondTeamLeadChanges: MutableList<GameScoreLeadChanges?> =
                mutableListOf()
        cacheOfGameScoreLeadChanges.add(secondTeamLeadChanges)
        // We only need to compute firstTeamLeadChanges once per
        // row i.e. once per firstTeamSubscore.  It is unchanged
        // from the firstTeamLeadChanges computed for the 0th
        // column of each row.
        val firstTeamLeadChanges = possiblePlayScores.map {
            firstTeamScore - it
        }.map {
            firstTeamSubscore ->
            if (firstTeamSubscore < 0)
                null
            else {
                cacheOfGameScoreLeadChanges[firstTeamSubscore][0]?.let {
                    it.copy(countOfFirstTeamScoringPlays =
                    it.countOfFirstTeamScoringPlays + 1)
                }
            }
        }
        (0..secondTeamGameScore).forEach secondTeamScoreLoop@ {
            secondTeamScore ->
            if (firstTeamScore == 0 &&
                    secondTeamScore == 0) {
                secondTeamLeadChanges.add(GameScoreLeadChanges(
                        0, 0))
                return@secondTeamScoreLoop
            }
            // Alternate approach would be to subtract combinations of play points
            // from both scores at once. So we'd be moving in 2 dimensions at once,
            // rather than one dimension as in the current approach. For this we'd
            // need to include 0 in the possible play points to allow for consecutive
            // point scoring by the same team.  But later we'd need to ignore those
            // 0 point scores as lead changes.
            secondTeamLeadChanges.add(listOf(
                    firstTeamLeadChanges,
                    possiblePlayScores.map {
                        secondTeamScore - it
                    }.map {
                        secondTeamSubscore ->
                        if (secondTeamSubscore < 0)
                            null
                        else {
                            cacheOfGameScoreLeadChanges[
                                    firstTeamScore][secondTeamSubscore]?.let {
                                it.copy(countOfSecondTeamScoringPlays =
                                it.countOfSecondTeamScoringPlays + 1)
                            }
                        }
                    }
            ).flatten().filterNotNull().let {
                if (it.isEmpty())
                    null
                else it.reduce {
                    acc, value ->
                    if (value.leadChangeCount > acc.leadChangeCount) value else acc
                }
            })
        }
    }
    return cacheOfGameScoreLeadChanges[
            firstTeamGameScore][secondTeamGameScore]?.leadChangeCount ?: 0
}