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
                    println(number)
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
                TestCase(1), // A
                TestCase(4), // D
                TestCase(26), // Z
                TestCase(27), // AA
                TestCase(702), // ZZ
                null).filterNotNull()
    }
}

