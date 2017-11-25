package com.lagostout.elementsofprogramminginterviews.strings

import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object VerifyRomanNumberIsValidSpek : Spek({
    describe("romanNumberIsValid()") {
        val data = listOfNotNull(
                data("I", true),
                data("V", true),
                data("X", true),
                data("L", true),
                data("C", true),
                data("D", true),
                data("M", true),
                data("II", true),
                data("VII", true),
                data("XVII", true),
                data("LXVII", true),
                data("CLXVII", true),
                data("DCLXVII", true),
                data("MDCLXVII", true),
                data("VVII", true),
                data("VVVII", true),
                data("XXVVVII", true),
                data("CCCXXVVVII", true),
                data("DDCCCXXVVVII", true),
                data("MMMDDCCCXXVVVII", true),
                data("IV", true),
                data("IX", true),
                data("IVV", true),
                data("IXX", true),
                data("XL", true),
                data("XLL", true),
                data("XCC", true),
                data("CD", true),
                data("CM", true),
                data("CDD", true),
                data("CMM", true),
                data("XXXXXIIIIIIIII", true),
                data("LVIIII", true),
                data("LIX", true),
                data("CMMXCCIXX", true),
                data("IIL", false),
                data("IIC", false),
                data("IID", false),
                data("IIM", false),
                data("XXD", false),
                data("XXM", false),
                null
        ).toTypedArray()
        describe("romanNumberIsValid()") {
            on("roman: %s", with = *data) { roman, expected ->
                it("returns $expected") {
                    assertThat(romanNumberIsValid(roman))
                            .isEqualTo(expected)
                }
            }
        }
    }
})