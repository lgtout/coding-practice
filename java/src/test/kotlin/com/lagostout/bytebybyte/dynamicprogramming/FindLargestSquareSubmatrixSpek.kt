package com.lagostout.bytebybyte.dynamicprogramming

import com.lagostout.bytebybyte.dynamicprogramming.FindLargestSquareSubmatrix.Rectangle2
import com.lagostout.bytebybyte.dynamicprogramming.FindLargestSquareSubmatrix.Rectangle2.Companion.r2
import com.lagostout.bytebybyte.dynamicprogramming.FindLargestSquareSubmatrix.computeWithBruteForceAndRecursion
import com.lagostout.bytebybyte.dynamicprogramming.FindLargestSquareSubmatrix.computeWithMemoizationBottomUp
import com.lagostout.bytebybyte.dynamicprogramming.FindLargestSquareSubmatrix.computeWithRecursionAndMemoization
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data

object FindLargestSquareSubmatrixSpek : Spek({

    fun toList(array: Array<Array<Boolean>>): List<List<Boolean>> {
        return List(array.size) { array[it].toList() }
    }

    val data = listOfNotNull(
        Pair(arrayOf(arrayOf(false)), null as Rectangle2?),
        Pair(arrayOf(arrayOf(true)), r2(0,0,0,0)),
        Pair(arrayOf(arrayOf(true, true)), r2(0,0,0,0)),
        Pair(arrayOf(arrayOf(true, false)), r2(0,0,0,0)),
        Pair(arrayOf(arrayOf(false, true)), r2(1,1,0,0)),
        Pair(arrayOf(arrayOf(false, false)), null),
        Pair(arrayOf(
            arrayOf(false, false),
            arrayOf(false, false)),
            null as Rectangle2?),
        Pair(arrayOf(
            arrayOf(true, false),
            arrayOf(false, false)),
            r2(0,0,0,0)),
        Pair(arrayOf(
            arrayOf(false, true),
            arrayOf(false, false)),
            r2(1,1,0,0)),
        Pair(arrayOf(
            arrayOf(false, false),
            arrayOf(false, true)),
            r2(1,1,1,1)),
        Pair(arrayOf(
            arrayOf(false, false),
            arrayOf(true, false)),
            r2(0,0,1,1)),
        Pair(arrayOf(
            arrayOf(true, false),
            arrayOf(true, false)),
            r2(0,0,0,0)),
        Pair(arrayOf(
            arrayOf(true, true),
            arrayOf(false, false)),
            r2(0,0,0,0)),
        Pair(arrayOf(
            arrayOf(true, true),
            arrayOf(true, true)),
            r2(0,1,0,1)),
        Pair(arrayOf(
            arrayOf(true, false, false),
            arrayOf(false, false, false)),
            r2(0,0,0,0)),
        Pair(arrayOf(
            arrayOf(true, false, false),
            arrayOf(true, false, false)),
            r2(0,0,0,0)),
        Pair(arrayOf(
            arrayOf(true, true, false),
            arrayOf(true, true, false)),
            r2(0,1,0,1)),
        Pair(arrayOf(
            arrayOf(true, true, false),
            arrayOf(true, true, false),
            arrayOf(true, true, false)),
            r2(0,1,0,1)),
        Pair(arrayOf(
            arrayOf(true, true, true),
            arrayOf(true, true, true),
            arrayOf(true, true, true)),
            r2(0,2,0,2)),
        Pair(arrayOf(
            arrayOf(true, true, true),
            arrayOf(true, false, true),
            arrayOf(true, true, true)),
            r2(0,0,0,0)),
        Pair(arrayOf(
            arrayOf(false, true, true),
            arrayOf(true, true, true),
            arrayOf(true, true, true)),
            r2(1,2,0,1)),
        Pair(arrayOf(
            arrayOf(true, true, false, true),
            arrayOf(true, true, false, true),
            arrayOf(false, false, false, true),
            arrayOf(false, false, false, true),
            arrayOf(false, false, false, true)),
            r2(0,1,0,1)),
        Pair(arrayOf(
            arrayOf(true, true, true, true, false),
            arrayOf(true, true, true, true, false),
            arrayOf(false, true, true, true, false),
            arrayOf(false, true, true, true, false),
            arrayOf(false, true, true, true, false)),
            r2(1,3,0,2)),
        Pair(arrayOf(
            arrayOf(true, true, true, true, false),
            arrayOf(true, true, true, true, false),
            arrayOf(false, false, false, false, false),
            arrayOf(false, true, true, true, false),
            arrayOf(false, true, true, true, false),
            arrayOf(false, true, true, true, false)),
            r2(1,3,3,5)),
            null
    ).map {
        data(it.first, it.second)
    }.toTypedArray()

    describe("computeWithBruteForceAndRecursion") {
        data.forEach { (array, expected) ->
            describe("array: ${toList(array)}") {
                it("returns $expected") {
                    assertThat(computeWithBruteForceAndRecursion(array))
                            .isEqualTo(expected)
                }
            }
        }
    }

    describe("computeWithRecursionAndMemoization") {
        data.forEach { (array, expected) ->
            describe("array: ${toList(array)}") {
                it("should return $expected") {
                    assertThat(computeWithRecursionAndMemoization(array))
                            .isEqualTo(expected)
                }
            }
        }
    }

    describe("computeWithMemoizationBottomUp") {
        data.forEach { (array, expected) ->
            describe("array: ${toList(array)}") {
                it("should return $expected") {
                    assertThat(computeWithMemoizationBottomUp(array))
                            .isEqualTo(expected)
                }
            }
        }
    }
})