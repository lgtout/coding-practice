package com.lagostout.elementsofprogramminginterviews.stacksandqueues

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.Data1
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on
import kotlin.test.assertEquals
import com.lagostout.elementsofprogramminginterviews.stacksandqueues.EvaluateRpnExpressionWhenOperatorPrecedesOperands.evaluateRpnExpressionWhenOperatorPrecedesOperands as evaluateRpnExpression

class EvaluateRpnExpressionWhenOperatorPrecedesOperandsSpek : Spek({
    describe("evaluateRpnExpressionWhenOperatorPrecedesOperands") {
        val data = listOf<Data1<String, Int?>>(
                data("", expected = null),
                data("1", expected = 1),
                data("+,1,2", expected = 3),
                data("+,+,1,2,+,3,4", expected = 10),
                data("+,1,+,3,4", expected = 8),
                data("/,9,x,+,1,2,-,3,4", expected = -3)
        ).toTypedArray()
        on("expression: \"%s\"", with = *data) {
            expression, expected ->
            it("returns $expected") {
                assertEquals(expected, evaluateRpnExpression(expression))
            }
        }
    }
})