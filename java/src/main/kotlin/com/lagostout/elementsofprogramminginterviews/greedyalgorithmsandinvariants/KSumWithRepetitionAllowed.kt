package com.lagostout.elementsofprogramminginterviews.greedyalgorithmsandinvariants

import com.lagostout.common.isOdd

/**
 * Problem 18.4.3 page 346
 */
@Suppress("NAME_SHADOWING")
fun canPickKNumbersThatAddUpToSumWithRepetitionAllowed(
        list: List<Int>, k: Int, sum: Int): Boolean  {
    val list = list.sorted()
    return when {
        k < 0 -> false
        k == 0 -> sum == 0
        k == 1 -> list.contains(sum)
        list.isEmpty() -> false
        k.isOdd -> list.find {
            findClosestSum(list, k - 1, sum - it) + it == sum } != null
        else -> findClosestSum(list, k, sum) == sum
    }
}

//private fun findClosestSum(list: List<Int>, k: Int, sum: Int,
//                           start: Int = 0, end: Int = list.lastIndex): Int {
private fun findClosestSum(list: List<Int>, k: Int, sum: Int): Int {
    println("[k: $k] findClosestSum(list: $list, k: $k, sum: $sum)")
    var left = 0
    var right = list.lastIndex
    var currentSum: Int
    var leftNumber: Int
    var rightNumber: Int
    var exitLoop = false
//    var closestSum: Int? = null
    do {
        leftNumber = list[left]
        rightNumber = list[right]
        println("[k: $k] left: $left, right: $right, " +
                "leftNumber: $leftNumber, rightNumber: $rightNumber")
        val leftRightSum = leftNumber + rightNumber
        val subSum = + if (k > 2)
            findClosestSum(list, k - 2, sum - leftRightSum)
        else 0
        println("[k: $k] subSum: $subSum")
        currentSum = leftRightSum + subSum
        println("[k: $k] leftRightSum: $leftNumber + $rightNumber = " +
                "$leftRightSum, currentSum: $leftRightSum + $subSum = " +
                "$currentSum")
        when {
            currentSum < sum -> {
                println("[k: $k] currentSum < sum: $currentSum < $sum")
                if (left < right) {
                    ++left
                    println("[k: $k] moving left bound right")
                } else null
            }
            currentSum > sum -> {
                println("[k: $k] currentSum > sum: $currentSum > $sum")
                if (right > left) {
                    --right
                    println("[k: $k] moving right bound left")
                } else null
            }
            else -> {
                println("[k: $k] currentSum == sum: $currentSum == $sum")
                println("[k: $k] found matching sum!")
                println("[k: $k] leaving bounds as they are")
                null
            }
        } ?: run { exitLoop = true }
//        closestSum = closestSum?.let {
//            if ((sum - it).absoluteValue >
//                    (sum - currentSum).absoluteValue) {
//                currentSum
//            } else closestSum
//        } ?: currentSum
//        println("k: $k, sum: $sum, currentSum: $currentSum, leftNumber: $leftNumber, rightNumber: $rightNumber")
    } while (!exitLoop)
    println("[k: $k] currentSum $currentSum")
    return currentSum
//    println("[k: $k] closestSum $closestSum")
//    return closestSum!!
}