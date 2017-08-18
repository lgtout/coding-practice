package com.lagostout.elementsofprogramminginterviews.strings

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import kotlin.test.assertEquals

class BaseConversionSpek : Spek({
    describe("convertBase") {
        testCases.forEach {
            (numberInFirstBase, firstBase, secondBase,
                    expectedNumberAsStringInSecondBase) ->
            given("number: \"$numberInFirstBase\", " +
                    "first base: $firstBase, second base: $secondBase") {
                it("returns \"$expectedNumberAsStringInSecondBase\"") {
                    assertEquals(expectedNumberAsStringInSecondBase,
                            convertBase(numberInFirstBase, firstBase, secondBase))
                }
            }
        }
    }
}) {
    companion object {
        data class TestCase(
                val numberInFirstBase: String,
                val firstBase: Int, val secondBase: Int) {
            private val expectedNumberAsStringInSecondBase: String =
                    if (numberInFirstBase.isNotEmpty())
                        Integer.toString(Integer.parseInt(
                            numberInFirstBase, firstBase), secondBase)
                                .toUpperCase()
                    else ""
            operator fun component4() = expectedNumberAsStringInSecondBase
        }
        val testCases = run {
            listOf(
                TestCase("", 2, 10),
                TestCase("1", 2, 10),
                TestCase("16402", 8, 13),
                TestCase("-16402", 8, 13),
                TestCase("B1C9", 13, 8),
                TestCase("-B1C9", 13, 8),
            null).filterNotNull()
        }
    }
}