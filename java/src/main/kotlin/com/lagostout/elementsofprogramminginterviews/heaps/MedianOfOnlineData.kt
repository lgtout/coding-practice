package com.lagostout.elementsofprogramminginterviews.heaps

import java.util.*

fun medianOfOnlineData(data: List<Int>): Float? {
    val median: Float? = null
    val queues = listOf(PriorityQueue<Int>(), PriorityQueue<Int>({
        o1, o2 -> o2.compareTo(o1)
    }))
    if (data.isEmpty()) return median
    var lesserQueue: PriorityQueue<Int>
    var greaterQueue: PriorityQueue<Int>
    data.forEach {
//        val queue: PriorityQueue<Int> = if (median == null) {
//            lesserQueue = queues[1]
//            greaterQueue = queues[0]
//            queues[0]
//        } else {
//            if (it < median) {
//                if (queues[0].peek() )
//
//            } else {
//
//            }
//            null
//        }
//        queue.add(it)

    }
    return median
}