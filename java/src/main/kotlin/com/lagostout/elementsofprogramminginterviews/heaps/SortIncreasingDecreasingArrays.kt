package com.lagostout.elementsofprogramminginterviews.heaps

import org.apache.commons.collections4.iterators.PeekingIterator
import java.util.*

object SortIncreasingDecreasingArrays {

    enum class Direction {
        INCREASING, DECREASING;
    }

    fun sortIncreasingDecreasingArrays(array: List<Int>): List<Int> {
        val result = mutableListOf<Int>()
        val heap = PriorityQueue({
            iterator: PeekingIterator<Int>,
            otherIterator: PeekingIterator<Int> ->
            iterator.peek() - otherIterator.peek()
        })
        var previousItem: Int
        var previousDirection = Direction.INCREASING
        var increasingDecreasingSegment = mutableListOf<Int>()
        array.forEach {
            increasingDecreasingSegment.add(it)
            previousItem = it
            val direction = if (it == previousItem)
                previousDirection
            else if (it > previousItem) Direction.INCREASING
            else Direction.DECREASING
            if (direction != previousDirection) {
                heap.add(PeekingIterator(
                        increasingDecreasingSegment.iterator()))
                increasingDecreasingSegment = mutableListOf()
                previousDirection = direction
            }
        }
        while (heap.isNotEmpty()) {
            val iterator = heap.remove()
            result.add(iterator.next())
            heap.add(iterator)
        }
        return result
    }

}