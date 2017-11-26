package com.lagostout.elementsofprogramminginterviews.searching

import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object ComputeRealSquareRootSpek : Spek({
    describe("computeRealSquareRoot()") {
        val data = listOfNotNull(
                0.001,
                0.5,
                1.0,
                5.2,
                null
        ).map {
            data(it, Math.sqrt(it))
        }.toTypedArray()
        on("number: %s", with = *data) { number, expected ->
            it("returns: $expected") {
                val epsilon = 0.00001
                assertThat(computeRealSquareRoot(number, epsilon))
                        .isBetween(expected - epsilon, expected + epsilon)
            }
        }
    }
})
