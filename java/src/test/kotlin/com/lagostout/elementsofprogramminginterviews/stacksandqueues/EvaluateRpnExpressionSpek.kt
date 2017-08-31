package com.lagostout.elementsofprogramminginterviews.stacksandqueues

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import kotlin.test.assertEquals

class EvaluateRpnExpressionSpek : Spek({
    describe("evaluateRpnExpression") {
        testCases.forEach {
            (expression, expected) ->
            given("expression: $expression") {
                it("returns $expected") {
                    assertEquals(expected,
                            evaluateRpnExpression(expression))
                }
            }
        }
    }
}) {
    data class TestCase(val expression: String, val expected: Int?)
    companion object {
        val testCases = listOf(
                TestCase("", null),
                TestCase("2", 2),
                TestCase("2,3,+", 5),
                TestCase("2,3,-", -1),
                TestCase("-2,3,-", -5),
                TestCase("2,-3,x", -6),
                TestCase("2,3,-4,x,+", -10),
                null).filterNotNull()
    }
}