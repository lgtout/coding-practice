package com.lagostout.bytebybyte.recursion

import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object PermutationsSpek : Spek({

    val data = listOfNotNull(
        data(listOf(1), listOf(listOf(1))),
        data(listOf(1,2), listOf(listOf(1,2), listOf(2,1))),
        data(listOf(1,2,3), listOf(listOf(1,2,3), listOf(1,3,2), listOf(2,1,3),
            listOf(2,3,1), listOf(3,1,2), listOf(3,2,1))),
        null
    ).toTypedArray()

    describe("permutations") {
        on("numbers: %s", with = *data) { numbers, expected ->
            it("should return $expected") {
                assertThat(permutations(numbers))
                        .isEqualTo(expected)
            }
        }
    }

})