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
    fun seekLastOccurrenceOfPageIndex(iterator: PeekingIterator<T>, pageIndex: T) {
        while (iterator.peek() == pageIndex) {
           iterator.next()
        }
    }
    fun seekPageIndex(iterator: PeekingIterator<T>, pageIndex: T) {
        if (!iterator.hasNext()) return
        while (true) {
            firstIterator.peek()?.let {
                if (it < pageIndex) firstIterator.next()
            } ?: break
        }
    }
    var done = false
    while (!done) {
        listOf(firstIterator.peek(), secondIterator.peek()).let {
            (firstPageIndex, secondPageIndex) ->
            if (listOf(firstPageIndex, secondPageIndex).contains(null)) done = true
            else if (firstPageIndex == secondPageIndex) {
                intersection.add(firstPageIndex)
                seekLastOccurrenceOfPageIndex(firstIterator, firstPageIndex)
                seekLastOccurrenceOfPageIndex(secondIterator, firstPageIndex)
            } else {
                while (true) {
                    // TODO ?
//                    done = !firstIterator.hasNext() || !secondIterator.hasNext()
//                    if (done) break
                    seekPageIndex(firstIterator, secondIterator.peek())
                    seekPageIndex(secondIterator, firstIterator.peek())
                }
            }
        }
    }
    return intersection
}