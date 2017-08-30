package com.lagostout.elementsofprogramminginterviews.strings

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import kotlin.test.assertEquals

class ComputeSpreadsheetColumnEncodingWhereAIsZeroSpek : Spek({
    describe("numericEncodingOfColumn") {
        testCases.forEach {
            (expectedNumericEncoding, alphabeticEncoding) ->
            given("alphabetic encoding: $alphabeticEncoding") {
                it("returns $expectedNumericEncoding") {
                    assertEquals(expectedNumericEncoding,
                            numericEncodingOfColumnWhereAIsZero(
                                    alphabeticEncoding))
                }
            }
        }
    }
}) {
    companion object {
        data class TestCase(val expectedNumericEncoding: Int) {
            private val alphabeticEncoding = run {
                var number = expectedNumericEncoding + 1
                val stringBuilder = StringBuilder()
                while (number > 0) {
//                    println(number)
                    stringBuilder.append((((number - 1) % 26) + 'A'.toInt()).toChar())
                    number /= 27
                }
                stringBuilder.reverse().toString()
            }
            operator fun component2() = alphabeticEncoding
        }
        // We only need numeric encodings > 0 because
        // alphabetic encoding starts at A, which is
        // numerically encoded as 1.
        val testCases = listOf(
                TestCase(0), // A
                TestCase(3), // D
                TestCase(25), // Z
                TestCase(26), // AA
                TestCase(701), // ZZ
                null).filterNotNull()
    }
}