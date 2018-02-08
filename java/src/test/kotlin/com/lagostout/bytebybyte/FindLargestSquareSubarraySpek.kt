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
import org.jetbrains.spek.data_driven.on

object FindLargestSquareSubarraySpek : Spek({
    val data = listOfNotNull(
//            Pair(arrayOf(arrayOf(false)), null),
            Pair(arrayOf(arrayOf(true)), r(p(0,0), p(0,0))),
            null
    ).map {
        data(it.first, it.second)
    }.toTypedArray()
    describe("computeWithRecursion") {
        on("array: %s", with = *data) { array, expected ->
            it("returns $expected") {
                assertThat(computeWithRecursion(array)).isEqualTo(expected)
            }
        }
    }
    describe("computeWithRecursionAndMemoization") {
        on("array: %s", with = *data) { array, expected ->
            it("returns $expected") {
                assertThat(computeWithRecursionAndMemoization(array))
                        .isEqualTo(expected)
            }
        }
    }
})