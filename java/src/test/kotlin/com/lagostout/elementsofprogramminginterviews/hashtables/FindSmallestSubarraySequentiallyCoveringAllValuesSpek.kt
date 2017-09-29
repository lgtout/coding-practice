package com.lagostout.elementsofprogramminginterviews.hashtables

import com.lagostout.elementsofprogramminginterviews.hashtables.FindSmallestSubarraySequentiallyCoveringAllValues.smallestSubarraySequentiallyCoveringAllValues as cover
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.Data2
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on
import kotlin.test.assertEquals

object FindSmallestSubarraySequentiallyCoveringAllValuesSpek : Spek({
    describe("smallestSubarraySequentiallyCoveringAllValues") {
        val data = arrayOf<Data2<String, String, Pair<Int, Int>?>?>(
                data("", "", expected = null),
                data("apple", "", expected = null),
                data("apple", "banana", expected = null),
                data("apple", "apple", expected = Pair(0, 0)),
                data("banana apple", "apple", expected = Pair(1, 1)),
                data("banana banana", "banana", expected = Pair(0, 0)),
                data("apple banana mango", "banana mango", expected = Pair(0, 0)),
                null)
                .filterNotNull()
                .map {
                    it.run {
                        data(input1.split(" "), input2.split(" "), expected = expected)
                    }
                }.toTypedArray()
        on("paragraph: %s, keywords: %s", with = *data) {
            paragraph, keywords, expected: Pair<Int, Int>? ->
            it("returns %expected") {
                assertEquals(expected, cover(paragraph, keywords))
            }
        }
    }
})