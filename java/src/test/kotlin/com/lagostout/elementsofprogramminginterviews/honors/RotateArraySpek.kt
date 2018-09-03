package com.lagostout.elementsofprogramminginterviews.honors

import com.lagostout.kotlin.common.Alphabet
import com.lagostout.kotlin.common.Alphabet.*
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object RotateArraySpek : Spek({

    @Suppress("NAME_SHADOWING")
    fun rotate(distance: Int, array: List<Alphabet?>): List<Alphabet?> {
        val distance = distance % array.size
        val suffixSize = array.size - distance
        return array.drop(suffixSize) + array.take(suffixSize)
    }

    val data = listOfNotNull(
        Pair(0, mutableListOf<Alphabet?>(A, B, C, D)),
        Pair(1, mutableListOf<Alphabet?>(A, B, C, D)),
        Pair(2, mutableListOf<Alphabet?>(A, B, C, D)),
        Pair(3, mutableListOf<Alphabet?>(A, B, C, D)),
        Pair(4, mutableListOf<Alphabet?>(A, B, C, D)),
        Pair(6, mutableListOf<Alphabet?>(A, B, C, D)),
        null
    ).map { (distance, array) ->
        data(distance, array, rotate(distance, array))
    }.toTypedArray()

    describe("rotateArray") {
        on("distance %s, array %s", with = *data) { distance, array, expected ->
            it("should return $expected") {
                rotateArray(distance, array)
                assertThat(array).isEqualTo(expected)
            }
        }
    }

})