package com.lagostout.elementsofprogramminginterviews.stacksandqueues

import java.util.*

object SearchPostingsList {

    data class Entry<T>(val value: T,
                        var jumpNext: Entry<T>? = null,
                        var next: Entry<T>? = null,
                        var order: Int = -1)

    data class RawEntry<T>(val value: T, val jumpNext: Int? = null)

    fun <T> iterativeAssignJumpFirstOrder(head: Entry<T>) {
        val stack = LinkedList<Entry<T>>()
        var order = 0
        stack.add(head)
        while (!stack.isEmpty()) {
            val entry = stack.pop()
            if (entry.order >= 0) continue
            entry.order = order++
            listOf(entry.next, entry.jumpNext).forEach {
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

