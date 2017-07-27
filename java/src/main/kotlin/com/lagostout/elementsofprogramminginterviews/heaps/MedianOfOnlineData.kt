package com.lagostout.elementsofprogramminginterviews.heaps

import java.util.*

fun medianOfOnlineData(data: List<Int>): Float? {
    var median: Number? = null
    if (data.isEmpty()) return median?.toFloat()
    class Queues {
        var left = PriorityQueue<Int>()
        var right = PriorityQueue<Int>({
            o1, o2 -> o2.compareTo(o1)
        })
        val size: Int
            get() {
                return left.size + right.size
            }
    }
    val queues = Queues()
    data.forEach { number ->
        median = median?.run {
            val m = queues.run { (left.peek() + right.peek()) / 2F }
            if (number.toFloat() > this.toFloat()) {
                queues.right.add(this.toInt())
                queues.right.remove()
            } else run {

                null
            }
        }


    }
    return median?.toFloat()
}