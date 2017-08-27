package com.lagostout.elementsofprogramminginterviews.sorting

import org.apache.commons.collections4.iterators.PeekingIterator

fun <T : Comparable<T>> intersectionOfSortedArrays(
        firstArray: List<T>, secondArray: List<T>): List<T> {
    if (firstArray.isEmpty() || secondArray.isEmpty())
        return emptyList()
    val intersection = mutableListOf<T>()
    fun fastForwardTillCurrentValuesAreEqual(
            iterators: Pair<PeekingIterator<T>, PeekingIterator<T>>):
            Pair<PeekingIterator<T>, PeekingIterator<T>> {
        with (iterators) {
            val (firstIterator, secondIterator) =
                    if (first.peek() < second.peek()) {
                        Pair(first, second)
                    } else Pair(second, first)
            while (firstIterator.hasNext() &&
                    firstIterator.peek() < secondIterator.peek()) {
                firstIterator.next()
            }
            return Pair(firstIterator, secondIterator)
        }
    }
    val iterators = fastForwardTillCurrentValuesAreEqual(
            Pair(PeekingIterator<T>(firstArray.iterator()),
                    PeekingIterator<T>(secondArray.iterator())))
    return intersection
}