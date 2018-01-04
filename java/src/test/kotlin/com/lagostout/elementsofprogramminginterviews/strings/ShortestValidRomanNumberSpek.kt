package com.lagostout.elementsofprogramminginterviews.strings

import com.lagostout.elementsofprogramminginterviews.strings.ShortestValidRomanNumber.computeShortestValidRomanNumber
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object ShortestValidRomanNumberSpek : Spek({
    describe("computeShortestValidRomanNumber") {
        val data = listOfNotNull(
//                data(1, "I"),
//                data(2, "II"),
                data(3, "III"),
//                data(4, "IV"),
//                data(5, "V"),
//                data(7, "VII"),
//                data(8, "IIX"),
//                data(10, "X"),
//                data(14, "XIV"),
//                data(17, "XVII"),
                null
        ).toTypedArray()
        on("number: %s", with = *data) { number, expected ->
            it("returns $expected") {
                assertThat(computeShortestValidRomanNumber(number))
                        .isEqualTo(expected)
            }
        }
    }
})