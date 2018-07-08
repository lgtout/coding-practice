package com.lagostout.elementsofprogramminginterviews.greedyalgorithmsandinvariants

import com.lagostout.common.isOdd
import kotlin.math.absoluteValue

/**
 * Problem 18.4.3 page 346
 */

fun canPickKNumbersThatAddUpToSumWithRepetitionAllowed(
        list: List<Int>, k: Int, sum: Int,
        findClosestSum: (List<Int>, Int, Int) -> Int): Boolean  {
    return when {
        k < 0 -> false
        k == 0 -> sum == 0
        k == 1 -> list.contains(sum)
        list.isEmpty() -> false
        k.isOdd -> list.find {
            val t = sum - it
            findClosestSum(list.sorted(), k - 1, t) == t
        } != null
        else -> {
            findClosestSum(list.sorted(), k, sum).let {
                println("$it == $sum")
                it == sum
            }
        }
    }
}

fun findClosestSum1(list: List<Int>, k: Int, targetSum: Int): Int {
    println("findClosestSum($list, $k, $targetSum)")
    var leftIndex = 0
    var rightIndex = list.lastIndex
    var targetSumFound = false
    var sum = 0
    var left = list[leftIndex]
    var right = list[rightIndex]
    while  (leftIndex <= rightIndex && !targetSumFound) {
        left = list[leftIndex]
        right = list[rightIndex]
        sum = if (k >= 4) {
            findClosestSum1(list, k - 2, targetSum - (left + right))
        } else { 0 } + (left + right)
        when {
            sum < targetSum ->
                leftIndex += 1
            sum > targetSum ->
                rightIndex -=1
            else ->
                targetSumFound = true
        }
    }
    println("findClosestSum($list, $k, $targetSum) sum: $sum left: $left right: $right")
    return sum
}

fun findClosestSum2(list: List<Int>, k: Int, sum: Int): Int {
    println("[k: $k] findClosestSum(list: $list, k: $k, sum: $sum)")
    val sortedList = list.sorted()
    println("sortedList $sortedList")
    val boundsCount = k / 2
    val lastIndex = sortedList.lastIndex
    val bounds = (1..boundsCount).map {
        Pair(0, lastIndex)
    }.toMutableList()
    println("bounds $bounds")
    var exitLoop = false
    var sumAtBounds = 0
    while (!exitLoop) {
        sumAtBounds = bounds.fold(0) { acc, (leftIndex, rightIndex) ->
            println("$leftIndex $rightIndex")
            acc + sortedList[leftIndex] + sortedList[rightIndex]
        }
        println("sumAtBounds $sumAtBounds")
        when {
            sumAtBounds < sum -> {
                // TODO
                // Alternatively, we could use a sorted list of distances between
                // adjacent numbers in the sorted list.
                bounds.withIndex().mapNotNull {
                    if (it.value.first < it.value.second) {
                        val leftIndex = it.value.first + 1
                        Triple(it.index, (sortedList[it.value.first + 1] -
                                sortedList[it.value.first]).absoluteValue, leftIndex)
                    } else null
                }.sortedBy { it.second }.firstOrNull()?.let {
                        (index, _, leftIndex) ->
                    bounds[index] = bounds[index].copy(first = leftIndex)
                } ?: run {
                    exitLoop = true
                }
            }
            sumAtBounds > sum -> {
                bounds.withIndex().mapNotNull {
                    if (it.value.first < it.value.second) {
                        val rightIndex = it.value.second - 1
                        Triple(it.index, (sortedList[it.value.second] -
                                sortedList[it.value.second - 1]).absoluteValue, rightIndex)
                    } else null
                }.sortedBy { it.second }.firstOrNull()?.let {
                        (index, _, rightIndex) ->
                    bounds[index] = bounds[index].copy(second = rightIndex)
                } ?: run {
                    exitLoop = true
                }
            }
            else -> {
                exitLoop = true
            }
        }
    }
    return sumAtBounds
}

fun findClosestSum3(list: List<Int>, k: Int, targetSum: Int): Int {
    println("findClosestSum($list, $k, $targetSum)")
    var leftIndex = 0
    var rightIndex = list.lastIndex
    var targetSumFound = false
    var sum = 0
    var left = list[leftIndex]
    var right = list[rightIndex]
    var subSum = 0
    while  (leftIndex <= rightIndex && !targetSumFound) {
        left = list[leftIndex]
        right = list[rightIndex]
        subSum = if (k >= 4) {
            findClosestSum3(list, k - 2, targetSum - (left + right))
        } else { 0 }
        sum = subSum + (left + right)
        when {
            sum < targetSum ->
                leftIndex += 1
            sum > targetSum ->
                rightIndex -= 1
            else ->
                targetSumFound = true
        }
    }
    sum = when {
        sum < targetSum -> subSum + left * 2
        sum > targetSum -> subSum + right * 2
        else -> sum
    }
    println("findClosestSum($list, $k, $targetSum) sum: $sum left: $left right: $right")
    return sum
}

fun findClosestSum4(list: List<Int>, k: Int, targetSum: Int): Int {
    println("findClosestSum($list, $k, $targetSum)")
    var leftIndex = 0
    var rightIndex = list.lastIndex
//    var targetSumFound = false
    var sum = 0
    var left = list[leftIndex]
    var right = list[rightIndex]
    var exitLoop = false
    while (!exitLoop) {
        val subSum = if (k >= 4) {
            findClosestSum4(list, k - 2, targetSum - (left + right))
        } else { 0 }
        sum = subSum + left + right
        when {
            sum < targetSum -> {
                leftIndex += 1
                val nextLeft = list[leftIndex]
                exitLoop = (leftIndex > rightIndex) ||
                        (k == 2 && nextLeft + right > targetSum)
                if (!exitLoop) left = nextLeft
            }
            sum > targetSum -> {
                rightIndex -=1
                val nextRight = list[rightIndex]
                exitLoop = (leftIndex > rightIndex) ||
                        (k == 2 && left + nextRight < targetSum)
                if (!exitLoop) right = nextRight
            }
            else -> exitLoop = true
        }
    }
    println("findClosestSum($list, $k, $targetSum) sum: $sum left: $left right: $right")
    return sum
}
