package com.lagostout.elementsofprogramminginterviews.stacksandqueues

import java.util.*

/**
 * Problem 9.2.2 page 136
 */
object EvaluateRpnExpressionWhenOperatorPrecedesOperands {

    enum class Operation(val symbol: String) {

        ADD("+") {
            override fun compute(firstOperand: Int, secondOperand: Int): Int {
                return firstOperand + secondOperand
            }
        },
        SUBTRACT("-") {
            override fun compute(firstOperand: Int, secondOperand: Int): Int {
                return firstOperand - secondOperand
            }
        },
        MULTIPLY("x") {
            override fun compute(firstOperand: Int, secondOperand: Int): Int {
                return firstOperand * secondOperand
            }
        },
        DIVIDE("/") {
            override fun compute(firstOperand: Int, secondOperand: Int): Int {
                return firstOperand / secondOperand
            }
        };

        abstract fun compute(firstOperand: Int, secondOperand: Int): Int

        companion object {
            fun valueFor(symbol: String): Operation? {
                return values().find { it.symbol == symbol }
            }
        }

    }

    fun evaluateRpnExpressionWhenOperatorPrecedesOperands(expression: String): Int? {
        if (expression.isEmpty()) return null
        val stack = LinkedList<Int>()
        val parts = expression.split(',')
        parts.reversed().forEach {
            Operation.valueFor(it)?.let { op ->
                val leftOperand = stack.pop()
                val rightOperand = stack.pop()
                stack.push(op.compute(leftOperand, rightOperand))
            } ?: stack.push(Integer.parseInt(it))
        }
        return stack.pop()
    }

}
