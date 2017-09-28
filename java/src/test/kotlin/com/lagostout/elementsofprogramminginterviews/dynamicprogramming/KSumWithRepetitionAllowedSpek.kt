package com.lagostout.elementsofprogramminginterviews.dynamicprogramming

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

/**
 * Problem 18.4.3 page 346
 */
class KSumWithRepetitionAllowedSpek : Spek({
    describe("permutationsWithRepetition") {
        val data = arrayOf(
                data(arrayOf(1), 0, expected = false),
                data(arrayOf(1,2), 0, expected = false)
        )
        on("generate permutations of %s with %s repeats allowed", with = *data) {
            elements, repeats, expected ->
            // TODO
        }
    }
})
