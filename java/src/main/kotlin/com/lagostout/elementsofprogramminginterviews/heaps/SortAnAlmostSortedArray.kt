package com.lagostout.elementsofprogramminginterviews.heaps

import java.lang.IllegalArgumentException
import java.util.*

/**
 * Problem 11.3 page 181
 */
fun sortAlmostSortedArray(array: MutableList<Int>, k: Int) {
    if (k < 0) throw IllegalArgumentException("k must be >= 0")
    data class Entry(val value: Int, var index: Int) : Comparable<Entry> {
        override fun compareTo(other: Entry): Int {
            return value - other.value
        }
    }
    val heap = PriorityQueue<Entry>()
    var index = 0
    var j = 0
    // TODO Update entry index when swapping.  Or don't sort in-place.
    while (index < array.size) {
        while (j < minOf(index + k + 1, array.size)) {
            heap.add(Entry(array[j], j))
            j++
        }
        val entry = heap.poll()
        array[entry.index] = array[index]
        array[index] = entry.value
        index++
    }
}
