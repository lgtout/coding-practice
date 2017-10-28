package com.lagostout.elementsofprogramminginterviews.sorting

import com.lagostout.elementsofprogramminginterviews.sorting.RemoveFirstNameDuplicates.Name
import com.lagostout.elementsofprogramminginterviews.sorting.RemoveFirstNameDuplicates.removeFirstNameDuplicates
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object RemoveFirstNameDuplicatesSpek : Spek({
    describe("removeFirstNameDuplicates()") {
        val data = listOf(
                data(listOf(Name("a","b"), Name("c", "d")),
                        listOf("a","c")),
                data(listOf(Name("a","b"), Name("a", "d"), Name("c", "d")),
                        listOf("a","c")),
                data(listOf(Name("a","b"), Name("a", "d"),
                        Name("c", "d"), Name("a", "e")),
                        listOf("a","c")),
                data(listOf(Name("c", "d"), Name("a","b"),
                        Name("a", "d"), Name("b", "e")),
                        listOf("a","b","c")),
                null).filterNotNull().toTypedArray()
        on("names: %s", with = *data) { array: List<Name>, expected: List<String> ->
            it("removes duplicates from array so it contains ${expected.size} " +
                    "names with first names: $expected") {
                val list = array.toMutableList()
                removeFirstNameDuplicates(list)
                assertThat(list).extracting("first")
                        .containsOnly(*expected.toTypedArray())
                        .hasSize(expected.size)
            }
        }
    }
})