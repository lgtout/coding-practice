package com.lagostout.elementsofprogramminginterviews.greedyalgorithmsandinvariants

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import kotlin.test.assertEquals

class MinimizeCamerasToMonitorRobotsPatrollingACastleSpek : Spek({
    data class Perimeter(val arcs: List<Arc>)
    data class TestCase(val perimeters: List<Perimeter>, val expected:Int)
    describe("minimumNumberOfCamerasToMonitorRobotsARoundCastle") {
        describe("computes the minimum number of cameras necessary to monitor " +
                "robots that patrol arcs around a castle, where each robot patrols at " +
                "a different distance from the castle") {
            listOf(
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

                    // TODO 3 perimeters
                    TestCase(listOf(), 0)
            ).forEach {
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
})