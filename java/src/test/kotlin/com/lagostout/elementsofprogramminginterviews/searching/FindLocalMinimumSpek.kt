package com.lagostout.elementsofprogramminginterviews.searching

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on
import kotlin.test.assertTrue

object FindLocalMinimumSpek : Spek({
    describe("findIndexOfLocalMinimum") {
        val data = listOf(
                data(listOf(2,1,2), expected = setOf(1)),
                data(listOf(2,1,1,2), expected = setOf(1,2)),
                data(listOf(2,1,2,1,2), expected = setOf(1,3)),
                data(listOf(2,1,0,1,2), expected = setOf(2)),
                data(listOf(5,4,5,6,7,4,3,2,3), expected = setOf(1,2,7)),
                data(listOf(5,4,6,7,8,9,10,11), expected = setOf(1)),
                data(listOf(7,6,5,4,3,2,1,2), expected = setOf(6)),
                null
        ).filterNotNull().toTypedArray()
        on("array: %s", with = *data) { array, minima ->
            it("returns one of $minima") {
                val indexOfMinimum = findIndexOfLocalMinimum(array)
                println(indexOfMinimum)
                assertTrue(minima.contains(indexOfMinimum))
            }
        }
    }
})
