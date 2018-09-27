package com.lagostout.elementsofprogramminginterviews.honors

/* Problem 25.14 page 464 */

fun fairBonuses(linesOfCode: List<Int>): List<Int> {
    val bonuses = MutableList(linesOfCode.size) { 1 }

    // O(1) space
    val ascendingIndices = generateSequence(
        { 0.takeIf { linesOfCode.lastIndex > 0} },
        { (it + 1).takeIf { it < linesOfCode.lastIndex } })

    // Left to right
    ascendingIndices.forEach {
        val index = it
        val nextIndex = index + 1
        val value = linesOfCode[index]
        val nextValue = linesOfCode[nextIndex]
        if (nextValue == value)
            bonuses[nextIndex] = bonuses[index]
        else if (nextValue > value)
            bonuses[nextIndex] = bonuses[index] + 1
    }

    // O(1) space
    val descendingIndices = generateSequence(
        { linesOfCode.lastIndex.takeIf { it > 0} },
        { (it - 1).takeIf { it >= 1 } })

    // Right to left
    descendingIndices.forEach {
        val index = it
        val previousIndex = index - 1
        val value = linesOfCode[index]
        val previousValue = linesOfCode[previousIndex]
        if (previousValue == value)
            bonuses[previousIndex] = maxOf(bonuses[previousIndex], bonuses[index])
        else if (previousValue > value)
            bonuses[previousIndex] = maxOf(bonuses[previousIndex], bonuses[index] + 1)
    }
    return bonuses
}