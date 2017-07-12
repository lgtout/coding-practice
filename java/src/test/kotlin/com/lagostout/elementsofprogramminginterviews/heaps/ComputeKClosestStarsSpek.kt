package com.lagostout.elementsofprogramminginterviews.heaps

import org.apache.commons.math3.random.RandomDataGenerator
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import com.lagostout.elementsofprogramminginterviews.heaps.ComputeKClosestStars.Point

class ComputeKClosestStarsSpek : Spek({

    describe("computeKClosestStars") {

    }

    describe("Point") {
        describe("compare") {
            points.forEach {

            }
        }
    }

}) {

    companion object {

        data class PointCompareTestCase(
                val firstPoint: Point,
                val secondPoint: Point,
                val expected: Boolean = false)

        val points: List<Point> = {
            val random = RandomDataGenerator()
            random.reSeed(1)
            val result = mutableListOf<Point>()
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
                fun expected(firstPoint: Point, secondPoint: Point): Boolean {
//                    return Ma
                    return false
                }
                val firstPoint = randomPoint()
                val secondPoint = randomPoint()
                PointCompareTestCase(firstPoint, secondPoint,
                        expected(firstPoint, secondPoint))
            }
            result
        }()
    }

}

