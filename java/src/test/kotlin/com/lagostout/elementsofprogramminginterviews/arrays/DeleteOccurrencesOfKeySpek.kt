package com.lagostout.elementsofprogramminginterviews.arrays

import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object DeleteOccurrencesOfKey : Spek({
    describe("deleteOccurrencesOfKey") {
        val data = listOfNotNull(
                Pair(arrayOf(1,2,3), 2),
                Pair(arrayOf(1,2,3,2), 2),
                Pair(arrayOf(2,1,2,3,2), 2),
                Pair(arrayOf(2,1,1,2,3,2,1), 2),
                Pair(arrayOf(2,2,2,2), 2),
                null
        ).map { (array, key) ->
            array.filterNot { it == key }.let { filteredArray ->
                val expected = array.copyOf().apply {
                    for (index in 0..filteredArray.lastIndex) {
                        set(index, filteredArray[index])
                    }
                }.let {
                    Pair(it, filteredArray.size)
                }
                data(array, key, expected)
            }
        }.toTypedArray()
        on("array: %s, key: %s", with = *data) { array, key, expected ->
            it("returns $expected") {
                assertThat(deleteOccurrencesOfKey(key, array))
                        .isEqualTo(expected.second)
                assertThat(array).isEqualTo(expected.first)
            }
        }
    }

})
