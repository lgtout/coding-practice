package com.lagostout.elementsofprogramminginterviews.dynamicprogramming

import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object ComputeMinimumCharsToDeleteToMakePalindromeSpek : Spek({
    val data = listOf(
            data("", 0),
            data("a", 0),
            data("ab", 1),
            data("aba", 0),
            data("aaa", 0),
            data("aaab", 1),
            data("aaba", 1),
            data("abaa", 1),
            data("abcd", 3),
            data("abcdefg", 6),
            data("aaaaaaa", 0),
//            data("fabcdae", 4),
            null
    ).filterNotNull().toTypedArray()
    describe("computeMinimumCharsToDeleteToMakePalindrome()") {
        on("string %s", with = *data) { string, expected ->
            it("returns $expected") {
                assertThat(computeMinimumCharsToDeleteToMakePalindrome(string))
                        .isEqualTo(expected)
            }
        }
    }
})