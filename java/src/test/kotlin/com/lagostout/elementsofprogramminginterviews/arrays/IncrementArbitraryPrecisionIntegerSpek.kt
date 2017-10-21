package com.lagostout.elementsofprogramminginterviews.arrays

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on
import kotlin.test.assertEquals

object IncrementArbitraryPrecisionIntegerSpek : Spek({
    describe("incrementInteger()") {
        val data = listOf(
                data(listOf(0), listOf(1)),
                data(listOf(1), listOf(2)),
                data(listOf(0,9), listOf(1,0)),
                data(listOf(0,9,9), listOf(1,0,0)),
                data(listOf(0,3,9), listOf(0,4,0)),
                null
        ).filterNotNull().toTypedArray()
        on("integer: %s", with = *data) { integer, expected ->
            it("returns $expected") {
                val integer = integer.toMutableList()
                incrementInteger(integer)
                assertEquals(expected, integer)
            }
        }
    }
})
