package com.lagostout.elementsofprogramminginterviews.greedyalgorithmsandinvariants

/**
 * Problem 18.4.2 page 346
 */

fun canPickThreeWithNoRepetitionThatAddUpToSum(
        list: List<Int>, sum: Int): Boolean =
        findCombinationsOfThreeThatAddUpToSumWithNoRepetition(list, sum).isNotEmpty()

fun findCombinationsOfThreeThatAddUpToSumWithNoRepetition(
        list: List<Int>, sum: Int): List<Triple<Int, Int, Int>> {
    if (list.isEmpty()) return emptyList()
    val sortedList = list.sorted()
    return sortedList.flatMap { firstNumber ->
        findCombinationsOfTwoThatAddUpToSumWithNoRepetition(
                sortedList, sum - firstNumber, firstNumber)
                .map {
                    Triple(firstNumber, it.first, it.second)
                }
    }
}

fun findCombinationsOfTwoThatAddUpToSumWithNoRepetition(
        list: List<Int>, sum: Int, firstNumber: Int): List<Pair<Int,Int>> {
    var left = 0
    var right = list.lastIndex
    val combinations = mutableListOf<Pair<Int, Int>>()
    println(list)
    while (left < right) {
        val leftNumber = list[left]
        val rightNumber = list[right]
        val currentSum = leftNumber + rightNumber
        when {
            currentSum < sum -> {
                while (true) {
                    if (left >= list.lastIndex ||
                            list[++left] != firstNumber) break
                }
            }
            currentSum > sum -> {
                while (true) {
                    if (right <= 0 || list[--right] != firstNumber) break
                }
            }
            else -> {
                combinations.add(Pair(leftNumber, rightNumber))
                ++left; --right
            }
        }
    }
    return combinations
}
