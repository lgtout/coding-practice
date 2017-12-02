package com.lagostout.elementsofprogramminginterviews.searching

import kotlin.math.absoluteValue

/**
 * Problem 12.5.2 page 199
 */
fun divideWithoutUsingDivisionOperator(
        dividend: Double, divisor: Double,
        tolerance: Double): Double {
    var left = 0.0
    var right = dividend.absoluteValue
    val absoluteDivisor = divisor.absoluteValue
    val absoluteDividend = dividend.absoluteValue
    val dividendRange = (absoluteDividend - tolerance)
            .rangeTo(absoluteDividend + tolerance)
    var mid: Double
    loop@ while (true) {
        mid = left + (right - left)/2
        val product = absoluteDivisor * mid
        when  {
            product in dividendRange ->
                break@loop
            product < absoluteDividend ->
                left = mid
            product > absoluteDividend ->
                right = mid
        }
    }
    return mid.let {
        if (dividend * divisor < 0) it * -1 else it
    }
}