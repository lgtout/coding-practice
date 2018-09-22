package com.lagostout.elementsofprogramminginterviews.recursion

import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on
import org.paukov.combinatorics3.Generator

object GeneratePermutationsWhenDuplicatesMayBePresentSpek : Spek({

    val data = listOfNotNull(
        listOf(1),
        listOf(1,1),
        listOf(1,1,2),
        listOf(1,1,2,2),
        listOf(1,2,3),
        null
    ).map {
        data(it, Generator.permutation(it).simple().toSet())
    }.toTypedArray()

    describe("permutationsWhenDuplicatesMayBePresent") {
        on("items: %s", with = *data) { items, expected ->
            it("should return $expected") {
                assertThat(permutationsWhenDuplicatesMayBePresent(items).toSet())
                        .containsExactlyInAnyOrder(*expected.toTypedArray())
            }
        }
    }

})
