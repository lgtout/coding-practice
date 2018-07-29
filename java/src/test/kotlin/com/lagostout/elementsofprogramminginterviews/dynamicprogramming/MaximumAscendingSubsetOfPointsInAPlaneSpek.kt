package com.lagostout.elementsofprogramminginterviews.dynamicprogramming

import com.lagostout.common.nextInt
import com.lagostout.common.rdg
import com.lagostout.elementsofprogramminginterviews.dynamicprogramming.MaximumAscendingSubsetOfPointsInAPlane.ascendsTo
import com.lagostout.elementsofprogramminginterviews.dynamicprogramming.MaximumAscendingSubsetOfPointsInAPlane.computeBottomUpWithMemoization
import com.lagostout.elementsofprogramminginterviews.dynamicprogramming.MaximumAscendingSubsetOfPointsInAPlane.selectLongest
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on
import org.paukov.combinatorics3.Generator

object MaximumAscendingSubsetOfPointsInAPlaneSpek : Spek({

    fun computeWithBruteForce(points: List<Pair<Int, Int>>): List<List<Int>> {
        return selectLongest(Generator.subset(points.indices.toList()).simple().filter {
            it.fold(Pair(null as Int?, false)) { acc, curr ->
                val isAscending = (acc.first == null ||
                        (acc.second && points[acc.first!!].ascendsTo(points[curr])))
                Pair(curr, isAscending)
            }.second
        })
    }

    val randomData = run {
        val random = rdg()
        val caseCount = 10
        val axisRange = Pair(0,5)
        val pointCountRange = Pair(1,20)
        (0 until caseCount).map { _ ->
            val pointCount = random.nextInt(pointCountRange)
            (0 until pointCount).map { _ ->
                Pair(random.nextInt(axisRange), random.nextInt(axisRange))
            }
        }.map {
            data(it, computeWithBruteForce(it))
        }.toTypedArray()
    }

    val data = listOfNotNull(
        listOf(Pair(0,0)),
        listOf(Pair(0,0), Pair(0,0)),
        listOf(Pair(0,0), Pair(0,1)),
        listOf(Pair(0,0), Pair(1,0)),
        listOf(Pair(1,1), Pair(0,0)),
        listOf(Pair(0,0), Pair(1,1)),
        null
    ).map { data(it, computeWithBruteForce(it)) }.toTypedArray()

    describe("computeBottomUpWithMemoization") {
//        on("points %s", with = *data) { points, expected ->
        on("points %s", with = *randomData) { points, expected ->
            val ascendingSubsets = computeBottomUpWithMemoization(points)
            it("should return any of $expected") {
                assertThat(ascendingSubsets)
                        .containsExactlyInAnyOrder(*expected.toTypedArray())
            }
        }
    }

})