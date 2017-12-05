package com.lagostout.elementsofprogramminginterviews.greedyalgorithmsandinvariants

/**
 * Problem 18.4.3 page 346
 */
fun canPickKNumbersThatAddUpToSumWithRepetitionAllowed(
        list: List<Int>, k: Int, sum: Int): Boolean  {
    return _canPickKNumbersThatAddUpToSumWithRepetitionAllowed(
            list.sorted(), k, sum) == sum
}

fun _canPickKNumbersThatAddUpToSumWithRepetitionAllowed(
        list: List<Int>, k: Int, sum: Int): Int {
    if (k == 2) {
        return _findCombinationsOfTwoThatAddUpToSumWithRepetitionAllowed(list, sum)
    } else {
        var left = 0
        var right = list.lastIndex
        var endLoop = false
        var currentSum: Int
        do {
            val leftNumber = list[left]
            val rightNumber = list[right]
            currentSum = (leftNumber + rightNumber).let {
                 it + _canPickKNumbersThatAddUpToSumWithRepetitionAllowed(list, k - 2, sum - it)
            }
            when {
                currentSum < sum -> {
                    if (left < right) ++left
                    else endLoop = true
                }
                currentSum > sum -> {
                    if (left < right) --right
                    else endLoop = true
                }
                else -> {
                    endLoop = true
                }
            }
        } while (!endLoop)
        return currentSum
    }
}

fun _findCombinationsOfTwoThatAddUpToSumWithRepetitionAllowed(
        list: List<Int>, sum: Int): Int {
    var left = 0
    var right = list.lastIndex
    var endLoop = false
    while (!endLoop) {
        val leftNumber = list[left]
        val rightNumber = list[right]
        val currentSum = leftNumber + rightNumber
        when {
            currentSum < sum -> {
                if (left < right) ++left
                else endLoop = true
            }
            currentSum > sum -> {
                if (left < right) --right
                else endLoop = true
            }
            else -> {
                endLoop = true
            }
        }
    }
    return list[left] + list[right]
}
