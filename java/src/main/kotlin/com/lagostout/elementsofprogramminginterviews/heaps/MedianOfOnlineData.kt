package com.lagostout.elementsofprogramminginterviews.heaps

import java.util.*

fun medianOfOnlineData(data: List<Int>): Float? {

    data.apply {
        if (isEmpty()) return null
        else if (size == 1) return data[0].toFloat()
    }

    class Queues {

        var left = PriorityQueue<Int>()

        var right = PriorityQueue<Int>({
            o1, o2 -> o2.compareTo(o1)
        })

        val queuesBySize: List<PriorityQueue<Int>>
            get() {
                return if (left.size > right.size)
                    listOf(left, right) else listOf(right, left)
            }

        val needsRebalancing: Boolean
            get() = Math.abs(left.size - right.size) > 1

        val averageOfFirstItemInQueues: Float
            get() = (left.peek() - right.peek()) / 2F

    }

    val queues = Queues()
    var median: Number  = queues.run {
        left.add(data[0])
        right.add(data[1])
        averageOfFirstItemInQueues
    }
    data.takeLast(data.size - 2).forEach {
        if (it.toFloat() > median.toFloat()) {
            queues.right
        } else {
            queues.left
        }.add(it)
        with (queues) {
           while (needsRebalancing) {
               queuesBySize[0].add(queuesBySize[1].poll())
           }
        }
        val (left, right) = queues.queuesBySize
        median = if (left.size == right.size) {
            queues.averageOfFirstItemInQueues
        } else {
            right.peek()
        }
    }

    return median.toFloat()
}