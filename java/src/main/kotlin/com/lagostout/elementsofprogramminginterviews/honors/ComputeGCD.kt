package com.lagostout.elementsofprogramminginterviews.honors

/**
 * Problem 25.1 page 443
 */
@Suppress("NAME_SHADOWING")
fun computeGCD(firstNumber: Int, secondNumber: Int): Int {
    val gcd: Int
    var multiplesOfTwo = 0
    var firstNumber = firstNumber
    var secondNumber = secondNumber
    while (true) {
        while ((firstNumber and 1 or secondNumber and 1) == 0) {
            multiplesOfTwo++
            firstNumber = firstNumber shr 1
            secondNumber = secondNumber shr 1
        }
        while (firstNumber and 1 == 0) firstNumber = firstNumber shr 1
        while (secondNumber and 1 == 0) secondNumber = secondNumber shr 1
        if (firstNumber == secondNumber) {
            gcd = firstNumber shl multiplesOfTwo
            break
        }
        // TODO Not sure about this - how it affects time complexity.
        // Does it make complexity not depend solely on bit-count?
        val numbers = listOf(firstNumber, secondNumber).sorted().run {
            listOf(get(0), get(1) - get(0))
        }
        firstNumber = numbers[0]
        secondNumber = numbers[1]
    }
    return gcd
}

@Suppress("NAME_SHADOWING")
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
