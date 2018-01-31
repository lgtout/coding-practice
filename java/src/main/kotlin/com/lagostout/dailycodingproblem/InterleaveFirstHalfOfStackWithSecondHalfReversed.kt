package com.lagostout.dailycodingproblem

import java.util.*

/*
1/30/18
This problem was asked by Google.
Given a stack of N elements, interleave the first half of the stack with the second half reversed using only one other queue. This should be done in-place.
Recall that you can only push or pop from a stack, and enqueue or dequeue from a queue.
For example, if the stack is [1, 2, 3, 4, 5], it should become [1, 5, 2, 4, 3]. If the stack is [1, 2, 3, 4], it should become [1, 4, 2, 3].
*/

fun <T> interleaveFirstHalfOfStackWithSecondHalfReversed(stack: Stack<T>, queue: Queue<T>) {
    val halfCount = stack.size / 2
    // Move all from stack to queue
    while (stack.isNotEmpty()) {
        queue.add(stack.pop())
    }
    // Move back half of queue to front
    (0 until (queue.size - halfCount)).forEach {
        queue.add(queue.remove())
    }
    // Move forward half of queue to stack
    while (stack.size < halfCount) {
        stack.push(queue.remove())
    }
    // Interleave queue and stack (in queue)
    (0 until queue.size).forEach {
        if (stack.isNotEmpty()) {
            queue.add(stack.pop())
        }
        queue.add(queue.remove())
    }
    // Move all from queue to stack
    while (queue.isNotEmpty()) {
        stack.push(queue.remove())
    }
    // Done!
}
