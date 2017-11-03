package com.lagostout.elementsofprogramminginterviews.arrays

import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object MultiplyTwoArbitraryPrecisionIntegersSpek : Spek({
    val data = listOf(
            Pair(emptyList(), emptyList()),
            Pair(listOf(1), emptyList()),
            Pair(emptyList(), listOf(1)),
            Pair(listOf(1), listOf(1)),
            Pair(listOf(1), listOf(2)),
            Pair(listOf(2), listOf(5)),
            Pair(listOf(-2), listOf(5)),
            null
    ).filterNotNull().map { (int1, int2) ->
        val expected = when {
            (int1.isEmpty()) -> int2
            (int2.isEmpty()) -> int1
            else -> (int1.joinToString("").toInt() *
                    int2.joinToString("").toInt())
                    .toString()
                    .split(Regex("\\(?=-\\d\\)|\\(?<!-\\)"))
                    .map { it.toInt() }
        }
        data(int1, int2, expected)
    }.toTypedArray()
    describe("multiplyTwoArbitraryPrecisionIntegers()") {
        on("first: %s, second %s", with = *data) { first, second, expected ->
            it("returns $expected") {
                assertThat(multiplyTwoArbitraryPrecisionIntegers(first, second))
                        .isEqualTo(expected)
            }
        }
    }
})