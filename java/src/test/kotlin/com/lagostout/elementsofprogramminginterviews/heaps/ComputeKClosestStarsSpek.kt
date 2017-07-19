package com.lagostout.elementsofprogramminginterviews.heaps

import org.apache.commons.math3.random.RandomDataGenerator
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import com.lagostout.elementsofprogramminginterviews.heaps.ComputeKClosestStars.Point
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.xdescribe
import kotlin.test.assertEquals

class ComputeKClosestStarsSpek : Spek({

    describe("computeKClosestStars") {
        
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
                        println("$firstPoint $secondPoint")
                        println (it)
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

        val computeKClosestStarsTestCases: List<ComputeKClosestStarsTestCase> = {
            val random = RandomDataGenerator()
            val cases = mutableListOf<ComputeKClosestStarsTestCase>()
            val randomHelper = RandomHelper(IntRange(0, 10))
            random.reSeed(1)
            1.rangeTo(100).map {
                val starCount = random.nextInt(0, 20)
                var stars = emptyList<Point>()
                if (starCount > 0) {
                    stars = 1.rangeTo(starCount).map {
                        randomHelper.randomPoint()
                    }
                }
                val k = random.nextInt(0, starCount)
                val expectedStars = stars.sorted().take(k)
                val testCase = ComputeKClosestStarsTestCase(stars, k, expectedStars)
                cases.add(testCase)
            }
            cases
        }()

    }

}

