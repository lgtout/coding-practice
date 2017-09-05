package com.lagostout.elementsofprogramminginterviews.strings

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import kotlin.test.assertEquals

class TestStringForWellFormednessSpek : Spek({
    describe("isWellFormed") {
        testCases.forEach {
            (string, expected) ->
            given("string: $string") {
                it("finds string well-formedness is: " +
                        if (expected) "true" else "false") {
                    assertEquals(expected,
                            TestStringForWellFormedness.isWellFormed(string))
                }
            }
        }
    }
}) {
    companion object {
        data class TestCase(val string: String = "", val expected: Boolean = true)
        val testCases = listOf(
                TestCase(),
                TestCase("{}"),
                TestCase("()"),
                TestCase("[]"),
                TestCase("[()]"),
                TestCase("[(){}[]]"),
                TestCase("[(){}[]]{}"),
                TestCase("[(){}[[]]{([])}]{}"),
                TestCase("[(){}[]]{}}", false),
                TestCase("[((){}[]]{}}", false),
                TestCase("[(]", false),
                null).filterNotNull()
    }
}