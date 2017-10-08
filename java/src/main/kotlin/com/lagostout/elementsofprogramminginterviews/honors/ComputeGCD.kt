package com.lagostout.elementsofprogramminginterviews.honors

/**
 * Problem 25.1 page 443
 */
@Suppress("NAME_SHADOWING")
fun computeGCD(firstNumber: Int, secondNumber: Int): Int {
    var multiplesOfTwo = 0
    var firstNumber = firstNumber
    var secondNumber = secondNumber
    val firstNumberLessThanZero = firstNumber < 0
    val secondNumberLessThanZero = secondNumber < 0
    if (firstNumberLessThanZero) {
        firstNumber = -firstNumber
    }
    if (secondNumberLessThanZero) {
        secondNumber = -secondNumber
    }
    while (firstNumber > 0 && secondNumber > 0) {
        if ((firstNumber and 1 or secondNumber and 1) == 0) {
            multiplesOfTwo++
            firstNumber = firstNumber shr 1
            secondNumber = secondNumber shr 1
        }
        else if (firstNumber and 1 == 0)
            firstNumber = firstNumber shr 1
        else if (secondNumber and 1 == 0)
            secondNumber = secondNumber shr 1
        else if (firstNumber == secondNumber) {
            break
        } else {
            // At this point, both numbers are odd i.e. last bits are 1.
            // Subtract lesser from greater.
            val numbers = listOf(firstNumber, secondNumber).sorted().run {
                listOf(get(0), get(1) - get(0))
            }
            firstNumber = numbers[0]
            secondNumber = numbers[1]
        }
    }
    return firstNumber shl multiplesOfTwo
}

@Suppress("NAME_SHADOWING")
// Doesn't handle negative values.
fun computeGCDRecursively(first: Int, second: Int): Int {
    if (first == second) return first
    var first = first
    var second = second
    if (first < second) {
        second -= first
    } else {
        first -= second
    }
    return computeGCDRecursively(first, second)
}
