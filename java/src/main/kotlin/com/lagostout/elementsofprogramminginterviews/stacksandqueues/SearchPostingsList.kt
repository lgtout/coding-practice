package com.lagostout.elementsofprogramminginterviews.stacksandqueues

import com.google.common.base.MoreObjects.toStringHelper
import java.util.*

/**
 * Problem 9.5 page 140
 */
object SearchPostingsList {

    data class Entry<T>(val value: T,
                        var jumpNext: Entry<T>? = null,
                        var next: Entry<T>? = null,
                        var order: Int = -1) {
        override fun toString(): String {
            return toStringHelper(this).add("value", value)
                    .add("order", order).toString()
        }
    }

    data class RawEntry<out T>(val value: T, val jumpNext: Int? = null)

    fun <T> iterativeAssignJumpFirstOrder(head: Entry<T>) {
        val stack = LinkedList<Entry<T>>()
        var order = 0
        stack.add(head)
        while (!stack.isEmpty()) {
            val entry = stack.pop()
            // If order has been set, we've
            // already explored this entry.
            if (entry.order >= 0) continue
            entry.order = order++
            listOf(entry.next, entry.jumpNext).filterNotNull().forEach {
                stack.push(it)
            }
        }
    }

    fun <T> recursiveAssignJumpFirstOrder(entry: Entry<T>) {
        if (entry.order >= 0) return
        listOf(entry.next, entry.jumpNext).filterNotNull().forEach {
            recursiveAssignJumpFirstOrder(it)
        }
    }

}

