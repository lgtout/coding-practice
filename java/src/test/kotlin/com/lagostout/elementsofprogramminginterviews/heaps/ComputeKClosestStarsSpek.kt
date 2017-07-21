package com.lagostout.elementsofprogramminginterviews.heaps

import org.apache.commons.math3.random.RandomDataGenerator
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import com.lagostout.elementsofprogramminginterviews.heaps.ComputeKClosestStars.Point
import com.lagostout.elementsofprogramminginterviews.heaps.ComputeKClosestStars.computeKClosestStars
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.xdescribe
import kotlin.test.assertEquals
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.containsInAnyOrder

class ComputeKClosestStarsSpek : Spek({

    describe("computeKClosestStars") {
        computeKClosestStarsTestCases.forEach {
            (stars, k, expected) ->
            given("stars $stars k $k") {
                it("computes k closest stars as $expected") {
                    val kClosestStars = computeKClosestStars(stars, k)
                    // Force failure
//                    kClosestStars = kClosestStars.toMutableList().apply { removeAt(0) }
                    assertThat(kClosestStars, containsInAnyOrder<Point>(*expected.toTypedArray()))
                }
            }
        }
    }

    xdescribe("Point") {
        describe("compare") {
            pointComparisonTestCases.forEach {
                (firstPoint, secondPoint,
                        expectedComparison, comparisonString) ->
                given("points $firstPoint $secondPoint") {
                    it("finds that firstPoint is " +
                            "$comparisonString secondPoint") {
                        assertEquals(expectedComparison,
                                firstPoint.compareTo(secondPoint))
                    }
                }
            }
        }
    }

}) {

    companion object {

        class RandomHelper(val distanceRange: IntRange) {

            val random: RandomDataGenerator = RandomDataGenerator()

            init {
                random.reSeed(1)
            }

            private fun randomDistance(): Int {
                return random.nextInt(
                        distanceRange.start,
                        distanceRange.endInclusive)
            }

            fun randomPoint(): Point {
                return Point(randomDistance(),
                        randomDistance(),
                        randomDistance())
            }

            fun nextInt(lower: Int, upper: Int): Int {
                return random.nextInt(lower, upper)
            }

        }

        data class PointComparisonTestCase(
                val firstPoint: Point,
                val secondPoint: Point,
                val expectedComparison: Int,
                val comparisonString: String)

        val pointComparisonTestCases: List<PointComparisonTestCase> = {
            val randomHelper = RandomHelper(IntRange(0, 10))
            val result = mutableListOf<PointComparisonTestCase>()
            1.rangeTo(100).map {
                fun expected(firstPoint: Point, secondPoint: Point): Int {
                    val distancesFromOrigin = listOf(firstPoint, secondPoint).map({
                        (x, y, z) ->
                        listOf(x, y, z).map {
                            Math.pow(it.toDouble(), 2.0)
                        }.sum().let {
                            Math.sqrt(it)
                        }
                    })
                    return distancesFromOrigin.let{
                        it[0].compareTo(it[1])
                    }
                }
                fun comparisonString(comparison: Int): String {
                    return if (comparison == 0) "equal to"
                    else if (comparison < 0) "less than"
                    else "greater than"
                }
                val firstPoint = randomHelper.randomPoint()
                val secondPoint = randomHelper.randomPoint()
                val comparison = expected(firstPoint, secondPoint)
                result.add(PointComparisonTestCase(firstPoint, secondPoint,
                        comparison, comparisonString(comparison)))
            }
            result
        }()

        data class ComputeKClosestStarsTestCase(
                val stars: List<Point>, val k: Int,
                val expected: List<Point>)

        val computeKClosestStarsTestCases =
                mutableListOf<ComputeKClosestStarsTestCase>().apply {
                    val random = RandomHelper(IntRange(0, 10))
                    val testCaseCount = 10
                    1.rangeTo(testCaseCount).map {
                        val starCount = random.nextInt(0, 20)
                        var stars = emptyList<Point>()
                        if (starCount > 0) {
                            stars = 1.rangeTo(starCount).map {
                                random.randomPoint()
                            }
                        }
                        val k = random.nextInt(0, starCount)
                        val expectedStars = stars.sorted().take(k)
                        val testCase = ComputeKClosestStarsTestCase(stars, k, expectedStars)
                        this.add(testCase)
                    }
                }

    }

}

