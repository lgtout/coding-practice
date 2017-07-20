package com.lagostout.elementsofprogramminginterviews.dynamicprogramming

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import kotlin.test.assertEquals

class WaysToClimbStairsSpek : Spek({
    describe("countOfWaysToClimbStairs") {
        testCases.forEach {
            (stairCount, maxStepsAtATime, expectedCount) ->
            given("$stairCount stairs and $maxStepsAtATime " +
                    "maximum steps at a time") {
                it("computes $expectedCount as the number of " +
                        "ways to climb the stairs") {
                    assertEquals(expectedCount, countOfWaysToClimbStairs(
                            stairCount, maxStepsAtATime))
                }
            }
        }
    }
}) {
    companion object {
        data class TestCase(
                val stairCount: Int,
                val maxStepsAtATime: Int,
                val expectedCount: Int)
        val testCases = listOf(
                // stairCount = 0
                TestCase(0, 1, 0),
                TestCase(0, 2, 0),
                // k = 1
                TestCase(1, 1, 1),
                TestCase(2, 1, 1),
                TestCase(3, 1, 1),
                TestCase(10, 1, 1),
                // k = 2
                TestCase(1, 2, 1),
                TestCase(2, 2, 2),
                TestCase(3, 2, 3),
                TestCase(4, 2, 5),
                TestCase(5, 2, 8),
                // k = 3
                TestCase(1, 3, 1),
                TestCase(2, 3, 2),
                TestCase(3, 3, 4),
                TestCase(4, 3, 7),
                TestCase(5, 3, 13),
                null
        ).filterNotNull()
    }
}