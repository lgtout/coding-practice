package com.lagostout.elementsofprogramminginterviews.sorting

/* Problem 14.8 page 253 */

fun playerPlacementSubjectToConstraintIsPossible(
        team1: List<Int>, team2: List<Int>): Boolean {
    if (team1.size != team2.size) return false
    val sortedTeam1 = team1.sorted()
    val sortedTeam2 = team2.sorted()
    val rows = if (sortedTeam1.first() < sortedTeam2.first())
        Pair(sortedTeam1, sortedTeam2)
    else Pair(sortedTeam2, sortedTeam1)
    val pairs = rows.first.zip(rows.second)
    var result = true
    for (pair in pairs) {
        if (pair.first >= pair.second) {
            result = false
            break
        }
    }
    return result
}