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
        while (true) {
            iterator.peek()?.let {
                if (it < pageIndex) iterator.next() else null
            } ?: return
        }
    }
    var done = false
    while (!done) {
        listOf(firstIterator.peek(), secondIterator.peek()).let {
            (firstPageIndex, secondPageIndex) ->
            when {
                listOf(firstPageIndex, secondPageIndex).contains(null) ->
                    done = true
                firstPageIndex == secondPageIndex -> {
                    intersection.add(firstPageIndex)
                    seekLastOccurrenceOfPageIndex(firstIterator, firstPageIndex)
                    seekLastOccurrenceOfPageIndex(secondIterator, secondPageIndex)
                }
                else -> while (true) {
                    secondIterator.peek()?.let {
                        seekPageIndex(firstIterator, it)
                    } ?: break
                    firstIterator.peek()?.let {
                        seekPageIndex(secondIterator, it)
                    } ?: break
                    if (firstIterator.peek() == secondIterator.peek()) break
                }
            }
        }
    }
    return intersection
}