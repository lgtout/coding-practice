package com.lagostout.elementsofprogramminginterviews.stacksandqueues

import java.util.*

@Suppress("UNCHECKED_CAST")
class CircularQueue<T>(initialSize: Int) {

    private var array = arrayOfNulls<Any>(initialSize) as Array<T?>
    private var startIndex = 0
    private var endIndex = 0
    private var _size = 0

    fun enqueue(entry: T) {
        if (_size == array.size) {
            Collections.rotate(array.asList(), -startIndex)
            array = (arrayOfNulls<Any>(_size * 2) as Array<T?>).apply {
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
        get() {
            return _size
        }

}