package com.lagostout.elementsofprogramminginterviews.greedyalgorithmsandinvariants

/**
 * Problem 18.4.2 page 346
 */

fun canPickThreeWithNoRepetitionThatAddUpToSum(
        list: List<Int>, sum: Int): Boolean {
    return findCombinationsOfThreeWithNoRepetitionThatAddUpToSum(list, sum).isNotEmpty()
}

fun findCombinationsOfThreeWithNoRepetitionThatAddUpToSum(
        list: List<Int>, sum: Int): List<Triple<Int, Int, Int>> {
    if (list.isEmpty()) return emptyList()

    val result = mutableListOf<Triple<Int, Int, Int>>()
    val sortedList = list.toSet().sorted()

    var leftIndex = 0
    var rightIndex = sortedList.lastIndex
    while (leftIndex <= rightIndex - 2) {
        val left = sortedList[leftIndex]
        val right = sortedList[rightIndex]
        val leftRightSum = left + right
        val rightMiddle = sortedList[rightIndex - 1]
        val leftMiddle = sortedList[leftIndex + 1]
        val rightMiddleSum = leftRightSum + rightMiddle
        val leftMiddleSum = leftRightSum + leftMiddle
        if (leftMiddleSum <= sum) {
            if (leftMiddleSum == sum) {
                result.add(Triple(left, leftMiddle, right))
                rightIndex--
            }
            leftIndex++
        } else if (rightMiddleSum >= sum) {
            if (rightMiddleSum == sum) {
                result.add(Triple(left, rightMiddle, right))
                leftIndex++
            }
            rightIndex--
        }
    }

    return result
}
