package com.lagostout.elementsofprogramminginterviews.greedyalgorithmsandinvariants

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import kotlin.test.assertEquals

class MinimizeCamerasToMonitorRobotsPatrollingACastleSpek : Spek({
    describe("minimumNumberOfCamerasToMonitorRobotsARoundCastle") {
        describe("computes the minimum number of cameras necessary to monitor " +
                "robots that patrol arcs around a castle, where each robot patrols at " +
                "a different distance from the castle") {
            testCases.take(testCases.size - 1).forEach {
                given("patrol arcs ${it.perimeters}") {
                    it("computes ${it.expected} as " +
                            "the minimum number of cameras needed") {
                        assertEquals(it.expected,
                                minimumNumberOfCamerasToMonitorRobotsPatrollingACastle(
                                        it.perimeters.map { it.arcs } ))
                    }
                }
            }
        }
    }
}) {
    companion object {
        data class Perimeter(val arcs: List<Arc>)
        private data class TestCase(val perimeters: List<Perimeter>, val expected:Int)
        private val testCases = listOf(
                // 1 perimeter
                TestCase(listOf(), 0),
                TestCase(listOf(Perimeter(emptyList())), 0),
                TestCase(listOf(Perimeter(listOf(Arc(0, 99)))), 1),
                TestCase(listOf(Perimeter(listOf(
                        Arc(0, 50), Arc(50, 99)))), 1),
                TestCase(listOf(Perimeter(listOf(
                        Arc(0, 49), Arc(50, 99)))), 2),

                // 2 perimeters
                TestCase(listOf(
                        Perimeter(listOf(Arc(0, 99))),
                        Perimeter(listOf(Arc(0, 99)))), 1),
                TestCase(listOf(
                        Perimeter(listOf(Arc(0, 50))),
                        Perimeter(listOf(Arc(50, 99)))), 1),
                TestCase(listOf(
                        Perimeter(listOf(Arc(0, 50))),
                        Perimeter(listOf(Arc(51, 99)))), 2),
                TestCase(listOf(
                        Perimeter(listOf(Arc(0, 50))),
                        Perimeter(listOf(Arc(51, 99)))), 2),
                TestCase(listOf(
                        Perimeter(listOf(Arc(0, 50), Arc(51, 99))),
                        Perimeter(listOf(Arc(51, 99)))), 2),
                TestCase(listOf(
                        Perimeter(listOf(Arc(0, 50), Arc(51, 99))),
                        Perimeter(listOf())), 2),
                TestCase(listOf(
                        Perimeter(listOf()),
                        Perimeter(listOf(Arc(0, 50), Arc(51, 99)))), 2),
                TestCase(listOf(
                        Perimeter(listOf(Arc(0, 50))),
                        Perimeter(listOf(Arc(0, 50), Arc(51, 99)))), 2),
                TestCase(listOf(
                        Perimeter(listOf(Arc(10, 20))),
                        Perimeter(listOf(Arc(0, 30), Arc(40, 70)))), 2),
                TestCase(listOf(
                        Perimeter(listOf(Arc(10, 20), Arc(50, 60))),
                        Perimeter(listOf(Arc(0, 30), Arc(40, 70)))), 2),
                TestCase(listOf(
                        Perimeter(listOf(Arc(20, 50), Arc(50, 90))),
                        Perimeter(listOf(Arc(0, 30), Arc(40, 70)))), 2),
                TestCase(listOf(
                        Perimeter(listOf(Arc(20, 80))),
                        Perimeter(listOf(Arc(20, 30), Arc(40, 50), Arc(60, 70)))), 3),

                // 3 perimeters
                TestCase(listOf(
                        Perimeter(listOf(Arc(20, 80))),
                        Perimeter(listOf(Arc(20, 30), Arc(40, 50), Arc(60, 70))),
                        Perimeter(listOf(Arc(20, 30), Arc(40, 50), Arc(60, 70)))), 3),
                TestCase(listOf(
                        Perimeter(listOf(Arc(20, 80))),
                        Perimeter(listOf(Arc(20, 30), Arc(40, 50), Arc(60, 70))),
                        Perimeter(listOf(Arc(25, 35), Arc(45, 55), Arc(65, 75)))), 3),
                TestCase(listOf(
                        Perimeter(listOf(Arc(20, 80))),
                        Perimeter(listOf(Arc(20, 30), Arc(40, 50), Arc(60, 70))),
                        Perimeter(listOf(Arc(25, 26), Arc(55, 56), Arc(57, 60)))), 4),
                TestCase(listOf(
                        Perimeter(listOf(Arc(20, 80), Arc(95, 99))),
                        Perimeter(listOf(Arc(20, 30), Arc(40, 50), Arc(60, 70))),
                        Perimeter(listOf(Arc(25, 26), Arc(55, 56), Arc(57, 60), Arc(85, 90)))), 6),

                // End placeholder
                TestCase(listOf(), 0)
        )
    }
}
