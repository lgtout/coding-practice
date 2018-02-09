package com.lagostout.bytebybyte

import com.lagostout.bytebybyte.FindLargestSquareSubarray.Position.Companion.p
import com.lagostout.bytebybyte.FindLargestSquareSubarray.Rectangle.Companion.r
import com.lagostout.bytebybyte.FindLargestSquareSubarray.computeWithRecursion
import com.lagostout.bytebybyte.FindLargestSquareSubarray.computeWithRecursionAndMemoization
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data

object FindLargestSquareSubarraySpek : Spek({
    fun toList(array: Array<Array<Boolean>>): List<List<Boolean>> {
        return List(array.size) { array[it].toList() }
    }
    val data = listOfNotNull(
            Pair(arrayOf(arrayOf(false)), null),
            Pair(arrayOf(arrayOf(true)), r(p(0,0), p(0,0))),
            Pair(arrayOf(arrayOf(true, true)), r(p(0,0), p(0,0))),
            Pair(arrayOf(arrayOf(true, false)), r(p(0,0), p(0,0))),
            Pair(arrayOf(arrayOf(false, true)), r(p(0,1), p(0,1))),
            Pair(arrayOf(arrayOf(false, false)), null),
            null
    ).map {
        data(it.first, it.second)
    }.toTypedArray()
    describe("computeWithRecursion") {
        data.forEach { (array, expected) ->
            describe("array: ${toList(array)}") {
                it("returns $expected") {
                    assertThat(computeWithRecursion(array)).isEqualTo(expected)
                }
            }
        }
    }
//    describe("computeWithRecursionAndMemoization") {
//        data.forEach { (array, expected) ->
//            describe("array: ${toList(array)}") {
//                it("should return $expected") {
//                    assertThat(computeWithRecursionAndMemoization(array))
//                            .isEqualTo(expected)
//                }
//            }
//        }
//    }
})