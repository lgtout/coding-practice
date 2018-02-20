package com.lagostout.bytebybyte.dynamicprogramming

import com.lagostout.bytebybyte.dynamicprogramming.EditDistance.computeWithBruteForceAndRecursion
import com.lagostout.bytebybyte.dynamicprogramming.EditDistance.computeWithMemoizationBottomUp
import com.lagostout.bytebybyte.dynamicprogramming.EditDistance.computeWithRecursionAndMemoization
import com.lagostout.bytebybyte.dynamicprogramming.EditDistance.computeWithRecursionAndMemoization2
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.xdescribe
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object EditDistanceSpek : Spek({

    val data = listOfNotNull(
        data("A", "A", 0),
//        data("A", "", 1),
//        data("", "A", 1),
//        data("", "", 0),
//        data("AA", "", 2),
//        data("", "AA", 2),
//        data("AA", "AA", 0),
//        data("AA", "AB", 1),
//        data("AB", "AB", 0),
//        data("AB", "AA", 1),
//        data("BB", "AA", 2),
//        data("ABCD", "ACBD", 2),
//        data("AC", "ABCD", 2),
//        data("ACBD", "AC", 2),
        null
    ).toTypedArray()

    xdescribe("computeWithBruteForceAndRecursion") {
        on("string1: %s, string2: %s", with = *data) {
                string1, string2, expected ->
            it("should return $expected") {
                assertThat(computeWithBruteForceAndRecursion(
                    string1, string2)).isEqualTo(expected)
            }
        }
    }

    xdescribe("computeWithRecursionAndMemoization") {
        on("string1: %s, string2: %s", with = *data) {
                string1, string2, expected ->
            it("should return $expected") {
                assertThat(computeWithRecursionAndMemoization(
                    string1, string2)).isEqualTo(expected)
            }
        }
    }

    describe("computeWithRecursionAndMemoization2") {
        on("string1: %s, string2: %s", with = *data) {
                string1, string2, expected ->
            it("should return $expected") {
                assertThat(computeWithRecursionAndMemoization2(
                    string1, string2)).isEqualTo(expected)
            }
        }
    }

    xdescribe("computeWithMemoizationBottomUp") {
        on("string1: %s, string2: %s", with = *data) {
                string1, string2, expected ->
            it("should return $expected") {
                assertThat(computeWithMemoizationBottomUp(
                    string1, string2)).isEqualTo(expected)
            }
        }
    }

})