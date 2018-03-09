package com.lagostout.bytebybyte.dynamicprogramming

import com.lagostout.bytebybyte.dynamicprogramming.LongestIncreasingSubsequence.computeWithBruteForceAndRecursion
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object LongestIncreasingSubsequenceSpek : Spek({

    val data = listOfNotNull(
        data(listOf(1,4,2,3,5), listOf(1,2,3,5)),
        data(listOf(10,22,9,33,21,50,41,60),
            listOf(10,22,33,50,60)),
        null
    ).toTypedArray()

    describe("computeWithBruteForceAndRecursion") {
        on("integers: %s", with = *data) { integers, expected ->
            it("returns $expected") {
                assertThat(computeWithBruteForceAndRecursion(
                    integers)).isEqualTo(expected.size)
            }
        }
    }
})