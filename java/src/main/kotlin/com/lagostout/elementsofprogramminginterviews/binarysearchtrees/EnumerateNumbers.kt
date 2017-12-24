package com.lagostout.elementsofprogramminginterviews.binarysearchtrees

import java.util.TreeSet
import kotlin.Comparator

/**
 * Problem 15.7 page 271
 */
object EnumerateNumbers {
    data class Expression(val a: Int, val b: Int) {
        val value = a + b * Math.sqrt(2.0)
    }
    // O(n) solution.
    fun enumerateNumbers(count: Int): List<Expression> {
        val numbers = mutableListOf<Expression>(). apply {
            add(Expression(0, 0))
        }
        var nextZeroCoeffAExpression = Expression(0, 1)
        var sourceSetSize = 1
        while (numbers.size < count) {
            var index = numbers.size - sourceSetSize
            val endIndex = numbers.lastIndex
            while (index <= endIndex && numbers.size < count) {
                val nextExpression = numbers[index++].run {
                    copy(a = a + 1)
                }
                if (nextExpression.value >
                        nextZeroCoeffAExpression.value) {
                    numbers.add(nextZeroCoeffAExpression)
                    if (numbers.size == count) break
                    nextZeroCoeffAExpression = nextZeroCoeffAExpression.run {
                        copy(b = b + 1)
                    }
                }
                numbers.add(nextExpression)
            }
            sourceSetSize = numbers.lastIndex - endIndex
        }
        return numbers
    }
    // O(n log(n)) solution
    fun enumerateNumbersWithBST(count: Int): List<Expression> {
        val treeSet = TreeSet<Expression>(Comparator<Expression> {
            o1, o2 -> compareValues(o1?.value, o2?.value) })
        (0 until count).forEach {
            treeSet.add(Expression(0, it))
        }
        var expressionCount = 1
        var expression = treeSet.first()
        while (expressionCount < count) {
            treeSet.add(expression.copy(a = expression.a + 1))
            expression = treeSet.higher(expression)
            ++expressionCount
        }
        return treeSet.take(count)
    }
}