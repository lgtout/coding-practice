package com.lagostout.elementsofprogramminginterviews.stacksandqueues

import java.util.*

object EvaluateRpnExpressionWhenOperatorPrecedesOperands {

    enum class Operation(val symbol: String) {

        ADD("+") {
            override fun compute(first: Int, second: Int): Int {
                return first + second
            }
        },
        SUBTRACT("-") {
            override fun compute(first: Int, second: Int): Int {
                return first - second
            }
        },
        MULTIPLY("x") {
            override fun compute(first: Int, second: Int): Int {
                return first * second
            }
        },
        DIVIDE("/") {
            override fun compute(first: Int, second: Int): Int {
                return first / second
            }
        };

        abstract fun compute(first: Int, second: Int): Int

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
                val rightOperand = stack.pop()
                val leftOperand = stack.pop()
                stack.push(op.compute(leftOperand, rightOperand))
            } ?: stack.push(Integer.parseInt(it))
        }
        return stack.pop()
    }

}
