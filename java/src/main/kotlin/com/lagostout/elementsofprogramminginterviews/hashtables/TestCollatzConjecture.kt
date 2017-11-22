package com.lagostout.elementsofprogramminginterviews.hashtables

import java.math.BigInteger

/**
 * Problem 13.13 page 234
 */
fun testCollatzConjecture(count: Int): Boolean {
    val found = mutableSetOf<BigInteger>()
    var conjectureIsTrue = true
    (1..count).forEach {
        val seen = mutableSetOf<BigInteger>()
        var currentNumber = BigInteger.valueOf(it.toLong())
        while (true) {
            if (seen.contains(currentNumber)) {
                conjectureIsTrue = false
                break
            } else {
                seen.add(currentNumber)
                if (currentNumber == BigInteger.ONE ||
                        currentNumber in found) {
                    found.addAll(seen)
                    break
                } else {
                    currentNumber = if (currentNumber.mod(BigInteger.valueOf(2))
                            == BigInteger.ZERO) {
                        currentNumber.div(BigInteger.valueOf(2))
                    } else {
                        currentNumber.times(BigInteger.valueOf(3))
                                .plus(BigInteger.ONE)
                    }
                }
            }
        }
    }
    return conjectureIsTrue
}