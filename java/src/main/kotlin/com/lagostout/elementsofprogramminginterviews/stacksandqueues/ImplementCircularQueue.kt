package com.lagostout.elementsofprogramminginterviews.stacksandqueues

import java.util.*

/**
 * Problem 9.8 page 146
 */
@Suppress("UNCHECKED_CAST")
class CircularQueue<T> constructor(
        initialSize: Int = 0,
        private val arrayFactory: (Int) -> Array<T?>) {

    var array = arrayFactory(initialSize)
    private var startIndex = 0
    private var endIndex = 0
    private var _size = 0

    fun enqueue(entry: T) {
        if (_size == array.size) {
            Collections.rotate(array.asList(), -startIndex)
            array = arrayFactory((if (_size == 0) 1 else array.size ) * 2).apply {
                (0 until array.size).forEach {
                    set(it, array[it])
                }
            }
        }
        endIndex = (startIndex + _size) % array.size
        ++_size
        array[endIndex] = entry
    }

    fun dequeue(): T {
        val entry = array[startIndex]
        array[startIndex] = null
        --_size
        startIndex = (startIndex + 1) % array.size
        return entry!!
    }

    val size: Int
        get() { return _size }

}

inline fun <reified T> arrayFactory(): (Int) -> Array<T?> {
    return { size -> arrayOfNulls(size) }
}

inline fun <reified T> circularQueue(initialSize: Int = 0): CircularQueue<T> {
    return circularQueue(initialSize, arrayFactory())
}

fun <T> circularQueue(initialCapacity: Int = 0,
                      arrayFactory: (Int) -> Array<T?>): CircularQueue<T> {
    return CircularQueue(initialCapacity, arrayFactory)
}
