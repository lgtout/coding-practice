package com.lagostout.elementsofprogramminginterviews.heaps

import org.apache.commons.math3.random.RandomDataGenerator
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import com.lagostout.elementsofprogramminginterviews.heaps.ComputeKClosestStars.Point
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.xdescribe
import kotlin.test.assertTrue

class ComputeKClosestStarsSpek : Spek({

    xdescribe("computeKClosestStars") {

    }

    describe("Point") {
        describe("compare") {
            testCases.forEach {
                (firstPoint, secondPoint,
                        expectedVectorFromFirstPointToSecond,
                        comparison) ->
                given("points $firstPoint $secondPoint") {
                    it("compares them expecting firstPoint to be $comparison") {
                        val comparison = firstPoint.compareTo(secondPoint)
                        assertTrue {
                            (comparison == 0 &&
                                    expectedVectorFromFirstPointToSecond == 0) ||
                                    comparison < 0 ==
                                            expectedVectorFromFirstPointToSecond < 0
                        }
                    }
                }
            }
        }
    }

}) {

    companion object {

        data class PointCompareTestCase(
                val firstPoint: Point,
                val secondPoint: Point,
                val vectorFromFirstPointToSecond: Int,
                val comparison: String)

        val testCases: List<PointCompareTestCase> = {
            val random = RandomDataGenerator()
            random.reSeed(1)
            val result = mutableListOf<PointCompareTestCase>()
            val distanceRange = IntRange(0, 10)
            fun randomDistance(): Int {
                return random.nextInt(
                        distanceRange.start,
                        distanceRange.endInclusive)
            }
            fun randomPoint(): Point {
                return Point(randomDistance(),
                        randomDistance(),
                        randomDistance())
            }
            1.rangeTo(1_000).map {
                fun expected(firstPoint: Point, secondPoint: Point): Int {
                    val distancesFromOrigin = listOf(firstPoint, secondPoint).map({
                        (x, y, z) ->
                        listOf(x, y, z).map {
                            Math.pow(it.toDouble(), 2.0)
                        }.sum().let {
                            Math.sqrt(it)
                        }
                    })
                    return distancesFromOrigin.let {
                        Math.round(it[0] - it[1]).toInt()
                    }
                }
                val firstPoint = randomPoint()
                val secondPoint = randomPoint()
                result.add(PointCompareTestCase(firstPoint, secondPoint,
                        expected(firstPoint, secondPoint), ""))
            }
            result
        }()

    }

}

