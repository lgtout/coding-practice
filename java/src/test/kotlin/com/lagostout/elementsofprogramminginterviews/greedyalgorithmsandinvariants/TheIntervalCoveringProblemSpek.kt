package com.lagostout.elementsofprogramminginterviews.greedyalgorithmsandinvariants

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import java.util.*
import kotlin.test.assertEquals

private data class TestCase(val intervals: List<Interval>, val expected: List<Int>) {
    val label = "computes the minimum sized set of numbers that " +
            "covers the intervals as $expected"
    init {
        Collections.shuffle(intervals)
    }
}

@RunWith(JUnitPlatform::class)
class TheIntervalCoveringProblemSpek : Spek({
    describe("minimumSizedSetOfNumbersThatCoverAllIntervals") {
        listOf(
                TestCase(listOf(Interval(0, 3), Interval(2, 6),
                        Interval(3, 4), Interval(6, 9)), listOf(3,6)),
                TestCase(listOf(Interval(1, 2), Interval(3, 4),
                        Interval(5, 6)), listOf(1,3,5)),
                TestCase(listOf(Interval(0, 2), Interval(2, 4),
                        Interval(4, 6)), listOf(2,4)),
                TestCase(listOf(Interval(0, 2), Interval(1, 3),
                        Interval(2, 4)), listOf(2)),
                TestCase(listOf(Interval(0, 2), Interval(6, 9),
                        Interval(7, 8)), listOf(0,7)),
                TestCase(listOf(Interval(2, 4), Interval(4, 6),
                        Interval(6, 8)), listOf(4,6)),
                TestCase(listOf(Interval(4, 5)), listOf(4))
        ).forEach {
            context("intervals ${it.intervals}") {
                it(it.label) {
                    assertEquals(it.expected,
                            minimumSizedSetOfNumbersThatCoverAllIntervals(it.intervals))
                }
            }
        }
    }
})
