package com.lagostout.elementsofprogramminginterviews.heaps

import org.apache.commons.collections4.iterators.PeekingIterator
import java.util.*

/**
 * Problem 11.2 page 180
 */
object SortIncreasingDecreasingArray {

    enum class Direction {
        INCREASING, DECREASING;
    }

    fun sort(array: List<Int>): List<Int> {
        val result = mutableListOf<Int>()
        if (array.isEmpty()) return result
        val heap = PriorityQueue({
            iterator: PeekingIterator<Int>,
            otherIterator: PeekingIterator<Int> ->
            iterator.peek() - otherIterator.peek()
        })
        val arrayIterator = array.iterator()
        var increasingDecreasingSegment = mutableListOf<Int>()
        var previousItem = arrayIterator.next()
        increasingDecreasingSegment.add(previousItem)
        val increasingDecreasingSegments =
                mutableListOf(increasingDecreasingSegment)
        var previousDirection = Direction.INCREASING
        arrayIterator.forEach {
            val direction =
                    if (it == previousItem) previousDirection
                    else if (it > previousItem) Direction.INCREASING
                    else Direction.DECREASING
            if (direction != previousDirection) {
                increasingDecreasingSegment = mutableListOf()
                increasingDecreasingSegments.add(
                        increasingDecreasingSegment)
            }
            increasingDecreasingSegment.add(it)
            previousItem = it
            previousDirection = direction
        }
        increasingDecreasingSegments.forEach {
            heap.add(PeekingIterator(it.iterator()))
        }
        while (heap.isNotEmpty()) {
            val iterator = heap.remove()
            result.add(iterator.next())
            if (iterator.hasNext()) {
                heap.add(iterator)
            }
        }
        return result
    }

}