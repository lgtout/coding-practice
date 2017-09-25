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
    fun fastForwardToNextPageIndex(iterator: PeekingIterator<T>) {
        if (!iterator.hasNext()) return
        val firstValue = iterator.peek()
        do {
            iterator.next()
        } while (iterator.hasNext() &&
                iterator.peek() == firstValue)
    }
    fun fastForwardToPageIndex(iterator: PeekingIterator<T>, pageIndex: T) {
        if (!iterator.hasNext()) return
        while (firstIterator.peek() < pageIndex) {
            firstIterator.next()
        }
    }
    var done = false
    while (!done) {
        if (firstIterator.peek() == secondIterator.peek()) {
            val duplicate = firstIterator.peek()
            intersection.add(duplicate)
            fastForwardToNextPageIndex(firstIterator)
            fastForwardToNextPageIndex(secondIterator)
        } else {
            while (true) {
                done = !firstIterator.hasNext() || !secondIterator.hasNext()
                if (done) break
                fastForwardToPageIndex(firstIterator, secondIterator.peek())
                fastForwardToPageIndex(secondIterator, firstIterator.peek())
            }
        }
    }
    return intersection
}