package com.lagostout.elementsofprogramminginterviews.dynamicprogramming

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

class KSumWithRepetitionAllowedSpek : Spek({
    describe("permutationsWithRepetition") {
        val data = arrayOf(
                data(arrayOf(1), 0),
                data(arrayOf(1,2), 0)
        )
        on("generate permutations of %s with %s repeats allowed", with = *data) {
            elements, repeats ->
            // TODO
        }
    }
})
