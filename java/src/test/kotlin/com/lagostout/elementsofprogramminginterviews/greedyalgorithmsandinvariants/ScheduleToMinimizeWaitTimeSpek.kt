package com.lagostout.elementsofprogramminginterviews.greedyalgorithmsandinvariants

import org.apache.commons.math3.random.RandomDataGenerator
import org.jetbrains.spek.api.Spek
import kotlin.test.assertEquals

@Suppress("UNCHECKED_CAST")
class ScheduleToMinimizeWaitTimeSpek : Spek({
    given("brute force algorithm and a set of query service times") {
        val data:List<List<Any>> = listOf(listOf(listOf(2,5,1,3), 10))
        data.forEach {
            val serviceTimes = it[0] as List<Int>
            val expected = it[1] as Int
            on("computing the minimum total wait time of $it") {
                it("should be $expected") {
                    assertEquals(expected, minimumWaitTimeWithBruteForce(serviceTimes))
                }
            }
        }
    }
    given("algorithm and a set of randomly generated query service times") {
        randomQueryServiceTimes().forEach {
            on("computing the minimum total wait time of $it") {
                val minimumTotalWaitTime = minimumWaitTime(it)
                val expected = minimumWaitTimeWithBruteForce(it)
                it("should be $expected") {
                    assertEquals(expected, minimumTotalWaitTime)
                }
            }
        }
    }
})

fun randomQueryServiceTimes(
        taskCount:Int = 4, dataCount:Int = 10,
        serviceTimeUpperBound:Int = 9,
        serviceTimeLowerBound:Int = 0): List<List<Int>> {
    val random = RandomDataGenerator()
    random.reSeed(1L)
    val listOfServiceTimes = mutableListOf<List<Int>>()
    0.until(dataCount).forEach {
        val serviceTimes = mutableListOf<Int>()
        listOfServiceTimes.add(serviceTimes)
        0.until(taskCount).forEach {
            serviceTimes.add(random.nextInt(
                    serviceTimeLowerBound, serviceTimeUpperBound))
        }
    }
    return listOfServiceTimes
}
