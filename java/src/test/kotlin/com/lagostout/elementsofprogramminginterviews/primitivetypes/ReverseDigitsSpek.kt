package com.lagostout.elementsofprogramminginterviews.primitivetypes

import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object ReverseDigitsSpek : Spek({

    val data = listOfNotNull(
        data(0, 0),
        data(1, 1),
        data(-1, -1),
        data(12, 21),
        data(-12, -21),
        null
    ).toTypedArray()

    describe("reverseDigits2") {
        on("number %s", with = *data) { number, expected ->
            it("should return $expected") {
                assertThat(reverseDigits2(number))
                        .isEqualTo(expected)
            }
        }
    }

})