package com.lagostout.elementsofprogramminginterviews.arrays

import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object SumOfIntegersEncodedAsBinaryNumbersSpek : Spek({
    describe("sumOfIntegersEncodedAsBinaryNumbers()") {
        val data = listOf(
                data("", "", ""),
                data("0", "", "0"),
                data("1", "", "1"),
                data("11", "", "11"),
                data("0", "0", "0"),
                data("0", "1", "1"),
                data("1", "0", "1"),
                data("1", "1", "10"),
                data("1010", "0101", "1111"),
                data("0101", "0101", "1010"),
                data("10101", "0101", "11010"),
                null
        ).filterNotNull().toTypedArray()
        on("first integer: %s, second integer: %s", with = *data) {
            firstInt, secondInt, expected ->
            it("returns $expected") {
                assertThat(sumOfIntegersEncodedAsBinaryNumbers(
                        firstInt, secondInt)).isEqualTo(expected)
            }
        }
    }
})