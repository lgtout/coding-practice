package com.lagostout.elementsofprogramminginterviews.arrays

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on
import kotlin.test.assertEquals

object DutchNationalFlagWithBooleanValuesSpek : Spek({
    describe("arrangeAsDutchNationalFlag()") {
        val data = listOf(
                data(mutableListOf(false), expected = listOf(false)),
                data(mutableListOf(true), expected = listOf(true)),
                data(mutableListOf(false, true), expected = listOf(false, true)),
                data(mutableListOf(true, false), expected = listOf(false, true)),
                data(mutableListOf(true, true, true), expected = listOf(true, true, true)),
                data(mutableListOf(false, true, false), expected = listOf(false, false, true)),
                data(mutableListOf(true, false, false, true, false, true),
                        expected = listOf(false, false, false, true, true, true)),
                null
        ).filterNotNull().toTypedArray()
        on("array: %s", with = *data) { array, expected ->
            it("arranges array as $expected") {
                arrangeAsDutchNationalFlag(array)
                assertEquals(expected, array)
            }
        }
    }
})
