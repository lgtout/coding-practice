package com.lagostout.elementsofprogramminginterviews.dynamicprogramming

import com.lagostout.elementsofprogramminginterviews.dynamicprogramming.EnumerateOccurrencesOfSequenceIn2DArrayWithSingleVisitPerPositionLimit.computeBottomUpWithMemoization
import com.lagostout.elementsofprogramminginterviews.dynamicprogramming.EnumerateOccurrencesOfSequenceIn2DArrayWithSingleVisitPerPositionLimit.computeWithRecursionAndBruteForce
import com.lagostout.elementsofprogramminginterviews.dynamicprogramming.EnumerateOccurrencesOfSequenceIn2DArrayWithSingleVisitPerPositionLimit.computeWithRecursionAndMemoization
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

@Suppress("RemoveExplicitTypeArguments")
object EnumerateOccurrencesOfSequenceIn2DArrayWithSingleVisitPerPositionLimitSpek : Spek({

    val data = listOfNotNull(
        data(emptyList<List<Int>>(), emptyList<Int>(),
            emptySet<List<Pair<Int, Int>>>()),
        data(emptyList<List<Int>>(), listOf(1),
            emptySet<List<Pair<Int, Int>>>()),
        data(listOf(listOf(0)), emptyList<Int>(),
            emptySet<List<Pair<Int, Int>>>()),
        data(listOf(listOf(0)), listOf(0), setOf(listOf(Pair(0,0)))),
        data(listOf(listOf(0)), listOf(0,1),
            emptySet<List<Pair<Int, Int>>>()),
        data(listOf(listOf(0,1)), listOf(0,1),
            setOf(listOf(Pair(0,0), Pair(0,1)))),
        data(listOf(listOf(0,1), listOf(0,1)), listOf(0,1),
            setOf(listOf(Pair(0,0), Pair(0,1)),
                listOf(Pair(1,0), Pair(1,1)))),
        data(listOf(listOf(0,1), listOf(0,1)), listOf(0,0),
            setOf(listOf(Pair(0,0), Pair(1,0)),
                listOf(Pair(1,0), Pair(0,0)))),
        data(listOf(listOf(0,1), listOf(0,1)), listOf(1,1),
            setOf(listOf(Pair(0,1), Pair(1,1)),
                listOf(Pair(1,1), Pair(0,1)))),
        data(listOf(listOf(0,1), listOf(0,1)), listOf(1,0),
            setOf(listOf(Pair(0,1), Pair(0,0)),
                listOf(Pair(1,1), Pair(1,0)))),
        data(listOf(listOf(0,1), listOf(0,1)), listOf(1,1,0),
            setOf(listOf(Pair(0,1), Pair(1,1), Pair(1,0)),
                listOf(Pair(1,1), Pair(0,1), Pair(0,0)))),
        data(listOf(listOf(0,1), listOf(0,1)), listOf(1,0,0),
            setOf(listOf(Pair(1,1), Pair(1,0), Pair(0,0)),
                listOf(Pair(0,1), Pair(0,0), Pair(1,0)))),
        data(listOf(listOf(0,1), listOf(0,1)), listOf(0,0,1),
            setOf(listOf(Pair(1,0), Pair(0,0), Pair(0,1)),
                listOf(Pair(0,0), Pair(1,0), Pair(1,1)))),
        data(listOf(listOf(0,1), listOf(0,1)), listOf(0,1,0),
            emptySet<List<Pair<Int, Int>>>()),
        data(listOf(listOf(0,1), listOf(0,1)), listOf(1,1,1),
            emptySet<List<Pair<Int, Int>>>()),
        data(listOf(listOf(0,1), listOf(1,0)), listOf(0,1,0,1,0),
            emptySet<List<Pair<Int, Int>>>()),
        data(listOf(listOf(0,1), listOf(1,0)), listOf(1,0,1,0,1),
            emptySet<List<Pair<Int, Int>>>()),
        data(listOf(listOf(0,1), listOf(1,0), listOf(0,1)), listOf(0,1),
            setOf(listOf(Pair(0,0), Pair(0,1)), listOf(Pair(1,1), Pair(1,0)),
                listOf(Pair(2,0), Pair(2,1)), listOf(Pair(0,0), Pair(1,0)),
                listOf(Pair(2,0), Pair(1,0)), listOf(Pair(1,1), Pair(0,1)),
                listOf(Pair(1,1), Pair(2,1)))),
        data(listOf(listOf(1,0), listOf(1,0), listOf(0,1)), listOf(1,0),
            setOf(listOf(Pair(0,0), Pair(0,1)), listOf(Pair(1,0), Pair(1,1)),
                listOf(Pair(2,1), Pair(2,0)), listOf(Pair(1,0), Pair(2,0)),
                listOf(Pair(2,1), Pair(1,1)))),
        data(listOf(listOf(1,0,1), listOf(1,0,1), listOf(0,1,0)), listOf(1,0,1),
            setOf(
                listOf(Pair(0,0), Pair(0,1), Pair(0,2)),
                listOf(Pair(0,2), Pair(0,1), Pair(0,0)),
                listOf(Pair(1,0), Pair(1,1), Pair(1,2)),
                listOf(Pair(1,2), Pair(1,1), Pair(1,0)),

                listOf(Pair(1,0), Pair(1,1), Pair(2,1)),
                listOf(Pair(2,1), Pair(1,1), Pair(1,0)),
                listOf(Pair(1,2), Pair(1,1), Pair(2,1)),
                listOf(Pair(2,1), Pair(1,1), Pair(1,2)),

                listOf(Pair(1,0), Pair(2,0), Pair(2,1)),
                listOf(Pair(2,1), Pair(2,0), Pair(1,0)),
                listOf(Pair(2,1), Pair(2,2), Pair(1,2)),
                listOf(Pair(1,2), Pair(2,2), Pair(2,1))
            )),
        null
    ).toTypedArray()

    describe("computeWithRecursionAndBruteForce") {
        on("grid: %s, pattern: %s", with = *data) { grid, pattern, expected ->
            it("returns $expected") {
                assertThat(computeWithRecursionAndBruteForce(grid, pattern))
                        .isEqualTo(expected)
            }
        }
    }

    describe("computeWithRecursionAndMemoization") {
        on("grid: %s, pattern: %s", with = *data) { grid, pattern, expected ->
            it("returns $expected") {
                assertThat(computeWithRecursionAndMemoization(grid, pattern))
                        .isEqualTo(expected)
            }
        }
    }

    describe("computeBottomUpWithMemoization") {
        on("grid: %s, pattern: %s", with = *data) { grid, pattern, expected ->
            it("returns $expected") {
                assertThat(computeBottomUpWithMemoization(grid, pattern))
                        .isEqualTo(expected)
            }
        }
    }

})