package com.lagostout.elementsofprogramminginterviews.recursion

import com.lagostout.elementsofprogramminginterviews.recursion.TowersOfHanoi.Pegs
import com.lagostout.elementsofprogramminginterviews.recursion.TowersOfHanoi.Peg
import com.lagostout.elementsofprogramminginterviews.recursion.TowersOfHanoi.RingMove
import java.util.*

/**
 * Problem 16.1.3 page 290
 */
fun minimumNumberOfOperationsWithOnePegAlwaysInvolved(
        pegs: Pegs, fromPeg: Peg, toPeg: Peg,
        operations: MutableList<RingMove>) {
    data class Frame(val from: Peg, val to: Peg,
                     val countOfRingsToMove: Int)

    val stack = LinkedList<Frame>().apply {
        push(Frame(fromPeg, toPeg, fromPeg.size))
    }
    while (stack.isNotEmpty()) {
        with(stack.pop()) { }
        // TODO
    }
}
