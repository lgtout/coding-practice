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

//private fun findClosestSum(list: List<Int>, k: Int, sum: Int,
//                           start: Int = 0, end: Int = list.lastIndex): Int {
private fun findClosestSum(list: List<Int>, k: Int, sum: Int): Int {
    var left = 0
    var right = list.lastIndex
    var endLoop = false
    var currentSum: Int
    var leftNumber: Int
    var rightNumber: Int
    fun subSum(list: List<Int>, k: Int, sum: Int, leftRightSum: Int): Int {
        return if (k > 2)
            findClosestSum(list, k - 2, sum - leftRightSum)
        else 0
    }
    do {
        leftNumber = list[left]
        rightNumber = list[right]
        val leftRightSum = leftNumber + rightNumber
//        val subSum = if (k > 2)
//            findClosestSum(list, k - 2, sum - leftRightSum)
//        else 0
        currentSum = leftRightSum + subSum(list, k, sum, leftRightSum)

//        currentSum = (leftNumber + rightNumber).let {
//            it + (if (k > 2) {
//                findClosestSum(list, k - 2, sum - it)
////                findClosestSum(list, k - 2, sum - it, left, right)
//            } else 0)
//        }
//        println("k: $k, sum: $sum, left: $left, right: $right, leftNumber: $leftNumber, " +
//                "rightNumber: $rightNumber, currentSum: $currentSum")
        when {
            currentSum < sum -> {
//                if (left < right && subSum(list, k, sum, list[left + 1] + rightNumber) <= sum) ++left
                if (left < right) ++left
                else endLoop = true
            }
            currentSum > sum -> {
//                if (left < right && subSum(list, k, sum, list[right - 1] + rightNumber) >= sum) --right
                if (left < right) --right
                else endLoop = true
            }
            else -> endLoop = true
        }
    } while (!endLoop)
//    } while (!endLoop && left >= start && right <= end)
    println("k: $k, sum: $sum, currentSum: $currentSum, leftNumber: $leftNumber, rightNumber: $rightNumber")
    return currentSum
}