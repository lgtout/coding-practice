package com.lagostout.elementsofprogramminginterviews.recursion

import com.lagostout.elementsofprogramminginterviews.recursion.TowersOfHanoi.Pegs
import com.lagostout.elementsofprogramminginterviews.recursion.TowersOfHanoi.Peg
import com.lagostout.elementsofprogramminginterviews.recursion.TowersOfHanoi.RingMove
import com.lagostout.elementsofprogramminginterviews.recursion.TowersOfHanoi.PegPosition
import java.util.*

/**
 * Problem 16.1.3 page 290
 */
fun minimumNumberOfOperationsWithOnePegAlwaysInvolved(
        pegs: Pegs, fromPeg: Peg, toPeg: Peg,
        operations: MutableList<RingMove>) {
    // We'll assume the right peg is the one that should
    // be involved in every operation.
    // TODO Make required peg a parameter.
    data class Frame(val from: Peg, val to: Peg,
                     val countOfRingsToMove: Int)
    val stack = LinkedList<Frame>().apply {
        push(Frame(fromPeg, toPeg, fromPeg.size))
    }
    val rightPegPosition = PegPosition.RIGHT
    val rightPeg = pegs.at(rightPegPosition)
    while (stack.isNotEmpty()) {
        stack.pop().let { frame ->
            if (frame.to != rightPeg) {
                listOf(Frame(frame.from, rightPeg, frame.countOfRingsToMove),
                        Frame(rightPeg, frame.to, frame.countOfRingsToMove)).reversed().forEach {
                    stack.push(it)
                }
            } else if (frame.to == rightPeg) {
                // TODO Is this right?  Have we covered all to/from cases?
            } else if (frame.from == rightPeg) {
                val extraPeg = pegs.extra(listOf(frame.from, frame.to))
                val ringCount = frame.countOfRingsToMove - 1
                listOf(Frame(frame.from, extraPeg, ringCount),
                        Frame(frame.from, frame.to, 1),
                        Frame(extraPeg, frame.to, ringCount)).reversed().forEach {
                    stack.push(it)
                }
            }
        }
    }
}
