package com.lagostout.elementsofprogramminginterviews.searching

import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object ComputeIntegerSquareRootSpek : Spek({
    describe("computeIntegerSquareRoot") {
        val data = listOfNotNull(
                data(1, 1),
                data(2, 1),
                data(3, 1),
                data(4, 2),
                data(5, 2),
                data(7, 2),
                data(9, 3),
                data(300, 17),
                null
        ).toTypedArray()
        on("integer: %s", with = *data) { integer, expected ->
            it("returns: $expected") {
                assertThat(computeIntegerSquareRoot(integer))
                        .isEqualTo(expected)
            }
        }
    }
})