package com.lagostout.elementsofprogramminginterviews.primitivetypes

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on
import kotlin.test.assertEquals

object ParityOfAWordSpek : Spek({
    describe("computeParity()") {
        val data = listOf(
                data(arrayOf(), expected = listOf()),
                data(arrayOf<Long>(0), expected = listOf(0)),
                data(arrayOf<Long>(0,3,5,7,11), expected = listOf(0,0,0,1,1)),
                null
        ).filterNotNull().toTypedArray()
        on("compute parity of words %s", with = *data) { words, expected ->
            it("returns $expected") {
                assertEquals(expected, computeParity(words))
            }
        }
    }
})