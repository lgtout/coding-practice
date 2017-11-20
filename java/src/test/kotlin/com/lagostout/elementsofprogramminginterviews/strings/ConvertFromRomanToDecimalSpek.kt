package com.lagostout.elementsofprogramminginterviews.strings

import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object ConvertFromRomanToDecimalSpek : Spek({
    val data = listOfNotNull(
            data("I", 1),
            data("V", 5),
            data("X", 10),
            data("L", 50),
            data("C", 100),
            data("D", 500),
            data("M", 1000),
            data("II", 2),
            data("VII", 7),
            data("XVII", 17),
            data("LXVII", 67),
            data("CLXVII", 167),
            data("DCLXVII", 667),
            data("MDCLXVII", 1667),
            data("VVII", 12),
            data("VVVII", 17),
            data("XXVVVII", 37),
            data("CCCXXVVVII", 337),
            data("DDCCCXXVVVII", 1337),
            data("MMMDDCCCXXVVVII", 4337),
            data("IV", 4),
            data("IX", 9),
            data("IVV", 9),
            data("IXX", 19),
            data("XL", 40),
            data("XLL", 90),
            data("XCC", 190),
            data("CD", 400),
            data("CM", 900),
            data("CDD", 900),
            data("CMM", 1900),
            data("XXXXXIIIIIIIII", 59),
            data("LVIIII", 59),
            data("LIX", 59),
            data("CMMXCCIXX", 2109),
            null
    ).toTypedArray()
    describe("convertFromRomanToDecimal()") {
        on("roman: %s", with = *data) { roman, expected ->
            it("returns $expected") {
                assertThat(convertFromRomanToDecimal(roman))
                        .isEqualTo(expected)
            }
        }
    }
})
