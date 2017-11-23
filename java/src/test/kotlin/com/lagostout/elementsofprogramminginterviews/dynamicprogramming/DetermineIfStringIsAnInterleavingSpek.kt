package com.lagostout.elementsofprogramminginterviews.dynamicprogramming

import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object DetermineIfStringIsAnInterleavingSpek : Spek({
    describe("determineIfStringIsAnInterleavingOfTwoStrings()") {
        val data = listOfNotNull(
                data("", "", "", true),
                data("a", "a", "", true),
                data("a", "", "a", true),
                data("a", "", "b", false),
                data("ab", "ab", "", true),
                data("ab", "", "ab", true),
                data("ab", "", "abc", false),
                data("abc", "c", "ab", true),
                data("abc", "b", "ac", true),
                data("abc", "ac", "b", true),
                data("abc", "a", "bc", true),
                data("ab", "c", "ab", false),
                data("gattaca", "gtaa", "atc", true),
                data("gatacta", "gtaa", "atc", false),
                null).toTypedArray()
        on("string: %s first: %s, second: %s", with = *data) {
            string, first, second, expected ->
            it("returns $expected") {
                assertThat(determineIfStringIsAnInterleavingOfTwoStrings(
                        string, first, second
                )).isEqualTo(expected)
            }
        }
    }
})