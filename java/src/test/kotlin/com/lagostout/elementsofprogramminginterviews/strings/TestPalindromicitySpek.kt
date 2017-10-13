package com.lagostout.elementsofprogramminginterviews.strings

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on
import kotlin.test.assertEquals

object TestPalindromicitySpek : Spek({
    describe("testPalindromicity()") {
        val data = listOf(
                data("A man, a plan, a canal, Panama", expected = true),
                data("ab", expected = false),
                data("a b", expected = false),
                data("Ray a Ray", expected = false),
                data("Ray a a Yar", expected = true),
                data("Ray a d Yar", expected = false),
                null
        ).filterNotNull().toTypedArray()
        on("string: %s", with = *data) { string, expected ->
            it("detects palindromicity is $expected") {
                assertEquals(expected, testPalindromicity(string))
            }
        }
    }
})