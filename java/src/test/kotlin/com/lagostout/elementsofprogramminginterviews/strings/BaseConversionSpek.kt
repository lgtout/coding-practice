package com.lagostout.elementsofprogramminginterviews.strings

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it

class BaseConversionSpek : Spek({
    describe("") {
        testCases.forEach {
            given("") {
                it("") {

                }
            }
        }
    }
}) {
    companion object {
        data class TestCase(val numberInFirstBase: String,
                       val firstBase: Int, val secondBase: Int) {
            val expectedNumberAsStringInSecondBase: String =
                    Integer.toString(Integer.parseInt(
                            numberInFirstBase, firstBase), secondBase)
            operator fun component4() = expectedNumberAsStringInSecondBase
        }
        val testCases = run {
            listOf<TestCase>()
        }
    }
}