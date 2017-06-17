package com.lagostout.elementsofprogramminginterviews.greedyalgorithmsandinvariants

import org.apache.commons.collections4.iterators.PermutationIterator

// Problem 18.2 p341
fun minimumWaitTime(serviceTimes:List<Int>):Int {
    val sortedServiceTimes = serviceTimes.sorted()
    val times = waitTime(sortedServiceTimes)
    return times.accumulatedWaitTime
}

data class Times(val previousServiceTime:Int, val previousWaitTime:Int, val accumulatedWaitTime:Int)

fun minimumWaitTimeWithBruteForce(serviceTimes:List<Int>):Int? {
    var sequenceWithMinWaitingTime:List<Int>? = null
    var bestWaitingTime = Int.MAX_VALUE
    PermutationIterator(serviceTimes).forEach {
        serviceTimesPermutation:List<Int> ->
        val waitTime = waitTime(serviceTimesPermutation)
        if (waitTime.accumulatedWaitTime < bestWaitingTime) {
            sequenceWithMinWaitingTime = serviceTimes
            bestWaitingTime = waitTime.accumulatedWaitTime
        }
    }
    return if (sequenceWithMinWaitingTime != null) bestWaitingTime else null
}

fun waitTime(serviceTimes: List<Int>): Times {
    return serviceTimes.fold(Times(0, 0, 0)) {
        (previousServiceTime, previousWaitTime, accumulatedWaitTime), serviceTime ->
        val currentWaitTime = previousWaitTime + previousServiceTime
        val times = Times(serviceTime, currentWaitTime, accumulatedWaitTime + currentWaitTime)
        times
    }
}

