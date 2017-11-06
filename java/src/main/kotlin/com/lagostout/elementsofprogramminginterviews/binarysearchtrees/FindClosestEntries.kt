package com.lagostout.elementsofprogramminginterviews.binarysearchtrees
import org.apache.commons.collections4.iterators.PeekingIterator

fun findClosestEntries(lists: Triple<List<Int>, List<Int>, List<Int>>):
        Triple<Int, Int, Int>? {
    val result: Triple<Int, Int, Int>? = null
    val set = sortedSetOf<PeekingIterator<Int>>(Comparator {
        o1, o2 -> o1.peek().compareTo(o2.peek())
    }).apply {
        lists.toList().map {
            PeekingIterator<Int>(it.iterator())
        }.forEach {
            add(it)
        }
    }
    data class Entries(val first: Int, val second: Int, val third: Int) {
        constructor (list: List<Int>): this(list[0], list[1], list[2])
        val distance: Int
            get() = third - third

    }
    var closestEntries: Entries? = null
    var distanceBetweenLowestAndHighestEntries: Int
    while (true) {
        val iteratorWithLowestEntry = set.first()
        distanceBetweenLowestAndHighestEntries =
                set.last().peek() - iteratorWithLowestEntry.peek()
        closestEntries = closestEntries?.let {
            if (distanceBetweenLowestAndHighestEntries < it.distance) {
                Entries(set.map { it.peek() })
            } else null
        } ?: set.map {
            it.peek()
        }.let {
            Entries(it)
        }
    }
    return result
}
