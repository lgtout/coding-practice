package com.lagostout.bytebybyte.dynamicprogramming;

import com.lagostout.bytebybyte.dynamicprogramming.EggDrop.computeBottomUpWithMemoization
import com.lagostout.bytebybyte.dynamicprogramming.EggDrop.computeWithRecursionAndMemoization
import com.lagostout.bytebybyte.dynamicprogramming.EggDrop.computeWithRecursionByBruteForce
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object EggDropSpek : Spek({

    val data = listOfNotNull(
        data(1, 1, 1),
        data(1, 10, 10),
        data(2, 4, 3),
        data(2, 5, 3),
        data(2, 6, 3),
        data(2, 7, 4),
        data(2, 8, 4),
        data(2, 9, 4),
        data(2, 10, 4),
        data(2, 11, 5),
        data(2, 12, 5),
        data(2, 15, 5),
        data(2, 20, 6),
        data(3, 10, 4),
        null
    ).toTypedArray()

    describe("computeWithRecursionByBruteForce") {
        on("eggs %s, floors %s", with = *data) { eggs, floors, expected ->
            it("returns $expected") {
                assertThat(computeWithRecursionByBruteForce(eggs, floors))
                        .isEqualTo(expected)
            }
        }
    }

    describe("computeWithRecursionAndMemoization") {
        on("eggs %s, floors %s", with = *data) { eggs, floors, expected ->
            it("returns $expected") {
                assertThat(computeWithRecursionAndMemoization(eggs, floors))
                        .isEqualTo(expected)
            }
        }
    }

    describe("computeBottomUpWithMemoization") {
        on("eggs %s, floors %s", with = *data) { eggs, floors, expected ->
            it("returns $expected") {
                assertThat(computeBottomUpWithMemoization(eggs, floors))
                        .isEqualTo(expected)
            }
        }
    }

})
