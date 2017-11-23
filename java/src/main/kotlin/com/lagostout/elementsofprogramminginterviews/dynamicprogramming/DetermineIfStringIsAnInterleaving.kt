package com.lagostout.elementsofprogramminginterviews.dynamicprogramming

import org.apache.commons.collections4.iterators.PeekingIterator

/**
 * Problem 17.2.6 page 316
 */
fun determineIfStringIsAnInterleavingOfTwoStrings(
       string: String, first: String, second: String): Boolean {
    val stringIterator = string.reversed().iterator()
    val firstIterator = PeekingIterator(first.reversed().iterator())
    val secondIterator = PeekingIterator(second.reversed().iterator())
    var isNotInterleaving = false
    while (!isNotInterleaving && stringIterator.hasNext()) {
        val stringChar = stringIterator.next()
        when (stringChar) {
            firstIterator.peek() -> {
                firstIterator.next()
            }
            secondIterator.peek() -> {
                secondIterator.next()
            }
            else -> {
                isNotInterleaving = true
            }
        }
    }
    return !isNotInterleaving &&
            !firstIterator.hasNext() &&
            !secondIterator.hasNext()
}