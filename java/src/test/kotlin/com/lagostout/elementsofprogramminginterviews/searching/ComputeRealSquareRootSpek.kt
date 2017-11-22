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
                data(1.0, 1.0),
                data(27.04, 5.2),
                null
        ).toTypedArray()
        on("number: %s", with = *data) { number, expected ->
            it("returns: $expected") {
                assertThat(computeRealSquareRoot(number))
                        .isEqualTo(expected)
            }
        }
    }
})
