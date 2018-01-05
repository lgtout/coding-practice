package com.lagostout.elementsofprogramminginterviews.stacksandqueues

import java.util.*

/**
 * Problem 9.9 page 147
 */
class Queue<T> {

    private val inStack = LinkedList<T>()
    private val outStack = LinkedList<T>()

    val size: Int
        get() = inStack.size + outStack.size

    fun isEmpty(): Boolean {
        return size == 0
    }

    fun isNotEmpty(): Boolean {
        return !isEmpty()
    }

    fun enqueue(entry: T) {
        inStack.push(entry)
    }

    fun dequeue(): T {
        if (outStack.isEmpty()) {
            while (inStack.isNotEmpty()) {
                outStack.push(inStack.pop())
            }
        }
        if (outStack.isEmpty()) throw IllegalStateException()
        return outStack.pop()
    }

}