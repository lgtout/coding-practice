package com.lagostout.bytebybyte.recursion

import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object PrintOddIndicesOfIntegerArraySpek : Spek({

    fun computeByIteration(numbers: Array<Int>): List<Int> {
        return numbers.withIndex().mapNotNull {
            if (it.index % 2 == 1) it.value
            else null
        }
    }

    val data = listOfNotNull(
        arrayOf(0,1,2),
        arrayOf(0,1,2,3,4,5),
        null
    ).map {
        data(it, computeByIteration(it))
    }.toTypedArray()

    describe("printOddIndicesOfIntegerArray") {
        on("numbers %s", with = *data) { numbers, expected ->
            it("should return $expected") {
                assertThat(printOddIndicesOfIntegerArray(numbers))
                        .isEqualTo(expected)
            }
        }
    }

})