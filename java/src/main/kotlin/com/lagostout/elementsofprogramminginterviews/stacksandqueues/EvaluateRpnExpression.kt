package com.lagostout.elementsofprogramminginterviews.stacksandqueues

import java.util.*

/**
 * Problem 9.2 page 136
 */
fun evaluateRpnExpression(expression: String): Int? {
    if (expression.isEmpty()) return null
    val stack = LinkedList<Int>()
    val parts = expression.split(',')
    parts.forEach {
        when (it in listOf("+", "-", "x", "/")) {
            true -> {
                val second = stack.pop()
                val first = stack.pop()
                when (it) {
                    "+" -> {
                        first + second
                    }
                    "-" -> {
                        first - second
                    }
                    "x" -> {
                        first * second
                    }
                    else -> { // "/"
                        first / second
                    }
                }.let {
                    stack.push(it)
                }
            }
            false -> {
                stack.push(Integer.parseInt(it))
            }
        }
    }
    return stack.pop()
}