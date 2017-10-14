package com.lagostout.elementsofprogramminginterviews.honors

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on
import kotlin.test.assertEquals

object FindFirstMissingPositiveEntrySpek : Spek({
    describe("findFirstMissingPositiveEntry()") {
        val data = listOf(
                data(mutableListOf(3,5,4,-1,5,1,-1), expected = 2),
                data(mutableListOf(1,2,3,4,5), expected = 6),
                data(mutableListOf(1,2,3,4,4), expected = 5),
                data(mutableListOf(4,4,1,2,3), expected = 5),
                data(mutableListOf(1,3,5,7,2,4), expected = 6),
                data(mutableListOf(1,1,2,4,2,2), expected = 3),
                null
        ).filterNotNull().toTypedArray()
        on("array: %s", with = *data) { array, expected ->
            it("returns $expected") {
                assertEquals(expected, findFirstMissingPositiveEntry(array))
            }
        }
    }
})
