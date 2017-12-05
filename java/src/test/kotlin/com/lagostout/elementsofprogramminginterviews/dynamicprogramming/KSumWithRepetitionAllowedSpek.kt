package com.lagostout.elementsofprogramminginterviews.dynamicprogramming

import com.lagostout.elementsofprogramminginterviews.greedyalgorithmsandinvariants.canPickKNumbersThatAddUpToSumWithRepetitionAllowed
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on
import org.paukov.combinatorics3.Generator
import kotlin.streams.toList

class KSumWithRepetitionAllowedSpek : Spek({
    describe("permutationsWithRepetition") {
        fun bruteForceSolution(list: List<Int>, k: Int, sum: Int): Boolean {
            return Generator.combination(list).multi(k).stream()
                    .filter { it.sum() == sum }.toList().also {
                println(it)
            }.isNotEmpty()
        }
        val data = listOfNotNull(
                Triple(listOf(1), 0, 3),
                Triple(listOf(1,2).shuffled(), 0, 2),
                null
        ).map { (list, k, sum) ->
            data(list, k, sum, bruteForceSolution(list, k, sum))
        }.toTypedArray()
        on("list: %s, k: %s, sum: %s", with = *data) {
            list: List<Int>, k: Int, sum: Int, expected: Boolean ->
            it("returns $expected") {
                assertThat(canPickKNumbersThatAddUpToSumWithRepetitionAllowed(
                                list, k, sum)).isEqualTo(expected)
            }
        }
    }
})
