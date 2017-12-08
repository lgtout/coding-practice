package com.lagostout.elementsofprogramminginterviews.stacksandqueues

import java.util.*

inline fun <reified T> arrayFactory(): (Int) -> Array<T?> {
    return { size -> arrayOfNulls(size) }
}

inline fun <reified T> circularQueue(initialSize: Int = 0): CircularQueue<T> {
    return circularQueue(initialSize, arrayFactory())
}

fun <T> circularQueue(initialSize: Int = 0,
                      arrayFactory: (Int) -> Array<T?>): CircularQueue<T> {
    return CircularQueue(initialSize, arrayFactory)
}

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
            array = arrayFactory(_size * 2).apply {
                (0 until size).forEach {
                    set(it, array[it])
                }
            }
        }
        ++_size
        endIndex = (startIndex + _size) % _size
        array[endIndex] = entry
    }

    fun dequeue(): T {
        val entry = array[startIndex]
        array[startIndex] = null
        startIndex = (startIndex + 1) % _size
        return entry!!
    }

    val size: Int
        get() { return _size }

}