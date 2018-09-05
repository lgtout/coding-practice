package com.lagostout.elementsofprogramminginterviews.arrays

import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object PermuteElementsOfArraySpek : Spek({

    fun <T> compute(array: List<T>, permutation: List<Int>): List<T> {
        return permutation.withIndex().sortedBy { it.value }.map {
            array[it.index]
        }
    }

    val data = listOfNotNull(
        Pair(listOf(1), mutableListOf(0)),
        null
    ).map { (array, permutation) ->
        data(mutableListOf<Int?>(*array.toTypedArray()),
            permutation, compute(array, permutation))
    }.toTypedArray()

    describe("permuteElementsOfArray") {
        on("array: %s, permutation: %s", with = *data) { array, permutation, expected ->
            it("should return $expected") {
                permuteElementsOfArray(array, permutation)
                assertThat(array).isEqualTo(expected)
            }
        }
    }

})