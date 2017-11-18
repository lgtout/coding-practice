package com.lagostout.elementsofprogramminginterviews.binarysearchtrees

import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object FindClosestEntriesSpek : Spek({
    val data = listOfNotNull(
            data(Triple(listOf(1), listOf(1), listOf(1)), Triple(1,1,1)),
            data(Triple(listOf(2), listOf(1), listOf(1)), Triple(1,1,2)),
            data(Triple(listOf(1), listOf(2), listOf(1)), Triple(1,1,2)),
            data(Triple(listOf(1,2), listOf(1,2), listOf(1,2)), Triple(1,1,1)),
            data(Triple(listOf(1,2), listOf(2,3), listOf(3,4)), Triple(2,2,3)),
            data(Triple(listOf(5,10,15), listOf(3,6,9,12,15), listOf(8,16,24)), Triple(15,15,16)),
            null
    ).toTypedArray()
    describe("findClosestEntries()") {
        on("lists: %s", with = *data) { (first, second, third), expected ->
            it("returns $expected") {
                assertThat(findClosestEntries(first, second, third))
                        .isEqualTo(expected)
            }
        }
    }
})
