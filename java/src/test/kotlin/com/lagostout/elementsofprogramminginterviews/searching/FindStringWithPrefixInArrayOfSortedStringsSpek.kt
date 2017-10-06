package com.lagostout.elementsofprogramminginterviews.searching

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.Data2
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on
import kotlin.test.assertEquals

object FindStringWithPrefixInArrayOfSortedStringsSpek : Spek({
    describe("findStringWithPrefix()") {
        val data = listOf<Data2<List<String>, String, String?>?>(
//                data(listOf("a"), "", expected = null),
//                data(listOf("a"), "a", expected = "a"),
                data(listOf("a","b"), "a", expected = "a"),
//                data(listOf("a","b"), "b", expected = "b"),
//                data(listOf("ab","bc"), "b", expected = "bc"),
//                data(listOf("ab","bc"), "a", expected = "ab"),
//                data(listOf("ab","ac"), "a", expected = "ab"),
//                data(listOf("ab","ac","bc","bd","cd","cee"), "ce", expected = "cee"),
                null
        ).filterNotNull().toTypedArray()
        on("strings: %s prefix: %s", with = *data) {
            strings: List<String>, prefix: String, expected: String? ->
            it("returns $expected") {
                assertEquals(expected, findStringWithPrefix(prefix, strings))
            }
        }
    }
})
