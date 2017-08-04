package com.lagostout.elementsofprogramminginterviews.greedyalgorithmsandinvariants

/**
 * Problem 18.4 page 345
 */
fun canPickThreeWithRepetitionAllowedThatAddUpToNumber(
        list: List<Int>, sum: Int): List<Triple<Int, Int, Int>> {
    val result = mutableListOf<Triple<Int, Int, Int>>()
    val sortedList = list.sorted()

    // TODO
    // What if the list is empty?
    // What if no combination is found?
    // What if there are fewer than 3 numbers in the list?

    // Find combinations containing of 2 or 3 of the same number.
    // There can only be one combination of 3 of the same number,
    // and finding combinations containing 2 will naturally find
    // it as a side-effect.
    var doublesIndex = 0
    var singlesIndex = sortedList.lastIndex
    while (doublesIndex <= sortedList.lastIndex) {
        val double = sortedList[doublesIndex]
        val single = sortedList[singlesIndex]
        while (singlesIndex >= 0) {
            val currentSum = double * 2 + single
            if (currentSum == sum) {
                result.add(Triple(double, double, single))
            }
            if (currentSum <= sum) {
                doublesIndex++
            } else if (currentSum >= sum) {
                singlesIndex--
            }
        }
    }

    // Find combinations containing 3 different numbers.
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