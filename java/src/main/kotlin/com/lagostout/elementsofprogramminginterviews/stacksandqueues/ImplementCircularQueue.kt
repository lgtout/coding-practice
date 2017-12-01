package com.lagostout.elementsofprogramminginterviews.stacksandqueues

class CircularQueue<T>(initialSize: Int) {
    @Suppress("UNCHECKED_CAST")
    var array = arrayOfNulls<Any>(initialSize) as Array<T?>
    var startIndex = 0
    var endIndex = 0
    fun enqueue(entry: T) {

    }
    fun dequeue(): T {
        throw IllegalStateException()
    }
    fun size(): Int {
        return 0
    }
}