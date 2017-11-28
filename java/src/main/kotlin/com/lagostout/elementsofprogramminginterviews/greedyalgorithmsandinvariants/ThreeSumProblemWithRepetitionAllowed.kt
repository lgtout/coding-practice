package com.lagostout.elementsofprogramminginterviews.greedyalgorithmsandinvariants

/**
 * Problem 18.4.1 page 345
 */

fun canPickThreeWithRepetitionAllowedThatAddUpToSum(
        list: List<Int>, sum: Int): Boolean =
        findCombinationsOfThreeWithRepetitionAllowedThatAddUpToSum(list, sum).isNotEmpty()

private fun findCombinationsOfThreeWithRepetitionAllowedThatAddUpToSum(
        list: List<Int>, sum: Int): List<Triple<Int, Int, Int>> {
    if (list.isEmpty()) return emptyList()
    return list.sorted().flatMap { firstNumber ->
        findCombinationsOfTwoThatAddUpToSumWithRepetitionAllowed(list, sum - firstNumber)
                .map {
                    Triple(firstNumber, it.first, it.second)
                }
    }
}

fun findCombinationsOfTwoThatAddUpToSumWithRepetitionAllowed(
        list: List<Int>, sum: Int): List<Pair<Int,Int>> {
    var left = 0
    var right = list.lastIndex
    val combinations = mutableListOf<Pair<Int, Int>>()
    while (left <= right) {
        val leftNumber = list[left]
        val rightNumber = list[right]
        val currentSum = leftNumber + rightNumber
        when {
            currentSum < sum -> ++left
            currentSum > sum -> --right
            else -> {
                combinations.add(Pair(leftNumber, rightNumber))
                ++left; --right
            }
        }
    }
    return combinations
}