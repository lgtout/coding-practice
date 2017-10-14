package com.lagostout.elementsofprogramminginterviews.searching

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.isIn
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.Data1
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object FindArrayEntryEqualToItsIndexSpek : Spek({
    describe("findArrayEntryEqualToItsIndex()") {
        val data = listOf<Data1<List<Int>, List<Int?>>?>(
                data(listOf(-2,0,2,3,6,7,9), expected = listOf(2,3)),
                data(listOf(-2,2,3,4), expected = listOf(null)),
                data(listOf(0,2,3,4), expected = listOf(0)),
                data(listOf(-2,-1,1,3), expected = listOf(3)),
                null
        ).filterNotNull().toTypedArray()
        on("array: %s", with = *data) { array, expected ->
            it("returns one of: $expected") {
                val entry = findArrayEntryEqualToItsIndex(array)
                assertThat(entry, isIn(expected))
            }
        }
    }
})

