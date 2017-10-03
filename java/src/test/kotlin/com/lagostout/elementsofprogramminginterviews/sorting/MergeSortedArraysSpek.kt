package com.lagostout.elementsofprogramminginterviews.sorting

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on
import kotlin.test.assertEquals

object MergeSortedArraysSpek : Spek({
    describe("mergeSortedArrays()") {
        val data = listOf(
                data(emptyArray(), emptyArray(), expected = emptyArray()),
                data(arrayOf<Int?>(1), emptyArray(), expected = arrayOf<Int?>(1)),
                data(arrayOf<Int?>(null), arrayOf<Int?>(1), expected = arrayOf<Int?>(1)),
                data(arrayOf<Int?>(null, null), arrayOf<Int?>(1), expected = arrayOf(1, null)),
                data(arrayOf<Int?>(null, null), arrayOf<Int?>(1, 2), expected = arrayOf<Int?>(1, 2)),
                data(arrayOf(1, null, null), arrayOf<Int?>(2, 3), expected = arrayOf<Int?>(1, 2, 3)),
                data(arrayOf(1, 3, null, null, null), arrayOf<Int?>(2, 4), expected = arrayOf(1, 2, 3, 4, null)),
                data(arrayOf(5, 6, 8, null, null), arrayOf<Int?>(2, 7), expected = arrayOf<Int?>(2, 5, 6, 7, 8)),
                null
        ).filterNotNull().toTypedArray()
        on("merging %s and %a", with = *data) { array1, array2, expected ->
            it("modifies the first array to $expected") {
                mergeSortedArrays(array1, array2)
                assertEquals(expected, array1)
            }
        }
    }
})