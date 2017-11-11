package com.lagostout.elementsofprogramminginterviews.binarysearchtrees
import org.apache.commons.collections4.iterators.PeekingIterator

/**
 * Problem 15.6 page 269
 */
// We'll assume that the list have at least one entry, otherwise
// the problem statement wouldn't say to return one entry from
// each list.
fun findClosestEntries(first: List<Int>, second: List<Int>, third: List<Int>):
        Triple<Int, Int, Int> {
    val set = sortedSetOf<PeekingIterator<Int>>(Comparator {
        o1, o2 -> o1.peek().compareTo(o2.peek())
    }).apply {
        listOf(first, second, third).map {
            PeekingIterator<Int>(it.iterator())
        }.forEach {
            add(it)
        }
    }
    data class Entries(val first: Int, val second: Int, val third: Int) {
        constructor (list: List<Int>): this(list[0], list[1], list[2])
        val distance: Int
            get() = this.third - this.first

    }
    var closestEntries: Entries? = null
    var distanceBetweenLowestAndHighestEntries: Int
    while (true) {
        val iteratorWithLowestEntry = set.first()
        if (!iteratorWithLowestEntry.hasNext()) break
        val lowestEntry = iteratorWithLowestEntry.peek()
        distanceBetweenLowestAndHighestEntries =
                set.last().peek() - lowestEntry
        closestEntries = closestEntries?.let {
            if (distanceBetweenLowestAndHighestEntries < it.distance) {
                Entries(set.map { it.peek() })
            } else it
        } ?: set.map {
            it.peek()
        }.let {
            Entries(it)
        }
        // Advance the iterator and update BST
        set.apply {
            iteratorWithLowestEntry.let {
                remove(it)
                it.next()
                add(it)
            }
        }

    }
    return closestEntries?.let {
        Triple(it.first, it.second, it.third)
    }!!
}
