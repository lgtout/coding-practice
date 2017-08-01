package com.lagostout.elementsofprogramminginterviews.heaps

import java.util.*

// TODO Modify to take one number at a time and compute median at any point.
object MedianOfOnlineData {

    class Queues {

        var left: PriorityQueue<Int> = PriorityQueue ()
        var right: PriorityQueue<Int> = PriorityQueue {
            o1, o2 -> o2.compareTo(o1)
        }

        fun sort() {
            val sortedQueues = listOf(left, right).sortedBy { it.peek() }
            left = sortedQueues[0]
            right = sortedQueues[1]
        }

        fun rebalance() {
            if (left.size > right.size + 1) {
                right.add(left.poll())
            } else if (right.size > left.size + 1) {
                left.add(right.poll())
            }
        }

        val averageOfFirstItemInQueues: Double
            get() = (left.peek() + right.peek()) / 2.0

        val median: Double
            get() {
                return if (isBalanced) averageOfFirstItemInQueues
                else (if (left.size > right.size) left.peek()
                else right.peek()).toDouble()
            }

        var isBalanced: Boolean = false
            fun get() = left.size == right.size

        fun add(number: Int) {
            // TODO Not sure about this
            (if (left.isEmpty()) {
                left
            } else if (right.isEmpty()){
                right
            } else null )?: (
                if (number.toDouble() > median) {
                    right
                } else {
                    left
                }
            ).add(number)
            rebalance()
        }
    }

    val queues = Queues()

    fun medianAfterAdding(number: Int): Double {
        // TODO Not sure about this. Revisit 1,2-number cases
        queues.add(number)
        return queues.median
    }

}
