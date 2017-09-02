package com.lagostout.elementsofprogramminginterviews.sorting

import org.apache.commons.collections4.iterators.PeekingIterator

/**
 * Problem 14.1 page 240
 */
fun <T : Comparable<T>> intersectionOfSortedArrays(
        firstArray: List<T>, secondArray: List<T>): List<T> {
    if (firstArray.isEmpty() || secondArray.isEmpty())
        return emptyList()
    val intersection = mutableListOf<T>()
    val firstIterator = PeekingIterator<T>(firstArray.iterator())
    val secondIterator = PeekingIterator<T>(secondArray.iterator())
    while (true) {
        if (firstIterator.peek() == secondIterator.peek()) {
            intersection.add(firstIterator.next())
            secondIterator.next()
            while (firstIterator.hasNext() &&
                    firstIterator.peek() == secondIterator.peek()) {
                firstIterator.next()
            }
        }
        // TODO Continue here.  Avoid having to use optional/null - it complicates matters.
        var currentFirstValue: T? = null
        while (firstIterator.hasNext() &&
                firstIterator.peek() < secondIterator.peek()) {
            currentFirstValue = firstIterator.next()
        }
//        while (secondIterator.hasNext() &&
//                secondIterator.peek() < currentFirstValue) {
//            secondIterator.next()
//        }
        if (!firstIterator.hasNext() || !secondIterator.hasNext())
            break
    }
    return intersection
}