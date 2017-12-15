package com.lagostout.elementsofprogramminginterviews.binarysearchtrees

import com.lagostout.elementsofprogramminginterviews.binarysearchtrees.EnumerateNumbers.Expression
import com.lagostout.elementsofprogramminginterviews.binarysearchtrees.EnumerateNumbers.enumerateNumbers
import com.lagostout.elementsofprogramminginterviews.binarysearchtrees.EnumerateNumbers.enumerateNumbersWithBST
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on
import org.paukov.combinatorics3.Generator

object EnumerateNumbersSpek : Spek({
    describe("Enumerate Numbers") {
        fun bruteForceEnumerateNumbers(count: Int): List<Expression> {
            return Generator.permutation((0..(count * 10)).toList())
                    .withRepetitions(2)
                    .map { (first, second) ->
                        Expression(first, second)
                    }.sortedBy { it.value }.take(count)
        }
        val data by memoized {
            listOfNotNull(
//                    1,
//                    2,
//                    3,
                    4,
//                    5,
//                    6,
//                    7,
//                    8,
//                    9,
//                    20,
                    null
            ).map {
                data(it, bruteForceEnumerateNumbers(it))
            }.toTypedArray()
        }
        describe("enumerateNumbers in O(n)") {
            on("count: %d", with = *data) { count, expected ->
                it("returns $expected") {
                    assertThat(enumerateNumbers(count))
                            .isEqualTo(expected)
                }
            }
        }
        describe("enumerateNumbers in O(n log(n))") {
            on("count: %d", with = *data) { count, expected ->
                it("returns $expected") {
                    assertThat(enumerateNumbersWithBST(count))
                            .isEqualTo(expected)
                }
            }
        }
    }
})
