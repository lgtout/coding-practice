package com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi

import com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi.TowersOfHanoi.Pegs
import com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi.TowersOfHanoi.Peg
import com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi.TowersOfHanoi.RingMove
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
            with (stack.pop()) {
                when {
                    countOfRingsToMove == 1 -> {
                        from.pop().apply {
                            to.push(this)
                            operations.add(RingMove(from.position, to.position, this))
                        }
                    }
                    countOfRingsToMove > 1 -> {
                        val extraPeg = pegs.extra(listOf(from, to))

                        // Since this is a stack we need to push frames in
                        // reverse order of execution: from last operation to first.
                        // This ensures the last operation pushed will be
                        // the first operation executed.

                        // 3rd: Move rings set aside on extraPeg to toPeg
                        stack.push(copy(from = extraPeg, to = to,
                                countOfRingsToMove = countOfRingsToMove - 1))
                        // 2nd: Move bottom ring from fromPeg to toPeg
                        stack.push(Frame(from, to, 1))
                        // 1st: Set aside all rings but the bottom one: move them to extraPeg
                        stack.push(Frame(from, extraPeg, countOfRingsToMove - 1))
                    }
                    else -> Unit
                }
            }
        }
    }

}
