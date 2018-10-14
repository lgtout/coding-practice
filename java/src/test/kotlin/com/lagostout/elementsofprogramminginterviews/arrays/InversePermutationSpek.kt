package com.lagostout.elementsofprogramminginterviews.arrays

import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object InversePermutationSpek : Spek({

    val data = listOfNotNull(
        emptyList(),
        listOf(0),
        listOf(0,1),
        listOf(1,0),
        listOf(1,4,2,3,0),
        listOf(5,4,3,2,1,0),
        null
    ).map {
        data(it, it)
    }.toTypedArray()

    describe("inversePermutation") {
        on("permutation %s", with = *data) { permutation, expected ->
            it("should return $expected") {
                // Inverting a permutation a second time should undo
                // the first transformation.
                assertThat(expected).isEqualTo(
                    inversePermutation(inversePermutation(
                        permutation.toMutableList())))
            }
        }
    }

})
