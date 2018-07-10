package com.lagostout.elementsofprogramminginterviews.arrays

import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

class LengthOfLongestSubarrayOfEqualEntriesSpek : Spek({

    val data = run {
        listOfNotNull(
            data(emptyList(), 0),
            data(listOf(1), 1),
            data(listOf(1,1), 2),
            data(listOf(1,2), 1),
            data(listOf(1,2,2), 2),
            data(listOf(1,2,2,1,1,1,2,2), 3),
            data(listOf(1,2,2,1,1,1,2,3,3,3,3,2), 4),
            data(listOf(1,2,2,1,1,1,2,3,3,3,3,2,1,1,1), 4),
            null
        ).toTypedArray()
    }

    describe("lengthOfLongestSubarrayOfEqualEntries") {
        on("array %s", with = *data) { array, expected ->
            it("should return $expected") {
                assertThat(lengthOfLongestSubarrayOfEqualEntries(array))
                        .isEqualTo(expected)
            }
        }
    }

})