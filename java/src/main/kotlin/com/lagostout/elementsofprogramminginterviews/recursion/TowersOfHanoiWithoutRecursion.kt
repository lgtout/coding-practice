package com.lagostout.elementsofprogramminginterviews.recursion

import com.lagostout.elementsofprogramminginterviews.recursion.TowersOfHanoi.Pegs
import com.lagostout.elementsofprogramminginterviews.recursion.TowersOfHanoi.Peg
import com.lagostout.elementsofprogramminginterviews.recursion.TowersOfHanoi.RingMove
import java.util.*

/**
 * Problem 16.1.2 page 290
 */
object TowersOfHanoiWithoutRecursion {

    fun transferRingsBetweenPegsWithoutRecursion(
            pegs: Pegs, fromPeg: Peg, toPeg: Peg,
            operations: MutableList<RingMove>) {
        data class Frame(val from: Peg, val to: Peg,
                         val countOfRingsToMove: Int)
        val stack = LinkedList<Frame>().apply {
            push(Frame(fromPeg, toPeg, fromPeg.size))
        }
        while (stack.isNotEmpty()) {
            val frame = stack.pop()
            if (frame.countOfRingsToMove == 1) {
                fromPeg.pop().apply {
                    toPeg.push(this)
                    operations.add(RingMove(fromPeg.position, toPeg.position, this))
                }
            }
            if (frame.countOfRingsToMove <= 1) continue
            val extraPeg = pegs.extra(listOf(fromPeg, toPeg))

            // Since this is a stack we need to push frames in
            // reverse order of execution: from last operation to first.
            // This ensures the last operation pushed will be
            // the first operation executed.

            // 3rd: Move rings set aside on extraPeg to toPeg
            stack.push(frame.copy(from = extraPeg, to = toPeg,
                    countOfRingsToMove = frame.countOfRingsToMove - 1))
            // 2nd: Move bottom ring from fromPeg to toPeg
            stack.push(Frame(fromPeg, toPeg, 1))
            // 1st: Set aside all rings but the bottom one: move them to extraPeg
            stack.push(Frame(fromPeg, extraPeg, frame.countOfRingsToMove - 1))
        }
    }

}
