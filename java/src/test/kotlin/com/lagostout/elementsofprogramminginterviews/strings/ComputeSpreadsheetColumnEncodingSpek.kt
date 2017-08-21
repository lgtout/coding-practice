package com.lagostout.elementsofprogramminginterviews.strings

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import kotlin.test.assertEquals

class ComputeSpreadsheetColumnEncodingSpek : Spek({
    describe("numericEncodingOfColumn") {
        testCases.forEach {
            (expectedNumericEncoding, alphabeticEncoding) ->
            given("alphabetic encoding: $alphabeticEncoding") {
                it("returns $expectedNumericEncoding") {
                    assertEquals(expectedNumericEncoding,
                            numericEncodingOfColumn(alphabeticEncoding))
                }
            }
        }
    }
}) {
    companion object {
        data class TestCase(val expectedNumericEncoding: Int) {
            private val alphabeticEncoding = run {
                var number = expectedNumericEncoding
                val stringBuilder = StringBuilder()
                while (number > 0) {
                    stringBuilder.append(((number % 26) - 1 + 'A'.toInt()).toChar())
                    // TODO ???
                    number = number / 26 * 26
                }
                stringBuilder.reverse().toString()
            }
            operator fun component2() = alphabeticEncoding
        }
        val testCases = listOf(
                TestCase(1),
                TestCase(4),
                TestCase(26),
                TestCase(27),
                TestCase(702),
                null).filterNotNull()
    }
}

