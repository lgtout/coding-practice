package com.lagostout.bytebybyte.recursion

import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object KnapsackProblemUsingDFSSpek : Spek({

    val data = listOfNotNull(
        data(listOf(Item(1,1)), 1, 1),
        data(listOf(Item(1,1)), 0, 0),
        data(listOf(Item(1,1), Item(1,1)), 1, 1),
        data(listOf(Item(1,1), Item(2,1)), 1, 2),
        data(listOf(Item(1,1), Item(1,2)), 2, 1),
        data(listOf(Item(1,1), Item(1,2)), 3, 2),
        data(listOf(Item(3,1), Item(2,1)), 1, 3),
        data(listOf(Item(5,4), Item(3,1), Item(3,2)), 4, 6),
        null
    ).toTypedArray()

    describe("knapsack") {
        on("items: %s, maxWeight: %s", with = *data) { items, maxWeight, expected ->
            it("should return $expected") {
                assertThat(knapsack(items, maxWeight))
                        .isEqualTo(expected)
            }
        }
    }

})
