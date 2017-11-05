package com.lagostout.elementsofprogramminginterviews.searching

import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object FindMaxInAscendingDescendingSequencesSpek : Spek({
    describe("findMaxInAscDescSequences()") {
        val data = listOf(
                data(listOf(1,1), 1),
                data(listOf(1,1), 1),
                data(listOf(1,2,1), 2),
                data(listOf(3,4,2,1), 4),
                data(listOf(3,4,5,2,1), 5),
                data(listOf(3,4,5,4,3), 5),
                data(listOf(3,4,5,4,3,1), 5),
                null
        ).filterNotNull().toTypedArray()
        on("sequences: %s", with = *data) { sequences, expected ->
            it("returns $expected") {
                assertThat(findMaxInAscDescSequences(sequences))
                        .isEqualTo(expected)
            }
        }
    }
})