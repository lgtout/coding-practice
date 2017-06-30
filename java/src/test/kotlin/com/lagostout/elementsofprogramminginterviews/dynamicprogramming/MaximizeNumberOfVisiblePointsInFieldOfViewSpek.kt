package com.lagostout.elementsofprogramminginterviews.dynamicprogramming

import com.lagostout.elementsofprogramminginterviews.greedyalgorithmsandinvariants.MaximizeNumberOfVisiblePointsInFieldOfView.Companion.FieldOfView
import com.lagostout.elementsofprogramminginterviews.greedyalgorithmsandinvariants.MaximizeNumberOfVisiblePointsInFieldOfView.Companion.maximumNumberOfVisiblePointsInFieldOfView
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import java.util.*
import kotlin.test.assertTrue

@RunWith(JUnitPlatform::class)
class MaximizeNumberOfVisiblePointsInFieldOfView : Spek({
    describe("maximumNumberOfVisiblePointsInFieldOfView") {
        testCases.forEach {
            (points, fieldOfView, expected) ->
            given("points $points") {
                it("computes $expected") {
                    val (optimalFieldOfView, _) =
                            maximumNumberOfVisiblePointsInFieldOfView(
                                    points, fieldOfView)
                    assertTrue(fieldOfView.contains(optimalFieldOfView))
                }
            }
        }
    }
}){
    companion object {
        data class TestCase(val points: List<Int> = listOf(),
                            val fieldOfView: FieldOfView,
                            val expected: FieldOfView) {
            init {
                Collections.shuffle(points)
            }
        }
        val testCases = listOf(
                TestCase(fieldOfView = FieldOfView(0, 49), expected = FieldOfView(0,99)),
                TestCase(listOf(0), FieldOfView(0,19), FieldOfView(81,19)),
                TestCase(listOf(0,0,0), FieldOfView(0,19), FieldOfView(81,19)),
                TestCase(listOf(0,1), FieldOfView(0,19), FieldOfView(82,19)),
                TestCase(listOf(0,1), FieldOfView(0,19), FieldOfView(82,19)),
                TestCase(listOf(0,1,10), FieldOfView(0,19), FieldOfView(91,19)),
                TestCase(listOf(0,1,10,40), FieldOfView(0,19), FieldOfView(91,19)),
                TestCase(listOf(0,1,10,40,41), FieldOfView(0,19), FieldOfView(91,19)),
                TestCase(listOf(0,1,10,40,41,42), FieldOfView(0,19), FieldOfView(91,19)),
                TestCase(listOf(0,1,10,40,41,42,43), FieldOfView(0,19), FieldOfView(24,59)),
                TestCase(listOf(0,1,10,40,41,42,43,70,71), FieldOfView(0,19), FieldOfView(24,59)),
                TestCase(listOf(0,1,10,40,41,42,43,70,70,80,80,70), FieldOfView(0,19), FieldOfView(61,89)),
                null
        ).filterNotNull()
    }
}