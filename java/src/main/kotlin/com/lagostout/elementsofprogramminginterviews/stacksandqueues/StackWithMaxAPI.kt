package com.lagostout.elementsofprogramminginterviews.stacksandqueues

import sun.plugin.dom.exception.InvalidStateException

class Stack<T : Comparable<T>>  {

    val items = mutableListOf<T>()

    fun max(): T? {
        val otherStack = Stack<T>()
        var maxItem: T? = null
        while (isNotEmpty()) {
            val item = pop()
            otherStack.push(item)
            maxItem = maxItem?.let { if (item > it) item else it } ?: item
        }
        while (otherStack.isNotEmpty()) {
            push(otherStack.pop())
        }
        return maxItem
    }

    fun isNotEmpty(): Boolean {
        return items.isNotEmpty()
    }

    fun push(item: T) {
        items.add(item)
    }

    fun pop(): T {
        if (items.isEmpty())
            throw InvalidStateException("Cannot pop when stack is empty")
        return items.removeAt(items.lastIndex)
    }
}