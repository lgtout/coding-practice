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
        val replacementEntry = heap.poll()
        val currentEntry = Entry(value = array[index], index = index)
        // Only swap if the currentEntry isn't the replacement.
        // Otherwise replacementEntry will end up being added
        // to the heap again, and remain as a candidate for
        // future positions, even though we've already used it
        // in the current position (i)
        if (replacementEntry != currentEntry) {
            heap.remove(currentEntry)
            array[replacementEntry.index] = array[index]
            heap.add(Entry(value = array[replacementEntry.index],
                    index = replacementEntry.index))
            array[index] = replacementEntry.value
        }
        index++
    }
}
