package com.lagostout.elementsofprogramminginterviews.arrays

import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object LimitDuplicatesInSortedArraySpek : Spek({

    fun p(array: Array<Int?>, m: Int) = Pair(array, m)

    describe("limitDuplicatesInSortedArray") {
        val data = listOfNotNull(
                p(arrayOf(1), 1),
                p(arrayOf(1,1), 1),
                p(arrayOf(1,1), 0),
                p(arrayOf(1,2,2), 2),
                p(arrayOf(1,2,2,3,3), 2),
                p(arrayOf(1,2,2,2), 2),
                p(arrayOf(1,2,2,2), 3),
                p(arrayOf(2,2,2,3), 3),
                p(arrayOf(1,2,2,2,3), 3),
                p(arrayOf(1,2,2,2,3,4,4,4,5), 3),
                p(arrayOf(1,2,2,2,3,4,4,4,5), 1),
                null
        ).map { (array, m) ->
            array.groupBy { it }.values.map { repeats ->
                if (repeats.size == m) {
                    repeats.take(minOf(m, 2))
                } else repeats
            }.flatten().let { filteredList ->
                data(array, m, Array(array.size) {
                    if (it <= filteredList.lastIndex)
                        filteredList[it] else null
                })
            }
        }.toTypedArray()
        on("array: %s, m: %s", with = *data) { array, m, expected ->
            it("returns ${expected.toList()}") {
                limitDuplicatesInSortedArray(array, m)
                assertThat(array).isEqualTo(expected)
            }
        }
    }

})