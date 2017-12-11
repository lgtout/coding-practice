package com.lagostout.elementsofprogramminginterviews.greedyalgorithmsandinvariants

import com.lagostout.common.isOdd

/**
 * Problem 18.4.3 page 346
 */
@Suppress("NAME_SHADOWING")
fun canPickKNumbersThatAddUpToSumWithRepetitionAllowed(
        list: List<Int>, k: Int, sum: Int): Boolean  {
    val list = list.sorted()
//    println(list)
//    println(sum)
    return when {
        k < 0 -> false
        k == 0 -> sum == 0
        k == 1 -> list.contains(sum)
        list.isEmpty() -> false
        k.isOdd -> list.find {
            findClosestSum(list, k - 1, sum - it) + it == sum } != null
        else -> findClosestSum(list, k, sum).apply {
            println(this)
        } == sum
    }
}

private fun findClosestSum(
        list: List<Int>, k: Int, sum: Int): Int {
    var left = 0
    var right = list.lastIndex
    var endLoop = false
    var currentSum: Int
    var leftNumber: Int
    var rightNumber: Int
    do {
        leftNumber = list[left]
        rightNumber = list[right]
        currentSum = (leftNumber + rightNumber).let {
            it + (if (k > 2) {
                findClosestSum(list, k - 2, sum - it)
            } else 0)
        }
        println("k: $k, sum: $sum, nextSum: left: $left, right: $right, leftNumber: $leftNumber, " +
                "rightNumber: $rightNumber, currentSum: $currentSum")
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