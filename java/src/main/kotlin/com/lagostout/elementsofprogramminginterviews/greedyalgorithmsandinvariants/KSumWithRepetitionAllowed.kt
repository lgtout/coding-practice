package com.lagostout.elementsofprogramminginterviews.greedyalgorithmsandinvariants

import com.lagostout.common.isOdd
import kotlin.math.absoluteValue

/**
 * Problem 18.4.3 page 346
 */
@Suppress("NAME_SHADOWING")
fun canPickKNumbersThatAddUpToSumWithRepetitionAllowed(
        list: List<Int>, k: Int, sum: Int): Boolean  {
    val list = list.sorted()
    println("list: $list")
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

private fun subSum(list: List<Int>, k: Int, sum: Int, leftRightSum: Int): Int {
    return if (k > 2)
        findClosestSum(list, k - 2, sum - leftRightSum)
    else 0
}

//private fun findClosestSum(list: List<Int>, k: Int, sum: Int,
//                           start: Int = 0, end: Int = list.lastIndex): Int {
private fun findClosestSum(list: List<Int>, k: Int, sum: Int): Int {
    println("[k: $k] findClosestSum(k: $k, sum: $sum)")
    var left = 0
    var right = list.lastIndex
    var currentSum: Int
    var leftNumber: Int
    var rightNumber: Int
    var exitLoop = false
    var closestSum: Int? = null
    do {
        leftNumber = list[left]
        rightNumber = list[right]
        println("[k: $k] left: $left, right: $right, leftNumber: $leftNumber, rightNumber: $rightNumber")
        val leftRightSum = leftNumber + rightNumber
        currentSum = leftRightSum + subSum(list, k, sum, leftRightSum)
        println("[k: $k] leftRightSum $leftRightSum, currentSum $currentSum")
        when {
            currentSum < sum -> {
                println("[k: $k] currentSum < sum: $currentSum < $sum")
                if (left < right) {
                    ++left
                } else null
            }
            currentSum > sum -> {
                println("[k: $k] currentSum > sum: $currentSum > $sum")
                if (right > left) {
                    --right
                } else null
            }
            else -> {
                println("[k: $k] currentSum == sum: $currentSum == $sum")
                null
            }
        } ?: run { exitLoop = true }
        closestSum = closestSum?.let {
            if ((sum - it).absoluteValue >
                    (sum - currentSum).absoluteValue) {
                currentSum
            } else closestSum
        } ?: currentSum
//        println("k: $k, sum: $sum, currentSum: $currentSum, leftNumber: $leftNumber, rightNumber: $rightNumber")
    } while (!exitLoop)
//    return currentSum
    println("[k: $k] closestSum $closestSum")
    return closestSum!!
}