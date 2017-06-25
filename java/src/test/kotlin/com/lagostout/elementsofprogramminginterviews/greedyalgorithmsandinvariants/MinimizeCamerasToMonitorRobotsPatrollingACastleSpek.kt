package com.lagostout.elementsofprogramminginterviews.greedyalgorithmsandinvariants

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import kotlin.test.assertEquals

class MinimizeCamerasToMonitorRobotsPatrollingACastleSpek : Spek({
    data class TestCase(val robotPatrolArcs: List<List<Int>>, val expected:Int)
    describe("minimumNumberOfCamerasToMonitorRobotsARoundCastle") {
        describe("computes the minimum number of cameras necessary to monitor " +
                "robots that patrol arcs around a castle, where each robot patrols at " +
                "a different distance from the castle") {
            listOf(TestCase(listOf(), 0)).forEach {
                given("patrol arcs ${it.robotPatrolArcs}") {
                    it("computes ${it.expected} as " +
                            "the minimum number of cameras needed") {
                        assertEquals(it.expected,
                                minimumNumberOfCamerasToMonitorRobotsPatrollingACastle(
                                        it.robotPatrolArcs))
                    }
                }
            }
        }
    }
})