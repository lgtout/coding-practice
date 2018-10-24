package com.lagostout.elementsofprogramminginterviews.arrays

import com.lagostout.common.nextInt
import com.lagostout.common.reproducibleRdg
import com.lagostout.common.shuffled
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

@Suppress("NAME_SHADOWING")
object PermuteElementsOfArraySpek : Spek({

    fun <T> computeExpected(array: List<T>, permutation: List<Int>): List<T> {
        return permutation.withIndex().sortedBy { it.value }.map {
            array[it.index]
        }
    }

    val randomData = run {
        val count = 100
        val random = reproducibleRdg()
        val arraySizeRange = (1..10)
        val valueRange = (1..10)
        val cases = mutableListOf<List<Int>>()
        repeat(count) {
            val case = mutableListOf<Int>()
            val arraySize = random.nextInt(arraySizeRange)
            repeat(arraySize) {
                case.add(random.nextInt(valueRange))
            }
            cases.add(case)
        }
        cases.map {
            val permutation = it.indices.shuffled(random)
            data(it, permutation, computeExpected(it, permutation))
        }.toTypedArray()
    }

    val data = listOfNotNull(
        Pair(listOf(1), listOf(0)),
        Pair(listOf(7,8), listOf(1,0)),
        null
    ).map { (array, permutation) ->
        data(listOf<Int?>(*array.toTypedArray()),
            permutation, computeExpected(array, permutation))
    }.toTypedArray()

    describe("permuteElementsOfArray") {
        on("array: %s, permutation: %s", with = *randomData) {
                array, permutation, expected ->
            it("should return $expected") {
                val array = array.toMutableList()
                val permutation = permutation.toMutableList()
                permuteElementsOfArray<Int>(array, permutation)
                assertThat(array).isEqualTo(expected)
            }
        }
    }

})