package com.lagostout.elementsofprogramminginterviews.hashtables

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on
import kotlin.test.assertEquals

object LengthOfLongestContainedIntervalSpek : Spek({
    describe("findLengthOfLongestContainedInterval()") {
        val data = listOf(
                data(emptyList(), 0),
                data(listOf(1), 1),
                data(listOf(1,2), 2),
                data(listOf(2,1), 2),
                data(listOf(1,3), 1),
                data(listOf(3,2,1), 3),
                data(listOf(-1,0,3,2,1), 5),
                data(listOf(0,-1,3,2,4,-1,2), 3),
                data(listOf(3,-2,7,9,8,1,2,0,-1,5,8), 6),
                null
        ).filterNotNull().toTypedArray()
        on("array: %s", with = *data) { array, expected ->
            it("returns $expected") {
                assertEquals(expected,
                        findLengthOfLongestContainedInterval(array))
            }
        }
    }
})