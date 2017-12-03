package com.lagostout.elementsofprogramminginterviews.binarysearchtrees

/**
 * Problem 15.7 page 271
 */
fun enumerateNumbers(count: Int): List<Double> {
    data class Expression(val a: Int, val b: Int) {
        val value: Double
            get() {
                return a + b * Math.sqrt(2.0)
            }
    }
    val numbers = mutableListOf<Expression>(). apply {
        add(Expression(0, 0))
    }
    var currentCoeffA = 1
    var currentCoeffB = 1
    var maxCoeffA = 1
    var nextZeroCoeffAExpression = Expression(0, 1)
    var nextZeroCoeffBExpression = Expression(1, 0)
    while (numbers.size < count) {
        (maxCoeffA downTo 1).forEach {

        }
        ++maxCoeffA
    }
    return emptyList()
}