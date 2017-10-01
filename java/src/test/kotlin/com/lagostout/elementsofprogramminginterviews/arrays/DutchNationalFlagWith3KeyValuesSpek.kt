package com.lagostout.elementsofprogramminginterviews.arrays

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on
import kotlin.test.assertEquals

object DutchNationalFlagWith3KeyValuesSpek : Spek({
    describe("arrangeAsDutchNationalFlag()") {
        val data = arrayOf(
                data(mutableListOf(), expected = emptyList()),
                data(mutableListOf(1), expected = listOf(1)),
                data(mutableListOf(1,2), expected = listOf(1,2)),
                data(mutableListOf(2,1), expected = listOf(2,1)),
                data(mutableListOf(1,1), expected = listOf(1,1)),
                data(mutableListOf(2,1,2), expected = listOf(2,2,1)),
                data(mutableListOf(2,1,2,3,1,3), expected = listOf(2,2,1,1,3,3)),
                data(mutableListOf(2,1,2,3,1,3,2,1), expected = listOf(2,2,2,3,3,1,1,1))
        )
        on("array: %s", with = *data) { array, expected ->
            it("returns $expected") {
                arrangeAsDutchNationalFlag(array)
                assertEquals(expected, array)
            }
        }
    }
})