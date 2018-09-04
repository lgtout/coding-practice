package com.lagostout.elementsofprogramminginterviews.primitivetypes

import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on
import kotlin.math.pow

object ComputeXToPowerYSpek : Spek({

    val data = listOfNotNull(
        Pair(1.0, 1),
        Pair(1.0, 2),
        Pair(2.0, 1),
        Pair(2.0, 2),
        Pair(2.0, 3),
        Pair(3.0, 1),
        Pair(3.0, 3),
        Pair(3.0, 4),
        Pair(3.0, 5),
        Pair(3.0, 7),
        Pair(3.0, 8),
        null
    ).map { (x, y) ->
        data(x, y, x.pow(y))
    }.toTypedArray()

    describe("computeXToPowerY") {
        on("x: %s, y: %s", with = *data) { x, y, expected ->
            it("should return $expected") {
                assertThat(computeXToPowerY(x, y))
                        .isEqualTo(expected)
            }
        }
    }

})