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
                data(mutableListOf(), emptyList(), expected = emptyList()),
                data(mutableListOf<Int?>(1), emptyList(), expected = listOf<Int?>(1)),
                data(mutableListOf<Int?>(null), listOf<Int?>(1), expected = listOf<Int?>(1)),
                data(mutableListOf<Int?>(null, null), listOf<Int?>(1), expected = listOf(1, null)),
                data(mutableListOf<Int?>(1, 2), emptyList(), expected = listOf<Int?>(1, 2)),
                data(mutableListOf<Int?>(null, null), listOf<Int?>(1, 2), expected = listOf<Int?>(1, 2)),
                data(mutableListOf(1, null, null), listOf<Int?>(2, 3),
                        expected = listOf<Int?>(1, 2, 3)),
                data(mutableListOf(1, 3, null, null, null), listOf<Int?>(2, 4),
                        expected = listOf(1, 2, 3, 4, null)),
                data(mutableListOf(5, 6, 8, null, null), listOf<Int?>(2, 7),
                        expected = listOf<Int?>(2, 5, 6, 7, 8)),
                // This following 2 inputs cover the case where the same entry appears in
                // both arrays.  According to the book forums, duplicates should be preserved.
                data(mutableListOf(1, 2, null, null), listOf<Int?>(1, 2), expected = listOf<Int?>(1, 1, 2, 2)),
                data(mutableListOf(1, 2, null, null, null, null), listOf<Int?>(1, 2, 3),
                        expected = listOf(1, 1, 2, 2, 3, null)),
                null
        ).filterNotNull().toTypedArray()
        on("merging %s and %s", with = *data) {
            array1: MutableList<Int?>, array2: List<Int?>, expected: List<Int?> ->
            it("modifies the first array to $expected") {
                mergeSortedArrays(array1, array2)
                assertEquals(expected.toList(), array1.toList())
            }
        }
    }
})