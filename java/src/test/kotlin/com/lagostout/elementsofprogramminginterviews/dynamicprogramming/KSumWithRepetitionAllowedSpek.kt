package com.lagostout.elementsofprogramminginterviews.dynamicprogramming

import com.lagostout.elementsofprogramminginterviews.greedyalgorithmsandinvariants.permutationsWithRepetition
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.Data2
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on
import kotlin.test.assertEquals

/**
 * Problem 18.4.3 page 346
 */
class KSumWithRepetitionAllowedSpek : Spek({
    describe("permutationsWithRepetition") {
        val data = listOf(
                data(listOf(1), 0, expected = emptyList<List<Int>>()),
                data(listOf(1,2), 0, expected = emptyList()),
                null
        ).filterNotNull().toTypedArray()
        on("generate permutations of %s with %s repeats allowed", with = *data) {
            elements: List<Int>, repeatCount: Int, expected: List<List<Int>> ->
            it("returns $expected") {
                assertEquals(expected,
                        permutationsWithRepetition(elements, repeatCount))
            }
        }
    }
})
