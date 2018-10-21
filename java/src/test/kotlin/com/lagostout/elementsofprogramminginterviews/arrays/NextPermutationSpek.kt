package com.lagostout.elementsofprogramminginterviews.arrays

import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object NextPermutationSpek : Spek({

    val data = listOfNotNull(
        data(emptyList(), emptyList()),
        data(listOf(3), listOf(3)),
        data(listOf(2,1,3), listOf(2,3,1)),
        data(listOf(1,3,2), listOf(2,1,3)),
        data(listOf(1,0,3,2,1,0), listOf(1,1,0,0,2,3)),
        null
    ).toTypedArray()

    describe("nextPermutation") {
        on("permutation %s", with = *data) { permutation, expected ->
            assertThat(nextPermutation(permutation))
                    .isEqualTo(expected)
        }
    }

})