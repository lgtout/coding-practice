package com.lagostout.elementsofprogramminginterviews.searching

import java.util.*

/**
 * Problem 12.2.2 page 194
 */
fun findArrayEntryEqualToItsIndexWhenThereMayBeDuplicates(
        array: List<Int>): Int? {
    var entryEqualToIndex: Int? = null
    if (array.isEmpty()) return entryEqualToIndex
    data class Frame(val left: Int, val right: Int) {
        val mid: Int
            get() = (left - right) + right/2
    }
    val stack = LinkedList<Frame>().apply {
        push(Frame(0, array.lastIndex))
    }
    while (stack.isNotEmpty() || entryEqualToIndex != null) {
        val frame = stack.pop()
        if (with (frame) {
            left > right || left < 0 || right > array.lastIndex
        }) continue
        val mid = frame.mid
        val entry = array[mid]
        when {
            entry == mid -> entryEqualToIndex = entry
            entry < mid ->
                listOf(Frame(frame.left, array[entry]),
                        Frame(mid + 1, frame.right))
                        .reversed()
                        .forEach {
                            stack.push(it)
                        }
            else -> {
                listOf(Frame(array[entry], frame.right),
                        Frame(frame.left, mid - 1))
                        .forEach {
                            stack.push(it)
                        }
            }
        }
    }
    return entryEqualToIndex
}