package com.lagostout.elementsofprogramminginterviews.strings

import java.util.*

object TestStringForWellFormedness {

    private fun isClosingBracket(bracket: Char, otherBracket: Char): Boolean {
        return (bracket == '(' && otherBracket == ')') ||
                (bracket == '{' && otherBracket == '}') ||
                (bracket == '[' && otherBracket == ']')
    }

    fun isWellFormed(string: String): Boolean {
        val chars = string.toList()
        if (chars.find { it !in "(){}[]" } != null)
            throw IllegalArgumentException()
        val stack = LinkedList<Char>()
        chars.forEach {
            if (stack.isNotEmpty() &&
                    isClosingBracket(stack.peek(), it)) stack.pop()
            else stack.push(it)
        }
        return stack.isEmpty()
    }

}