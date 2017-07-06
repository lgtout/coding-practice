package com.lagostout.elementsofprogramminginterviews.dynamicprogramming

import com.lagostout.elementsofprogramminginterviews.greedyalgorithmsandinvariants.MaximizeNumberOfVisiblePointsInFieldOfView.FieldOfView
import com.lagostout.elementsofprogramminginterviews.greedyalgorithmsandinvariants.MaximizeNumberOfVisiblePointsInFieldOfView.Result
import com.lagostout.elementsofprogramminginterviews.greedyalgorithmsandinvariants.MaximizeNumberOfVisiblePointsInFieldOfView.maximumNumberOfVisiblePointsInFieldOfView
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@RunWith(JUnitPlatform::class)
class MaximizeNumberOfVisiblePointsInFieldOfView : Spek({
    describe("maximumNumberOfVisiblePointsInFieldOfView") {
        testCases.forEach {
            (points, fieldOfView, expected) ->
            given("points $points") {
                it("computes $expected") {
                    val (optimalFieldOfView, visiblePoints) =
                            maximumNumberOfVisiblePointsInFieldOfView(
                                    points, fieldOfView)
                    assertTrue(expected.fieldOfView.contains(optimalFieldOfView))
                    assertEquals(expected.visiblePointCount, visiblePoints)
                }
            }
        }
    }
}){
    companion object {
        data class TestCase(val points: List<Int> = listOf(),
                            val fieldOfView: FieldOfView,
                            val expected: Result) {
            init {
                Collections.shuffle(points)
            }
        }
        val testCases = listOf(
                TestCase(fieldOfView = FieldOfView(0, 49),
                        expected = Result(FieldOfView(0,99), 0)),
                TestCase(listOf(0), FieldOfView(0,19),
                        Result(FieldOfView(0,19), 1)),
                TestCase(listOf(0,0,0), FieldOfView(0,19),
                        Result(FieldOfView(0,19), 3)),
                TestCase(listOf(0,1), FieldOfView(0,19),
                        Result(FieldOfView(0,19), 2)),
                TestCase(listOf(0,1,10), FieldOfView(0,19),
                        Result(FieldOfView(0,19), 3)),
                TestCase(listOf(0,1,10,40), FieldOfView(0,40),
                        Result(FieldOfView(0,40), 4)),
                TestCase(listOf(0,1,10,40,41), FieldOfView(0,19),
                        Result(FieldOfView(0,19), 3)),
                TestCase(listOf(0,1,10,40,41,42), FieldOfView(0,19),
                        Result(FieldOfView(40,59), 3)),
                TestCase(listOf(0,1,10,40,41,42,43), FieldOfView(0,19),
                        Result(FieldOfView(40,59), 4)),
                TestCase(listOf(0,1,10,40,41,42,43,70,71), FieldOfView(0,19),
                        Result(FieldOfView(40,59), 4)),
                TestCase(listOf(0,1,10,40,41,42,43,70,70,80,80,70), FieldOfView(0,19),
                        Result(FieldOfView(70,89), 5)),
                null
        ).filterNotNull()
    }
}