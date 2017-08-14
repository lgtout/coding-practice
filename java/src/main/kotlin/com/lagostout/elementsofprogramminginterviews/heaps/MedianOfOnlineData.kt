package com.lagostout.elementsofprogramminginterviews.heaps

import java.util.*

/**
 * Problem 11.5 page 184
 */
object MedianOfOnlineData {

    class Queues {

        var left: PriorityQueue<Int> = PriorityQueue {
            o1, o2 -> o2.compareTo(o1)
        }

        var right: PriorityQueue<Int> = PriorityQueue ()

        fun sort() {
            val sortedQueues = listOf(left, right).sortedBy { it.peek() }
            left = sortedQueues[0]
            right = sortedQueues[1]
        }

        private fun rebalance() {
            if (left.size > right.size + 1) {
                right.add(left.poll())
            } else if (right.size > left.size + 1) {
                left.add(right.poll())
            }
        }

        private val averageOfFirstItemInQueues: Double
            get() = (left.peek() + right.peek()) / 2.0

        val median: Double
            get() {
                return if (isBalanced) averageOfFirstItemInQueues
                else (if (left.size > right.size) left.peek()
                else right.peek()).toDouble()
            }

        private val isBalanced: Boolean
            get() = left.size == right.size

        fun add(number: Int) {
            ((if (left.isEmpty()) {
                left
            } else if (right.isEmpty()){
                right
            } else null)?:
                    (if (number.toDouble() > median) {
                        right
                    } else {
                        left
                    })).add(number)
            if (left.size == 1 && right.size == 1) {
                sort()
            }
            rebalance()
        }

        override fun toString(): String {
            return "Queues { left: $left, right: $right }"
        }
    }

    fun medians(numbers: List<Int>): List<Double> {
        val queues = Queues()
        return numbers.map {
            queues.run {
                add(it)
//                println(this)
                median
            }
        }
    }

}
