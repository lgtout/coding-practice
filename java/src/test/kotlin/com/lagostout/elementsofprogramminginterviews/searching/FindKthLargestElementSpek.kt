package com.lagostout.elementsofprogramminginterviews.searching

import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object FindKthLargestElementSpek : Spek({
    describe("findKthLargestElement") {
        val data = listOfNotNull(
                data(listOf(2), 1, 2),
                data(listOf(3, 1), 1, 3),
                data(listOf(3, 1), 2, 1),
                data(listOf(6, 1, 2, 5, 4, 3), 1, 6),
                data(listOf(6, 1, 2, 5, 4, 3), 2, 5),
                data(listOf(3, 1, 2, 5, 4, 6), 2, 5),
                data(listOf(6, 1, 2, 5, 4, 3), 3, 4),
                data(listOf(6, 1, 2, 5, 4, 3), 4, 3),
                data(listOf(3, 1, 2, 5, 4, 6), 5, 2),
                data(listOf(3, 1, 2, 5, 4, 6), 6, 1),
                data(listOf(3, 1, 2, 5, 4, 6), 6, 1),
                data(listOf(6, 1, 2, 5, 4, 3), 6, 1),
                null
        ).toTypedArray()
        on("list: %s, k: %s", with = *data) { list, k, expected ->
            it("expected: $expected") {
                assertThat(findKthLargestElement(list.toMutableList(), k))
                        .isEqualTo(expected)
            }
        }
    }
})
