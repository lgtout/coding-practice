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
    val set = sortedSetOf<Pair<Int, PeekingIterator<Int>>>(Comparator {
        o1, o2 ->
        (o1.second.peek().compareTo(o2.second.peek())).let {
            // If the numbers in the 2 iterators are equal,
            // break the tie using the Pair's "id".
            if (it == 0) o1.first.compareTo(o2.first) else it
        }
    }).apply {
        listOf(first, second, third).map {
            PeekingIterator<Int>(it.iterator())
        }.forEachIndexed { index, iterator ->
            add(Pair(index, iterator))
        }
    }
    println()
    println(set.size)
    println(set)
    data class Entries(val first: Int, val second: Int, val third: Int) {
        constructor (list: List<Int>): this(list[0], list[1], list[2])
        val distance: Int
            get() = this.third - this.first

    }
    var closestEntries: Entries? = null
    var distanceBetweenLowestAndHighestEntries: Int
    var done = false
    while (!done) {
        val iteratorWithLowestEntry = set.first()
        val lowestEntry = iteratorWithLowestEntry.second.peek()
        distanceBetweenLowestAndHighestEntries =
                set.last().second.peek() - lowestEntry
        closestEntries = closestEntries?.let {
            if (distanceBetweenLowestAndHighestEntries < it.distance) {
                Entries(set.map { it.second.peek() })
            } else it
        } ?: set.map {
            it.second.peek()
        }.let {
            Entries(it)
        }
        // Advance the iterator and update BST
        set.apply {
            iteratorWithLowestEntry.let {
                remove(it)
                it.second.next()
                if (it.second.hasNext())
                    add(it)
                else done = true
                println(it)
            }
        }

    }
    return closestEntries?.let {
        Triple(it.first, it.second, it.third)
    }!!
}
