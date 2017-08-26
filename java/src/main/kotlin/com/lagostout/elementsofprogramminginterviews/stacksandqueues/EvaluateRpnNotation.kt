package com.lagostout.elementsofprogramminginterviews.stacksandqueues

import java.util.*

/**
 * Problem 9.2 page 136
 */
// TODO Not sure if this approach will work.  Is it possible to have nested expressions on the right side of an operation?
fun evaluateRpnNotation(arithmeticExpression: String) {
    val stack = LinkedList<Int>()
    var index = 0
    var result = 0
    val parts = arithmeticExpression.split(',')
    // TODO arithmeticExpression may be empty.
    val operation =
            fun (operand1: Int, operand2: Int, operator: String): Int{
                return when (operator) {
                    "+" -> {
                        operand1 + operand2
                    }
                    "-" -> {
                        operand1 - operand2
                    }
                    "x" -> {
                        operand1 * operand2
                    }
                    "/" -> {
                        operand1 / operand2
                    }
                    else -> throw IllegalArgumentException()
                }
            }
//    for (part in parts) {
//        val operation = when (part) {
//            "+" -> {
//
//            }
//            "-" -> {
//
//            }
//        }
//    }
    while (true) {
//        if ()
        val builder = StringBuilder()
        while (index <= arithmeticExpression.lastIndex) {
            val char = arithmeticExpression[index]
            if (char == '-' || char.isDigit()) {
                builder.append(char)
                index++
            } else break
        }
        if (builder.isNotEmpty())
            stack.push(builder.reverse().toString().toInt())
        if (index >= arithmeticExpression.lastIndex) break
        val char = arithmeticExpression[index]
        if (char == ',') index++
        val operators = listOf('+','-','x','/')
//        val expressions = listOf(stack.remove(), stack.remove()).
//        if (char in operators) {
//            when (char) {
//                '+' -> {
//                    listOf(1,2).reduce
//                }
//            }
//        }
    }
}