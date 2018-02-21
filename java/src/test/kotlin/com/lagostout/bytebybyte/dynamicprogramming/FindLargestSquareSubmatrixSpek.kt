package com.lagostout.bytebybyte.dynamicprogramming

import com.lagostout.bytebybyte.dynamicprogramming.FindLargestSquareSubmatrix.Position.Companion.p
import com.lagostout.bytebybyte.dynamicprogramming.FindLargestSquareSubmatrix.Rectangle
import com.lagostout.bytebybyte.dynamicprogramming.FindLargestSquareSubmatrix.Rectangle.Companion.r
import com.lagostout.bytebybyte.dynamicprogramming.FindLargestSquareSubmatrix.computeWithBruteForceAndRecursion
import com.lagostout.bytebybyte.dynamicprogramming.FindLargestSquareSubmatrix.computeWithRecursionAndMemoization
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.xdescribe
import org.jetbrains.spek.data_driven.data

object FindLargestSquareSubmatrixSpek : Spek({

    fun toList(array: Array<Array<Boolean>>): List<List<Boolean>> {
        return List(array.size) { array[it].toList() }
    }

    val data = listOfNotNull(
        Pair(arrayOf(arrayOf(false)), null as Rectangle?),
        Pair(arrayOf(arrayOf(true)), r(p(0,0), p(0,0))),
        Pair(arrayOf(arrayOf(true, true)), r(p(0,0), p(0,0))),
        Pair(arrayOf(arrayOf(true, false)), r(p(0,0), p(0,0))),
        Pair(arrayOf(arrayOf(false, true)), r(p(0,1), p(0,1))),
        Pair(arrayOf(arrayOf(false, false)), null),
        Pair(arrayOf(
            arrayOf(false, false),
            arrayOf(false, false)),
            null as Rectangle?),
        Pair(arrayOf(
            arrayOf(true, false),
            arrayOf(false, false)),
            r(p(0,0), p(0,0))),
        Pair(arrayOf(
            arrayOf(false, true),
            arrayOf(false, false)),
            r(p(0,1), p(0,1))),
        Pair(arrayOf(
            arrayOf(false, false),
            arrayOf(false, true)),
            r(p(1,1), p(1,1))),
        Pair(arrayOf(
            arrayOf(false, false),
            arrayOf(true, false)),
            r(p(1,0), p(1,0))),
        Pair(arrayOf(
            arrayOf(true, false),
            arrayOf(true, false)),
            r(p(0,0), p(0,0))),
        Pair(arrayOf(
            arrayOf(true, true),
            arrayOf(false, false)),
            r(p(0,0), p(0,0))),
        Pair(arrayOf(
            arrayOf(true, true),
            arrayOf(true, true)),
            r(p(0,0), p(1,1))),
        Pair(arrayOf(
            arrayOf(true, false, false),
            arrayOf(false, false, false)),
            r(p(0,0), p(0,0))),
        Pair(arrayOf(
            arrayOf(true, false, false),
            arrayOf(true, false, false)),
            r(p(0,0), p(0,0))),
        Pair(arrayOf(
            arrayOf(true, true, false),
            arrayOf(true, true, false)),
            r(p(0,0), p(1,1))),
        Pair(arrayOf(
            arrayOf(true, true, false, true),
            arrayOf(true, true, false, true),
            arrayOf(false, false, false, true),
            arrayOf(false, false, false, true),
            arrayOf(false, false, false, true)),
            r(p(0,0), p(1,1))),
        Pair(arrayOf(
            arrayOf(true, true, true, true),
            arrayOf(true, true, true, true),
            arrayOf(false, true, true, true),
            arrayOf(false, true, true, false),
            arrayOf(false, true, true, false)),
            r(p(0,1), p(2,3))),
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

    xdescribe("computeWithRecursionAndMemoization") {
        data.forEach { (array, expected) ->
            describe("array: ${toList(array)}") {
                it("should return $expected") {
                    assertThat(computeWithRecursionAndMemoization(array)
                            .largestRectangle).isEqualTo(expected)
                }
            }
        }
    }
})